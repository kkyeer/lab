package com.kkyeer.lab.cms.entity.po;

import com.kkyeer.lab.cms.entity.enums.ContentOpTypeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: kkyeer
 * @Description: 存储用户信息的缓存实体
 * @Date:Created in 上午10:44 2021/1/24
 * @Modified By:
 */
@Data
@Builder
public class UserOpCache {
    private int userId;

    /**
     * 内容ID
     */
    private int contentId;

    /**
     * 操作类型 2bit
     */
    private ContentOpTypeEnum contentOpTypeEnum;

    /**
     * 计算在Bitmap中的偏移量
     *
     * @return Bitmap中的偏移量
     */
    public Integer getBitMapOffset() {
        return (contentId << 2) | (contentOpTypeEnum.ordinal());
    }
}
