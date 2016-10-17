package com.huobanplus.sapservice.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by wuxiongliu on 2016-10-17.
 */
public class RequestUtil {

    /**
     * @param param     请求的json对应的string数据（没有系统级数据）
     * @param secretKey
     * @return
     * @throws Exception
     */
    public static String getParamData(String param, String secretKey) throws Exception {

        byte[] keyBytes = Base64.decodeBase64(secretKey);
        byte[] dataBytes = param.getBytes("UTF-8");

        byte[] secretByte = AESSecurityUtil.encrypt(dataBytes, keyBytes);
        return Base64.encodeBase64String(secretByte);
    }
}
