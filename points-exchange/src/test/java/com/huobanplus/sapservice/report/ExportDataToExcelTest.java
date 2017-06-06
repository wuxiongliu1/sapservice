package com.huobanplus.sapservice.report;

import com.huobanplus.sapservice.TestBase;
import com.huobanplus.sapservice.entity.ExchangeRecord;
import com.huobanplus.sapservice.entity.WxUser;
import com.huobanplus.sapservice.repository.ExchangeRecordRepository;
import com.huobanplus.sapservice.repository.WxUserRepository;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by wuxiongliu on 2016-12-01.
 */
public class ExportDataToExcelTest extends TestBase {

    @Autowired
    private WxUserRepository wxUserRepository;
    @Autowired
    private ExchangeRecordRepository exchangeRecordRepository;

    @Test
    public void testExportData() throws IOException {

        String[] cellTitle = {"用户手机号", "兑换时间", "积分档次", "套餐名称", "数量"};


        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet();
        workBook.setSheetName(0, "微信积分兑换记录");
        XSSFFont font = workBook.createFont();
        font.setColor(XSSFFont.COLOR_NORMAL);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        XSSFCellStyle cellStyle = workBook.createCellStyle();//创建格式
        cellStyle.setFont(font);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        XSSFRow titleRow = sheet.createRow((short) 0);//第一行标题
        for (int i = 0, size = cellTitle.length; i < size; i++) {//创建第1行标题单元格
            switch (i) {
                case 0:
                    sheet.setColumnWidth(0, 3000);
                    break;
                case 1:
                    sheet.setColumnWidth(1, 4000);
                    break;
                case 2:
                    sheet.setColumnWidth(2, 2000);
                    break;
                case 3:
                    sheet.setColumnWidth(3, 4000);
                    break;
                case 4:
                    sheet.setColumnWidth(4, 2000);
                    break;
            }
            XSSFCell cell = titleRow.createCell(i, 0);
            cell.setCellStyle(cellStyle);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(cellTitle[i]);
        }


        System.out.println("手机号\t兑换时间\t积分档次\t套餐\t数量");
        List<WxUser> wxUserList = wxUserRepository.findByIsFirstExchange(false);
        XSSFCellStyle style = workBook.createCellStyle();
        int count = 0;
        for (int i = 0; i < wxUserList.size(); i++) {
            List<ExchangeRecord> exchangeRecordList = exchangeRecordRepository.findByWxOpenIdOrderByCreateTimeDesc(wxUserList.get(i).getOpenId());

            for (int j = 0; j < exchangeRecordList.size(); j++) {
                count++;
                ExchangeRecord exchangeRecord = exchangeRecordList.get(j);

                XSSFRow row = sheet.createRow((short) count);

                for (int k = 0, length = cellTitle.length; k < length; k++) {
                    XSSFCell cell = row.createCell(k, 0);
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);// 定义单元格为字符串类型
                    switch (k) {// 在单元格中输入一些内容
                        case 0://手机号
                            cell.setCellValue(exchangeRecord.getWxOpenId());
                            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                            cell.setCellStyle(style);
                            break;
                        case 1://兑换时间
                            cell.setCellValue(exchangeRecord.getCreateTime());
                            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                            cell.setCellStyle(style);
                            break;
                        case 2://积分档次
                            cell.setCellValue(exchangeRecord.getExchangeGoods().getGoodsName().substring(0, 4));
                            style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                            cell.setCellStyle(style);
                            break;
                        case 3://套餐名称
                            cell.setCellValue(exchangeRecord.getExchangeGoods().getGoodsName());
                            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                            style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                            cell.setCellStyle(style);
                            break;
                        case 4://数量
                            cell.setCellValue(exchangeRecord.getNum());
                            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                            style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                            cell.setCellStyle(style);
                            break;
                    }
                }
            }
        }

        // 输出保存文件
        String basePath = "d:\\";
        String exportFileName = "珀莱雅微信积分兑换记录2.xlsx";
        File file = new File(basePath + exportFileName);
        FileOutputStream outStream = new FileOutputStream(file);
        workBook.write(outStream);
        outStream.flush();
        outStream.close();
        System.out.println("珀莱雅微信积分兑换记录导出完成");
    }
}
