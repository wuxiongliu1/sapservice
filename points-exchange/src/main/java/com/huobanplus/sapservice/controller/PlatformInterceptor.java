/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2016. All rights reserved.
 */

package com.huobanplus.sapservice.controller;

import com.huobanplus.sapservice.commons.Constant;
import com.huobanplus.sapservice.utils.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liual on 2015-10-28.
 */
@Component
public class PlatformInterceptor extends HandlerInterceptorAdapter {
    private static final Log log = LogFactory.getLog(PlatformInterceptor.class);

    @Autowired
    private Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String userMobile = request.getParameter("usermobile");
//        String username = (String) request.getSession().getAttribute("username");
        String password = (String) request.getSession().getAttribute("password");
        if (StringUtil.isEmpty(password)) {
            response.sendRedirect("/sapservice/admin/toLogin");
            return false;
        }
        String md5Str = DigestUtils.md5Hex(password.getBytes());
        if (md5Str.equals(Constant.PLY_MD5_PASSWORD)) {
            return true;
        } else {
            response.getWriter().write("登录失败,密码错误");
        }
        return true;
    }
}
