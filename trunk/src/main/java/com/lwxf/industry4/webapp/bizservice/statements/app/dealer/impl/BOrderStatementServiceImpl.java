package com.lwxf.industry4.webapp.bizservice.statements.app.dealer.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.statements.app.dealer.BOrderStatementService;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dao.statements.dealer.BOrderStatementDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/4 0004 10:34
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("bOrderStatementService")
public class BOrderStatementServiceImpl extends BaseNoIdServiceImpl implements BOrderStatementService {
	@Resource
	private BOrderStatementDao bOrderStatementDao;
	@Override
	public void setDao(BaseNoIdDao dao) {
		this.dao=bOrderStatementDao;
	}

	@Override
	public int add(Object entity) {
		return 0;
	}

	@Override
	public CountByWeekDto findOrderStatementByWeek(String companyId, String countType) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("companyId",companyId);
		mapContext.put("countType",countType);
		return bOrderStatementDao.findOrderStatementByWeek(mapContext);
	}

	@Override
	public MapContext findOrderStatementType(MapContext map) {
		return bOrderStatementDao.findOrderStatementType(map);
	}

	@Override
	public CountByMonthDto findOrderStatementByMonth(MapContext mapContext) {
		return bOrderStatementDao.findOrderStatementByMonth(mapContext);
	}

	@Override
	public CountByQuarterDto findOrderStatementByQuarter(MapContext mapContext) {
		return bOrderStatementDao.findOrderStatementByQuarter(mapContext);
	}

	@Override
	public CountByYearDto findOrderStatementByYear(String companyId, String countType) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("companyId",companyId);
		mapContext.put("countType",countType);
		return bOrderStatementDao.findOrderStatementByYear(mapContext);
	}

	@Override
	public DateNum findOrderStatements(MapContext mapContext) {
		return bOrderStatementDao.findOrderStatements(mapContext);
	}
}
