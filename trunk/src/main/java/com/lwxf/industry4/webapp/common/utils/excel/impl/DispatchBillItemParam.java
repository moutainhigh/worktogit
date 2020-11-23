package com.lwxf.industry4.webapp.common.utils.excel.impl;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.*;

import com.lwxf.industry4.webapp.common.enums.storage.FinishedStockItemType;
import com.lwxf.industry4.webapp.common.utils.excel.ExcelParam;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/22/022 16:45
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DispatchBillItemParam implements ExcelParam {

	private String fileName;

	{
		try {
			fileName = new String("待发货清单".concat(".xls").getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, String> getHeaderMap() {
		Map<String,String> headerMap = new LinkedHashMap<String, String>(){
			{
				put("barcode","包装编号");
				put("orderNo","订单编号");
				put("dealerName","经销商名称");
				put("dealerTel","经销商电话");
				put("type","包裹类型");
				put("consigneeName","收货人");
				put("consigneeTel","收货人电话");
				put("address","收货人地址");
				put("planNote","备注");
				put("cityName",null);
			}
		};
		return headerMap;
	}

	@Override
	public void createBody(HSSFWorkbook workbook, HSSFSheet sheet, HSSFCellStyle bodyStyle, List<Map<String, Object>> mapList) {
		//遍历集合数据,产生数据行
		HSSFRow row;
		int colIdx;
		String cityName = "";

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
						case "barcode":
							cell.setCellValue(value.toString());
							break;
						case "orderNo":
							cell.setCellValue(value.toString());
							break;
						case "type":
							cell.setCellValue(FinishedStockItemType.getByValue(Integer.parseInt(value.toString())).getName());
							break;
						case "dealerName":
							cell.setCellValue(value.toString());
							break;
						case "dealerTel":
							cell.setCellValue(value.toString());
							break;
						case "planNote":
							cell.setCellValue(value.toString());
							break;
						case "consigneeName":
							cell.setCellValue(value.toString());
							break;
						case "consigneeTel":
							cell.setCellValue(value.toString());
							break;
						case "address":
							cell.setCellValue(cityName.concat(value.toString()));
							break;
						case "cityName":
							cityName=value.toString();
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
