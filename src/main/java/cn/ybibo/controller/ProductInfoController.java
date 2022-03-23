package cn.ybibo.controller;


import cn.ybibo.pojo.ProductInfo;
import cn.ybibo.pojo.vo.ProductInfoVo;
import cn.ybibo.service.ProductInfoService;
import cn.ybibo.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoController {
    int PAGE_SIZE=5;

    @Autowired
    private ProductInfoService productInfoService;


    @RequestMapping("/getAll")
    public String getAll(Model model){

        List<ProductInfo> list = productInfoService.getAll();
        model.addAttribute("list",list);

        return "product";
    }

    @RequestMapping("/split")
    //第一页的五条
    private String split(HttpServletRequest request){

        PageInfo pageInfo =null;

        Object vo=request.getSession().getAttribute("prodVo");


        if (vo!=null){
            pageInfo=productInfoService.splitPageVo((ProductInfoVo) vo,PAGE_SIZE);
            request.getSession().removeAttribute("prodVo");
        }else {
            pageInfo = productInfoService.splitPage(1, PAGE_SIZE);
        }


//        pageInfo=productInfoService.splitPage(1,PAGE_SIZE);
        request.setAttribute("info",pageInfo);
        return "product";
    }



    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public void ajaxSplit(ProductInfoVo infoVo, HttpSession session){
        PageInfo info = productInfoService.splitPageVo(infoVo,PAGE_SIZE);
//        PageInfo info = productInfoService.splitPage(page, 5);
        session.setAttribute("info",info);
    }

    //多条件查询
    @ResponseBody
    @RequestMapping("/condition")
    public void condition(ProductInfoVo infoVo,HttpSession session){
        List<ProductInfo> infoList = productInfoService.selectCondition(infoVo);
        session.setAttribute("list",infoList);

    }

    String saveFileName;

    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
        //提取生成文件名UUID+上传图片的后缀+jpg +png
        saveFileName = FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目中图片储存的路径
        String path = request.getServletContext().getRealPath("/image_big");
//        System.out.println(path+"............path..........");
        //转存 以下效果类似这样：d:\idea workspace\mimissm\image_big\saveFileName
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回客户端json对象，封装图片路径，为了在页面实现立即回显
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);

//        System.out.println(object.toString()+"，object在这里");
        return object.toString();
    }

    @RequestMapping("/save")
    public String sava(ProductInfo info,HttpServletRequest request){
        info.setpImage(saveFileName);
        info.setpDate(new Date());

        //info有表单提交的五个数据，有异步ajax上传的数据，有上架日期数据
        int num=-1;
        try {
            num=productInfoService.sava(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num>0){
            request.setAttribute("msg","增加成功");
        }else {
            request.setAttribute("msg","增加失败");
        }
        //清空savaFileName的数据
        saveFileName="";
        //增加成功之后应该重新访问数据库，所以跳到分页显示页面
        return "forward:/prod/split.action";
    }

    @RequestMapping("/one")
    public String one(int pid,ProductInfoVo infoVo,Model model,HttpSession session){
        ProductInfo info = productInfoService.getByID(pid);
        model.addAttribute("prod",info);
//放到session，更新后处理结束后分页时处理读取条件和页码进行处理
        session.setAttribute("prodVo",infoVo);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo info,HttpServletRequest request){
        //因为ajax的异步上传，
        // 如果有异步上传国，则savaFileName里有上传上来的图片名称，
        // 如果没有异步ajax上传的图片，则savaFileName=“”,
        // 实体类info使用隐藏表单域提供上来的pImage原始图片的名称
        if (!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }
        //完成更新处理
        int num=-1;
        try {
            num=productInfoService.update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num>0){
            //更新成功
//            request.setAttribute("msg","更新成功");
           request.getSession().setAttribute("msg","更新成功");
        }else {
            //更新失败
            request.getSession().setAttribute("msg","更新成功");
        }

        //处理完更新之后，savaFileName可能有数据
        //而下一次更新时要使用这个变量作为判断的依据，就会出错，所以必须清空saveFileName
        saveFileName="";

        return "forward:/prod/split.action";
    }



    @RequestMapping("/delete")
    public String delete(int pid,HttpServletRequest request){
        int num=-1;
        try {
            num=productInfoService.delete(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num>0){
            //删除成功
            request.setAttribute("msg","删除成功");
        }else {
            //删除失败
            request.setAttribute("msg","删除失败");
        }


        return "forward:/prod/deleteAjaxSplit.action";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit",produces = "rexr/html;charset=utf-8")
    public Object deleteAjaxSplit(HttpServletRequest request){
        //取得第一页数据
        PageInfo info = productInfoService.splitPage(1, PAGE_SIZE);
        request.getSession().setAttribute("info",info);
        return request.getAttribute("msg");

    }

    @RequestMapping("/deleteBatch")
    public String deleteBatch(String pids,HttpServletRequest request){

        //将上传的字符串拆解，形成商品id的字符串数组
        String []ps=pids.split(",");


        try {
            int num=productInfoService.deleteBatch(ps);
            if (num>0){
            request.setAttribute("msg","批量删除成功");
            }else {
                request.setAttribute("msg","批量删除失败");
            }
        } catch (Exception e) {
            request.setAttribute("msg","商品不可删除！");
            e.printStackTrace();
        }

        //增删改后用重定向跳转
        return "forward:/prod/deleteAjaxSplit.action";
    }




}
