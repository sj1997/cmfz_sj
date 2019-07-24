package com.baizhi.controller;

import com.baizhi.dto.DTO;
import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService ;

    public void setCarouselService(CarouselService carouselService) { this.carouselService = carouselService; }


    @RequestMapping("/findByPage")
    @ResponseBody
    public DTO findByPage(Integer page, Integer rows){
        DTO carouselDTO = carouselService.queryByPage(page,rows);
        return carouselDTO ;
    }
    @RequestMapping("/ope")
    @ResponseBody
    public String  ope(String oper, String id, Carousel carousel){
        if(oper.equals("del")){
            if(id.contains(",")){
                String[] split = id.split(",");
                for (String s : split) {
                    carouselService.remove(s);
                }
            }else{
                carouselService.remove(id);
            }
        }else if(oper.equals("edit")){
            String s = carouselService.queryImgPath(id);
            carousel.setImgPath(s);
            carouselService.update(carousel);
            return id ;
        }else{
            if(carousel.getCreateTime() == null || ("").equals(carousel.getCreateTime())){
                carousel.setCreateTime(new Date());
            }
            if(carousel.getTitle() == null || ("").equals(carousel.getTitle())){
                carousel.setTitle("轮播图");
            }
            if(carousel.getImgPath() == null || ("").equals(carousel.getImgPath())){
                carousel.setImgPath("1.jpg");
            }
            String add = carouselService.add(carousel);
            return add ;
        }
        return null ;
    }
    @RequestMapping("/upload")
    @ResponseBody
    public void upload(String id , MultipartFile imgPath, HttpServletRequest request , HttpServletResponse response){
       if(imgPath != null){
           //文件上传
           String originalFilename = imgPath.getOriginalFilename();
           String path = request.getSession().getServletContext().getRealPath("carouselPic");
           File file = new File(path);
           if (!file.exists()){
               file.mkdir();
           }
           try {
               imgPath.transferTo(new File(path,originalFilename));
               String s = carouselService.queryImgPath(id);
               File file1 = new File(path+s);
               if(file1.exists()){
                   boolean delete = file1.delete();
               }
               //修改数据库的路径
               Carousel carousel = new Carousel();
               carousel.setId(id);
               carousel.setImgPath(originalFilename);
               carouselService.modifyImgPath(carousel);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }
    @RequestMapping("/updateStatus")
    public void updateStatus(String status, String id){
        Carousel carousel = new Carousel();
        carousel.setId(id);
        carousel.setStatus(status);
        carouselService.updateStatus(carousel);
    }
}
