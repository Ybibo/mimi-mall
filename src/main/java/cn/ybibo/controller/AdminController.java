package cn.ybibo.controller;

import cn.ybibo.pojo.Admin;
import cn.ybibo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    @Qualifier("adminServiceImpl")
    private AdminService adminService;


    @RequestMapping("/login")
    public String login(String name, String pwd, Model model){
        Admin admin = adminService.login(name, pwd);
        if (admin!=null){
            //登陆成功
            model.addAttribute("admin",admin);
            return "main";
        }else {
            model.addAttribute("errmsg","用户名或密码错误");
            return "login";
        }
    }


}
