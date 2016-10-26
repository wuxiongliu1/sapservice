package com.huobanplus.sapservice.service;

import com.huobanplus.sapservice.entity.ShopCar;

/**
 * Created by wuxiongliu on 2016-10-26.
 */
public interface ShopCarService {

    void removeItem(String openId,int level);

    void updateShopCar(ShopCar shopCar,int level);
}
