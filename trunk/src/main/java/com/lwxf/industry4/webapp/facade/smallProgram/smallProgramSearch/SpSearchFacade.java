package com.lwxf.industry4.webapp.facade.smallProgram.smallProgramSearch;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/19 0019 14:15
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface SpSearchFacade extends BaseFacade {

	RequestResult FuzzyQuery(String searchType, String branchId, String searchMessage, Integer pageSize, Integer pageNum);
}
