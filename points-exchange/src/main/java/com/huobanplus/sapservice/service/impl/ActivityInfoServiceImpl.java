/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.service.impl;

import com.huobanplus.sapservice.commons.SapServiceEnum;
import com.huobanplus.sapservice.commons.ienum.EnumHelper;
import com.huobanplus.sapservice.entity.ActivityInfo;
import com.huobanplus.sapservice.model.ExchangeActivity;
import com.huobanplus.sapservice.repository.ActivityInfoRepository;
import com.huobanplus.sapservice.service.ActivityInfoService;
import com.huobanplus.sapservice.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-24.
 */
@Service
public class ActivityInfoServiceImpl implements ActivityInfoService {

    @Autowired
    private ActivityInfoRepository activityInfoRepository;

    @Override
    public List<ExchangeActivity> findLevel1200Activity() {
        List<ActivityInfo> activityInfoList = activityInfoRepository.findByLevelGroup(1);
        return convertEntityToModel(activityInfoList);
    }

    @Override
    public List<ExchangeActivity> findLevel2200Activity() {
        List<ActivityInfo> activityInfoList = activityInfoRepository.findByLevelGroup(2);
        return convertEntityToModel(activityInfoList);
    }

    @Override
    public List<ExchangeActivity> findLevel3200Activity() {
        List<ActivityInfo> activityInfoList = activityInfoRepository.findByLevelGroup(3);
        return convertEntityToModel(activityInfoList);
    }

    @Override
    public List<ExchangeActivity> findLevel5000Activity() {
        List<ActivityInfo> activityInfoList = activityInfoRepository.findByLevelGroup(4);
        return convertEntityToModel(activityInfoList);
    }

    @Override
    public List<ExchangeActivity> findByLevelGroup(int levelGroup) {
        List<ActivityInfo> activityInfoList = activityInfoRepository.findByLevelGroup(levelGroup);
        return convertEntityToModel(activityInfoList);
    }

    @Override
    public ExchangeActivity findByLevel(int level) {
        SapServiceEnum.ActivityLevel activityLevel = EnumHelper.getEnumType(SapServiceEnum.ActivityLevel.class,level);
        ActivityInfo activityInfo = activityInfoRepository.findByActivityLevel(activityLevel);

        ExchangeActivity exchangeActivity = new ExchangeActivity();
        exchangeActivity.setActivityCode(activityInfo.getActivityCode());
        exchangeActivity.setActivityLevel(activityInfo.getActivityLevel());
        exchangeActivity.setGiftsCode(StringUtil.splitStrToArray(activityInfo.getGiftsCode(),","));
        exchangeActivity.setGiftsBarCode(StringUtil.splitStrToArray(activityInfo.getGiftsBarCode(),","));
        exchangeActivity.setGiftsName(StringUtil.splitStrToArray(activityInfo.getGiftsName(),","));
        exchangeActivity.setPoints(activityInfo.getPoints());
        exchangeActivity.setImgName(activityInfo.getImgName());

        return exchangeActivity;
    }

    private List<ExchangeActivity> convertEntityToModel(List<ActivityInfo> activityInfoList){
        List<ExchangeActivity> exchangeActivityList = new ArrayList<>();
        activityInfoList.forEach(activityInfo -> {
            ExchangeActivity exchangeActivity = new ExchangeActivity();
            exchangeActivity.setActivityCode(activityInfo.getActivityCode());
            exchangeActivity.setActivityLevel(activityInfo.getActivityLevel());
            exchangeActivity.setGiftsName(StringUtil.splitStrToArray(activityInfo.getGiftsName(),","));
            exchangeActivity.setGiftsBarCode(StringUtil.splitStrToArray(activityInfo.getGiftsBarCode(),","));
            exchangeActivity.setGiftsCode(StringUtil.splitStrToArray(activityInfo.getGiftsCode(),","));
            exchangeActivity.setImgName(activityInfo.getImgName());
            exchangeActivity.setPoints(activityInfo.getPoints());

            exchangeActivityList.add(exchangeActivity);
        });
        return exchangeActivityList;
    }
}
