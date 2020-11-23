package com.lwxf.industry4.webapp.facade.admin.factory.system.impl;

import com.lwxf.industry4.webapp.bizservice.dept.DeptMemberService;
import com.lwxf.industry4.webapp.bizservice.system.SysMessageReciversService;
import com.lwxf.industry4.webapp.bizservice.system.SysMessageService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.system.SysMessageReciversDto;
import com.lwxf.industry4.webapp.domain.entity.system.SysMessage;
import com.lwxf.industry4.webapp.domain.entity.system.SysMessageRecivers;
import com.lwxf.industry4.webapp.facade.admin.factory.system.SysMessageFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Component("sysMessageFacade")
public class SysMessageFacadeImpl extends BaseFacadeImpl implements SysMessageFacade {

    @Resource(name = "sysMessageService")
    private SysMessageService sysMessageService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "sysMessageReciversService")
    private SysMessageReciversService sysMessageReciversService;
    @Resource(name = "deptMemberService")
    private DeptMemberService deptMemberService;

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addMessage(SysMessage sysMessage) {
        return ResultFactory.generateRequestResult(this.sysMessageService.add(sysMessage));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult sendMessage(String messageId) {
        SysMessage message = sysMessageService.findById(messageId);
        if(message.getType()==1){  //群发所有工厂用户
            List<String> listUsers = userService.findUserIdByType(message.getBranchId(),1);
            for(String userId:listUsers){
                SysMessageRecivers smr = new SysMessageRecivers();
                smr.setMessageId(messageId);
                smr.setUserId(userId);
                smr.setCreated(new Date());
                sysMessageReciversService.add(smr);
            }
        }else if(message.getType()==2){ //群发所有经销商用户
            List<String> listUsers = userService.findUserIdByType(message.getBranchId(),2);
            for(String userId:listUsers){
                SysMessageRecivers smr = new SysMessageRecivers();
                smr.setMessageId(messageId);
                smr.setUserId(userId);
                smr.setCreated(new Date());
                sysMessageReciversService.add(smr);
            }
        }else if(message.getType()==3){ //按部门群
            String[] deptIds = message.getDeptIds().split(",");
            for(String deptid: deptIds){
                List<String> listUsers=deptMemberService.selectUserIdByDeptId(message.getDeptIds());
                for(String userId : listUsers){
                    SysMessageRecivers smr = new SysMessageRecivers();
                    smr.setMessageId(messageId);
                    smr.setUserId(userId);
                    smr.setCreated(new Date());
                    sysMessageReciversService.add(smr);
                }
            }
        }else if(message.getType()==4){ //按用户id发
            for(String userId : message.getReciverIds().split(",")){
                SysMessageRecivers smr = new SysMessageRecivers();
                smr.setMessageId(messageId);
                smr.setUserId(userId);
                smr.setCreated(new Date());
                sysMessageReciversService.add(smr);
            }
        }
        //已发送
        MapContext map = MapContext.newOne();
        map.put("id",messageId);
        map.put("stauts","1");
        sysMessageService.updateByMapContext(map);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult findMessageById(String messageId) {
        return ResultFactory.generateRequestResult(this.sysMessageService.findById(messageId));
    }

    @Override
    public RequestResult findMessageByMap(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        paginatedFilter.setFilters(mapContext);
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        paginatedFilter.setPagination(pagination);
        Map<String,String> created = new HashMap<String, String>();
        created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
        List sort = new ArrayList();
        sort.add(created);
        paginatedFilter.setSorts(sort);
        return ResultFactory.generateRequestResult(this.sysMessageService.selectByFilter(paginatedFilter));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateMessage(String messageId, MapContext params) {
        RequestResult requestResult = SysMessage.validateFields(params);
        if (null != requestResult) {
            return requestResult;
        }
        params.put("id",messageId);
        this.sysMessageService.updateByMapContext(params);
        SysMessage bc = sysMessageService.findById(messageId);
        return ResultFactory.generateRequestResult(bc);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult delete(String id) {
        return ResultFactory.generateRequestResult(this.sysMessageService.deleteById(id));
    }

    @Override
    public RequestResult myMessageList(String userId) {
        List<SysMessageReciversDto> list = sysMessageReciversService.selectMessageByUserId(userId);
        return ResultFactory.generateRequestResult(list);
    }

    @Override
    public RequestResult myMessage(String messageId, String userId) {
        SysMessageRecivers message = sysMessageReciversService.selectByUserIdAndMessageId(messageId,userId);
        return ResultFactory.generateRequestResult(message);
    }
}
