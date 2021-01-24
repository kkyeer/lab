package com.kkyeer.lab.cms.entity.vo;

import com.kkyeer.lab.cms.entity.enums.ContentOpTypeEnum;

import java.util.Map;

/**
 * @Author: kkyeer
 * @Description: 文章操作记录
 * @Date:Created in 下午9:11 2021/1/23
 * @Modified By:
 */
public class ContentOpSummary {
   private int userId;
   private int contentId;
   private Map<ContentOpTypeEnum,Boolean> opState;

   public int getUserId() {
      return userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   public int getContentId() {
      return contentId;
   }

   public void setContentId(int contentId) {
      this.contentId = contentId;
   }

   public Map<ContentOpTypeEnum, Boolean> getOpState() {
      return opState;
   }

   public void setOpState(Map<ContentOpTypeEnum, Boolean> opState) {
      this.opState = opState;
   }
}
