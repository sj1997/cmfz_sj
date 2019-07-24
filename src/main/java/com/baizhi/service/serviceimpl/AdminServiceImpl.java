package com.baizhi.service.serviceimpl;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO ;
    @Autowired
    private HttpSession session ;

    public void setAdminDAO(AdminDAO adminDAO) { this.adminDAO = adminDAO; }

    /**
 * 验证登录
 * @return 是否是管理员
 */
    @Transactional(propagation =Propagation.SUPPORTS,readOnly = true)
    @Override
    public Map<String,Object> queryByNameAndPassword(Admin admin) {
        Map<String,Object> map = new HashMap<>();
        Admin admin1 = adminDAO.selectByName(admin.getUsername());
        if(admin1 == null ){
            map.put("code",400);
            map.put("message","用户名不存在");
            return map ;
        }else if(admin1 != null && admin1.getPassword().equals(admin.getPassword())){
            session.setAttribute("admin",admin1);
            map.put("code",200);
            return map ;
        }else{
            map.put("code",500);
            map.put("message","密码不正确");
            return map ;
        }
    }

}
