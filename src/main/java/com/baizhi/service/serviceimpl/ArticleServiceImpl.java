package com.baizhi.service.serviceimpl;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.dto.DTO;
import com.baizhi.entity.Article;
import com.baizhi.entity.Guru;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDAO articleDAO ;

    public void setArticleDAO(ArticleDAO articleDAO) { this.articleDAO = articleDAO; }
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public DTO<Article> queryByPage(Integer page, Integer pagenum, String id) {
        Integer zpage = (page-1)*pagenum ;
        List<Article> list = articleDAO.selectByPage(zpage, pagenum,id);
        Integer integer = articleDAO.selectZnum(id);
        DTO<Article> dto = new DTO<>();
        dto.setPage(page);
        dto.setTotal(integer%pagenum == 0?integer/pagenum:integer/pagenum+1);
        dto.setRecords(integer);
        dto.setRows(list);
        return dto ;
    }

    /**
     * 删除
     * @param s
     */
    @Override
    public void remove(String s) {
        articleDAO.delete(s);
    }

    /**
     * 添加
     * @param article
     */
    @Override
    public void add(Article article) {
        UUID uuid = UUID.randomUUID();
        article.setId(uuid.toString());
        article.setPublishTime(new Date());
        articleDAO.insert(article);
    }

    /**
     * 修改
     * @param article
     */
    @Override
    public void update(Article article) {
        articleDAO.modify(article);
    }

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Article queryxq(String id) {
        Article article = articleDAO.selectProfile(id);
        return article ;
    }
}
