package com.baizhi.dao;

import com.baizhi.entity.Guru;

import java.util.List;

public interface GuruDAO extends BaseDAO<Guru> {

    void modify(Guru guru);
    void modifyStatus(Guru guru);

    void modifySex(Guru guru);

    List<Guru> selectAll();
}
