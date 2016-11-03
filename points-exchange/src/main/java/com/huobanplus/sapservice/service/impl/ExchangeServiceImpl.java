/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huobanplus.sapservice.commons.Constant;
import com.huobanplus.sapservice.commons.SapServiceEnum;
import com.huobanplus.sapservice.commons.bean.ApiResult;
import com.huobanplus.sapservice.commons.bean.ResultCode;
import com.huobanplus.sapservice.commons.ienum.EnumHelper;
import com.huobanplus.sapservice.entity.ExchangeGoods;
import com.huobanplus.sapservice.entity.ExchangeRecord;
import com.huobanplus.sapservice.entity.WxUser;
import com.huobanplus.sapservice.model.*;
import com.huobanplus.sapservice.repository.ExchangeRecordRepository;
import com.huobanplus.sapservice.repository.WxUserRepository;
import com.huobanplus.sapservice.service.ActivityInfoService;
import com.huobanplus.sapservice.service.ExchangeService;
import com.huobanplus.sapservice.utils.CherryAESCoder;
import com.huobanplus.sapservice.utils.HttpClientUtil;
import com.huobanplus.sapservice.utils.HttpResult;
import com.huobanplus.sapservice.utils.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.*;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

    private static final Log log = LogFactory.getLog(ExchangeServiceImpl.class);
    @Autowired
    private WxUserRepository wxUserRepository;
    @Autowired
    private ActivityInfoService activityInfoService;
    @Autowired
    private ActivityDate activityDate;
    @Autowired
    private ExchangeRecordRepository exchangeRecordRepository;


    @Override
    public ApiResult exchangePoints(ExchangeInfo exchangeInfo) {

        String exchageInfoJson = JSON.toJSONString(exchangeInfo);
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("brandCode", Constant.BRAND_CODE);
        try{
            String paramData = CherryAESCoder.encrypt(exchageInfoJson, Constant.AES_KEY);
            paramData = URLEncoder.encode(paramData, "utf-8");
            requestMap.put("paramData",paramData);

            log.info("requestJson:"+exchageInfoJson);

            HttpResult httpResult = HttpClientUtil.getInstance().get(Constant.BASE_URL,requestMap);
            if(httpResult.getHttpStatus() == HttpStatus.SC_OK){
                JSONObject resultJson = JSON.parseObject(httpResult.getHttpContent());
                if (resultJson != null) {
                    if ("0".equals(resultJson.getString("ERRORCODE").trim())) {// 执行成功
                        String resultMapJson = CherryAESCoder.decrypt(resultJson.get("ResultMap").toString(), Constant.AES_KEY);
                        ResultMap resultMap = JSONArray.parseObject(resultMapJson,ResultMap.class);
                      return ApiResult.resultWith(ResultCode.SUCCESS,resultMap);
                    } else {
                        int errorCode = resultJson.getInteger("ERRORCODE");
                        SapServiceEnum.ErrorCodeEnum errorCodeEnum = EnumHelper.getEnumType(SapServiceEnum.ErrorCodeEnum.class,errorCode);
                        if(errorCodeEnum != null){
                            return ApiResult.resultWith(ResultCode.ERROR,errorCodeEnum.getName(),null);
                        } else{
                            return ApiResult.resultWith(ResultCode.ERROR,"兑换失败",null);
                        }
                    }
                } else{
                    return ApiResult.resultWith(ResultCode.ERROR);
                }
            } else{
                return ApiResult.resultWith(ResultCode.ERROR);
            }

        } catch (Exception e){
            log.info(e.getMessage());
            return ApiResult.resultWith(ResultCode.ERROR);
        }

    }

    @Override
    public ApiResult memberInfoQuery(MemberInfoSearch memberInfoBean) {
        String dataJson = JSON.toJSONString(memberInfoBean);
        log.info("requestJson:"+dataJson);

        try {

            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("appID", Constant.APP_ID);
            requestMap.put("brandCode", Constant.BRAND_CODE);
            String paramData = CherryAESCoder.encrypt(dataJson, Constant.AES_KEY);
            requestMap.put("paramData",paramData);
            //发送请求
            HttpResult httpResult = HttpClientUtil.getInstance().get(Constant.BASE_URL, requestMap);
            //拿到返回值并解密
            if (httpResult.getHttpStatus() == HttpStatus.SC_OK) {
                JSONObject resultJson = JSON.parseObject(httpResult.getHttpContent());

                if (resultJson != null) {
                    if ("0".equals(resultJson.getString("ERRORCODE").trim())) {
                        String resultMap = CherryAESCoder.decrypt(resultJson.get("ResultContent").toString(), Constant.AES_KEY);
                        JSONArray myJsonArray = JSONArray.parseArray(resultMap);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("ResultContent", myJsonArray);
                        return ApiResult.resultWith(ResultCode.SUCCESS,myJsonArray);
                    } else {
                        return ApiResult.resultWith(ResultCode.ERROR,resultJson.getString("ERRORCODE"),null);
                    }
                } else{
                    return ApiResult.resultWith(ResultCode.ERROR);
                }
            } else{
                return ApiResult.resultWith(ResultCode.ERROR);
            }

        } catch (Exception e){
            log.info(e.getMessage());
            return ApiResult.resultWith(ResultCode.ERROR,e.getMessage(),null);
        }
    }

    @Override
    public synchronized ApiResult syncExchangePoints(String openId, String counterCode, String shopName, String shopAddr,
                                                     int[] level, int[] num, int points) {
        // 判断是否是第一次兑换，并且当前时间是在珀莱雅的优惠期间里，如果是，则优惠200积分，否则不优惠
        WxUser wxUser = wxUserRepository.findByOpenId(openId);

        // 再去实时的查询一下用户的积分是多少
        MemberInfoSearch memberInfoBean = new MemberInfoSearch();
        memberInfoBean.setBrandCode(Constant.BRAND_CODE);
        memberInfoBean.setTradeType("GetMemberInfo");
        memberInfoBean.setConditionType("MobilePhone");
        memberInfoBean.setCondition(openId);
        ApiResult apiResult = memberInfoQuery(memberInfoBean);
        if (apiResult.getResultCode() != ResultCode.SUCCESS.getResultCode()) {

        } else {
            JSONArray jsonArray = (JSONArray) apiResult.getData();
            if (jsonArray.size() > 0) {// 是会员
                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                int currentPoint = jsonObject.getIntValue("CurrentPoint");
//                wxUser = wxUserService.updateOrSaveUser(openId, currentPoint);
//                return ApiResult.resultWith(ResultCode.ERROR,"积分不足",null);
            } else {
                return ApiResult.resultWith(ResultCode.ERROR, "不是珀莱雅会员", null);
            }
        }

        if (wxUser.isFirstExchange()) {
            points = points - 200;// 优惠两百积分后在判断是否积分不足
        }
        if (points > wxUser.getPoints()) {
            return ApiResult.resultWith(ResultCode.ERROR, "积分不足", null);
        }
        int totalPoints = 0;

        if (wxUser != null) {
            ExchangeInfo exchangeInfo = createExchangeInfo(openId, counterCode, num, level, wxUser.isFirstExchange());

            apiResult = exchangePoints(exchangeInfo);
            if (apiResult.getResultCode() == ResultCode.SUCCESS.getResultCode()) {

                for (int i = 0; i < level.length; i++) {

                    ExchangeActivity activity = activityInfoService.findByLevel(level[i]);
                    totalPoints += activity.getPoints() * num[i];

                    ResultMap resultMap = (ResultMap) apiResult.getData();
                    // 保存积分兑换记录

                    ExchangeRecord exchangeRecord = new ExchangeRecord();
                    exchangeRecord.setExchangeCode(resultMap.getCouponCode());
                    exchangeRecord.setExchangeShop(shopName);
                    exchangeRecord.setVerification(false);
                    exchangeRecord.setStartDate(resultMap.getObtainFromDate());
                    exchangeRecord.setEndDate(resultMap.getObtainToDate());
                    exchangeRecord.setWxOpenId(wxUser.getOpenId());
                    exchangeRecord.setShopName(shopName);
                    exchangeRecord.setShopAddr(shopAddr);
                    exchangeRecord.setNum(num[i]);
                    exchangeRecord.setCreateTime(StringUtil.DateFormat(new Date(), StringUtil.TIME_PATTERN));

                    ExchangeGoods exchangeGoods = new ExchangeGoods();
                    exchangeGoods.setGoodsName(activity.getGiftsName()[activity.getGiftsName().length - 1]);
                    exchangeGoods.setExchangePoints(activity.getPoints());
                    exchangeGoods.setLevelCode(level[i]);
                    exchangeGoods.setUnitCode(StringUtil.parseArrarToCommaStr(activity.getGiftsCode()));
                    exchangeGoods.setBarCode(StringUtil.parseArrarToCommaStr(activity.getGiftsBarCode()));
                    exchangeGoods.setImgUrl(activity.getImgName());

                    exchangeRecord.setExchangeGoods(exchangeGoods);

                    exchangeRecordRepository.save(exchangeRecord);
                }


                boolean isFirstExchange = wxUser.isFirstExchange();


                String exchangeDate = StringUtil.DateFormat(new Date(), StringUtil.DATE_PATTERN);


                if (isFirstExchange && exchangeDate.compareTo(activityDate.getBenefitStartDate()) >= 0
                        && exchangeDate.compareTo(activityDate.getBenefitEndDate()) <= 0) {
                    wxUser.setPoints(wxUser.getPoints() - (totalPoints - 200));
                } else {
                    wxUser.setPoints(wxUser.getPoints() - totalPoints);
                }

                wxUser.setFirstExchange(false);

                wxUserRepository.save(wxUser);

                return ApiResult.resultWith(ResultCode.SUCCESS);
            } else {
                return ApiResult.resultWith(ResultCode.ERROR, apiResult.getResultMsg(), null);
            }
        } else {
            return ApiResult.resultWith(ResultCode.ERROR);
        }
    }

    /**
     * 创建积分兑换请求bean
     *
     * @param openId
     * @param counterCode
     * @param quantity
     * @param level
     * @param isFirstExchange
     * @return
     */
    private ExchangeInfo createExchangeInfo(String openId, String counterCode, int[] quantity, int[] level, boolean isFirstExchange) {

        ExchangeActivity exchangeActivity = activityInfoService.findByLevel(level[0]);

        ExchangeInfo exchangeInfo = new ExchangeInfo();

        exchangeInfo.setBrandCode(Constant.BRAND_CODE);
        exchangeInfo.setTradeType(Constant.TRADE_TYPE);
        exchangeInfo.setTicketType(0);
        exchangeInfo.setSubCampCode(exchangeActivity.getActivityCode());
        exchangeInfo.setSubType("PX");
        exchangeInfo.setBookDateTime(StringUtil.DateFormat(new Date(), StringUtil.TIME_PATTERN));
        exchangeInfo.setMemberCode(openId);
//        exchangeInfo.setCounterCode(counterCode);
        exchangeInfo.setCounterCodeGet("ALL");
        exchangeInfo.setDataSourse(3);
        exchangeInfo.setSendPos(1);
//        exchangeInfo.setModifyCounts(1);


        ExchangeDetail benefitExchangeDetail = null;

        String exchangeDate = StringUtil.DateFormat(new Date(), StringUtil.DATE_PATTERN);


        // 第一次兑换并且在活动期间,才有优惠
        if (isFirstExchange && exchangeDate.compareTo(activityDate.getBenefitStartDate()) >= 0
                && exchangeDate.compareTo(activityDate.getBenefitEndDate()) <= 0) {
            benefitExchangeDetail = new ExchangeDetail();
            benefitExchangeDetail.setSubCampCode(exchangeActivity.getActivityCode());
            benefitExchangeDetail.setDetailType("P");
            benefitExchangeDetail.setBarCode("DH999999");
            benefitExchangeDetail.setUnitCode("DH999999");
            benefitExchangeDetail.setQuantity(1);
            benefitExchangeDetail.setPrice(0);
            benefitExchangeDetail.setExPoint(-200);
        }

        List<ExchangeDetail> exchangeDetails = new ArrayList<>();

        for (int j = 0; j < level.length; j++) {

            ExchangeActivity activity = activityInfoService.findByLevel(level[j]);

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
                exchangeDetail.setQuantity(quantity[j]);
                exchangeDetail.setPrice(0);
                if (i == (giftsCode.length - 1)) {
                    exchangeDetail.setExPoint(activity.getPoints());
                } else {
                    exchangeDetail.setExPoint(0);
                }
                exchangeDetails.add(exchangeDetail);
            }
        }

        if (benefitExchangeDetail != null) {
            exchangeDetails.add(benefitExchangeDetail);
        }
        exchangeInfo.setDetailList(exchangeDetails);

        return exchangeInfo;
    }
}
