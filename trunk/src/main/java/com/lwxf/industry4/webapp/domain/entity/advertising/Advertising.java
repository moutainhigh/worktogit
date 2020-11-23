package com.lwxf.industry4.webapp.domain.entity.advertising;

import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.TypesExtend;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.annotation.Column;

import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;

/**
 * 功能：advertising 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-03 01:24
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "advertising", displayName = "advertising")
public class Advertising extends IdEntity {
    private static final long serialVersionUID = 1L;
    @Column(type = Types.VARCHAR, length = 50, nullable = false, name = "name", displayName = "名称")
    private String name;
    @Column(type = Types.TINYINT, nullable = false, name = "type", displayName = "文件类型  0图文 1视频")
    private Integer type;
    @Column(type = Types.TINYINT, nullable = false, name = "position", displayName = "位置")
    private Integer position;
    @Column(type = Types.TINYINT, nullable = false, name = "link_type", displayName = "链接类型 0商品 1文章 2活动 3店铺 ")
    private Integer linkType;
    @Column(type = Types.VARCHAR, length = 100, name = "link_path", displayName = "链接的路径")
    private String linkPath;
    @Column(type = Types.BIT, nullable = false, name = "link_status", displayName = "链接状态 0禁用  1启用")
    private Boolean linkStatus;
    @Column(type = Types.VARCHAR, length = 100, nullable = false, name = "img_path", displayName = "图片的路径")
    private String imgPath;
    @Column(type = Types.BIT, nullable = false, name = "is_inner", displayName = "是否内联  1内联 0外联")
    private Boolean inner;
    @Column(type = Types.CHAR, length = 13, nullable = false, updatable = false, name = "creator", displayName = "创建人")
    private String creator;
    @Column(type = TypesExtend.DATETIME, nullable = false, updatable = false, name = "created", displayName = "创建时间")
    private Date created;

    public Advertising() {
    }

    public RequestResult validateFields() {
        Map<String, String> validResult = new HashMap<>();
        if (this.name == null) {
            validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        } else {
            if (LwxfStringUtils.getStringLength(this.name) > 50) {
                validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (this.type == null) {
            validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (this.position == null) {
            validResult.put("position", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (this.linkType == null) {
            validResult.put("linkType", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (LwxfStringUtils.getStringLength(this.linkPath) > 100) {
            validResult.put("linkPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
        }
        if (this.linkStatus == null) {
            validResult.put("linkStatus", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (this.imgPath == null) {
            validResult.put("imgPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        } else {
            if (LwxfStringUtils.getStringLength(this.imgPath) > 100) {
                validResult.put("imgPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (this.inner == null) {
            validResult.put("inner", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (this.creator == null) {
            validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        } else {
            if (LwxfStringUtils.getStringLength(this.creator) > 13) {
                validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (this.created == null) {
            validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (validResult.size() > 0) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
        } else {
            return null;
        }
    }

    private final static List<String> propertiesList = Arrays.asList("name", "type", "position", "linkType", "linkPath", "linkStatus", "imgPath", "inner");

    public static RequestResult validateFields(MapContext map) {
        Map<String, String> validResult = new HashMap<>();
        if (map.size() == 0) {
            return ResultFactory.generateErrorResult("error", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        boolean flag;
        Set<String> mapSet = map.keySet();
        flag = propertiesList.containsAll(mapSet);
        if (!flag) {
            return ResultFactory.generateErrorResult("error", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
        }
        if (map.containsKey("type")) {
            if (!DataValidatorUtils.isInteger1(map.getTypedValue("type", String.class))) {
                validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("position")) {
            if (!DataValidatorUtils.isInteger1(map.getTypedValue("position", String.class))) {
                validResult.put("position", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("linkType")) {
            if (!DataValidatorUtils.isInteger1(map.getTypedValue("linkType", String.class))) {
                validResult.put("linkType", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("linkStatus")) {
            if (!DataValidatorUtils.isBoolean(map.getTypedValue("linkStatus", String.class))) {
                validResult.put("linkStatus", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("inner")) {
            if (!DataValidatorUtils.isBoolean(map.getTypedValue("inner", String.class))) {
                validResult.put("inner", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("name")) {
            if (map.getTypedValue("name", String.class) == null) {
                validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            } else {
                if (LwxfStringUtils.getStringLength(map.getTypedValue("name", String.class)) > 50) {
                    validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
                }
            }
        }
        if (map.containsKey("type")) {
            if (map.get("type") == null) {
                validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
        }
        if (map.containsKey("position")) {
            if (map.get("position") == null) {
                validResult.put("position", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
        }
        if (map.containsKey("linkType")) {
            if (map.get("linkType") == null) {
                validResult.put("linkType", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
        }
        if (map.containsKey("linkPath")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("linkPath", String.class)) > 100) {
                validResult.put("linkPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (map.containsKey("linkStatus")) {
            if (map.get("linkStatus") == null) {
                validResult.put("linkStatus", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
        }
        if (map.containsKey("imgPath")) {
            if (map.getTypedValue("imgPath", String.class) == null) {
                validResult.put("imgPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            } else {
                if (LwxfStringUtils.getStringLength(map.getTypedValue("imgPath", String.class)) > 100) {
                    validResult.put("imgPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
                }
            }
        }
        if (map.containsKey("inner")) {
            if (map.get("inner") == null) {
                validResult.put("inner", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
        }
        if (validResult.size() > 0) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
        } else {
            return null;
        }
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    public Integer getLinkType() {
        return linkType;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }

    public String getLinkPath() {
        return linkPath;
    }

    public void setLinkStatus(Boolean linkStatus) {
        this.linkStatus = linkStatus;
    }

    public Boolean getLinkStatus() {
        return linkStatus;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setInner(Boolean inner) {
        this.inner = inner;
    }

    public Boolean getInner() {
        return inner;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCreated() {
        return created;
    }
}
