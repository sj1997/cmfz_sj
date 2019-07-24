package com.baizhi.controller;

import com.baizhi.dto.DTO;
import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guru")
public class GuruController {
    @Autowired
    private GuruService guruService ;

    public void setGuruService(GuruService guruService) { this.guruService = guruService; }

    @RequestMapping("/findByPage")
    public DTO findByPage(Integer page,Integer rows){
        DTO<Guru> guruDTO = guruService.queryByPage(page, rows);
        return guruDTO ;
    }
    @RequestMapping("/ope")
    public void ope(String oper, String[] id , Guru guru){
        if(oper.equals("del")){
            for (String s : id) {
                guruService.remove(s);
            }
        }else if(oper.equals("edit")){
            guruService.update(guru);
        }else{
            guruService.add(guru);
        }
    }
    @RequestMapping("/updateStatus")
    public void updateStatus(String id,String status){
        Guru guru = new Guru();
        guru.setId(id);
        guru.setStatus(status);
        guruService.updateStatus(guru);
    }
    @RequestMapping("/updateSex")
    public void updateSex(String sex, String id){
        Guru guru = new Guru();
        guru.setId(id);
        guru.setSex(sex);
        guruService.updateSex(guru);
    }
    @RequestMapping("/queryAll")
    public List<Guru> queryAll(){
        List<Guru> gurus = guruService.queryAll();
        return gurus ;
    }
}
