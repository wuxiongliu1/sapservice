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
