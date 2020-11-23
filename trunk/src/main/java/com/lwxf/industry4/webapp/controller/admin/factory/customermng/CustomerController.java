package com.lwxf.industry4.webapp.controller.admin.factory.customermng;

import io.swagger.annotations.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customer.CustomerDtoV2;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.customermng.CustomerFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/1/15 9:52
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController("fcustomerController")
@RequestMapping(value = "/api/f",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "CustomerController",tags = "客户管理")
public class CustomerController {

    @Resource(name = "fCustomerFacade")
    private CustomerFacade fCustomerFacade;

    /**
     *
     * 查询所有的客户（可以根据公司id和顾客姓名，电话查询）
     * @param companyId
     * @return
     */
    @GetMapping(value = "customers")
    @ApiOperation(value = "查询所有的客户",notes = "查询所有的客户",response = CustomerDtoV2.class)
    public RequestResult findByClient(@RequestParam(required = false)@ApiParam(value = "公司主键ID") String companyId,
                                      @RequestParam(required = false)@ApiParam(value = "姓名") String name,
                                      @RequestParam(required = false)@ApiParam(value = "电话") String phone,
                                      @RequestParam(required = false)@ApiParam(value = "数据量") Integer pageSize,
                                      @RequestParam(required = false)@ApiParam(value = "页码") Integer pageNum){
        if(null == pageSize){
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if(null == pageNum){
            pageNum = 1;
        }
        MapContext mapContext = this.createMapContext(companyId,name,phone);
        RequestResult result = this.fCustomerFacade.findByClient(mapContext,pageSize,pageNum);
        return result;
    }


    @ApiOperation(value = "查询客户详情",notes = "查询客户详情",response = CustomerDtoV2.class)
    @GetMapping("/customers/{id}")
    private RequestResult findClientInfo(@PathVariable String id){
        return this.fCustomerFacade.findClientInfo(id);
    }


    private MapContext createMapContext(String companyId, String name, String phone) {
        MapContext mapContext = new MapContext();
        if(companyId!=null){
            mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID,companyId);
        }
        if(name!=null&&!name.trim().equals("")){
            mapContext.put(WebConstant.KEY_ENTITY_NAME,name);
        }
        if(phone!=null&&!phone.trim().equals("")){
            mapContext.put(WebConstant.KEY_ENTITY_PHONE,phone);
        }
        return mapContext;
    }

    /**
     * 添加顾客
     * @param customer
     * @return
     */
    @PostMapping(value = "customers")
    @ApiOperation(value = "添加顾客",notes = "添加顾客")
    public String addClient(@RequestBody CompanyCustomer customer){
        String currUserId = WebUtils.getCurrUserId();
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(fCustomerFacade.addCustomer(customer, currUserId));
    }

    /**
     * 修改客户信息
     * @param cid
     * @param mapContext
     * @return
     */
    @PutMapping("/customers/{cid}")
    @ApiOperation(value = "修改客户信息",notes = "修改客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "客户的用户主键ID",name = "cid",dataType = "string",paramType = "path"),
            @ApiImplicitParam(value = "所需修改的内容",name = "mapContext",dataTypeClass = MapContext.class,paramType = "body")
    })
    public RequestResult updateClient(@PathVariable String cid,@RequestBody MapContext mapContext){
        RequestResult result = CompanyCustomer.validateFields(mapContext);
        if(result!=null){
            return result;
        }
        return this.fCustomerFacade.updateClient(cid,mapContext);
    }
    /**
     * 客户信息统计
     *
     * @param
     * @return
     */
    @GetMapping("/customers/count")
    @ApiOperation(value = "客户信息统计",notes = "客户信息统计")
    private RequestResult findCustomerCount() {
        String branchId= WebUtils.getCurrBranchId();
        return this.fCustomerFacade.findCustomerCount(branchId);
    }

}

