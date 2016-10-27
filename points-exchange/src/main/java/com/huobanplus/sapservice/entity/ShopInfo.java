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
 * Created by wuxiongliu on 2016-10-19.
 * 柜台信息
 */
@Setter
@Getter
@Entity
@Table(name = "shop_info")
public class ShopInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String shopType;
    private String province;

    private String city;
    private String crmCode;
    private String shopCode;
    private String shopName;
    private String shopAddr;
    private String shopStatus;

}
