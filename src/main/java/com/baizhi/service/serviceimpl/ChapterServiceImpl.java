package com.baizhi.service.serviceimpl;

import com.baizhi.dao.ChapterDAO;
import com.baizhi.dto.DTO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDAO chapterDAO ;

    public void setChapterDAO(ChapterDAO chapterDAO) { this.chapterDAO = chapterDAO; }

    @Override
    public DTO<Chapter> queryByPage(Integer page, Integer rowNum,String id) {
        Integer zpage = (page-1)*rowNum ;
        List<Chapter> list = chapterDAO.selectByPage(zpage, rowNum,id);
        Integer integer = chapterDAO.selectZnum(id);
        DTO<Chapter> dto = new DTO<>();
        dto.setPage(page);
        dto.setTotal(integer%rowNum == 0?integer/rowNum:integer/rowNum+1);
        dto.setRecords(integer);
        dto.setRows(list);
        return dto ;
    }

    /**
     * 添加
     * @param chapter
     * @param id
     * @return
     */
    @Override
    @Transactional
    public String add(Chapter chapter, String id) {
        UUID uuid = UUID.randomUUID();
        chapter.setId(uuid.toString());
        Album album = new Album();
        album.setId(id);
        chapter.setAlbum(album);
        System.out.println(chapter+"---------------------");
        chapterDAO.insert(chapter);
        return uuid.toString();
    }

    /**
     * 修改路径
     * @param chapter
     */
    @Transactional
    @Override
    public void modifyDownPath(Chapter chapter) {
        chapterDAO.update(chapter);
    }

    /**
     * 根据id查询原路径
     * @param id
     * @return
     */
    @Override
    public String queryDownPath(String id) {
        chapterDAO.selectDownPath(id);
        return id ;
    }

    /**
     * 删除
     * @param s
     */
    @Transactional
    @Override
    public void remove(String s) {
        chapterDAO.delete(s);
    }

    /**
     * 修改数据
     * @param chapter
     */
    @Transactional
    @Override
    public void update(Chapter chapter) {
        chapterDAO.updates(chapter);
    }

    /**
     * 修改大小
     * @param mb
     */
    @Transactional
    @Override
    public void updateSize(String mb,String id) {
        chapterDAO.modifySize(mb,id);
    }
}
