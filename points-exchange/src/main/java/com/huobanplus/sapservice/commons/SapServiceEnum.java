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
}
