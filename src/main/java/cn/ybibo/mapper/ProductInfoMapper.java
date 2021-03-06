package cn.ybibo.mapper;

import cn.ybibo.pojo.ProductInfo;
import cn.ybibo.pojo.ProductInfoExample;
import java.util.List;

import cn.ybibo.pojo.vo.ProductInfoVo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

public interface ProductInfoMapper {
    int countByExample(ProductInfoExample example);

    int deleteByExample(ProductInfoExample example);

    int deleteByPrimaryKey(Integer pId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    List<ProductInfo> selectByExample(ProductInfoExample example);

    ProductInfo selectByPrimaryKey(Integer pId);

    int updateByExampleSelective(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByExample(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);


    //批量删除商品的功能
    int deleteBatch(String []ids);

    //多条件查询商品
    List<ProductInfo> selectCondition(ProductInfoVo infoVo);


}