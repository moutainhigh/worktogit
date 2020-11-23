package com.lwxf.industry4.webapp.controller.visitinfo;

import com.lwxf.industry4.webapp.bizservice.visitinfo.VisitInfoFilesService;
import com.lwxf.industry4.webapp.bizservice.visitinfo.VisitInfoService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.visitinfo.VisitInfoFilesDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderInfoDto;
import com.lwxf.industry4.webapp.domain.entity.visitinfo.VisitInfo;
import com.lwxf.industry4.webapp.domain.entity.visitinfo.VisitInfoFiles;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author lyh on 2019/11/26
 */
@Api(value = "/visitInfoController", tags = {"拜访追踪"})
@RestController(value = "/visitInfoController")
@RequestMapping(value = "/visitInfoController", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class VisitInfoController {

    @Resource(name = "visitInfoService")
    private VisitInfoService visitInfoService;

    @Resource(name = "visitInfoFilesDao")
    private VisitInfoFilesDao visitInfoFilesDao;

    @Resource(name = "visitInfoFilesService")
    private VisitInfoFilesService visitInfoFilesService;


    @ApiOperation(value = "拜访信息添加", notes = "拜访信息添加",response = VisitInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "visitInfo", value = "访问信息实体", dataTypeClass = VisitInfo.class, paramType = "body", required = true)
    })
    @PostMapping("/addVisiInfo")
    public RequestResult addVisiInfo(@RequestBody VisitInfo visitInfo,String[] fileIds) {
        int i = visitInfoService.insertVisitInfo(visitInfo);
        if(fileIds!=null)
        for (String fileid : fileIds) {
            //更新附件id
            HashMap<String, Object> mapContext = new HashMap<>();
            mapContext.put("visit_info_id", visitInfo.getId());
            mapContext.put("id", fileid);MapContext from = MapContext.from(mapContext);
            //更新附件信息
            visitInfoFilesDao.updateByMapContext(from);
        }
        if(i!=0){
            return ResultFactory.generateSuccessResult();
        }
        return ResultFactory.generateResNotFoundResult();
    }

    @ApiOperation(value = "拜访信息列表查询", notes = "拜访信息列表查询")
    @GetMapping("/queryVisiInfoList")
    public RequestResult queryVisiInfoList( @RequestParam(required = false) Integer pageNum,
                                            @RequestParam(required = false) Integer pageSize,
                                            @RequestParam(required = false) String visitDate,
                                            @RequestParam(required = false) String visitedUserName,
                                            @RequestParam(required = false) String visitType,
                                            @RequestParam(required = false) String visitUserName,
                                            @RequestParam(required = false) String visitUserId,
                                            @RequestParam(required = false) String visitedUserId) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        MapContext mapContext = new MapContext();
        mapContext.put("visited_user_name",visitedUserName);
        mapContext.put("visitDate",visitDate);
        mapContext.put("visitType",visitType);
        mapContext.put("visitUserId",visitUserId);
        mapContext.put("visitedUserId",visitedUserId);
        paginatedFilter.setFilters(mapContext);
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        PaginatedList<VisitInfo> visitInfoPaginatedList = visitInfoService.selectByFilter(paginatedFilter);

        return ResultFactory.generateRequestResult(visitInfoPaginatedList);
    }

    @ApiOperation(value = "访问单上传附件", notes = "访问单上传附件")
    @PostMapping(value = "/uploadFileVisiInfo")
    public RequestResult uploadFileVisiInfo(@RequestBody MultipartFile multipartFile) {
        //updateFilesList
        String uid = WebUtils.getCurrUserId();
        if (uid == null) {
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        return visitInfoFilesService.uploadVisitInfoFiles(multipartFile, uid);
    }
    @ApiOperation(value = "访问单上传附件", notes = "访问单上传附件")
    @PostMapping(value = "/uploadFileVisiInfo2")
    public String  uploadFileVisiInfo2(@RequestBody MultipartFile file) {
        //updateFilesList
        String uid = WebUtils.getCurrUserId();

        return visitInfoFilesService.uploadVisitInfoFiles2(file, uid);
    }



}
