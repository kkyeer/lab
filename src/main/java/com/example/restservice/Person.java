package com.example.restservice;

import org.springframework.data.annotation.Id;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 下午6:29 2021/7/18
 * @Modified By:
 */
public class Person {

    @Id
    private long id;
    private String firstName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
