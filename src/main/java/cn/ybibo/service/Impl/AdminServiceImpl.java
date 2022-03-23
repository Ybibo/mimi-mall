package cn.ybibo.service.Impl;

import cn.ybibo.mapper.AdminMapper;
import cn.ybibo.pojo.Admin;
import cn.ybibo.pojo.AdminExample;
import cn.ybibo.service.AdminService;
import cn.ybibo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {


        //根据传入的用户或到DB中查询相应的用户对象
        AdminExample example = new AdminExample();
        //逆向工程生成的mapper文件，如何添加条件？
        //查询语句不要用*，因为spring注册的AdminExample中select是由一个个sql片段组成的
        //例子：添加用户名,类似拼接了一个在select中的where后拼接了 a_name={name}
        example.createCriteria().andANameEqualTo(name);

        List<Admin> admins = adminMapper.selectByExample(example);
        if (admins.size()>0){//size大于0，说明已经获取成功
            //这里有个假设，就是用户名具有唯一性
            Admin admin = admins.get(0);
            //如果查询到用户对象，再进行密码比对（md5密文），所以先加密明文再对比数据库
            String miPwd = MD5Util.getMD5(pwd);
            if (miPwd.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }

    public AdminMapper getAdminMapper() {
        return adminMapper;
    }

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }



}
