package com.lwxf.industry4.webapp.controller.admin.factory.org;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeDailyRecordVisibleState;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.company.EmployeeDailyRecordDto;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeDailyRecord;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeDailyRecordComment;
import com.lwxf.industry4.webapp.facade.admin.factory.org.employee.EmployeeDailyRecordFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：F端 员工日报管理
 *
 * @author：Administrator
 * @create：2019/5/25/025 14:28
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController("fEmployeeController")
@RequestMapping(value = "/api/f/employees/dailyrecords",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "F端 员工日报管理",tags = "F端 员工日报管理")
public class EmployeeController {

	@Resource(name = "fEmployeeDailyRecordFacade")
	private EmployeeDailyRecordFacade employeeDailyRecordFacade;

	/**
	 * 新增日报
	 * @param employeeDailyRecord
	 * @return
	 */
	@ApiOperation(value = "新增日报",notes = "新增日报")
	@PostMapping
	private RequestResult addDailyrecord(@RequestBody EmployeeDailyRecord employeeDailyRecord){
		employeeDailyRecord.setCreateTime(DateUtil.getSystemDate());
		employeeDailyRecord.setCreateUser(WebUtils.getCurrUserId());
		employeeDailyRecord.setVisibleState(EmployeeDailyRecordVisibleState.DEPT.getValue());
		RequestResult result = employeeDailyRecord.validateFields();
		if(result!=null){
			return result;
		}
		return this.employeeDailyRecordFacade.addDailyrecord(employeeDailyRecord);
	}

	/**
	 * 查询日报列表
	 * @param title
	 * @param content
	 * @param createUserName
	 * @param createTime
	 * @param recordState
	 * @param visibleState
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "查询日报列表",notes = "查询日报列表",response = EmployeeDailyRecordDto.class)
	private RequestResult findDailyrecord(@RequestParam(required = false)@ApiParam(value = "日志标题") String title,
										  @RequestParam(required = false)@ApiParam(value = "日志内容") String content,
										  @RequestParam(required = false)@ApiParam(value = "创建人名称")String createUserName,
										  @RequestParam(required = false)@ApiParam(value = "创建时间")String createTime,
										  @RequestParam(required = false)@ApiParam(value = "日志状态，0：草稿，1：发布，2：已删除，默认：0")Integer recordState,
										  @RequestParam(required = false)@ApiParam(value = "日志可见范围")Integer visibleState,
										  @RequestParam(required = false)@ApiParam(value = "页码")Integer pageNum,
										  @RequestParam(required = false)@ApiParam(value = "数据量")Integer pageSize){
		MapContext mapContext = this.createMapContext(title,content,createUserName,createTime,recordState,visibleState);
		return this.employeeDailyRecordFacade.findDailyrecord(mapContext,pageSize,pageNum);
	}

	/**
	 * 修改日报列表
	 * @param id
	 * @param mapContext
	 * @return
	 */
	@PutMapping("/{id}")
	@ApiOperation(value = "修改日报列表",notes = "修改日报列表")
	private RequestResult updateDailyrecord(@PathVariable String id,@RequestBody MapContext mapContext){
		mapContext.put("updateUser",WebUtils.getCurrUserId());
		mapContext.put("updateTime",DateUtil.getSystemDate());
		RequestResult result = EmployeeDailyRecord.validateFields(mapContext);
		if (result!=null){
			return result;
		}
		return this.employeeDailyRecordFacade.updateDailyrecord(id,mapContext);
	}

	/**
	 * 删除日志 (逻辑删除)
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除日志",notes = "删除日志")
	private RequestResult deleteDailyrecord(@PathVariable String id){
		return this.employeeDailyRecordFacade.deleteDailyrecord(id);
	}

	/**
	 * 对日志评论
	 * @param id
	 * @param employeeDailyRecordComment
	 * @return
	 */
	@PostMapping("/{id}/comment")
	@ApiOperation(value = "对日志评论",notes = "对日志评论")
	private RequestResult addDailyrecordComment(@PathVariable String id, @RequestBody EmployeeDailyRecordComment employeeDailyRecordComment){
		employeeDailyRecordComment.setDailyRecordId(id);
		employeeDailyRecordComment.setCommentUser(WebUtils.getCurrUserId());
		employeeDailyRecordComment.setCreateTime(DateUtil.getSystemDate());
		RequestResult result = employeeDailyRecordComment.validateFields();
		if(result!=null){
			return result;
		}
		return this.employeeDailyRecordFacade.addDailyrecordComment(id,employeeDailyRecordComment);
	}

	/**
	 * 删除评论
	 * @param id
	 * @param commentId
	 * @return
	 */
	@DeleteMapping("/{id}/comment/{commentId}")
	@ApiOperation(value = "删除评论",notes = "删除评论")
	private RequestResult deleteComment(@PathVariable@ApiParam(value = "日志ID") String id,@PathVariable@ApiParam(value = "评论ID") String commentId){
		return this.employeeDailyRecordFacade.deleteComment(id,commentId);
	}

	private MapContext createMapContext(String title, String content, String createUserName, String createTime, Integer recordState, Integer visibleState) {
		MapContext mapContext = new MapContext();
		if(title!=null){
			mapContext.put("title",title);
		}
		if(content!=null){
			mapContext.put("content",content);
		}
		if(createUserName!=null){
			mapContext.put("createUserName",createUserName);
		}
		if(createTime!=null){
			mapContext.put("createTime",createTime);
		}
		if(recordState!=null){
			mapContext.put("recordState",recordState);
		}
		if(visibleState!=null){
			mapContext.put("visibleState",visibleState);
		}
		return mapContext;
	}
}
