package com.kkyeer.study.spring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkyeer.study.spring.TxTestPO;
import com.kkyeer.study.spring.dao.TxTestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 上午8:26 2022/11/13
 * @Modified By:
 */
@Service
@Slf4j
public class MyService extends ServiceImpl<TxTestMapper,TxTestPO> implements IService<TxTestPO> {


    public void tryUpdate(TxTestPO txTestPO) throws Exception {
        if (Objects.isNull(txTestPO.getId())) {
            throw new Exception("error update,must specify id");
        }
        updateById(txTestPO);
    }

    public void trySave(TxTestPO txTestPO){
        log.info("trySave-start");
        save(txTestPO);
        log.info("trySave-done");
        log.info("tryUpdate-start");
        try {
            tryUpdate(txTestPO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("tryUpdate-done");
    }

    public void innerMockUpdateFail(TxTestPO txTestPO) throws Exception {
        if (Objects.isNull(txTestPO.getId())) {
            throw new Exception("error update,must specify id");
        }
        updateById(txTestPO);
        throw new Exception("Mock fail");
    }

    public void testSaveBatch() {
        List<TxTestPO> list;
        list = genList();
        long start = System.currentTimeMillis();
        baseMapper.oneSQL(list);
        System.out.println("oneSQL cost:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        list = genList();
        saveBatch(list);
        System.out.println("saveBatch cost:" + (System.currentTimeMillis() - start));
        list = genList();
        start = System.currentTimeMillis();
        for (TxTestPO txTestPO : list) {
            save(txTestPO);
        }
        System.out.println("save for in cost:" + (System.currentTimeMillis() - start));
    }

    private static List<TxTestPO> genList() {
        List<TxTestPO> list;
        list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            TxTestPO txTestPO = new TxTestPO();
            txTestPO.setVersion(0);
            txTestPO.setUpdateTime(LocalDateTime.now());
            list.add(txTestPO);
        }
        return list;
    }
}
