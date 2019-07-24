package com.baizhi.dao;

import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselDAO {
    /**
     * 分页查询
     */
    List<Carousel> selectByPage(@Param("page")Integer page,@Param("pagenum")Integer pagenum);
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
    void insert(Carousel carousel);
    /**
     * 修改
     */
    void update(Carousel carousel);
    /**
     * 根据id查询图片名称
     */
    String selectImgPath(@Param("id") String id);
    /**
     * 修改信息
     */
    void updatex(Carousel carousel);

    /**
     * 修改状态
     * @param carousel
     */
    void modifyStatus(Carousel carousel);
}
