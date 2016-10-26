package com.huobanplus.sapservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by wuxiongliu on 2016-10-26.
 */

@Setter
@Getter
@Entity
@Table(name = "shop_car")
public class ShopCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "wx_openId")
    private String wxOpenId;

    @Column(name = "create_time")
    private String createTime;

    @OneToOne
    private ActivityInfo activityInfo;

    @Column(name = "num")
    private int num;
}
