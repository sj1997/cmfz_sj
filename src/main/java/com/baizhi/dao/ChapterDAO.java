package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDAO {
    /**
     * 分页查询
     */
    List<Chapter> selectByPage(@Param("page")Integer page, @Param("pagenum")Integer pagenum,@Param("id")String id);
    /**
     * 总条数
     */
    Integer selectZnum(@Param("id")String id);

    void insert(Chapter chapter);
    /**
     * 修改路径
     */
    void update(Chapter chapter);

    /**
     * 根据id查询原路径
     * @param id
     */
    void selectDownPath(String id);

    /**
     * 删除
     * @param id
     */
    void delete(@Param("id") String id);

    /**
     * 修改数据
     * @param chapter
     */
    void updates(Chapter chapter);

    void modifySize(@Param("mb") String mb,@Param("id") String id);
}
