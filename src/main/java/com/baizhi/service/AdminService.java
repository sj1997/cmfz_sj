package com.baizhi.service;

import com.baizhi.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    /**
     * 验证登录
     * @return 是否存在
     */
    Map<String,Object> queryByNameAndPassword(Admin admin);
}
