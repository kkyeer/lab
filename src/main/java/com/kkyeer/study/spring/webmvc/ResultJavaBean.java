package com.kkyeer.study.spring.webmvc;

/**
 * @Author: kkyeer
 * @Description: 使用的Bean定义
 * @Date:Created in 14:31 2019/8/15
 * @Modified By:
 */
public class ResultJavaBean {
    private int code;
    private String msg;

    public ResultJavaBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
