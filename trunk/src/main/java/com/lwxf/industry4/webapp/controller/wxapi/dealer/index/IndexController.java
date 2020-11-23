package com.lwxf.industry4.webapp.controller.wxapi.dealer.index;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsTypeService;
import com.lwxf.industry4.webapp.bizservice.jdproduct.JdProductInfoService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.crawlerUtil.JDProduct;
import com.lwxf.industry4.webapp.common.enums.scheme.SchemeStatus;
import com.lwxf.industry4.webapp.common.enums.scheme.SchemeType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.industry4.webapp.domain.dto.design.DesignSchemeDto;
import com.lwxf.industry4.webapp.domain.dto.product.ProductDto;
import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsType;
import com.lwxf.industry4.webapp.domain.entity.design.DesignScheme;
import com.lwxf.industry4.webapp.domain.entity.jdproduct.JdProductInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.contentmng.ContentsFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.design.DesignSchemeFacade;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.Index.IndexFacade;
import com.lwxf.mybatis.utils.MapContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Api(value = "IndexController", tags = {"B端微信小程序接口:经销商首页"})
@RestController
@RequestMapping(value = "/wxapi/b/dealer", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class IndexController {

    @Resource(name = "wxDealerIndexFacade")
    private IndexFacade indexFacade;

    @Resource(name = "jdProductInfoService")
    private JdProductInfoService jdProductInfoService;


    @Autowired
    private JDProduct jdProduct;


    @Resource(name = "designSchemeFacade")
    private DesignSchemeFacade designSchemeFacade;


    @Resource(name = "fContentsFacade")
    private ContentsFacade contentsFacade;

    @Resource(name = "contentsTypeService")
    private ContentsTypeService contentsTypeService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = Contents.class)
    })
    @ApiOperation(value="查询内容列表",notes="查询内容列表")
    @GetMapping("findContents")
    private RequestResult findContents(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) String code,
                                       @RequestParam(required = false) String publisher,
                                       @RequestParam(required = false) String typeCode,
                                       @RequestParam(required = false) Integer status,
                                       @RequestParam(required = false) Integer pageSize,
                                       @RequestParam(required = false) Integer pageNum) {
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        String contentTypeId=null;
        if(typeCode!=null&&!typeCode.equals("")){
            ContentsType contentsListByCode = contentsTypeService.findContentsListByCode(typeCode);
            contentTypeId=contentsListByCode.getId();
        }
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        MapContext mapContent = this.createMapContent(name, code, publisher,contentTypeId,status);
        mapContent.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
        return this.contentsFacade.findContents(mapContent,pageNum,pageSize);
    }

    @ApiResponse(code = 200, message = "查询成功", response = ContentsDto.class)
    @ApiOperation(value="查看内容详情",notes="查看内容详情")
    @GetMapping(value = "/findContentsById/{contentId}")
    private RequestResult findContentsById(@PathVariable String contentId) {
        return  this.contentsFacade.findByContentId(contentId);
    }

    /**
     * 参数组成
     * @param name  标题
     * @param code  文件类型(0:学习 1帮助 2新闻)
     * @param publisher  发布人
     * @param contentTypeId  文章类型ID
     * @param status  文件状态：0草稿 1发布 2取消发布（默认为0）
     * @return
     */
    private MapContext createMapContent(String name, String code, String publisher, String contentTypeId,Integer status) {
        MapContext mapContext = MapContext.newOne();
        if (name != null&&!name.equals("")) {
            mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
        }
        if (code!=null) {
            mapContext.put("code", code);
        }
        if (publisher!=null) {
            mapContext.put("publisher", publisher);
        }
        if (status!=null) {
            mapContext.put("status", status);
        }
        if (contentTypeId!=null) {
            mapContext.put("contentsTypeId", contentTypeId);
        }
        return mapContext;
    }
    /**
     * 根据条件查询设计案例列表
     *
     * @param name
     * @param status
     * @param styles
     * @param type
     * @param doorState
     * @param no
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findDesignSchemeList")
    @ApiOperation(value = "根据条件查询设计案例列表",notes = "",response = DesignSchemeDto.class)
    private String findDesignSchemeList(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) Integer status,
                                        @RequestParam(required = false) String styles,
                                        @RequestParam(required = false) Integer type,
                                        @RequestParam(required = false) String doorState,
                                        @RequestParam(required = false) String no,
                                        @RequestParam(required = false) Integer pageNum,
                                        @RequestParam(required = false) Integer pageSize) {

        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        MapContext mapContext = this.createMapContext(name, status, styles, type, doorState, no);
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.designSchemeFacade.findDesignSchemeList(mapContext, pageNum, pageSize));
    }

    private MapContext createMapContext(String name, Integer status, String styles, Integer type, String doorState, String no) {
        MapContext mapContext = new MapContext();
        if (name != null && !name.trim().equals("")) {
            mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
        }
        if (status != null) {
            mapContext.put(WebConstant.KEY_ENTITY_STATUS, Arrays.asList(status));
        }else{
            mapContext.put(WebConstant.KEY_ENTITY_STATUS,Arrays.asList(SchemeStatus.DRAFT.getValue(),SchemeStatus.TO_AUDIT.getValue(),SchemeStatus.PUBLISHED.getValue(),SchemeStatus.SOLD_OUT.getValue()));
        }
        if (styles != null) {
            mapContext.put("styles", styles);
        }
        if (type != null) {
            mapContext.put("type", type);
        }
        if (doorState != null) {
            mapContext.put("doorState", doorState);
        }
        if (no != null) {
            mapContext.put(WebConstant.STRING_NO, no);
        }
        return mapContext;
    }

    /**
     * 查询案例详情
     * @param id
     * @return
     */
    @GetMapping("/findDesignSchemeInfo/{id}")
    @ApiOperation(value = "查询案例详情")
    private String findDesignSchemeInfo(@PathVariable String id){
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.designSchemeFacade.findDesignSchemeInfo(id));
    }

    /**
     * 查询产品列表接口
     *
     * @return
     */
    @GetMapping("/index")
    @ApiOperation(value = "查询产品列表接口", response = ProductDto.class)
    private RequestResult viewIndex(HttpServletRequest request) {
        String atoken = request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        return this.indexFacade.viewIndex(mapInfo.get("companyId").toString());
    }

    /**
     * 查询京东商品列表接口
     *
     * @return
     */
    @GetMapping("/jdProductGoodsList")
    @ApiOperation(value = "查询产品列表接口", response = ProductDto.class)
    private RequestResult jdProductGoodsList(HttpServletRequest request) {
        String atoken = request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        MapContext mapContext = new MapContext();
        paginatedFilter.setFilters(mapContext);
        PaginatedList<JdProductInfo> jdProductInfoPaginatedList = jdProductInfoService.selectByFilter(paginatedFilter);
        List<JdProductInfo> rows = jdProductInfoPaginatedList.getRows();
        if (rows == null || rows.size() <= 4) {
            jdProduct.insertJdProductInfo();
        } else {
            JdProductInfo jdProductInfo = rows.get(0);
            Date fetchingTime = jdProductInfo.getFetchingTime();
            int i = differentDays(fetchingTime, new Date());
            if (i >= 7) {//大于七天就删除重新抓取
                jdProduct.insertJdProductInfo();
                jdProductInfoService.deleteByFetchingTime(fetchingTime);
            } else {
                return ResultFactory.generateRequestResult(jdProductInfoPaginatedList);
            }
        }
        PaginatedFilter paginatedFilter2 = new PaginatedFilter();
        MapContext mapContext2 = new MapContext();
        paginatedFilter.setFilters(mapContext);
        PaginatedList<JdProductInfo> jdProductInfoPaginatedList2 = jdProductInfoService.selectByFilter(paginatedFilter);
        return ResultFactory.generateRequestResult(jdProductInfoPaginatedList2);
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //不同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //同一年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

}
