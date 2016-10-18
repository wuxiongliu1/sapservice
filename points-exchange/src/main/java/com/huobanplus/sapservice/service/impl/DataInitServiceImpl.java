package com.huobanplus.sapservice.service.impl;

import com.huobanplus.sapservice.commons.SapServiceEnum;
import com.huobanplus.sapservice.model.ExchangeActivity;
import com.huobanplus.sapservice.service.DataInitService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
@Service
public class DataInitServiceImpl implements DataInitService {

    private List<ExchangeActivity> level1200ActivityList;
    private List<ExchangeActivity> level2200ActivityList;
    private List<ExchangeActivity> level3200ActivityList;
    private List<ExchangeActivity> level5000ActivityList;

    @PostConstruct
    public void initData(){
        level1200ActivityList = createLevel1200Activity();
        level2200ActivityList = createLevel2200Activity();
        level3200ActivityList = createLevel3200Activity();
        level5000ActivityList = createLevel5000Activity();
    }

    @Override
    public List<ExchangeActivity> createExchangeActivityData() {
        List<ExchangeActivity> exchangeActivityList = new ArrayList<>();
        exchangeActivityList.addAll(createLevel1200Activity());
        exchangeActivityList.addAll(createLevel2200Activity());
        exchangeActivityList.addAll(createLevel3200Activity());
        exchangeActivityList.addAll(createLevel5000Activity());
        return exchangeActivityList;
    }

    @Override
    public List<ExchangeActivity> createLevel1200Activity() {

        List<ExchangeActivity> level1200 = new ArrayList<>();
        ExchangeActivity level1200A = new ExchangeActivity();
        String[] level1200ACode = {"10005818","10008775","DH000212"};
        String[] level1200ABarcode = {"10005818CX","10008775CX","DH000212"};
        String[] level1200AName = {"珀莱雅水漾肌密柔滑洁面膏120ml会员专享","睛彩珍珠净化提亮炭黑眼膜6g*6片-会员专享",
                "1200分A套餐(水漾系列)"};
        level1200A.setActivityCode("AT1610110000018");
        level1200A.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_1200_A);
        level1200A.setGiftsCode(level1200ACode);
        level1200A.setGiftsBarCode(level1200ABarcode);
        level1200A.setGiftsName(level1200AName);
        level1200A.setPoints(1200);

