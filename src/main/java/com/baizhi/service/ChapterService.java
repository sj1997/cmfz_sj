package com.baizhi.service;

import com.baizhi.dto.DTO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;

public interface ChapterService {
    /**
     * 分页查询
     */
    DTO<Chapter> queryByPage(Integer page, Integer pagenum,String id);
    /**
     * 添加
     */
    String add(Chapter chapter, String id);
    /**
     * 修改路径
     */
    void modifyDownPath(Chapter chapter);
    /**
     * 根据id查询原路径
     */
    String queryDownPath(String id);

    void remove(String s);

    void update(Chapter chapter);

    /**
     * 修改大小
     * @param mb
     */
    void updateSize(String mb,String id);
}
