package com.lwxf.industry4.webapp.controller.path;

import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.common.AppFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 9:17:31
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IEA
 */
@Controller
public class AdminController extends LoadBaseCfgAndSysCfgController {
    private static final String PAGE_FADMIN = "fadmin";
    @Resource(name = "appFacade")
    private AppFacade appFacade;

    @Override
    protected Map<String, Object> loadUserPreload(HttpServletRequest request) {
        String userId = WebUtils.getCurrUserId();
        if (null == userId) {
            return new HashMap<>();
        }
        // 返回预加载数据
        Map<String, Object> userPreloadData = (Map<String, Object>) AppBeanInjector.userFacade.findUserPreloadData(userId).getData();
        // 加载商城的预加载数据
        this.appFacade.loadMallPreloadData(userPreloadData);
        return userPreloadData;
    }

    // 工厂管理页面
    @GetMapping("/fadmin")
    public String goToAdmin()
    {
        return WebUtils.getAdminsPagePath(PAGE_FADMIN);
    }
}
