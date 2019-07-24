package com.baizhi.service;

import com.baizhi.dto.DTO;
import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumService {
    /**
     * 分页查询
     */
    DTO<Album> queryByPage(Integer page,Integer pagenum);
    /**
     * 添加
     */
    String add(Album album);
    /**
     * 修改路径
     */
    void modifyImgPath(Album album);

    /**
     * 根据id查询图片原路径
     * @param id
     * @return
     */
    String queryCover(String id);
    /**
     * 修改数据
     */
    void update(Album album);
    /**
     * 删除数据
     */
    void remove(String id);
}
