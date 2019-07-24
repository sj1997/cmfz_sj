package com.baizhi.service;

import com.baizhi.dto.DTO;
import com.baizhi.entity.User;
import com.baizhi.entity.UserDTO;
import com.baizhi.entity.UserRegist;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    DTO<User> queryByPage(Integer page,Integer rows);

    void remove(String s);

    void add(User user);

    void update(User user);
    /**
     * 查询所有导出表格
     */
    List<User> queryAll();
    /**
     * 根据id查询是否已存在
     */
    String queryById(String id);
    /**
     * 验证登录
     */
    Map<String,Object> queryByNameAndCode(User user);
    /**
     * 地区分布数据查询
     */
    List<UserDTO> queryMap();
    /**
     * 注册时间报表
     */
    List<UserRegist> queryRegist();

    /**
     * 验证手机号是否已注册
     * @param phone
     * @return
     */
    User queryByPhone(String phone);
    /**
     * 手机号登录
     */

}
