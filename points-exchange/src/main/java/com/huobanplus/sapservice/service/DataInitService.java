/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.service;

import com.huobanplus.sapservice.model.ExchangeActivity;

import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
public interface DataInitService {


    public List<ExchangeActivity> createLevel1200Activity();

    public List<ExchangeActivity> createLevel2200Activity();

    public List<ExchangeActivity> createLevel3200Activity();

    public List<ExchangeActivity> createLevel5000Activity();

}
