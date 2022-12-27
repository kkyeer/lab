package com.kkyeer.study.spring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkyeer.study.spring.TxTestPO;
import com.kkyeer.study.spring.dao.TxTestMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 上午8:26 2022/11/13
 * @Modified By:
 */
@Service
public class MyService extends ServiceImpl<TxTestMapper,TxTestPO> implements IService<TxTestPO> {


    public void tryUpdate(TxTestPO txTestPO) throws Exception {
        if (Objects.isNull(txTestPO.getId())) {
            throw new Exception("error update,must specify id");
        }
        updateById(txTestPO);
    }

    public void mockUpdateFail(TxTestPO txTestPO) throws Exception {
        if (Objects.isNull(txTestPO.getId())) {
            throw new Exception("error update,must specify id");
        }
        updateById(txTestPO);
    }
}
