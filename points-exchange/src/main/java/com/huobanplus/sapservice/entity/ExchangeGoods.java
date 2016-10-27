/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

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
