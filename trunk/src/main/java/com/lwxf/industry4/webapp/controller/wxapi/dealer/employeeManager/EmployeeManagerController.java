package com.lwxf.industry4.webapp.controller.wxapi.dealer.employeeManager;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.rongcloud.RongCloudUtils;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.bizservice.dealerEmployee.DealerEmployeeService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.dto.UserInfoObj;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.UniqueKeyUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.attachmentFiles.impl.AttachmentFilesDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderInfoDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerAddressDto;
import com.lwxf.industry4.webapp.domain.dto.dept.CompanyEmployeeInfoDto;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeCertificateDto;
import com.lwxf.industry4.webapp.domain.dto.dept.UpdateUserRoleDeptDto;
import com.lwxf.industry4.webapp.domain.entity.attachmentFiles.AttachmentFiles;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.company.*;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAddress;
import com.lwxf.industry4.webapp.domain.entity.dealerEmployee.DealerEmployee;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.org.dept.DeptFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.org.employee.DeptMemberFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.rong.models.response.TokenResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.get;

/**
 * 功能：b端经销商员工管理
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/21/ 14:17
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "EmployeeManagerController", tags = {"b端经销商员工管理"})
@RestController
@RequestMapping(value = "/wxdealer/EmployeeManagerController", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class EmployeeManagerController {

    @Resource(name = "dealerEmployeeService")
    private DealerEmployeeService dealerEmployeeService;

    @Resource(name = "attachmentFilesDao")
    private AttachmentFilesDao attachmentFilesDao;

    /**
     * 新增经销商员工信息
     *
     * @param dealerEmployee
     * @return
     */
    @PostMapping("/addEmployee")
    @ApiOperation(value = "新增员工", notes = "新增员工", response = DealerEmployee.class)
    public RequestResult addEmployee(@RequestBody DealerEmployee dealerEmployee) {
        dealerEmployee.setDealerCompanyId(WebUtils.getCid());
        dealerEmployee.setId(UniqueKeyUtil.getStringId());

        int add = dealerEmployeeService.add(dealerEmployee);
        //更新附件信息
        String fileIds = dealerEmployee.getFileIds();
        if(!"".equals(fileIds)){
            MapContext mapContext = new MapContext();
            mapContext.put("id",fileIds);
            mapContext.put("originalId",dealerEmployee.getId());
            mapContext.put("functionType","1");//经销商员工头像
            attachmentFilesDao.updateByMapContext(mapContext);
        }
        if (add != 0) {
            return ResultFactory.generateSuccessResult();
        }
        return ResultFactory.generateErrorResult("500", "新建员工失败");
    }


    /**
     * 删除经销商员工信息
     *
     * @param eid
     * @return
     */
    @PostMapping("/deleteEmployee")
    @ApiOperation(value = "删除员工", notes = "删除员工", response = DealerEmployee.class)
    public RequestResult deleteEmployee(String eid) {
        int i = dealerEmployeeService.deleteById(eid);
        if (i != 0) {
            return ResultFactory.generateSuccessResult();
        }
        return ResultFactory.generateErrorResult("500", "删除员工失败");
    }




    /**
     * 员工列表
     *
     * @return
     */
    @GetMapping("/listEmployee")
    @ApiOperation(value = "员工列表", notes = "员工列表", response = DealerEmployee.class)
    public RequestResult listEmployee(String eid) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        MapContext mapContext = new MapContext();
        String cid = WebUtils.getCid();
        mapContext.put("dealerCompanyId", cid);
        if(!"".equals(eid) && eid !=null){
            mapContext.put("id", eid);
        }
        paginatedFilter.setFilters(mapContext);
        PaginatedList<DealerEmployee> paginatedList = dealerEmployeeService.selectByFilter(paginatedFilter);
        List<DealerEmployee> rows = paginatedList.getRows();
        for (DealerEmployee d:rows){
            String id = d.getId();
            PaginatedFilter paginatedFilter1 = new PaginatedFilter();
            MapContext mapContext1 = new MapContext();
            mapContext1.put("originalId",id);
            mapContext1.put("functionType","1");//经销商员工头像
            paginatedFilter1.setFilters(mapContext1);
            PaginatedList<AttachmentFiles> attachmentFilesPaginatedList = attachmentFilesDao.selectByFilter(paginatedFilter1 );
            List<AttachmentFiles> rows1 = attachmentFilesPaginatedList.getRows();
            if(rows1 !=null&& rows1.size() !=0){
                AttachmentFiles attachmentFiles =  rows1.get(0);
                d.setHeadPortraits(attachmentFiles.getPath());
            }else{
                d.setHeadPortraits("/man.png");
            }
        }
        return ResultFactory.generateRequestResult(paginatedList);
    }

    /**
     * 编辑员工信息
     *
     * @return
     */
    @PostMapping("/editEmployee")
    @ApiOperation(value = "编辑员工信息", notes = "编辑员工信息", response = MapContext.class)
    public RequestResult editEmployee(@RequestBody MapContext mapContext) {
        int i = dealerEmployeeService.updateByMapContext(mapContext);
        String id = mapContext.get("id")+"";
        //编辑头像
        String fileIds = mapContext.get("fileIds")+"";//头像附件id
        //先查询是否存在originalId 不存在则更新
        if(!"".equals(fileIds) && fileIds!=null){//存在就更新
            //先删除旧头像
            attachmentFilesDao.deleteByOriginalId(id);
            //更新新头像
            MapContext mapContextfile = new MapContext();
            mapContextfile.put("id",fileIds);
            mapContextfile.put("originalId",id);
            mapContextfile.put("functionType","1");
            attachmentFilesDao.updateByMapContext(mapContextfile);
        }
        if (i != 0) {
            return ResultFactory.generateSuccessResult();
        }
        return ResultFactory.generateErrorResult("500", "更新员工失败");
    }



















}
