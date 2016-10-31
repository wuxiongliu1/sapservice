/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.model;

import com.huobanplus.sapservice.commons.SapServiceEnum;
import lombok.Data;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
@Data
public class ExchangeActivity {

    /**
     *  活动代码
     */
    private String activityCode;

    /**
     *  活动档次
     */
    private SapServiceEnum.ActivityLevel activityLevel;

    /**
     *  礼品编码
     */
    private String[] giftsCode;

    /**
     * 礼品条码
     */
    private String[] giftsBarCode;

    /**
     *  礼品名称
     */
    private String[] giftsName;

    /**
     *  所需积分
     */
    private int points;

    /**
     * 套餐对应图片名称
     */
    private String imgName;

    /**
     * 是否可用
     */
    private int isEnable;

}
