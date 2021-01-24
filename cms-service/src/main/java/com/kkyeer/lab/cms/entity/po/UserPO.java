package com.kkyeer.lab.cms.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: kkyeer
 * @Description: 用户实体
 * @Date:Created in 下午4:06 2021/1/23
 * @Modified By:
 */
@Data
@TableName("t_user")
public class UserPO {
    private Integer id;
    private String nickname;
}
