package com.lwxf.industry4.webapp.bizservice.statements.app.factory.produce.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.produce.ProduceStatementService;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.produce.ProduceStatementDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/3 0003 16:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("produceStatementService")
public class ProduceStatementServiceImpl extends BaseNoIdServiceImpl implements ProduceStatementService {
	@Resource
	private ProduceStatementDao produceStatementDao;
	@Override
	public void setDao(BaseNoIdDao dao) {
		this.dao=produceStatementDao;
	}

	@Override
	public int add(Object entity) {
		return 0;
	}

	@Override
	public CountByWeekDto findProduceStatementByWeek(String branchId, String countType) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		return produceStatementDao.findProduceStatementByWeek(mapContext);
	}

	@Override
	public CountByMonthDto findProduceStatementByMonth(MapContext mapContext) {
		return produceStatementDao.findProduceStatementByMonth(mapContext);
	}

	@Override
	public CountByQuarterDto findProduceStatementByQuarter(MapContext mapContext) {
		return produceStatementDao.findProduceStatementByQuarter(mapContext);
	}

	@Override
	public CountByYearDto findProduceStatementByYear(String branchId, String countType) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		return produceStatementDao.findProduceStatementByYear(mapContext);
	}

	@Override
	public DateNum findProduceStatements(MapContext mapContext) {
		return produceStatementDao.findProduceStatements(mapContext);
	}

	@Override
	public MapContext orderProduceCount(MapContext mapContext) {
		return produceStatementDao.findOrderProduceCount(mapContext);
	}

	@Override
	public List<MapContext> orderProduceTypeCountByCondition(MapContext mapContext) {
		return produceStatementDao.orderProduceTypeCountByCondition(mapContext);
	}

	@Override
	public Integer orderProduceDelayByDay(MapContext mapContext) {
		return produceStatementDao.orderProduceDelayByDay(mapContext);
	}
}
