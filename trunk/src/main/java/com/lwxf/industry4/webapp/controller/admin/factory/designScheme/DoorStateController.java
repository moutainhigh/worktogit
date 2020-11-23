package com.lwxf.industry4.webapp.controller.admin.factory.designScheme;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.design.DoorState;
import com.lwxf.industry4.webapp.facade.admin.factory.design.DoorStateFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/3/29/029 13:13
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/designs/doors",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DoorStateController {

	@Resource(name = "doorStateFacade")
	private DoorStateFacade doorStateFacade;

	/**
	 * 查询户型列表
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping
	private RequestResult findDoorList(@RequestParam(required = false) String name,@RequestParam(required = false,defaultValue = "1") Integer pageNum,
									   @RequestParam(required = false,defaultValue = "10") Integer pageSize){
		return this.doorStateFacade.findDoorList(name,pageNum,pageSize);
	}


	/**
	 * 新增户型
	 * @param doorState
	 * @return
	 */
	@PostMapping
	private RequestResult addDoorState(@RequestBody DoorState doorState){
		RequestResult result = doorState.validateFields();
		if(result!=null){
			return result;
		}
		return this.doorStateFacade.addDoorState(doorState);
	}

	/**
	 * 修改户型
	 * @param id
	 * @param mapContext
	 * @return
	 */
	@PutMapping("/{id}")
	private RequestResult updateDoorState(@PathVariable String id, @RequestBody MapContext mapContext){
		RequestResult result = DoorState.validateFields(mapContext);
		if (result!=null){
			return result;
		}
		return this.doorStateFacade.updateDoorState(id,mapContext);
	}

	/**
	 * 删除户型
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	private RequestResult deleteDoorState(@PathVariable String id){
		return this.doorStateFacade.deleteDoorState(id);
	}
}
