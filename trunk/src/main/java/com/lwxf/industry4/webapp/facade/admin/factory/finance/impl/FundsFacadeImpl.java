package com.lwxf.industry4.webapp.facade.admin.factory.finance.impl;

import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.financing.FundsService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.financing.FundsDto;
import com.lwxf.industry4.webapp.domain.entity.financing.Funds;
import com.lwxf.industry4.webapp.facade.admin.factory.finance.FundsFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.DateUtil;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/9/25 0025 14:17
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("fundsFacade")
public class FundsFacadeImpl extends BaseFacadeImpl implements FundsFacade {
	@Resource(name = "fundsService")
	private FundsService fundsService;
	@Override
	public RequestResult findFundsList(MapContext mapContext) {
		//查询一级
		mapContext.put("grade",0);
		List<FundsDto> fundsList=this.fundsService.findFundsList(mapContext);
		//查询二级
		if(fundsList!=null&&fundsList.size()>0) {
			for (FundsDto fundsDto : fundsList) {
				String id = fundsDto.getId();
				List<FundsDto> twofunds = this.fundsService.findByParentId(id);
				if(twofunds!=null&&twofunds.size()>0) {
					for (FundsDto funds1 : twofunds) {//查询三级
						id = funds1.getId();
						List<FundsDto> threefunds = this.fundsService.findByParentId(id);
						funds1.setFunds(threefunds);
					}
				}
				fundsDto.setFunds(twofunds);
			}
		}
		return ResultFactory.generateRequestResult(fundsList);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addFunds(Funds funds) {
		String parentId=funds.getParentId();
		if(parentId==null||parentId.equals("")){//一级科目
			funds.setGrade(0);
		}
		else {
			Funds funds1=this.fundsService.findById(parentId);
			if(funds1.getGrade()==0){
				funds.setGrade(1);
			}else {
				funds.setGrade(2);
			}
		}
		funds.setStatus(0);
		funds.setCreator(WebUtils.getCurrUserId());
		funds.setCreated(DateUtil.now());
		funds.setBranchId(WebUtils.getCurrBranchId());
		this.fundsService.add(funds);
		return ResultFactory.generateRequestResult(funds);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateFunds(MapContext mapContext) {
		return ResultFactory.generateRequestResult(this.fundsService.updateByMapContext(mapContext));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteFunds(String id) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("id",id);
		mapContext.put("status",1);
		this.fundsService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}
}
