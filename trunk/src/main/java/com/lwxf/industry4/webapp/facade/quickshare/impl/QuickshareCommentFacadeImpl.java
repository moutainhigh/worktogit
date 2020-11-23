package com.lwxf.industry4.webapp.facade.quickshare.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.quickshare.QuickshareCommentService;
import com.lwxf.industry4.webapp.bizservice.quickshare.QuickshareService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.quickshare.QuickshareCommentDto;
import com.lwxf.industry4.webapp.domain.entity.quickshare.Quickshare;
import com.lwxf.industry4.webapp.domain.entity.quickshare.QuickshareComment;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.quickshare.QuickshareCommentFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/6 0006 15:57
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("quickshareCommentFacade")
public class QuickshareCommentFacadeImpl extends BaseFacadeImpl implements QuickshareCommentFacade {
	@Resource(name="quickshareCommentService")
	private QuickshareCommentService quickshareCommentService;
	@Resource(name="quickshareService")
	private QuickshareService quickshareService;
	@Resource(name="userService")
	private UserService userService;

	//快享评论列表
	@Override
	public RequestResult selectQuickshareCommentList(String quickshareId, Integer pageNum, Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		//查询条件
		paginatedFilter.setPagination(pagination);
		MapContext mapContext=MapContext.newOne();
		mapContext.put("quickshareId",quickshareId);
		paginatedFilter.setFilters(mapContext);
		Map dataSort = new HashMap();
		List sorts = new ArrayList();
		dataSort.put("created","desc");
		sorts.add(dataSort);
		paginatedFilter.setSorts(sorts);
		PaginatedList<QuickshareComment> quickshareCommentPagination=this.quickshareCommentService.selectByFilter(paginatedFilter);
		MapContext result=MapContext.newOne();
		result.put("quickshareComment",quickshareCommentPagination.getRows());
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult  addQuickshareComment(String quickshareId, MapContext mapContext, HttpServletRequest request) {
		String uid=request.getHeader("X-UID");
		User user0=this.userService.findByUid(uid);
		if(user0==null){
			return ResultFactory.generateResNotFoundResult();
		}
		String commentCreatorName=user0.getName();
		QuickshareComment quickshareComment=new QuickshareComment();
		if(mapContext.containsKey("parentId")){
			quickshareComment.setParentId(mapContext.getTypedValue("parentId",String.class));

		}

		if(mapContext.containsKey("parentCreator")){
			quickshareComment.setParentCreator(mapContext.getTypedValue("parentCreator",String.class));
		}
		quickshareComment.setCreator(uid);
		quickshareComment.setContent(mapContext.getTypedValue("content",String.class));
		quickshareComment.setQuickshareId(quickshareId);
		quickshareComment.setCreated(DateUtil.getSystemDate());
		quickshareComment.setDisabled(true);
		this.quickshareCommentService.add(quickshareComment);
		QuickshareCommentDto quickshareCommentDto=new QuickshareCommentDto();
		quickshareCommentDto.setId(quickshareComment.getId());
		quickshareCommentDto.setContent(quickshareComment.getContent());
		quickshareCommentDto.setCreated(quickshareComment.getCreated());
		quickshareCommentDto.setCreator(quickshareComment.getCreator());
		quickshareCommentDto.setCommentName(commentCreatorName);
		quickshareCommentDto.setDisabled(quickshareComment.getDisabled());
		quickshareCommentDto.setParentCreator(quickshareComment.getParentCreator());
		quickshareCommentDto.setParentId(quickshareComment.getParentId());
		quickshareCommentDto.setQuickshareId(quickshareComment.getQuickshareId());
		String parentCreator=quickshareComment.getParentCreator();
		if(parentCreator!=null) {
			User user = this.userService.findByUserId(parentCreator);
			if(user!=null){
				String reCommentName=user.getName();
				quickshareCommentDto.setReCommentName(reCommentName);
			}
		}else {
			quickshareCommentDto.setReCommentName("");
			quickshareCommentDto.setParentId("");
			quickshareCommentDto.setParentCreator("");
		}
		return ResultFactory.generateRequestResult(quickshareCommentDto);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteQuickshareComment(String quickshareId, String commentId, HttpServletRequest request) {
		String uid=request.getHeader("X-UID");
		Quickshare quickshare=this.quickshareService.findById(quickshareId);
		if(quickshare==null){
			return ResultFactory.generateResNotFoundResult();
		}
		QuickshareComment quickshareComment=this.quickshareCommentService.findById(commentId);
		if(quickshareComment==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断此评论是否是登陆人的
		if(!uid.equals(quickshareComment.getCreator())){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NO_PERMISSION_10003, AppBeanInjector.i18nUtil.getMessage("BIZ_NO_PERMISSION_10003"));
		}
		//判断此评论下是否有回复的评论
		List<QuickshareComment> quickshareCommentList=this.quickshareCommentService.findByParentId(commentId);
		if(!quickshareCommentList.isEmpty()){
			this.quickshareCommentService.updateByParentId(commentId);
		}
		this.quickshareCommentService.deleteById(commentId);
		return ResultFactory.generateSuccessResult();
	}
}
