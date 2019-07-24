package com.baizhi.controller;

import com.baizhi.dto.DTO;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService ;

    public void setChapterService(ChapterService chapterService) { this.chapterService = chapterService; }

    @RequestMapping("/findByPage")
    public DTO findByPage(Integer page, Integer rows,String id){
        DTO<Chapter> chapterDTO = chapterService.queryByPage(page, rows,id);
        return chapterDTO ;
    }
    @RequestMapping("/ope")
    public String ope(String fid , String[] id , String oper, Chapter chapter){
        if(oper.equals("del")){
            for (String s : id) {
                chapterService.remove(s);
            }
        }else if(oper.equals("edit")){
            chapterService.update(chapter);
            return id[0] ;
        }else{
            String add = chapterService.add(chapter,fid);
            return add ;
        }
        return null ;
    }
    @RequestMapping("/upload")
    public void upload(String id , MultipartFile downPath, HttpServletRequest request , HttpServletResponse response){
        //文件上传
        String originalFilename = downPath.getOriginalFilename();
        System.out.println(originalFilename);
        long size = downPath.getSize();
        String MB = size/1024000+ "MB";
        chapterService.updateSize(MB,id);
        String path = request.getSession().getServletContext().getRealPath("music");
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        try {
            downPath.transferTo(new File(path,originalFilename));
            String s = chapterService.queryDownPath(id);
            File file1 = new File(path+s);
            if(file1.exists()){
                boolean delete = file1.delete();
            }
            //修改数据库的路径
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setDownPath(originalFilename);
            chapterService.modifyDownPath(chapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String name,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //指定要下载的文件所在路径
        String path = request.getServletContext().getRealPath("/music");
        String name1 = new String(name.getBytes(),"utf-8");
        URLEncoder.encode(name,"utf-8");
        //创建该文件对象
        File file = new File(path+File.separator+name);
        //设置响应头
        HttpHeaders headers = new HttpHeaders();
        //通知浏览器以下载的方式打开文件
        headers.setContentDispositionFormData("attachment",URLEncoder.encode(name,"utf-8"));
        //定义以流的形式下载返回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //使用Spring MVC框架的ResponseEntity对象封装返回下载数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.OK);

    }

}
