package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baizhi.annotation.UserAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Excel(name="编号")
    private String id ;
    @Excel(name="密码")
    private String password ;
    @Excel(name="盐")
    private String salt ;
    @Excel(name="手机号")
    private String phone ;
    @Excel(name="法名")
    private String dharmaName ;
    @Excel(name="省份")
    private String province ;
    @Excel(name="城市")
    private String city ;
    @Excel(name="性别")
    private String gender ;
    @Excel(name="个性签名")
    private String personalSign ;
    @Excel(name="姓名")
    private String profile ;
    @Excel(name="状态")
    private String status ;
    @Excel(name = "头像", type = 2 ,width = 20 , height = 10)
    private String cover ;
    @Excel(name="注册时间",format = "yyyy-MM-dd")
    private Date registTime ;
    @ExcelEntity(name="信仰上师")
    private Guru guru ;




}
