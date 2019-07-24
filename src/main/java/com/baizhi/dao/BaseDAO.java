package com.baizhi.dao;

import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDAO<T>{
    /**
     * 分页查询
     */
    List<T> selectByPage(@Param("page")Integer page, @Param("pagenum")Integer pagenum);
    /**
     * 总条数
     */
    Integer selectZnum();
    /**
     * 删除
     */
    void delete(@Param("id")String id);
    /**
     *添加
     */
    void insert(T t);
}
