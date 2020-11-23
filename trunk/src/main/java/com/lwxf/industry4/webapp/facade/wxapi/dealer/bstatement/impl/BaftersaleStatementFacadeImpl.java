package com.lwxf.industry4.webapp.facade.wxapi.dealer.bstatement.impl;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.statements.app.dealer.BaftersaleStatementService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.bstatement.BaftersaleStatementFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/8 0008 10:11
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("baftersaleStatementFacade")
public class BaftersaleStatementFacadeImpl extends BaseFacadeImpl implements BaftersaleStatementFacade {
	@Resource(name = "BaftersaleStatementService")
	private BaftersaleStatementService baftersaleStatementService;

	/**
	 * 本周售后报表
	 * @param companyId
	 * @param countType
	 * @return
	 */
	@Override
	public RequestResult weekAftersaleStatements(String companyId, String countType) {
		MapContext result=MapContext.newOne();
		CountByWeekDto countByWeekDto=this.baftersaleStatementService.findAftersaleStatementByWeek(companyId,countType);
		String[] strings={countByWeekDto.getMon().toString(),countByWeekDto.getTues().toString(),
				countByWeekDto.getWed().toString(),countByWeekDto.getThur().toString(),
				countByWeekDto.getFri().toString(),countByWeekDto.getSat().toString(),
				countByWeekDto.getSun().toString()};
		//本周第一天
		Date startTime = DateUtilsExt.getBeginDayOfWeek();
		//本周最后一天
		Date endTime=DateUtilsExt.getEndDayOfWeek();
		MapContext map=MapContext.newOne();
		map.put("companyId",companyId);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		List list=this.getPieCharts(countType,map);
		result.put("countByWeekDto",strings);
		result.put("type",list);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult monthAftersaleStatements(String companyId, String countType) {
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
		mapContext.put("companyId",companyId);
		mapContext.put("countType",countType);
		CountByMonthDto countByMonthDto=this.baftersaleStatementService.findAftersaleStatementByMonth(mapContext);
		String[] strings={countByMonthDto.getPoint1().toString(),countByMonthDto.getPoint2().toString(),
				countByMonthDto.getPoint3().toString(),countByMonthDto.getPoint4().toString()};
		MapContext map=MapContext.newOne();
		map.put("companyId",companyId);
		map.put("startTime",(Date)timeList.get(0));
		map.put("endTime",(Date)timeList.get(timeList.size()-1));
		List list=this.getPieCharts(countType,map);
		MapContext result=MapContext.newOne();
		result.put("countByMonthDto",strings);
		result.put("type",list);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult quarterAftersaleStatements(String companyId, String countType) {
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
		mapContext.put("companyId",companyId);
		mapContext.put("countType",countType);
		mapContext.put("firstMonth",firstMonth);
		mapContext.put("secondMonth",secondMonth);
		mapContext.put("thirdMonth",thirdMonth);
		CountByQuarterDto countByQuarterDto=this.baftersaleStatementService.findAftersaleStatementByQuarter(mapContext);
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
		map.put("companyId",companyId);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		List list=this.getPieCharts(countType,map);
		MapContext result=MapContext.newOne();
		result.put("type",list);
		result.put("months",months);
		result.put("countByQuarterDto",strings);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult yearAftersaleStatements(String companyId, String countType) {
		CountByYearDto countByYearDto=this.baftersaleStatementService.findAftersaleStatementByYear(companyId,countType);
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
		map.put("companyId",companyId);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		List list=this.getPieCharts(countType,map);
		MapContext result=MapContext.newOne();
		result.put("type",list);
		result.put("countByYearDto",strings);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult aftersaleStatements(String companyId, String countType,MapContext mapContext) {
		MapContext result=MapContext.newOne();
		//柱状图数据
		mapContext.put("companyId",companyId);
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
		List<Date> dates = DateUtilsExt.findDates(startTime, endTime);
		List list=new ArrayList();
		SimpleDateFormat format=new SimpleDateFormat("dd");
		for(Date date:dates) {
			mapContext.put("date",date);
			DateNum dateNum = this.baftersaleStatementService.findAftersaleStatements(mapContext);
			dateNum.setCreatTime(format.format(date));
			list.add(dateNum);
		}
		//圆饼图信息
		MapContext map=MapContext.newOne();
		map.put("companyId",companyId);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		List piceCharts=this.getPieCharts(countType,map);
		result.put("result",list);
		result.put("type",piceCharts);
		return ResultFactory.generateRequestResult(result);
	}

	/**
	 * 圆饼图数据信息
	 * @param countType
	 * @param map
	 * @return
	 */
	public List getPieCharts(String countType,MapContext map){
		List list=new ArrayList();
		map.put("countType",countType);
			MapContext mapZero=this.baftersaleStatementService.findAftersaleStatementType(map);
			int a=0;
			for(int i=0;i<2;i++){
				Map map1=new HashMap<>();
				switch (a){
					case 0:
						map1.put("name","反馈");
						map1.put("value",mapZero.getTypedValue("feedback",Integer.class));
						a=a+1;
						break;
					case 1:
						map1.put("name","补料");
						map1.put("value",mapZero.getTypedValue("buLiao",Integer.class));
						a=a+1;
						break;
				}
				list.add(map1);
			}
		return list;
	}
}
