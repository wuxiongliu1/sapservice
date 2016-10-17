package com.huobanplus.sapservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huobanplus.sapservice.commons.Constant;
import com.huobanplus.sapservice.commons.annotation.OpenID;
import com.huobanplus.sapservice.commons.bean.ApiResult;
import com.huobanplus.sapservice.commons.bean.ResultCode;
import com.huobanplus.sapservice.model.MemberInfoSearch;
import com.huobanplus.sapservice.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuxiongliu on 2016-10-12.
 */
@Controller
public class IndexController {

    @Autowired
    private ExchangeService exchangeService;

    @RequestMapping(value = "/index")
    public String index(@OpenID String openId, HttpServletRequest request, Model model) throws Exception {

        // 先判断该用户是否是珀莱雅的会员，如果不是，则进入会员注册页面
        MemberInfoSearch memberInfoBean = new MemberInfoSearch();
        memberInfoBean.setBrandCode(Constant.BRAND_CODE);
        memberInfoBean.setTradeType("GetMemberInfo");
        memberInfoBean.setConditionType("MobilePhone");
        memberInfoBean.setCondition(openId);
        ApiResult apiResult = exchangeService.memberInfoQuery(memberInfoBean);
        if(apiResult.getResultCode() != ResultCode.SUCCESS.getResultCode()){
            return "redirect:/";
        } else{
            JSONArray jsonArray = (JSONArray) apiResult.getData();
            if(jsonArray.size()>0){// 是会员
                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                int points = jsonObject.getIntValue("CurrentPoint");
                model.addAttribute("points",points);

                return "ExchangeGoodsList";
            } else{
                return "redirect:/";
            }

        }
    }

//    public

}
