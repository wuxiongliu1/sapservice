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
     * 是否第一次兑换
     */
    @Column(name = "is_first_exchange")
    private boolean isFirstExchange;

}
