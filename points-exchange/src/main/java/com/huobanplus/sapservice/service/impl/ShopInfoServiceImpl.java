/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.service.impl;

import com.huobanplus.sapservice.entity.ShopInfo;
import com.huobanplus.sapservice.repository.ShopInfoRepository;
import com.huobanplus.sapservice.service.ShopInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wuxiongliu on 2016-10-19.
 */
@Service
public class ShopInfoServiceImpl implements ShopInfoService {

    private static final Log log = LogFactory.getLog(ShopInfoServiceImpl.class);

    @Autowired
    private ShopInfoRepository shopInfoRepository;

    @PostConstruct
    public void initShopInfo() throws IOException {
        ShopInfo info = shopInfoRepository.findOne(1);

        if(info == null){// 未初始化过数据，则初始化，否则不初始化

            InputStream fin = ShopInfoServiceImpl.class.getResourceAsStream("/shop_info_10.28.xlsx");
            XSSFWorkbook workbook= new XSSFWorkbook(fin);
//            for(int i=0;i<workbook.getNumberOfSheets();i++){
            int sheetIndex = 2;
                XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                for(int rowIndex = 1;rowIndex<=sheet.getLastRowNum();rowIndex++){
                    XSSFRow row = sheet.getRow(rowIndex);
                    ShopInfo shopInfo = new ShopInfo();
                    shopInfo.setShopType(row.getCell(0)!=null?row.getCell(0).toString():"");
                    shopInfo.setProvince(row.getCell(2)!=null?row.getCell(2).toString():"");
                    shopInfo.setCity(row.getCell(3)!=null?row.getCell(3).toString():"");
                    shopInfo.setCrmCode(row.getCell(4)!=null?row.getCell(4).toString():"");
                    shopInfo.setShopCode(row.getCell(5)!=null?row.getCell(5).toString():"");
                    shopInfo.setShopName(row.getCell(6)!=null?row.getCell(6).toString():"");
                    shopInfo.setShopAddr(row.getCell(7)!=null?row.getCell(7).toString():"");
                    shopInfo.setShopStatus(row.getCell(8)!=null?row.getCell(8).toString():"");
                    shopInfoRepository.save(shopInfo);
                    log.info("柜台数据初始化中.....");
                }
//            }
            log.info("柜台数据初始化完成");
        }
    }
}
