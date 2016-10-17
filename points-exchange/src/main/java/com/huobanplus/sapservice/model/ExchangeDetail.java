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
