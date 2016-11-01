package com.huobanplus.sapservice.service;

import com.huobanplus.sapservice.entity.WxUser;

/**
 * Created by wuxiongliu on 2016-11-01.
 */

public interface WxUserService {

    public WxUser updateOrSaveUser(String openId, int points);
}
