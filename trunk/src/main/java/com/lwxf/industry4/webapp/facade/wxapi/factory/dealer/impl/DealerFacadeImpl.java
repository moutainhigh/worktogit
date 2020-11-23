package com.lwxf.industry4.webapp.facade.wxapi.factory.dealer.impl;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.uniquekey.IIdGenerator;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.ValidateUtils;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.company.DealerShopService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductService;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.bizservice.user.UserBasisService;
import com.lwxf.industry4.webapp.common.authcode.AuthCodeUtils;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.company.CompanyStatus;
import com.lwxf.industry4.webapp.common.enums.company.DealerEmployeeRole;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeStatus;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderFilesStatus;
import com.lwxf.industry4.webapp.common.enums.user.*;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.UserExtraUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDtoForApp;
import com.lwxf.industry4.webapp.domain.dto.company.WxCompanyDto;
import com.lwxf.industry4.webapp.domain.dto.uploadFiles.UploadFileDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.company.DealerShop;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccount;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.factory.dealer.DealerFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/6 0006 15:20
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "wxDealerFacade")
public class DealerFacadeImpl extends BaseFacadeImpl implements DealerFacade {
	@Resource(name = "companyService")
	private CompanyService companyService;
	@Resource(name = "dealerShopService")
	private DealerShopService dealerShopService;
	@Resource(name = "uploadFilesService")
	private UploadFilesService uploadFilesService;
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "orderProductService")
	private OrderProductService orderProductService;
	@Resource(name = "userBasisService")
	private UserBasisService userBasisService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "basecodeService")
	private BasecodeService basecodeService;
	@Resource(name = "dealerAccountService")
	private DealerAccountService dealerAccountService;

	@Override
	public RequestResult findWxDealers(String branchId, Integer pageNum, Integer pageSize, MapContext params) {
		params.put("branchId", branchId);
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		paginatedFilter.setPagination(pagination);
		paginatedFilter.setFilters(params);
		PaginatedList<CompanyDtoForApp> companyDtoForAppPaginatedList = this.companyService.findWxDealers(paginatedFilter);
		MapContext result = MapContext.newOne();
		result.put("companyDto", companyDtoForAppPaginatedList.getRows());
		//经销商总数
		List count = new ArrayList();
		Map map1 = new HashMap();
		Integer allDealer = this.companyService.findAllCompanyCount(branchId);
		map1.put("name", "合计");
		map1.put("value", allDealer);
		count.add(map1);
		//意向经销商数量
		Map map2 = new HashMap();
		Integer intention = CompanyStatus.INTENTION.getValue();
		Integer intentionDealer = this.companyService.findIntentionDealer(intention, branchId);
		map2.put("name", "意向");
		map2.put("value", intentionDealer);
		count.add(map2);
		//签约经销商数量
		Map map3 = new HashMap();
		Integer signed = CompanyStatus.NORMAL.getValue();
		Integer signedDealer = this.companyService.findSignedDealer(signed, branchId);
		map3.put("name", "签约");
		map3.put("value", signedDealer);
		count.add(map3);
		result.put("count", count);
		return ResultFactory.generateRequestResult(result, companyDtoForAppPaginatedList.getPagination());
	}

	/**
	 * 经销商信息查询
	 *
	 * @param branchId
	 * @param dealerId
	 * @return
	 */
	@Override
	public RequestResult findWxDealerInfo(String branchId, String dealerId) {
		//查询经销商基础信息
		WxCompanyDto wxCompanyDto = this.companyService.findByBranchIdAndDealerId(branchId, dealerId);
		if (wxCompanyDto == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		Integer status = 1;
		//查询封面地址
		Integer coverType = UploadResourceType.COVER.getType();
		UploadFiles coverImage = this.uploadFilesService.findCoverImageByCidAndStatusAndResourceType(dealerId, status, coverType);
		if (coverImage != null) {
			String coverUrl = coverImage.getFullPath();
			wxCompanyDto.setCoverUrl(coverUrl);
		}
		//图片查询
		MapContext params = MapContext.newOne();
		params.put("resourceId", dealerId);
		//查询名片地址
		Integer cardType = UploadResourceType.VISITING_CARD.getType();
		params.put("status", status);
		params.put("cardType", cardType);
		List<UploadFileDto> cardUrl = this.uploadFilesService.findByCidAndStatusAndType(params);
		wxCompanyDto.setCardUrl(cardUrl);
		//查询合同附件
		Integer contractType = UploadResourceType.COMPANY.getType();
		params.remove("cardType");
		params.put("contractType", contractType);
		List<UploadFileDto> contractUrl = this.uploadFilesService.findByCidAndStatusAndType(params);
		wxCompanyDto.setCompanyUrl(contractUrl);
		//最近的一个订单信息
		CustomOrder customOrder = this.customOrderService.findByCidAndBranchId(dealerId, branchId);
		if (customOrder != null) {
			//获取订单id和订单价格
			String orderId = customOrder.getId();
			wxCompanyDto.setOrderId(orderId);
			wxCompanyDto.setOrderAmount(customOrder.getAmount().toString());
			//获取订单产品类型
			List<Map> products = this.orderProductService.findByOrderId(orderId);
			if (products.size() > 1) {
				wxCompanyDto.setOrderProdyctType("多品");
			} else if (products.size() == 1) {
				wxCompanyDto.setOrderProdyctType(products.get(0).get("type").toString());
			}
		}

		return ResultFactory.generateRequestResult(wxCompanyDto);
	}

	/**
	 * 添加经销商
	 *
	 * @param company
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addWxDealer(Company company, String fileId, String uid, Integer status) {
		//添加经销商公司信息
		company.setCreated(DateUtil.getSystemDate());
		company.setCreator(uid);
		company.setStatus(status);
		company.setFollowers(0);
		company.setGrade(0);
		this.companyService.add(company);
		//修改封面图片状态
		MapContext map = MapContext.newOne();
		map.put("id", fileId);
		map.put("status", UploadType.FORMAL.getValue());
		map.put("companyId", company.getId());
		map.put("resourceId", company.getId());
		this.uploadFilesService.updateByMapContext(map);
		return ResultFactory.generateRequestResult(company);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateWxDealerInfo(String dealerId, MapContext mapContext) {
		mapContext.put("id", dealerId);
		MapContext map=MapContext.newOne();
		if(mapContext.containsKey("leaderName")){
			map.put("name",mapContext.getTypedValue("leaderName",String.class));
		}
		if(mapContext.containsKey("leaderTel")){
			map.put("leaderTel",mapContext.getTypedValue("leaderTel",String.class));
		}
		if(map.size()>0){
			String uId=this.companyService.findCompanyById(dealerId).getLeader();
			map.put("id",uId);
			AppBeanInjector.userService.updateByMapContext(map);
		}
		if(mapContext.size()==1&&mapContext.containsKey("leaderName")){
			return ResultFactory.generateSuccessResult();
		}
		this.companyService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDealerShopCoverImage(MultipartFile multipartFile) {
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
		if (uid == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		String cid = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
		if (cid == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		Integer status = 1;
		Integer resourcetype = UploadResourceType.COVER.getType();
		UploadFiles uploadFile = this.uploadFilesService.findCoverImageByCidAndStatusAndResourceType(cid, status, resourcetype);
		if (uploadFile != null) {
			uploadFilesService.deleteById(uploadFile.getId());
		}
		UploadFiles uploadFiles = new UploadFiles();
		UploadInfo uploadInfo;
		uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.COVER, "cover");
		uploadFiles.setCompanyId(cid);
		uploadFiles.setName(uploadInfo.getFileName());
		uploadFiles.setCreator(uid);
		uploadFiles.setCreated(DateUtil.getSystemDate());
		uploadFiles.setPath(uploadInfo.getRelativePath());
		uploadFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
		uploadFiles.setMime(uploadInfo.getFileMimeType().getRealType());
		uploadFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
		uploadFiles.setStatus(UploadType.TEMP.getValue());
		uploadFiles.setResourceType(UploadResourceType.COVER.getType());
		this.uploadFilesService.add(uploadFiles);
		MapContext mapContext = MapContext.newOne();
		mapContext.put("imagePath", uploadFiles.getFullPath());
		mapContext.put("fileId", uploadFiles.getId());
		return ResultFactory.generateRequestResult(mapContext);
	}

	@Override
	public RequestResult companyOrderInfoCount(String dealerId, String yearTime) {
		//经销商详情
		CompanyDto companyById = this.companyService.findCompanyById(dealerId);
		if(companyById==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//查询经销商账户信息
		Integer nature=1;
		List<DealerAccount> byCompanIdAndNature = this.dealerAccountService.findByCompanIdAndNature(dealerId, nature);
		//月份下单数据和金额
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM");
		String startTime=null;
		String endTime=null;
		ArrayList<Date> dates = new ArrayList<Date>();
		if(yearTime==null||yearTime.equals("")){
			startTime= DateUtilsExt.getNowYear().toString()+"-"+1;
			endTime=dateFormat.format(com.lwxf.mybatis.utils.DateUtil.now());
		}else {
			startTime=yearTime+"-"+1;
			endTime=yearTime+"-"+12;
		}
		try {
			Date start=dateFormat.parse(startTime);
			Date end=dateFormat.parse(endTime);
			dates=DateUtilsExt.getMonthBetween(start,end);//两个日期内的月份集合
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList list=new ArrayList();
		for(int i=dates.size();i>0;i--){//日期循环查询
			Date date=dates.get(i-1);
			MapContext mapContext=MapContext.newOne();
			mapContext.put("monthDate",date);
			mapContext.put("companyId",dealerId);
			MapContext context=this.customOrderService.selectCompanyOrderInfoCount(mapContext);
			context.put("date",dateFormat.format(date));
			list.add(context);
		}
		//系列分析
		MapContext seriesMap = MapContext.newOne();
		Integer deFlag = 0;
		String seriesType = "orderProductSeries";
		//查询产品系列
		List<Basecode> basecodes = this.basecodeService.findByTypeAndDelFlag(seriesType, deFlag);
		//橱柜
		Integer productType = 0;
		MapContext param=MapContext.newOne();
		param.put("branchId", WebUtils.getCurrBranchId());
		param.put("startDateTime",startTime);
		param.put("endDateTime",endTime);
		param.put("companyId",dealerId);
		param.put("type", productType);
		List list0 = new ArrayList();
		for (Basecode basecode : basecodes) {
			Integer code = Integer.valueOf(basecode.getCode());
			param.put("series", code);
			MapContext map = this.customOrderService.countOrderSeries(param);
			map.put("seriesName", basecode.getValue());
			list0.add(map);
		}
		//衣柜
		productType = 1;
		param.put("type", productType);
		List list1 = new ArrayList();
		for (Basecode basecode : basecodes) {
			Integer code = Integer.valueOf(basecode.getCode());
			param.put("series", code);
			MapContext map = this.customOrderService.countOrderSeries(param);
			map.put("seriesName", basecode.getValue());
			list1.add(map);
		}
		seriesMap.put("橱柜", list0);
		seriesMap.put("衣柜", list1);
		//结果返回
		MapContext result=MapContext.newOne();
		result.put("companyInfo",companyById);
		result.put("monthList",list);
		result.put("seriesMap",seriesMap);
		result.put("dealerAccount",byCompanIdAndNature);
		return ResultFactory.generateRequestResult(result);
	}

}
