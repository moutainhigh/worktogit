package com.lwxf.industry4.webapp.domain.dto.printTable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillItemDto;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBill;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillItem;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/13/013 9:38
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "配送单打印信息",discriminator = "配送单打印信息")
public class DispatchPrintTableDto extends DispatchBill {
	@ApiModelProperty(value = "创建人名称")
	private String creatorName;
	@ApiModelProperty(value = "发货人名称")
	private String delivererName;
	@ApiModelProperty(value = "柜体包裹集合")
	private List<DispatchBillItemDto> bodyDispatchBillItems;
	@ApiModelProperty(value = "门板包裹集合")
	private List<DispatchBillItemDto> doorDispatchBillItems;
	@ApiModelProperty(value = "五金包裹集合")
	private List<DispatchBillItemDto> hardwareDispatchBillItems;


	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getDelivererName() {
		return delivererName;
	}

	public void setDelivererName(String delivererName) {
		this.delivererName = delivererName;
	}

	public List<DispatchBillItemDto> getBodyDispatchBillItems() {
		return bodyDispatchBillItems;
	}

	public void setBodyDispatchBillItems(List<DispatchBillItemDto> bodyDispatchBillItems) {
		this.bodyDispatchBillItems = bodyDispatchBillItems;
	}

	public List<DispatchBillItemDto> getDoorDispatchBillItems() {
		return doorDispatchBillItems;
	}

	public void setDoorDispatchBillItems(List<DispatchBillItemDto> doorDispatchBillItems) {
		this.doorDispatchBillItems = doorDispatchBillItems;
	}

	public List<DispatchBillItemDto> getHardwareDispatchBillItems() {
		return hardwareDispatchBillItems;
	}

	public void setHardwareDispatchBillItems(List<DispatchBillItemDto> hardwareDispatchBillItems) {
		this.hardwareDispatchBillItems = hardwareDispatchBillItems;
	}
}
