package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService ;

    public void setAdminService(AdminService adminService) { this.adminService = adminService; }
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> logig(Admin admin, String image, Model model){
        Map<String, Object> map = adminService.queryByNameAndPassword(admin);
        return map ;
    }
    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.invalidate();
        return "redirect:/back/login.jsp";
    }
}
