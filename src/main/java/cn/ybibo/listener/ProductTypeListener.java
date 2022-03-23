package cn.ybibo.listener;



import cn.ybibo.pojo.ProductType;
import cn.ybibo.service.Impl.ProductTypeServiceImpl;
import cn.ybibo.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author 张阿荣
 * @version 1.0
 * @title: ProductTypeListener
 * @projectName SSM_XiaoMi_5
 * @description:
 * @date 2019/11/29   15:13
 */

@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //取出所有的商品类型，便于在增加和更新的页面上使用，或者前端的类型查询中使用
        //切记切记：只能手工从spring的Bean工厂中按名称取出类型的service
        //方法1
//        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//        ProductTypeService typeService= (ProductTypeService) context.getBean("ProductTypeServiceImpl");
//        方法2
//        ApplicationContext context = (ApplicationContext) servletContextEvent.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//        ProductTypeService typeService=(ProductTypeService) context.getBean("ProductService");

//方法3
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        ProductTypeServiceImpl typeService = context.getBean("ProductTypeServiceImpl", ProductTypeServiceImpl.class);
        List<ProductType> list=typeService.getAll();

        servletContextEvent.getServletContext().setAttribute("typelist",list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}



