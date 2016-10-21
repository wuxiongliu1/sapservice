package com.huobanplus.sapservice.repository;

import com.huobanplus.sapservice.TestBase;
import com.huobanplus.sapservice.entity.ExchangeGoods;
import com.huobanplus.sapservice.entity.ExchangeRecord;
import com.huobanplus.sapservice.entity.WxUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-18.
 */
public class WxUserRepositoryTest extends TestBase{

    @Autowired
    private WxUserRepository wxUserRepository;

    @Test
//    @Rollback(value = false)
    public void testSave(){
        WxUser wxUser = new WxUser();
        wxUser.setOpenId("13512112162");

        List<ExchangeRecord> exchangeRecords = new ArrayList<>();
        for(int i=0;i<5;i++){
            ExchangeRecord exchangeRecord = new ExchangeRecord();
            exchangeRecord.setExchangeShop("test"+i);
            exchangeRecord.setExchangeCode("test_code"+i);

            ExchangeGoods exchangeGoods = new ExchangeGoods();
            exchangeGoods.setGoodsName("test_goods_name"+i);

            exchangeRecord.setExchangeGoods(exchangeGoods);
            exchangeRecords.add(exchangeRecord);
        }

        wxUserRepository.save(wxUser);
    }

    @Test
    public void testFind(){
        Long id = 1L;
        WxUser wxUser = wxUserRepository.findOne(id);
        System.out.println(wxUser);
    }
}
