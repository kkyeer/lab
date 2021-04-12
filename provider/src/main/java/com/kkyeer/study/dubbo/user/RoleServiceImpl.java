package com.kkyeer.study.dubbo.user;

import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @Author: kkyeer
 * @Description: 角色服务实现
 * @Date:Created in  2020-2-7 10:43
 * @Modified By:
 */
@Service(version = "1.0")
public class RoleServiceImpl implements RoleService {

    @Override
    public List<String> getRoleList() {
        return null;
    }
}
