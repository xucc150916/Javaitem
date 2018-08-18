package com.xucc.weixin_sell.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 数据库product_info的实体映射类
 */
@Data
@Entity
public class ProductInfoEntity {
    /**
     * 商品id，并标识主键
     */
    @Id
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 商品图片
     */
    private String productIcon;

    /**
     * 商品状态，0表示在售，1表示下架
     */
    private Integer productStatus;

    /**
     * 商品类目
     */
    private Integer categoryType;
}
