package com.huobanplus.sapservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by wuxiongliu on 2016-10-14.
 */

@Entity
@Table(name = "Exchange_Goods")
@Setter
@Getter
public class ExchangeGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *  商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     *  商品兑换所需积分
     */
    @Column(name = "exchange_points")
    private Integer exchangePoints;

    /**
     *  商品图片
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     *  该产品的套餐类型码
     */
    private int levelCode;

    /**
     * 礼品编码
     */
    private String unitCode;

    /**
     * 礼品条码
     */
    private String barCode;




}
