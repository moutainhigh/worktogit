package com.lwxf.industry4.webapp.controller.admin.factory.corporatePartners;

import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.enums.user.UserSex;
import com.lwxf.industry4.webapp.common.enums.user.UserState;
import com.lwxf.industry4.webapp.common.enums.user.UserType;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.corporatePartners.CorporatePartnersDto;
import com.lwxf.industry4.webapp.domain.entity.corporatePartners.CorporatePartners;
import com.lwxf.industry4.webapp.facade.admin.factory.CorporatePartners.CorporatePartnersFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/8/1/001 11:43
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@Api(value = "CorporatePartnersController",tags = "外协厂家管理")
@RequestMapping(value = "/api/f/corporatepartners",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class CorporatePartnersController {

	@Resource(name = "corporatePartnersFacade")
	private CorporatePartnersFacade corporatePartnersFacade;



	@GetMapping
	@ApiOperation(value = "查询外协厂家列表",response = CorporatePartnersDto.class)
	private RequestResult findCorporatePartnersList(@RequestParam(required = false)@ApiParam(value = "名称") String name
	,@RequestParam(required = false)@ApiParam(value = "银行账户") String bankAccount
	,@RequestParam(required = false)@ApiParam(value = "开户行") String bank
	,@RequestParam(required = false)@ApiParam(value = "联系人电话") String tel
	,@RequestParam(required = false)@ApiParam(value = "联系人名称") String contactName
	,@RequestParam(required = false,defaultValue = "1")@ApiParam(value = "页码") Integer pageNum
	,@RequestParam(required = false,defaultValue = "10")@ApiParam(value = "数据量")Integer pageSize){
		MapContext mapContext = this.createMapContext(name,bankAccount,bank,tel,contactName);

		return this.corporatePartnersFacade.findCorporatePartnersList(mapContext,pageSize,pageNum);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "查询外协厂家详情")
	private String findCorporatePartnersInfo(@PathVariable String id){
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		return jsonMapper.toJson(this.corporatePartnersFacade.findCorporatePartnersInfo(id));
	}

	@PostMapping("addoutAccount")
	@ApiOperation(value = "新增外协账号信息")
	private RequestResult addoutAccount(@RequestBody CorporatePartners corporatePartners){
		corporatePartners.setCreated(DateUtil.getSystemDate());
		corporatePartners.setCreator(WebUtils.getCurrUserId());
		corporatePartners.setBranchId(WebUtils.getCurrBranchId());
		RequestResult result = corporatePartners.validateFields();
		//todo 新建外协厂家账号
        String tel = corporatePartners.getTel();
        if(LwxfStringUtils.isBlank(tel)){
            return ResultFactory.generateErrorResult("404","手机号不能为空!");
        }
        if(result!=null){
			return result;
		}
		return this.corporatePartnersFacade.addoutAccount(corporatePartners);
	}



    @PostMapping
    @ApiOperation(value = "新增外协厂家")
    private RequestResult addCorporatePartners(@RequestBody CorporatePartners corporatePartners){
        corporatePartners.setCreated(DateUtil.getSystemDate());
        corporatePartners.setCreator(WebUtils.getCurrUserId());
        corporatePartners.setBranchId(WebUtils.getCurrBranchId());
        RequestResult result = corporatePartners.validateFields();
        //todo 新建外协厂家账号
        String tel = corporatePartners.getTel();
        if(LwxfStringUtils.isBlank(tel)){
            return ResultFactory.generateErrorResult("404","手机号不能为空!");
        }
        if(result!=null){
            return result;
        }
        return this.corporatePartnersFacade.addCorporatePartners(corporatePartners);
    }


	@PutMapping("/{id}")
	@ApiOperation(value = "修改外协厂家")
	private RequestResult updateCorporatePartners(@PathVariable String id,@RequestBody MapContext update){
		RequestResult result = CorporatePartners.validateFields(update);
		if(result!=null){
			return result;
		}
		return this.corporatePartnersFacade.updateCorporatePartners(id,update);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除外协厂家")
	private RequestResult deleteCorporatePartners(@PathVariable String id){
		return this.corporatePartnersFacade.deleteCorporatePartners(id);
	}


	private MapContext createMapContext(String name, String bankAccount, String bank, String tel, String contactName) {
		MapContext mapContext = new MapContext();
		if(name!=null){
			mapContext.put(WebConstant.KEY_ENTITY_NAME,name);
		}
		if(bankAccount!=null){
			mapContext.put("bankAccount",bankAccount);
		}
		if(bank!=null){
			mapContext.put("bank",bank);
		}
		if(tel!=null){
			mapContext.put("tel",tel);
		}
		if(contactName!=null){
			mapContext.put("contactName",contactName);
		}
		return mapContext;
	}


}
