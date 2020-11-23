package com.lwxf.industry4.webapp.domain.dao.user.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.user.UserDao;
import com.lwxf.industry4.webapp.domain.dto.user.UserAreaDto;
import com.lwxf.industry4.webapp.domain.dto.user.UserDto;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 功能：缓存示例实现
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 15:03:33
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {
	@Override
    public User selectByEmail(String email) {
        String sqlId = this.getNamedSqlId("selectByEmail");
        return this.getSqlSession().selectOne(sqlId, email);
    }

	@Override
	public User findByMobile(String mobile) {
		String sqlId = this.getNamedSqlId("findByMobile");
		return this.getSqlSession().selectOne(sqlId, mobile);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public PaginatedList<User> selectByFilter(PaginatedFilter paginatedFilter) {
        String sqlId = this.getNamedSqlId("selectByFilter");
        PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
        PageList<User> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
        return this.toPaginatedList(pageList);
    }

    @Override
	public List<User> selectByUserIdList(List<String> userIdList) {
		String sqlId = this.getNamedSqlId("selectByUserIdList");
        return this.getSqlSession().selectList(sqlId, userIdList);
    }

    @Override
	public List<UserDto> selectUserDtoListByUserIds(String[] userIds) {
		String sqlId = this.getNamedSqlId("selectUserDtoListByUserIds");
        return this.getSqlSession().selectList(sqlId, userIds);
    }

    @Override
    public boolean isExistsByEmail(String email) {
        String sqlId = this.getNamedSqlId("isExistsByEmail");
        return this.getSqlSession().selectOne(sqlId, email);
    }

    @Override
	public User selectByUserId(String userId) {
		String sqlId = this.getNamedSqlId("selectByUserId");
        return this.getSqlSession().selectOne(sqlId, userId);
    }

    @Override
	public List<String> selectAllUserId() {
		String sqlId = this.getNamedSqlId("selectAllUserId");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public User findByLoginName(String loginName) {
		String sqlId = this.getNamedSqlId("findByLoginName");
		return this.getSqlSession().selectOne(sqlId,loginName);
	}

	@Override
	public List<User> findUserInfoByUserIds(Collection<String> id) {
		String sqlId = this.getNamedSqlId("findUserInfoByUserIds");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public int updateUserName(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("updateUserName");
		return this.getSqlSession().update(sqlId, mapContext);
	}

	@Override
	public Map<String, Object> findUserById(String id) {
		String sqlId = this.getNamedSqlId("findUserById");
		return this.getSqlSession().selectOne(sqlId,id);
	}



	@Override
	public UserAreaDto selectUserAreaDtoById(String id) {
		String sqlId = this.getNamedSqlId("selectUserAreaDtoById");
		return this.getSqlSession().selectOne(sqlId,id);
	}



	@Override
	public List<User> selectCompanyShopkeeperByCompanyIds(List ids,String roleKey) {
		String sqlId = this.getNamedSqlId("selectCompanyShopkeeperByCompanyIds");
		MapContext mapContext = new MapContext();
		mapContext.put("ids",ids);
		mapContext.put("key",roleKey);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		return this.getSqlSession().selectList(sqlId,mapContext);
	}


	@Override
	public UserAreaDto findUserMessageById(String userId) {
		String sqlId = this.getNamedSqlId("findUserMessageById");
		return this.getSqlSession().selectOne(sqlId,userId);
	}

	@Override
	public UserAreaDto findCityNameByUID(String userId) {
		String sqlId = this.getNamedSqlId("findCityNameByUID");
		return this.getSqlSession().selectOne(sqlId,userId);
	}


	@Override
	public List<User> findByUserIds(List<String> values) {
		String sqlId = this.getNamedSqlId("findByUserIds");
		return this.getSqlSession().selectList(sqlId,values);
	}

	@Override
	public PaginatedList<UserAreaDto> findByClient(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findByClient");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<UserAreaDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public UserAreaDto findByUid(String userId) {
		String sqlId = this.getNamedSqlId("findByUid");
		return this.getSqlSession().selectOne(sqlId,userId);
	}

	@Override
	public Map factoryUserPersonalInfo(MapContext params) {
		String sqlId = this.getNamedSqlId("factoryUserPersonalInfo");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public List<MapContext> findFactoryUserDeptName(String companyId,String userId) {
		MapContext map = MapContext.newOne();
		map.put("companyId", companyId);
		map.put("userId", userId);
		String sqlId = this.getNamedSqlId("findFactoryUserDeptName");
		return this.getSqlSession().selectList(sqlId, map);

	}


	@Override
	public Map findFactoryUserAccountInfo(String companyId, String userId) {
		String sqlId = this.getNamedSqlId("findFactoryUserAccountInfo");
		return this.getSqlSession().selectOne(sqlId, userId);
	}

	@Override
	public List<String> findAllUserIdByBranchId(String branchId) {
		String sqlId = this.getNamedSqlId("findAllUserIdByBranchId");
		return this.getSqlSession().selectList(sqlId, branchId);
	}

	@Override
	public List<String> findUserIdByType(String branchId, Integer type) {
		String sqlId = this.getNamedSqlId("findUserIdByType");
		MapContext map = MapContext.newOne();
		map.put("type", type);
		map.put("branchId", branchId);
		return this.getSqlSession().selectList(sqlId, map);
	}

	@Override
	public User findByMobileAndBranchId(String mobile, String branchId) {
		MapContext map = MapContext.newOne();
		map.put("mobile", mobile);
		map.put("branchId", branchId);
		String sqlId = this.getNamedSqlId("findByMobileAndBranchId");
		return this.getSqlSession().selectOne(sqlId, map);
	}

	@Override
	public Integer countEnableDealerUser(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("countEnableDealerUser");
		return this.getSqlSession().selectOne(sqlId, mapContext);
	}

	@Override
	public Integer countEnableWxLoginDealerUser(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("countEnableWxLoginDealerUser");
		return this.getSqlSession().selectOne(sqlId, mapContext);
	}

	@Override
	public Integer countEnablePcLoginDealerUser(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("countEnablePcLoginDealerUser");
		return this.getSqlSession().selectOne(sqlId, mapContext);
	}

	@Override
	public Integer countEnableFactoryUser(String branchId) {
		String sqlId = this.getNamedSqlId("countEnableFactoryUser");
		return this.getSqlSession().selectOne(sqlId, branchId);
	}

	@Override
	public Integer countEnableWxLoginFactoryUser(String branchId) {
		String sqlId = this.getNamedSqlId("countEnableWxLoginFactoryUser");
		return this.getSqlSession().selectOne(sqlId, branchId);
	}

	@Override
	public Integer countEnablePcLoginFactoryUser(String branchId) {
		String sqlId = this.getNamedSqlId("countEnablePcLoginFactoryUser");
		return this.getSqlSession().selectOne(sqlId, branchId);
	}

	@Override
	public Integer countDealerUser(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("countDealerUser");
		return this.getSqlSession().selectOne(sqlId, mapContext);
	}
}
