package com.lwxf.industry4.webapp.facade.admin.factory.statement.impl;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.customorder.CoordinationState;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentStatus;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.enums.user.UserRole;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import org.springframework.stereotype.Component;

import com.lwxf.industry4.webapp.bizservice.corporatePartners.CorporatePartnersService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptMemberService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.orderStatements.CustomOrderStatementService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductType;
import com.lwxf.industry4.webapp.common.enums.order.OrderType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.corporatePartners.CorporatePartnersDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.CustomOrderStatementFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.DateUtil;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.transaction.annotation.Transactional;

@Component("customOrderStatementFacade")
public class CustomOrderStatementFacadeImpl extends BaseFacadeImpl implements CustomOrderStatementFacade {

	@Resource(name = "customOrderStatementService")
	private CustomOrderStatementService customOrderStatementService;
	@Resource(name = "basecodeService")
	private BasecodeService basecodeService;
	@Resource(name = "deptService")
	private DeptService deptService;
	@Resource(name = "deptMemberService")
	private DeptMemberService deptMemberService;
	@Resource(name = "orderProductService")
	private OrderProductService orderProductService;
	@Resource(name = "corporatePartnersService")
	private CorporatePartnersService corporatePartnersService;
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "roleService")
	private RoleService roleService;
    @Resource(name = "paymentService")
    private PaymentService paymentService;


	//	@Override
