/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.service;

import com.huobanplus.sapservice.commons.bean.ApiResult;
import com.huobanplus.sapservice.model.ExchangeInfo;
import com.huobanplus.sapservice.model.MemberInfoSearch;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
public interface ExchangeService {

    /**
     * 积分兑换
     * @param exchangeInfo
     * @return
     */
    public ApiResult exchangePoints(ExchangeInfo exchangeInfo);


    /**
     * 同步积分兑换
     *
     * @param openId
     * @param counterCode
     * @param shopName
     * @param shopAddr
     * @param level
     * @param num
     * @param points
     * @return
     */
    public ApiResult syncExchangePoints(String openId, String counterCode, String shopName, String shopAddr,
                                        int[] level, int[] num, int points);

    /**
     *  会员查询
     * @param memberInfoBean
     * @return
     */
    public ApiResult memberInfoQuery(MemberInfoSearch memberInfoBean);
}
