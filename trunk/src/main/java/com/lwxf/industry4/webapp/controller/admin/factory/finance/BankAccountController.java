package com.lwxf.industry4.webapp.controller.admin.factory.finance;


import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDto;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import com.lwxf.industry4.webapp.domain.entity.financing.BankAccount;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.finance.BankAccountFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(value="BankAccountController",tags={"F端后台管理接口:工厂银行机构管理"})
@RestController("BankAccountController")
@RequestMapping(value = "/api/f/bankAccounts", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class BankAccountController {
    @Resource(name = "bankAccountFacade")
    private BankAccountFacade bankAccountFacade;

    /**
     * 查询所有银行信息
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value = "经销商财务信息", notes = "")
    @GetMapping("")
    private String getyBankAccountList(){
        JsonMapper jsonMapper=new JsonMapper();
        MapContext map = MapContext.newOne();
        RequestResult result=this.bankAccountFacade.selectByFilter(map);
        return jsonMapper.toJson(result);
    }

    /**
     * 查询所有银行信息
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value = "银行账户列表分页查询", notes = "")
    @GetMapping("/list")
    private String getAllBanks(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Integer pageSize,
                               @RequestParam(required = false) Integer pageNum){
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        JsonMapper jsonMapper=new JsonMapper();
        MapContext map = MapContext.newOne();
        if(name!=null &&!name.equals("")){
            map.put("name",name);
        }

        RequestResult result=this.bankAccountFacade.selectByFilter(map,pageNum,pageSize);
        return jsonMapper.toJson(result);
    }

    /**
     * 新增银行
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value="添加银行",notes="添加银行",response = BankAccount.class)
    @PostMapping("bankAccount")
    private RequestResult addSupplier(@RequestBody BankAccount bankAccount){
        return this.bankAccountFacade.addBankAccount(bankAccount);
    }

    /**
     * 供应商更新
     * @return
     */
    @ApiOperation(value="更新供应商信息",notes="更新供应商信息")
    @PutMapping(value = "bankAccount/{bankAccountId}")
    public String updateBankAccount(@PathVariable String bankAccountId,
                                 @RequestBody MapContext map) {
        JsonMapper jsonMapper=new JsonMapper();
        return jsonMapper.toJson(this.bankAccountFacade.updateBankAccount(bankAccountId,map));
    }

    /**
     * 删除银行
     * @return
     */
    @ApiOperation(value="更新银行信息",notes="更新供应商信息")
    @DeleteMapping(value = "bankAccount/{bankAccountId}")
    public String deleteBankAccount(@PathVariable String bankAccountId) {
        JsonMapper jsonMapper=new JsonMapper();
        return jsonMapper.toJson(this.bankAccountFacade.deleteBankAccount(bankAccountId));
    }

    /**
     * 根据ID查询银行详情
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value="查看银行详情",notes="查看银行详情",response = BankAccount.class)
    @GetMapping("/bankAccount/{bankAccountId}")
    @ApiImplicitParam(name = "bankAccount", value = "银行id", dataType = "string", paramType = "path")
    private RequestResult findBankAccount(@PathVariable String bankAccountId){
        RequestResult result=this.bankAccountFacade.viewBankAccountInfo(bankAccountId);
        return result;
    }
}
