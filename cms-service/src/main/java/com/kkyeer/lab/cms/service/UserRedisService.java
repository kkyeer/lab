package com.kkyeer.lab.cms.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author: kkyeer
 * @Description: 基于用户ID的一致性哈希redis路由选择
 * @Date:Created in 上午10:31 2021/1/24
 * @Modified By:
 */
@Service
@Slf4j
public class UserRedisService {
    @Resource(name = "userRedisList")
    private List<RedissonClient> userRedisList;

    private final TreeMap<Long,RedissonClient> map = new TreeMap<>();
    private static final int EPHEMERAL_NODE_COUNT = 100;

    /**
     * 初始化一致性HashMap
     */
    @PostConstruct
    public void init(){
        for (RedissonClient redissonClient : userRedisList) {
            for (int i = 0; i < EPHEMERAL_NODE_COUNT; i++) {
                String nodeId = redissonClient.getConfig().useSingleServer().getAddress() + i;
                Long hash = murmurHash(nodeId);
                map.put(hash, redissonClient);
            }
        }
    }

    /**
     * 根据给定的用户ID通过一致性哈希返回缓存节点
     * @param userId 用户ID
     * @return 最近的缓存节点
     */
    public RedissonClient choose(Integer userId) {
        RedissonClient client = map.floorEntry(murmurHash("u_" + userId)).getValue();
        log.info("选中:{}",client.getConfig().useSingleServer().getAddress());
        return client;
    }

    /**
     *  MurMurHash算法，是非加密HASH算法，性能很高，
     *  比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免）
     *  等HASH算法要快很多，而且据说这个算法的碰撞率很低.
     *  http://murmurhash.googlepages.com/
     */
    private Long murmurHash(String key) {

        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }
}
