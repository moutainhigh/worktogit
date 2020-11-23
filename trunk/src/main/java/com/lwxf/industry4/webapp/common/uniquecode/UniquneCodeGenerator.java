package com.lwxf.industry4.webapp.common.uniquecode;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.lwxf.commons.constant.CommonConstant;
import com.lwxf.commons.exception.LwxfIllegalArgumentException;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.cache.RedisNotSupportTransactionUtils;
import com.lwxf.industry4.webapp.baseservice.cache.SequenceGenerator;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.utils.Configuration;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：编号生成器
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-06-28 15:13
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("uniquneCodeGenerator")
public class UniquneCodeGenerator {
	@Resource(name = "sequenceGenerator")
	private SequenceGenerator sequenceGenerator;
	@Resource(name = "redisNotSupportTransactionUtils")
	private RedisNotSupportTransactionUtils redisUtils;
	@Resource(name = "configuration")
	private Configuration configuration;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<UniqueResource,INoCreator> creators;
	UniquneCodeGenerator(){
		creators = new HashMap<>();
		creators.put(UniqueResource.ORDER_NO,new OrderNoCreator(UniqueResource.ORDER_NO));
		creators.put(UniqueResource.SPECIMEN_ORDER_NO,new SpecimenOrderNoCreator(UniqueResource.SPECIMEN_ORDER_NO));
		creators.put(UniqueResource.LOGISTICS_NO,new LogisticsNoCreator(UniqueResource.LOGISTICS_NO));
		creators.put(UniqueResource.PAID_RECORDS_NO,new PaidRecordsNoCreator(UniqueResource.PAID_RECORDS_NO));
		creators.put(UniqueResource.NEWS_ID,new NewsIdCreator(UniqueResource.NEWS_ID));
		creators.put(UniqueResource.TRADE_NO,new TradeNoCreator(UniqueResource.TRADE_NO));

		creators.put(UniqueResource.DISPATCH_LIST_NO,new DispatchListNoCreator(UniqueResource.DISPATCH_LIST_NO));

		creators.put(UniqueResource.PAYMENT_NO,new PaymentNoCreator(UniqueResource.PAYMENT_NO));

		creators.put(UniqueResource.AFTERSALE_APPLY_NO,new AftersaleNoCreator(UniqueResource.AFTERSALE_APPLY_NO));


		creators.put(UniqueResource.ORDER_DEMAND_NO,new OrderDemandNoCreator(UniqueResource.ORDER_DEMAND_NO));
		creators.put(UniqueResource.DISPATCH_NO,new DispatchNoCreator(UniqueResource.DISPATCH_NO));
		creators.put(UniqueResource.PURCHASE_REQUEST,new PurchaseRequestNoCreator((UniqueResource.PURCHASE_REQUEST)));
		creators.put(UniqueResource.PURCHASE_BATCH,new PurchaseBatchNoCreator(UniqueResource.PURCHASE_BATCH));
		creators.put(UniqueResource.STORAGE_OUTPUTIN_NO,new StorageOutputInNoCreator(UniqueResource.STORAGE_OUTPUTIN_NO));
		creators.put(UniqueResource.DESIGN_NO,new DesignNoCreator(UniqueResource.DESIGN_NO));
		creators.put(UniqueResource.DESIGN_SCHEME_NO,new DesignSchemeNoCreator(UniqueResource.DESIGN_SCHEME_NO));
		creators.put(UniqueResource.ACTIVITY_NO,new ActivityNoCreator(UniqueResource.ACTIVITY_NO));
		creators.put(UniqueResource.USER_LOGNAME_NO,new UserLoginNameNoCreator(UniqueResource.USER_LOGNAME_NO));
		creators.put(UniqueResource.PRODUCE_ORDER_NO,new ProduceOrderNoCreator(UniqueResource.PRODUCE_ORDER_NO));
		creators.put(UniqueResource.PACKAGE_NO,new PackageNoCreator(UniqueResource.PACKAGE_NO));
		creators.put(UniqueResource.COMPANY_NO,new CompanyNoCreator(UniqueResource.COMPANY_NO));
	}

	/**
	 * 正常的编号生成接口（不需要有特殊前缀的）
	 * @param resource
	 * @return
	 */
	public String getNextNo(UniqueResource resource){
		return this.getNextNo(resource,WebConstant.STRING_EMPTY);
	}

	public String getNextNoWithOutDate(UniqueResource resource){
		return this.getNextNoWithOutDate(resource,WebConstant.STRING_EMPTY);
	}

