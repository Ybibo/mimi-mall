package cn.ybibo.service.Impl;

import cn.ybibo.mapper.ProductTypeMapper;
import cn.ybibo.pojo.ProductType;
import cn.ybibo.pojo.ProductTypeExample;
import cn.ybibo.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());

    }

    public ProductTypeMapper getProductTypeMapper() {
        return productTypeMapper;
    }

    public void setProductTypeMapper(ProductTypeMapper productTypeMapper) {
        this.productTypeMapper = productTypeMapper;
    }
}
