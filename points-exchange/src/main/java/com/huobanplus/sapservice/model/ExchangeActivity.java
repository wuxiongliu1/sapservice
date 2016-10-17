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

}
