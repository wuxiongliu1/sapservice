package com.huobanplus.sapservice.repository;

import com.huobanplus.sapservice.TestBase;
import com.huobanplus.sapservice.entity.ActivityInfo;
import com.huobanplus.sapservice.model.ExchangeActivity;
import com.huobanplus.sapservice.service.ActivityInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-24.
 */
public class ActivityRepositoryTest extends TestBase {

    @Autowired
    private ActivityInfoRepository activityInfoRepository;
    @Autowired
    private ActivityInfoService activityInfoService;

    @Test
    public void testFindByActivityLevel(){
        List<ActivityInfo> activityInfoList = activityInfoRepository.findByLevelGroup(1);
        System.out.println("\n*****************Data***************");
        System.out.println(activityInfoList.size());
        System.out.println("\n*****************Data***************");
    }

    @Test
    public void testFindByLevel(){
        int level = 1;
        for(int i=1;i<=16;i++) {
            ExchangeActivity exchangeActivity = activityInfoService.findByLevel(i);
            System.out.println(exchangeActivity.getImgName());
        }
    }

    @Test
    public void test(){
        List<ExchangeActivity> ls1 = activityInfoService.findLevel1200Activity();
        List<ExchangeActivity> ls2 = activityInfoService.findLevel2200Activity();
        List<ExchangeActivity> ls3 = activityInfoService.findLevel3200Activity();
        List<ExchangeActivity> ls4 = activityInfoService.findLevel5000Activity();
        System.out.println();

    }

}
