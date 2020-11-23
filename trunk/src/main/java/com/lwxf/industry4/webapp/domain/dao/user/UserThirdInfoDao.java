package com.lwxf.industry4.webapp.domain.dao.user;


import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;


@IBatisSqlTarget
public interface UserThirdInfoDao extends BaseNoIdDao<UserThirdInfo> {

	List<UserThirdInfo> selectByWxOpenId(String wxOpenId);

	List<String> selectUserIdsBysWxOpenId(String wxOpenId);

	List<UserThirdInfo> selectByWxUnionId(String wxUnionId);

	void deleteByOpenId(String wxOpenId);

	void deleteByUserId(String userId);

	List<UserThirdInfo> findAllWithNotBlankWxOpenId();

	List<UserThirdInfo> findByUserIds(List<String> userIdList);

	UserThirdInfo selectByUserId(String userId);

	void updateByWxOpenId(MapContext mapContext);

	List <UserThirdInfo> findAllClerks(String companyId);

	UserThirdInfo selectByOpenId(String wxOpenId);

	List<UserThirdInfo> findByAppTokenAndUserId(String appToken,String userId);

	UserThirdInfo findByAppToken(String appToken);

	Object userlogout(MapContext mapContext);

    UserThirdInfo findUserByunionid(String unionid);
}