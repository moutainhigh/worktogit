package com.lwxf.industry4.webapp.facade.wxapi.dealer.company.impl;

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
import com.lwxf.industry4.webapp.common.enums.company.CompanyCustomerGrade;
import com.lwxf.industry4.webapp.common.enums.company.CompanyCustomerSource;
import com.lwxf.industry4.webapp.common.enums.company.CompanyCustomerStatus;
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
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.company.BWxCustomerFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/20 0020 9:59
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "bWxCustomerFacade")
public class BWxCustomerFacadeImpl extends BaseFacadeImpl implements BWxCustomerFacade {
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
	@Override
	public RequestResult findWxBCustomers(Integer pageNum, Integer pageSize, MapContext mapContext) {
		//分页查询处理
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		paginatedFilter.setPagination(pagination);
		paginatedFilter.setFilters(mapContext);
		Map dataSort = new HashMap();
		List sorts = new ArrayList();
		dataSort.put("created","desc");
		sorts.add(dataSort);
		paginatedFilter.setSorts(sorts);
		PaginatedList<WxCustomerDto> paginatedList = this.companyCustomerService.findBWxCustomers(paginatedFilter);
		//处理地区名称的显示,把','换成'-'
		for (WxCustomerDto wxCustomerDto : paginatedList.getRows()) {
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

	@Override
	public RequestResult findWxCustomerInfo(String customerId,String cid) {
		MapContext params = MapContext.newOne();
		params.put("id", customerId);
		WxCustomerInfoDto wxCustomerInfoDto = this.companyCustomerService.findWxCustomerInfo(params);
		if (wxCustomerInfoDto == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//获取客户最近的一条订单信息
		MapContext mapContext=MapContext.newOne();
		mapContext.put("customId",customerId);
		mapContext.put("companyId",cid);
		CustomOrder customOrder = this.customOrderService.findByUidAndBranchId(mapContext);
		if (customOrder != null) {
			//获取订单id和订单价格
			String orderId = customOrder.getId();
			wxCustomerInfoDto.setOrderId(orderId);
			wxCustomerInfoDto.setOrderAmount(customOrder.getAmount());
			//获取订单产品类型
			List<Map> products = this.orderProductService.findByOrderId(orderId);
			if (products.size() > 1) {
				wxCustomerInfoDto.setOrderProductType("多品");
			} else if (products.size() == 1) {
				wxCustomerInfoDto.setOrderProductType(products.get(0).get("type").toString());
			}
		}
		return ResultFactory.generateRequestResult(wxCustomerInfoDto);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addWxCustomers(MapContext mapContext, String cid, String uid, String branchid) {
		String mobile = mapContext.getTypedValue("mobile", String.class);
		String companyId = cid;
		String customerManager = null;
		if (mapContext.containsKey("customerManager")) {
			customerManager = (String) mapContext.get("customerManager");
		} else {
			customerManager = uid;
		}
		//验证电话号码是否正确
		if (!ValidateUtils.isChinaPhoneNumber(mobile)) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_INVALID_MOBILE_NO, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
		}
		//判断手机号是否已存在
		CompanyCustomer customer = this.companyCustomerService.findByPhoneAndBranchId(mobile, companyId);
		//如果存在
		if (customer != null) {
//			if (!customer.getName().equals(mapContext.getTypedValue("name", String.class))) {
//				MapContext params = MapContext.newOne();
//				params.put("name", mapContext.getTypedValue("name", String.class));
//				params.put("id", customer.getId());
//				AppBeanInjector.userService.updateByMapContext(params);
//				return ResultFactory.generateRequestResult(customer);
//			}
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_MOBILE_HAVE_EXISTED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_MOBILE_HAVE_EXISTED"));
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
		companyCustomer.setRemarks(mapContext.getTypedValue("remarks", String.class));
		companyCustomer.setSex(mapContext.getTypedValue("sex",Integer.class));
		companyCustomer.setName(mapContext.getTypedValue("name",String.class));
		companyCustomer.setPhone(mapContext.getTypedValue("mobile",String.class));
		this.companyCustomerService.add(companyCustomer);
		return ResultFactory.generateRequestResult(companyCustomer);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateWxCustomers(MapContext mapContext, String customerId) {
		MapContext mapContext2=MapContext.newOne();
		if(mapContext.containsKey("sex")){
			mapContext2.put("sex",mapContext.getTypedValue("sex",String.class));
		}
		if(mapContext.containsKey("name")){
			mapContext2.put("name",mapContext.getTypedValue("name",String.class));
		}
		if(mapContext.containsKey("mobile")){
			String mobile = mapContext.getTypedValue("mobile",String.class);
			String companyId = mapContext.getTypedValue("companyId", String.class);
			mapContext2.put("phone", mobile);
			//验证电话号码是否正确
			if (!ValidateUtils.isChinaPhoneNumber(mobile)) {
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_INVALID_MOBILE_NO, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
			}
			// 验证新手机号是否已存在
            CompanyCustomer duplicateCustomer = this.companyCustomerService.findByPhoneAndBranchId(mobile, companyId); // 冲突客户
            String duplicateCustomerId = duplicateCustomer == null ? null : duplicateCustomer.getId();
            if (duplicateCustomerId != null && !duplicateCustomerId.equals(customerId)) {
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_MOBILE_HAVE_EXISTED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_MOBILE_HAVE_EXISTED"));
            }

		}
		if(mapContext.containsKey("cityAreaId")){
           mapContext2.put("cityAreaId",(String) mapContext.get("cityAreaId"));
		}
		if(mapContext.containsKey("address")){
			mapContext2.put("address",(String) mapContext.get("address"));
		}
		if(mapContext.containsKey("community")){
			mapContext2.put("community",(String) mapContext.get("community"));
		}
		if(mapContext.containsKey("remarks")){
			mapContext2.put("remarks",(String) mapContext.get("remarks"));
		}
		if(mapContext2.size()>0){
			mapContext2.put("id",customerId);
			this.companyCustomerService.updateByMapContext(mapContext2);
		}

		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult deleteWxCustomer(String customerId, String cid) {
	    // 存在订单不允许删除
        MapContext mapContext=MapContext.newOne();
        mapContext.put("customId",customerId);
        mapContext.put("companyId",cid);
        CustomOrder customOrder = this.customOrderService.findByUidAndBranchId(mapContext);
        if (customOrder != null) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_DELETE_FAILURE_10073, AppBeanInjector.i18nUtil.getMessage("BIZ_DELETE_FAILURE_10073"));
        }
		this.companyCustomerService.deleteById(customerId);
		return ResultFactory.generateSuccessResult();
	}
}
