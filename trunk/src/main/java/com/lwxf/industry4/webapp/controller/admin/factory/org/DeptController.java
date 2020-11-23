package com.lwxf.industry4.webapp.controller.admin.factory.org;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.rongcloud.RongCloudUtils;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.bizservice.user.UserRoleService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.dto.UserInfoObj;
import com.lwxf.industry4.webapp.common.enums.user.UserState;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.dept.CompanyEmployeeInfoDto;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeCertificateDto;
import com.lwxf.industry4.webapp.domain.dto.dept.UpdateUserRoleDeptDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.company.*;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.org.dept.DeptFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.org.employee.DeptMemberFacade;
import com.lwxf.industry4.webapp.facade.user.UserFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.rong.models.response.TokenResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：部门管理与员工管理
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/11/30/030 14:17
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "DeptController",tags = {"部门管理与员工管理接口"})
@RestController
@RequestMapping(value = "api/f/depts", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DeptController {
    @Resource(name = "deptFacade")
    private DeptFacade deptFacade;
    @Resource(name = "deptMemberFacade")
    private DeptMemberFacade deptMemberFacade;
    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @Resource(name = "userRoleService")
    private UserRoleService userRoleService;

    /**
     * 查询全部部门
     *
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询全部部门")
    private String findAllDeptList() {
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.deptFacade.findAllDeptList());
    }


    /**
     * 添加部门
     *
     * @param dept
     * @return
     */
    @PostMapping
    @ApiOperation(value = "添加部门")
    private String addDept(@RequestBody@ApiParam(value = "部门信息") Dept dept) {
        JsonMapper jsonMapper = new JsonMapper();
        dept.setCompanyId(WebUtils.getCurrCompanyId());
        dept.setBranchId(WebUtils.getCurrBranchId());
        RequestResult result = dept.validateFields();
        if (result != null) {
            return jsonMapper.toJson(result);
        }
        return jsonMapper.toJson(this.deptFacade.addDept(dept));
    }

    /**
     * 修改部门信息
     *
     * @param mapContext
     * @param id
     * @return
     */
    @PutMapping("{id}")
    @ApiOperation(value = "修改部门信息")
    private String updateDept(@RequestBody MapContext mapContext, @PathVariable String id) {
        JsonMapper jsonMapper = new JsonMapper();
        RequestResult result = Dept.validateFields(mapContext);
        if (result != null) {
            return jsonMapper.toJson(result);
        }
        return jsonMapper.toJson(this.deptFacade.updateDept(mapContext, id));
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation(value = "删除部门")
    private RequestResult deleteById(@PathVariable String id) {
        return this.deptFacade.deleteById(id);
    }

    /**
     * 获取公司下全部员工
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("members")
    @ApiOperation(value = "获取公司下全部员工")
    private String findMemberList(@RequestParam(required = false) Integer pageNum,
                                  @RequestParam(required = false) Integer pageSize,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String no,
                                  @RequestParam(required = false) String mobile,
                                  @RequestParam(required = false) Integer status,
                                  @RequestParam(required = false) String roleId,
                                  @RequestParam(required = false) String companyId,
                                  @RequestParam(required = false) String deptId) {

        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        JsonMapper jsonMapper = new JsonMapper();
        MapContext mapContext = this.createMapContent(name, no, mobile, status, roleId,companyId);
        return jsonMapper.toJson(this.deptFacade.findMemberList(pageNum, pageSize, mapContext, deptId));
    }

    /**
     * 获取公司下全部部门及部门下员工
     *
     * @return
     */
    @GetMapping("/members/all")
    @ApiOperation(value = "获取公司下全部部门及部门下员工")
    private String findDeptAndMemberList() {
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.deptFacade.findDeptAndMemberList(WebUtils.getCurrBranchId()));
    }

    private MapContext createMapContent(String name, String no, String mobile, Integer status, String roleId,String companyId) {
        MapContext mapContext = MapContext.newOne();
        if (name != null && !name.trim().equals("")) {
            mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
        }
        if (no != null && !no.trim().equals("")) {
            mapContext.put(WebConstant.STRING_NO, no);
        }
        if (mobile != null && !mobile.trim().equals("")) {
            mapContext.put(WebConstant.KEY_ENTITY_MOBILE, mobile);
        }
        if (status == null || status == -1) {
            mapContext.put(WebConstant.KEY_ENTITY_STATUS, null);
        } else {
            mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
        }
        if (roleId == null || roleId.equals("all")) {
            mapContext.put("roleId", null);
        } else {
            mapContext.put("roleId", roleId);
        }
        if(companyId==null){
            companyId=WebUtils.getCurrCompanyId();
        }
        mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID,companyId);
        return mapContext;
    }

    /**
     * 公司新增员工
     *
     * @param mapContext
     * @return
     */
    @PostMapping("members")
    @ApiOperation(value = "公司新增员工")
    private String addDMember(@RequestBody MapContext mapContext) {
        JsonMapper jsonMapper = new JsonMapper();
        StringBuffer pwd = new StringBuffer();
        String deptIds=mapContext.getTypedValue("deptId",String.class);
        RequestResult requestResult = this.deptMemberFacade.addDeptMember(mapContext, deptIds, pwd);

        //注册成功后给用户发短信
        if (Integer.parseInt((String) requestResult.get("code")) == (200)) {
            SmsUtil.sendDealerInfoMessage(mapContext.getTypedValue("mobile", String.class), mapContext.getTypedValue("name", String.class), pwd.toString());
        }
        //处理融云token
        UserInfoObj data = (UserInfoObj) requestResult.getData();
        User user = null;
        if (data != null) {
            user = data.getUser();
            TokenResult tokenResult = RongCloudUtils.registerUser(user);
            if (tokenResult != null) {
                String token = tokenResult.getToken();
                AppBeanInjector.userThirdInfoFacade.updateRongToken(user.getId(), token);
                UserThirdInfo userThirdInfo = data.getUserThirdInfo();
                userThirdInfo.setRongcloudToken(token);
            }
            return jsonMapper.toJson(ResultFactory.generateRequestResult(AppBeanInjector.deptService.findOneByUserId(user.getId())));
        }
        return jsonMapper.toJson(requestResult);
    }


    /**
     * 修改员工信息
     *
     * @param eid
     * @param updateDto
     * @return
     */
    @PutMapping("members/{eid}")
    @ApiOperation(value = "修改员工信息",notes = "修改员工信息")
    @ApiResponses({
            @ApiResponse(code = 200,message = "只存在公司员工信息和员工信息 余下四个集合不返回",response = CompanyEmployeeInfoDto.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",dataType = "string",paramType = "path")
    }
    )
    private String updateMemberInfo(@PathVariable String eid,
                                           @RequestBody UpdateUserRoleDeptDto updateDto) {

        //角色组判断
        List<String> roleIds = updateDto.getRoleIds();
        String userId = updateDto.getUserInfo().getTypedValue("userId", String.class);
        userRoleService.updateInsertUserRole(userId,roleIds);//一个用户对应多个角色
        String email = updateDto.getUserInfo().getTypedValue("email", String.class);
        if (email == null || email.trim().equals("")) {
            updateDto.getUserInfo().put("email", null);
        }
        String birthday = updateDto.getUserInfo().getTypedValue("birthday", String.class);
        if (birthday == null || birthday.trim().equals("")) {
            updateDto.getUserInfo().put("birthday", null);
        }
        JsonMapper jsonMapper = new JsonMapper();
        if(updateDto.getEmployeeInfo().size()==0){
            if(updateDto.getUserInfo().size()==0){
                return jsonMapper.toJson(ResultFactory.generateSuccessResult());
            }else{
                RequestResult res = User.validateFields(updateDto.getUserInfo());
                if(res!=null){
                    return jsonMapper.toJson(res);
                }
            }
        }else{
            RequestResult result = EmployeeInfo.validateFields(updateDto.getEmployeeInfo());
            if (result != null) {
                return jsonMapper.toJson(result);
            }
            if(updateDto.getUserInfo().size()!=0){
                RequestResult res = User.validateFields(updateDto.getUserInfo());
                if(res!=null){
                    return jsonMapper.toJson(res);
                }
            }
        }
        return jsonMapper.toJson(this.deptFacade.updateMemberInfo(eid, updateDto));
    }

    /**
     * 查询员工信息详情
     * @param eid
     * @return
     */
    @ApiResponses({
            @ApiResponse(code =200,message = "查询成功",response =CompanyEmployeeInfoDto.class)
    })
    @ApiOperation(value = "查询员工信息详情",notes = "附带全部的员工信息")
    @GetMapping("/members/{eid}/info")
    @ApiImplicitParam(name = "eid",value = "公司员工主键ID",dataType = "string",paramType = "path")
    private String findMemberInfo(@PathVariable String eid){
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.deptFacade.findMemberInfo(eid));
    }

    /**
     * 修改员工信息
     * @param eid
     * @return
     */
    @PutMapping("/members/{eid}/employeeInfo")
    @ApiResponses({
            @ApiResponse(code = 200,message = "修改成功",response = EmployeeInfo.class)
    })
    @ApiOperation(value = "修改员工信息",notes = "修改完成后,会把员工信息返回回去")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",dataType = "string",paramType = "path"),
            @ApiImplicitParam(name = "mapContext",value = "所需修改的字段参数",dataType = "map",paramType = "body")
    })
    private String updateEmployeeInfo(@PathVariable String eid,@RequestBody MapContext mapContext){
        RequestResult result = EmployeeInfo.validateFields(mapContext);
        JsonMapper jsonMapper = new JsonMapper();
        if(result!=null){
            return jsonMapper.toJson(result);
        }
        return jsonMapper.toJson(this.deptFacade.updateEmployeeInfo(eid,mapContext));
    }

    /**
     * 新增员工工作经历
     * @param eid
     * @param employeeExperience
     * @return
     */
    @PostMapping("/members/{eid}/experiences")
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加成功",response = EmployeeExperience.class)
    })
    @ApiOperation(value = "新增员工工作经历",notes = "新增完成后,会把工作经历数据返回")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String addEmployeeExperience(@PathVariable String eid,@RequestBody EmployeeExperience employeeExperience){
        employeeExperience.setCompanyEmployeeId(eid);
        RequestResult result = employeeExperience.validateFields();
        JsonMapper jsonMapper = new JsonMapper();
        if(result!=null){
            return jsonMapper.toJson(result);
        }
        return jsonMapper.toJson(this.deptFacade.addEmployeeExperience(eid,employeeExperience));
    }

    /**
     * 修改员工工作经历
     * @param eid
     * @param id
     * @param mapContext
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200,message = "修改完成",response = EmployeeExperience.class)
    })
    @PutMapping("/members/{eid}/experiences/{id}")
    @ApiOperation(value = "修改员工工作经历",notes = "修改员工工作经历")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path"),
            @ApiImplicitParam(name = "mapContext",value = "所需修改的数据",required = true,dataType = "map",paramType = "body"),
            @ApiImplicitParam(name = "id",value = "员工工作经历主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String updataEmployeeExperience(@PathVariable String eid,@PathVariable String id,@RequestBody MapContext mapContext){
        RequestResult result = EmployeeExperience.validateFields(mapContext);
        JsonMapper jsonMapper = new JsonMapper();
        if (result!=null){
            return jsonMapper.toJson(result);
        }
        return jsonMapper.toJson(this.deptFacade.updataEmployeeExperience(eid,id,mapContext));
    }

    /**
     * 删除员工工作经历
     * @param eid
     * @param id
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除成功")
    })
    @DeleteMapping("/members/{eid}/experiences/{id}")
    @ApiOperation(value = "删除员工工作经历",notes = "删除员工工作经历")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path"),
            @ApiImplicitParam(name = "id",value = "员工工作经历主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String deleteEmployeeExperience(@PathVariable String eid,@PathVariable String id){
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.deptFacade.deleteEmployeeExperience(eid,id));
    }

    /**
     * 新增员工考核信息
     * @param eid
     * @param employeeAssessment
     * @return
     */
    @PostMapping("/members/{eid}/assessment")
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加成功",response = EmployeeAssessment.class)
    })
    @ApiOperation(value = "新增员工考核信息",notes = "新增员工考核信息,会把考核信息数据返回")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String addEmployeeAssessment(@PathVariable String eid,@RequestBody EmployeeAssessment employeeAssessment){
        employeeAssessment.setCompanyEmployeeId(eid);
        RequestResult result = employeeAssessment.validateFields();
        JsonMapper jsonMapper = new JsonMapper();
        if(result!=null){
            return jsonMapper.toJson(result);
        }
        return jsonMapper.toJson(this.deptFacade.addEmployeeAssessment(eid,employeeAssessment));
    }

    /**
     * 修改员工考核信息
     * @param eid
     * @param id
     * @param mapContext
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200,message = "修改完成",response = EmployeeExperience.class)
    })
    @PutMapping("/members/{eid}/assessment/{id}")
    @ApiOperation(value = "修改员工考核信息",notes = "修改员工考核信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path"),
            @ApiImplicitParam(name = "mapContext",value = "所需修改的数据",required = true,dataType = "map",paramType = "body"),
            @ApiImplicitParam(name = "id",value = "员工考核信息主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String updataEmployeeAssessment(@PathVariable String eid,@PathVariable String id,@RequestBody MapContext mapContext){
        RequestResult result = EmployeeAssessment.validateFields(mapContext);
        JsonMapper jsonMapper = new JsonMapper();
        if (result!=null){
            return jsonMapper.toJson(result);
        }
        return jsonMapper.toJson(this.deptFacade.updataEmployeeAssessment(eid,id,mapContext));
    }

    /**
     * 删除员工考核信息
     * @param eid
     * @param id
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除成功")
    })
    @DeleteMapping("/members/{eid}/assessment/{id}")
    @ApiOperation(value = "删除员工考核信息",notes = "删除员工考核信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path"),
            @ApiImplicitParam(name = "id",value = "员工考核信息主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String deleteEmployeeAssessment(@PathVariable String eid,@PathVariable String id){
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.deptFacade.deleteEmployeeAssessment(eid,id));
    }
    /**
     * 新增员工证书信息
     * @param eid
     * @param employeeCertificate
     * @return
     */
    @PostMapping("/members/{eid}/certificate")
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加成功",response = EmployeeCertificate.class)
    })
    @ApiOperation(value = "新增员工证书信息",notes = "新增员工证书信息,会把考核信息数据返回")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String addEmployeeCertificate(@PathVariable String eid,@RequestBody EmployeeCertificateDto employeeCertificate){
        employeeCertificate.setCompanyEmployeeId(eid);
        RequestResult result = employeeCertificate.validateFields();
        JsonMapper jsonMapper = new JsonMapper();
        if(result!=null){
            return jsonMapper.toJson(result);
        }
        return jsonMapper.toJson(this.deptFacade.addEmployeeCertificate(eid,employeeCertificate));
    }

    /**
     * 修改员工证书信息
     * @param eid
     * @param id
     * @param mapContext
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200,message = "修改完成",response = EmployeeCertificate.class)
    })
    @PutMapping("/members/{eid}/certificate/{id}")
    @ApiOperation(value = "修改员工证书信息",notes = "修改员工证书信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path"),
            @ApiImplicitParam(name = "mapContext",value = "所需修改的数据",required = true,dataType = "map",paramType = "body"),
            @ApiImplicitParam(name = "id",value = "员工证书信息主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String updataEmployeeCertificate(@PathVariable String eid,@PathVariable String id,@RequestBody MapContext mapContext){
        RequestResult result = EmployeeCertificate.validateFields(mapContext);
        JsonMapper jsonMapper = new JsonMapper();
        if (result!=null){
            return jsonMapper.toJson(result);
        }
        return jsonMapper.toJson(this.deptFacade.updataEmployeeCertificate(eid,id,mapContext));
    }

    /**
     * 删除员工证书信息
     * @param eid
     * @param id
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除成功")
    })
    @DeleteMapping("/members/{eid}/certificate/{id}")
    @ApiOperation(value = "删除员工证书信息",notes = "删除员工证书信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path"),
            @ApiImplicitParam(name = "id",value = "员工证书信息主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String deleteEmployeeCertificate(@PathVariable String eid,@PathVariable String id){
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.deptFacade.deleteEmployeeCertificate(eid,id));
    }
    /**
     * 新增员工教育经历
     * @param eid
     * @param employeeEducationExperience
     * @return
     */
    @PostMapping("/members/{eid}/education")
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加成功",response = EmployeeEducationExperience.class)
    })
    @ApiOperation(value = "新增员工教育经历",notes = "新增员工教育经历,会把员工教育经历返回")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String addEmployeeEducationExperience(@PathVariable String eid,@RequestBody EmployeeEducationExperience employeeEducationExperience){
        employeeEducationExperience.setCompanyEmployeeId(eid);
        RequestResult result = employeeEducationExperience.validateFields();
        JsonMapper jsonMapper = new JsonMapper();
        if(result!=null){
            return jsonMapper.toJson(result);
        }
        return jsonMapper.toJson(this.deptFacade.addEmployeeEducationExperience(eid,employeeEducationExperience));
    }

    /**
     * 修改员工教育经历
     * @param eid
     * @param id
     * @param mapContext
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200,message = "修改完成",response = EmployeeEducationExperience.class)
    })
    @PutMapping("/members/{eid}/education/{id}")
    @ApiOperation(value = "修改员工教育经历",notes = "修改员工教育经历")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path"),
            @ApiImplicitParam(name = "mapContext",value = "所需修改的数据",required = true,dataType = "map",paramType = "body"),
            @ApiImplicitParam(name = "id",value = "员工教育经历主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String updataEmployeeEducationExperience(@PathVariable String eid,@PathVariable String id,@RequestBody MapContext mapContext){
        RequestResult result = EmployeeEducationExperience.validateFields(mapContext);
        JsonMapper jsonMapper = new JsonMapper();
        if (result!=null){
            return jsonMapper.toJson(result);
        }
        return jsonMapper.toJson(this.deptFacade.updataEmployeeEducationExperience(eid,id,mapContext));
    }

    /**
     * 删除员工教育经历
     * @param eid
     * @param id
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除成功")
    })
    @DeleteMapping("/members/{eid}/education/{id}")
    @ApiOperation(value = "删除员工教育经历",notes = "删除员工教育经历")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eid",value = "公司员工主键ID",required = true,dataType = "string",paramType = "path"),
            @ApiImplicitParam(name = "id",value = "员工证书信息主键ID",required = true,dataType = "string",paramType = "path")
    })
    private String deleteEmployeeEducationExperience(@PathVariable String eid,@PathVariable String id){
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.deptFacade.deleteEmployeeEducationExperience(eid,id));
    }

    /**
     * 员工信息上传图片资源
     * @param eid
     * @param resId
     * @param multipartFiles
     * @return
     */
    @ApiResponses({
            @ApiResponse(message = "上传成功",code = 200,response = UploadFiles.class)
    })
    @ApiOperation(value = "员工信息上传图片资源",notes = "员工信息上传图片资源")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "图片资源集合",name = "multipartFiles",paramType = "body",dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(value = "上传资源所属的那个资源的ID",name = "resId",paramType = "path",dataType = "string"),
            @ApiImplicitParam(value = "员工主键ID",name = "eid",paramType = "path",dataType = "string")
    })
    @PostMapping("/members/{eid}/files/{resId}")
    private RequestResult uploadEmployeeFiles(@PathVariable String eid, @PathVariable String resId, @RequestBody List<MultipartFile> multipartFiles){
        Map errorInfo = new HashMap();
        if (multipartFiles == null||multipartFiles.size()==0) {
            errorInfo.put("multipartFiles", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.VALIDATE_ERROR, errorInfo);
        }

        for (MultipartFile multipartFile:multipartFiles) {
            if (multipartFile==null){
                errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
            if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
                errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
            }
            if (multipartFile.getSize() > 1024L * 1024L * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
                return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
            }
        }
        if (errorInfo.size() > 0) {
            return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.VALIDATE_ERROR, errorInfo);
        }
        return this.deptFacade.uploadEmployeeFiles(eid,resId,multipartFiles);
    }


    /**
     * 删除员工资源图片
     * @param eid
     * @param fileId
     * @return
     */
    @ApiOperation(value = "删除员工资源图片",notes = "删除员工资源图片")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "资源文件主键ID",name = "fileId",paramType = "path",dataType = "string"),
            @ApiImplicitParam(value = "员工主键ID",name = "eid",paramType = "path",dataType = "string")
    })
    @DeleteMapping("/members/{eid}/files/{fileId}")
    private RequestResult deleteUploadFiles(@PathVariable String eid,@PathVariable String fileId){
        return this.deptFacade.deleteUploadFiles(eid,fileId);
    }

    /**
     * 工厂账户启用/禁用
     * @param uid
     * @return
     */
    @PutMapping("/members/{uid}/enableAndProhibit")
    @ApiOperation(value = "工厂账户启用/禁用",notes = "工厂账户启用/禁用")
    private String enableAndProhibitFactory(@PathVariable@ApiParam(value = "用户主键ID") String uid) {
        User user = new User();
        user.setId(uid);
        RequestResult requestResult = this.userFacade.enableAndProhibitFactory(uid);
        JsonMapper jsonMapper = new JsonMapper();
        //启用后给用户发短信
        UserInfoObj data = (UserInfoObj) requestResult.getData();
        if (Integer.parseInt((String) requestResult.get("code")) == (200)) {
            User u=data.getUser();
            if(u.getState()== UserState.ENABLED.getValue()) {
                SmsUtil.sendDealerInfoMessage(u.getMobile(), u.getName(),"111111");
            }
        }
        return jsonMapper.toJson(requestResult);
    }

    /**
     * 登录小程序工厂账户启用/禁用
     * @param uid
     * @return
     */
    @PutMapping("/members/{uid}/enableAndProhibitWxLogin")
    @ApiOperation(value = "工厂账户微信小程序登录功能启用/禁用",notes = "工厂账户微信小程序登录功能启用/禁用")
    public RequestResult enableAndProhibitWxLogin(@PathVariable@ApiParam(value = "用户主键ID") String uid) {
        return this.userFacade.enableAndProhibitWxLogin(uid);
    }

    /**
     * 登录小程序工厂账户启用/禁用
     * @param uid
     * @return
     */
    @PutMapping("/members/{uid}/enableAndProhibitPcLogin")
    @ApiOperation(value = "工厂账户PC端登录功能启用/禁用",notes = "工厂账户PC端登录功能启用/禁用")
    public RequestResult enableAndProhibitPcLogin(@PathVariable@ApiParam(value = "用户主键ID") String uid) {
        return this.userFacade.enableAndProhibitPcLogin(uid);
    }

}
