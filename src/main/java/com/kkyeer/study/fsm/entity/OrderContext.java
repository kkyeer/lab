package com.kkyeer.study.fsm.entity;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 10:45 2023/1/7
 * @Modified By:
 */
public class OrderContext {
    private final StatedOrder order;

    public OrderContext(StatedOrder order) {
        this.order = order;
    }
}
