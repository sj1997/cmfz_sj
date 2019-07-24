package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDAO {
    /**
     * 验证登录
     */
    public Admin selectByName(@Param("name")String name);
}
