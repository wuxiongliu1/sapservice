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
