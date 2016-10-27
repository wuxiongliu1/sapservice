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
 * Created by wuxiongliu on 2016-10-26.
 */

@Setter
@Getter
@Entity
@Table(name = "shop_car")
public class ShopCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "wx_openId")
    private String wxOpenId;

    @Column(name = "create_time")
    private String createTime;

    @OneToOne
    private ActivityInfo activityInfo;

    @Column(name = "num")
    private int num;
}
