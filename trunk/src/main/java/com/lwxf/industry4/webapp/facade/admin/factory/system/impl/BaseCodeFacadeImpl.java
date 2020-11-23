package com.lwxf.industry4.webapp.facade.admin.factory.system.impl;

import com.lwxf.industry4.webapp.bizservice.branch.BranchService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.system.BaseCodeFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("baseCodeFacade")
public class BaseCodeFacadeImpl extends BaseFacadeImpl implements BaseCodeFacade {

    @Resource(name = "basecodeService")
    private BasecodeService basecodeService;
    @Resource(name = "branchService")
    private BranchService branchService;

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult add(Basecode basecode) {
        basecode.setDelFlag(0);
        return ResultFactory.generateRequestResult(this.basecodeService.add(basecode));
    }

    @Override
    public RequestResult findById(String baseCodeId) {
        return ResultFactory.generateRequestResult(this.basecodeService.findById(baseCodeId));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult update(String basecodeId, MapContext params) {
        RequestResult requestResult = Basecode.validateFields(params);
        if (null != requestResult) {
            return requestResult;
        }
        params.put("id",basecodeId);
        String value=params.getTypedValue("value",String.class);
        String type=params.getTypedValue("type",String.class);
        Integer delFlag=0;
        MapContext mapContext=MapContext.newOne();
        mapContext.put("value",value);
        mapContext.put("type",type);
        mapContext.put("delFlag",delFlag);
        Basecode basecode=this.basecodeService.findByTypeAndValueAndDelFlag(mapContext);
        if(basecode!=null) {
            if (!basecode.getId().equals(basecodeId)) {
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_NOT_ALLOWED_REPEAT, AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
            }
        }
        this.basecodeService.updateByMapContext(params);
        Basecode bc = basecodeService.findById(basecodeId);
        return ResultFactory.generateRequestResult(bc);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult delete(String id) {
        return ResultFactory.generateRequestResult(this.basecodeService.deleteById(id));
    }

    @Override
    public RequestResult findListBasecodes(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        paginatedFilter.setFilters(mapContext);
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        paginatedFilter.setPagination(pagination);
        Map<String,String> created = new HashMap<String, String>();
        created.put(WebConstant.KEY_ENTITY_NAME,"desc");
        List sort = new ArrayList();
        sort.add(created);
        paginatedFilter.setSorts(sort);
        return ResultFactory.generateRequestResult(this.basecodeService.selectByFilter(paginatedFilter));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addProductBaseCode(Basecode basecode) {
        String type=basecode.getType();
        if(type==null){
            return ResultFactory.generateResNotFoundResult();
        }
        List<Basecode> basecodes=this.basecodeService.findByType(type);
        if(basecodes==null||basecodes.size()==0){
            return ResultFactory.generateResNotFoundResult();
        }
        Integer delFlag=0;
        MapContext mapContext=MapContext.newOne();
        mapContext.put("type",type);
        mapContext.put("value",basecode.getValue());
        mapContext.put("delFlag",delFlag);
        Basecode basecode1=this.basecodeService.findByTypeAndValueAndDelFlag(mapContext);
        if(basecode1!=null){
            mapContext.put("remark",basecode.getRemark());
            mapContext.put("id",basecode1.getId());
            this.basecodeService.updateByMapContext(mapContext);
        }else {
            Integer code = basecodes.size();
            Integer num = basecodes.size()+1;
            basecode.setCode(code.toString());
            basecode.setOrderNum(num);
            basecode.setDelFlag(0);
            basecode.setName(basecodes.get(0).getName());
            this.basecodeService.add(basecode);
        }
        return ResultFactory.generateRequestResult(basecode);
    }

    @Override
    public RequestResult findListByMap(MapContext map) {
        return ResultFactory.generateRequestResult(this.basecodeService.selectByFilter(map));
    }

    @Override
    public RequestResult findByMap(MapContext mapContext) {
        List<Basecode> basecodes = this.basecodeService.selectByFilter(mapContext);
        if(basecodes==null||basecodes.size()==0){
            return ResultFactory.generateResNotFoundResult();
        }
        String branchId= WebUtils.getCurrBranchId();
        Branch byId = this.branchService.findById(branchId);
        MapContext result=MapContext.newOne();
        List receive=new ArrayList();
        List beReceive=new ArrayList();
        List toBePay=new ArrayList();
        List bePay=new ArrayList();
        List allStatus=new ArrayList();
        for(Basecode basecode:basecodes){
            String code = basecode.getCode();
            if(code.equals(OrderStatus.TO_RECEIVE.getValue().toString())){
                receive.add(basecode);
            }
            if(!code.equals(OrderStatus.TO_RECEIVE.getValue().toString())&&!code.equals(OrderStatus.DELETE.getValue().toString())){
                beReceive.add(basecode);
            }
            if(code.equals(OrderStatus.TO_QUOTED.getValue().toString())){
                toBePay.add(basecode);
            }
            if(!code.equals(OrderStatus.TO_RECEIVE.getValue().toString())&&!code.equals(OrderStatus.DELETE.getValue().toString())&&!code.equals(OrderStatus.TO_QUOTED.getValue().toString())){
                bePay.add(basecode);
            }
            if(byId.getEnableRemind().toString().equals("0")){
                if(!code.equals(OrderStatus.DELETE.getValue().toString())&&!code.equals(OrderStatus.REMINDING.getValue().toString())){
                    allStatus.add(basecode);
                }
            }
            if(byId.getEnableRemind().toString().equals("1")){
                if(!code.equals(OrderStatus.DELETE.getValue().toString())){
                    allStatus.add(basecode);
                }
            }
        }
        result.put("receive",receive);
        result.put("beReceive",beReceive);
        result.put("toBePay",toBePay);
        result.put("bePay",bePay);
        result.put("allStatus",allStatus);
        return ResultFactory.generateRequestResult(result);
    }
}
