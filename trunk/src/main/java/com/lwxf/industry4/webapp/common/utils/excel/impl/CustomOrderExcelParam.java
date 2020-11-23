package com.lwxf.industry4.webapp.common.utils.excel.impl;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.*;

import com.lwxf.industry4.webapp.common.utils.excel.ExcelParam;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/12/4 0004 14:49
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class CustomOrderExcelParam implements ExcelParam {
	private String fileName;

	{
		try {
			fileName = new String("订单列表信息".concat(".xls").getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, String> getHeaderMap() {
		Map<String,String> headerMap = new LinkedHashMap<String, String>(){
			{
				put("no","编号");
				put("product","产品名称");
				put("seriesName","产品系列");
				put("productDoor","门型");
				put("doorColor","门板颜色");
				put("bodyTec","柜体工艺");
				put("bodyColor","柜体颜色");
				put("price","产品价格");
				put("install","安装位置");
				put("productStatus","产品状态");
				put("companyName","下单经销商");
				put("dealerTel","经销商电话");
				put("companyAddress","经销商地址");
				put("customer","终端客户");
				put("reciverName","接单人");
				put("placeOrderName","下单人");

			}
		};
		return headerMap;
	}

	@Override
	public void createBody(HSSFWorkbook workbook, HSSFSheet sheet, HSSFCellStyle bodyStyle, List<Map<String, Object>> mapList) {
		//遍历集合数据,产生数据行
		HSSFRow row;
		int colIdx;
		for (int m = 0, len = mapList.size(); m < len; m++) {
			Map<String, Object> backlog = mapList.get(m);
			row = sheet.createRow(m + 1);
			Iterator<String> keys = getHeaderMap().keySet().iterator();
			colIdx = 0;
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = backlog.get(key);
				HSSFCell cell = row.createCell(colIdx++);
				cell.setCellStyle(bodyStyle);
				if (null != value) {
					switch (key) {
						case "no":
							cell.setCellValue(value.toString());
							break;
						case "product":
							cell.setCellValue(value.toString());
							break;
						case "seriesName":
							cell.setCellValue(value.toString());
							break;
						case "productDoor":
							cell.setCellValue(value.toString());
							break;
						case "doorColor":
							cell.setCellValue(value.toString());
							break;
						case "bodyTec":
							cell.setCellValue(value.toString());
							break;
						case "bodyColor":
							cell.setCellValue(value.toString());
							break;
						case "price":
							cell.setCellValue(value.toString());
							break;
						case "install":
							cell.setCellValue(value.toString());
							break;
						case "productStatus":
							cell.setCellValue(value.toString());
							break;
						case "companyName":
							cell.setCellValue(value.toString());
							break;
						case "dealerTel":
							cell.setCellValue(value.toString());
							break;
						case "companyAddress":
							cell.setCellValue(value.toString());
							break;
						case "customer":
							cell.setCellValue(value.toString());
							break;
						case "reciverName":
							cell.setCellValue(value.toString());
							break;
						case "placeOrderName":
							cell.setCellValue(value.toString());
							break;
					}
				}
			}
		}

	}

	@Override
	public String getFileName() {
		return this.fileName;
	}
}
