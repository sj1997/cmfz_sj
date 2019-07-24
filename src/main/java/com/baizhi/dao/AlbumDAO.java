package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

public interface AlbumDAO extends BaseDAO<Album> {
    /**
     * 修改路径
     */
    void updateCover(Album album);

    String selectCover(@Param("id") String id);

    void updatez(Album album);
}
