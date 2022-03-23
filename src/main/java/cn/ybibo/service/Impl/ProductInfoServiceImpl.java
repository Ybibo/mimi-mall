package cn.ybibo.service.Impl;

import cn.ybibo.mapper.ProductInfoMapper;
import cn.ybibo.pojo.ProductInfo;
import cn.ybibo.pojo.ProductInfoExample;
import cn.ybibo.pojo.vo.ProductInfoVo;
import cn.ybibo.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;


    @Override
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    //数据库语句：select * from table limit (pageNum-1)*pageSize,pageSize;
    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {

        // 分类插件使用分类PageHelper完成分页设置
        PageHelper.startPage(pageNum, pageSize);

        //进行pageInfo的数据封装
        //进行有条件的查询操作，必须要创建ProductInfoExample对象
        ProductInfoExample example = new ProductInfoExample();

        //设置主键（id）的降序操作
        //数据库操作：select * from table order by {p_id desc}
        example.setOrderByClause("p_id desc");

        //设置完排序后，取集合，切记：在取集合之前设置PageHelper.startPage(pageNum, pageSize);
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        //将查到的集合封装进PageInfo
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public ProductInfo getByID(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int delete(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }

    @Override
    public List<ProductInfo> selectCondition(ProductInfoVo infoVo) {
        return productInfoMapper.selectCondition(infoVo);
    }

    @Override
    public PageInfo<ProductInfo> splitPageVo(ProductInfoVo infoVo, int pageSize) {
        //取出集合之前，要先设置PageHelper.startPage（）属性
        PageHelper.startPage(infoVo.getPage(),pageSize);
        List<ProductInfo> list = productInfoMapper.selectCondition(infoVo);
        return new PageInfo<>(list);
    }



    @Override
    public int sava(ProductInfo info) {
        return productInfoMapper.insert(info);
    }


    public ProductInfoMapper getProductInfoMapper() {
        return productInfoMapper;
    }

    public void setProductInfoMapper(ProductInfoMapper productInfoMapper) {
        this.productInfoMapper = productInfoMapper;
    }
}
