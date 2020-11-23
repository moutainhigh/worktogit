package com.lwxf.industry4.webapp.common.exceptions;

/**
 * 功能：ErrorCodes扩展定义
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-07-04 11:12
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public abstract class ErrorCodes extends com.lwxf.commons.exception.ErrorCodes {
	//图片资源数量超过限制
	public final static String BIZ_IMAGE_NUM_LIMIT_10057 = "10057";
	//商品未下架不可修改
	public final static String BIZ_GOODS_NOT_DISABLED_UPDATA_10058 = "10058";
	//商品未收货不可评论
	public final static String BIZ_GOODS_NOT_RECEIVED_NOT_COMMENTABLE_10059 = "10059";
	//报价组合不可更改
	public final static String BIZ_GOODS_PRICE_COMBINATION_NOT_UPDATE_10060="10060";
	//回复只允许一次
	public final static String BIZ_REPLIES_ARE_ALLOWED_ONLY_ONCE_10061="10061";
	//该资源下不可添加下级
	public final static String VALIDATE_RES_NOT_ADD_LOWER_LEVERL_20037 = "20037";
	//商品必须有价格
	public final static String BIZ_A_COMMODITY_MUST_HAVA_A_PRICE_10062="10062";
	//库存不足
	public final static String BIZ_LACK_OF_STOCK_10063="10063";
	//不可重复上传 Non-repeatable upload
	public final static String BIZ_NON_REPEATABLE_UPLOAD_10064="10064";
	//商品图片缺失
	public final static String BIZ_LACK_OF_PICTURE_10065="10065";
	//重复关注 Repeat attention
	public final static String BIZ_REPEAT_ATTENTION_10066="10066";
	//已经是店主
	public final static String BIZ_ALREADY_IS_SHOPKEEPER_10067="10067";
	//申请失败（未成功）
	public final static String BIZ_UNSUCCESSFUL_10068="10068";
	//不支持的身份
	public final static String BIZ_NONSUPPORT_IDENTITY_10069="10069";
	//身份已存在
	public final static String BIZ_IDENTITY_EXIST_10070="10070";
	//身份只能在同一家公司下
	public final static String BIZ_IDENTITY_ONLY_ONE_COMPANY_10071="10071";
	//添加失败
	public final static String BIZ_ADD_FAILURE_10072="10072";
	//删除失败
	public final static String BIZ_DELETE_FAILURE_10073="10073";
	//产品已存在
	public final static String BIZ_PRODUCTS_ALREADY_EXIST_10074="10074";
	//余额不足
	public final static String BIZ_CREDIT_IS_LOW_10075="10075";
	//资源超过最大限制
	public final static String BIZ_RESOURCES_LIMIT_10076="10076";
	//订单状态错误
	public final static String BIZ_ORDER_STATUS_ERROR_10077="10077";
	//未分配设计师
	public final static String BIZ_UNASSIGNED_DESIGNER_10078="10078";
	//提交后不允许删除
	public final static String BIZ_NO_DELETION_AFTER_SUBMISSION_10079="10079";
	//店主已存在
	public final static String BIZ_SHOPKEEPERS_ALREADY_EXIST_10080="10080";
	//案例状态错误
	public final static String BIZ_SCHEME_STATUS_ERROR_10081="10081";
	//已发货不允许操作
	public final static String BIZ_DELIVERED_NOT_OPERATE_10082="10082";
	//审核后不允许操作
	public final static String BIZ_AUDITED_NOT_OPERATE_10083="10083";
	//库存位置错误
	public final static String BIZ_INVENTORY_LOCATION_ERROR_10084="10084";
	//文章分类如果被使用无法删除
	public final static String BIZ_CONTENTS_TYPE_IN_USE_ERROR_10085="10085";
	//活动如果有参与者无法撤销
	public final static String BIZ_ACTIVITY_STATUS_CHANGE_ERROR_10086="10086";
	//经销商公司编码已存在
	public final static String BIZ_COMPANY_NO_DUPLICATE_10087="10087";
	//负责人电话已存在
	public final static String BIZ_USER_MOBILE_DUPLICATE_10088="10088";
	//无效的身份证号
	public final static String VALIDATE_INVALID_ID_NUMBER_20037="20037";
	//订单产品不存在
	public final static String BIZ_PRODUCT_DOES_NOT_EXIST_10089="10089";
	//已经是店主
	public final static String BIZ_BANK_NOT_FOUND_10090="10090";
	//银行余额不足
	public final static String BIZ_BANK_NOT_FOUND_10091="10091";
	//该类型生产单不存在
	public final static String BIZ_THIS_TYPE_OF_PRODUCE_ORDER_NOT_EXIST_10092 = "10092";
	//财务审核实付金额不能为空
	public final static String BIZ_PAYMENT_PAY_AMOUNT_NOT_NULL_10093 = "10093";
	//资源存在 不允许重复新增
	public final static String BIZ_NO_REPETITION_ALLOWED_10094 = "10094";
	//经销商账户余额不足
	public final static String BIZ_DEALERACCOUNT_LOW_10095="10095";
	//启用账户已超出限制
	public final static String BIZ_ENABLE_DEALER_USER_EXCEED_10096="10096";
	//有未完成的发货计划单
	public final static String BIZ_DISPATCH_PLAN_NOT_END_10097="10097";
}
