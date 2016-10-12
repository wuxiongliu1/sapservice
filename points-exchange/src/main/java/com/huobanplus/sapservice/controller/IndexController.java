package com.huobanplus.sapservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuxiongliu on 2016-10-12.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(){
        return "Hello ";
    }
}
