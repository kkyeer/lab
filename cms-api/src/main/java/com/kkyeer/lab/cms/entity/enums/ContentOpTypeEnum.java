package com.kkyeer.lab.cms.entity.enums;

/**
 * @Author: kkyeer
 * @Description: Content op type enum
 * @Date:Created in 下午9:06 2021/1/23
 * @Modified By:
 */
public enum ContentOpTypeEnum {
    LIKE(1),COLLECT(2);
    private int value;

    ContentOpTypeEnum(int value) {
        this.value = value;
    }
}
