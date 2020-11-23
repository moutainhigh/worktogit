package com.lwxf.industry4.webapp.controller.admin.factory.statement;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.order.OrderType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportRankDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportSummaryDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.DealerStatementFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/30 0030 9:28
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="dealerStatementController",tags={"F端后台管理接口:经销商报表接口"})
@RestController("DealerStatementController")
@RequestMapping(value = "/api/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DealerStatementController {
	@Resource(name = "dealerStatementFacade")
	private DealerStatementFacade dealerStatementFacade;

	/**
	 * 业务报表
	 *
	 * @param startTime
	 * @param endTime
	 * @param cityId
	 * @param salesmanId
	 * @return
	 */
	@ApiOperation(value = "业务报表条件搜索接口", notes = "业务报表条件搜索接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "cityId", value = "区域id", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "salesmanId", value = "业务经理id", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "countType", value = "要查看的类型，在网统计传“0”，签约统计传“1”，退网-2，意向-3", dataType = "string", paramType = "path", required = true)
	})
	@GetMapping("/dealerStatements/{countType}")
	private String dealerStatements(@RequestParam(required = false) String startTime,
									@RequestParam(required = false) String endTime,
									@RequestParam(required = false) String cityId,
									@RequestParam(required = false) Integer dateType,
									@RequestParam(required = false) String salesmanId,
									@PathVariable String countType) {
		JsonMapper jsonMapper = new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId = WebUtils.getCurrBranchId();
		if (uid == null) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		MapContext mapContext = MapContext.newOne();
		if (startTime != null && !startTime.equals("")) {
			mapContext.put("startTime", startTime);
		}
		if (endTime != null && !endTime.equals("")) {
			mapContext.put("endTime", endTime);
		}
		if (cityId != null && !cityId.equals("")) {
			mapContext.put("cityId", cityId);
		}
		if (salesmanId != null && !salesmanId.equals("")) {
			mapContext.put("salesmanId", salesmanId);
		}
		if (countType != null && !countType.equals("")) {
			mapContext.put("countType", countType);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RequestResult result = null;
		try {
			result = this.dealerStatementFacade.dealerStatements(sdf.parse(startTime), sdf.parse(endTime), mapContext, dateType);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonMapper.toJson(result);
	}

	@ApiOperation(value = "本周业务报表", notes = "本周业务报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType", value = "要查看的类型，在网统计传“0”，签约统计传“1”，退网-2，意向-3", dataType = "string", paramType = "path", required = true)
	})
	@GetMapping("/dealerStatements/week/{countType}")
	private String weekDealerStatements(@PathVariable String countType) {
		JsonMapper jsonMapper = new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId = WebUtils.getCurrBranchId();
		if (uid == null) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}

		RequestResult result = this.dealerStatementFacade.weekDealerStatements(branchId, countType);
		return jsonMapper.toJson(result);
	}

	@ApiOperation(value = "本月业务报表接口", notes = "本月业务报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType", value = "要查看的类型，在网统计传“0”，签约统计传“1”，退网-2，意向-3", dataType = "string", paramType = "path", required = true)
	})
	@GetMapping("/dealerStatements/month/{countType}")
	private String monthDealerStatements(@PathVariable String countType) {
		JsonMapper jsonMapper = new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId = WebUtils.getCurrBranchId();
		if (uid == null) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result = this.dealerStatementFacade.monthDealerStatements(branchId, countType);
		return jsonMapper.toJson(result);
	}

	@ApiOperation(value = "本季业务报表接口", notes = "本季业务报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType", value = "要查看的类型，在网统计传“0”，签约统计传“1”，退网-2，意向-3", dataType = "string", paramType = "path", required = true)
	})
	@GetMapping("/dealerStatements/quarter/{countType}")
	private String quarterDealerStatements(@PathVariable String countType) {
		JsonMapper jsonMapper = new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId = WebUtils.getCurrBranchId();
		if (uid == null) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result = this.dealerStatementFacade.quarterDealerStatements(branchId, countType);
		return jsonMapper.toJson(result);
	}

	@ApiOperation(value = "本年业务报表接口", notes = "本年业务报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType", value = "要查看的类型，在网统计传“0”，签约统计传“1”，退网-2，意向-3", dataType = "string", paramType = "path", required = true)
	})
	@GetMapping("/dealerStatements/year/{countType}")
	private String yearDealerStatements(@PathVariable String countType) {
		JsonMapper jsonMapper = new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId = WebUtils.getCurrBranchId();
		if (uid == null) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result = this.dealerStatementFacade.yearDealerStatements(branchId, countType);
		return jsonMapper.toJson(result);
	}

	@ApiOperation(value = "业务经理接口", notes = "业务经理接口")
	@GetMapping("/dealerStatements/salemans")
	private String getSalemans() {
		JsonMapper jsonMapper = new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId = WebUtils.getCurrBranchId();
		if (uid == null) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result = this.dealerStatementFacade.getSalemans(branchId);
		return jsonMapper.toJson(result);
	}

	@ApiOperation(value = "经销商销量排名", notes = "经销商销量排名")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "companyName", value = "经销商名称", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "address", value = "经销商地址", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "order", value = "数量倒叙-0 数量正序-1 金额倒叙-2 金额正序-3", dataType = "string", paramType = "query")
	})
	@GetMapping("/dealerStatements/companyOrderRankingList")
	private String companyOrderRankingList(@RequestParam(required = false) String beginTime,
										   @RequestParam(required = false) String endTime,
										   @RequestParam(required = false) String companyName,
										   @RequestParam(required = false) String address,
										   @RequestParam(required = false) String order
										   ) {
		JsonMapper jsonMapper = new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId = WebUtils.getCurrBranchId();
		if (uid == null) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		MapContext map = MapContext.newOne();
		map.put("branchId",branchId);
		if (beginTime != null && !beginTime.equals("")) {
			map.put("beginTime", beginTime);
		}
		if (endTime != null && !endTime.equals("")) {
			map.put("endTime", endTime);
		}
		if (companyName != null && !companyName.equals("")) {
			map.put("companyName", companyName);
		}
		if (address != null && !address.equals("")) {
			map.put("address", address);
		}
		if (order != null && !order.equals("")) {
			map.put("order", order);
		}else {
			map.put("order",0);
		}
		RequestResult result = this.dealerStatementFacade.companyOrderRankingList(map);
		return jsonMapper.toJson(result);
	}

	@ApiOperation(value = "经销商详情数据统计", notes = "经销商详情数据统计")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dealerId", value = "经销商id", dataType = "string", paramType = "query", required = true),
			@ApiImplicitParam(name = "yearTime", value = "年", dataType = "string", paramType = "query", required = false)

	})
	@GetMapping("/dealerStatements/infoCount")
	private String companyOrderRankingList(@RequestParam String dealerId,
										   @RequestParam(required = false) String yearTime) {
		JsonMapper jsonMapper = new JsonMapper();
		RequestResult result = this.dealerStatementFacade.companyOrderInfoCount(dealerId,yearTime);
		return jsonMapper.toJson(result);
	}

	/**
	 * 经销商数量汇总统计 包括经销商总数，意向经销商总数，签约经销商总数，月增量和年增量
	 *
	 * @param
	 * @return
	 */
	@GetMapping("/dealerStatements/summary")
	@ApiOperation(value = "经销商数量汇总统计",notes = "经销商数量汇总统计", response = DealerReportSummaryDto.class)
	public RequestResult findDealerReportSummary() {
		String branchId = WebUtils.getCurrBranchId();
		return this.dealerStatementFacade.findDealerReportSummary(branchId);
	}

    /**
     * 查询优质经销商排名 按经销商的订单金额进行排序
     * @param pageNum
     * @param pageSize
     * @param mode 查询类型 month-月，year-年，custom-自定义(提供开始和结束时间)
     * @param beginTime
     * @param endTime
     * @return
     */
	@GetMapping("/dealerStatements/excellent")
	@ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", dataType = "integer", paramType = "query"),
			@ApiImplicitParam(name = "mode", required = true, value = "查询类型 month-月，year-年，custom-自定义(提供开始和结束时间)", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "string", paramType = "query")
	})
	@ApiOperation(value = "优质经销商排行",notes = "优质经销商排行", response = DealerReportRankDto.class)
	public RequestResult findExcellentDealerRank(@RequestParam(required = false) Integer pageNum,
                                                  @RequestParam(required = false) Integer pageSize,
                                                  @RequestParam(required = true) String mode,
												  @RequestParam(required = false, defaultValue = "") String beginTime,
												  @RequestParam(required = false, defaultValue = "") String endTime) {
	    if (mode == null || "".equals(mode)) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_NOTNULL, "mode" + AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        MapContext mapContext = MapContext.newOne();
        String branchId = WebUtils.getCurrBranchId();
        mapContext.put("branchId", branchId); // 企业ID
        mapContext.put("beginTime", beginTime); // 开始时间
        mapContext.put("endTime", endTime); // 结束时间
        this.createTime(mode, mapContext); // 计算开始和结束时间
        mapContext.put("type", OrderType.NORMALORDER.getValue()); // 正常订单
        mapContext.put("order", "orderMoneyTotal"); // 按订单金额查询
		return this.dealerStatementFacade.findDealerRank(pageNum, pageSize, mapContext);
	}

    /**
     * 查询经销商下单排名 按经销商的订单量进行排序
     * @param pageNum
     * @param pageSize
     * @param mode 查询类型 month-月，year-年，custom-自定义(提供开始和结束时间)
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/dealerStatements/orderNum")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "mode", required = true, value = "查询类型 month-月，year-年，custom-自定义(提供开始和结束时间)", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "string", paramType = "query")
    })
    @ApiOperation(value = "经销商下单量排行",notes = "经销商下单量排行", response = DealerReportRankDto.class)
    public RequestResult findOrderNumDealerRank(@RequestParam(required = false) Integer pageNum,
                                                  @RequestParam(required = false) Integer pageSize,
                                                  @RequestParam(required = true) String mode,
                                                  @RequestParam(required = false, defaultValue = "") String beginTime,
                                                  @RequestParam(required = false, defaultValue = "") String endTime) {
        if (mode == null || "".equals(mode)) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_NOTNULL, "mode" + AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        MapContext mapContext = MapContext.newOne();
        String branchId = WebUtils.getCurrBranchId();
        mapContext.put("branchId", branchId); // 企业ID
        mapContext.put("beginTime", beginTime); // 开始时间
        mapContext.put("endTime", endTime); // 结束时间
        this.createTime(mode, mapContext); // 计算开始和结束时间
        mapContext.put("type", OrderType.NORMALORDER.getValue()); // 正常订单
        mapContext.put("order", "orderTotal"); // 按订单数量查询
        return this.dealerStatementFacade.findDealerRank(pageNum, pageSize, mapContext);
    }

    /**
     * 查询经销商收费补单排名 按经销商的订单金额进行排序
     * @param pageNum
     * @param pageSize
     * @param mode 查询类型 month-月，year-年，custom-自定义(提供开始和结束时间)
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/dealerStatements/chargeSupplementOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "mode", required = true, value = "查询类型 month-月，year-年，custom-自定义(提供开始和结束时间)", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "string", paramType = "query")
    })
    @ApiOperation(value = "经销商收费补单排行",notes = "经销商收费补单排行", response = DealerReportRankDto.class)
    public RequestResult findChargeSupplementOrderDealerRank(@RequestParam(required = false) Integer pageNum,
                                                 @RequestParam(required = false) Integer pageSize,
                                                 @RequestParam(required = true) String mode,
                                                 @RequestParam(required = false, defaultValue = "") String beginTime,
                                                 @RequestParam(required = false, defaultValue = "") String endTime) {
        if (mode == null || "".equals(mode)) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_NOTNULL, "mode" + AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        MapContext mapContext = MapContext.newOne();
        String branchId = WebUtils.getCurrBranchId();
        mapContext.put("branchId", branchId); // 企业ID
        mapContext.put("beginTime", beginTime); // 开始时间
        mapContext.put("endTime", endTime); // 结束时间
        this.createTime(mode, mapContext); // 计算开始和结束时间
        mapContext.put("type", OrderType.SUPPLEMENTORDER.getValue()); // 补单
        mapContext.put("order", "orderMoneyTotal"); // 按订单数量查询
        mapContext.put("isCharge", WebConstant.IS_CHARGE); // 收费
        return this.dealerStatementFacade.findDealerRank(pageNum, pageSize, mapContext);
    }

    /**
     * 查询经销商不收费补单排名 按经销商的订单金额进行排序
     * @param pageNum
     * @param pageSize
     * @param mode 查询类型 month-月，year-年，custom-自定义(提供开始和结束时间)
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/dealerStatements/noChargeSupplementOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "mode", required = true, value = "查询类型 month-月，year-年，custom-自定义(提供开始和结束时间)", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "string", paramType = "query")
    })
    @ApiOperation(value = "经销商不收费补单排行",notes = "经销商不收费补单排行", response = DealerReportRankDto.class)
    public RequestResult findNoChargeSupplementOrderDealerRank(@RequestParam(required = false) Integer pageNum,
                                                              @RequestParam(required = false) Integer pageSize,
                                                              @RequestParam(required = true) String mode,
                                                              @RequestParam(required = false, defaultValue = "") String beginTime,
                                                              @RequestParam(required = false, defaultValue = "") String endTime) {
        if (mode == null || "".equals(mode)) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_NOTNULL, "mode" + AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        MapContext mapContext = MapContext.newOne();
        String branchId = WebUtils.getCurrBranchId();
        mapContext.put("branchId", branchId); // 企业ID
        mapContext.put("beginTime", beginTime); // 开始时间
        mapContext.put("endTime", endTime); // 结束时间
        this.createTime(mode, mapContext); // 计算开始和结束时间
        mapContext.put("type", OrderType.SUPPLEMENTORDER.getValue()); // 补单
        mapContext.put("order", "orderMoneyTotal"); // 按订单数量查询
        mapContext.put("isCharge", WebConstant.NO_CHARGE); // 不收费
        return this.dealerStatementFacade.findDealerRank(pageNum, pageSize, mapContext);
    }

    /**
     * 生成查询时间条件
     * @param mode
     * @param mapContext
     */
	private void createTime(String mode, MapContext mapContext) {
        // 今年1月1日
        String firstDateOfYear = DateUtil.dateTimeToString(DateUtilsExt.getBeginDayOfYear(), DateUtil.FORMAT_DATE);
        // 现在年月日
        String nowDateOfYear = DateUtil.dateTimeToString(DateUtil.getSystemDate(), DateUtil.FORMAT_DATE);
        // 当月1日
        String firstDayOfMonth = DateUtil.dateTimeToString(DateUtilsExt.getBeginDayOfMonth(), DateUtil.FORMAT_DATE);
        switch (mode) {
            case "month":
                mapContext.put("beginTime", firstDayOfMonth);
                mapContext.put("endTime", nowDateOfYear);
                break;
            case "year":
                mapContext.put("beginTime", firstDateOfYear);
                mapContext.put("endTime", nowDateOfYear);
                break;
            case "custom":
                break;
            default: // 默认按年的时间区间插叙
                mapContext.put("beginTime", firstDateOfYear);
                mapContext.put("endTime", nowDateOfYear);
        }
    }
}