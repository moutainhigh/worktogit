package com.lwxf.industry4.webapp.controller.admin.factory.erpbigdata;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.ErpBigDataStatementFacade;
import com.lwxf.industry4.webapp.facade.wxapi.factory.statements.DealerStatementFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/30 0030 9:28
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="ErpBigDataStatementController",tags={"F端Erp大数据报表接口:F端Erp大数据报表接口"})
@RestController("ErpBigDataStatementController")
@RequestMapping(value = "/api/f/ErpBigData",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ErpBigDataStatementController {

	@Resource(name = "erpBigDataStatementFacade")
	private ErpBigDataStatementFacade erpBigDataStatementFacade;

	/**
	 * 经销商数据
	 * 签约 意向 即将到期 已到期
	 */
	@ApiOperation(value = "经销商数据",notes = "经销商数据" )
	@GetMapping("/dealerDate")
	private RequestResult dealerDate(@RequestParam(required = false) String refresh) {
        Map map = erpBigDataStatementFacade.queryBigdData(refresh);
        return ResultFactory.generateRequestResult(map);
    }











}
