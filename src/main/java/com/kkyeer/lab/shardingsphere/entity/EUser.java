package com.kkyeer.lab.shardingsphere.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: kkyeer
 * @Description: 用户
 * @Date:Created in 4:14 PM 2020/12/24
 * @Modified By:
 */
@Data
public class EUser {
    private int id;
    private int userId;
    private String username;
    private Date createTime;
}
