/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.repository;

import com.huobanplus.sapservice.entity.ActivityInfo;
import com.huobanplus.sapservice.entity.ShopCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-26.
 */
@Repository
public interface ShopCarRepository extends JpaRepository<ShopCar,Integer>{

    List<ShopCar> findByWxOpenId(String wxOpenId);

    ShopCar findByWxOpenIdAndActivityInfo(String wxOpenId, ActivityInfo activityInfo);
}
