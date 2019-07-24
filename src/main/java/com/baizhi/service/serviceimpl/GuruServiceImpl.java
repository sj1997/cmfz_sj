package com.baizhi.service.serviceimpl;

import com.baizhi.dao.GuruDAO;
import com.baizhi.dto.DTO;
import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDAO guruDAO ;

    public void setGuruDAO(GuruDAO guruDAO) { this.guruDAO = guruDAO; }

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public DTO<Guru> queryByPage(Integer page, Integer rows) {
        Integer zpage = (page-1)*rows ;
        List<Guru> list = guruDAO.selectByPage(zpage, rows);
        Integer integer = guruDAO.selectZnum();
        DTO<Guru> dto = new DTO<>();
        dto.setPage(page);
        dto.setTotal(integer%rows == 0?integer/rows:integer/rows+1);
        dto.setRecords(integer);
        dto.setRows(list);
        return dto ;
    }

    /**
     * 删除
     * @param s
     */
    @Transactional
    @Override
    public void remove(String s) {
        guruDAO.delete(s);
    }

    /**
     * 添加
     * @param guru
     */
    @Transactional
    @Override
    public void add(Guru guru) {
        UUID uuid = UUID.randomUUID();
        guru.setId(uuid.toString());
        guruDAO.insert(guru);
    }

    /**
     * 修改
     * @param guru
     */
    @Transactional
    @Override
    public void update(Guru guru) {
        guruDAO.modify(guru);
    }

    /**
     * 修改状态
     * @param guru
     */
    @Transactional
    @Override
    public void updateStatus(Guru guru) {
        guruDAO.modifyStatus(guru);
    }

    /**
     * 修改性别
     * @param guru
     */
    @Transactional
    @Override
    public void updateSex(Guru guru) {
        guruDAO.modifySex(guru);
    }

    /**
     * 查询所有
     */
    @Override
    public List<Guru> queryAll() {
        List<Guru> gurus = guruDAO.selectAll();
        return gurus ;
    }
}
