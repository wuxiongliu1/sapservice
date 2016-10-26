package com.huobanplus.sapservice.service.impl;

import com.huobanplus.sapservice.entity.ShopCar;
import com.huobanplus.sapservice.repository.ActivityInfoRepository;
import com.huobanplus.sapservice.repository.ShopCarRepository;
import com.huobanplus.sapservice.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuxiongliu on 2016-10-26.
 */
@Service
public class ShopCarServiceImpl implements ShopCarService {

    @Autowired
    private ShopCarRepository shopCarRepository;
    @Autowired
    private ActivityInfoRepository activityInfoRepository;

    @Override
    public void removeItem(String openId, int level) {

    }

    @Override
    public void updateShopCar(ShopCar shopCar,int level) {

    }
}
