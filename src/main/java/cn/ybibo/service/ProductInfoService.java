package cn.ybibo.service;

import cn.ybibo.pojo.ProductInfo;
import cn.ybibo.pojo.vo.ProductInfoVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductInfoService {

    //显示所有商品，不分页
    public List<ProductInfo> getAll();

    //分页功能实现
    public PageInfo splitPage(int pageNum,int pageSize);

    int sava(ProductInfo info);
    ProductInfo getByID(int pid);
    int update(ProductInfo info);
    int delete(int pid);
    int deleteBatch(String []ids);
    //多条件查询商品查询
    List<ProductInfo> selectCondition(ProductInfoVo infoVo);
    //多条件查询分页
    public  PageInfo splitPageVo(ProductInfoVo infoVo,int pageSize);
}
