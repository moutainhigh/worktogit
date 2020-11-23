package com.lwxf.industry4.webapp.bizservice.statements.app.factory.designStatement.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.designStatement.DesignStatementService;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.designStatement.DesignStatementDao;
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
 * @create：2019/6/30 0030 10:56
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("designStatementService")
public class DesignStatementServiceImpl extends BaseNoIdServiceImpl implements DesignStatementService {
	@Resource
	private DesignStatementDao designStatementDao;
	@Override
	public void setDao(BaseNoIdDao dao) {
		this.dao=designStatementDao;
	}

	@Override
	public int add(Object entity) {
		return 0;
	}

	@Override
	public CountByWeekDto findDesignStatementByWeek(MapContext map) {
		return designStatementDao.findDesignStatementByWeek(map);
	}

	@Override
	public CountByMonthDto findDesignStatementByMonth(MapContext mapContext) {
		return designStatementDao.findDesignStatementByMonth(mapContext);
	}

	@Override
	public CountByQuarterDto findDesignStatementByQuarter(MapContext mapContext) {
		return designStatementDao.findDesignStatementByQuarter(mapContext);
	}

	@Override
	public CountByYearDto findDesignStatementByYear(MapContext mapContext) {
		return designStatementDao.findDesignStatementByYear(mapContext);
	}

	@Override
	public DateNum findDesignStatements(MapContext mapContext) {
		return designStatementDao.findDesignStatements(mapContext);
	}


}
