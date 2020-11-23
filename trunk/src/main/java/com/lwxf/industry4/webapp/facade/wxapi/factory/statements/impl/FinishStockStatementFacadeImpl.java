package com.lwxf.industry4.webapp.facade.wxapi.factory.statements.impl;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.finishStockStatement.FinishStockStatementService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.factory.statements.FinishStockStatementFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/3 0003 15:12
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "finishStockStatementFacade")
public class FinishStockStatementFacadeImpl extends BaseFacadeImpl implements FinishStockStatementFacade {
	@Resource(name = "finishStockStatementService")
	private FinishStockStatementService finishStockStatementService;

	@Override
	public RequestResult weekFinishStockBillStatements(String branchId, String countType) {
		//柱状图信息
		CountByWeekDto countByWeekDto=this.finishStockStatementService.findFinishStockStatementByWeek(branchId,countType);
		String[] strings={countByWeekDto.getMon().toString(),countByWeekDto.getTues().toString(),
				countByWeekDto.getWed().toString(),countByWeekDto.getThur().toString(),
				countByWeekDto.getFri().toString(),countByWeekDto.getSat().toString(),
				countByWeekDto.getSun().toString()};
		//本周第一天
		Date startTime = DateUtilsExt.getBeginDayOfWeek();
		//本周最后一天
		Date endTime=DateUtilsExt.getEndDayOfWeek();
		MapContext map=MapContext.newOne();
		map.put("branchId",branchId);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		//饼图信息
		List list=this.getList(map,countType);
		MapContext result=MapContext.newOne();
		result.put("countByWeekDto",strings);
		result.put("list",list);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult monthFinisheStockStatements(String branchId, String countType) {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		List timeList = DateUtilsExt.getTimeList(year, month-1, 7);
		MapContext mapContext=MapContext.newOne();
		mapContext.put("oneTime",(Date)timeList.get(0));
		mapContext.put("twoTime",(Date)timeList.get(1));
		mapContext.put("threeTime",(Date)timeList.get(2));
		mapContext.put("fourTime",(Date)timeList.get(3));
		mapContext.put("fiveTime",(Date)timeList.get(timeList.size()-1));
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		CountByMonthDto countByMonthDto=this.finishStockStatementService.findFinishStockStatementByMonth(mapContext);
		String[] strings={countByMonthDto.getPoint1().toString(),countByMonthDto.getPoint2().toString(),
				countByMonthDto.getPoint3().toString(),countByMonthDto.getPoint4().toString()};
		MapContext map=MapContext.newOne();
		map.put("branchId",branchId);
		map.put("startTime",(Date)timeList.get(0));
		map.put("endTime",(Date)timeList.get(timeList.size()-1));
		List list=this.getList(map,countType);
		MapContext result=MapContext.newOne();
		result.put("countByMonthDto",strings);
		result.put("type",list);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult quarterFinishStockStatements(String branchId, String countType) {
		//柱状图信息
		Date date= DateUtil.getSystemDate();
		//获取本季的第一个月
		Date firstSeasonDate = DateUtilsExt.getFirstSeasonDate(date);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(firstSeasonDate);
		int firstMonth=calendar.get(Calendar.MONTH)+1;
		int secondMonth=firstMonth+1;
		int thirdMonth=firstMonth+2;
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		mapContext.put("firstMonth",firstMonth);
		mapContext.put("secondMonth",secondMonth);
		mapContext.put("thirdMonth",thirdMonth);
		CountByQuarterDto countByQuarterDto=this.finishStockStatementService.findFinishStockStatementByQuarter(mapContext);
		String[] strings={countByQuarterDto.getMonth1().toString(),
				countByQuarterDto.getMonth2().toString(),
				countByQuarterDto.getMonth3().toString()};
		String[] months={firstMonth+"月份",secondMonth+"月份",thirdMonth+"月份"};
		//圆饼图信息
		//本季度第一个月的第一天
		calendar.setTime(firstSeasonDate);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date startTime=calendar.getTime();
		//某季度的最后一个月
		SimpleDateFormat dateFormat=new SimpleDateFormat("MM");
		int a =Integer.parseInt(dateFormat.format(firstSeasonDate)) ;
		calendar.set(Calendar.MONTH,a+1);
		Date endTime=calendar.getTime();
		MapContext map=MapContext.newOne();
		map.put("branchId",branchId);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		List list=this.getList(map,countType);
		MapContext result=MapContext.newOne();
		result.put("type",list);
		result.put("months",months);
		result.put("countByQuarterDto",strings);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult yearFinishStockStatements(String branchId, String countType) {
		CountByYearDto countByYearDto=this.finishStockStatementService.findAFinishStockStatementByYear(branchId,countType);
		String[] strings={countByYearDto.getJun().toString(),
				countByYearDto.getFeb().toString(),
				countByYearDto.getMar().toString(),
				countByYearDto.getApr().toString(),
				countByYearDto.getJun().toString(),
				countByYearDto.getJul().toString(),
				countByYearDto.getAug().toString(),
				countByYearDto.getSept().toString(),
				countByYearDto.getOct().toString(),
				countByYearDto.getNov().toString(),
				countByYearDto.getDece().toString()};
		MapContext map=MapContext.newOne();
		Date startTime=DateUtilsExt.getBeginDayOfYear();//本年的开始时间
		Date endTime=DateUtilsExt.getEndDayOfYear();//本年的结束时间
		map.put("branchId",branchId);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		List list=this.getList(map,countType);
		MapContext result=MapContext.newOne();
		result.put("type",list);
		result.put("countByYearDto",strings);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult finishStockStatements(String branchId, String countType, MapContext mapContext) {
		MapContext result=MapContext.newOne();
		//柱状图数据
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		//选择开始时间和结束时间
		Date startTime = new Date();
		Date endTime = new Date();
		if (mapContext.containsKey("startTime")) {
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				endTime = DateUtil.getSystemDate();
				if (mapContext.containsKey("endTime")) {
					endTime = simpleDateFormat.parse(mapContext.getTypedValue("endTime", String.class));
				}
				String timeValue=mapContext.getTypedValue("startTime", String.class);
				startTime = simpleDateFormat.parse(timeValue);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			endTime = DateUtil.getSystemDate();
			startTime = DateUtilsExt.getFrontDay(endTime, 6);
		}
		List<Date> dates = new ArrayList<>();
		List list=new ArrayList();
		SimpleDateFormat format=new SimpleDateFormat("MM-dd");
		try {
			if(mapContext.getTypedValue("dateType",String.class).equals("0")) {//按日
				dates = DateUtilsExt.findDates(startTime, endTime);
			}else if(mapContext.getTypedValue("dateType",String.class).equals("1")){//按月
				dates = DateUtilsExt.getMonthBetween(startTime,endTime);
				format=new SimpleDateFormat("yyyy-MM");
			}else if(mapContext.getTypedValue("dateType",String.class).equals("2")){//按年
				dates = DateUtilsExt.getYearBetween(startTime,endTime);
				format=new SimpleDateFormat("yyyy");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for(Date date:dates) {
			mapContext.put("date",date);
			DateNum dateNum = this.finishStockStatementService.findFinishStockStatements(mapContext);
			dateNum.setCreatTime(format.format(date));
			list.add(dateNum);
		}
		//圆饼图信息（按日查询不用处理）
		if(mapContext.getTypedValue("dateType",String.class).equals("1")){//按月查询，开始时间为开始月的第一天，结束时间为结束月的最后一天
			startTime=DateUtilsExt.getFirstDayOfMonth(startTime);
			endTime=DateUtilsExt.getLastDayOfMonth(endTime);
		}
		if(mapContext.getTypedValue("dateType",String.class).equals("2")){//按年查询，开始时间为开始年的第一天，结束时间为结束年的最后一天
			startTime=DateUtilsExt.getFirstDayOfYear(startTime);
			endTime=DateUtilsExt.getLastDayOfYear(endTime);
		}
		MapContext map=MapContext.newOne();
		map.put("branchId",branchId);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		List piceCharts=this.getList(map,countType);
		result.put("result",list);
		result.put("type",piceCharts);
		return ResultFactory.generateRequestResult(result);
	}

	private List getList(MapContext map,String countType) {
		map.put("countType",countType);
		List list = new ArrayList();
			MapContext mapContext = this.finishStockStatementService.findList(map);
			int a = 0;
			for (int i = 0; i < 3; i++) {
				Map map1 = new HashMap<>();
				switch (a) {
					case 0:
						map1.put("name", "柜体包装");
						map1.put("value", mapContext.getTypedValue("guiTi", Integer.class));
						a = a + 1;
						break;
					case 1:
						map1.put("name", "门板包装");
						map1.put("value", mapContext.getTypedValue("menBan", Integer.class));
						a = a + 1;
						break;
					case 2:
						map1.put("name", "五金包装");
						map1.put("value", mapContext.getTypedValue("wuJin", Integer.class));
						a = a + 1;
						break;
				}
				list.add(map1);
			}
		return list;
	}
}
