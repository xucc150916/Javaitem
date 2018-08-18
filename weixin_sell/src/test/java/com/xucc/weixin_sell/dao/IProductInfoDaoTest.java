package com.xucc.weixin_sell.dao;

import com.xucc.weixin_sell.entity.ProductInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j // 日志
public class IProductInfoDaoTest {

    @Autowired
    private IProductInfoDao iProductInfoDao;

    @Test
    public void saveTest() {
        ProductInfoEntity productInfoEntity = new ProductInfoEntity();
        productInfoEntity.setProductId("0");
        productInfoEntity.setProductName("xucc");
        productInfoEntity.setProductPrice(new BigDecimal(1.1));
        productInfoEntity.setProductStock(0);
        productInfoEntity.setProductDescription("xucc");
        productInfoEntity.setProductIcon("http://***");
        productInfoEntity.setProductStatus(0);
        productInfoEntity.setCategoryType(0);
        iProductInfoDao.save(productInfoEntity);
    }
}