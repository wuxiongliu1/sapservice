/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.repository;

import com.huobanplus.sapservice.entity.ShopInfo;
import com.huobanplus.sapservice.model.ShopModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wuxiongliu on 2016-10-19.
 */
@Repository
public interface ShopInfoRepository extends JpaRepository<ShopInfo,Integer> {

    @Query("select distinct s.province from ShopInfo s")
    List<String> findProvince();

    @Query("select distinct s.city from  ShopInfo s where s.province=?1")
    List<String> findCityByProvince(String province);

    @Query("select s from ShopInfo s where s.city=?1")
    List<ShopInfo> findShopInfoByCity(String city);
}
