package com.kkyeer.lab.shardingsphere.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kkyeer.lab.shardingsphere.entity.EOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 4:13 PM 2020/12/24
 * @Modified By:
 */
public interface IOrderDAO extends BaseMapper<EOrder> {
    @Insert(
            "insert into t_order(id,user_id,order_id) values(#{item.id},#{item.userId},#{item.orderId})"
    )
    void insertOrder(@Param("item") EOrder order);
    @Select(
            "select o.*,u.username from t_order o left join t_user u on o.user_id=u.user_id"
    )
    List<Map<String,Object>> allOrder();
}
