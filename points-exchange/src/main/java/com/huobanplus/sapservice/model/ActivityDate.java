/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.model;

import lombok.Data;

/**
 * Created by wuxiongliu on 2016-10-21.
 */
@Data
public class ActivityDate {

    private String startDate;

    private String endDate;

    /**
     *  优惠200积分的开始日期
     */
    private String benefitStartDate;

    /**
     * 优惠200积分的截止日期
     */
    private String benefitEndDate;
}
