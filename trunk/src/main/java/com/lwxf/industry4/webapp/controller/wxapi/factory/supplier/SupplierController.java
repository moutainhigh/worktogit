package com.lwxf.industry4.webapp.controller.wxapi.factory.supplier;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.supplier.SupplierFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能：
 *
 * @author：zhangxiaolin(3965488)
 * @create：2019/06/09 15:22
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="CompanyFinanceController",tags={"F端微信小程序接口:供应商信息管理"})
@RestController("wxSupplierController")
@RequestMapping(value = "/wxapi/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class SupplierController {

    @Resource(name = "wxsupplierFacade")
    private SupplierFacade supplierFacade;

    /**
     * 查询供应商列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value = "供应商信息列表", notes = "",response = SupplierDtoFowWx.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "area", value = "地区名称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型", dataType = "integer", paramType = "query" ),
            @ApiImplicitParam(name = "keywords", value = "关键字：供应商名称、联系人名称、联系人电话", dataType = "string", paramType = "query" ),
            @ApiImplicitParam(name = "status", value = "状态:0：意向，1：签约", dataType = "integer", paramType = "query" ),
    })
    @GetMapping("suppliers")
    private RequestResult findAllSupplierList(@RequestParam(required = false) String area,
                                       @RequestParam(required = false) String status,
                                       @RequestParam(required = false) String type,
                                       @RequestParam(required = false) String keywords,
                                       @RequestParam(required = false) Integer pageNum,
                                       @RequestParam(required = false) Integer pageSize,
                                       HttpServletRequest request){
        if(null == pageSize){
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if(null == pageNum){
            pageNum = 1;
        }
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        MapContext mapContext = this.createdMapContent(area,type,status,keywords);
        mapContext.put("branchId",mapInfo.get("branchId"));
        return this.supplierFacade.findAllSupplierList(pageNum,pageSize,mapContext);
    }

    private MapContext createdMapContent(String area, String type,String status,String keywords) {
        MapContext mapContext = new MapContext();
        if(area!=null&&!area.trim().equals("")){
            mapContext.put("area",area);
        }
        if(type!=null&&!type.trim().equals("")){
            mapContext.put("categoryId",type);
        }
        if(status!=null&&!status.trim().equals("")){
            mapContext.put("supplierStage",status);
        }
        if(status!=null&&!status.trim().equals("")){
            mapContext.put("supplierStage",status);
        }
        if(keywords!=null&&!keywords.trim().equals("")){
            mapContext.put("keywords",keywords);
        }
        return mapContext;
    }

    /**
     * 新增供应商
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value="添加供应商",notes="添加供应商信息",response = Supplier.class)
    @PostMapping("suppliers")
    private RequestResult addSupplier(@RequestBody Supplier supplier,HttpServletRequest request){
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        MapContext map = MapContext.newOne();
        map.put("branchId",mapInfo.get("branchId"));
        //公司表信息
        RequestResult result = supplier.validateFields();
        supplier.setBranchId(mapInfo.get("branchId").toString());
        if(result!=null){
            return result;
        }
        return this.supplierFacade.addSupplier(supplier);
    }

    /**
     * 根据ID查询供应商详情
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value="查看供应商详情",notes="查看供应商详情",response = SupplierDtoFowWx.class)
    @GetMapping("/suppliers/{supplierId}")
    @ApiImplicitParam(name = "supplierId", value = "供应商id", dataType = "string", paramType = "path")
    private RequestResult findSupplierInfo(@PathVariable String supplierId,HttpServletRequest request){
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        RequestResult result=this.supplierFacade.viewSupplierInfo(supplierId);
        return result;
    }

    /**
     * 附件图片上传
     * @param multipartFileList 多个附件
     * @return
     */
    @ApiOperation(value="附件图片上传",notes="返回值样例:{ \"code\": \"200\", \"data\": { \"ids\": [ \"4tuqvoqutzpc\", \"4tuqvovumolc\" ] } }")
    @PostMapping(value = "/suppliers/uploadImages")
    public RequestResult uploadImages(@RequestBody List<MultipartFile> multipartFileList,HttpServletRequest request){
        Map<String, Object> errorInfo = new HashMap<>();
        if(multipartFileList!=null && multipartFileList.size()>0) {
            for (MultipartFile multipartFile : multipartFileList) {
                if (multipartFile == null) {
                    errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
                }
                if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
                    errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
                }
                if (multipartFile.getSize() > 1024L * 1024L * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
                    return ResultFactory.generateErrorResult(ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
                }
                if (errorInfo.size() > 0) {
                    return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorInfo);
                }
            }
        }
        return this.supplierFacade.uploadImage(multipartFileList);
    }

    /**
     * 供应商更新
     * @return
     */
    @ApiOperation(value="更新供应商信息",notes="更新供应商信息")
    @PutMapping(value = "suppliers/{supplierId}")
    public String updatePaymentSimple(@PathVariable String supplierId,
                                   @RequestBody MapContext map, HttpServletRequest request) {
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        return jsonMapper.toJson(this.supplierFacade.updateSupplier(supplierId,map));
    }

}
