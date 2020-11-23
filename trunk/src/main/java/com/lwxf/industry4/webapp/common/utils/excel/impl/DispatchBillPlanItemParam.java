package com.lwxf.industry4.webapp.common.utils.excel.impl;

import com.lwxf.industry4.webapp.common.utils.excel.ExcelParam;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能：配送计划包裹导出excel的参数值
 *
 * @author：Administrator
 * @create：2019/5/22/022 10:18
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DispatchBillPlanItemParam implements ExcelParam {

	private String fileName;

//	{
//		try {
//			fileName = new String("发货明细表".concat(".xls").getBytes(), "ISO8859-1");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 定义excel标题
	 * @return
	 */
	@Override
	public Map<String, String> getHeaderMap() {
		Map<String,String> headerMap = new LinkedHashMap<String, String>(){
			{
				put("productNo", "产品编号");
				put("dealerName", "经销商");
				put("cityName", "省市区");
				put("address", "详细地址");
				put("customerName", "终端客户");
				put("series", "系列");
				put("dealerTel", "电话");
				put("countList", "数量");
				put("logisticsName", "物流");
				put("logisticsNo", "物流编号");
				put("productNotes", "备注");
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

		//行号
		int rowNum = 0;
		//设置标题
		HSSFRow row1 = sheet.createRow(rowNum++);
		row1.setHeight((short) 600);
		HSSFCell cell1 = row1.createCell(0);
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		cell1.setCellValue("发货明细表");
        cell1.setCellStyle(headerStyle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
		//第二行
		HSSFRow row3 = sheet.createRow(rowNum++);
		row3.setHeight((short)400);
        String[] values={"公司:","","","发货人:","","","发货电话:","","","",""};
        for(int i=0;i<values.length;i++){
			HSSFCell cell = row3.createCell(i);
			cell.setCellValue(values[i]);
			cell.setCellStyle(alignLeftStyle);
		}
        //合并
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 10));
		//设置行
		String[] haeder={"产品编号","经销商","省市区","详细地址","终端客户","系列","电话","数量","物流","物流编号","备注"};
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
						case "productNo":
							cell.setCellValue(value.toString());
							break;
						case "dealerName":
							cell.setCellValue(value.toString());
							break;
						case "cityName":
							cell.setCellValue(value.toString());
							break;
						case "address":
							cell.setCellValue(value.toString());
							break;
						case "dealerTel":
							cell.setCellValue(value.toString());
							break;
						case "customerName":
							cell.setCellValue(value.toString());
							break;
						case "series":
							cell.setCellValue(value.toString());
							break;
						case "countList":
							cell.setCellValue(value.toString());
							break;
						case "logisticsName":
							cell.setCellValue(value.toString());
							break;
						case "logisticsNo":
							cell.setCellValue(value.toString());
							break;
						case "productNotes":
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
		String[] end={"制表人:","","审核人:","","配件仓:","","特供仓:","","件数:","",""};
		for(int i=0;i<end.length;i++){
			HSSFCell cell = rowend.createCell(i);
			cell.setCellValue(end[i]);
			cell.setCellStyle(alignLeftStyle);
		}
		sheet.addMergedRegion(new CellRangeAddress(endNum+1, endNum+1, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(endNum+1, endNum+1, 2, 3));
		sheet.addMergedRegion(new CellRangeAddress(endNum+1, endNum+1, 4, 5));
		sheet.addMergedRegion(new CellRangeAddress(endNum+1, endNum+1, 6, 7));
		sheet.addMergedRegion(new CellRangeAddress(endNum+1, endNum+1, 8, 10));

		//备注行
		HSSFRow notesRow = sheet.createRow(endNum+2);
		notesRow.setHeight((short)400);
		String[] notes={"备注：特供、五金、理货员、司机全部核对件数核对并签字，工资以签字的发货明细为准，不签字无效，发错担责","","","","","","","","","",""};
		HSSFCell cell = notesRow.createCell(0);
		cell.setCellValue(notes[0]);
		cell.setCellStyle(alignLeftStyle);
		sheet.addMergedRegion(new CellRangeAddress(endNum+2, endNum+2, 0, 10));
	}

	@Override
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
