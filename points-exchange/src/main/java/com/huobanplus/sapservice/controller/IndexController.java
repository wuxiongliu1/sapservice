package com.huobanplus.sapservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huobanplus.sapservice.commons.Constant;
import com.huobanplus.sapservice.commons.annotation.OpenID;
import com.huobanplus.sapservice.commons.bean.ApiResult;
import com.huobanplus.sapservice.commons.bean.ResultCode;
import com.huobanplus.sapservice.entity.ExchangeGoods;
import com.huobanplus.sapservice.entity.ExchangeRecord;
import com.huobanplus.sapservice.entity.ShopInfo;
import com.huobanplus.sapservice.entity.WxUser;
import com.huobanplus.sapservice.model.*;
import com.huobanplus.sapservice.repository.ExchangeRecordRepository;
import com.huobanplus.sapservice.repository.ShopInfoRepository;
import com.huobanplus.sapservice.repository.WxUserRepository;
import com.huobanplus.sapservice.service.ActivityInfoService;
import com.huobanplus.sapservice.service.DataInitService;
import com.huobanplus.sapservice.service.ExchangeService;
import com.huobanplus.sapservice.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-12.
 */
@Controller
@RequestMapping(value = "/sapservice")
public class IndexController {

    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private DataInitService dataInitService;
    @Autowired
    private WxUserRepository wxUserRepository;
    @Autowired
    private ExchangeRecordRepository exchangeRecordRepository;
    @Autowired
    private ShopInfoRepository shopInfoRepository;
    @Autowired
    private ActivityDate activityDate;
    @Autowired
    private ActivityInfoService activityInfoService;

    @RequestMapping(value = {"/index"})
    public String index(@RequestParam(name = "usermobile") String openId, HttpServletRequest request, Model model) throws Exception {


        // 先判断该用户是否是珀莱雅的会员，如果不是，则进入会员注册页面
        MemberInfoSearch memberInfoBean = new MemberInfoSearch();
        memberInfoBean.setBrandCode(Constant.BRAND_CODE);
        memberInfoBean.setTradeType("GetMemberInfo");
        memberInfoBean.setConditionType("MobilePhone");
        memberInfoBean.setCondition(openId);
        ApiResult apiResult = exchangeService.memberInfoQuery(memberInfoBean);
        if (apiResult.getResultCode() != ResultCode.SUCCESS.getResultCode()) {
            return "redirect:"+Constant.UNBIND_USER_REDIRECT_URL;
        } else {
            JSONArray jsonArray = (JSONArray) apiResult.getData();
            if (jsonArray.size() > 0) {// 是会员
                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                int points = jsonObject.getIntValue("CurrentPoint");


                WxUser wxUser = wxUserRepository.findByOpenId(openId);
                if (wxUser == null) {
                    wxUser = new WxUser();
                    wxUser.setPoints(points);// 首次积分
                    wxUser.setFirstExchange(true);
                    wxUser.setOpenId(openId);
                    wxUser.setWxName("");
                    wxUser.setWxImgUrl("");
                    wxUserRepository.save(wxUser);
                } else {
                    wxUser.setPoints(points);// TODO: 2016-10-18
                    wxUserRepository.save(wxUser);
                }

                List<ExchangeActivity> level1200ActivityList = activityInfoService.findByLevelGroup(1);
                List<ExchangeActivity> level2200ActivityList = activityInfoService.findByLevelGroup(2);
                List<ExchangeActivity> level3200ActivityList = activityInfoService.findByLevelGroup(3);
                List<ExchangeActivity> level5000ActivityList = activityInfoService.findByLevelGroup(4);
                model.addAttribute("points", wxUser.getPoints());
                model.addAttribute("openId", openId);
                model.addAttribute("level1200ActivityList", level1200ActivityList);
                model.addAttribute("level2200ActivityList", level2200ActivityList);
                model.addAttribute("level3200ActivityList", level3200ActivityList);
                model.addAttribute("level5000ActivityList", level5000ActivityList);

                return "ExchangeGoodsList";
            } else {
                return "redirect:"+Constant.UNBIND_USER_REDIRECT_URL;
            }

        }
    }


    @RequestMapping(value = "/toExchange")
    public String toExchangeDetail(@RequestParam(name = "openId") String openId, int level, int meal, Model model) {

        WxUser wxUser = wxUserRepository.findByOpenId(openId);
        int isFirstExchange = 0;
        int isBenefit = 0;
        if (wxUser == null) {
            // 去会员注册？
            return "redirect:/sapservice/index?usermobile="+openId;

        } else {
            isFirstExchange = wxUser.isFirstExchange() ? 1 : 0;
        }

        ExchangeActivity exchangeActivity = activityInfoService.findByLevel(meal);

        int enableExchange = 0;

        if (wxUser.isFirstExchange()) {
            if (wxUser.getPoints() >= (exchangeActivity.getPoints() - 200)) {
                enableExchange = 1;
            }
        } else {
            if (wxUser.getPoints() >= exchangeActivity.getPoints()) {
                enableExchange = 1;
            }
        }

        String exchangeDate = StringUtil.DateFormat(new Date(), StringUtil.DATE_PATTERN);


        if (wxUser.isFirstExchange() && exchangeDate.compareTo(activityDate.getBenefitStartDate()) >= 0
                && exchangeDate.compareTo(activityDate.getBenefitEndDate()) <= 0) {
            isBenefit = 1;
        } else{
            isBenefit = 0;
        }

        List<String> provinces = shopInfoRepository.findProvince();

        model.addAttribute("level", level);
        model.addAttribute("openId", openId);
        model.addAttribute("activity", exchangeActivity);
        model.addAttribute("isFirstExchange", isFirstExchange);
        model.addAttribute("userPoints", wxUser.getPoints());
        model.addAttribute("enableExchange", enableExchange);
        model.addAttribute("provinces", provinces);
        model.addAttribute("isBenefit",isBenefit);
        return "ExchangeDetail";
    }

