package com.lwxf.industry4.webapp.controller.admin.factory.customOrder;

import io.swagger.annotations.*;

import javax.annotation.Resource;

import java.time.Year;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStockItem;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.OrderFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：订单打包包装
 *
 * @author：Administrator
 * @create：2019/4/18/018 17:16
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/customorders",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "OrderPackController",tags = "F端后台管理接口：订单打包管理")
public class OrderPackController {

	@Resource(name="orderFacade")
	private OrderFacade orderFacade;

	/**
	 * 订单下打包包裹
	 * @param id
	 * @param finishedStockItemDtoList
	 * @return
	 */
	@PostMapping("/{id}/{type}")
	@ApiOperation(value = "打包包裹",notes = "打包包裹")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "订单主键ID",name = "id",dataType = "string",paramType = "path"),
			@ApiImplicitParam(value = "资源类型 0 订单 1 售后单",name = "type",dataType = "int",paramType = "path")
	})
	private RequestResult addOrderPack(@PathVariable String id,@PathVariable int type,@RequestBody@ApiParam(value = "包裹") List<FinishedStockItemDto> finishedStockItemDtoList){
		//判断包裹数量是否为空
		if(finishedStockItemDtoList.size()==0){
			return ResultFactory.generateResNotFoundResult();
		}
		for (FinishedStockItemDto finishedStockItem : finishedStockItemDtoList) {
			finishedStockItem.setBranchId(WebUtils.getCurrBranchId());
			//暂时赋值此值 验证使用 后面 会赋值 会覆盖此值
			finishedStockItem.setFinishedStockId("aa");
			finishedStockItem.setShipped(false);
			finishedStockItem.setOperator(finishedStockItem.getCreator());//入库人为包裹创建
			finishedStockItem.setOperated(finishedStockItem.getCreated());//入库时间为包裹创建时间
			finishedStockItem.setIn(true);//默认已入库
			RequestResult result = finishedStockItem.validateFields();
			if(result!=null){
				return result;
			}
		}
		return this.orderFacade.addOrderPack(id,finishedStockItemDtoList,type);
	}

	/**
	 * 订单打包完成
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "订单打包完成",notes = "订单打包完成")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "订单主键ID",name = "id",dataType = "string",paramType = "path")
	})
	@PutMapping("/{id}/packed")
	private RequestResult orderPacked(@PathVariable String id){
		return this.orderFacade.orderPacked(id);
	}

	/**
	 * 查询当前订单的下一个包裹编号
	 * @param prodId
	 * @return
	 */
	@GetMapping("/no/{prodId}")
	@ApiOperation(value = "查询当前订单的下一个包裹编号",notes = "查询当前订单的下一个包裹编号")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "产品Id",name = "prodId",dataType = "string",paramType = "path"),
			@ApiImplicitParam(value = "包裹类型,A 柜体 B 门板 BZ 自产 BW 外协 BT 特供实木 C 五金",name = "type",dataType = "string",paramType = "query"),
			@ApiImplicitParam(value = "数量",name = "count",dataType = "int",paramType = "query")
	})
	private RequestResult findOrderPackagesNo(@PathVariable String prodId,@RequestParam(required = false)String type,@RequestParam(required = false)Integer count){
		return this.orderFacade.findOrderPackagesNo(prodId,type,count);
	}

	private MapContext createMapContext(String orderNo) {
		MapContext mapContext = new MapContext();
		if(orderNo!=null){
			mapContext.put("orderNo",orderNo);
		}
		return mapContext;
	}
}
