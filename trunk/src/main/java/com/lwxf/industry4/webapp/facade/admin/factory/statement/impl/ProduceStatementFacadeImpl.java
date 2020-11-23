package com.lwxf.industry4.webapp.facade.admin.factory.statement.impl;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.lwxf.industry4.webapp.facade.admin.factory.statement.ProduceStatementFacade;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.produce.ProduceStatementService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/3 0003 17:12
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("pcProduceStatementFacade")
public class ProduceStatementFacadeImpl extends BaseFacadeImpl implements ProduceStatementFacade {
	@Resource(name = "produceStatementService")
	private ProduceStatementService produceStatementService;
	@Override
	public RequestResult weekProduceStatements(String branchId, String countType) {
		CountByWeekDto countByWeekDto=this.produceStatementService.findProduceStatementByWeek(branchId,countType);
		String[] strings={countByWeekDto.getMon().toString(),countByWeekDto.getTues().toString(),
				countByWeekDto.getWed().toString(),countByWeekDto.getThur().toString(),
				countByWeekDto.getFri().toString(),countByWeekDto.getSat().toString(),
				countByWeekDto.getSun().toString()};
		MapContext mapContext=MapContext.newOne();
		mapContext.put("result",strings);
		return ResultFactory.generateRequestResult(mapContext);
	}

	@Override
	public RequestResult monthProduceStatements(String branchId, String countType) {
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
		CountByMonthDto countByMonthDto=this.produceStatementService.findProduceStatementByMonth(mapContext);
		String[] strings={countByMonthDto.getPoint1().toString(),countByMonthDto.getPoint2().toString(),
				countByMonthDto.getPoint3().toString(),countByMonthDto.getPoint4().toString()};
		return ResultFactory.generateRequestResult(strings);
	}

	@Override
	public RequestResult quarterProduceStatements(String branchId, String countType) {
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
		CountByQuarterDto countByQuarterDto=this.produceStatementService.findProduceStatementByQuarter(mapContext);
		String[] strings={countByQuarterDto.getMonth1().toString(),
				countByQuarterDto.getMonth2().toString(),
				countByQuarterDto.getMonth3().toString()};
		String[] months={firstMonth+"月份",secondMonth+"月份",thirdMonth+"月份"};
		List list=new ArrayList();
		list.add(strings);
		list.add(months);
		return ResultFactory.generateRequestResult(list);
	}

	@Override
	public RequestResult yearProduceStatements(String branchId, String countType) {
		CountByYearDto countByYearDto=this.produceStatementService.findProduceStatementByYear(branchId,countType);
		String[] strings={countByYearDto.getJan().toString(),
				countByYearDto.getFeb().toString(),
				countByYearDto.getMar().toString(),
				countByYearDto.getApr().toString(),
				countByYearDto.getMay().toString(),
				countByYearDto.getJun().toString(),
				countByYearDto.getJul().toString(),
				countByYearDto.getAug().toString(),
				countByYearDto.getSept().toString(),
				countByYearDto.getOct().toString(),
				countByYearDto.getNov().toString(),
				countByYearDto.getDece().toString()};
		return ResultFactory.generateRequestResult(strings);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult produceStatements(String branchId, String countType, MapContext mapContext) {
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		MapContext result=MapContext.newOne();
		if(countType==null||countType.trim().equals("")){
			mapContext.put("countType","0");
		}
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
				startTime = simpleDateFormat.parse(mapContext.getTypedValue("startTime", String.class));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			endTime = DateUtil.getSystemDate();
			startTime = DateUtilsExt.getFrontDay(endTime, 6);
		}
		List<Date> dates = new ArrayList<>();
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
		List list=new ArrayList();
		for(Date date:dates) {
			mapContext.put("date",date);
			DateNum dateNum = this.produceStatementService.findProduceStatements(mapContext);
			dateNum.setCreatTime(format.format(date));
			list.add(dateNum);
		}
		result.put("result",list);
		return ResultFactory.generateRequestResult(result);
	}

    @Override
    public RequestResult orderProduceCount(MapContext mapContext) {
        return ResultFactory.generateRequestResult(this.produceStatementService.orderProduceCount(mapContext));
    }

    @Override
    public RequestResult orderProduceTypeCount(MapContext mapContext) {
        List<MapContext> list = new ArrayList<>();
        // 构造条件
        Map<String,String> map = new LinkedHashMap<String, String>(){
            {
                put("waitProduce", "1"); // 待生产
                put("atProduce","2"); // 生产中
                put("matureProduce","3"); // 即将逾期
                put("lateProduce", "4"); // 延期
            }
        };
        MapContext context = MapContext.newOne();
        for(Map.Entry<String, String> entry : map.entrySet()) {
            mapContext.put("condition", entry.getValue());
            List<MapContext> mc = this.produceStatementService.orderProduceTypeCountByCondition(mapContext);
            if (mc != null && mc.size() > 0) {
                Long total = 0L;
                for (MapContext m : mc) {
                    total += (Long) m.get("count");
                }
                MapContext mdata = MapContext.newOne();
                mdata.put("total", total);
                mdata.put("detail", mc);
                context.put(entry.getKey(), mdata);
                list.add(context);
            }

        }

        return ResultFactory.generateRequestResult(context);
    }

    @Override
    public RequestResult orderProduceDelayCount(String branchId, String date) {
	    MapContext mapContext = MapContext.newOne();
	    mapContext.put("branchId", branchId);
	    // 获取指定月的全部天数
        List<Date> dates = DateUtilsExt.getDaysOfMonth(date);
        List<Map> list = new ArrayList<>(dates.size());
        if (dates != null && dates.size() > 0) {
            for (Date day : dates) {
                mapContext.put("day", day);
				Map lm = new LinkedHashMap<>();
                lm.put("date", DateUtil.dateTimeToString(day, "M-d"));
                lm.put("count", this.produceStatementService.orderProduceDelayByDay(mapContext));
				list.add(lm);
            }
        }
        return ResultFactory.generateRequestResult(list);
    }
}
