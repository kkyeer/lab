package com.kkyeer.lab.cms.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kkyeer.lab.cms.entity.enums.ContentOpTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @Author: kkyeer
 * @Description: 用户文章操作
 * @Date:Created in 下午8:50 2021/1/23
 * @Modified By:
 */
@Data
@TableName("t_user_content_op")
public class UserContentOperationPO {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private int contentId;
    private int userId;
    private ContentOpTypeEnum opType;
    private Date createTime;
}
