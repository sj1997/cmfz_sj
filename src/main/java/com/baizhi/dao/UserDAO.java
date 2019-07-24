package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserDTO;
import com.baizhi.entity.UserRegist;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO extends BaseDAO<User>{


    void modify(User user);
    /**
     * 查询所有导出表格
     */
    List<User> selectAll();
    /**
     * 根据id查询是否已存在
     */
    String selectById(@Param("id")String id);

    /**
     * 根据名字查询是否存在
     * @param name
     * @return
     */
    User selectByName(@Param("name") String name);
    /**
     * 地区分布数据查询
     */
    List<UserDTO> selectMap();
    /**
     * 注册时间报表
     */
    List<UserRegist> selectRegist();

    /**
     * 验证手机号是否已注册
     * @param phone
     * @return
     */
    User selectPhone(@Param("phone") String phone);
}
