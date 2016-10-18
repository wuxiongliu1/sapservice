package com.huobanplus.sapservice.service;

import com.huobanplus.sapservice.model.ExchangeActivity;

import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
public interface DataInitService {

    public List<ExchangeActivity> createExchangeActivityData();

    public List<ExchangeActivity> createLevel1200Activity();

    public List<ExchangeActivity> createLevel2200Activity();

    public List<ExchangeActivity> createLevel3200Activity();

    public List<ExchangeActivity> createLevel5000Activity();

    public ExchangeActivity findActivityByLevelAndMeal(int level,int meal);


}
