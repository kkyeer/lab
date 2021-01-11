package com.kkyeer.lab.shardingsphere.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 4:13 PM 2020/12/24
 * @Modified By:
 */
@Data
public class EOrder {
    private int id;
    private int orderId;
    private int userId;
    private Date createTime;
}
