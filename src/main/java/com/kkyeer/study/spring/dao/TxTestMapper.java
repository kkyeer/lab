package com.kkyeer.study.spring.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kkyeer.study.spring.TxTestPO;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Collection;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 上午8:46 2022/11/13
 * @Modified By:
 */
@Repository
public interface TxTestMapper extends BaseMapper<TxTestPO> {
    Integer insertBatchSomeColumn(Collection<TxTestPO> entityList);
    void oneSQL(List<TxTestPO> list);
}
