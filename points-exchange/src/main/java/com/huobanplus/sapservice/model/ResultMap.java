/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
@Data
public class ResultMap {

    /**
     *  单据号
     */
    @JSONField(name = "billNo")
    private String billNo;

    /**
     *  验证码
     */
    @JSONField(name = "couponCode")
    private String couponCode;

    /**
     *  领用开始日期
     */
    @JSONField(name = "obtainFromDate")
    private String obtainFromDate;

    /**
     * 领用截止日期
     */
    @JSONField(name = "obtainToDate")
    private String obtainToDate;
}
