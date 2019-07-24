package com.baizhi.service;

import com.baizhi.dto.DTO;
import com.baizhi.entity.Guru;

import java.util.List;

public interface GuruService {
    /**
     * 分页查询
     */
    DTO<Guru> queryByPage(Integer page, Integer rows);

    /**
     * 删除
     * @param s
     */
    void remove(String s);

    /**
     * 添加
     * @param guru
     */
    void add(Guru guru);

    void update(Guru guru);
    /**
     * 修改状态
     */
    void updateStatus(Guru guru);

    void updateSex(Guru guru);

    /**
     * 查询所有
     */
    List<Guru> queryAll();

}
