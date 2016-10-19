package com.huobanplus.sapservice.repository;

import com.huobanplus.sapservice.TestBase;
import com.huobanplus.sapservice.entity.ShopInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-19.
 */
public class ShopInfoRepositoryTest extends TestBase {

    @Autowired
    private ShopInfoRepository shopInfoRepository;

    @Test
    public void testFindProvince() {
        List<String> shopInfoList = shopInfoRepository.findProvince();
        for (String shopInfo : shopInfoList) {
            System.out.println(shopInfo);
        }
    }

    @Test
    public void testFindCityByProvince() {
        String province = "河北";
//        List<ShopInfo> shopInfoList = shopInfoRepository.findCityByProvince(province);
//        System.out.println(shopInfoList);

    }

    @Test
    public void testFindShopNameByCity() {

        String city = "石家庄";
        List<ShopInfo> shopInfoList = shopInfoRepository.findShopInfoByCity(city);
        shopInfoList.forEach(shopInfo -> {
            System.out.print(shopInfo);
            System.out.println();
                }
        );
    }

//    @Test
//    public void testFindTest(){
//        String city = "石家庄";
//        Map<String,String> shops = shopInfoRepository.findTest(city);
//        System.out.println(shops);
//    }

}
