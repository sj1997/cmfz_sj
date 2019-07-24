package com.baizhi.controller;

import com.baizhi.dto.DTO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService ;

    public void setAlbumService(AlbumService albumService) { this.albumService = albumService; }



    @RequestMapping("/findByPage")
    public DTO findByPage(Integer page, Integer rows){
        DTO<Album> albumDTO = albumService.queryByPage(page, rows);
        return albumDTO ;
    }
    @RequestMapping("/ope")
    public String ope(Album album,String[] id,String oper){
        if(oper.equals("del")){
            for (String s : id) {
                albumService.remove(s);
            }
        }else if(oper.equals("edit")){
            albumService.update(album);
            return id[0] ;
        }else{
            String add = albumService.add(album);
            return add ;
        }
        return null ;
    }
    @RequestMapping("/upload")
    public void upload(String id , MultipartFile cover, HttpServletRequest request , HttpServletResponse response){
        //文件上传
        String originalFilename = cover.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("carouselPic");
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        try {
            cover.transferTo(new File(path,originalFilename));
            String s = albumService.queryCover(id);
            File file1 = new File(path+s);
            if(file1.exists()){
                boolean delete = file1.delete();
            }
            //修改数据库的路径
            Album album = new Album();
            album.setId(id);
            album.setCover(originalFilename);
            albumService.modifyImgPath(album);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
