package com.huobanplus.sapservice.controller;

import com.huobanplus.sapservice.commons.annotation.RequestAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wuxiongliu on 2016-10-12.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.setAttribute("openID","123wxl");
        return "Hello ";
    }

    @RequestMapping(value = "/r1")
    public String request1(@RequestAttribute String customerId){
        System.out.println(customerId);
        return "";
    }
}
