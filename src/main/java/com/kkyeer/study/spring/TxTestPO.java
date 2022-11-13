package com.kkyeer.study.spring;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 上午8:47 2022/11/13
 * @Modified By:
 */
@TableName("tx_test")
@Data
public class TxTestPO {
    private Long id;
    private Integer version;
    private LocalDateTime updateTime;
}
