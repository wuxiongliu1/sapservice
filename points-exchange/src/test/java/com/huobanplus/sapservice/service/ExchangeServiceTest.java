package com.huobanplus.sapservice.service;

import com.huobanplus.sapservice.TestBase;
import com.huobanplus.sapservice.commons.Constant;
import com.huobanplus.sapservice.commons.bean.ApiResult;
import com.huobanplus.sapservice.model.ExchangeActivity;
import com.huobanplus.sapservice.model.ExchangeDetail;
import com.huobanplus.sapservice.model.ExchangeInfo;
import com.huobanplus.sapservice.model.MemberInfoSearch;
import com.huobanplus.sapservice.utils.AESSecurityUtil;
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
    @Autowired
    private ActivityInfoService activityInfoService;
    @Test
    public void testNotNull(){
        Assert.assertNotNull(exchangeService);
    }

    @Test
    public void testExchangePoints(){
//        ExchangeInfo exchangeInfo = new ExchangeInfo();
//        exchangeInfo.setBrandCode(Constant.BRAND_CODE);
//        exchangeInfo.setTradeType(Constant.TRADE_TYPE);
//        exchangeInfo.setTicketType(0);
//        exchangeInfo.setSubCampCode("AT1610240000001");
//        exchangeInfo.setSubType("PX");
//        exchangeInfo.setBookDateTime(StringUtil.DateFormat(new Date(),StringUtil.TIME_PATTERN));
//        exchangeInfo.setMemberCode("13512112162");
//        exchangeInfo.setCounterCode("ALL");
//        exchangeInfo.setCounterCodeGet("0731004S");
//        exchangeInfo.setDataSourse(3);
//        exchangeInfo.setSendPos(1);
//        exchangeInfo.setModifyCounts(1);
//
//        List<ExchangeDetail> exchangeDetails = new ArrayList<>();
//        ExchangeDetail detail1 = new ExchangeDetail();
//        detail1.setSubCampCode("AT1610240000001");
//        detail1.setDetailType("N");
//        detail1.setBarCode("10005818CX");
//        detail1.setUnitCode("10005818");
//        detail1.setQuantity(1);
//        detail1.setPrice(0);
//        detail1.setExPoint(0);
//        exchangeDetails.add(detail1);
//
//        ExchangeDetail detail2 = new ExchangeDetail();
//        detail2.setSubCampCode("AT1610240000002");
//        detail2.setDetailType("N");
//        detail2.setBarCode("10008210CX");
//        detail2.setUnitCode("10008210");
//        detail2.setQuantity(1);
//        detail2.setPrice(0);
//        detail2.setExPoint(0);
//        exchangeDetails.add(detail2);
//
//
//
//        exchangeInfo.setDetailList(exchangeDetails);

        ExchangeInfo exchangeInfo = createExchangeInfo("15067134476", 2, "0731004S", new int[]{1, 2}, false);

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

    private ExchangeInfo createExchangeInfo(String openId, int quantity,String counterCode,int[] meal, boolean isFirstExchange) {

        ExchangeActivity exchangeActivity = activityInfoService.findByLevel(meal[0]);

        ExchangeInfo exchangeInfo = new ExchangeInfo();

        exchangeInfo.setBrandCode(Constant.BRAND_CODE);
        exchangeInfo.setTradeType(Constant.TRADE_TYPE);
        exchangeInfo.setTicketType(0);
        exchangeInfo.setSubCampCode(exchangeActivity.getActivityCode());
        exchangeInfo.setSubType("PX");
        exchangeInfo.setBookDateTime(StringUtil.DateFormat(new Date(), StringUtil.TIME_PATTERN));
        exchangeInfo.setMemberCode(openId);
        exchangeInfo.setCounterCode(counterCode);// TODO: 2016-10-18
        exchangeInfo.setCounterCodeGet(counterCode);
        exchangeInfo.setDataSourse(3);
        exchangeInfo.setSendPos(0);
        exchangeInfo.setModifyCounts(1);// TODO: 2016-10-18

        List<ExchangeDetail> exchangeDetails = new ArrayList<>();

        for(int j=0;j<meal.length;j++){
            ExchangeActivity activity = activityInfoService.findByLevel(meal[j]);

            String[] giftsCode = activity.getGiftsCode();
            String[] giftsBarCode = activity.getGiftsBarCode();
            String[] giftsName = activity.getGiftsName();

            for (int i = 0; i < giftsCode.length; i++) {
                ExchangeDetail exchangeDetail = new ExchangeDetail();
                exchangeDetail.setSubCampCode(activity.getActivityCode());
                if (giftsCode[i].startsWith("DH")) {
                    exchangeDetail.setDetailType("P");
                } else {
                    exchangeDetail.setDetailType("N");
                }
                exchangeDetail.setBarCode(giftsBarCode[i]);
                exchangeDetail.setUnitCode(giftsCode[i]);
                exchangeDetail.setQuantity(1);
                exchangeDetail.setPrice(0);
                if (i == (giftsCode.length - 1)) {
                    exchangeDetail.setExPoint(isFirstExchange ? activity.getPoints() - 200 : activity.getPoints());// TODO: 2016-10-18
                } else {
                    exchangeDetail.setExPoint(0);
                }
                exchangeDetails.add(exchangeDetail);
            }
        }

        exchangeInfo.setDetailList(exchangeDetails);

        return exchangeInfo;
    }

    @Test
    public void testnothing() throws Exception {
        String encryptData = AESSecurityUtil.encrypt(Constant.PLY_PASSWORD,Constant.AES_KEY);
        System.out.println("\nencypt data:"+encryptData);

    }
}