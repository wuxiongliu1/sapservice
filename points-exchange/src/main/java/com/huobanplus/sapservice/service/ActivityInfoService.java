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
 * Created by wuxiongliu on 2016-10-24.
 */
public interface ActivityInfoService {

    List<ExchangeActivity> findLevel1200Activity();

    List<ExchangeActivity> findLevel2200Activity();

    List<ExchangeActivity> findLevel3200Activity();

    List<ExchangeActivity> findLevel5000Activity();

    /**
     *  根据 levelgruop 查询
     *  levegroup 1: 1200档
     *  levegroup 2: 2200档
     *  levegroup 4: 3200档
     *  levegroup 4: 5000档
     * @param levelGroup
     * @return
     */
    List<ExchangeActivity> findByLevelGroup(int levelGroup);

    ExchangeActivity findByLevel(int level);
}
