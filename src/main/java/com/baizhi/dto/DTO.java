package com.baizhi.dto;

import com.baizhi.entity.Carousel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTO<T> {
    private Integer page ;
    private Integer total ;
    private Integer records ;
    private List<T> rows ;
}
