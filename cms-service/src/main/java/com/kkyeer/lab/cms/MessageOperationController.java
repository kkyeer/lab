package com.kkyeer.lab.cms;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kkyeer
 * @Description: 文章操作记录
 * @Date:Created in 3:58 PM 2021/1/11
 * @Modified By:
 */
@RestController
public class MessageOperationController {

    @PutMapping
    public boolean userLike(){
        return true;
    }
}
