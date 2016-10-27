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
public class ExchangeDetail {

    @JSONField(name = "SubCampCode")
    private String subCampCode;

    /**
     * 礼品类型
     */
    @JSONField(name = "DetailType")
    private String detailType;

    /**
     * 兑换礼品条码 必填
     */
    @JSONField(name = "BarCode")
    private String barCode;

    /**
     * 兑换礼品编码 必填
     */
    @JSONField(name = "UnitCode")
    private String unitCode;

    /**
     * 预约礼品数量 必填
     */
    @JSONField(name = "Quantity")
    private int quantity;

    /**
     * 礼品单价 必填
     */
    @JSONField(name = "Price")
    private double price;

    /**
     * 兑换积分 必填
     */
    @JSONField(name = "ExPoint")
    private int exPoint;
}
