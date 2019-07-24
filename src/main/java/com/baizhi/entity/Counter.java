package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Counter implements Serializable {
    private String id ;
    private String title ;
    private String count ;
    private Date createTime ;
    private User user ;
    private Course course ;
}
