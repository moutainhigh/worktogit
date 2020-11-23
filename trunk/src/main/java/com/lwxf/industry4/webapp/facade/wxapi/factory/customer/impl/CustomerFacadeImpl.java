package com.lwxf.industry4.webapp.facade.wxapi.factory.customer.impl;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.ValidateUtils;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.customer.CompanyCustomerService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductService;
import com.lwxf.industry4.webapp.bizservice.user.UserBasisService;
import com.lwxf.industry4.webapp.common.authcode.AuthCodeUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.dto.UserInfoObj;
import com.lwxf.industry4.webapp.common.enums.company.*;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductType;
import com.lwxf.industry4.webapp.common.enums.user.*;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.UserExtraUtil;
import com.lwxf.industry4.webapp.domain.dto.customer.WxCustomerDto;
import com.lwxf.industry4.webapp.domain.dto.customer.WxCustomerInfoDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.WxCustomOrderDto;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.factory.customer.CustomerFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/6 0006 15:43
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "wxCustomerFacade")
public class CustomerFacadeImpl extends BaseFacadeImpl implements CustomerFacade {
	@Resource(name = "companyCustomerService")
	private CompanyCustomerService companyCustomerService;
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "orderProductService")
	private OrderProductService orderProductService;
	@Resource(name = "userBasisService")
	private UserBasisService userBasisService;
	@Resource(name = "cityAreaService")
	private CityAreaService cityAreaService;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;
	@Resource(name = "companyService")
	private CompanyService companyService;

	/**
	 * 查询客户列表
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param mapContext
	 * @return
	 */
	@Override
	public RequestResult findWxCustomers(Integer pageNum, Integer pageSize, MapContext mapContext) {
		//分页查询处理
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		paginatedFilter.setPagination(pagination);
		paginatedFilter.setFilters(mapContext);
		Map dataSort = new HashMap();
		List sorts = new ArrayList();
		dataSort.put("created", "desc");
		sorts.add(dataSort);
		paginatedFilter.setSorts(sorts);
		PaginatedList<WxCustomerDto> paginatedList = this.companyCustomerService.findWxCustomers(paginatedFilter);
		//处理地区名称的显示,把','换成'-'
		for (WxCustomerDto wxCustomerDto : paginatedList.getRows()) {
			// TODO 替换city_area_id
			String mergerName = wxCustomerDto.getCustomerMergerName();
			if (mergerName != null) {
				Integer a = mergerName.indexOf(",");
				String mergerNameValue = mergerName.substring(a + 1);
				String mergerValue = mergerNameValue.replaceAll(",", "-");
				wxCustomerDto.setCustomerMergerName(mergerValue);
			}
		}
		MapContext result = MapContext.newOne();
		result.put("result", paginatedList.getRows());
		return ResultFactory.generateRequestResult(result, paginatedList.getPagination());
	}

	/**
	 * 根据客户表id查询客户详情
	 *
	 * @param branchId
	 * @param customerId
	 * @return
	 */
	@Override
	public Object findWxCustomerInfo(String branchId, String customerId) {
		MapContext params = MapContext.newOne();
		params.put("id", customerId);
		WxCustomerInfoDto wxCustomerInfoDto = this.companyCustomerService.findWxCustomerInfo(params);
		if (wxCustomerInfoDto == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//获取客户最近的一条订单信息
		String userId = wxCustomerInfoDto.getUserId();
		MapContext mapContext=MapContext.newOne();
		mapContext.put("uId",userId);
		mapContext.put("branchId",branchId);
		CustomOrder customOrder = this.customOrderService.findByUidAndBranchId(mapContext);
		if (customOrder != null) {
			//获取订单id和订单价格
			String orderId = customOrder.getId();
			wxCustomerInfoDto.setOrderId(orderId);
			wxCustomerInfoDto.setOrderAmount(customOrder.getAmount());
			//获取订单产品类型

			wxCustomerInfoDto.setOrderProductType(OrderProductType.getByValue(customOrder.getOrderProductType()).getName());
//			List<Map> products = this.orderProductService.findByOrderId(orderId);
//			if (products.size() > 1) {
//				wxCustomerInfoDto.setOrderProductType("多品");
//			} else if (products.size() == 1) {
//				wxCustomerInfoDto.setOrderProductType(products.get(0).get("type").toString());
//			}
		}
		return ResultFactory.generateRequestResult(wxCustomerInfoDto);
	}

	/**
	 * 添加客户
	 *
	 * @param branchId
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addWxCustomers(MapContext mapContext, String branchId,String uid) {
		String mobile = mapContext.getTypedValue("mobile", String.class);
		String companyId = mapContext.getTypedValue("companyId", String.class);
		String customerManager = (String) mapContext.get("customerManager");
		//验证电话号码是否正确
		if (!ValidateUtils.isChinaPhoneNumber(mobile)) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, mobile);
		}
		//判断手机号是否已存在
		CompanyCustomer customer=this.companyCustomerService.findByPhoneAndBranchId(mobile,companyId);
		//如果存在
		if (customer != null) {
			if (!customer.getName().equals(mapContext.getTypedValue("name", String.class))) {
				MapContext params = MapContext.newOne();
				params.put("name", mapContext.getTypedValue("name", String.class));
				params.put("id", customer.getId());
				AppBeanInjector.userService.updateByMapContext(params);
				return ResultFactory.generateRequestResult(customer);
			}
		}

		//客户与经销商绑定
		CompanyCustomer companyCustomer = new CompanyCustomer();
		companyCustomer.setCompanyId(companyId);
		companyCustomer.setStatus(CompanyCustomerStatus.CREATE.getValue());
		companyCustomer.setCreator(uid);
		companyCustomer.setCreated(DateUtil.getSystemDate());
		if (customerManager != null) {
			companyCustomer.setCustomerManager(customerManager);
		} else {
			companyCustomer.setCustomerManager(uid);
		}
		companyCustomer.setGrade(CompanyCustomerGrade.LOW.getValue());
		companyCustomer.setSource(CompanyCustomerSource.CSOCKET.getValue());
		companyCustomer.setCommunity((String) mapContext.get("community"));
		companyCustomer.setAddress((String) mapContext.get("address"));
		companyCustomer.setCityAreaId((String) mapContext.get("cityAreaId"));
		companyCustomer.setRemarks(mapContext.getTypedValue("remarks",String.class));
		companyCustomer.setSex(mapContext.getTypedValue("sex",Integer.class));
		companyCustomer.setName(mapContext.getTypedValue("name",String.class));
		companyCustomer.setPhone(mapContext.getTypedValue("mobile",String.class));
		this.companyCustomerService.add(companyCustomer);
		return ResultFactory.generateRequestResult(companyCustomer);
	}

	/**
	 * 添加客户时员工列表
	 *
	 * @param branchId
	 * @param dealerId
	 * @return
	 */
	@Override
	public RequestResult findWxCompanyEmployees(String branchId, String dealerId) {
		MapContext mapContext = MapContext.newOne();
		Integer employeeStatus = EmployeeStatus.NORMAL.getValue();
		mapContext.put("employeeStatus", employeeStatus);
		mapContext.put("companyId", dealerId);
		List<MapContext> employeeList = this.companyEmployeeService.findCompanyEmployees(mapContext);
		return ResultFactory.generateRequestResult(employeeList);
	}

	/**
	 * 添加客户时经销商列表
	 *
	 * @param branchId
	 * @return
	 */
	@Override
	public RequestResult findWxDealersAddCustomer(String branchId, String cityId) {
		List companyTypeList = new ArrayList();
		companyTypeList.add(CompanyType.DIRECT_SHOP.getValue());
		companyTypeList.add(CompanyType.SHOP_IN_STORE.getValue());
		companyTypeList.add(CompanyType.EXCLUSIVE_SHOP.getValue());
		companyTypeList.add(CompanyType.FRANCHISED_STORE.getValue());
		MapContext mapContext = MapContext.newOne();
		mapContext.put("branchId", branchId);
		mapContext.put("typeList", companyTypeList);
		if (cityId != null && !cityId.trim().equals("")) {
			mapContext.put("cityId", cityId);
		}
		List<Map> dealers = this.companyService.findWxDealersAddCustomer(mapContext);
		return ResultFactory.generateRequestResult(dealers);
	}

	@Override
	public RequestResult findCustomerOrders(Integer pageSize, Integer pageNum,String customerId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("customerId",customerId);
		//列表分页查询
		Pagination pagination = new Pagination();//设置分页信息
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		PaginatedFilter paginatedFilter = new PaginatedFilter();//查询条件
		paginatedFilter.setFilters(mapContext);
		paginatedFilter.setPagination(pagination);
		PaginatedList<WxCustomOrderDto> customOrderDtoPaginatedList = this.customOrderService.findWxOrderList(paginatedFilter);
		MapContext result = MapContext.newOne();
		result.put("CustomOrderlist", customOrderDtoPaginatedList.getRows());
		return ResultFactory.generateRequestResult(result, customOrderDtoPaginatedList.getPagination());
	}
}
