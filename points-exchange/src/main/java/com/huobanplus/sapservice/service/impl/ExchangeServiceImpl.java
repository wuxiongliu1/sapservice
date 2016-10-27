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
import com.huobanplus.sapservice.model.ExchangeInfo;
import com.huobanplus.sapservice.model.MemberInfoSearch;
import com.huobanplus.sapservice.model.ResultMap;
import com.huobanplus.sapservice.service.ExchangeService;
import com.huobanplus.sapservice.utils.CherryAESCoder;
import com.huobanplus.sapservice.utils.HttpClientUtil;
import com.huobanplus.sapservice.utils.HttpResult;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Override
    public ApiResult exchangePoints(ExchangeInfo exchangeInfo) {

        String exchageInfoJson = JSON.toJSONString(exchangeInfo);
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("brandCode", Constant.BRAND_CODE);
        try{
            String paramData = CherryAESCoder.encrypt(exchageInfoJson, Constant.AES_KEY);
            paramData = URLEncoder.encode(paramData, "utf-8");
            requestMap.put("paramData",paramData);

            System.out.println("\n*******************");
            System.out.println("requestJson:"+exchageInfoJson);
            System.out.println("encrypt data:"+ paramData);
            System.out.println("\n*******************");

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
                }
                return ApiResult.resultWith(ResultCode.SUCCESS);
            } else{
                return ApiResult.resultWith(ResultCode.ERROR);
            }

        } catch (Exception e){
            return ApiResult.resultWith(ResultCode.ERROR);
        }

    }

    @Override
    public ApiResult memberInfoQuery(MemberInfoSearch memberInfoBean) {
        String dataJson = JSON.toJSONString(memberInfoBean);

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
            return ApiResult.resultWith(ResultCode.ERROR,e.getMessage(),null);
        }
    }
}
