package com.baizhi.service.serviceimpl;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.dto.DTO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDAO albumDAO ;
    @Autowired
    private ChapterDAO chapterDAO ;

    public void setAlbumDAO(AlbumDAO albumDAO) { this.albumDAO = albumDAO; }

    @Override
    public DTO<Album> queryByPage(Integer page, Integer rowNum) {
        Integer zpage = (page-1)*rowNum ;
        List<Album> list = albumDAO.selectByPage(zpage, rowNum);
        for (Album album : list) {
            Integer integer = chapterDAO.selectZnum(album.getId());
            album.setCount((integer.toString()));
        }
        Integer integer = albumDAO.selectZnum();
        DTO<Album> dto = new DTO<>();
        dto.setPage(page);
        dto.setTotal(integer%rowNum == 0?integer/rowNum:integer/rowNum+1);
        dto.setRecords(integer);
        dto.setRows(list);
        return dto ;
    }

    @Override
    @Transactional
    public String add(Album album) {
        UUID uuid = UUID.randomUUID();
        album.setId(uuid.toString());
        albumDAO.insert(album);
        return uuid.toString();
    }
    @Transactional
    @Override
    public void modifyImgPath(Album album) {
        albumDAO.updateCover(album);
    }

    /**
     * 根据id查询原路径
     * @param id
     * @return
     */
    @Override
    public String queryCover(String id) {
        String lujing = albumDAO.selectCover(id);
        return lujing ;
    }

    /**
     * 修改数据
     * @param album
     */
    @Transactional
    @Override
    public void update(Album album) {
        albumDAO.updatez(album);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional
    public void remove(String id) {
        albumDAO.delete(id);
    }
}
