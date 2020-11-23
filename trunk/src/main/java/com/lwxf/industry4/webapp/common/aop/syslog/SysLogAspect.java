package com.lwxf.industry4.webapp.common.aop.syslog;

import com.alibaba.fastjson.JSON;
import com.lwxf.industry4.webapp.bizservice.system.SysLogService;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;
import com.lwxf.industry4.webapp.domain.entity.system.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog)")
    public void logPoinCut() {
    }

    /**
     * 环绕增强，相当于MethodInterceptor
     */
    @Around("logPoinCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        long time = System.currentTimeMillis();
        try {
            res =  joinPoint.proceed();
            time = System.currentTimeMillis() - time;
            return res;
        } finally {
            try {
                //方法执行完成后增加日志
                saveSysLog(joinPoint,res,time);
            }catch (Exception e){
                System.out.println("LogAspect 操作失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public void saveSysLog(JoinPoint joinPoint,Object res, long time) {
       // System.out.println("切面。。。。。");
        //保存日志
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        SysOperationLog myLog = method.getAnnotation(SysOperationLog.class);
        if (myLog != null) {
            sysLog.setOperation( myLog.detail());
            sysLog.setMouduleCode( myLog.operationMoudule().getValue());
            sysLog.setMouduleName( myLog.operationMoudule().getMouduleName());
            sysLog.setOperationType( myLog.operationType().getValue());
        }
        sysLog.setBranchId(WebUtils.getCurrBranchId());
        sysLog.setOperatorId(WebUtils.getCurrUserId());
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);
        sysLog.setClassName(className);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);
        sysLog.setOperatorId(WebUtils.getCurrUserId());
        sysLog.setCreated(new Date());
        //获取用户名
        LoginedUser currUser = WebUtils.getCurrUser();
        if(currUser==null){
            return;
        }
        sysLog.setOperatorName(WebUtils.getCurrUser().getName());
        if(sysLog.getOperationType().equals("insert")){
            sysLog.setDescription(sysLog.getOperatorName()+"在"+WebUtils.dateNowFomatToStr()+"时创建了一条新的"+sysLog.getMouduleName()+"记录");
        }else if(sysLog.getOperationType().equals("update")){
            sysLog.setDescription(sysLog.getOperatorName()+"在"+WebUtils.dateNowFomatToStr()+"时更新了一条"+sysLog.getMouduleName()+"记录");
        }else if(sysLog.getOperationType().equals("delete")){
            sysLog.setDescription(sysLog.getOperatorName()+"在"+WebUtils.dateNowFomatToStr()+"时删除了一条"+sysLog.getMouduleName()+"记录");
        }else if(sysLog.getOperationType().equals("verify")){
            sysLog.setDescription(sysLog.getOperatorName()+"在"+WebUtils.dateNowFomatToStr()+"时审核了一条"+sysLog.getMouduleName()+"记录");
        }
        //调用service保存SysLog实体类到数据库
        sysLogService.add(sysLog);
    }




}
