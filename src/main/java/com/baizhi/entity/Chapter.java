package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Chapter implements Serializable {
    private String id ;
    private String title ;
    private String size ;
    private String downPath ;
    private Date uploadTime ;
    private Album album ;

}
