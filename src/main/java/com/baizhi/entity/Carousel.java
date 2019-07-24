package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carousel implements Serializable {
    private String id ;
    private String title ;
    private String status ;
    private String imgPath ;
    private Date createTime ;
}