        ExchangeActivity level1200B = new ExchangeActivity();
        String[] level1200BCode = {"10008210","DH000213"};
        String[] level1200BBarcode = {"10008210CX","DH000213"};
        String[] level1200BName = {"靓白肌密如瓷透白隔离霜40ml/146元(紫色)","1200分B套餐(靓白系列)"};
        level1200B.setActivityCode("AT1610110000019");
        level1200B.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_1200_B);
        level1200B.setGiftsCode(level1200BCode);
        level1200B.setGiftsBarCode(level1200BBarcode);
        level1200B.setGiftsName(level1200BName);
        level1200B.setPoints(1200);

        ExchangeActivity level1200C = new ExchangeActivity();
        String[] level1200CCode = {"10008772","10008777","DH000214"};
        String[] level1200CBarcode = {"10008772CX","10008777CX","DH000214"};
        String[] level1200CName = {"光采贝壳水润靓颜晶透冻膜6g*2-会员专享","珀莱雅紧致肌密洁面乳霜120ml-16版会员专享","1200分C套餐(紧致系列)"};
        level1200C.setActivityCode("AT1610110000020");
        level1200C.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_1200_C);
        level1200C.setGiftsCode(level1200CCode);
        level1200C.setGiftsBarCode(level1200CBarcode);
        level1200C.setGiftsName(level1200CName);
        level1200C.setPoints(1200);

        ExchangeActivity level1200D = new ExchangeActivity();
        String[] level1200DCode = {"10006714","DH000215"};
        String[] level1200DBarcode = {"10006714CX","DH000215"};
        String[] level1200DName = {"深海致臻塑颜洁面霜120ml-14版会员专享","1200分D套餐(深海致臻系列)"};
        level1200D.setActivityCode("AT1610110000021");
        level1200D.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_1200_D);
        level1200D.setGiftsCode(level1200DCode);
        level1200D.setGiftsBarCode(level1200DBarcode);
        level1200D.setGiftsName(level1200DName);
        level1200D.setPoints(1200);

        level1200.add(level1200A);
        level1200.add(level1200B);
        level1200.add(level1200C);
        level1200.add(level1200D);

        return level1200;
    }

    @Override
    public List<ExchangeActivity> createLevel2200Activity() {

        List<ExchangeActivity> level2200 = new ArrayList<>();

        ExchangeActivity level2200A = new ExchangeActivity();
        String[] level2200ACode = {"10008762","10008776","DH000216"};
        String[] level2200ABarcode = {"10008762CX","10008776CX","DH000216"};
        String[] level2200AName = {"泡叶藻补水保湿柔润面膜25ml*6片-会员专享","珀莱雅水漾肌密恒润霜50g-16版会员专享",
        "2200分A套餐(水漾系列)"};

        level2200A.setActivityCode("AT1610110000022");
        level2200A.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_2200_A);
        level2200A.setGiftsCode(level2200ACode);
        level2200A.setGiftsBarCode(level2200ABarcode);
        level2200A.setGiftsName(level2200AName);
        level2200A.setPoints(2200);

        ExchangeActivity level2200B = new ExchangeActivity();
        String[] level2200BCode = {"10008210","10008762","DH000217"};
        String[] level2200BBarcode = {"10008210CX","10008762CX","DH000217"};
        String[] level2200BName = {"靓白肌密如瓷透白隔离霜40ml/146元（紫色）","泡叶藻补水保湿柔润面膜25ml*6片-会员专享","2200分B套餐(靓白系列)"};
        level2200B.setActivityCode("AT1610110000023");
        level2200B.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_2200_B);
        level2200B.setGiftsCode(level2200BCode);
        level2200B.setGiftsBarCode(level2200BBarcode);
        level2200B.setGiftsName(level2200BName);
        level2200B.setPoints(2200);

        ExchangeActivity level2200C = new ExchangeActivity();
        String[] level2200CCode = {"10007530","DH000218"};
        String[] level2200CBarcode = {"10007530CX","DH000218"};
        String[] level2200CName = {"深海美肌可弹套盒/268元（内含6片面膜）","2200分C套餐(紧致系列)"};
        level2200C.setActivityCode("AT1610110000024");
        level2200C.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_2200_C);
        level2200C.setGiftsCode(level2200CCode);
        level2200C.setGiftsBarCode(level2200CBarcode);
        level2200C.setGiftsName(level2200CName);
        level2200C.setPoints(2200);

        ExchangeActivity level2200D = new ExchangeActivity();
        String[] level2200DCode = {"10006757","DH000219"};
        String[] level2200DBarcode = {"10006757CX","DH000219"};
        String[] level2200DName = {"深海致臻塑颜紧肤水滋润型150ml-会员专享","2200分D套餐(深海致臻系列)"};
        level2200D.setActivityCode("AT1610110000025");
        level2200D.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_2200_D);
        level2200D.setGiftsCode(level2200DCode);
        level2200D.setGiftsBarCode(level2200DBarcode);
        level2200D.setGiftsName(level2200DName);
        level2200D.setPoints(2200);

        level2200.add(level2200A);
        level2200.add(level2200B);
        level2200.add(level2200C);
        level2200.add(level2200D);

        return level2200;
    }


    @Override
    public List<ExchangeActivity> createLevel3200Activity() {

        List<ExchangeActivity> level3200 = new ArrayList<>();
        ExchangeActivity level3200A = new ExchangeActivity();
        String[] level3200ACode = {"10005818","10008763","10008776","DH000220"};
        String[] level3200ABarcode = {"10005818CX","10008763CX","10008776CX","DH000220"};
        String[] level3200AName = {"珀莱雅水漾肌密柔滑洁面膏120ml会员专享","麒麟藻密集保湿丝滑面膜25ml*6片-会员专享","珀莱雅水漾肌密恒润霜50g-16版会员专享","3200分A套餐(水漾系列)"};
        level3200A.setActivityCode("AT1610110000026");
        level3200A.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_3200_A);
        level3200A.setGiftsCode(level3200ACode);
        level3200A.setGiftsBarCode(level3200ABarcode);
        level3200A.setGiftsName(level3200AName);
        level3200A.setPoints(3200);

        ExchangeActivity level3200B = new ExchangeActivity();
        String[] level3200BCode = {"10007301","10008210","10008764","DH000221"};
        String[] level3200BBarcode = {"10007301CX","10008210CX","10008764CX","DH000221"};
        String[] level3200BName = {"肌密柔润洁面乳120ml会员专享-15版会员专享","靓白肌密如瓷透白隔离霜40ml/146元（紫色）","黑顶藻净化亮肤炭黑面膜25ml*6片-会员专享","3200分B套餐(靓白系列)"};
        level3200B.setActivityCode("AT1610110000027");
        level3200B.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_3200_B);
        level3200B.setGiftsCode(level3200BCode);
        level3200B.setGiftsBarCode(level3200BBarcode);
        level3200B.setGiftsName(level3200BName);
        level3200B.setPoints(3200);

        ExchangeActivity level3200C = new ExchangeActivity();
        String[] level3200CCode = {"10006755","10008211","DH000222"};
        String[] level3200CBarcode = {"10006755CX","10008211CX","DH000222"};
        String[] level3200CName = {"紧致肌密凝时滋养水150ml-14版会员专享","珀莱雅紧致肌密弹力修护霜50g-16版会员专享","3200分C套餐(紧致系列)"};
        level3200C.setActivityCode("AT1610110000028");
        level3200C.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_3200_C);
        level3200C.setGiftsCode(level3200CCode);
        level3200C.setGiftsBarCode(level3200CBarcode);
        level3200C.setGiftsName(level3200CName);
        level3200C.setPoints(3200);

        ExchangeActivity level3200D = new ExchangeActivity();
        String[] level3200DCode = {"10006759","10008772","DH000223"};
        String[] level3200DBarcode = {"10006759CX","10008772CX","DH000223"};
        String[] level3200DName = {"深海致臻塑颜抚纹细肤精华液40ml-会员专享","光采贝壳水润靓颜晶透冻膜6g*2-会员专享","3200分D套餐(深海致臻系列)"};
        level3200D.setActivityCode("AT1610110000029");
        level3200D.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_3200_D);
        level3200D.setGiftsCode(level3200DCode);
        level3200D.setGiftsBarCode(level3200DBarcode);
        level3200D.setGiftsName(level3200DName);
        level3200D.setPoints(3200);

        level3200.add(level3200A);
        level3200.add(level3200B);
        level3200.add(level3200C);
        level3200.add(level3200D);

        return level3200;
    }

    @Override
    public List<ExchangeActivity> createLevel5000Activity() {

        List<ExchangeActivity> level5000 = new ArrayList<>();
        ExchangeActivity level5000B = new ExchangeActivity();
        String[] level5000BCode = {"10007301","10007816","10008210","10008762","10008775","DH000224"};
        String[] level5000BBarcode = {"10007301CX","10007816CX","10008210CX","10008762CX","10008775CX","DH000224"};
        String[] level5000BName = {"肌密柔润洁面乳120ml会员专享-15版会员专享","珀莱雅靓白肌密明星电眼霜20g-15版会员专享","靓白肌密如瓷透白隔离霜40ml/146元（紫色）","泡叶藻补水保湿柔润面膜25ml*6片-会员专享","睛彩珍珠净化提亮炭黑眼膜6g*6片-会员专享","5000分B套餐(靓白系列)"};
        level5000B.setActivityCode("AT1610110000030");
        level5000B.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_5000_B);
        level5000B.setGiftsCode(level5000BCode);
        level5000B.setGiftsBarCode(level5000BBarcode);
        level5000B.setGiftsName(level5000BName);
        level5000B.setPoints(5000);

        ExchangeActivity level5000A = new ExchangeActivity();
        String[] level5000ACode = {"10005818","10006754","10008764","10008775","10008776","DH000225"};
        String[] level5000ABarcode = {"10005818CX","10006754CX","10008764CX","10008775CX","10008776CX","DH000225"};
        String[] level5000AName = {"珀莱雅水漾肌密柔滑洁面膏120ml会员专享","珀莱雅水漾肌密眼部凝露20g-14版会员专享","黑顶藻净化亮肤炭黑面膜25ml*6片-会员专享","睛彩珍珠净化提亮炭黑眼膜6g*6片-会员专享","珀莱雅水漾肌密恒润霜50g-16版会员专享","5000分A套餐(水漾系列)"};
        level5000A.setActivityCode("AT1610110000031");
        level5000A.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_5000_A);
        level5000A.setGiftsCode(level5000ACode);
        level5000A.setGiftsBarCode(level5000ABarcode);
        level5000A.setGiftsName(level5000AName);
        level5000A.setPoints(5000);

        ExchangeActivity level5000D = new ExchangeActivity();
        String[] level5000DCode = {"10006714","10006759","10008764","DH000226"};
        String[] level5000DBarcode = {"10006714CX","10006759CX","10008764CX","DH000226"};
        String[] level5000DName = {"深海致臻塑颜洁面霜120ml-14版会员专享","深海致臻塑颜抚纹细肤精华液40ml-会员专享","黑顶藻净化亮肤炭黑面膜25ml*6片-会员专享","5000分D套餐(深海致臻系列)"};
        level5000D.setActivityCode("AT1610110000032");
        level5000D.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_5000_D);
        level5000D.setGiftsCode(level5000DCode);
        level5000D.setGiftsBarCode(level5000DBarcode);
        level5000D.setGiftsName(level5000DName);
        level5000D.setPoints(5000);

        ExchangeActivity level5000C = new ExchangeActivity();
        String[] level5000CCode = {"10006755","10008211","10008762","10008772","10008777","DH000227"};
        String[] level5000CBarcode = {"10006755CX","10008211CX","10008762CX","10008772CX","10008777CX","DH000227"};
        String[] level5000CName = {"紧致肌密凝时滋养水150ml-14版会员专享","珀莱雅紧致肌密弹力修护霜50g-16版会员专享","泡叶藻补水保湿柔润面膜25ml*6片-会员专享","光采贝壳水润靓颜晶透冻膜6g*2-会员专享","珀莱雅紧致肌密洁面乳霜120ml-16版会员专享","5000分C套餐(紧致系列)"};
        level5000C.setActivityCode("AT1610110000033");
        level5000C.setActivityLevel(SapServiceEnum.ActivityLevel.LEVEL_5000_C);
        level5000C.setGiftsCode(level5000CCode);
        level5000C.setGiftsBarCode(level5000CBarcode);
        level5000C.setGiftsName(level5000CName);
        level5000C.setPoints(5000);
        
        level5000.add(level5000A);
        level5000.add(level5000B);
        level5000.add(level5000C);
        level5000.add(level5000D);

        return level5000;
    }

    @Override
    public ExchangeActivity findActivityByLevelAndMeal(int level, int meal) {
        List<ExchangeActivity> exchangeActivityList = null;
        switch (level) {
            case 1: {
                exchangeActivityList = level1200ActivityList;
                break;
            }
            case 2: {
                exchangeActivityList = level2200ActivityList;
                break;
            }
            case 3: {
                exchangeActivityList = level3200ActivityList;
                break;
            }
            case 4:{
                exchangeActivityList = level5000ActivityList;
                break;
            }
        }

        ExchangeActivity activity = new ExchangeActivity();
        for (ExchangeActivity exchangeActivity : exchangeActivityList) {
            if(exchangeActivity.getActivityLevel().getCode() == meal){
                activity = exchangeActivity;
                break;
            }
        }
        return activity;
    }
}