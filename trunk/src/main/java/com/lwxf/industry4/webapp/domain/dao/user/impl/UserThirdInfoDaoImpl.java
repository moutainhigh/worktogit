package com.lwxf.industry4.webapp.domain.dao.user.impl;



import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.user.UserThirdInfoDao;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userThirdInfoDao")
public class UserThirdInfoDaoImpl extends BaseNoIdDaoImpl<UserThirdInfo> implements UserThirdInfoDao {

	@Override
	public List<UserThirdInfo> selectByWxOpenId(String wxOpenId) {
		String sqlId = this.getNamedSqlId("selectByWxOpenId");
		//
		return this.getSqlSession().selectList(sqlId, wxOpenId);
	}

	@Override
	public List<String> selectUserIdsBysWxOpenId(String wxOpenId) {
		String sqlId = this.getNamedSqlId("selectUserIdsBysWxOpenId");
		return this.getSqlSession().selectList(sqlId, wxOpenId);
	}

	@Override
	public List<UserThirdInfo> selectByWxUnionId(String wxUnionId) {
		String sqlId = this.getNamedSqlId("selectByWxUnionId");
		//
		return this.getSqlSession().selectList(sqlId, wxUnionId);
	}

	@Override
	public void deleteByOpenId(String openId) {
		String sqlId = this.getNamedSqlId("deleteByOpenId");
		this.getSqlSession().delete(sqlId, openId);
	}

	@Override
	public void deleteByUserId(String userId) {
		String sqlId = this.getNamedSqlId("deleteByUserId");
		this.getSqlSession().delete(sqlId, userId);
	}

	@Override
	public List<UserThirdInfo> findAllWithNotBlankWxOpenId() {
		String sqlId = this.getNamedSqlId("findAllWithNotBlankWxOpenId");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public List<UserThirdInfo> findByUserIds(List<String> userIdList) {
		String sqlId = this.getNamedSqlId("findByUserIds");
		return this.getSqlSession().selectList(sqlId, userIdList);
	}

	@Override
	public UserThirdInfo selectByUserId(String userId) {
		String sqlId = this.getNamedSqlId("selectByUserId");
		return this.getSqlSession().selectOne(sqlId, userId);
	}

	@Override
	public void updateByWxOpenId(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("updateByWxOpenId");
		this.getSqlSession().update(sqlId, mapContext);
	}

	@Override
	public List<UserThirdInfo> findAllClerks(String companyId) {
		String sqlId = this.getNamedSqlId("findAllClerks");
		return this.getSqlSession().selectList(sqlId,companyId);
	}

	@Override
	public UserThirdInfo selectByOpenId(String wxOpenId) {
		String sqlId = this.getNamedSqlId("selectByOpenId");
		return this.getSqlSession().selectOne(sqlId, wxOpenId);
	}

	@Override
	public List<UserThirdInfo> findByAppTokenAndUserId(String appToken,String userId) {
		String sqlId = this.getNamedSqlId("findByAppTokenAndUserId");
		MapContext map = MapContext.newOne();
		map.put("appToken",appToken);
		map.put("userId",userId);
		return this.getSqlSession().selectList(sqlId,map);
	}

	@Override
	public UserThirdInfo findByAppToken(String appToken) {
		String sqlId = this.getNamedSqlId("findByAppToken");
		MapContext map = MapContext.newOne();
		map.put("appToken",appToken);
		return this.getSqlSession().selectOne(sqlId,map);
	}

	@Override
	public Object userlogout(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("userlogout");
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public UserThirdInfo findUserByunionid(String unionid) {
		String sqlId = this.getNamedSqlId("findUserByunionid");
		MapContext map = MapContext.newOne();
		map.put("unionid",unionid);
		return this.getSqlSession().selectOne(sqlId,map);
	}
}