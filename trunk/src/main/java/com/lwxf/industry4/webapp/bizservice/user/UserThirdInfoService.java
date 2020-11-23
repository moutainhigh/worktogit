package com.lwxf.industry4.webapp.bizservice.user;


import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdService;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;


public interface UserThirdInfoService extends BaseNoIdService<UserThirdInfo> {
	void bindingWeixin(String orgUserId, UserThirdInfo weixinInfo);

	void unbindWeixinByUserId(String userId);
	List<UserThirdInfo> findByWxOpenId(String wxOpenId);
	List<UserThirdInfo> findByWxUnionId(String wxUnionId);
	UserThirdInfo findByUserId(String userId);

	List<UserThirdInfo> findAllWithNotBlankWxOpenId();

	List<UserThirdInfo> findByUserIds(List<String> userIdList);

	void unSubscribeByWxOpenId(String wxOpenId);

	int updateByMapContext(MapContext saveMap);

	void updateByWxOpenId(MapContext mapContext);

	List<UserThirdInfo> findClerks(List<String> strList);

	List<UserThirdInfo> findAllClerks(String companyId);

	UserThirdInfo findByOpenId(String wxOpenId);

	void deleteByOppenId(String wxOppenId);

	List<UserThirdInfo> findByAppTokenAndUserId(String appToken,String userId);

	UserThirdInfo findByAppToken(String appToken);


	Object userlogout(MapContext mapContext);

    UserThirdInfo findUserByunionid(String unionid);
}