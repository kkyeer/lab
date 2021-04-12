package com.kkyeer.lab.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 4:13 PM 2020/12/24
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order")
public class EOrder {
    private Integer id;
    private int orderId;
    private int userId;
    @TableField(exist = false)
    private Date createTime;
}
