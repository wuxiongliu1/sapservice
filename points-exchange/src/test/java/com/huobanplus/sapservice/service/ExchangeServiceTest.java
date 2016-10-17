package com.huobanplus.sapservice.service;

import com.huobanplus.sapservice.TestBase;
import com.huobanplus.sapservice.commons.Constant;
import com.huobanplus.sapservice.commons.bean.ApiResult;
import com.huobanplus.sapservice.model.ExchangeDetail;
import com.huobanplus.sapservice.model.ExchangeInfo;
import com.huobanplus.sapservice.model.MemberInfoSearch;
import com.huobanplus.sapservice.utils.StringUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
public class ExchangeServiceTest extends TestBase{

    @Autowired
    private ExchangeService exchangeService;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(exchangeService);
    }

    @Test
    public void testExchangePoints(){
        ExchangeInfo exchangeInfo = new ExchangeInfo();
        exchangeInfo.setBrandCode(Constant.BRAND_CODE);
        exchangeInfo.setTradeType(Constant.TRADE_TYPE);
        exchangeInfo.setTicketType(0);
        exchangeInfo.setSubCampCode("AT1610110000018");
        exchangeInfo.setSubType("PX");
        exchangeInfo.setBookDateTime(StringUtil.DateFormat(new Date(),StringUtil.TIME_PATTERN));
        exchangeInfo.setMemberCode("13512112162");
        exchangeInfo.setCounterCode("ALL");
        exchangeInfo.setCounterCodeGet("0731004S");
        exchangeInfo.setDataSourse(3);
        exchangeInfo.setSendPos(0);
        exchangeInfo.setModifyCounts(1);

        List<ExchangeDetail> exchangeDetails = new ArrayList<>();
        ExchangeDetail exchangeDetail = new ExchangeDetail();
        exchangeDetail.setSubCampCode("AT1610110000018");
        exchangeDetail.setDetailType("N");
        exchangeDetail.setBarCode("10005818CX");
        exchangeDetail.setUnitCode("10005818");
        exchangeDetail.setQuantity(1);
        exchangeDetail.setPrice(0);
        exchangeDetail.setExPoint(0);
        exchangeDetails.add(exchangeDetail);

        exchangeInfo.setDetailList(exchangeDetails);

        ApiResult apiResult = exchangeService.exchangePoints(exchangeInfo);
        System.out.println("\n************************");
        System.out.println(apiResult.getResultCode());
        System.out.println(apiResult.getResultMsg());
        System.out.println(apiResult.getData());
        System.out.println("\n************************");

    }

    @Test
    public void testMemberInfoQuery(){
        MemberInfoSearch memberInfoBean = new MemberInfoSearch();
        memberInfoBean.setBrandCode(Constant.BRAND_CODE);
        memberInfoBean.setTradeType("GetMemberInfo");
        memberInfoBean.setConditionType("MobilePhone");
        memberInfoBean.setCondition("13512112162");

        ApiResult apiResult = exchangeService.memberInfoQuery(memberInfoBean);
        System.out.println("\n************************");
        System.out.println(apiResult.getResultCode());
        System.out.println(apiResult.getResultMsg());
        System.out.println(apiResult.getData());
        System.out.println("\n************************");
    }
}
