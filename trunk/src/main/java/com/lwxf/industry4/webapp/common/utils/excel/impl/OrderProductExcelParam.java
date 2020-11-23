package com.lwxf.industry4.webapp.common.utils.excel.impl;

import com.lwxf.industry4.webapp.common.utils.excel.ExcelParam;
import org.apache.poi.hssf.usermodel.*;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：导出成品库包裹列表
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/12/4 0004 14:49
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class OrderProductExcelParam implements ExcelParam {
	private String fileName;

	{
		try {
			fileName = new String("订单产品(成品入库)列表信息".concat(".xls").getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, String> getHeaderMap() {
		Map<String,String> headerMap = new LinkedHashMap<String, String>(){
			{
				put("orderNo", "订单编号");
			    put("no","产品编号");
                put("statusName","产品状态");
                put("typeName", "产品类型");
				put("packageCount","包裹数量");
                put("payTime", "审核时间");
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
                bodyStyle.setWrapText(true);
                cell.setCellStyle(bodyStyle);
				if (null != value) {
					switch (key) {
                        case "orderNo":
                            cell.setCellValue(value.toString());
                            break;
						case "no":
							cell.setCellValue(value.toString());
							break;
						case "statusName":
							cell.setCellValue(value.toString());
							break;
						case "packageCount":
							cell.setCellValue(value.toString());
							break;
                        case "payTime":
                            cell.setCellValue(value.toString());
							break;
                        case "typeName":
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
