package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guru implements Serializable {
    @Excel(name= "上师编号")
    private String id ;
    @Excel(name= "上师姓名")
    private String name ;
    @Excel(name= "上师简介")
    private String profile ;
    @Excel(name= "上师状态")
    private String status ;
    @Excel(name= "上师性别")
    private String sex ;
}
