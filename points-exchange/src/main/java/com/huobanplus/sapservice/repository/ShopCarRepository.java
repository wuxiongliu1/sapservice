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