	/**
	 * 当需要特殊前缀时，调用该接口，比如订单需要将经销商的公司编号作为前缀
	 * @param resource
	 * @param startPrefixKey：要生成编号的特殊前缀
	 * @return
	 */
	public String getNextNo(UniqueResource resource,String startPrefixKey){
		INoCreator creator = this.creators.get(resource);
		if(null == creator){
			throw new LwxfIllegalArgumentException("未初始化的编号生成器："+resource.toString());
		}
		return creator.createNextNo(startPrefixKey);
	}

	public String getNextNoWithOutDate(UniqueResource resource,String startPrefixKey){
		INoCreator creator = this.creators.get(resource);
		if(null == creator){
			throw new LwxfIllegalArgumentException("未初始化的编号生成器："+resource.toString());
		}
		return creator.createNextNoWithOutDate(startPrefixKey);
	}

	/**
	 * 订单包裹编号生成
	 * @param customOrderId
	 * @return
	 */
	public String getPackageNextNo(String customOrderId){
		return this.getPackageNextNo(customOrderId,null);
	}

	/**
	 * 订单包裹编号生成 有前缀
	 * @param customOrderId
	 * @param type
	 * @return
	 */
	public String getPackageNextNo(String customOrderId,String type){

		INoCreator creator = this.creators.get(UniqueResource.PACKAGE_NO);
		if(null == creator){
			throw new LwxfIllegalArgumentException("未初始化的编号生成器：PACKAGE_NO");
		}
		return creator.getPackageNextNo(customOrderId,type);
	}

	public String getNoByTime(Date date,String type){
		INoCreator creator = this.creators.get(UniqueResource.ORDER_NO);
		if(null == creator){
			throw new LwxfIllegalArgumentException("未初始化的编号生成器：ORDER_NO");
		}
		return creator.getNoByTime(date,type);
	}
	public String getNumberIncrement(String companyNo){
		INoCreator creator = this.creators.get(UniqueResource.COMPANY_NO);
		if(null == creator){
			throw new LwxfIllegalArgumentException("未初始化的编号生成器：ORDER_NO");
		}
		return creator.getNumberIncrement(companyNo);
	}
	interface INoCreator{
		String createNextNo(String startPrefixKey);
		String createNextNoWithOutDate(String startPrefixKey);
		String getPackageNextNo(String customOrderNo,String type);
		String getNoByTime(Date date,String type);
		String getNumberIncrement(String companyNo);
	}

	abstract class AbstractNoCreator implements INoCreator{
		protected static final String dateFormat = "yyMM-";
		protected static final String dateFormat1 = "yyyyMM";
		protected static final String REDIS_KEY_PREFIX_TEMPLATE="{0}_PREFIX";
		protected volatile  String prefix;
		protected int noLen=2;
		protected String redisKey;
		protected String redisKeyPrefix;

		AbstractNoCreator(UniqueResource uniqueResource){
			this.redisKey=uniqueResource.toString();
			this.redisKeyPrefix = LwxfStringUtils.format(REDIS_KEY_PREFIX_TEMPLATE,this.redisKey);
		}

		@Override
		public String createNextNo(String startPrefixKey) {
			return this.getNextNo(this.reset(), startPrefixKey);
		}

		@Override
		public String createNextNoWithOutDate(String startPrefixKey) {
			return this.getNextNoWithOutDate(this.reset(), startPrefixKey);
		}
		@Override
		public String getNumberIncrement(String companyNo) {
			String redisKey = redisUtils.getString(companyNo);
			if(LwxfStringUtils.isBlank(redisKey)){
				redisUtils.set(companyNo,10001);//放入
				sequenceGenerator.set(companyNo,10000);
			}
			String noStr = String.valueOf(sequenceGenerator.generate(companyNo));
			return noStr;
		}
		@Override
		public String getPackageNextNo(String customOrderNo,String type) {
			//通过判断redis是否存在 该订单Id 来控制 编号是否初始化
			String redisCurrDate = redisUtils.getString(customOrderNo);
			if(LwxfStringUtils.isBlank(redisCurrDate)){
				redisUtils.set(customOrderNo,1);//放入1 代表 该订单 已存在包裹
				sequenceGenerator.set(customOrderNo,0);
			}
			String noStr = String.valueOf(sequenceGenerator.generate(customOrderNo));
			logger.error("1 >>>>>>>>>> 订单编号 ={}，生成的包裹编号：{}",customOrderNo,noStr);
			int noStrLen = noStr.length();
			StringBuilder sb = new StringBuilder(customOrderNo+"-");
			if(type!=null){
				sb.append(type);
			}
			// 长度超过时
			if(noStrLen < this.noLen){
				int preFixLen = this.noLen - noStrLen;
				// 长度未超过
				for(int i=0;i<preFixLen;i++){
					sb.append(CommonConstant.STRING_ZERO);
				}
			}
			String retNo = sb.append(noStr).toString();
			logger.error("2 >>>>>>>>>> 订单编号 ={}，生成的包裹编号：{}",customOrderNo,retNo);
			return retNo;
		}