    @RequestMapping(value = "/exchange", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ApiResult exchange(@RequestParam(name = "openId") String openId, int level, int meal, String counterCode, String shopName, String shopAddr) {
        // 判断是否是第一次兑换，并且当前时间是在珀莱雅的优惠期间里，如果是，则优惠200积分，否则不优惠
        WxUser wxUser = wxUserRepository.findByOpenId(openId);
        ExchangeActivity activity = activityInfoService.findByLevel(meal);

        if (wxUser != null) {
            ExchangeInfo exchangeInfo = createExchangeInfo(openId, counterCode, level, meal, wxUser.isFirstExchange());

            ApiResult apiResult = exchangeService.exchangePoints(exchangeInfo);
            if (apiResult.getResultCode() == ResultCode.SUCCESS.getResultCode()) {

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
                exchangeRecord.setCreateTime(StringUtil.DateFormat(new Date(), StringUtil.TIME_PATTERN));

                ExchangeGoods exchangeGoods = new ExchangeGoods();
                exchangeGoods.setGoodsName(activity.getGiftsName()[activity.getGiftsName().length - 1]);
                exchangeGoods.setExchangePoints(activity.getPoints());
                exchangeGoods.setLevelCode(meal);
                exchangeGoods.setUnitCode(StringUtil.parseArrarToCommaStr(activity.getGiftsCode()));
                exchangeGoods.setBarCode(StringUtil.parseArrarToCommaStr(activity.getGiftsBarCode()));
                exchangeGoods.setImgUrl(activity.getImgName());

                exchangeRecord.setExchangeGoods(exchangeGoods);

                exchangeRecordRepository.save(exchangeRecord);

                boolean isFirstExchange = wxUser.isFirstExchange();


                String exchangeDate = StringUtil.DateFormat(new Date(), StringUtil.DATE_PATTERN);


                if (isFirstExchange && exchangeDate.compareTo(activityDate.getBenefitStartDate()) >= 0
                        && exchangeDate.compareTo(activityDate.getBenefitEndDate()) <= 0) {
                    wxUser.setPoints(wxUser.getPoints() - (activity.getPoints() - 200));
                } else {
                    wxUser.setPoints(wxUser.getPoints() - activity.getPoints());
                }

                wxUser.setFirstExchange(false);

                wxUserRepository.save(wxUser);

                return ApiResult.resultWith(ResultCode.SUCCESS);
            } else {
                return ApiResult.resultWith(ResultCode.ERROR,apiResult.getResultMsg(),null);
            }
        } else {
            return ApiResult.resultWith(ResultCode.ERROR);
        }
    }

    private ExchangeInfo createExchangeInfo(String openId, String counterCode, int level, int meal, boolean isFirstExchange) {

        ExchangeActivity exchangeActivity = activityInfoService.findByLevel(meal);

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
        String[] giftsCode = exchangeActivity.getGiftsCode();
        String[] giftsBarCode = exchangeActivity.getGiftsBarCode();
        String[] giftsName = exchangeActivity.getGiftsName();

        for (int i = 0; i < giftsCode.length; i++) {
            ExchangeDetail exchangeDetail = new ExchangeDetail();
            exchangeDetail.setSubCampCode(exchangeActivity.getActivityCode());
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
                exchangeDetail.setExPoint(isFirstExchange ? exchangeActivity.getPoints() - 200 : exchangeActivity.getPoints());// TODO: 2016-10-18
            } else {
                exchangeDetail.setExPoint(0);
            }
            exchangeDetails.add(exchangeDetail);
        }

        exchangeInfo.setDetailList(exchangeDetails);

        return exchangeInfo;
    }

    @RequestMapping(value = "/myExchangeRecord")
    public String myExchangeRecord(@RequestParam(name = "openId") String openId, Model model) {
        List<ExchangeRecord> exchangeRecords = exchangeRecordRepository.findByWxOpenIdOrderByCreateTimeDesc(openId);
        model.addAttribute("exchangeRecords", exchangeRecords);
        return "ExchangeRecords";
    }

    @RequestMapping(value = "/toSuccess")
    public String toSuccessPage(String openId, String shopName, String shopAddr, Model model, String imgUrl) {
        model.addAttribute("shopName", shopName);
        model.addAttribute("shopAddr", shopAddr);
        model.addAttribute("imgUrl", imgUrl);
//        model.
        return "ExchangeSuccess";
    }

    @RequestMapping(value = "/changeProvince")
    @ResponseBody
    public ApiResult changeProvinceGetCitys(@OpenID String openId, String provinceName) {
        List<String> citys = shopInfoRepository.findCityByProvince(provinceName);
        return ApiResult.resultWith(ResultCode.SUCCESS, citys);
    }

    @RequestMapping(value = "/changeCity")
    @ResponseBody
    public ApiResult changeCityGetShopNames(@OpenID String openId, String cityName) {
        List<ShopInfo> shopNames = shopInfoRepository.findShopInfoByCity(cityName);
        List<ShopModel> shopModels = new ArrayList<>();
        shopNames.forEach(shopInfo -> {
            ShopModel shopModel = new ShopModel();
            shopModel.setShopName(shopInfo.getShopName());
            shopModel.setShopCode(shopInfo.getShopCode());
            shopModel.setShopAddr(shopInfo.getShopAddr());
            shopModels.add(shopModel);
        });
        return ApiResult.resultWith(ResultCode.SUCCESS, shopModels);
    }



}
