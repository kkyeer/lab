package com.kkyeer.study.spring.dal.ck;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kkyeer.study.spring.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 17:20 2023/5/27
 * @Modified By:
 */

@Repository
public interface SomeMapper extends BaseMapper<User> {
}
