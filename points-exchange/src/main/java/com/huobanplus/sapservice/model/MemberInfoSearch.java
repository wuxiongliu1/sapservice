package com.huobanplus.sapservice.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by elvis on 2016/5/26.
 */
@Setter
@Getter
public class MemberInfoSearch {
//    BrandCode	品牌代码	必填
//    TradeType	业务类型	必填，固定字符串，"GetMemberInfo"
//    ConditionType	查找会员的条件类型	"必填，只能为以下列表中的一种：
//                  MemberID：会员ID
//                  MemberCode：会员号
//                  MessageID：微信号
//                  MobilePhone：手机号
//                  Email：邮件"
//    Condition	条件内容	和ConditionType配合使用，可以是会员ID/会员号/微信号/手机号/邮件中的一种

//    最后一笔失效积分 CurDisablePoint;
//    最后一笔失效日期 CurDealDate;
    /**
     * BrandCode	品牌代码	必填
     */
    @JSONField(name = "BrandCode")
    private String brandCode;
    /**
     * TradeType	业务类型	必填，固定字符串，"GetMemberInfo"
     */
    @JSONField(name = "TradeType")
    private String tradeType;

    /**
     * ConditionType	查找会员的条件类型	"必填，只能为以下列表中的一种：
     */
    @JSONField(name = "ConditionType")
    private String conditionType;

    /**
     * Condition	条件内容	和ConditionType配合使用，可以是会员ID/会员号/微信号/手机号/邮件中的一种
     */
    @JSONField(name = "Condition")
    private String condition;

    /**
     * 最后一笔失效积分
     */
    @JSONField(name = "CurDisablePoint")
    private String curDisablePoint;

    /**
     * 最后一笔失效日期
     */
    @JSONField(name = "CurDealDate")
    private String curDealDate;



}
