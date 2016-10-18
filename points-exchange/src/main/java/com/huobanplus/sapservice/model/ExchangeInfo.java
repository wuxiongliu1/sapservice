package com.huobanplus.sapservice.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
@Data
public class ExchangeInfo {

    /**
     * 品牌代码 必填，珀莱雅：ply
     */
    @JSONField(name= "BrandCode")
    private String brandCode;

    /**
     * 业务类型 必填，固定字符串，"PointExchangeBooking"
     */
    @JSONField(name= "TradeType")
    private String tradeType;

    /**
     * 操作类型 必填。 0：预约
     */
    @JSONField(name= "TicketType")
    private int ticketType;

    /**
     * 单据号 有则填，varchar(35)。全局唯一
     */
    @JSONField(name= "TradeNoIF")
    private String tradeNoIF;

    /**
     * 活动码 必填
     */
    @JSONField(name= "SubCampCode")
    private String subCampCode;

    /**
     * 活动子类型 必填 ‘PX’：积分兑换
     */
    @JSONField(name= "SubType")
    private String subType;

    /**
     * 预约柜台号 有则填。进行预约的柜台
     */
    @JSONField(name= "CounterCode")
    private String counterCode;

    /**
     * 预约操作时间 必填。YYYY-MM-DD HH:mm:ss
     */
    @JSONField(name= "BookDateTime")
    private String bookDateTime;

    /**
     * 预约服务日期 有则填。YYYY-MM-DD
     */
    @JSONField(name= "BookDate")
    private String bookDate;

    /**
     * 预约服务时间段 有则填。HH:mm-HH:mm
     */
    @JSONField(name= "BookTimeRange")
    private String bookTimeRange;

    /**
     * 会员号 有则填。
     */
    @JSONField(name= "MemberCode")
    private String memberCode;

    /**
     * 预约时手机号 有则填。
     */
    @JSONField(name= "MobilePhone")
    private String mobilePhone;

    /**
     * 预约人姓名 有则填。
     */
    @JSONField(name= "Name")
    private String name;

    /**
     * 预约时电话号码 有则填。
     */
    @JSONField(name= "Telephone")
    private String telephone;

    /**
     * 预约时微信号码 有则填。
     */
    @JSONField(name= "MessageID")
    private String messageID;

    /**
     * 预约领取柜台 必填。可任意柜台领用填“ALL”；否则填预约时的柜台
     */
    @JSONField(name= "CounterCodeGet")
    private String counterCodeGet;

    /**
     * 员工代码 有则填。
     */
    @JSONField(name= "EmployeeCode")
    private String employeeCode;

    /**
     * 预约前的积分 有则填。
     */
    @JSONField(name= "PointBeforeBooking")
    private int pointBeforeBooking;

    /**
     * 验证码 有则填。柜台领用时的验证码
     */
    @JSONField(name= "CouponCode")
    private String couponCode;

    /**
     * 数据来源 必填。‘3’：微信 ‘4’：官网
     */
    @JSONField(name= "DataSourse")
    private int dataSourse;

    /**
     * mq发送到终端pos 0则不发送，1发送
     */
    @JSONField(name= "SendPos")
    private int sendPos;

    /**
     * 积分维护  0不维护，1维护
     */
    @JSONField(name= "HandlePoints")
    private String handlePoints;

    /**
     * 修改回数 必填
     */
    @JSONField(name= "ModifyCounts")
    private int modifyCounts;

    /**
     * 明细 必填
     */
    @JSONField(name= "DetailList")
    private List<ExchangeDetail> detailList;
}
