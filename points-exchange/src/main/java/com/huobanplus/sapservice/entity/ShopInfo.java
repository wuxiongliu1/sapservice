package com.huobanplus.sapservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by wuxiongliu on 2016-10-19.
 * 柜台信息
 */
@Setter
@Getter
@Entity
@Table(name = "shop_info")
public class ShopInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String shopType;
    private String province;

    private String city;
    private String crmCode;
    private String shopCode;
    private String shopName;
    private String shopAddr;
    private String shopStatus;

}