		@Override
		public String getNoByTime(Date date,String type) {
			//redis中存储的日期 年月
			String currDate1 = DateUtil.dateTimeToString(date,dateFormat1);
			//订单中使用的字符串 年月日
			String currDate = DateUtil.dateTimeToString(date,dateFormat);
			//通过判断redis是否存在 该日期 来控制 编号是否初始化
			String dateStr = type+currDate1;
			String redisCurrDate = redisUtils.getString(dateStr);
			if(LwxfStringUtils.isBlank(redisCurrDate)){
				redisUtils.set(dateStr,1);//放入1 代表 该日期 已存在
				sequenceGenerator.set(dateStr,0);
			}
			String noStr = String.valueOf(sequenceGenerator.generate(dateStr));
			logger.error("1 >>>>>>>>>> redisKey ={}，生成的订单编号：{}",this.redisKey,noStr);
			int noStrLen = noStr.length();
			StringBuilder sb = new StringBuilder();
			sb.append(currDate);
			// 长度超过时
			//if(noStrLen < this.noLen){
			//	int preFixLen = this.noLen - noStrLen;
				// 长度未超过
			//	for(int i=0;i<preFixLen;i++){
			//		sb.append(CommonConstant.STRING_ZERO);
			//	}
			//}
			String nostr2="";
			nostr2 = String.format("%03d", Integer.parseInt(noStr));
			String retNo = sb.append(nostr2).toString();
			logger.error("2 >>>>>>>>>> redisKey ={}，生成的编号：{}",this.redisKey,retNo);
			return retNo;
		}

		/**
		 * 生成编码的前缀
		 * @return
		 */
		protected String createPrefix(){
			return this.prefix;
			//return this.prefix.concat(configuration.getUniquneCodeFlag());
		}

		protected String getNextNo(int resetNo,String startPrefixKey){
			String noStr;
			if(resetNo > 0){
				//重置的话  返回的 就是1 因此 可以直接拿来用
				noStr = String.valueOf(resetNo);
			}else{
				//通过模块的key获取当前的值 然后+1得到noStr
				noStr = String.valueOf(sequenceGenerator.generate(this.redisKey));
			}
			logger.error("1 >>>>>>>>>> redisKey ={}，生成的编号：{}",this.redisKey,noStr);
			int noStrLen = noStr.length();
			StringBuilder sb = new StringBuilder(startPrefixKey);
			sb.append(this.createPrefix());
			// 长度超过时
			if(noStrLen < this.noLen){
				int preFixLen = this.noLen - noStrLen;
				// 长度未超过
				for(int i=0;i<preFixLen;i++){
					sb.append(CommonConstant.STRING_ZERO);
				}
			}
			String retNo = sb.append(noStr).toString();
			logger.error("2 >>>>>>>>>> redisKey ={}，生成的编号：{}",this.redisKey,retNo);
			return retNo;
		}

		protected String getNextNoWithOutDate(int resetNo,String startPrefixKey){
			String noStr;
			if(resetNo > 0){
				//重置的话  返回的 就是1 因此 可以直接拿来用
				noStr = String.valueOf(resetNo);
			}else{
				//通过模块的key获取当前的值 然后+1得到noStr
				noStr = String.valueOf(sequenceGenerator.generate(this.redisKey));
			}
			logger.error("1 >>>>>>>>>> redisKey ={}，生成的编号：{}",this.redisKey,noStr);
			int noStrLen = noStr.length();
			StringBuilder sb = new StringBuilder(startPrefixKey);
		//	sb.append(this.createPrefix());
			// 长度超过时
			if(noStrLen < this.noLen){
				int preFixLen = this.noLen - noStrLen;
				// 长度未超过
				for(int i=0;i<preFixLen;i++){
					sb.append(CommonConstant.STRING_ZERO);
				}
			}
			String retNo = sb.append(noStr).toString();
			logger.error("2 >>>>>>>>>> redisKey ={}，生成的编号：{}",this.redisKey,retNo);
			return retNo;
		}

		protected int reset(){
			synchronized(this){
				String currDate = DateUtil.dateTimeToString(DateUtil.getSystemDate(),dateFormat);
				int resetNo = -1; // 表示没重置
				if(this.prefix == null){
					String redisCurrDate = redisUtils.getString(this.redisKeyPrefix);
					if(LwxfStringUtils.isBlank(redisCurrDate)){
						redisCurrDate = currDate;
						redisUtils.set(this.redisKeyPrefix,redisCurrDate);
					}
					this.prefix = redisCurrDate;
				}
				if(!this.prefix.equals(currDate)){
					resetNo = 1;
					this.prefix = currDate;
					sequenceGenerator.set(this.redisKey,resetNo);
					redisUtils.set(this.redisKeyPrefix,currDate);
				}
				return resetNo;
			}
		}
	}

