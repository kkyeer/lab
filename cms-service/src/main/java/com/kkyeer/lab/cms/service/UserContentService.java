package com.kkyeer.lab.cms.service;

import com.kkyeer.lab.cms.entity.enums.ContentOpTypeEnum;
import com.kkyeer.lab.cms.entity.po.UserOpCache;
import com.kkyeer.lab.cms.entity.vo.ContentOpSummary;
import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: kkyeer
 * @Description: 用户服务
 * @Date:Created in 下午3:49 2021/1/23
 * @Modified By:
 */
@Service
public class UserContentService {
    @Autowired
    private UserRedisService userRedisService;

    /**
     * 将指定的用户操作状态存入Redis
     * @param userId 用户Id
     * @param userOpCache 用户操作状态
     */
    public void setUserOp(Integer userId, UserOpCache userOpCache){
        RedissonClient redissonClient = userRedisService.choose(userId);
        if (!redissonClient.isShutdown()) {
            redissonClient.getBitSet("uop_" + userId).set(userOpCache.getBitMapOffset(), true);
        }
    }

    /**
     * 删除指定的用户操作状态
     * @param userId 用户Id
     * @param userOpCache 用户操作状态
     */
    public void deleteUserOp(Integer userId, UserOpCache userOpCache){
        RedissonClient redissonClient = userRedisService.choose(userId);
        if (!redissonClient.isShutdown()) {
            redissonClient.getBitSet("uop_" + userId).clear(userOpCache.getBitMapOffset());
        }
    }

    /**
     * 查询指定用户操作缓存是否存在
     * @param userId 用户ID
     * @param userOpCache 用户操作缓存
     * @return 是否存在
     * @throws Exception key不存在
     */
    public boolean queryUserOpExistStatus(Integer userId, UserOpCache userOpCache) throws Exception{
        RedissonClient redissonClient = userRedisService.choose(userId);
        if (!redissonClient.isShutdown()) {
            String key = "uop_" + userId;
            long count = redissonClient.getKeys().countExists(key);
            if (count == 1) {
                return redissonClient.getBitSet(key).get(userOpCache.getBitMapOffset());
            } else if (count == 0) {
                throw new Exception("Key " + key + " not exists");
            } else {
                throw new Exception("Key " + key + " more than 1");
            }
        }
        throw new Exception("No cache");
    }

    /**
     * 用户点赞文章
     * @param userId 用户ID
     * @param contentId 文章ID
     */
    public void userLikeContent(int userId, int contentId) {
        UserOpCache cache = UserOpCache.builder()
                .userId(userId)
                .contentId(contentId)
                .contentOpTypeEnum(ContentOpTypeEnum.LIKE)
                .build();
        setUserOp(userId,cache);
    }

    /**
     * 用户取消点赞文章
     * @param userId 用户ID
     * @param contentId 文章ID
     */
    public void userUnlikeContent(int userId, int contentId) {
        UserOpCache cache = UserOpCache.builder()
                .userId(userId)
                .contentId(contentId)
                .contentOpTypeEnum(ContentOpTypeEnum.LIKE)
                .build();
        deleteUserOp(userId,cache);
    }

    /**
     * 查询指定用户对指定文章的所有操作状态
     * @param userId 用户ID
     * @param contentId 文章ID
     * @return 用户对指定文章的所有操作状态
     */
    public ContentOpSummary queryUserContentOpSummary(int userId, int contentId) throws Exception {
        ContentOpSummary summary = new ContentOpSummary();
        summary.setUserId(userId);
        summary.setContentId(contentId);
        summary.setOpState(new HashMap<>());
        for (ContentOpTypeEnum opTypeEnum : ContentOpTypeEnum.values()) {
            UserOpCache cache = UserOpCache.builder()
                    .userId(userId)
                    .contentId(contentId)
                    .contentOpTypeEnum(opTypeEnum)
                    .build();
            boolean exists = queryUserOpExistStatus(userId, cache);
            summary.getOpState().put(opTypeEnum, exists);
        }
        return summary;
    }
}
