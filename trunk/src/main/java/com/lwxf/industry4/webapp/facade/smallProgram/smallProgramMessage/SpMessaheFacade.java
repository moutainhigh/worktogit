package com.lwxf.industry4.webapp.facade.smallProgram.smallProgramMessage;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMessage;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/21 0021 9:54
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface SpMessaheFacade extends BaseFacade {
	RequestResult findCompanyMessageList(MapContext map, Integer pageNum, Integer pageSize);

	RequestResult findMessagesByFromuser(Integer pageNum, Integer pageSize, MapContext map);

	RequestResult sendCompanyMessage(CompanyMessage companyMessage);

	RequestResult getCompanyList(MapContext mapInfo);
}