	/**
	 * 订单编号生成器
	 */
	class OrderNoCreator extends AbstractNoCreator{
		public OrderNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}



	/**
	 * 物流编号生成器
	 */
	class LogisticsNoCreator extends AbstractNoCreator{
		public LogisticsNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}


	/**
	 * 支付记录编号生成器
	 */
	class PaidRecordsNoCreator extends AbstractNoCreator{
		public PaidRecordsNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}

	/**
	 * 商户系统内部订单号
	 */
	class TradeNoCreator extends OrderNoCreator{
		public TradeNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}
	/**
	 * 安装单编号生成器
	 */
	class DispatchListNoCreator extends AbstractNoCreator{
		public DispatchListNoCreator(UniqueResource uniqueResource) {

			super(uniqueResource);
		}
	}

	/**
	 * 售后单编号生成器
	 */
	class AftersaleNoCreator extends AbstractNoCreator{
		public AftersaleNoCreator(UniqueResource uniqueResource) {

			super(uniqueResource);
		}
	}
	/**
	 * 生成企业动态的文章id
	 */
	class NewsIdCreator extends AbstractNoCreator{
		public NewsIdCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}

		protected String createPrefix(){
			return this.prefix;
		}
	}

	/**
	 * 订单需求编号生成器
	 */
	class OrderDemandNoCreator extends AbstractNoCreator{
		public OrderDemandNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}
/**
	 * 配送单编号生成器
	 */
	class DispatchNoCreator extends AbstractNoCreator{
		public DispatchNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}
/**
	 * 采购申请单编号生成器
	 */
	class PurchaseRequestNoCreator extends AbstractNoCreator{
		public PurchaseRequestNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}
/**
	 * 采购单采购批次生成器
	 */
	class PurchaseBatchNoCreator extends AbstractNoCreator{
		public PurchaseBatchNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}

/**
	 * 支付记录编号批次生成器
	 */
	class PaymentNoCreator extends AbstractNoCreator{
		public PaymentNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}

/**
	 * 出入库单编号生成器
	 */
	class StorageOutputInNoCreator extends AbstractNoCreator{
		public StorageOutputInNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}

/**
	 * 设计单编号生成器
	 */
	class DesignNoCreator extends AbstractNoCreator{
		public DesignNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}

/**
	 * 设计案例编号生成器
	 */
	class DesignSchemeNoCreator extends AbstractNoCreator{
		public DesignSchemeNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}

/**
	 * 活动编号生成器
	 */
	class ActivityNoCreator extends AbstractNoCreator{
		public ActivityNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}
/**
	 * 上样订单编号生成器
	 */
	class SpecimenOrderNoCreator extends AbstractNoCreator{
		public SpecimenOrderNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}
/**
	 * 用户登录名编号生成器
	 */
	class UserLoginNameNoCreator extends AbstractNoCreator{
		public UserLoginNameNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}
/**
	 * 生产订单编号生成器
	 */
	class ProduceOrderNoCreator extends AbstractNoCreator{
		public ProduceOrderNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}
/**
	 * 包裹编号生成器
	 */
	class PackageNoCreator extends AbstractNoCreator{
		public PackageNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}
/**
	 * 经销商公司编号生成器
	 */
	class CompanyNoCreator extends AbstractNoCreator{
		public CompanyNoCreator(UniqueResource uniqueResource) {
			super(uniqueResource);
		}
	}

	public enum UniqueResource{
		ORDER_NO, // 订单编号
		LOGISTICS_NO, // 物流编号
		PAID_RECORDS_NO,// 支付记录编号
		TRADE_NO,//商户系统内部订单号
		NEWS_ID,			// 企业动态的文章id
		DISPATCH_LIST_NO ,   //安装单编号
		ORDER_DEMAND_NO, //订单需求编码
		DISPATCH_NO, //发货单编号
		PURCHASE_REQUEST, //采购申请编号
		PURCHASE_BATCH,	//采购单采购批次
		PAYMENT_NO,//支付编号
		AFTERSALE_APPLY_NO,//售后单编号
		STORAGE_OUTPUTIN_NO,//出入库单编号
		DESIGN_NO,//设计单编号
		DESIGN_SCHEME_NO,//设计案例编号
		ACTIVITY_NO,//活动编号
		SPECIMEN_ORDER_NO,//上样订单编号
		USER_LOGNAME_NO,//用户登录名编号
		PRODUCE_ORDER_NO,//生产订单编号
		PACKAGE_NO,//包裹编号
		COMPANY_NO,//经销商公司编号
	}
}
