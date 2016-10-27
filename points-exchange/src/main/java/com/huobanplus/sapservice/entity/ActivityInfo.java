/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.entity;

import com.huobanplus.sapservice.commons.SapServiceEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by wuxiongliu on 2016-10-24.
 */
@Setter
@Getter
@Table(name = "activity_info")
@Entity
public class ActivityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     *  活动代码
     */
    private String activityCode;

    /**
     *  活动档次
     */
    private SapServiceEnum.ActivityLevel activityLevel;

    /**
     * 1. 1200档
     * 2. 2200档
     * 3. 3200档
     * 4. 5000档
     */
    private int levelGroup;

    /**
     *  礼品编码 逗号分隔
     */
    private String giftsCode;

    /**
     * 礼品条码 逗号分隔
     */
    private String giftsBarCode;

    /**
     *  礼品名称 逗号分隔
     */
    private String giftsName;

    /**
     *  所需积分
     */
    private int points;

    /**
     * 套餐对应图片名称
     */
    private String imgName;

}
