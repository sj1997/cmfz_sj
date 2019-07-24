package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDAO {
    /**
     * 分页查询
     */
    List<Article> selectByPage(@Param("page")Integer page, @Param("pagenum")Integer pagenum, @Param("id")String id);
    /**
     * 总条数
     */
    Integer selectZnum(@Param("id")String id);

    void delete(String s);

    void insert(Article article);

    void modify(Article article);

    Article selectProfile(@Param("id")String id);
}
