package com.huobanplus.sapservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by wuxiongliu on 2016-10-14.
 */

@Entity
@Table(name = "Exchange_Record")
@Setter
@Getter
public class ExchangeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *  兑换码
     */
    @Column(name = "exchange_code")
    private String exchangeCode;

    /**
     *  兑换门店
     */
    @Column(name = "exchange_shop")
    private String exchangeShop;

    /**
     *  是否核销
     */
    @Column(name = "is_verification")
    private boolean isVerification;

    /**
     * 微信用户id (自增字段id,不是openId)
     */
    private String wxOpenId;

    /**
     * 领用开始日期
     */
    private String startDate;

    /**
     * 领用截止日期
     */
    private String endDate;

    /**
     * 记录生成日期
     */
    private String createTime;

    /**
     *  兑换的门店名称
     */
    private String shopName;

    /**
     *  兑换门店地址
     */
    private String shopAddr;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "goods_id")
    private ExchangeGoods exchangeGoods;

}
