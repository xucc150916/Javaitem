package com.xucc.weixin_sell.dao;

import com.xucc.weixin_sell.entity.ProductInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 前面是映射的实体类，后面是主键的类型
 * @author xuche
 */
public interface IProductInfoDao extends JpaRepository<ProductInfoEntity, String> {
}
