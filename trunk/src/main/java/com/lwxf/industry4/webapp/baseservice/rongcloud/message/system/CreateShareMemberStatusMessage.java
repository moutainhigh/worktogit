package com.lwxf.industry4.webapp.baseservice.rongcloud.message.system;

import java.util.Map;

import com.lwxf.industry4.webapp.baseservice.rongcloud.message.MessageCategory;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.MessageResource;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.MessageType;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.ResourceOperation;
import com.lwxf.industry4.webapp.common.utils.LwxfAssert;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyShareMember;
import com.lwxf.industry4.webapp.domain.entity.company.StoreConfig;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.MessageResource;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.MessageType;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.ResourceOperation;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/10/11 19:03
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class CreateShareMemberStatusMessage extends AbstractSystemMessage {
    protected static final String CONTENT_TEXT = "您的身份审核有新消息了！";
    protected static final String MSG_KEY_EXTRA_COMPANY_SHARE_MEMBER ="companyShareMember";

    private CompanyShareMember companyShareMember;

    public CreateShareMemberStatusMessage(StoreConfig storeConfig,CompanyShareMember companyShareMember,User creator) {
        LwxfAssert.notNull(null,"该方法还没实现");
        /*this.storeConfig = storeConfig;
        this.companyShareMember = companyShareMember;
        this.creator = creator;
        this.setType(MessageType.USER_IDENTITY);
        this.setOperation(ResourceOperation.UPDATE);
        this.setResource(MessageResource.USERS);

        // 必设
        this.setContentText(CONTENT_TEXT);*/
    }

    @Override
    protected void setExtraProperInfo(Map<String, Object> extra) {
        extra.put(MSG_KEY_EXTRA_COMPANY_SHARE_MEMBER,this.companyShareMember);
    }
}

