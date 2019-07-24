package com.baizhi.service;

import com.baizhi.dto.DTO;
import com.baizhi.entity.Article;

public interface ArticleService{
    /**
     * 分页查询
     */
    DTO<Article> queryByPage(Integer page, Integer pagenum, String id);

    void remove(String s);

    void add(Article article);

    void update(Article article);

    Article queryxq(String id);
}
