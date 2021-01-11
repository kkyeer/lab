package com.kkyeer.study.spring.webmvc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;

import java.util.StringJoiner;

/**
 * @Author: kkyeer
 * @Description: 从配置文件读取的配置
 * @Date:Created in  2020-2-6 20:10
 * @Modified By:
 */
@ConfigurationProperties(prefix = "app")
public class AppInfo {
    @NonNull
    private String name;
    @NonNull
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AppInfo.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("version='" + version + "'")
                .toString();
    }
}
