package com.baizhi.service.serviceimpl;

import com.baizhi.dao.CarouselDAO;
import com.baizhi.dto.DTO;
import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselDAO carouselDAO ;

    public void setCarouselDAO(CarouselDAO carouselDAO) { this.carouselDAO = carouselDAO; }

    /**
     * 分页查询
     * @param page 页码
     * @return 相应DTO
     */
    @Override
    public DTO queryByPage(Integer page, Integer rowNum) {
        Integer zpage = (page-1)*rowNum ;
        List<Carousel> list = carouselDAO.selectByPage(zpage, rowNum);
        Integer integer = carouselDAO.selectZnum();
        DTO<Carousel> dto = new DTO<>();
        dto.setPage(page);
        dto.setTotal(integer%rowNum == 0?integer/rowNum:integer/rowNum+1);
        dto.setRecords(integer);
        dto.setRows(list);
        return dto ;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional
    public void remove(String id) {
        carouselDAO.delete(id);
    }

    /**
     * 添加
     * @param carousel
     * @return id
     */
    @Override
    @Transactional
    public String add(Carousel carousel) {
        UUID uuid = UUID.randomUUID();
        carousel.setId(uuid.toString());
        carouselDAO.insert(carousel);
        return uuid.toString();
    }

    /**
     * 修改图片路径
     * @param carousel
     */
    @Override
    public void modifyImgPath(Carousel carousel) {
        carouselDAO.update(carousel);
    }

    /**
     * 根据id查询图片路径
     * @param id
     * @return 路径
     */
    @Override
    public String queryImgPath(String id) {
        String s = carouselDAO.selectImgPath(id);
        return s ;
    }

    /**
     * 修改数据
     * @param carousel
     */
    @Override
    @Transactional
    public void update(Carousel carousel) {
        carouselDAO.updatex(carousel);
    }

    /**
     * 修改状态
     * @param carousel
     */
    @Override
    public void updateStatus(Carousel carousel) {
        carouselDAO.modifyStatus(carousel);
    }
}
