import cn.ybibo.mapper.ProductInfoMapper;
import cn.ybibo.pojo.ProductInfo;
import cn.ybibo.pojo.vo.ProductInfoVo;
import cn.ybibo.service.AdminService;
import cn.ybibo.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyTest {

    @Autowired
    ProductInfoMapper mapper;

    @Test
    public void testMD5Util(){
    String md5 = MD5Util.getMD5("123456");
    System.out.println(md5);

    }


    @Test
    public void testSeletCondition(){
        ProductInfoVo infoVo = new ProductInfoVo();
        infoVo.setPname("4");
        infoVo.setLprice(4000.0);
        List<ProductInfo> list = mapper.selectCondition(infoVo);
        list.forEach(productInfo -> System.out.println(productInfo) );
    }


}


