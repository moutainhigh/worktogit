package com.lwxf.industry4.webapp.controller.admin.factory.content;

import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsTypeService;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsType;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.contentmng.ContentsFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;

import javax.annotation.Resource;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.admin.factory.content.ContentFacade;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/15/015 16:33
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "内容管理",tags = "内容管理")
@RestController
@RequestMapping(value = "/api/f/contents",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ContentController {
	@Resource(name = "contentFacade")
	private ContentFacade contentFacade;

	@Resource(name = "contentsTypeService")
	private ContentsTypeService contentsTypeService;

	@Resource(name = "fContentsFacade")
	private ContentsFacade contentsFacade;

	@ApiOperation(value = "查询内容详情",notes = "返回值为三种 案例 (案例+内容数组) 活动 内容")
	@GetMapping("/{type}/{id}")
	private RequestResult findContentByTypeAndId(@PathVariable@ApiParam(value = "0 设计案例 1 活动 2 内容") Integer type, @PathVariable@ApiParam(value = "资源ID") String id){
		return this.contentFacade.findContentByTypeAndId(type,id);
	}


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
		ContentsType contentsListByCode = contentsTypeService.findContentsListByCode(typeCode);
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		MapContext mapContent = this.createMapContent(name, code, publisher,contentsListByCode.getId(),status);
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


}
