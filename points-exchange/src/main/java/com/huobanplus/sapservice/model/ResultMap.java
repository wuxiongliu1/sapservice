package com.huobanplus.sapservice.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
@Data
public class ResultMap {

    @JSONField(name = "billNo")
    private String billNo;

    @JSONField(name = "couponCode")
    private String couponCode;

    @JSONField(name = "obtainFromDate")
    private String obtainFromDate;

    @JSONField(name = "obtainToDate")
    private String obtainToDate;
}
