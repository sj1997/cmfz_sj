package com.baizhi.controller;

import com.baizhi.dto.DTO;
import com.baizhi.entity.Article;
import com.baizhi.entity.Guru;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService ;

    public void setArticleService(ArticleService articleService) { this.articleService = articleService; }


    @RequestMapping("/findByPage")
    public DTO findByPage(String id, Integer page, Integer rows){
        DTO<Article> articleDTO = articleService.queryByPage(page, rows, id);
        return articleDTO ;
    }
    @RequestMapping("/ope")
    public void ope(String[] id, String oper,Article article,String fid){
        if(oper.equals("del")){
            for (String s : id) {
                articleService.remove(s);
            }
        }else if(oper.equals("edit")){
            articleService.update(article);
        }else{
            articleService.add(article);

        }
    }
    @RequestMapping("/queryxq")
    public Article queryxq(String id, HttpServletRequest request){
        Article xq = articleService.queryxq(id);
        request.setAttribute("article",xq);
        System.out.println(xq+"--------------");
        return xq ;
    }
    @RequestMapping("/uploadArticle")
    public void uploadArticle(Article article){
        articleService.add(article);
    }
    @RequestMapping("/uploadPictrue")
    public Map<String,Object> uploadPictrue(MultipartFile file, HttpServletRequest request) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/pictrue");
        String originalFilename = file.getOriginalFilename();
        File file1 = new File(realPath);
        Map<String,Object> map = new HashMap<>();
        if(!file1.exists()){
            file1.mkdir();
        }
        file.transferTo(new File(realPath,originalFilename));
        map.put("error",0);
        map.put("url","http://localhost:9999/cmfz/pictrue/"+originalFilename);
        return map;
    }
    @RequestMapping("/allpictrue")
    public Map<String,Object> allpictrue(HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/pictrue");
        File file = new File(realPath);
        String[] list = file.list();
        Map<String , Object> map = new HashMap<>();
        map.put("current_url","http://localhost:9999/cmfz/pictrue/");
        map.put("total_count",list.length);
        List<Object> lists = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            String s = list[i];
            Map<String , Object> map1 = new HashMap<>();
            map1.put("is_dir",false);
            map1.put("has_file",false);
            File file1 = new File(realPath,s);
            long length = file1.length();
            map1.put("filesize",length);
            map1.put("is_photo",true);
            String substring = s.substring(s.lastIndexOf(".") + 1);
            map1.put("filetype",substring);
            map1.put("filename",s);
            map1.put("datetime",new Date());
            lists.add(map1);
        }
        map.put("file_list",lists);
        return map;
    }
}
