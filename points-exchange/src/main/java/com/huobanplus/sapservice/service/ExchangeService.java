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
     *  会员查询
     * @param memberInfoBean
     * @return
     */
    public ApiResult memberInfoQuery(MemberInfoSearch memberInfoBean);
}
