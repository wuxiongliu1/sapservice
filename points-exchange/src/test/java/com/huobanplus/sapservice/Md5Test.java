package com.huobanplus.sapservice;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * Created by wuxiongliu on 2016-11-02.
 */
public class Md5Test {

    @Test
    public void testMd5() {
        String pwd = "ply201611";
        String md5Str = DigestUtils.md5Hex(pwd.getBytes());
        System.out.println(md5Str);
    }
}
