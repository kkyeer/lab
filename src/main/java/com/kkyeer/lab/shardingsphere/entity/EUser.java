package com.kkyeer.lab.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: kkyeer
 * @Description: 用户
 * @Date:Created in 4:14 PM 2020/12/24
 * @Modified By:
 */
@Data
@TableName("t_user")
public class EUser {
    private Integer id;
    private int userId;
    private String username;
    private Date createTime;
}
