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
@Table(name = "Exchange_Record")
@Setter
@Getter
public class ExchangeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *  兑换码
     */
    @Column(name = "exchange_code")
    private String exchangeCode;

    /**
     *  兑换门店
     */
    @Column(name = "exchange_shop")
    private String exchangeShop;

    /**
     *  是否核销
     */
    @Column(name = "is_verification")
    private boolean isVerification;

    /**
     * 微信用户id
     */
    private String wxOpenId;

    /**
     * 领用开始日期
     */
    private String startDate;

    /**
     * 领用截止日期
     */
    private String endDate;

    /**
     * 记录生成日期
     */
    private String createTime;

    /**
     *  兑换的门店名称
     */
    private String shopName;

    /**
     *  兑换门店地址
     */
    private String shopAddr;

    /**
     *  兑换数量
     */
    private int num;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "goods_id")
    private ExchangeGoods exchangeGoods;

}
