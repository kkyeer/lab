package com.kkyeer.lab.cms.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: kkyeer
 * @Description: 内容实体
 * @Date:Created in 下午5:46 2021/1/23
 * @Modified By:
 */
@Data
@TableName("t_content")
public class ContentPO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
}
