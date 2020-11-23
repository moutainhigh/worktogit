package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.bizservice.system.SysExceptionService;
import com.lwxf.industry4.webapp.domain.entity.system.SysException;
import com.lwxf.mybatis.utils.MapContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Resource(name = "sysExceptionService")
    private SysExceptionService sysExceptionService;
    /**
     * 处理 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String apiExceptionHandler(HttpServletRequest httpServletRequest,Exception e) {
        logger.error("ApiException 异常抛出：{}", e);
        JsonMapper jsonMapper = new JsonMapper();
        MapContext map= MapContext.newOne();
        map.put("api",httpServletRequest.getMethod());
        map.put("msg","操作失败");
        map.put("exception",e.getMessage());
        SysException exception =  new SysException();
        exception.setContent(e.getMessage());
        exception.setCreated(new Date());
        exception.setPostType(httpServletRequest.getMethod());
        exception.setUrl(httpServletRequest.getRequestURI());
        exception.setParam("");
        sysExceptionService.add(exception);
        return jsonMapper.toJson(map);
    }

}
