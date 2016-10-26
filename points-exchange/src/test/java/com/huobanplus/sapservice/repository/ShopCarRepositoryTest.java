package com.huobanplus.sapservice.repository;

import com.huobanplus.sapservice.TestBase;
import com.huobanplus.sapservice.commons.SapServiceEnum;
import com.huobanplus.sapservice.entity.ActivityInfo;
import com.huobanplus.sapservice.entity.ShopCar;
import com.huobanplus.sapservice.utils.StringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-26.
 */
public class ShopCarRepositoryTest extends TestBase{

    @Autowired
    private ShopCarRepository shopCarRepository;
    @Autowired
    private ActivityInfoRepository activityInfoRepository;

    @Test
    @Rollback(value = false)
    public void testSave(){
        ShopCar shopCar = new ShopCar();
        shopCar.setWxOpenId("15946882414");
        shopCar.setCreateTime(StringUtil.DateFormat(new Date(),StringUtil.TIME_PATTERN));

        ActivityInfo activity1 = activityInfoRepository.findByActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_1200_B);
        shopCar.setActivityInfo(activity1);
        shopCarRepository.save(shopCar);
    }

    @Test
    public void testFind(){
        List<ShopCar> shopCar = shopCarRepository.findByWxOpenId("15946882414");
        shopCar.forEach(item->{

            System.out.println(item.getActivityInfo().getActivityLevel().getName());
        });
    }

}
