package com.baizhi.service.serviceimpl;

import com.baizhi.dao.UserDAO;
import com.baizhi.dto.DTO;
import com.baizhi.entity.User;
import com.baizhi.entity.UserDTO;
import com.baizhi.entity.UserRegist;
import com.baizhi.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO ;

    public void setUserDAO(UserDAO userDAO) { this.userDAO = userDAO; }


    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public DTO<User> queryByPage(Integer page, Integer rows) {
        Integer zpage = (page-1)*rows ;
        List<User> list = userDAO.selectByPage(zpage, rows);
        Integer integer = userDAO.selectZnum();
        DTO<User> dto = new DTO<>();
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
    @Override
    public void remove(String s) {
        userDAO.delete(s);
    }

    /**
     * 添加
     * @param user
     * @return
     */
    @Override
    public void add(User user) {
        userDAO.insert(user);
    }

    /**
     * 修改
     * @param user
     */
    @Override
    public void update(User user) {
        userDAO.modify(user);
    }
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<User> queryAll() {
        List<User> users = userDAO.selectAll();
        return users ;
    }
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public String queryById(String id) {
        String s = userDAO.selectById(id);
        return s ;
    }
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Map<String, Object> queryByNameAndCode(User user) {
        System.out.println(user);
        Map<String,Object> map = new HashMap<>();
       if(user.getPassword() != null){
           System.out.println(user+"-------------------");
           User user1 = userDAO.selectByName(user.getPhone());
           if(user1 != null){
               String salt = user1.getSalt();
               String s = DigestUtils.md5Hex(salt + user.getPassword() + salt);
               if(s.equals(user1.getPassword())){
                   if(user1.getStatus().equals("冻结")){
                       map.put("error",300);
                       map.put("message","该用户被冻结，请联系管理员：TEL：18888888888");
                       return map ;
                   }else{
                       map.put("error",200);
                       map.put("message",user1);
                       return map ;
                   }
               }else{
                   map.put("error",500);
                   map.put("message","密码输入错误");
                   return map ;
               }

           }else{
               map.put("error",400);
               map.put("message","用户名不存在");
               return map ;
           }
       }else{
           System.out.println(user+"==========================");
           User user1 = userDAO.selectByName(user.getPhone());
           if(user1 == null){
               map.put("error",400);
               map.put("message","用户名不存在");
               return map ;
           }else{
               if(user1.getStatus().equals("冻结")){
                   map.put("error",300);
                   map.put("message","该用户被冻结，请联系管理员：TEL：18888888888");
                   return map ;
               }else{
                   map.put("error",200);
                   map.put("message",user1);
                   return map ;
               }
           }
       }
    }
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<UserDTO> queryMap() {
        List<UserDTO> userDTOS = userDAO.selectMap();
        return userDTOS ;
    }

    /**
     * 注册时间报表数据
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<UserRegist> queryRegist() {
        List<UserRegist> userDTO1s = userDAO.selectRegist();
        return userDTO1s ;
    }

    /**
     * 验证手机号是否已注册
     * @param phone
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public User queryByPhone(String phone) {
        User user = userDAO.selectPhone(phone);
        return user ;
    }

}
