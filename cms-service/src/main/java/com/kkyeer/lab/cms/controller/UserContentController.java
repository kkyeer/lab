package com.kkyeer.lab.cms.controller;

import com.kkyeer.lab.cms.entity.vo.ContentOpSummary;
import com.kkyeer.lab.cms.service.UserContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: kkyeer
 * @Description: controller
 * @Date:Created in 上午9:59 2021/1/24
 * @Modified By:
 */
@RestController
@RequestMapping("/user/content")
public class UserContentController {
    @Autowired
    private UserContentService userContentService;

    @PostMapping("/like")
    public String userLike(@RequestParam("uid") Integer userId, @RequestParam("cid") Integer contentId) {
        userContentService.userLikeContent(userId, contentId);
        return "success";
    }

    @PostMapping("/unlike")
    public String userUnLike(@RequestParam("uid") Integer userId, @RequestParam("cid") Integer contentId) {
        userContentService.userUnlikeContent(userId, contentId);
        return "success";
    }

    @GetMapping("/summary")
    public ContentOpSummary userContentSummary(@RequestParam("uid") Integer userId, @RequestParam("cid") Integer contentId) throws Exception {
        return userContentService.queryUserContentOpSummary(userId, contentId);
    }
}
