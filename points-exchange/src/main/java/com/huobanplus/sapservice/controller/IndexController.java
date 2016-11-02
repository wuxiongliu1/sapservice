/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huobanplus.sapservice.commons.Constant;
import com.huobanplus.sapservice.commons.SapServiceEnum;
import com.huobanplus.sapservice.commons.bean.ApiResult;
import com.huobanplus.sapservice.commons.bean.ResultCode;
import com.huobanplus.sapservice.commons.ienum.EnumHelper;
import com.huobanplus.sapservice.entity.*;
import com.huobanplus.sapservice.model.*;
import com.huobanplus.sapservice.repository.*;
import com.huobanplus.sapservice.service.ActivityInfoService;
import com.huobanplus.sapservice.service.DataInitService;
import com.huobanplus.sapservice.service.ExchangeService;
import com.huobanplus.sapservice.service.WxUserService;
import com.huobanplus.sapservice.utils.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
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
    private ActivityInfoRepository activityInfoRepository;
    @Autowired
    private ShopCarRepository shopCarRepository;
    @Autowired
    private ActivityDate activityDate;
    @Autowired
    private ActivityInfoService activityInfoService;
    @Autowired
    private WxUserService wxUserService;


    /**
     *  积分兑换首页
     * @param openId
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/index"})
    public String index(@RequestParam(name = "usermobile") String openId, HttpServletRequest request, Model model) throws Exception {

        // 判断活动是否在活动期间
        String nowStr = StringUtil.DateFormat(new Date(),StringUtil.DATE_PATTERN);
//        if(nowStr.compareTo(activityDate.getBenefitStartDate())<0){
//            model.addAttribute("errorMsg","活动未开始");
//            return "error";
//        }
        if(nowStr.compareTo(activityDate.getBenefitEndDate())>0){
            model.addAttribute("errorMsg","活动已经结束");
            return "error";
        }


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

                WxUser wxUser = wxUserService.updateOrSaveUser(openId, points);

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


    /**
     *  兑换详情页
     * @param openId
     * @param level
     * @param meal
     * @param model
     * @return
     */
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

    /**
     *  兑换
     * @param openId
     * @param counterCode
     * @param shopName
     * @param shopAddr
     * @param level
     * @param num
     * @return
     */
    @RequestMapping(value = "/exchange", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ApiResult exchange(@RequestParam(name = "openId") String openId, String counterCode, String shopName, String shopAddr,
                              @RequestParam(value = "level[]") int[] level,
                              @RequestParam(value = "num[]") int[] num,
                              int points) {
        // 判断是否是第一次兑换，并且当前时间是在珀莱雅的优惠期间里，如果是，则优惠200积分，否则不优惠
        WxUser wxUser = wxUserRepository.findByOpenId(openId);
        if(points > wxUser.getPoints()){
            return ApiResult.resultWith(ResultCode.ERROR,"积分不足",null);
        }
        int totalPoints = 0;

        if (wxUser != null) {
            ExchangeInfo exchangeInfo = createExchangeInfo(openId, counterCode, num,level, wxUser.isFirstExchange());

            ApiResult apiResult = exchangeService.exchangePoints(exchangeInfo);
            if (apiResult.getResultCode() == ResultCode.SUCCESS.getResultCode()) {

                for(int i=0;i<level.length;i++){

                    ExchangeActivity activity = activityInfoService.findByLevel(level[i]);
                    totalPoints += activity.getPoints()*num[i];

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
                return ApiResult.resultWith(ResultCode.ERROR,apiResult.getResultMsg(),null);
            }
        } else {
            return ApiResult.resultWith(ResultCode.ERROR);
        }
    }

    /**
     * 创建积分兑换请求bean
     * @param openId
     * @param counterCode
     * @param quantity
     * @param level
     * @param isFirstExchange
     * @return
     */
    private ExchangeInfo createExchangeInfo(String openId,String counterCode,int[] quantity,int[] level, boolean isFirstExchange) {

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

        for(int j=0;j<level.length;j++){

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

        if(benefitExchangeDetail != null){
            exchangeDetails.add(benefitExchangeDetail);
        }
        exchangeInfo.setDetailList(exchangeDetails);

        return exchangeInfo;
    }

    /**
     *  我的兑换记录
     * @param openId
     * @param model
     * @return
     */
    @RequestMapping(value = "/myExchangeRecord")
    public String myExchangeRecord(@RequestParam(name = "openId") String openId, Model model) {
        List<ExchangeRecord> exchangeRecords = exchangeRecordRepository.findByWxOpenIdOrderByCreateTimeDesc(openId);
        model.addAttribute("exchangeRecords", exchangeRecords);
        return "ExchangeRecords";
    }

    /**
     *  兑换成功页面
     * @param openId
     * @param shopName
     * @param shopAddr
     * @param model
     * @param imgUrl
     * @param num
     * @return
     */
    @RequestMapping(value = "/toSuccess")
    public String toSuccessPage(String openId, String shopName, String shopAddr, Model model, String[] imgUrl,int[] num) {

        List<LevelModel> levelModels = new ArrayList<>();
        for(int i=0;i<imgUrl.length;i++){
            LevelModel levelModel = new LevelModel();
            levelModel.setImgUrl(imgUrl[i]);
            levelModel.setNum(num[i]);
            levelModels.add(levelModel);
        }
        model.addAttribute("shopName", shopName);
        model.addAttribute("shopAddr", shopAddr);


        model.addAttribute("levelModels", levelModels);
        return "ExchangeSuccess";
    }

    /**
     * 选择省时查询相应市
     * @param provinceName
     * @return
     */
    @RequestMapping(value = "/changeProvince")
    @ResponseBody
    public ApiResult changeProvinceGetCitys(String provinceName) {
        List<String> citys = shopInfoRepository.findCityByProvince(provinceName);
        return ApiResult.resultWith(ResultCode.SUCCESS, citys);
    }

    /**
     * 选择市时查询相应门店
     * @param cityName
     * @return
     */
    @RequestMapping(value = "/changeCity")
    @ResponseBody
    public ApiResult changeCityGetShopNames(String cityName) {
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

    /**
     * 增加套餐至购物车
     * @param openId
     * @param level
     * @param flag 1:增加一个 0:减少一个
     * @return
     */
    @RequestMapping(value = "/addToShopCar")
    @ResponseBody
    public ApiResult addToShopCar(@RequestParam(name = "openId") String openId,int level,int flag,Integer num){

        SapServiceEnum.ActivityLevel activityLevel = EnumHelper.getEnumType(SapServiceEnum.ActivityLevel.class,level);
        ActivityInfo activityInfo = activityInfoRepository.findByActivityLevel(activityLevel);
        ShopCar shopCar = shopCarRepository.findByWxOpenIdAndActivityInfo(openId,activityInfo);

        if(shopCar == null){
            shopCar = new ShopCar();
            shopCar.setNum(num);
            shopCar.setCreateTime(StringUtil.DateFormat(new Date(),StringUtil.TIME_PATTERN));
            shopCar.setActivityInfo(activityInfo);
            shopCar.setWxOpenId(openId);
            shopCarRepository.save(shopCar);
        } else{

            if(num != null){
                shopCar.setNum(shopCar.getNum()+num);
            } else{
                if(flag==1){
                    shopCar.setNum(shopCar.getNum()+1);
                } else if(flag==0){
                    shopCar.setNum(shopCar.getNum()-1);
                }
            }
            shopCarRepository.save(shopCar);
        }

        return ApiResult.resultWith(ResultCode.SUCCESS);
    }


    /**
     * 我的购物车
     * @param openId
     * @param model
     * @return
     */
    @RequestMapping(value = "/myShopCar")
    public String myShopCar(@RequestParam(name = "openId") String openId,Model model){

        WxUser wxUser = wxUserRepository.findByOpenId(openId);
        int isFirstExchange = 0;
        int isBenefit = 0;
        if (wxUser == null) {
            // 去会员注册？
            return "redirect:/sapservice/index?usermobile="+openId;

        } else {
            isFirstExchange = wxUser.isFirstExchange() ? 1 : 0;
        }
        String exchangeDate = StringUtil.DateFormat(new Date(), StringUtil.DATE_PATTERN);

        if (wxUser.isFirstExchange() && exchangeDate.compareTo(activityDate.getBenefitStartDate()) >= 0
                && exchangeDate.compareTo(activityDate.getBenefitEndDate()) <= 0) {
            isBenefit = 1;
        } else{
            isBenefit = 0;
        }


        List<ShopCar> shopCars = shopCarRepository.findByWxOpenId(openId);
        List<String> provinces = shopInfoRepository.findProvince();

        model.addAttribute("shopCarList",shopCars);
        model.addAttribute("openId",openId);
        model.addAttribute("provinces", provinces);
        model.addAttribute("isBenefit",isBenefit);
        model.addAttribute("isFirstExchange", isFirstExchange);
        model.addAttribute("userPoints",wxUser.getPoints());

        return "ShopCarList";
    }

    /**
     * 从购物车中删除某套餐
     * @param openId
     * @param level
     * @return
     */
    @RequestMapping(value = "/removeItem")
    @ResponseBody
    public ApiResult removeItem(@RequestParam(name = "openId") String openId,int level){
        SapServiceEnum.ActivityLevel activityLevel = EnumHelper.getEnumType(SapServiceEnum.ActivityLevel.class,level);
        ActivityInfo activityInfo = activityInfoRepository.findByActivityLevel(activityLevel);
        ShopCar shopCar = shopCarRepository.findByWxOpenIdAndActivityInfo(openId,activityInfo);
        if(shopCar != null){
            shopCarRepository.delete(shopCar);
            return ApiResult.resultWith(ResultCode.SUCCESS);
        } else {
            return ApiResult.resultWith(ResultCode.ERROR);
        }
    }

    @RequestMapping(value = "/activityManage")
    public String activityManage(String pd,Model model){
        model.addAttribute("activityList", activityInfoService.findAllActivity());
        return "ActivityManage";

    }

    @RequestMapping(value = "/enableActivity")
    @ResponseBody
    public ApiResult enableActivity(String passWord,String activityCode,int enable) throws Exception {
        ActivityInfo activityInfo = activityInfoRepository.findByActivityCode(activityCode);
        if(activityInfo != null){
            activityInfo.setIsEnable(enable);
            activityInfoRepository.save(activityInfo);
            return ApiResult.resultWith(ResultCode.SUCCESS);
        } else{
            return ApiResult.resultWith(ResultCode.ERROR,"该套餐不存在",null);
        }
    }

    @RequestMapping(value = "/admin/login")
    public String login(HttpServletRequest request, String password, Model model) {
        String md5Str = DigestUtils.md5Hex(password.getBytes());
        if (md5Str.equals(Constant.PLY_MD5_PASSWORD)) {
            request.getSession().setAttribute("password", password);
            return "redirect:/sapservice/activityManage";
        } else {
            return "redirect:/sapservice/admin/toLogin?errorMsg=密码错误";
        }
    }

    @RequestMapping(value = "/admin/toLogin")
    public String toLogin(String errorMsg, Model model) {
        model.addAttribute("errorMsg", errorMsg);
        return "Login";
    }

}
