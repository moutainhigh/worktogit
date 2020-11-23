package com.lwxf.industry4.webapp.common.utils.excel.impl;

import com.lwxf.industry4.webapp.common.utils.excel.ExcelParam;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-05-25 10:54
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class PaymentExcelParam implements ExcelParam {

    private String fileName;
    {
        try {
            fileName = new String("财务审核明细表".concat(".xls").getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Map<String, String> getHeaderMap() {
        Map<String,String> headerMap = new LinkedHashMap<String, String>(){
            {
                put("orderNo", "订单编号");
                put("companyName", "经销商");
                put("amount", "订单金额");
                put("payAmount", "实收金额");
                put("fundsName", "科目");
                put("customName", "终端客户");
                put("audited", "审核时间");
            }
        };
        return headerMap;
    }

    @Override
    public void createBody(HSSFWorkbook workbook, HSSFSheet sheet, HSSFCellStyle bodyStyle, List<Map<String, Object>> mapList) {
        //表头字体
        Font headerFont = workbook.createFont();
        headerFont.setFontName("微软雅黑");
        headerFont.setFontHeightInPoints((short) 18);
        headerFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        headerFont.setColor(HSSFColor.BLACK.index);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        headerStyle.setLocked(true);
        headerStyle.setWrapText(false);// 自动换行
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        //正文字体
        Font contextFont = workbook.createFont();
        contextFont.setFontName("微软雅黑");
        contextFont.setFontHeightInPoints((short) 12);
        contextFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        contextFont.setColor(HSSFColor.BLACK.index);
        //单元格 有边框
        CellStyle alignLeftStyle = workbook.createCellStyle();
        alignLeftStyle.setFont(contextFont);
        alignLeftStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        alignLeftStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        alignLeftStyle.setLocked(true);
        alignLeftStyle.setWrapText(false);// 自动换行
        alignLeftStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        alignLeftStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        alignLeftStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        alignLeftStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        alignLeftStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框



        //取值
        String orderNum="";
        String params="";
        String amountAll="";
        if(mapList!=null&&mapList.size()>0) {
            Map<String, Object> stringObjectMap = mapList.get(mapList.size() - 1);
            orderNum=stringObjectMap.get("orderNum").toString();
            params=stringObjectMap.get("params").toString();
            amountAll=stringObjectMap.get("amountAll").toString();
        }

        //行号
        int rowNum = 0;
        //第一行
        HSSFRow row3 = sheet.createRow(rowNum++);
        row3.setHeight((short)400);
        String[] values={"导出订单数量:"+orderNum,"","导出条件:"+params,"","","",""};
        for(int i=0;i<values.length;i++){
            HSSFCell cell = row3.createCell(i);
            cell.setCellValue(values[i]);
            cell.setCellStyle(alignLeftStyle);
        }
        //合并
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 6));

        //设置行
        String[] haeder={"订单编号","经销商","订单金额","实收金额","科目","终端客户","审核时间"};
        HSSFRow row2 = sheet.createRow(rowNum++);
        row2.setHeight((short)400);
        for(int i=0;i<getHeaderMap().size();i++){
            HSSFCell tempCell=row2.createCell(i);
            tempCell.setCellValue(haeder[i]);
            tempCell.setCellStyle(alignLeftStyle);
        }
        //遍历集合数据,产生数据行
        HSSFRow row;
        int colIdx;
        for (int m = 0; m < mapList.size(); m++) {
            Map<String, Object> backlog = mapList.get(m);
            row = sheet.createRow(rowNum++ );
            Iterator<String> keys = getHeaderMap().keySet().iterator();
            colIdx = 0;
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = backlog.get(key);
                HSSFCell cell = row.createCell(colIdx++);
                cell.setCellStyle(alignLeftStyle);
                if (null != value) {
                    switch (key) {
                        case "orderNo":
                            cell.setCellValue(value.toString());
                            break;
                        case "companyName":
                            cell.setCellValue(value.toString());
                            break;
                        case "amount":
                            cell.setCellValue(value.toString());
                            break;
                        case "payAmount":
                            cell.setCellValue(value.toString());
                            break;
                        case "fundsName":
                            cell.setCellValue(value.toString());
                            break;
                        case "customName":
                            cell.setCellValue(value.toString());
                            break;
                        case "audited":
                            cell.setCellValue(value.toString());
                            break;
                    }
                }
            }
        }
        //表尾
        int endNum=rowNum++;
        HSSFRow rowend = sheet.createRow(endNum+1);
        rowend.setHeight((short)400);
        HSSFCell cell = rowend.createCell(0);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("合计金额："+amountAll);
        sheet.addMergedRegion(new CellRangeAddress(endNum+1, endNum+1, 0, 6));
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
