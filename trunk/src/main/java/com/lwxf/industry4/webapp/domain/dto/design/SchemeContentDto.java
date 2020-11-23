package com.lwxf.industry4.webapp.domain.dto.design;

import com.lwxf.industry4.webapp.domain.entity.design.SchemeContent;

import java.util.List;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/3/15 15:40
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class SchemeContentDto extends SchemeContent {

    private List<String> paths;

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }
}
