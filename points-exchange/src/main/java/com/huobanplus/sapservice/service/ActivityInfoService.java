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
