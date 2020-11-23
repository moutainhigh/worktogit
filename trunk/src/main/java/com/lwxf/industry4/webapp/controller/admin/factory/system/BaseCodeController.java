package com.lwxf.industry4.webapp.controller.admin.factory.system;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dao.system.BasecodeDao;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.system.BaseCodeFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@Api(value="ActivityController",tags={"F端后台管理接口:字典管理"})
@RestController
@RequestMapping(value = "/api/f/basecodes",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class BaseCodeController {

    @Resource(name = "baseCodeFacade")
    private BaseCodeFacade baseCodeFacade;

    @Resource(name = "basecodeDao")
    private BasecodeDao basecodeDao;

    /**
     * 查询所有字典
     * @return 字典信息列表
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = Basecode.class)
    })
    @ApiOperation(value="获取字典信息",notes="")
    @GetMapping
    public String findBaseCodes(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String type,
                                       @RequestParam(required = false) String code,
                                       @RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false) Integer pageNum) {
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        MapContext mapContent = MapContext.newOne();
        mapContent.put("name",name);
        mapContent.put("type",type);
        if(code !=null && !"".equals(code)){
            mapContent.put("code",code);
        }
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        return jsonMapper.toJson(this.baseCodeFacade.findListBasecodes(mapContent,pageNum,pageSize));
    }


    /**
     * 查询所有字典
     * @return 字典信息列表
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询短信信息", response = Basecode.class)
    })
    @ApiOperation(value="查询短信信息",notes="")
    @GetMapping("/findSmsInfo")
    public RequestResult findSmsInfo(@RequestParam(required = false) String value) {
        //默认的
        String appKey = basecodeDao.findByTypeAndCode(value+"_appKey", value+"_appKey").getValue();
        String appSecret = basecodeDao.findByTypeAndCode(value+"_appSecret", value+"_appSecret").getValue();
        String templateCode = basecodeDao.findByTypeAndCode(value+"_templateCode", value+"_templateCode").getValue();
        String signName = basecodeDao.findByTypeAndCode(value+"_signName", value+"_signName").getValue();

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("appKey",appKey);
        objectObjectHashMap.put("appSecret",appSecret);
        objectObjectHashMap.put("templateCode",templateCode);
        objectObjectHashMap.put("signName",signName);

        return ResultFactory.generateRequestResult(objectObjectHashMap);
    }


    /**
     * 查询所有字典
     * @return 字典信息列表
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "设置短信信息", response = Basecode.class)
    })
    @ApiOperation(value="设置短信信息",notes="")
    @GetMapping("/setSmsInfo")
    public RequestResult setSmsInfo(@RequestParam(required = false) String appKey,
                                    @RequestParam(required = false) String appSecret,
                                    @RequestParam(required = false) String templateCode,
                                    @RequestParam(required = false) String signName,
                                    @RequestParam(required = false) String selectCode) {
        //_appKey

        MapContext mapContext =new MapContext();
        Basecode byTypeAndCode = basecodeDao.findByTypeAndCode(selectCode + "_appKey", selectCode + "_appKey");
        mapContext.put("value",appKey);
        mapContext.put("id",byTypeAndCode.getId());
        basecodeDao.updateByMapContext(mapContext);

        MapContext mapContext1 =new MapContext();
        Basecode byTypeAndCode1 = basecodeDao.findByTypeAndCode(selectCode + "_appSecret", selectCode + "_appSecret");
        mapContext1.put("value",appSecret);
        mapContext1.put("id",byTypeAndCode1.getId());
        basecodeDao.updateByMapContext(mapContext1);

        MapContext mapContext2 =new MapContext();
        Basecode byTypeAndCode2 = basecodeDao.findByTypeAndCode(selectCode + "_templateCode", selectCode + "_templateCode");
        mapContext2.put("value",templateCode);
        mapContext2.put("id",byTypeAndCode2.getId());
        basecodeDao.updateByMapContext(mapContext2);

        MapContext mapContext3 =new MapContext();
        Basecode byTypeAndCode3 = basecodeDao.findByTypeAndCode(selectCode + "_signName", selectCode + "_signName");
        mapContext3.put("value",signName);
        mapContext3.put("id",byTypeAndCode3.getId());
        basecodeDao.updateByMapContext(mapContext3);
        return ResultFactory.generateSuccessResult();
    }

    /**
     * 厂家添加字典
     * @RequestBody 字典数据对象
     * @return
     */
    @ApiOperation(value = "字典添加", notes = "字典添加")
    @PostMapping
    public String addBaseCode(@RequestBody  Basecode Basecode) {
        JsonMapper jsonMapper = new JsonMapper();
        RequestResult requestResult = this.baseCodeFacade.add(Basecode);
        return jsonMapper.toJson(requestResult);
    }

    /**
     * 查询字典详情
     *
     * @return
     */
    @ApiOperation(value = "字典详情", notes = "")
    @GetMapping(value = "/{baseCodeId}")
    public String findById(@PathVariable String baseCodeId) {
        JsonMapper mapper = JsonMapper.createAllToStringMapper();
        RequestResult result = this.baseCodeFacade.findById(baseCodeId);
        return mapper.toJson(result);
    }

    /**
     * 根据字典类型查询数据
     *
     * @return
     */
    @ApiOperation(value = "根据字典类型查询数据", notes = "")
    @GetMapping(value = "/types/{type}")
    public String findByType(@PathVariable String type) {
        JsonMapper mapper = JsonMapper.createAllToStringMapper();
        MapContext map = MapContext.newOne();
        map.put("type",type);
        RequestResult result = this.baseCodeFacade.findListByMap(map);
        return mapper.toJson(result);
    }

    /**
     * @param baseCodeId
     * @param parmas 更新的内容
     * 商家编辑活动（只能修改自己的创建的活动）
     * @return
     */
    @ApiOperation(value="编辑字典",notes="编辑字典")
    @PutMapping(value = "/{baseCodeId}")
    public RequestResult updateBaseCode(@PathVariable String baseCodeId,
                                        @RequestBody MapContext parmas) {
        parmas.put("id",baseCodeId);
        return this.baseCodeFacade.update(baseCodeId,parmas);
    }

    /**
     * 删除字典
     * @param baseCodeId 字典id
     * @return
     */
    @ApiOperation(value="字典删除",notes="字典删除")
    @DeleteMapping(value = "/{baseCodeId}")
    public RequestResult delete(@PathVariable String baseCodeId){
        return this.baseCodeFacade.delete(baseCodeId);
    }

    /**
     * 产品相关字典数据添加
     */
    @ApiOperation(value = "产品相关字典数据添加", notes = "产品相关字典数据添加",response = Basecode.class)
    @PostMapping("/product")
    public String addProductBaseCode(@RequestBody  Basecode Basecode) {
        JsonMapper jsonMapper = new JsonMapper();
        RequestResult requestResult = this.baseCodeFacade.addProductBaseCode(Basecode);
        return jsonMapper.toJson(requestResult);
    }

    /**
     * 获取待报价订单字典数据
     * @return
     */
    @ApiOperation(value = "获取待报价订单字典数据", notes = "获取待报价订单字典数据",response = Basecode.class)
    @GetMapping("/findToPayCode")
    public String findToPayCode() {
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        MapContext map = MapContext.newOne();
        map.put("type","orderStatus");
        //map.put("code", OrderStatus.TO_QUOTED.getValue());
        RequestResult result = this.baseCodeFacade.findByMap(map);

        return jsonMapper.toJson(result);
    }

    /**
     * 获取待接单订单字典数据
     * @return
     */
    @ApiOperation(value = "获取待接单订单字典数据", notes = "获取待接单订单字典数据",response = Basecode.class)
    @GetMapping("/findToReceiveCode")
    public String findToReceiveCode() {
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        MapContext map = MapContext.newOne();
        map.put("type","orderStatus");
        //map.put("code", OrderStatus.TO_RECEIVE.getValue());
        RequestResult result = this.baseCodeFacade.findByMap(map);

        return jsonMapper.toJson(result);
    }

}
