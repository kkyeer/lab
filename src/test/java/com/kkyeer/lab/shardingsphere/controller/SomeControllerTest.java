package com.kkyeer.lab.shardingsphere.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kkyeer.lab.shardingsphere.dao.IOrderDAO;
import com.kkyeer.lab.shardingsphere.dao.IUserDAO;
import com.kkyeer.lab.shardingsphere.entity.EOrder;
import com.kkyeer.lab.shardingsphere.entity.EUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 11:46 AM 2021/4/12
 * @Modified By:
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SomeControllerTest {
    @Autowired
    private IOrderDAO orderDAO;
    @Autowired
    private IUserDAO userDAO;

    @Test
    public void testOrder() {
        orderDAO.delete(new QueryWrapper<>());
        for (int i = 0; i < 10; i++) {
            EOrder order = EOrder.builder().build();
            order.setId(i);
            order.setOrderId(1000 + i);
            order.setUserId(2000 + i);
            orderDAO.insert(order);
        }
        assertEquals(10, orderDAO.selectList(new QueryWrapper<EOrder>()).size());
    }

    @Test
    public void testSpecificOrder(){
        EOrder order = orderDAO.selectById(0);
        assertEquals(1000, order.getOrderId());
        assertEquals(2000, order.getUserId());
    }

    @Test
    public void testUser() {
        userDAO.delete(new QueryWrapper<>());
        for (int i = 0; i < 10; i++) {
            EUser user = new EUser();
            user.setId(i);
            user.setUserId(2000 + i);
            user.setUsername("SU" + i);
            userDAO.insert(user);
        }
        assertEquals(10, userDAO.selectList(new QueryWrapper<EUser>()).size());
    }


}