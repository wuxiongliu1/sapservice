package com.huobanplus.sapservice.commons;

import com.huobanplus.sapservice.commons.ienum.ICommonEnum;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
public interface SapServiceEnum {

    enum ActivityLevel implements ICommonEnum{
        LEVEL_1200_A(1,"1200分A套餐(水漾系列)"),
        LEVEL_1200_B(2,"1200分B套餐(靓白系列)"),
        LEVEL_1200_C(3,"1200分C套餐(紧致系列)"),
        LEVEL_1200_D(4,"1200分D套餐(深海致臻系列)"),
        LEVEL_2200_A(5,"2200分A套餐(水漾系列)"),
        LEVEL_2200_B(6,"2200分B套餐(靓白系列)"),
        LEVEL_2200_C(7,"2200分C套餐(紧致系列)"),
        LEVEL_2200_D(8,"2200分D套餐(深海致臻系列)"),
        LEVEL_3200_A(9,"3200分A套餐(水漾系列)"),
        LEVEL_3200_B(10,"3200分B套餐(靓白系列)"),
        LEVEL_3200_C(11,"3200分C套餐(紧致系列)"),
        LEVEL_3200_D(12,"3200分D套餐(深海致臻系列)"),
        LEVEL_5000_A(13,"5000分A套餐(靓白系列)"),
        LEVEL_5000_B(14,"5000分B套餐(水漾系列)"),
        LEVEL_5000_C(15,"5000分C套餐(深海致臻系列)"),
        LEVEL_5000_D(16,"5000分D套餐(紧致系列)");


        private int code;
        private String name;

        ActivityLevel(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }

    enum ErrorCodeEnum implements ICommonEnum{
        ACTIVITY_NOT_EXIST(3,"活动不存在"),
        BOOK_NUM_LIMIT(4,"预约单据总数已达活动上线"),
        USER_BOOK_TIME_LIMIT(5,"单个对象预约次数大于活动上限"),
        NO_BOOK_TIME(6,"活动无预约阶段,无法预约"),
        ACTIVITY_END(7,"活动已结束"),
        ACTIVITY_NOT_START(8,"活动未开始"),
        JSON_PARSE_ERROR(9,"活动领用日期参考规则JSON解析失败"),
        BILL_DATE_ERROR(10,"生成会员单据领用日期失败"),
        BOOK_DATE_BIGGER(12,"预约日期大于预约截止日期"),
        BOOK_DATE_LOWER(13,"预约日期小于预约开始日期"),
        ACTIVITY_CODE_LOSE(21,"活动码参数缺失"),
        GUITAI_CODE_LOSE(22,"领取柜台参数缺失"),
        GUITAI_CODE_ERROR(23,"领取柜台无效"),
        BILL_FILL_ERROR(24,"单据填充失败"),
        POINTS_SERVICE_ERROR(25,"积分维护失败"),
        BOOK_TIME_EXIST(26,"预约时间段已存在，请修改预约日期或时间段"),
        BOOK_NUMBER_DAILY_LIMIT(27,"预约超出每天最大预约数"),
        BOOK_EXCEPTION(30,"预约异常")
        ;

        ErrorCodeEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        private int code;
        private String name;

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