//	@Transactional(value = "transactionManager")
//	public RequestResult selectByfilter(Date StartDate, Date endDate, MapContext map, Integer dateType) {
//		MapContext mapRes = MapContext.newOne();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		List<MapContext> listRes = new ArrayList<>();
//		if (dateType == null) {
//			dateType = 0;
//		}
//		if (dateType == 0) { //按日统计
//			if (StartDate != null && endDate != null) {
//				SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
//				List<Date> list = DateUtilsExt.findDates(StartDate, endDate);
//				if (list != null && list.size() > 0) {
//					for (Date date : list) {
//						map.put("created", sdf.format(date));
//						MapContext mapValue = MapContext.newOne();
//						mapValue.put("date", sdf1.format(date));
//						mapValue.put("value", customOrderStatementService.selectByfilter(map));
//						listRes.add(mapValue);
//					}
//				}
//			}
//		} else if (dateType == 1) { //按月统计
//			SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
//			SimpleDateFormat sdfParam = new SimpleDateFormat("yyyy-MM-dd");
//			if (StartDate != null && endDate != null) {
//				List<Date> list = null;
//				try {
//					list = DateUtilsExt.getMonthBetween(StartDate, endDate);
//					if (list != null && list.size() > 0) {
//						for (Date date : list) {
//							map.put("beginDate", sdfParam.format(DateUtilsExt.getFirstDayOfMonth(date)));
//							map.put("endDate", sdfParam.format(DateUtilsExt.getLastDayOfMonth(date)));
//							MapContext mapValue = MapContext.newOne();
//							mapValue.put("date", sdfMonth.format(date));
//							mapValue.put("value", customOrderStatementService.selectByfilter(map));
//							listRes.add(mapValue);
//						}
//					}
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//			}
//		} else if (dateType == 2) { //按年统计
//			SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
//			SimpleDateFormat sdfParam = new SimpleDateFormat("yyyy-MM-dd");
//			if (StartDate != null && endDate != null) {
//				List<Date> list = null;
//				try {
//					list = DateUtilsExt.getYearBetween(StartDate, endDate);
//					if (list != null && list.size() > 0) {
//						for (Date date : list) {
//							map.put("beginDate", sdfParam.format(DateUtilsExt.getFirstDayOfYear(date)));
//							map.put("endDate", sdfParam.format(DateUtilsExt.getLastDayOfYear(date)));
//							MapContext mapValue = MapContext.newOne();
//							mapValue.put("date", sdfYear.format(date));
//							mapValue.put("value", customOrderStatementService.selectByfilter(map));
//							listRes.add(mapValue);
//						}
//					}
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		mapRes.put("chart1", listRes);
//		return ResultFactory.generateRequestResult(mapRes);
//	}
//
//	@Override
//	@Transactional(value = "transactionManager")
//	public RequestResult countOrderByWeek(MapContext map) {
//		MapContext mapRes = MapContext.newOne();
//		CountByWeekDto dto = customOrderStatementService.countOrderByWeek(map);
//		List<MapContext> listRes = new ArrayList<MapContext>();
//		MapContext mapChart1 = MapContext.newOne();
//		try {
//			if (dto != null) {
//				Class cls = dto.getClass();
//				Field[] fields = cls.getDeclaredFields();
//				for (int i = 0; i < fields.length; i++) {
//					Field f = fields[i];
//					f.setAccessible(true);
//					mapChart1.put(f.getName(), f.get(dto));
//				}
//			}
//		} catch (IllegalAccessException ex) {
//			ex.printStackTrace();
//		}
//		mapRes.put("chart1", mapChart1);
//		return ResultFactory.generateRequestResult(mapRes);
//	}
//
//	@Override
//	public RequestResult countOrderByMonth(MapContext map) {
//		MapContext mapRes = MapContext.newOne();
//		CountByMonthDto dto = customOrderStatementService.countOrderByMonth(map);
//		MapContext mapChart1 = MapContext.newOne();
//		try {
//			if (dto != null) {
//				Class cls = dto.getClass();
//				Field[] fields = cls.getDeclaredFields();
//				for (int i = 0; i < fields.length; i++) {
//					Field f = fields[i];
//					f.setAccessible(true);
//					mapChart1.put(f.getName(), f.get(dto));
//				}
//			}
//		} catch (IllegalAccessException ex) {
//			ex.printStackTrace();
//		}
//		mapRes.put("chart1", mapChart1);
//		return ResultFactory.generateRequestResult(mapRes);
//	}
//
//	@Override
//	public RequestResult countOrderByQuarter(MapContext map) {
//		MapContext mapRes = MapContext.newOne();
//		CountByQuarterDto dto = customOrderStatementService.countOrderByQuarter(map);
//		MapContext mapChart1 = MapContext.newOne();
//		try {
//			if (dto != null) {
//				Class cls = dto.getClass();
//				Field[] fields = cls.getDeclaredFields();
//				for (int i = 0; i < fields.length; i++) {
//					Field f = fields[i];
//					f.setAccessible(true);
//					mapChart1.put(f.getName(), f.get(dto));
//				}
//			}
//		} catch (IllegalAccessException ex) {
//			ex.printStackTrace();
//		}
//		mapRes.put("chart1", mapChart1);
//		return ResultFactory.generateRequestResult(mapRes);
//	}
//
//	@Override
//	public RequestResult countOrderByYear(MapContext map) {
//		MapContext mapRes = MapContext.newOne();
//		CountByYearDto dto = customOrderStatementService.countOrderByYear(map);
//		MapContext mapChart1 = MapContext.newOne();
//		try {
//			if (dto != null) {
//				Class cls = dto.getClass();
//				Field[] fields = cls.getDeclaredFields();
//				for (int i = 0; i < fields.length; i++) {
//					Field f = fields[i];
//					f.setAccessible(true);
//					mapChart1.put(f.getName(), f.get(dto));
//				}
//			}
//		} catch (IllegalAccessException ex) {
//			ex.printStackTrace();
//		}
//		mapRes.put("chart1", mapChart1);
//		return ResultFactory.generateRequestResult(mapRes);
//	}
//
	@Override
	public RequestResult countOrderSeries(String startDateTime, String endDateTime, MapContext mapContext) {
		MapContext result = MapContext.newOne();
		//如果不选择时间，则默认当前7天时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (startDateTime == null || startDateTime.equals("")) {
			endDateTime = dateFormat.format(DateUtil.now());
			startDateTime = DateUtilsExt.getDayDate(-6);
		}
		mapContext.put("startDateTime", startDateTime);
		mapContext.put("endDateTime", endDateTime);
		Integer deFlag = 0;
		String seriesType = "orderProductSeries";
		//查询产品系列
		List<Basecode> basecodes = this.basecodeService.findByTypeAndDelFlag(seriesType, deFlag);
		//橱柜
		Integer productType = 0;
		mapContext.put("type", productType);
		List list0 = new ArrayList();
		for (Basecode basecode : basecodes) {
			Integer code = Integer.valueOf(basecode.getCode());
			mapContext.put("series", code);
			MapContext map = this.customOrderService.countOrderSeries(mapContext);
			map.put("seriesName", basecode.getValue());
			list0.add(map);
		}
		//衣柜
		productType = 1;
		mapContext.put("type", productType);
		List list1 = new ArrayList();
		for (Basecode basecode : basecodes) {
			Integer code = Integer.valueOf(basecode.getCode());
			mapContext.put("series", code);
			MapContext map = this.customOrderService.countOrderSeries(mapContext);
			map.put("seriesName", basecode.getValue());
			list1.add(map);
		}
		result.put("橱柜", list0);
		result.put("衣柜", list1);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult countCorporatePartners(String startDateTime, String endDateTime, MapContext mapContext) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		//默认时间
		if (startDateTime == null || startDateTime.equals("")) {
			endDateTime = dateFormat.format(DateUtil.now());
			startDateTime = DateUtilsExt.getDayDate(-6);
		}
		try {
			startTime = dateFormat.parse(startDateTime);
			endTime = dateFormat.parse(endDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Date> dates = DateUtilsExt.findDates(startTime, endTime);

		//查询外协厂家
		List<MapContext> CorporatePartners = this.customOrderStatementService.findCorporatesByBranchId(WebUtils.getCurrBranchId());
//		mapContext.put("startDateTime",startDateTime);
//		mapContext.put("endDateTime",endDateTime);
		for (MapContext map : CorporatePartners) {
			String CorporateName = map.getTypedValue("coordinationName", String.class);
			List list = new ArrayList();
			for (Date date : dates) {
				mapContext.put("created", date);
				mapContext.put("coordinationName", CorporateName);
				MapContext mapContext1 = this.customOrderStatementService.findByCorporateNameAndDate(mapContext);
				MapContext mapContext2 = MapContext.newOne();
				mapContext2.put("name", dateFormat.format(date));
				mapContext2.put("value", mapContext1);
				list.add(mapContext2);
				map.put("list", list);
			}
		}
		return ResultFactory.generateRequestResult(CorporatePartners);

	}

	@Override
	public RequestResult countSalemanAchievements(String startDateTime, String endDateTime, MapContext mapContext) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		//默认时间
		if (startDateTime == null || startDateTime.equals("")) {
			endDateTime = dateFormat.format(DateUtil.now());
			startDateTime = DateUtilsExt.getDayDate(-6);
		}
		try {
			startTime = dateFormat.parse(startDateTime);
			endTime = dateFormat.parse(endDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//查询大区经理列表
		List<Date> dates = DateUtilsExt.findDates(startTime, endTime);
		String key = "scyx";
		String branchId = WebUtils.getCurrBranchId();
		Dept dept = this.deptService.findDeptByKey(key, branchId);
		if (dept == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		String deptId = dept.getId();
		List<MapContext> deptMemberDtos = this.deptMemberService.findEmployeeNameByDeptId(deptId);
		for (MapContext map : deptMemberDtos) {
			String manager = map.getTypedValue("id", String.class);
			mapContext.put("businessManager", manager);
			List list = new ArrayList();
			for (Date date : dates) {
				mapContext.put("created", date);
				MapContext mapContext1 = this.customOrderStatementService.countSalemanAchievements(mapContext);
				MapContext mapContext2 = MapContext.newOne();
				mapContext2.put("name", dateFormat.format(date));
				mapContext2.put("value", mapContext1);
				list.add(mapContext2);
				map.put("list", list);

			}
		}
		return ResultFactory.generateRequestResult(deptMemberDtos);
	}

	@Override
	public RequestResult selectOrderCount(String branchId, String yearTime) {
		//默认本年
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String startTime = null;
		String endTime = null;
		ArrayList<Date> dates = new ArrayList<Date>();
		if (yearTime == null || yearTime.equals("")) {
			startTime = DateUtilsExt.getNowYear().toString() + "-" + 1;
			endTime = dateFormat.format(DateUtil.now());
		} else {
			startTime = yearTime + "-" + 1;
			endTime = yearTime + "-" + 12;
		}
		try {
			Date start = dateFormat.parse(startTime);
			Date end = dateFormat.parse(endTime);
			dates = DateUtilsExt.getMonthBetween(start, end);//两个日期内的月份集合
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ArrayList result = new ArrayList();
		for (Date date : dates) {//日期循环查询
			MapContext map = MapContext.newOne();
			map.put("branchId", branchId);
			map.put("date", date);
			MapContext orderCount = this.customOrderService.selectOrderCount(map);//查询订单总数和订单金额
			MapContext produceCount = this.customOrderService.selectProduceCount(map);//查询外协总数和外协总金额
			MapContext afterCount = this.customOrderService.selectAfterCount(map);//查询售后总数
			orderCount.put("produceCount", produceCount.getTypedValue("produceCount", String.class));
			orderCount.put("produceAmount", produceCount.getTypedValue("produceAmount", String.class));
			orderCount.put("afterCount", afterCount.getTypedValue("afterCount", String.class));
			MapContext mapContext = MapContext.newOne();
			mapContext.put("date", dateFormat.format(date));
			mapContext.put("orderCount", orderCount);
			result.add(mapContext);
		}
		return ResultFactory.generateRequestResult(result);
	}

	/**
	 * 订单月份明细统计接口
	 *
	 * @param branchId
	 * @param monthTime
	 * @return
	 */
	@Override
	public RequestResult selectOrderMonthCount(String branchId, String monthTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List list = new ArrayList();
		//查询橱柜、衣柜、五金、样块数量
		MapContext map = this.customOrderService.findCountByBidAndType(branchId, monthTime);
		//某月所有日期
		List<Date> daysOfMonth = DateUtilsExt.getDaysOfMonth(monthTime);
		for (Date date : daysOfMonth) {
			MapContext count = MapContext.newOne();
			MapContext mapContext = MapContext.newOne();
			mapContext.put("branchId", branchId);
			mapContext.put("date", date);
			//下单趋势和金额
			MapContext mapContext1 = this.customOrderService.selectOrderCountByDay(mapContext);
			count.put("orderCount", mapContext1.getTypedValue("orderCount", String.class));
			count.put("orderAmount", mapContext1.getTypedValue("orderAmount", String.class));
			//橱柜趋势和金额
			mapContext.put("orderProductType", 0);
			MapContext mapContext2 = this.customOrderService.selectOrderCountByDay(mapContext);
			count.put("chuguiCount", mapContext2.getTypedValue("orderCount", String.class));
			count.put("chuguiAmount", mapContext2.getTypedValue("orderAmount", String.class));
			//衣柜趋势和金额
			mapContext.put("orderProductType", 1);
			MapContext mapContext3 = this.customOrderService.selectOrderCountByDay(mapContext);
			count.put("yiguiCount", mapContext3.getTypedValue("orderCount", String.class));
			count.put("yiguiAmount", mapContext3.getTypedValue("orderAmount", String.class));
			count.put("date", dateFormat.format(date));
			list.add(count);
		}
		//经销商订单数据统计
		List<MapContext> list1 = this.customOrderService.findDealerByTime(branchId, monthTime);//查询下单的经销商列表
		for (MapContext mapContext : list1) {
			String companyId = mapContext.getTypedValue("dealerId", String.class);
			List<CustomOrder> list2 = this.customOrderService.findOrderCOuntByCidAndTime(companyId, monthTime);
			BigDecimal amount = new BigDecimal("0");
			Integer productCount = 0;
			for (CustomOrder order : list2) {
				if (order.getFactoryFinalPrice() != null && !order.getFactoryFinalPrice().equals("")) {
					amount = amount.add(order.getFactoryFinalPrice());
				}
				String orderId = order.getId();
				List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
				productCount = productCount + listByOrderId.size();
			}
			mapContext.put("orderNum", list2.size());
			mapContext.put("orderAmount", amount);
			mapContext.put("productNum", productCount);
		}
		MapContext result = MapContext.newOne();
		result.put("dealer", list1);
		result.put("orderList", list);
		result.put("yuanbing", map);
		return ResultFactory.generateRequestResult(result);
	}

	/**
	 * 外协单月份明细统计接口
	 *
	 * @param branchId
	 * @param monthTime
	 * @return
	 */
	@Override
	public RequestResult selectcoordinationMonthCount(String branchId, String monthTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//外协下单详情
		List<MapContext> map = this.customOrderService.selectcoordinationMonthCount(branchId, monthTime);
		//某月所有日期
		List<Date> daysOfMonth = DateUtilsExt.getDaysOfMonth(monthTime);
		List list = new ArrayList();
		for (Date date : daysOfMonth) {//所有日期下的外协单数据列表
			MapContext mapContext = MapContext.newOne();
			mapContext.put("branchId", branchId);
			mapContext.put("date", date);
			MapContext mapContext1 = this.customOrderService.findCountAndAmountByDate(mapContext);
			MapContext mapContext2 = MapContext.newOne();
			mapContext2.put("date", dateFormat.format(date));
			mapContext2.put("countAmount", mapContext1.getTypedValue("countAmount", String.class));
			mapContext2.put("countNum", mapContext1.getTypedValue("countNum", String.class));
			list.add(mapContext2);
		}
		MapContext result = MapContext.newOne();
		result.put("coordination", map);
		result.put("list", list);
		return ResultFactory.generateRequestResult(result);
	}

	/**
	 * 外协厂家排名统计接口
	 *
	 * @param startTime
	 * @param endTime
	 * @param mapContext
	 * @return
	 */
	@Override
	public RequestResult coordinationOrderRanking(String startTime, String endTime, MapContext mapContext) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//如果没有开始时间和结束时间，则默认当月
		if (startTime == null || startTime.equals("")) {
			startTime = dateFormat.format(DateUtilsExt.getFirstDayOfMonth(com.lwxf.commons.utils.DateUtil.getSystemDate()));
		}
		if (endTime == null || endTime.equals("")) {
			endTime = dateFormat.format(DateUtilsExt.getLastDayOfMonth(com.lwxf.commons.utils.DateUtil.getSystemDate()));
		}
		mapContext.put("startTime", startTime);
		mapContext.put("endTime", endTime);
		List<MapContext> mapContexts = this.customOrderService.coordinationOrderRanking(mapContext);
		return ResultFactory.generateRequestResult(mapContexts);
	}

	/**
	 * 外协厂家详情数据统计
	 *
	 * @param coordinationName
	 * @return
	 */
	@Override
	public RequestResult selectcoordinationInfoCount(String coordinationName, String yearTime) {
		//外协厂家信息
		CorporatePartnersDto corporatePartners = this.corporatePartnersService.findCorporateInfoByName(coordinationName);
		//月份下单数据和金额
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String startTime = null;
		String endTime = null;
		ArrayList<Date> dates = new ArrayList<Date>();
		if (yearTime == null || yearTime.equals("")) {
			startTime = DateUtilsExt.getNowYear().toString() + "-" + 1;
			endTime = dateFormat.format(DateUtil.now());
		} else {
			startTime = yearTime + "-" + 1;
			endTime = yearTime + "-" + 12;
		}
		try {
			Date start = dateFormat.parse(startTime);
			Date end = dateFormat.parse(endTime);
			dates = DateUtilsExt.getMonthBetween(start, end);//两个日期内的月份集合
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList list = new ArrayList();
		for (Date date : dates) {
			MapContext mapContext = MapContext.newOne();
			mapContext.put("monthDate", date);
			mapContext.put("name", coordinationName);
			MapContext context = this.customOrderService.selectcoordinationCount(mapContext);
			MapContext mapContext1 = MapContext.newOne();
			mapContext1.put("date", dateFormat.format(date));
			mapContext1.put("countAmount", context.getTypedValue("countAmount", String.class));
			mapContext1.put("countNum", context.getTypedValue("countNum", String.class));
			list.add(mapContext1);
		}
		//近30天走势

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dayDate = DateUtilsExt.getDayDate(-30);//前30天的日期
		List<Date> daydates = new ArrayList<>();
		try {
			Date date = simpleDateFormat.parse(dayDate);
			daydates = DateUtilsExt.findDates(date, DateUtil.now());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ArrayList dayList = new ArrayList();
		for (Date day : daydates) {
			MapContext mapContext = MapContext.newOne();
			mapContext.put("dayDate", day);
			mapContext.put("name", coordinationName);
			MapContext context = this.customOrderService.selectcoordinationCount(mapContext);
			MapContext mapContext1 = MapContext.newOne();
			mapContext1.put("date", simpleDateFormat.format(day));
			mapContext1.put("countAmount", context.getTypedValue("countAmount", String.class));
			mapContext1.put("countNum", context.getTypedValue("countNum", String.class));
			dayList.add(mapContext1);
		}

		MapContext result = MapContext.newOne();
		result.put("info", corporatePartners);
		result.put("monthList", list);
		result.put("dayList", dayList);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult countOrderDeptAchievements(MapContext mapContext) {
		String deptId = "4v0wdo6nmkg0";//订单部id
		mapContext.put("deptId", deptId);
		String role = mapContext.getTypedValue("role", String.class);
		if (role != null) {
			if (role.equals("1")) {
				mapContext.put("receiverRole", "4k8dgjvu8mx3");
			} else if (role.equals("2")) {
				mapContext.put("placeOrderRole", "5cak2fx8zu9s");
			}
		}
		List result = new ArrayList();
		List<CompanyEmployeeDto> employeeDtoByDeptId = this.deptMemberService.findEmployeeDtoByDeptIdAndRoleId(mapContext);
		for (CompanyEmployeeDto emploeeDto : employeeDtoByDeptId) {
			String employeeUserId = emploeeDto.getUserId();
			//查询下单数
			mapContext.put("placeOrderId", employeeUserId);
			Integer placeOrderNum = this.customOrderService.findOrderNumByemployeeUserId(mapContext);
			//查询接单数
			mapContext.remove("placeOrderId");
			mapContext.put("receiverId", employeeUserId);
			Integer receiveOrderNum = this.customOrderService.findOrderNumByemployeeUserId(mapContext);
			//查询设计单数和设计金额
			mapContext.remove("receiverId");
			mapContext.put("designerId", employeeUserId);
			MapContext designOrderNum = this.customOrderService.finddesignNumByemployeeUserId(mapContext);
			String designNum = designOrderNum.getTypedValue("designNum", String.class);
			String designAmount = designOrderNum.getTypedValue("designAmount", String.class);
			//查询订单数和金额
			mapContext.remove("designerId");
			mapContext.put("userId", employeeUserId);
			MapContext countOrderNumAndAmount = this.customOrderService.finddesignNumByemployeeUserId(mapContext);
			String orderNum = countOrderNumAndAmount.getTypedValue("designNum", String.class);
			String orderAmount = countOrderNumAndAmount.getTypedValue("designAmount", String.class);
			MapContext map = MapContext.newOne();
			map.put("userId", emploeeDto.getUserId());
			map.put("userName", emploeeDto.getUserName());
			map.put("roleName", emploeeDto.getRoleName());
			map.put("placeOrderNum", placeOrderNum);
			map.put("receiveOrderNum", receiveOrderNum);
			map.put("designNum", designNum);
			map.put("designAmount", designAmount);
			map.put("orderNum", orderNum);
			map.put("orderAmount", orderAmount);
			result.add(map);
		}

		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult countUserAchievements(MapContext mapContext, Integer pageNum, Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		paginatedFilter.setFilters(mapContext);
		PaginatedList<MapContext> contextList = this.customOrderService.countUserAchievements(paginatedFilter);
		return ResultFactory.generateRequestResult(contextList);
	}

	@Override
	public RequestResult newOrderCount(String branchId) {
		MapContext result = MapContext.newOne();
		//两日订单量对比
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date1 = calendar.getTime();//当天
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DATE) - 2);//前二天
		Date date3 = calendar.getTime();
		List<Date> dates = DateUtilsExt.findDates(date3, date1);
		List list = new ArrayList<>();
		Integer first = 0;
		for (Date date : dates) {
			MapContext mapContext = MapContext.newOne();
			mapContext.put("branchId", branchId);
			mapContext.put("startTime", date);
			mapContext.put("endTime", date);
			//前二天订单数
			Integer count = this.customOrderService.findOrderCountByTime(mapContext);
			//已审核订单数
			Integer auditCount = this.customOrderService.findAuditCountByTime(mapContext);
			//未审核订单数
			Integer noAuditCount = this.customOrderService.findnoAuditCountByTime(mapContext);
			String chainRatio = "";
			String chainRatioPercentage = "";
				chainRatio = String.valueOf(count - first);
				if (first == 0) {
					chainRatioPercentage = "100%";
				} else {
					chainRatioPercentage = ((count - first)*100 / first) + "%";
				}
			first = count;
			//前二天外协订单数
			mapContext.put("coordination", 1);
			Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext);
			MapContext mapContext1 = MapContext.newOne();
			mapContext1.put("date", dateFormat.format(date));
			mapContext1.put("orderNum", count);
			mapContext1.put("auditCount", auditCount);
			mapContext1.put("noAuditCount", noAuditCount);
			mapContext1.put("waiOrderNum", waiCount);
			mapContext1.put("chainRatio", chainRatio);
			mapContext1.put("chainRatioPercentage", chainRatioPercentage);
			list.add(mapContext1);
		}
		//当日产品系列明细统计
		Integer deFlag = 0;
		String seriesType = "orderProductSeries";
		//查询产品系列
		List<Basecode> basecodes = this.basecodeService.findByTypeAndDelFlag(seriesType, deFlag);
		List productTypelist=new ArrayList();
		//各产品订单总数及近30天未打款数
		//查询产品分类
		List<Basecode> orderProductType = this.basecodeService.findByTypeAndDelFlag("orderProductType", 0);
		MapContext mapCon = MapContext.newOne();
		mapCon.put("branchId", branchId);
		mapCon.put("startTime", date1);
		mapCon.put("endTime", date1);
		if(orderProductType!=null&&orderProductType.size()>0) {
			for (Basecode type : orderProductType) {
				MapContext productMap=MapContext.newOne();
				productMap.put("name",type.getValue());
				//橱柜
				List list0 = new ArrayList();
				Integer productType=Integer.valueOf(type.getCode());
				//橱柜订单总下单数和近30天未打款数量
				mapCon.put("type", productType);
				MapContext cupboardCount = this.customOrderService.findCupboardOrWardrobeCountByTime(mapCon);
				cupboardCount.put("seriesName", type.getValue()+"总量");
				list0.add(cupboardCount);
				//橱柜系列产品数和下单数和近30天未打款数量
				for (Basecode basecode : basecodes) {
					MapContext mapContext = MapContext.newOne();
					mapContext.put("branchId", branchId);
					mapContext.put("startTime", date1);
					mapContext.put("endTime", date1);
					mapContext.put("type", productType);
					Integer code = Integer.valueOf(basecode.getCode());
					mapContext.put("series", code);
					//今日下单数
					MapContext payNum = this.customOrderService.countOrderSeriesByDay(mapContext);
					//近30天未付款数
					Calendar calendar0 = Calendar.getInstance();
					calendar.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DATE) - 29);//前三十天
					Date start = calendar.getTime();
					mapContext.put("startTime", start);
					mapContext.put("endTime", date1);
					MapContext nopayNum = this.customOrderService.countNopayOrderSeriesByDay(mapContext);
					MapContext map = MapContext.newOne();
					map.put("seriesName", basecode.getValue());
					map.put("payNum", payNum.getTypedValue("count", Integer.class));
					map.put("payMoney", payNum.getTypedValue("productPrice", BigDecimal.class));
					map.put("nopayNum", nopayNum.getTypedValue("count", Integer.class));
					map.put("nopayMoney", nopayNum.getTypedValue("productPrice", BigDecimal.class));
					list0.add(map);
				}
				productMap.put("list",list0);
				productTypelist.add(productMap);
			}
		}
//		//衣柜
//		//衣柜订单总下单数和近30天未打款数量
//		mapCon.put("type", 1);
//		MapContext wardrobeCount = this.customOrderService.findCupboardOrWardrobeCountByTime(mapCon);
//		List list1 = new ArrayList();
//		wardrobeCount.put("seriesName", "衣柜总量");
//		list1.add(wardrobeCount);
//		for (Basecode basecode : basecodes) {
//			MapContext mapContext = MapContext.newOne();
//			mapContext.put("branchId", branchId);
//			mapContext.put("startTime", date1);
//			mapContext.put("endTime", date1);
//			Integer productType = 1;
//			mapContext.put("type", productType);
//			Integer code = Integer.valueOf(basecode.getCode());
//			mapContext.put("series", code);
//			//今日下单数
//			MapContext payNum = this.customOrderService.countOrderSeriesByDay(mapContext);
//			//近30天未付款数
//			Calendar calendar0 = Calendar.getInstance();
//			calendar.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DATE) - 29);//前三十天
//			Date start = calendar.getTime();
//			mapContext.put("startTime", start);
//			mapContext.put("endTime", date1);
//			MapContext nopayNum = this.customOrderService.countNopayOrderSeriesByDay(mapContext);
//			MapContext map = MapContext.newOne();
//			map.put("seriesName", basecode.getValue());
//			map.put("payNum", payNum.getTypedValue("count", Integer.class));
//			map.put("payMoney", payNum.getTypedValue("productPrice", BigDecimal.class));
//			map.put("nopayNum", nopayNum.getTypedValue("count", Integer.class));
//			map.put("nopayMoney", nopayNum.getTypedValue("productPrice", BigDecimal.class));
//			list1.add(map);
//		}
		//当日产品统计
		MapContext params = MapContext.newOne();
		params.put("branchId", branchId);
		params.put("startTime", date1);
		params.put("endTime", date1);
		List<MapContext> products = new ArrayList<>();
		//       int[] typeList=new int[]{0,1,4,5};
		if(orderProductType!=null&&orderProductType.size()>0) {
			for (Basecode basecode : orderProductType) {
				params.put("productType", basecode.getCode());
				MapContext map = this.customOrderService.findOrderCountByProductType(params);
				products.add(map);
			}
		}
		//当日产品门型统计
		List<MapContext> doorCount = this.customOrderService.findOrderCountByDoor(params);
		//当日门板颜色统计
		List<MapContext> doorcolorCount = this.customOrderService.findOrderCountByDoorcolor(params);
		//当日柜体颜色统计
		//List<MapContext> colorCount=this.customOrderService.findOrderCountBycolor(params);
		//数据分析（订单分析）
		List orderAnalysis = new ArrayList();
		params.put("orderType",OrderType.NORMALORDER.getValue());
		//卖的最好的产品
		MapContext theBestProduct = this.customOrderService.findSaleBestProduct(params);
		//卖的最好的橱柜系列
		params.put("type", OrderProductType.CUPBOARD.getValue());
		MapContext theBestCupboardSeries = this.customOrderService.findSaleBestSeries(params);
		//卖的最好的衣柜系列
		params.put("type", OrderProductType.WARDROBE.getValue());
		MapContext theBestWardrobeSeries = this.customOrderService.findSaleBestSeries(params);
		params.remove("type");
		//卖的最好的门板颜色
		MapContext theSaleBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(params);
		//卖的最好的门型
		MapContext theSaleBestDoor = this.customOrderService.findTheSaleBestDoor(params);
		orderAnalysis.add(theBestProduct);
		orderAnalysis.add(theBestCupboardSeries);
		orderAnalysis.add(theBestWardrobeSeries);
		orderAnalysis.add(theSaleBestDoorClolor);
		orderAnalysis.add(theSaleBestDoor);
		//数据分析（售后分析）
		//售后总数
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("startTime", date1);
		mapContext.put("endTime", date1);
		mapContext.put("orderType", OrderType.SUPPLEMENTORDER.getValue());
		Integer afterCountProducts = this.customOrderService.findAftersaleCountByTime(mapContext);
		//产生售后最多的产品
		MapContext theafterMore = this.customOrderService.findSaleBestProduct(mapContext);
		//产生售后最多的橱柜系列
		mapContext.put("type", OrderProductType.CUPBOARD.getValue());
		MapContext theafterCupboardSeries = this.customOrderService.findSaleBestSeries(mapContext);
		//产生售后最多的衣柜系列
		mapContext.put("type", OrderProductType.WARDROBE.getValue());
		MapContext theafterWardrobeSeries = this.customOrderService.findSaleBestSeries(mapContext);
		mapContext.remove("type");
		//产生售后最多的门板颜色
		MapContext theafterBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(mapContext);
		//产生售后最多的门型
		MapContext theafterBestDoor = this.customOrderService.findTheSaleBestDoor(mapContext);
		//产生售后比例最多的产品
		if(afterCountProducts==0){
			afterCountProducts=1;
		}
		MapContext afterProductProportion=MapContext.newOne();
		if(theafterMore!=null) {
			afterProductProportion.put("name", theafterMore.getTypedValue("name", String.class));
			String productProportion = (theafterMore.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterProductProportion.put("count", productProportion);
		}
		//产生售后比例最多的橱柜系列
		MapContext afterCSeriesProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterCupboardSeries!=null) {
			afterCSeriesProportion.put("name", theafterCupboardSeries.getTypedValue("name", String.class));
			String CSeriesProportion = (theafterCupboardSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterCSeriesProportion.put("count", CSeriesProportion);
		}
		//产生售后比例最多的衣柜系列
		MapContext afterBSeriesProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterWardrobeSeries!=null) {
			afterBSeriesProportion.put("name", theafterWardrobeSeries.getTypedValue("name", String.class));
			String BSeriesProportion = (theafterWardrobeSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterBSeriesProportion.put("count", BSeriesProportion);
		}
		//产生售后比例最多的门板颜色
		MapContext afterDoorcolorProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterBestDoorClolor!=null) {
			afterDoorcolorProportion.put("name", theafterBestDoorClolor.getTypedValue("name", String.class));
			String doorcolorProportion = (theafterBestDoorClolor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterDoorcolorProportion.put("count", doorcolorProportion);
		}
		//产生售后比例最多的门型
		MapContext afterdoorProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterBestDoor!=null) {
			afterdoorProportion.put("name", theafterBestDoor.getTypedValue("name", String.class));
			String doorProportion = (theafterBestDoor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterdoorProportion.put("count", doorProportion);
		}
		List aftersaleAnalysis=new ArrayList();
		aftersaleAnalysis.add(theafterMore);
		aftersaleAnalysis.add(theafterCupboardSeries);
		aftersaleAnalysis.add(theafterWardrobeSeries);
		aftersaleAnalysis.add(theafterBestDoorClolor);
		aftersaleAnalysis.add(theafterBestDoor);
		aftersaleAnalysis.add(afterProductProportion);
		aftersaleAnalysis.add(afterCSeriesProportion);
		aftersaleAnalysis.add(afterBSeriesProportion);
		aftersaleAnalysis.add(afterDoorcolorProportion);
		aftersaleAnalysis.add(afterdoorProportion);
		//数据分析（经销商分析）
		List dealereAnalysis=new ArrayList();
		//下单最多的经销商
		mapContext.put("orderType",OrderType.NORMALORDER.getValue());
		MapContext placeOrderMost=this.customOrderService.findPlaceOrderMost(mapContext);
		//下单金额最多的经销商
		MapContext placeOrderMoneyMost=this.customOrderService.findPlaceOrderMoneyMost(mapContext);
		//申请售后最多的经销商
		mapContext.put("orderType",OrderType.SUPPLEMENTORDER.getValue());
		MapContext placeAfterOrderMost=this.customOrderService.findPlaceOrderMost(mapContext);
		dealereAnalysis.add(placeOrderMost);
		dealereAnalysis.add(placeOrderMoneyMost);
		dealereAnalysis.add(placeAfterOrderMost);


		//结果返回
		result.put("contrast", list);
		result.put("productTypelist", productTypelist);
	//	result.put("yiguiSeries", list1);
		result.put("products", products);
		result.put("doorCount", doorCount);
		result.put("doorcolorCount", doorcolorCount);
		result.put("orderAnalysis", orderAnalysis);
		result.put("aftersaleAnalysis", aftersaleAnalysis);
		result.put("dealereAnalysis", dealereAnalysis);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult newOrderCountByMonth(String branchId) {
		MapContext result = MapContext.newOne();
		List<Date> dates = DateUtilsExt.findDates(DateUtilsExt.getFirstDayOfMonth(DateUtil.now()), DateUtil.now());
		//两月订单量对比
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date1 = calendar.getTime();//当月
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 2);//前二月
		Date date3 = calendar.getTime();
		List list = new ArrayList<>();
		try {
			ArrayList<Date> monthBetween = DateUtilsExt.getMonthBetween(date3, date1);
			Integer first = 0;
			for (Date date : monthBetween) {
				Date firstDayOfMonth = DateUtilsExt.getFirstDayOfMonth(date);
				Date lastDayOfMonth = DateUtilsExt.getLastDayOfMonth(date);
				MapContext mapContext = MapContext.newOne();
				mapContext.put("branchId", branchId);
				mapContext.put("startTime", firstDayOfMonth);
				mapContext.put("endTime", lastDayOfMonth);
				//订单数
				Integer count = this.customOrderService.findOrderCountByTime(mapContext);
				//已审核订单数
				Integer auditCount = this.customOrderService.findAuditCountByTime(mapContext);
				//未审核订单数
				Integer noAuditCount = this.customOrderService.findnoAuditCountByTime(mapContext);
				//日均单量
				String averagePerDay="";
				//如果是本月，查出本日的日期
				Calendar calendar1=Calendar.getInstance();
				Integer day=calendar1.get(Calendar.DAY_OF_MONTH);
				Integer month=calendar1.get(Calendar.MONTH)+1;
				Calendar calendar2=Calendar.getInstance();
				calendar2.setTime(date);
				Integer year=calendar2.get(Calendar.YEAR);
				Integer month2=calendar2.get(Calendar.MONTH)+1;
				if(month==month2){
					averagePerDay=String.format("%.2f",(double)count/day);
				}else {
					day=DateUtilsExt.getDaysOfMonth(year+"-"+month2).size();
					averagePerDay=String.format("%.2f",(double)count/day);
				}
				//同比新增数(去年同月份的订单数)
				Calendar calendar3=Calendar.getInstance();
				calendar3.add(Calendar.YEAR,-1);
				Date date2=calendar3.getTime();
				Date firstDayOfMontho = DateUtilsExt.getFirstDayOfMonth(date2);
				Date lastDayOfMontho = DateUtilsExt.getLastDayOfMonth(date2);
				mapContext.put("startTime", firstDayOfMontho);
				mapContext.put("endTime", lastDayOfMontho);
				Integer count0 = this.customOrderService.findOrderCountByTime(mapContext);
                 String yearOnYear="";
                 String yearOnYearPercentage="";
                 //同比新增比
					yearOnYear = String.valueOf(count - count0);
					if (count0 == 0) {
						yearOnYearPercentage ="100%";
					} else {
						yearOnYearPercentage = ((count - count0) *100/ count0)+ "%";
					}

				//环比新增数和新增比
				String chainRatio = "";
				String chainRatioPercentage = "";
					chainRatio = String.valueOf(count - first);
					if (first == 0) {
						chainRatioPercentage = "100%";
					} else {
						chainRatioPercentage = ((count - first) *100/ first) + "%";
					}
				first = count;
				//前二月外协订单数
				mapContext.put("startTime", firstDayOfMonth);
				mapContext.put("endTime", lastDayOfMonth);
				mapContext.put("coordination", 1);
				Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext);
				MapContext mapContext1 = MapContext.newOne();
				mapContext1.put("date", dateFormat.format(date));
				mapContext1.put("orderNum", count);
				mapContext1.put("auditCount", auditCount);
				mapContext1.put("noAuditCount", noAuditCount);
				mapContext1.put("averagePerDay", averagePerDay);
				mapContext1.put("yearOnYear", yearOnYear);
				mapContext1.put("yearOnYearPercentage", yearOnYearPercentage);
				mapContext1.put("chainRatio", chainRatio);
				mapContext1.put("chainRatioPercentage", chainRatioPercentage);
				mapContext1.put("waiOrderNum", waiCount);
				list.add(mapContext1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//本月订单统计（表格数据）
		List newList=new ArrayList();
		Integer first = 0;
		for (Date date : dates) {
			MapContext mapContext = MapContext.newOne();
			mapContext.put("branchId", branchId);
			mapContext.put("startTime", date);
			mapContext.put("endTime", date);
			//订单数
			Integer count = this.customOrderService.findOrderCountByTime(mapContext);
			//已审核订单数
			Integer auditCount = this.customOrderService.findAuditCountByTime(mapContext);
			//未审核订单数
			Integer noAuditCount = this.customOrderService.findnoAuditCountByTime(mapContext);
			String chainRatio = "";
			String chainRatioPercentage = "";
				chainRatio = String.valueOf(count - first);
				if (first == 0) {
					chainRatioPercentage = "100%";
				} else {
					chainRatioPercentage = ((count - first)*100 / first) + "%";
				}
			first = count;
			//外协订单数
			mapContext.put("coordination", 1);
			Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext);
			MapContext mapContext1 = MapContext.newOne();
			mapContext1.put("date", format.format(date));
			mapContext1.put("orderNum", count);
			mapContext1.put("auditCount", auditCount);
			mapContext1.put("noAuditCount", noAuditCount);
			mapContext1.put("waiOrderNum", waiCount);
			mapContext1.put("chainRatio", chainRatio);
			mapContext1.put("chainRatioPercentage", chainRatioPercentage);
			newList.add(mapContext1);
		}
		//当月产品系列明细统计
		Integer deFlag = 0;
		String seriesType = "orderProductSeries";
		//查询产品系列
		List<Basecode> basecodes = this.basecodeService.findByTypeAndDelFlag(seriesType, deFlag);
		List productTypelist=new ArrayList();
		//各产品订单总数及近30天未打款数
		//查询产品分类
		List<Basecode> orderProductType = this.basecodeService.findByTypeAndDelFlag("orderProductType", 0);
		if(orderProductType!=null&&orderProductType.size()>0) {
			for (Basecode type : orderProductType) {
				MapContext productMap=MapContext.newOne();
				productMap.put("name",type.getValue());
				Integer productType =Integer.valueOf(type.getCode());
				//橱柜
				List list0 = new ArrayList();
				//橱柜订单总下单数和近30天未打款数量
				MapContext mapCon = MapContext.newOne();
				mapCon.put("branchId", branchId);
				mapCon.put("startTime", DateUtilsExt.getFirstDayOfMonth(DateUtil.now()));
				mapCon.put("endTime", DateUtil.now());
				mapCon.put("type", productType);
				MapContext cupboardCount = this.customOrderService.findCupboardOrWardrobeCountByTime(mapCon);
				cupboardCount.put("seriesName", type.getValue()+"总量");
				list0.add(cupboardCount);
				//橱柜系列产品数和下单数和近30天未打款数量
				for (Basecode basecode : basecodes) {
					MapContext mapContext = MapContext.newOne();
					mapContext.put("branchId", branchId);
					mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(DateUtil.now()));
					mapContext.put("endTime", DateUtil.now());
					mapContext.put("type", productType);
					Integer code = Integer.valueOf(basecode.getCode());
					mapContext.put("series", code);
					//今日下单数
					MapContext payNum = this.customOrderService.countOrderSeriesByDay(mapContext);
					//近30天未付款数
					Calendar calendar0 = Calendar.getInstance();
					calendar.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DATE) - 29);//前三十天
					Date start = calendar.getTime();
					mapContext.put("startTime", start);
					mapContext.put("endTime", DateUtil.now());
					MapContext nopayNum = this.customOrderService.countNopayOrderSeriesByDay(mapContext);
					MapContext map = MapContext.newOne();
					map.put("seriesName", basecode.getValue());
					map.put("payNum", payNum.getTypedValue("count", Integer.class));
					map.put("payMoney", payNum.getTypedValue("productPrice", BigDecimal.class));
					map.put("nopayNum", nopayNum.getTypedValue("count", Integer.class));
					map.put("nopayMoney", nopayNum.getTypedValue("productPrice", BigDecimal.class));
					list0.add(map);
				}
				productMap.put("list",list0);
				productTypelist.add(productMap);
			}
		}
		//衣柜订单总下单数和近30天未打款数量
//		mapCon.put("type", 1);
//		MapContext wardrobeCount = this.customOrderService.findCupboardOrWardrobeCountByTime(mapCon);
//		//衣柜
//		List list1 = new ArrayList();
//		wardrobeCount.put("seriesName", "衣柜总量");
//		list1.add(wardrobeCount);
//		for (Basecode basecode : basecodes) {
//			MapContext mapContext = MapContext.newOne();
//			mapContext.put("branchId", branchId);
//			mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(DateUtil.now()));
//			mapContext.put("endTime",  DateUtil.now());
//			Integer productType = 1;
//			mapContext.put("type", productType);
//			Integer code = Integer.valueOf(basecode.getCode());
//			mapContext.put("series", code);
//			//今日下单数
//			MapContext payNum = this.customOrderService.countOrderSeriesByDay(mapContext);
//			//近30天未付款数
//			Calendar calendar0 = Calendar.getInstance();
//			calendar.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DATE) - 29);//前三十天
//			Date start = calendar.getTime();
//			mapContext.put("startTime", start);
//			mapContext.put("endTime", DateUtil.now());
//			MapContext nopayNum = this.customOrderService.countNopayOrderSeriesByDay(mapContext);
//			MapContext map = MapContext.newOne();
//			map.put("seriesName", basecode.getValue());
//			map.put("payNum", payNum.getTypedValue("count", Integer.class));
//			map.put("payMoney", payNum.getTypedValue("productPrice", BigDecimal.class));
//			map.put("nopayNum", nopayNum.getTypedValue("count", Integer.class));
//			map.put("nopayMoney", nopayNum.getTypedValue("productPrice", BigDecimal.class));
//			list1.add(map);
//		}
		//当月产品统计
		MapContext params = MapContext.newOne();
		params.put("branchId", branchId);
		params.put("startTime", DateUtilsExt.getFirstDayOfMonth(DateUtil.now()));
		params.put("endTime",  DateUtil.now());
		List<MapContext> products = new ArrayList<>();
		//       int[] typeList=new int[]{0,1,4,5};
		if(orderProductType!=null&&orderProductType.size()>0) {
			for (Basecode basecode : orderProductType) {
				params.put("productType", basecode.getCode());
				MapContext map = this.customOrderService.findOrderCountByProductType(params);
				products.add(map);
			}
		}
		//当月产品明细统计
		List productsBydateList=new ArrayList<>();
		List title=new ArrayList();
		Map map0=new HashMap();
		map0.put("title","日期");
		map0.put("key","date");
		title.add(map0);
		Map map1=new HashMap();
		map1.put("title","总订单数");
		map1.put("key","OrderCount");
		title.add(map1);
		Map map2=new HashMap();
		map2.put("title","外协单");
		map2.put("key","waiCount");
		title.add(map2);
		if(orderProductType!=null&&orderProductType.size()>0){
			for(Basecode basecode:orderProductType) {
				Map newMap=new HashMap();
				newMap.put("title",basecode.getValue());
				newMap.put("key",basecode.getCode());
				title.add(newMap);
			}
		}
		for(Date date:dates){
			MapContext params0 = MapContext.newOne();
			params0.put("branchId", branchId);
			params0.put("startTime", date);
			params0.put("endTime",  date);
			Integer orderCountByTime = this.customOrderService.findOrderCountByTime(params0);
			//外协单数量
			params0.put("coordination", 1);
			Integer waiCount = this.customOrderService.findCoordinationCountByTime(params0);
			MapContext mapCont=MapContext.newOne();
			mapCont.put("date",format.format(date));
			mapCont.put("OrderCount",orderCountByTime);
			mapCont.put("waiCount",waiCount);
			if(orderProductType!=null&&orderProductType.size()>0){
				for(Basecode basecode:orderProductType) {
					params0.put("productType", basecode.getCode());
					MapContext map = this.customOrderService.findOrderCountByProductType(params0);
					mapCont.put(basecode.getCode(),map.getTypedValue("count",Integer.class));
				}
			}
			productsBydateList.add(mapCont);
		}
		params.put("startTime", DateUtilsExt.getFirstDayOfMonth(DateUtil.now()));
		params.put("endTime",  DateUtil.now());
		//当月产品门型统计
		List<MapContext> doorCount = this.customOrderService.findOrderCountByDoor(params);
		//当月门板颜色统计
		List<MapContext> doorcolorCount = this.customOrderService.findOrderCountByDoorcolor(params);
		//当月柜体颜色统计
		//List<MapContext> colorCount=this.customOrderService.findOrderCountBycolor(params);
		//数据分析（订单分析）
		List orderAnalysis = new ArrayList();
		params.put("orderType",OrderType.NORMALORDER.getValue());
		//卖的最好的产品
		MapContext theBestProduct = this.customOrderService.findSaleBestProduct(params);
		//卖的最好的橱柜系列
		params.put("type", OrderProductType.CUPBOARD.getValue());
		MapContext theBestCupboardSeries = this.customOrderService.findSaleBestSeries(params);
		//卖的最好的衣柜系列
		params.put("type", OrderProductType.WARDROBE.getValue());
		MapContext theBestWardrobeSeries = this.customOrderService.findSaleBestSeries(params);
		//卖的最好的门板颜色
		MapContext theSaleBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(params);
		//卖的最好的门型
		MapContext theSaleBestDoor = this.customOrderService.findTheSaleBestDoor(params);
		orderAnalysis.add(theBestProduct);
		orderAnalysis.add(theBestCupboardSeries);
		orderAnalysis.add(theBestWardrobeSeries);
		orderAnalysis.add(theSaleBestDoorClolor);
		orderAnalysis.add(theSaleBestDoor);
		//数据分析（售后分析）
		//售后总数
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(DateUtil.now()));
		mapContext.put("endTime", DateUtil.now());
		mapContext.put("orderType", OrderType.SUPPLEMENTORDER.getValue());
		Integer afterCountProducts = this.customOrderService.findAftersaleCountByTime(mapContext);
		//产生售后最多的产品
		MapContext theafterMore = this.customOrderService.findSaleBestProduct(mapContext);
		//产生售后最多的橱柜系列
		mapContext.put("type", OrderProductType.CUPBOARD.getValue());
		MapContext theafterCupboardSeries = this.customOrderService.findSaleBestSeries(mapContext);
		//产生售后最多的衣柜系列
		mapContext.put("type", OrderProductType.WARDROBE.getValue());
		MapContext theafterWardrobeSeries = this.customOrderService.findSaleBestSeries(mapContext);
		mapContext.remove("type");
		//产生售后最多的门板颜色
		MapContext theafterBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(mapContext);
		//产生售后最多的门型
		MapContext theafterBestDoor = this.customOrderService.findTheSaleBestDoor(mapContext);
		//产生售后比例最多的产品
		if(afterCountProducts==0){
			afterCountProducts=1;
		}
		MapContext afterProductProportion=MapContext.newOne();
		if(theafterMore!=null) {
			afterProductProportion.put("name", theafterMore.getTypedValue("name", String.class));
			String productProportion = (theafterMore.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterProductProportion.put("count", productProportion);
		}
		//产生售后比例最多的橱柜系列
		MapContext afterCSeriesProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterCupboardSeries!=null) {
			afterCSeriesProportion.put("name", theafterCupboardSeries.getTypedValue("name", String.class));
			String CSeriesProportion = (theafterCupboardSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterCSeriesProportion.put("count", CSeriesProportion);
		}
		//产生售后比例最多的衣柜系列
		MapContext afterBSeriesProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterWardrobeSeries!=null) {
			afterBSeriesProportion.put("name", theafterWardrobeSeries.getTypedValue("name", String.class));
			String BSeriesProportion = (theafterWardrobeSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterBSeriesProportion.put("count", BSeriesProportion);
		}
		//产生售后比例最多的门板颜色
		MapContext afterDoorcolorProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterBestDoorClolor!=null) {
			afterDoorcolorProportion.put("name", theafterBestDoorClolor.getTypedValue("name", String.class));
			String doorcolorProportion = (theafterBestDoorClolor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterDoorcolorProportion.put("count", doorcolorProportion);
		}
		//产生售后比例最多的门型
		MapContext afterdoorProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterBestDoor!=null) {
			afterdoorProportion.put("name", theafterBestDoor.getTypedValue("name", String.class));
			String doorProportion = (theafterBestDoor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterdoorProportion.put("count", doorProportion);
		}
		List aftersaleAnalysis=new ArrayList();
		aftersaleAnalysis.add(theafterMore);
		aftersaleAnalysis.add(theafterCupboardSeries);
		aftersaleAnalysis.add(theafterWardrobeSeries);
		aftersaleAnalysis.add(theafterBestDoorClolor);
		aftersaleAnalysis.add(theafterBestDoor);
		aftersaleAnalysis.add(afterProductProportion);
		aftersaleAnalysis.add(afterCSeriesProportion);
		aftersaleAnalysis.add(afterBSeriesProportion);
		aftersaleAnalysis.add(afterDoorcolorProportion);
		aftersaleAnalysis.add(afterdoorProportion);
		//数据分析（经销商分析）
		List dealereAnalysis=new ArrayList();
		//下单最多的经销商
		mapContext.put("orderType",OrderType.NORMALORDER.getValue());
		MapContext placeOrderMost=this.customOrderService.findPlaceOrderMost(mapContext);
		//下单金额最多的经销商
		MapContext placeOrderMoneyMost=this.customOrderService.findPlaceOrderMoneyMost(mapContext);
		//申请售后最多的经销商
		mapContext.put("orderType",OrderType.SUPPLEMENTORDER.getValue());
		MapContext placeAfterOrderMost=this.customOrderService.findPlaceOrderMost(mapContext);
		dealereAnalysis.add(placeOrderMost);
		dealereAnalysis.add(placeOrderMoneyMost);
		dealereAnalysis.add(placeAfterOrderMost);
		result.put("contrast", list);
		result.put("formCount",newList);
		result.put("productTypelist", productTypelist);
		result.put("title", title);
		result.put("productsBydateList", productsBydateList);
		result.put("products", products);
		result.put("doorCount", doorCount);
		result.put("doorcolorCount", doorcolorCount);
		result.put("orderAnalysis", orderAnalysis);
		result.put("aftersaleAnalysis", aftersaleAnalysis);
		result.put("dealereAnalysis",dealereAnalysis);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult newOrderCountByYear(String branchId) {
		MapContext result = MapContext.newOne();
		//获取月份集合
		List<Date> dates = new ArrayList<>();
		try {
			dates=DateUtilsExt.getMonthBetween(DateUtilsExt.getFirstDayOfYear(DateUtil.now()), DateUtil.now());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//两年订单量对比
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Calendar calendar = Calendar.getInstance();
		Date date1 = calendar.getTime();//当年
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 2);//前年
		Date date3 = calendar.getTime();
		List list = new ArrayList<>();
		try {
			List<Date> yearBetween = DateUtilsExt.getYearBetween(date3, date1);
			Integer first = 0;
			for (Date date : yearBetween) {
				Date firstDayOfMonth = DateUtilsExt.getFirstDayOfYear(date);
				Date lastDayOfMonth = DateUtilsExt.getLastDayOfYear(date);
				MapContext mapContext = MapContext.newOne();
				mapContext.put("branchId", branchId);
				mapContext.put("startTime", firstDayOfMonth);
				mapContext.put("endTime", lastDayOfMonth);
				//订单数
				Integer count = this.customOrderService.findOrderCountByTime(mapContext);
//				//已审核订单数
//				Integer auditCount = this.customOrderService.findAuditCountByTime(mapContext);
//				//未审核订单数
//				Integer noAuditCount = this.customOrderService.findnoAuditCountByTime(mapContext);
				//月均量
				Calendar calendar1=Calendar.getInstance();
				calendar1.setTime(date);
				Calendar calendar2=Calendar.getInstance();
				Integer monthNum=1;
				if(calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR)){
					monthNum=calendar2.get(Calendar.MONTH)+1;
				}else {
					monthNum=12;
				}
				String monthlyaverage=String.format("%.2f",(double)count/monthNum);
				String chainRatio = "";
				String chainRatioPercentage = "";
					chainRatio = String.valueOf(count - first);
					if (first == 0) {
						chainRatioPercentage = "100%";
					} else {
						chainRatioPercentage = ((count - first)*100 / first) + "%";
					}
				first = count;
				//外协订单数
				mapContext.put("coordination", 1);
				Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext);
				MapContext mapContext1 = MapContext.newOne();
				mapContext1.put("date", dateFormat.format(date));
				mapContext1.put("orderNum", count);
//				mapContext1.put("auditCount", auditCount);
//				mapContext1.put("noAuditCount", noAuditCount);
				mapContext1.put("monthlyaverage", monthlyaverage);
				mapContext1.put("waiOrderNum", waiCount);
				mapContext1.put("chainRatio", chainRatio);
				mapContext1.put("chainRatioPercentage", chainRatioPercentage);
				list.add(mapContext1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//本年月份订单统计（表格数据）
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		List newList=new ArrayList();
		Integer first = 0;
//		Integer second=0;
		for (Date date : dates) {
			MapContext mapContext = MapContext.newOne();
			mapContext.put("branchId", branchId);
			mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(date));
			mapContext.put("endTime", DateUtilsExt.getLastDayOfMonth(date));
			//订单数
			Integer count = this.customOrderService.findOrderCountByTime(mapContext);
			//已审核订单数
			Integer auditCount = this.customOrderService.findAuditCountByTime(mapContext);
			//未审核订单数
			Integer noAuditCount = this.customOrderService.findnoAuditCountByTime(mapContext);
			//日均单量
			Calendar calendar1=Calendar.getInstance();
			calendar1.setTime(date);
			Calendar calendar2=Calendar.getInstance();
			Integer dayNum=1;
			if(calendar1.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH)){
				dayNum=calendar2.get(Calendar.DAY_OF_MONTH);
			}else {
				SimpleDateFormat dateFormatx=new SimpleDateFormat("yyyy-MM");
				String format1 = dateFormatx.format(date);
				List<Date> daysOfMonth = DateUtilsExt.getDaysOfMonth(format1);
				dayNum=daysOfMonth.size();
			}
			String daylyaverage=String.format("%.2f",(double)count/dayNum);
			//同比新增数(去年同月份的订单数)
			Calendar calendar3=Calendar.getInstance();
			calendar3.add(Calendar.YEAR,-1);
			Date date2=calendar3.getTime();
			Date firstDayOfMonth = DateUtilsExt.getFirstDayOfMonth(date2);
			Date lastDayOfMonth = DateUtilsExt.getLastDayOfMonth(date2);
			mapContext.put("startTime", firstDayOfMonth);
			mapContext.put("endTime", lastDayOfMonth);
			//去年同期订单数
			Integer count0 = this.customOrderService.findOrderCountByTime(mapContext);
			String yearOnYear="";
			String yearOnYearPercentage="";
				yearOnYear = String.valueOf(count - count0);
				if (count0 == 0) {
					yearOnYearPercentage = "100%";
				} else {
					yearOnYearPercentage = ((count - count0)*100 / count0) + "%";
				}
			String chainRatio = "";
			String chainRatioPercentage = "";
				chainRatio = String.valueOf(count - first);
				if (first == 0) {
					chainRatioPercentage = "100%";
				} else {
					chainRatioPercentage = ((count - first)*100 / first) + "%";
				}
			first = count;
			//外协订单数
			mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(date));
			mapContext.put("endTime", DateUtilsExt.getLastDayOfMonth(date));
			mapContext.put("coordination", 1);
			Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext);
			MapContext mapContext1 = MapContext.newOne();
			mapContext1.put("date", format.format(date));
			mapContext1.put("orderNum", count);
			mapContext1.put("auditCount", auditCount);
			mapContext1.put("noAuditCount", noAuditCount);
			mapContext1.put("daylyaverage", daylyaverage);
			mapContext1.put("waiOrderNum", waiCount);
			mapContext1.put("yearOnYear", yearOnYear);
			mapContext1.put("yearOnYearPercentage", yearOnYearPercentage);
			mapContext1.put("chainRatio", chainRatio);
			mapContext1.put("chainRatioPercentage", chainRatioPercentage);
			newList.add(mapContext1);
		}
		//产品系列明细统计
		Integer deFlag = 0;
		String seriesType = "orderProductSeries";
		//查询产品系列
		List<Basecode> basecodes = this.basecodeService.findByTypeAndDelFlag(seriesType, deFlag);
		List productTypelist=new ArrayList();
		//各产品订单总数及近30天未打款数
		//查询产品分类
		List<Basecode> orderProductType = this.basecodeService.findByTypeAndDelFlag("orderProductType", 0);
		if(orderProductType!=null&&orderProductType.size()>0) {
			for (Basecode type : orderProductType) {
				MapContext productMap = MapContext.newOne();
				productMap.put("name", type.getValue());
				Integer productType = Integer.valueOf(type.getCode());
				//橱柜
				List list0 = new ArrayList();
				//橱柜订单总下单数和近30天未打款数量
				MapContext mapCon = MapContext.newOne();
				mapCon.put("branchId", branchId);
				mapCon.put("startTime", DateUtilsExt.getFirstDayOfYear(DateUtil.now()));
				mapCon.put("endTime", DateUtil.now());
				mapCon.put("type", productType);
				MapContext cupboardCount = this.customOrderService.findCupboardOrWardrobeCountByTime(mapCon);
				cupboardCount.put("seriesName", type.getValue()+"总量");
				list0.add(cupboardCount);
				//橱柜系列产品数和下单数和近30天未打款数量
				for (Basecode basecode : basecodes) {
					MapContext mapContext = MapContext.newOne();
					mapContext.put("branchId", branchId);
					mapContext.put("startTime", DateUtilsExt.getFirstDayOfYear(DateUtil.now()));
					mapContext.put("endTime", DateUtil.now());
					mapContext.put("type", productType);
					Integer code = Integer.valueOf(basecode.getCode());
					mapContext.put("series", code);
					//今日下单数
					MapContext payNum = this.customOrderService.countOrderSeriesByDay(mapContext);
					//近30天未付款数
					Calendar calendar0 = Calendar.getInstance();
					calendar.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DATE) - 29);//前三十天
					Date start = calendar.getTime();
					mapContext.put("startTime", start);
					mapContext.put("endTime", DateUtil.now());
					MapContext nopayNum = this.customOrderService.countNopayOrderSeriesByDay(mapContext);
					MapContext map = MapContext.newOne();
					map.put("seriesName", basecode.getValue());
					map.put("payNum", payNum.getTypedValue("count", Integer.class));
					map.put("payMoney", payNum.getTypedValue("productPrice", BigDecimal.class));
					map.put("nopayNum", nopayNum.getTypedValue("count", Integer.class));
					map.put("nopayMoney", nopayNum.getTypedValue("productPrice", BigDecimal.class));
					list0.add(map);
				}
				productMap.put("list",list0);
				productTypelist.add(productMap);
			}
		}
		//年产品统计
		MapContext params = MapContext.newOne();
		params.put("branchId", branchId);
		params.put("startTime", DateUtilsExt.getFirstDayOfYear(DateUtil.now()));
		params.put("endTime",  DateUtil.now());
		List<MapContext> products = new ArrayList<>();
		if(orderProductType!=null&&orderProductType.size()>0) {
			for (Basecode basecode : orderProductType) {
				params.put("productType", basecode.getCode());
				MapContext map = this.customOrderService.findOrderCountByProductType(params);
				products.add(map);
			}
		}
		//年产品月份明细统计
		List productsBydateList=new ArrayList<>();
		List title=new ArrayList();
		Map map0=new HashMap();
		map0.put("title","日期");
		map0.put("key","date");
		title.add(map0);
		Map map1=new HashMap();
		map1.put("title","总订单数");
		map1.put("key","OrderCount");
		title.add(map1);
		Map map2=new HashMap();
		map2.put("title","外协单");
		map2.put("key","waiCount");
		title.add(map2);
		Map map3=new HashMap();
		map3.put("title","售后单");
		map3.put("key","afterCount");
		title.add(map3);
		if(orderProductType!=null&&orderProductType.size()>0){
			for(Basecode basecode:orderProductType) {
				Map newMap=new HashMap();
				newMap.put("title",basecode.getValue());
				newMap.put("key",basecode.getCode());
				title.add(newMap);
			}
		}
		Integer second=0;
		for(Date date:dates){
			MapContext mapContext=MapContext.newOne();
			mapContext.put("branchId", branchId);
			mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(date));
			mapContext.put("endTime",  DateUtilsExt.getLastDayOfMonth(date));
			Integer orderCountByTime = this.customOrderService.findOrderCountByTime(mapContext);
			//外协单数量
			mapContext.put("coordination", 1);
			Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext);
			//售后单数量
			mapContext.remove("coordination");
			mapContext.put("type", 1);
			Integer afterCount = this.customOrderService.findOrderCountByTime(mapContext);
			//同比增长
			Calendar calendar3=Calendar.getInstance();
			calendar3.add(Calendar.YEAR,-1);
			Date date2=calendar3.getTime();
			Date firstDayOfMonth = DateUtilsExt.getFirstDayOfMonth(date2);
			Date lastDayOfMonth = DateUtilsExt.getLastDayOfMonth(date2);
			mapContext.remove("type");
			mapContext.put("startTime", firstDayOfMonth);
			mapContext.put("endTime", lastDayOfMonth);
			//去年同期订单数
			Integer count0 = this.customOrderService.findOrderCountByTime(mapContext);
			String yearOnYearPercentage="";
			if (count0 == 0) {
				yearOnYearPercentage = "100%";
			} else {
				yearOnYearPercentage = (orderCountByTime / count0)*100 + "%";
			}
			//环比增长
			String chainRatioPercentage = "";
			if (second == 0) {
				chainRatioPercentage = "100%";
			} else {
				chainRatioPercentage = (orderCountByTime / second)*100 + "%";
			}
			second = orderCountByTime;
			mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(date));
			mapContext.put("endTime",  DateUtilsExt.getLastDayOfMonth(date));
			MapContext mapCont=MapContext.newOne();
			mapCont.put("date",format.format(date));
			mapCont.put("OrderCount",orderCountByTime);
			mapCont.put("waiCount",waiCount);
			mapCont.put("afterCount",afterCount);
			mapCont.put("yearOnYearPercentage",yearOnYearPercentage);
			mapCont.put("chainRatioPercentage",chainRatioPercentage);
			if(orderProductType!=null&&orderProductType.size()>0){
				for (Basecode basecode:orderProductType){
					mapContext.put("productType",basecode.getCode());
					MapContext map=this.customOrderService.findOrderCountByProductType(mapContext);
					mapCont.put(basecode.getCode(),map.getTypedValue("count",Integer.class));
				}
			}
			productsBydateList.add(mapCont);
		}
		//当月产品门型统计
		List<MapContext> doorCount = this.customOrderService.findOrderCountByDoor(params);
		//当月门板颜色统计
		List<MapContext> doorcolorCount = this.customOrderService.findOrderCountByDoorcolor(params);
		//当月柜体颜色统计
		//List<MapContext> colorCount=this.customOrderService.findOrderCountBycolor(params);
		//数据分析（订单分析）
		List orderAnalysis = new ArrayList();
		params.put("orderType",OrderType.NORMALORDER.getValue());
		//卖的最好的产品
		MapContext theBestProduct = this.customOrderService.findSaleBestProduct(params);
		//卖的最好的橱柜系列
		params.put("type", OrderProductType.CUPBOARD.getValue());
		MapContext theBestCupboardSeries = this.customOrderService.findSaleBestSeries(params);
		//卖的最好的衣柜系列
		params.put("type", OrderProductType.WARDROBE.getValue());
		MapContext theBestWardrobeSeries = this.customOrderService.findSaleBestSeries(params);
		//卖的最好的门板颜色
		MapContext theSaleBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(params);
		//卖的最好的门型
		MapContext theSaleBestDoor = this.customOrderService.findTheSaleBestDoor(params);
		orderAnalysis.add(theBestProduct);
		orderAnalysis.add(theBestCupboardSeries);
		orderAnalysis.add(theBestWardrobeSeries);
		orderAnalysis.add(theSaleBestDoorClolor);
		orderAnalysis.add(theSaleBestDoor);
		//数据分析（售后分析）
		//售后总数
		MapContext mapContextSecond=MapContext.newOne();
		mapContextSecond.put("branchId",branchId);
		mapContextSecond.put("startTime", DateUtilsExt.getFirstDayOfYear(DateUtil.now()));
		mapContextSecond.put("endTime", DateUtil.now());
		mapContextSecond.put("orderType", OrderType.SUPPLEMENTORDER.getValue());
		Integer afterCountProducts = this.customOrderService.findAftersaleCountByTime(mapContextSecond);
		//产生售后最多的产品
		MapContext theafterMore = this.customOrderService.findSaleBestProduct(mapContextSecond);
		//产生售后最多的橱柜系列
		mapContextSecond.put("type", OrderProductType.CUPBOARD.getValue());
		MapContext theafterCupboardSeries = this.customOrderService.findSaleBestSeries(mapContextSecond);
		//产生售后最多的衣柜系列
		mapContextSecond.put("type", OrderProductType.WARDROBE.getValue());
		MapContext theafterWardrobeSeries = this.customOrderService.findSaleBestSeries(mapContextSecond);
		mapContextSecond.remove("type");
		//产生售后最多的门板颜色
		MapContext theafterBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(mapContextSecond);
		//产生售后最多的门型
		MapContext theafterBestDoor = this.customOrderService.findTheSaleBestDoor(mapContextSecond);
		//产生售后比例最多的产品
		if(afterCountProducts==0){
			afterCountProducts=1;
		}
		//产生售后比例最多的产品
		MapContext afterProductProportion=MapContext.newOne();
		if(theafterMore!=null) {
			afterProductProportion.put("name", theafterMore.getTypedValue("name", String.class));
			String productProportion = (theafterMore.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterProductProportion.put("count", productProportion);
		}
		//产生售后比例最多的橱柜系列
		MapContext afterCSeriesProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterCupboardSeries!=null) {
			afterCSeriesProportion.put("name", theafterCupboardSeries.getTypedValue("name", String.class));
			String CSeriesProportion = (theafterCupboardSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterCSeriesProportion.put("count", CSeriesProportion);
		}
		//产生售后比例最多的衣柜系列
		MapContext afterBSeriesProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterWardrobeSeries!=null) {
			afterBSeriesProportion.put("name", theafterWardrobeSeries.getTypedValue("name", String.class));
			String BSeriesProportion = (theafterWardrobeSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterBSeriesProportion.put("count", BSeriesProportion);
		}
		//产生售后比例最多的门板颜色
		MapContext afterDoorcolorProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterBestDoorClolor!=null) {
			afterDoorcolorProportion.put("name", theafterBestDoorClolor.getTypedValue("name", String.class));
			String doorcolorProportion = (theafterBestDoorClolor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterDoorcolorProportion.put("count", doorcolorProportion);
		}
		//产生售后比例最多的门型
		MapContext afterdoorProportion=MapContext.newOne();
		if(theafterMore!=null&&theafterBestDoor!=null) {
			afterdoorProportion.put("name", theafterBestDoor.getTypedValue("name", String.class));
			String doorProportion = (theafterBestDoor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
			afterdoorProportion.put("count", doorProportion);
		}
		List aftersaleAnalysis=new ArrayList();
		aftersaleAnalysis.add(theafterMore);
		aftersaleAnalysis.add(theafterCupboardSeries);
		aftersaleAnalysis.add(theafterWardrobeSeries);
		aftersaleAnalysis.add(theafterBestDoorClolor);
		aftersaleAnalysis.add(theafterBestDoor);
		aftersaleAnalysis.add(afterProductProportion);
		aftersaleAnalysis.add(afterCSeriesProportion);
		aftersaleAnalysis.add(afterBSeriesProportion);
		aftersaleAnalysis.add(afterDoorcolorProportion);
		aftersaleAnalysis.add(afterdoorProportion);
		//数据分析（经销商分析）
		List dealereAnalysis=new ArrayList();
		//下单最多的经销商
		mapContextSecond.put("orderType",OrderType.NORMALORDER.getValue());
		MapContext placeOrderMost=this.customOrderService.findPlaceOrderMost(mapContextSecond);
		//下单金额最多的经销商
		MapContext placeOrderMoneyMost=this.customOrderService.findPlaceOrderMoneyMost(mapContextSecond);
		//申请售后最多的经销商
		mapContextSecond.put("orderType",OrderType.SUPPLEMENTORDER.getValue());
		MapContext placeAfterOrderMost=this.customOrderService.findPlaceOrderMost(mapContextSecond);


		dealereAnalysis.add(placeOrderMost);
		dealereAnalysis.add(placeOrderMoneyMost);
		dealereAnalysis.add(placeAfterOrderMost);
		result.put("contrast", list);
		result.put("formCount",newList);
		result.put("productTypelist", productTypelist);
		result.put("title", title);
		result.put("productsBydateList", productsBydateList);
		result.put("products", products);
		result.put("doorCount", doorCount);
		result.put("doorcolorCount", doorcolorCount);
		result.put("orderAnalysis", orderAnalysis);
		result.put("aftersaleAnalysis", aftersaleAnalysis);
		result.put("dealereAnalysis", dealereAnalysis);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult homeOrderCount(String branchId) {
		MapContext mapContext = MapContext.newOne();
		mapContext.put("branchId", branchId);
		MapContext result = this.customOrderStatementService.homeOrderCount(mapContext);
		return ResultFactory.generateRequestResult(result);
	}

    @Override
    public RequestResult myOrderCount(String branchId, String companyId, String userId) {
		// 判断用户的角色
        Role role = roleService.findRoleByCidAndUid(userId, companyId);
        if (null == role) {
            return ResultFactory.generateRequestResult(null);
        }
        UserRole userRole = UserRole.getByValue(role.getKey());
        if (null == userRole) {
            return ResultFactory.generateRequestResult(null);
        }

        MapContext mapContext = MapContext.newOne();
        mapContext.put("branchId", branchId);
        mapContext.put("userId", userId);
        List list = new ArrayList<>();
        MapContext result = MapContext.newOne();
        switch (userRole) {
            case PLACE_ORDER_ROLE: // 拆单人员/下单人
                result = this.customOrderStatementService.placeOrderCount(mapContext);
                MapContext myOrderCountMap = MapContext.newOne();
                myOrderCountMap.put("name", "我的订单");
                myOrderCountMap.put("value", result.get("myOrderCount"));
                list.add(myOrderCountMap);
                MapContext toQuotedMap = MapContext.newOne();
                toQuotedMap.put("name", "我的待报价订单");
                toQuotedMap.put("value", result.get("toQuoted"));
                list.add(toQuotedMap);
                MapContext myOrderMoneyMap = MapContext.newOne();
                myOrderMoneyMap.put("name", "我的订单总金额");
                myOrderMoneyMap.put("value", String.format("%,.2f", result.get("myOrderMoney")));
                list.add(myOrderMoneyMap);
                MapContext toProducedMap = MapContext.newOne();
                toProducedMap.put("name", "我的待生产订单");
                toProducedMap.put("value", result.get("toProduced"));
                list.add(toProducedMap);
                break;
            case MERCHANDISER_ROLE: // 跟单员
                result = this.customOrderStatementService.merchandiserOrderCount(mapContext);
                MapContext myOrderCountMapContext = MapContext.newOne();
                myOrderCountMapContext.put("name", "我的订单");
                myOrderCountMapContext.put("value", result.get("myOrderCount"));
                list.add(myOrderCountMapContext);
                MapContext toQuotedMapContext = MapContext.newOne();
                toQuotedMapContext.put("name", "我的待报价订单");
                toQuotedMapContext.put("value", result.get("toQuoted"));
                list.add(toQuotedMapContext);
                MapContext myOrderMoneyMapContext = MapContext.newOne();
                myOrderMoneyMapContext.put("name", "我的订单总金额");
                myOrderMoneyMapContext.put("value", String.format("%,.2f", result.get("myOrderMoney")));
                list.add(myOrderMoneyMapContext);
                MapContext toProducedMapContext = MapContext.newOne();
                toProducedMapContext.put("name", "我的待生产订单");
                toProducedMapContext.put("value", result.get("toProduced"));
                list.add(toProducedMapContext);
                break;
            case ORDER_LEADER_ROLE:
                mapContext.put("type", OrderProductType.WARDROBE.getValue()); // 衣柜
                result = this.customOrderStatementService.findOrderProductTypeCount(mapContext);
                MapContext wardrobeMap = MapContext.newOne();
                wardrobeMap.put("name", "衣柜单量");
                wardrobeMap.put("value", result.get("count"));
                list.add(wardrobeMap);
                mapContext.put("type", OrderProductType.CUPBOARD.getValue()); // 橱柜
                result = this.customOrderStatementService.findOrderProductTypeCount(mapContext);
                MapContext cupboardMap = MapContext.newOne();
                cupboardMap.put("name", "橱柜单量");
                cupboardMap.put("value", result.get("count"));
                list.add(cupboardMap);
                mapContext.put("type", OrderProductType.HARDWARE.getValue()); // 五金
                result = this.customOrderStatementService.findOrderProductTypeCount(mapContext);
                MapContext hardwareMap = MapContext.newOne();
                hardwareMap.put("name", "五金单量");
                hardwareMap.put("value", result.get("count"));
                list.add(hardwareMap);
                mapContext.put("type", OrderProductType.SAMPLE_PIECE.getValue()); // 样块
                result = this.customOrderStatementService.findOrderProductTypeCount(mapContext);
                MapContext samplePieceMap = MapContext.newOne();
                samplePieceMap.put("name", "样块单量");
                samplePieceMap.put("value", result.get("count"));
                list.add(samplePieceMap);
                mapContext.remove("type");
                mapContext.put("isCoordination", 1); // 外协
                result = this.customOrderStatementService.findOrderProductTypeCount(mapContext);
                MapContext coordinationMap = MapContext.newOne();
                coordinationMap.put("name", "外协单量");
                coordinationMap.put("value", result.get("count"));
                list.add(coordinationMap);
                break;
            case FINANCE_LEADER_ROLE:
                mapContext.put("status", PaymentStatus.PENDING_PAYMENT.getValue()); // 代付款
                mapContext.put("dateType", 0);
                MapContext threeBeAuditedMap = MapContext.newOne();
                threeBeAuditedMap.put("name", "近3天待审核");
                threeBeAuditedMap.put("value", this.paymentService.findCountByStatus(mapContext));
                list.add(threeBeAuditedMap);
                mapContext.put("dateType", 1);
                MapContext sevenBeAuditedMap = MapContext.newOne();
                sevenBeAuditedMap.put("name", "4-7天待审核");
                sevenBeAuditedMap.put("value", this.paymentService.findCountByStatus(mapContext));
                list.add(sevenBeAuditedMap);
                mapContext.put("dateType", 2);
                MapContext thirtyBeAuditedMap = MapContext.newOne();
                thirtyBeAuditedMap.put("name", "8-30天待审核");
                thirtyBeAuditedMap.put("value", this.paymentService.findCountByStatus(mapContext));
                list.add(thirtyBeAuditedMap);
                mapContext.put("dateType", 3);
                MapContext afterThirtyBeAuditedMap = MapContext.newOne();
                afterThirtyBeAuditedMap.put("name", "超过30天待审核");
                afterThirtyBeAuditedMap.put("value", this.paymentService.findCountByStatus(mapContext));
                list.add(afterThirtyBeAuditedMap);
                break;
            case FINANCE_STAFF_ROLE:
                mapContext.put("status", PaymentStatus.PENDING_PAYMENT.getValue()); // 代付款
                mapContext.put("dateType", 0);
                MapContext threeBeAuditedMapContext = MapContext.newOne();
                threeBeAuditedMapContext.put("name", "近3天待审核");
                threeBeAuditedMapContext.put("value", this.paymentService.findCountByStatus(mapContext));
                list.add(threeBeAuditedMapContext);
                mapContext.put("dateType", 1);
                MapContext sevenBeAuditedMapContext = MapContext.newOne();
                sevenBeAuditedMapContext.put("name", "4-7天待审核");
                sevenBeAuditedMapContext.put("value", this.paymentService.findCountByStatus(mapContext));
                list.add(sevenBeAuditedMapContext);
                mapContext.put("dateType", 2);
                MapContext thirtyBeAuditedMapContext = MapContext.newOne();
                thirtyBeAuditedMapContext.put("name", "8-30天待审核");
                thirtyBeAuditedMapContext.put("value", this.paymentService.findCountByStatus(mapContext));
                list.add(thirtyBeAuditedMapContext);
                mapContext.put("dateType", 3);
                MapContext afterThirtyBeAuditedMapContext = MapContext.newOne();
                afterThirtyBeAuditedMapContext.put("name", "超过30天待审核");
                afterThirtyBeAuditedMapContext.put("value", this.paymentService.findCountByStatus(mapContext));
                list.add(afterThirtyBeAuditedMapContext);
                break;
            case STOREKEEPER_ROLE:
				result = this.customOrderStatementService.findOrderProductStockCount(mapContext);
				MapContext printCountMap = MapContext.newOne();
				printCountMap.put("name", "可打印包裹产品");
				printCountMap.put("value", result.get("printCount"));
				list.add(printCountMap);
                MapContext cabinetCountMap = MapContext.newOne();
                cabinetCountMap.put("name", "未入库柜体数量");
                cabinetCountMap.put("value", result.get("cabinetCount"));
                list.add(cabinetCountMap);
                MapContext doorCountMap = MapContext.newOne();
                doorCountMap.put("name", "未入库门板数量");
                doorCountMap.put("value", result.get("doorCount"));
                list.add(doorCountMap);
                MapContext hardwareCountMap = MapContext.newOne();
                hardwareCountMap.put("name", "未入库五金数量");
                hardwareCountMap.put("value", result.get("hardwareCount"));
                list.add(hardwareCountMap);
                break;
            case AFTERSALE_LEADER_ROLE:
                result = this.customOrderStatementService.findAfterOrderCount(mapContext); // 售后单待生产，待付款，延期
                //待付款售后单数
                MapContext afterSaleToPayMap = MapContext.newOne();
                afterSaleToPayMap.put("name", "待审核售后单数量");
                afterSaleToPayMap.put("value", result.get("toPayCount"));
                list.add(afterSaleToPayMap);
                //待生产售后单数
                MapContext afterSaleToProducedMap = MapContext.newOne();
                afterSaleToProducedMap.put("name", "待生产售后单数量");
                afterSaleToProducedMap.put("value", result.get("toProduceCount"));
                list.add(afterSaleToProducedMap);
                //延期售后单数
                MapContext afterSaleDelayMap = MapContext.newOne();
                afterSaleDelayMap.put("name", "延期售后单数量");
                afterSaleDelayMap.put("value", result.get("delayCount"));
                list.add(afterSaleDelayMap);
                //今天产生售后单数量
                mapContext.put("date", com.lwxf.commons.utils.DateUtil.getSystemDateTimeString()); // 今天时间
                result = this.customOrderStatementService.selectAfterCount(mapContext);
                MapContext afterSaleDayCountMap = MapContext.newOne();
                afterSaleDayCountMap.put("name", "今天产生售后单数量");
                afterSaleDayCountMap.put("value", result.get("afterCount"));
                list.add(afterSaleDayCountMap);
                break;
            case SHIPMENTS_LEADER_ROLE:
                mapContext.put("status", 4); // 产品待发货状态
                MapContext waitCountMap = MapContext.newOne();
                waitCountMap.put("name", "待发货产品数量");
                waitCountMap.put("value", this.orderProductService.countProductsByStatus(mapContext));
                list.add(waitCountMap);
                mapContext.remove("status");
                result = this.customOrderStatementService.dispatchOrderCountByDay(mapContext); // 今日完成产品和未完成计划名称
                MapContext todayCountMap = MapContext.newOne();
                todayCountMap.put("name", "今日发货完成");
                todayCountMap.put("value", result.get("completedToday"));
                list.add(todayCountMap);
                MapContext noCompletedPlanMap = MapContext.newOne();
                noCompletedPlanMap.put("name", "未完成发货计划");
                noCompletedPlanMap.put("value", result.get("noCompletedPlan") == null ? "" : result.get("noCompletedPlan"));
                list.add(noCompletedPlanMap);
                break;
            case SHIPMENTS_STAFF_ROLE:
                mapContext.put("status", 4); // 产品待发货状态
                MapContext waitCountMapContext = MapContext.newOne();
                waitCountMapContext.put("name", "待发货产品数量");
                waitCountMapContext.put("value", this.orderProductService.countProductsByStatus(mapContext));
                list.add(waitCountMapContext);
                mapContext.remove("status");
                result = this.customOrderStatementService.dispatchOrderCountByDay(mapContext); // 今日完成产品和未完成计划名称
                MapContext todayCountMapContext = MapContext.newOne();
                todayCountMapContext.put("name", "今日发货完成");
                todayCountMapContext.put("value", result.get("completedToday"));
                list.add(todayCountMapContext);
                MapContext noCompletedPlanMapContext = MapContext.newOne();
                noCompletedPlanMapContext.put("name", "未完成发货计划");
                noCompletedPlanMapContext.put("value", result.get("noCompletedPlan") == null ? "" : result.get("noCompletedPlan"));
                list.add(noCompletedPlanMapContext);
                break;
            default:

        }



        return ResultFactory.generateRequestResult(list);
    }


}
