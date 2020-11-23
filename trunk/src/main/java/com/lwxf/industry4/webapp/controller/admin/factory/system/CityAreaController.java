package com.lwxf.industry4.webapp.controller.admin.factory.system;

import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.system.CityAreaFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/1/17 9:15
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@Api(value="CityAreaController",tags={"F端后台管理接口:省市区管理"})
@RequestMapping(value = "/api/f",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class CityAreaController {

    @Resource(name = "fcityAreaFacade")
    private CityAreaFacade cityAreaFacade;

    /**
     * 查询所有的地区信息
     * @return
     */
    @GetMapping(value = "/cityareas")
    public RequestResult findAllCityArea(){

        RequestResult allCityArea = this.cityAreaFacade.findAllCityArea();
        return allCityArea;
    }

    /**
     * 查询所有的地区信息
     * @return
     */
    @ApiOperation(value = "查询当前省市区所有信息", notes = "")
    @GetMapping(value = "/citiesInfo/{id}")
    public RequestResult cityAllInfo(@PathVariable String id){

        RequestResult allCityArea = this.cityAreaFacade.findParentCityById(id);
        return allCityArea;
    }

    @GetMapping("/cities/{id}")
    public CityArea findCityAreaById(@PathVariable("id")  String id)
    {
        return  this.cityAreaFacade.findCityAreaById(id);
    }
    @GetMapping("/cities")
    public RequestResult getCityAreaList(@RequestParam(required = false) Integer pageNum,
                                         @RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false)Integer levelType,
                                         @RequestParam(required = false)String parentId,
                                         @RequestParam(required = false)String name)
    {
        if(null == pageSize){
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if(null == pageNum){
            pageNum = 1;
        }
        return  this.cityAreaFacade.selectCityAreaList(pageNum,pageSize,this.createParamsForFindList(name,parentId,levelType));
    }
    private MapContext createParamsForFindList(String name, String parentId, Integer levelType){
        MapContext params = MapContext.newOne();
        if(LwxfStringUtils.isNotBlank(name)){
            params.put(WebConstant.KEY_ENTITY_NAME,name);
        }
        if(LwxfStringUtils.isNotBlank(parentId)){
            params.put(WebConstant.KEY_ENTITY_PARENTID,parentId);
        }
        if(levelType != null){
            params.put(WebConstant.KEY_ENTITY_LEVELTYPE,levelType);
        }
        return params;
    }



}

