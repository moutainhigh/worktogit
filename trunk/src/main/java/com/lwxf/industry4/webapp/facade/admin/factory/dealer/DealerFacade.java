package com.lwxf.industry4.webapp.facade.admin.factory.dealer;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyCertificatesDto;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/5/005 13:26
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DealerFacade extends BaseFacade {
	RequestResult addDealer(CompanyDto company);

	RequestResult initDealerAccount(String branchId);

	RequestResult findDealerCompanyList(MapContext mapContent, Integer pageNum, Integer pageSize);

	RequestResult openDealer(User user,String cid,StringBuffer pwd);

	RequestResult fixDealers();

	RequestResult updateDealerCompany(MapContext mapContext, String cid,String logisticsCompanyId);

	RequestResult uploadDealerFiles(String cid, List<MultipartFile> multipartFileList);

	RequestResult deleteDealerFiles( String fileId);

	RequestResult submitDealer(String cid,Payment payment);

	RequestResult findDealerList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult updateDealer(MapContext mapContext, String id);

	RequestResult updateDealerMobile(String id, String mobile);

	RequestResult updateDealerAccountPwd(String id, String newPassword);

	RequestResult findDealerCompanyInfo(String cid);

	RequestResult findDealerCount(String branchId);

	RequestResult enableAndProhibitDealer(User user, String cid);

	RequestResult enableAndProhibitWxLogin(String cid);

	RequestResult enableAndProhibitPcLogin(String cid);

	RequestResult findDealerAccountCount(String branchId);

	RequestResult findDealerNum(String branchId);

	RequestResult addOrderFilesByType(List<MultipartFile> multipartFileList, String fileType);

	RequestResult addCertificates(CompanyCertificatesDto certificates);


	RequestResult updateCertificates(String id, MapContext mapContext);
}
