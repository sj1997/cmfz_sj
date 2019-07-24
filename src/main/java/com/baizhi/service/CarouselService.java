package com.baizhi.service;

import com.baizhi.dto.DTO;
import com.baizhi.entity.Carousel;

public interface CarouselService {
    /**
     * 分页查询
     */
    DTO queryByPage(Integer page, Integer rowNum);
    /**
     * 删除
     */
    void remove(String id);
    /**
     * 添加
     */
    String add(Carousel carousel);
    /**
     * 修改图片路径
     */
    void modifyImgPath(Carousel carousel);
    /**
     * 根据id 查询 图片路径
     */
    String queryImgPath(String id);
    /**
     * 修改数据
     */
    void update(Carousel carousel);

    void updateStatus(Carousel carousel);

}
