package com.huobanplus.sapservice.service.impl;

import com.huobanplus.sapservice.entity.WxUser;
import com.huobanplus.sapservice.repository.WxUserRepository;
import com.huobanplus.sapservice.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuxiongliu on 2016-11-01.
 */
@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserRepository wxUserRepository;

    @Override
    public synchronized WxUser updateOrSaveUser(String openId, int points) {

        WxUser wxUser = wxUserRepository.findByOpenId(openId);
        if (wxUser == null) {
            wxUser = new WxUser();
            wxUser.setPoints(points);// 首次积分
            wxUser.setFirstExchange(true);
            wxUser.setOpenId(openId);
            wxUser.setWxName("");
            wxUser.setWxImgUrl("");
            wxUser = wxUserRepository.save(wxUser);
        } else {
            wxUser.setPoints(points);// TODO: 2016-10-18
            wxUser = wxUserRepository.save(wxUser);
        }
        return wxUser;
    }
}
