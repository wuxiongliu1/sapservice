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
@Table(name = "Wx_User")
@Setter
@Getter
public class WxUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 微信用户id
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 微信用户名称
     */
    @Column(name = "wx_name")
    private String wxName;


    @Column(name = "wx_img_url")
    private String wxImgUrl;

    /**
     * 该微信用户的珀莱雅会员积分
     */
    @Column(name = "points")
    private int points;

    /**
     * 是否第一次兑换
     */
    @Column(name = "is_first_exchange")
    private boolean isFirstExchange;

}
