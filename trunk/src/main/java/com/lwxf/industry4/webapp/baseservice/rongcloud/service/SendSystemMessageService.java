package com.lwxf.industry4.webapp.baseservice.rongcloud.service;

import com.lwxf.industry4.webapp.baseservice.rongcloud.RongCloudUtils;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.system.*;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.utils.LwxfAssert;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyShareMember;
import com.lwxf.industry4.webapp.domain.entity.company.StoreConfig;
import com.lwxf.industry4.webapp.domain.entity.reservation.Reservation;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-10-07 18:38
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("sendSystemMessageService")
@Transactional(readOnly = true)
public class SendSystemMessageService {
	public void sendCreateReservationMessage(Reservation reservation,String userId,String companyId){
		LwxfAssert.notNull(null,"该发送消息方法还没实现");
		/*//通过公司id查询出所有店员的第三方信息
		List<UserThirdInfo> userThirdInfos = AppBeanInjector.userThirdInfoService.findAllClerks(companyId);
		//通过公司id查询公司下的所有身份
		List<CompanyShareMember> members = AppBeanInjector.companyShareMemberService.selectAllCompanyShareMemberByCompanyId(companyId);
		//拿到公司下的所有员工的id
		Set<String> userIds = new HashSet<>();
		userThirdInfos.forEach(uti -> {
			userIds.add(uti.getUserId());
		});
		//拿到公司下的所有员工身份的id（不会重复）
		members.forEach(m ->{
			if(m.getStatus()==2){
				userIds.add(m.getUserId());
			}
		});
		if (userIds.size()>0){
			//根据预约人的id查询预约人的用户信息
			User creator = AppBeanInjector.userService.findByUserId(userId);
			//根据公司id查询公司的店铺
			StoreConfig storeConfig = AppBeanInjector.storeConfigService.findByCompanyId(companyId);
			//创建发送的预约信息（预约信息，店铺信息，创建人信息）
			CreateReservationMessage createReservationMessage = new CreateReservationMessage(reservation,storeConfig,creator);
			//将list集合转成string数组
			String[] toUserIds = new String[userIds.size()];
			userIds.toArray(toUserIds);
			//消息需要传给那个用户
			createReservationMessage.setToUserId(userIds.toArray(toUserIds));
			//融云发送消息
			RongCloudUtils.sendSystemMessage(createReservationMessage);
		}*/
	}

	/**
	 * 预约单状态消息推送
	 * @param reservationId
	 * @param companyId
	 * @param designerId
	 */
	public void sendCreateReservationStatusMessage(String reservationId,String companyId,String designerId){

		LwxfAssert.notNull(null,"该发送消息方法还没实现");
		/*Reservation reservation = AppBeanInjector.reservationService.findById(reservationId);
		User creator = AppBeanInjector.userService.findById(reservation.getUserId());
		StoreConfig storeConfig = AppBeanInjector.storeConfigService.findByCompanyId(companyId);
		User designer = AppBeanInjector.userService.findById(designerId);
		Set<String> userIds = new HashSet<>();
			userIds.add(reservation.getUserId());
		if(userIds.size()>0){

			CreateReservationStatusMessage createdReservationStatusMessage = new CreateReservationStatusMessage(reservation,storeConfig,creator,designer);
			String[] toUserIds = new String[userIds.size()];
			createdReservationStatusMessage.setToUserId(userIds.toArray(toUserIds));
			RongCloudUtils.sendSystemMessage(createdReservationStatusMessage);
		}*/


	}

	/**
	 * 申请身份给店员、店长发消息
	 */
	/*public void sendCreateAddShareMember(CompanyShareMemberDto companyShareMemberDto){
		List<UserThirdInfo> userThirdInfos = AppBeanInjector.userThirdInfoService.findAllClerks(companyShareMemberDto.getCompanyId());
		Set<String> userIds = new HashSet<>();
		userThirdInfos.forEach(uti -> {
			userIds.add(uti.getUserId());
		});
		User creator = AppBeanInjector.userService.findByUserId(companyShareMemberDto.getUserId());
		StoreConfig storeConfig = AppBeanInjector.storeConfigService.findByCompanyId(companyShareMemberDto.getCompanyId());
		CreateAddShareMemberMessage createdAddShareMemberMessage = new CreateAddShareMemberMessage(companyShareMemberDto,storeConfig,creator);
		String[] toUserIds = new String[userIds.size()];
		userIds.toArray(toUserIds);
		createdAddShareMemberMessage.setToUserId(userIds.toArray(toUserIds));
		RongCloudUtils.sendSystemMessage(createdAddShareMemberMessage);

	}*/

	/**
	 * 审核身份状态
	 */
	/*public void sendCreateShareMemberStatus(MapContext mapContext){
		String userId = (String) mapContext.get(WebConstant.KEY_ENTITY_USER_ID);
		String[] touserId = new String[]{userId};
		StoreConfig storeConfig = AppBeanInjector.storeConfigService.findByCompanyId((String) mapContext.get("companyId"));
		CompanyShareMember companyShareMember = new CompanyShareMember();
		companyShareMember.setIdentity((Integer) mapContext.get("identity"));
		User creator = AppBeanInjector.userService.findById(userId);
		CreateShareMemberStatusMessage createShareMemberStatusMessage = new CreateShareMemberStatusMessage(storeConfig,companyShareMember,creator);
		createShareMemberStatusMessage.setToUserId(touserId);
		RongCloudUtils.sendSystemMessage(createShareMemberStatusMessage);
	}*/
}
