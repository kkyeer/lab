package com.kkyeer.lab.shardingsphere.dao;

import com.kkyeer.lab.shardingsphere.entity.EUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 4:13 PM 2020/12/24
 * @Modified By:
 */
public interface IUserDAO {
    @Insert(
            "insert into t_user(id,user_id,username) values(#{item.id},#{item.userId},#{item.username})"
    )
    void insert(@Param("item") EUser user);
}
