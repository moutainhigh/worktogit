package com.lwxf.industry4.webapp.bizservice.user;

import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.user.UserAreaDto;
import com.lwxf.industry4.webapp.domain.dto.user.UserDto;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.mybatis.utils.MapContext;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 18:32:20
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface UserService extends BaseService<User, String> {
	/**
	 * 根据用户信箱查用户对象
     *
     * @param email
     * @return
     */
    User findByEmail(String email);

	/**
	 * 根据用户手机号查用户对象
	 *
	 * @param mobile
	 * @return
	 */
	User findByMobile(String mobile);

    PaginatedList<User> findByFilter(PaginatedFilter paginatedFilter);

    /**
     * 根据一组用户标识返回用户列表(id,name)
     *
     * @param userIdList
     * @return
     */
	List<UserDto> findUserDtoListByUserIdList(String[] userIdList);

    /**
     * 检查邮箱存不在不存
     *
     * @param email
     * @return
     */
    boolean isExistsByEmail(String email);

    /**
     * 根据用户标识返回当前用户信息(结构为map)
     *
     * @param userId
     * @return
     */
	User findByUserId(String userId);

	/**
	 * 查询所有用户id
	 *
	 * @return
	 */
	List<String> findAllUserId();
	/**
	 * 根据用户名称返回当前用户信息
	 *
	 * @param loginName
	 * @return
	 */
	User findByLoginName(String loginName);


	/**
	 * 根据用户的ID查询用户的信息
	 * @param id
	 * @return
	 */
	List<User> findUserInfoByUserIds(Collection<String> id);

	/**
	 * 修改UserName的参数
	 * @param username
	 * @return
	 */
	int updateUserName(String username);

	Map<String, Object> findUserById(String id);



	UserAreaDto selectUserAreaDtoById(String id);

	int addFollowers(String userId);

	int closeFollowers(String userId);




	List<User> selectCompanyShopkeeperByCompanyIds(List ids,String roleKey);

	List<User> selectByUserIdList(List<String> ids);



	UserAreaDto findUserMessageById(String userId);


	UserAreaDto findCityNameByUID(String userId);

	List<User> findByUserIds(List<String> values);

	PaginatedList<UserAreaDto> findByClient(PaginatedFilter paginatedFilter);

	UserAreaDto findByUid (String userId);

    Map factoryUserPersonalInfo(MapContext params);

	List<MapContext> findFactoryUserDeptName(String companyId,String userId);

	Map findFactoryUserAccountInfo(String companyId,String userId);

	List<String> findAllUserIdByBranchId(String branchId);

	List<String> findUserIdByType(String branchId,Integer type);

	User findByMobileAndBranchId(String mobile, String branchId);

	Integer countEnableDealerUser(MapContext mapContext);

	Integer countEnableWxLoginDealerUser(MapContext mapContext);

	Integer countEnablePcLoginDealerUser(MapContext mapContext);

	Integer countEnableFactoryUser(String branchId);

	Integer countEnableWxLoginFactoryUser(String branchId);

	Integer countEnablePcLoginFactoryUser(String branchId);

	Integer countDealerUser(MapContext mapContext);

}
