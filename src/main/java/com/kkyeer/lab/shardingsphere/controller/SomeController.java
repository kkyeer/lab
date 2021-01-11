package com.kkyeer.lab.shardingsphere.controller;

import com.kkyeer.lab.shardingsphere.dao.IOrderDAO;
import com.kkyeer.lab.shardingsphere.dao.IUserDAO;
import com.kkyeer.lab.shardingsphere.entity.EOrder;
import com.kkyeer.lab.shardingsphere.entity.EUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 3:55 PM 2020/12/24
 * @Modified By:
 */
@RestController
public class SomeController {
    @Autowired
    private IOrderDAO orderDAO;
    @Autowired
    private IUserDAO userDAO;

    @GetMapping("/hello")
    public String hello() {
        for (int i = 0; i < 10; i++) {
            EOrder order = new EOrder();
            order.setId(i);
            order.setOrderId(1000 + i);
            order.setUserId(2000 + i);
            orderDAO.insert(order);
        }
        return "ok";
    }

    @GetMapping("/hello2")
    public String hello2() {
        for (int i = 0; i < 10; i++) {
            EUser user = new EUser();
            user.setId(i);
            user.setUserId(2000 + i);
            user.setUsername("SU" + i);
            userDAO.insert(user);
        }
        return "ok";
    }

    @GetMapping("/hello3")
    public List<Map<String,Object>> hello3() {
        return orderDAO.allOrder();
    }
}
