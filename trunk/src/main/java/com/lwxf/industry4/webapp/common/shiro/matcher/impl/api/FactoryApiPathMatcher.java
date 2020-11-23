package com.lwxf.industry4.webapp.common.shiro.matcher.impl.api;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderFilesType;
import com.lwxf.industry4.webapp.common.enums.user.UserType;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.common.shiro.matcher.IApiPathPermissionMatcher;
import com.lwxf.industry4.webapp.common.utils.LwxfAssert;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;

/**
 * 功能：工厂端后台管理api Shiro权限验证
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018/12/5 14:11
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class FactoryApiPathMatcher implements IApiPathPermissionMatcher {
	private IPermissionValidator validator;
	protected static Map<String, String> specialOperateMapping = new HashMap<String, String>() {{
		put("disabled", "disabled");
		put("enabled", "enabled");
		put("submit", "submit");
		put("approval", "approval");
		put("sapproval", "sapproval");
		put("updatestatus", "updatestatus");
	}};

	public FactoryApiPathMatcher() {
		this.validator = new DeptPermissionValidator();
		this.validator.setNext(new StoragePermissionValidator())
				.setNext(new FinancePermissionValidator())
				.setNext(new AftersaleApplyPermissionValidator())
				.setNext(new CustomerPermissionValidator())
				.setNext(new CustomOrderPermissionValidator())
				.setNext(new OrderAccountLogPermissionValidator())
				.setNext(new DealerPermissionValidator())
				.setNext(new DispatchPermissionValidator())
				.setNext(new ProductPermissionValidator())
				.setNext(new SupplierPermissionValidator())
				.setNext(new MenusPermissionValidator())
				.setNext(new OperationsPermissionValidator())
				.setNext(new RolePermissionValidator())
				.setNext(new PurchasePermissionValidator())
				.setNext(new DesignSchemePermissionValidator())
				.setNext(new PaymentSimplePermissionValidator())
				.setNext(new CompanyFinancePermissionValidator())
				.setNext(new EmployeeDailyRecordPermissionValidator())
		// .setNext(); // 链式设置，添加新的验证器就这样进行设置
		;
	}

	/**
	 * @param request
	 * @param action
	 * @param servletPath
	 * @param referer
	 * @return
	 */
	@Override
	public String matcher(HttpServletRequest request, String action, String servletPath, String referer) {
		LwxfAssert.notNull(validator, "validator未初始化");
		// referer验证

		// 业务验证
		return validator.validate(request, action, servletPath);
	}

	/**
	 * 验证器接口
	 */
	interface IPermissionValidator {
		String validate(HttpServletRequest request, String action, String servletPath);

		IPermissionValidator setNext(IPermissionValidator next);
	}

	/**
	 * 验证器基类实现
	 */
	abstract class BasePermissionValidator implements IPermissionValidator {
		protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
		private IPermissionValidator next;
		protected String apiPrefix;

		BasePermissionValidator() {
		}

		@Override
		public String validate(HttpServletRequest request, String action, String servletPath) {
			// 有效路径验证
			if (!this.permPath(servletPath)) {
				if (this.next != null) {
						return this.next.validate(request, action, servletPath);
				}
				return null;
			}

			// 用户身份验证
			LoginedUser loginedUser = WebUtils.getCurrUser();
			if (null == loginedUser) {
				return WebConstant.STRING_404;
			}
			Integer userType = loginedUser.getType();
			if (null == userType) {
				return WebConstant.STRING_404;
			} else if (userType == UserType.ADMIN.getValue() || userType == UserType.SUPER_ADMIN.getValue()) {
				return ShiroUtil.WILDCARD_TOKEN;
			} else if (userType.intValue() != UserType.FACTORY.getValue()) {
				return WebConstant.STRING_404;
			}

			// 用户操作权限验证
			String result = this.doValidate(request, action, servletPath);
			if (result != null) {
				return result;
			}
			if (this.next != null) {
				return this.next.validate(request, action, servletPath);
			}
			return null;
		}

		public IPermissionValidator setNext(IPermissionValidator next) {
			this.next = next;
			return this.next;
		}

		/**
		 * 验证路径是否为本功能模块的操作路径
		 * 如果功能模块存在多个api的开始路径，则需重写该方法，做特殊判断
		 *
		 * @param servletPath
		 * @return
		 */
		protected boolean permPath(String servletPath) {
			return servletPath.startsWith(this.apiPrefix);
		}

		abstract protected String doValidate(HttpServletRequest request, String action, String servletPath);

		/**
		 * 将请求动作转换为权限操作标识
		 *
		 * @param action
		 * @return
		 */
		protected String transActionToOperate(String action) {
			String op = "view";
			switch (action) {
				case WebConstant.REQUEST_ACTION_CREATE:
				case WebConstant.REQUEST_ACTION_UPDATE:
					op = "edit";
					break;
				case WebConstant.REQUEST_ACTION_DELETE:
					op = "delete";
					break;
			}
			return op;
		}

		protected String getSpecialOperate(String servletPath) {
			String op = servletPath.substring(servletPath.lastIndexOf(WebConstant.STRING_DIVIDE) + 1);
			op = specialOperateMapping.get(op);
			if (null == op) {
				logger.warn("从本api得到的特殊操作为null", servletPath);
			}
			return op;
		}

		/**
		 * 验证具体的操作
		 *
		 * @param servletPath
		 * @param action
		 * @param permTpl
		 * @return
		 */
		protected String validateOperate(String servletPath, String action, String permTpl) {
			String op = this.getSpecialOperate(servletPath);
			if (null == op) {
				op = this.transActionToOperate(action);
			}
			if (ShiroUtil.isPermitted(permTpl.concat(op))) {
				return ShiroUtil.WILDCARD_TOKEN;
			}
			if (action.equals(WebConstant.REQUEST_ACTION_READ)) {
				return WebConstant.STRING_404;
			}
			return WebConstant.STRING_EMPTY;
		}
	}

	/**
	 * 部门管理功能的api验证
	 * 部门管理
	 * 部门员工管理
	 */
	class DeptPermissionValidator extends BasePermissionValidator {
		// api/f/depts  //查询全部部门
		// api/f/depts  //添加部门
		// api/f/depts/{id}  //修改部门信息
		// api/f/depts/{id}  //删除部门

		// api/f/depts/members  //获取公司下全部员工
		// api/f/depts/members  //公司新增员工
		// api/f/depts/members/{eid}  //修改员工信息
		// api/f/depts/members/{eid}/info  //查询员工信息详情
		// api/f/depts/members/{eid}/employeeInfo  //修改员工信息
		// api/f/depts/members/{eid}/experiences  //新增员工工作经历
		// api/f/depts/members/{eid}/experiences/{id}  //修改员工工作经历
		// api/f/depts/members/{eid}/experiences/{id}  //删除员工工作经历
		// api/f/depts/members/{eid}/assessment  //新增员工考核信息
		// api/f/depts/members/{eid}/assessment/{id}  //修改员工考核信息
		// api/f/depts/members/{eid}/assessment/{id}  //删除员工考核信息
		// api/f/depts/members/{eid}/certificate  //新增员工证书信息
		// api/f/depts/members/{eid}/certificate/{id}  //修改员工证书信息
		// api/f/depts/members/{eid}/certificate/{id}  //删除员工证书信息
		// api/f/depts/members/{eid}/education  //新增员工教育经历
		// api/f/depts/members/{eid}/education/{id}  //修改员工教育经历
		// api/f/depts/members/{eid}/education/{id}  //删除员工教育经历
		// api/f/depts/members/{eid}/files/{resId}  //员工信息上传图片资源
		// api/f/depts/members/{eid}/files/{fileId}  //删除员工资源图片


		private String deptPermTpl = "0_orgmng:orgmng_dept:";
		private String deptMemberPermTpl = "0_orgmng:orgmng_empl:";
		private Pattern deptMemberPattern = Pattern.compile(LwxfStringUtils.format("/api/f/depts/(members(/{0}(/(info|employeeInfo|experiences(/{0})?|assessment(/{0})?|certificate(/{0})?|education(/{0})?|files/{0}))?)?|{0}/members)", WebConstant.REG_ID_MATCH));
		private Pattern deptPattern = Pattern.compile(LwxfStringUtils.format("/api/f/depts(/{0})?", WebConstant.REG_ID_MATCH));

		DeptPermissionValidator() {
			this.apiPrefix = "/api/f/depts";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			// 验证部门成员的操作
			Matcher matcher = deptMemberPattern.matcher(servletPath);
			if (matcher.matches()) {
				return this.validateOperate(servletPath, action, deptMemberPermTpl);
			}
			// 验证部门的操作
			matcher = deptPattern.matcher(servletPath);
			if (matcher.matches()) {
				return this.validateOperate(servletPath, action, deptPermTpl);
			}

			return null;
		}
	}

	/**
	 * 仓库管理的api验证
	 * 仓库管理
	 * 材料库存管理
	 * 成品库存管理
	 * 出入库管理
	 */
	class StoragePermissionValidator extends BasePermissionValidator {
		// /api/f/storages //工厂端仓库管理
		// /api/f/storages/* //工厂端仓库管理
		// /api/f/storages/*/stocks //工厂端库存管理
		// /api/f/storages/*/stocks/** //工厂端库存管理
		// /api/f/storages/*/finisheds //工厂端成品库管理
		// /api/f/storages/*/finisheds/* //工厂端成品库管理
		// /api/f/storages/*/outputins //工厂端出入单管理
		// /api/f/storages/*/outputins/* //工厂端出入单管理

		// /api/f/storages //新增仓库
		// /api/f/storages //获取仓库列表
		// /api/f/storages/{id} //修改仓库信息
		// /api/f/storages/{id} //删除仓库
		// /api/f/storages/{id}/products //查询全部产品


		// /api/f/storages/{storageId}/outputins //查询出库入库单列表
		// /api/f/storages/{storageId}/outputins/in //入库
		// /api/f/storages/{storageId}/outputins/{id} //确认出入库单
		// /api/f/storages/{storageId}/outputins/out //出库单创建
		// /api/f/storages/{storageId}/outputins/{id}/items/{itemId} //修改入库单下的产品位置
		// /api/f/storages/{storageId}/outputins/{id}/cancel //取消出库单


		// /api/f/storages/{storageId}/finisheds  //查询成品库列表
		// /api/f/storages/{storageId}/finisheds  //新增成品库单
		// /api/f/storages/{storageId}/finisheds/{id}  //成品库单下新增条目
		// /api/f/storages/{storageId}/finisheds/{id}  //修改成品库单
		// /api/f/storages/{storageId}/finisheds/{id}  //删除成品库单
		// /api/f/storages/{storageId}/finisheds/{id}/items/{itemId}  //修改成品库条目详情
		// /api/f/storages/{storageId}/finisheds/{id}/items/{itemId}  //删除成品库下的条目
		// /api/f/storages/{storageId}/finisheds/{id}/warehousing/{itemId}  //成品库下的条目入库

		// /api/f/storages/{id}/stocks //添加库存
		// /api/f/storages/{id}/stocks //根据仓库查询商品列表
		// /api/f/storages/{id}/stocks/{stockId} //修改仓库库存
		// /api/f/storages/{id}/stocks/{stockId} //删除仓库下的某种库存
		// /api/f/storages/{id}/stocks/quantities //出库


		// /api/f/storages/{storageId}/finisheds/{id}/packages/{itemId}/files //包裹上传资源文件
		// /api/f/storages/{storageId}/finisheds/{id}/packages/{itemId}/files //删除包裹相关资源文件



		private String storagePermTpl = "0_storagemng:storagemng_storage:"; // 仓库
		private String stockPermTpl = "0_stockmng:stockmng_{0}:";  // 库存
		private String outinPermTpl = "0_outinmng:outinmng_{0}:";  // 出入库单
//		private String dispatchPlanPermTpl = "0_dispatchingmng:";  // 配送计划

		private Pattern storagePattern = Pattern.compile(LwxfStringUtils.format("/api/f/storages(/{0}(/products)?)?", WebConstant.REG_ID_MATCH));
		private Pattern stockPattern = Pattern.compile(LwxfStringUtils.format("/api/f/storages/({0})/stocks(/({0}|quantities))?", WebConstant.REG_ID_MATCH));
		private Pattern finishedPattern = Pattern.compile(LwxfStringUtils.format("/api/f/storages/((({0})/finisheds(/{0}(/(items/{0}|warehousing/{0}|packages/({0}|itemId)/files))?)?)|all/finisheds)", WebConstant.REG_ID_MATCH));
		private Pattern outputinPattern = Pattern.compile(LwxfStringUtils.format("/api/f/storages/{0}/outputins(/(in|out|{0}(/(cancel|items/{0}))?)?)?", WebConstant.REG_ID_MATCH));

		StoragePermissionValidator() {
			this.apiPrefix = "/api/f/storages";
		}


		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			// 仓库验证
			Matcher matcher = this.storagePattern.matcher(servletPath);
			if (matcher.matches()) {
				return this.validateOperate(servletPath, action, this.storagePermTpl);
			}

			String storageId;
			// 库存验证
			matcher = this.stockPattern.matcher(servletPath);
			if (matcher.matches()) {
				storageId = matcher.group(1);
				return this.validateOperate(servletPath, action, LwxfStringUtils.format(this.stockPermTpl, storageId));
			}

			// 成品库存验证
			matcher = this.finishedPattern.matcher(servletPath);
			if (matcher.matches()) {
				storageId = matcher.group(3);
//				if(storageId.equals("all/finisheds")){
//					return ShiroUtil.WILDCARD_TOKEN;
//				}
				return this.validateOperate(servletPath, action, LwxfStringUtils.format(this.stockPermTpl, storageId));
			}

			// 出入库验证
			matcher = this.outputinPattern.matcher(servletPath);
			if (matcher.matches()) {
				storageId = matcher.group(1);
				return this.validateOperate(servletPath, action, LwxfStringUtils.format(this.outinPermTpl, storageId));
			}
			return null;
		}
	}

	/**
	 * 财务管理的API验证
	 */
	class FinancePermissionValidator extends BasePermissionValidator {

		// /api/f/finances/** //工厂端财务管理
		// /api/f/finances/* //工厂端财务管理
		// /api/f/finances/dealerpays/{id} 修改经销商支付记录状态
		// /api/f/finances/customorders 查询需要财务审核的订单与支付记录列表
		// /api/f/finances/purchases 查询待审批的采购单列表
		// /api/f/finances/dealerpays 查询待审核的经销商收支列表
		// /api/f/finances/suppliers 查询待审批供应商列表
		// /api/f/finances/suppliers/{id}/{status} 审批供应商
		// /api/f/finances/dealer/{id} 加盟费确认收款

		// /api/f/finances/coordination/{id} 财务审核外协生产单
		// /api/f/finances/coordination 查询需要财务审核的外协生产单

		private String orderPermTpl = "0_financingmng:financingmng_order:"; //订单
		private String purchaseTpl = "0_financingmng:financingmng_purchase:"; //采购
		private String dealerPermTpl = "0_financingmng:financingmng_dealerpay:"; //经销商
		private String supplierPermTpl = "0_financingmng:financingmng_supplier:"; //供应商
		private String coordinationPermTpl = "0_financingmng:financingmng_coordination:"; //外协

		private Pattern dealerPattern = Pattern.compile(LwxfStringUtils.format("/api/f/finances/(dealerpays|dealer)(/{0})?", WebConstant.REG_ID_MATCH));
		private Pattern orderPattern = Pattern.compile(LwxfStringUtils.format("/api/f/finances/customorders"));
		private Pattern purchasePattern = Pattern.compile(LwxfStringUtils.format("/api/f/finances/purchases"));
		private Pattern supplierPattern = Pattern.compile(LwxfStringUtils.format("/api/f/finances/suppliers(/{0}/{1})?", WebConstant.REG_ID_MATCH, "[0-9]"));
		private Pattern coordinationPattern = Pattern.compile(LwxfStringUtils.format("/api/f/finances/coordination(/{0})?",WebConstant.REG_ID_MATCH));

		public FinancePermissionValidator() {
			this.apiPrefix = "/api/f/finances";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//订单验证
			Matcher matcher = this.orderPattern.matcher(servletPath);
			if (matcher.matches()) {
				return this.validateOperate(servletPath, action, orderPermTpl);
			}
			//支付记录审核验证
			matcher = this.dealerPattern.matcher(servletPath);
			if (matcher.matches()) {
				return this.validateOperate(servletPath, action, dealerPermTpl);
			}
			//采购审核验证
			matcher = this.purchasePattern.matcher(servletPath);
			if (matcher.matches()) {
				return this.validateOperate(servletPath, action, purchaseTpl);
			}
			//供应商审核验证
			matcher = this.supplierPattern.matcher(servletPath);
			if (matcher.matches()) {
				return this.validateOperate(servletPath, action, supplierPermTpl);
			}
			//外协审核
			matcher = this.coordinationPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,coordinationPermTpl);
			}
			return null;
		}
	}

	/**
	 * 售后管理的API验证
	 */
	class AftersaleApplyPermissionValidator extends BasePermissionValidator {
		// /api/f/aftersales  //查询售后单列表
		// /api/f/aftersales/{orderId} //新增售后单
		// /api/f/aftersales/{id}/files //批量上传售后图片
		// /api/f/aftersales/{id}/files/{fileId} //删除售后单下的图片
		// /api/f/aftersales/{id} //修改售后单
		// /api/f/aftersales/{id} //删除售后单


		// V2
		// /api/f/aftersales/v2/aftersaleApplies //售后单查询列表
		// /api/f/aftersales/v2/aftersaleApplies/{aftersaleApplyId} //售后单详情
		// /api/f/aftersales/v2/dealers //经销商列表
		// /api/f/aftersales/v2/dealers/{dealerId}/customers //客户列表
		// /api/f/aftersales/v2/aftersale/request //创建售后申请单
		// /api/f/aftersales/v2/aftersale //创建售后单
		// /api/f/aftersales/v2/aftersaleApplies/{aftersaleId}/status/{status} //更新售后单状态
		// /api/f/aftersales/v2/handle //处理售后单
		// /api/f/aftersales/v2/aftersaleApplies/addfiles //上传售后申请证据图片

//		private String aftersalesPermTpl = "0_aftersalemng:aftersalemng_apply:"; //售后单
//
//		private Pattern aftersalesPattern = Pattern.compile(LwxfStringUtils.format("/api/f/aftersales(((/{0}/files/{0})?|(/{0}/files)?)?|(/{0})?)?", WebConstant.REG_ID_MATCH));

		private String aftersalesPermTpl_V2 = "0_aftersalemng:aftersalemng_apply:"; //V2 售后单

		private Pattern aftersalesPattern_V2 = Pattern.compile(LwxfStringUtils.format("/api/f/aftersales/v2/(aftersale|handle|dealers(/{0}/customers)?|aftersaleApplies(/({0}(/status/{1})?|addfiles))?)",WebConstant.REG_ID_MATCH,"\\d{1}"));

		public AftersaleApplyPermissionValidator() {
			this.apiPrefix = "/api/f/aftersales/v2";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//售后单验证
			Matcher matcher = this.aftersalesPattern_V2.matcher(servletPath);
			if (matcher.matches()) {
				return this.validateOperate(servletPath, action, aftersalesPermTpl_V2);
			}
			return null;
		}
	}

	/**
	 * 客户管理的API验证
	 */
	class CustomerPermissionValidator extends BasePermissionValidator {

		// /api/f/customers 查询所有的客户（可以根据公司id和顾客姓名，电话查询）
		// /api/f/customers 添加顾客
		// /api/f/customers/{cid} 修改客户信息

		private String customersPermTpl = "0_customermng:customermng_customer:"; //客户

		private Pattern customersPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customers(/{0})?",WebConstant.REG_ID_MATCH));

		public CustomerPermissionValidator() {
			this.apiPrefix = "/api/f/customers";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			Matcher matcher = this.customersPattern.matcher(servletPath);
			if (matcher.matches()) {
				return this.validateOperate(servletPath, action, customersPermTpl);
			}
			return null;
		}
	}

	/**
	 * 订单管理的API验证
	 */
	class CustomOrderPermissionValidator extends BasePermissionValidator {

		// /api/f/customorders/finishedorders //查询可生成成品库单的订单
		// /api/f/customorders/finishedorders/all //查询可生成成品库单的订单

		// /api/f/customorders //通过条件查询订单列表
		// /api/f/customorders/{id} //修改订单信息

		// /api/f/customorders/{orderId}/amount //根据订单 获取扣款详情
		// /api/f/customorders/{aftersaleId}/aftersales //新建售后订单
		// /api/f/customorders/{id}/info //查询订单详情

		// /api/f/customorders/{id}/demands/{demandId} //查询订单下需求详情
		// /api/f/customorders/{id}/designs/{designId} //查询订单下的设计详情

		// /api/f/customorders/{id}/orderevaluation //订单管理模块修改订单审价状态

		// /api/f/customorders/{id}/designers/{userId} //给订单设置设计人员

		// /api/f/customorders/{id}/designs //新建设计记录
		// /api/f/customorders/{id}/designs/{designId} //修改设计记录
		// /api/f/customorders/{id}/designerssubmit //设计管理模块修改订单状态
		// /api/f/customorders/{id}/designs/{designId} //删除设计记录
		// /api/f/customorders/{id}/designs/{designId}/files //上传设计图片
		// /api/f/customorders/{id}/designs/{designId}/file/{fileId}//删除设计记录下某张图片


		// /api/f/customorders/{id}/designevaluation //设计管理模块下修改订单审价状态



		// /api/f/customorders/{id}/manufactureplan //生产计划管理模块修改订单状态
		// /api/f/customorders/{id}/manufactureprocess //生产过程管理模块修改订单状态



		// /api/f/customorders/{id}/finance //财务管理模块修改订单状态


		// /api/f/customorders/{id}/produces 新增生产拆单
		// /api/f/customorders/{id}/produces/{pId} 修改生产拆单
		// /api/f/customorders/{id}/produces/{pId} 删除生产拆单


		// /api/f/customorders/{id}/products 新增订单产品
		// /api/f/customorders/{id}/products/{pId} 修改订单产品信息
		// /api/f/customorders/{id}/products/{pId} 删除订单产品信息

		// /api/f/customorders/{id}/{type}/{resId}/files 上传订单相关资源文件(订单产品 生产拆单)
		// /api/f/customorders/{id}/files/{fileId} 删除订单资源相关文件

		// /api/f/customorders/produces/plans 查询生产跟进生产单
		// /api/f/customorders/produces/plans 批量安排生产时间
		// /api/f/customorders/produces/starts 批量开始生产

		// /api/f/customorders/produces/processes 查询生产执行的生产单
		// /api/f/customorders/produces/processes/{id} 生产单流程完成
		// /api/f/customorders/produces/processes/{id}/packing 生产单 打包

		// /api/f/customorders/produces/coordinations/{id}/ends 外协单完成

		// /api/f/customorders/designs 查询设计案例列表

		// /api/f/customorders/{id} 订单下打包包裹
		// /api/f/customorders/{id}/packed 订单打包完成
		// /api/f/customorders/packs 查询尚未打包完毕的 订单
		// /api/f/customorders/no 查询当前订单的下一个包裹编号


		private String customOrderPermTpl = "0_ordermng:ordermng_order:"; //订单

		private String orderPriceEvaluationPermTpl = "0_ordermng:ordermng_evaluation:"; //订单货款管理

		private String orderTraceorderPermTpl = "0_ordermng:ordermng_traceorder:"; //订单跟单管理

		private String orderDesignerTaskPermTpl = "0_designmng:designmng_task:";//设计任务管理

		private String orderDesignerPermTpl = "0_designmng:designmng_programme:";//设计方案管理

		private String orderManufacturemngPlanPermTpl = "0_designmng:manufacturemng_plan:";//生产管理:生产计划

		private String orderManufacturemngProcessPermTpl = "0_designmng:manufacturemng_process:";//生产管理:生产过程

		private String orderFinancePermTpl = "0_financingmng:financingmng_order:";//财务管理:订单审批

		private String orderCoordinationPermTpl = "0_ordermng:ordermng_coordination:";//外协管理

		private String manufacturemngFollowPermTpl = "0_manufacturemng:manufacturemng_follow:";//生产跟进

		private String manufacturemngImplementPermTpl = "0_manufacturemng:manufacturemng_implement:";//生产执行

		private String packPermTpl = "0_packagemng:packagemng_pack:"; //打包管理

		private Pattern customOrderPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders(/finishedorders(/all)?|/{0}(/(info|amount|aftersales|demands/{0}|designs/{0}|products(/{0})?|files/({0})|{1}/{0}/files))?)?",WebConstant.REG_ID_MATCH,CustomOrderFilesType.ORDER_PRODUCT.getValue()));

		private Pattern orderPriceEvaluationPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/{0}/orderevaluation",WebConstant.REG_ID_MATCH));

		private Pattern orderTraceorderPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/{0}/(produces(/{0})?|{1}/(produceId|{0})/files)",WebConstant.REG_ID_MATCH,CustomOrderFilesType.PRODUCE_ORDER.getValue()));

		private Pattern orderDesignerUnallocatedPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/{0}/designers/{0}",WebConstant.REG_ID_MATCH));

		private Pattern orderDesignerAllocatedPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/{0}/(designerssubmit|designs(/{0}(/files(/{0})?)?)?)",WebConstant.REG_ID_MATCH));

		private Pattern orderDesignFeeEvalutionPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/{0}/designevaluation",WebConstant.REG_ID_MATCH));

		private Pattern orderDesignerPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/designs"));

		private Pattern orderManufacturemngPlanPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/{0}/manufactureplan",WebConstant.REG_ID_MATCH));

		private Pattern orderManufacturemngProcessPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/{0}/manufactureprocess",WebConstant.REG_ID_MATCH));

		private Pattern orderFinancePattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/{0}/finance",WebConstant.REG_ID_MATCH));

		private Pattern orderCoordinationPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/produces/coordinations/{0}/ends",WebConstant.REG_ID_MATCH));

		private Pattern manufacturemngFollowPattern = Pattern.compile("/api/f/customorders/produces/(plans|starts)");

		private Pattern manufacturemngImplementPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/produces/processes(/{0}(/packing)?)?",WebConstant.REG_ID_MATCH));

		private Pattern packPattern = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/(packs|{0}(/(packed|no))?)",WebConstant.REG_ID_MATCH,CustomOrderFilesType.ORDER_PACKAGE.getValue()));

		public CustomOrderPermissionValidator() {
			this.apiPrefix = "/api/f/customorders";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//订单验证
			Matcher matcher = customOrderPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,customOrderPermTpl);
			}
			//订单货款审价验证
			matcher = orderPriceEvaluationPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,orderPriceEvaluationPermTpl);
			}
			//订单设计管理:未分配 验证
			matcher = orderDesignerUnallocatedPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,orderDesignerTaskPermTpl);
			}
			//订单设计管理:已分配 验证
			matcher = orderDesignerAllocatedPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,orderDesignerTaskPermTpl);
			}
			//订单设计费估价管理 验证
			matcher = orderDesignFeeEvalutionPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,orderDesignerTaskPermTpl)		;
			}
			//生产计划管理 验证
			matcher = orderManufacturemngPlanPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,orderManufacturemngPlanPermTpl);
			}
			//生产管理:生产过程 验证
			matcher = orderManufacturemngProcessPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,orderManufacturemngProcessPermTpl);
			}
			//财务管理:订单审批 验证
			matcher = orderFinancePattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,orderFinancePermTpl);
			}
			//设计方案管理 验证
			matcher = orderDesignerPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,orderDesignerPermTpl);
			}
			//订单跟单管理 验证
			matcher = orderTraceorderPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,orderTraceorderPermTpl);
			}
			//外协管理 验证
			matcher = orderCoordinationPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,orderCoordinationPermTpl);
			}
			//生产跟进 验证
			matcher = manufacturemngFollowPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,manufacturemngFollowPermTpl);
			}
			//生产执行 验证
			matcher = manufacturemngImplementPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,manufacturemngImplementPermTpl);
			}
			//打包验证
			matcher = packPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,packPermTpl);
			}
			return null;
		}
	}

	/**
	 * 订单货款审价管理API验证
	 * 订单设计费审价管理API验证
	 */
	class OrderAccountLogPermissionValidator extends BasePermissionValidator {
		// /api/f/accounts/price  //查询需要审货款的订单列表
		// /api/f/accounts/price/{id}  //修改货款报价信息
		// /api/f/accounts/designfee  //查询需要审货款的订单列表
		// /api/f/accounts/designfee/{id}  //修改货款报价信息

		private String accountsPricePermTpl = "0_ordermng:ordermng_evaluation:"; //订单货款审价
		private String accountsDesignFeePermTpl = "0_designmg:designmg_evaluation:";  //订单设计费审价

		private Pattern pricePattern = Pattern.compile(LwxfStringUtils.format("/api/f/accounts/price(/{0})?", WebConstant.REG_ID_MATCH));
		private Pattern designFeePattern = Pattern.compile(LwxfStringUtils.format("/api/f/accounts/designfee(/{0})?", WebConstant.REG_ID_MATCH));

		public OrderAccountLogPermissionValidator() {
			this.apiPrefix = "/api/f/accounts";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//货款审核验证
			Matcher matcher = pricePattern.matcher(servletPath);
			if (matcher.matches()) {
				return this.validateOperate(servletPath, action, accountsPricePermTpl);
			}
			//设计费审核验证
			matcher = designFeePattern.matcher(servletPath);
			if (matcher.matches()) {
				return this.validateOperate(servletPath, action, accountsDesignFeePermTpl);
			}
			return null;
		}
	}

	/**
	 * 经销商管理的API 验证
	 */
	class DealerPermissionValidator extends BasePermissionValidator{

		// /api/f/dealers/companies  //新增经销商公司
		// /api/f/dealers/companies/{cid}/opendealer  //经销商启用
		// /api/f/dealers/companies //获取经销商公司列表
		// /api/f/dealers/companies/{cid} //查询经销商公司详情
		// /api/f/dealers/companies/{cid} //修改经销商公司信息
		// /api/f/dealers/companies/{cid}/files //经销商上传资源文件
		// /api/f/dealers/companies/{cid}/files/{fileId} //删除资源文件
		// /api/f/dealers/companies/{cid}/submit //经销商到财务审核



		// /api/f/dealers //经销商到财务审核
		// /api/f/dealers/{id} //修改经销商的用户信息
		// /api/f/dealers/{id}/account/number //修改经销商登录名
		// /api/f/dealers/{id}/account/password //修改经销商登录密码



		// /api/f/dealers/opendealer/{cid} //经销商启用

		private String dealerPermTpl = "0_dealermng:dealermng_dealer:"; //经销商信息管理
		private String dealerAccountPermTpl = "0_dealermng:dealermng_account:"; //经销商账号管理

		private Pattern dealerPattern = Pattern.compile(LwxfStringUtils.format("/api/f/dealers/companies(/{0}(/(opendealer|submit|files(/{0})?))?)?",WebConstant.REG_ID_MATCH));

		private Pattern accountPattern = Pattern.compile(LwxfStringUtils.format("/api/f/dealers(/{0}(/account/(number|password))?)?",WebConstant.REG_ID_MATCH));

		public DealerPermissionValidator() {
			this.apiPrefix = "/api/f/dealers";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//经销商管理验证
			Matcher matcher = dealerPattern.matcher(servletPath);
			if (matcher.matches()){
				return this.validateOperate(servletPath,action,dealerPermTpl);
			}
			//经销商账号管理 验证
			matcher = accountPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,dealerAccountPermTpl);
			}
			return null;
		}
	}

	/**
	 * 发货单管理的API 验证
	 */
	class DispatchPermissionValidator extends BasePermissionValidator{

		// /api/f/dispatchs   //查询发货单列表
		// /api/f/dispatchs   //新增发货单
		// /api/f/dispatchs/{id}   //删除发货单
		// /api/f/dispatchs/{id}/deliver   //发货单发货



		// /api/f/dispatchs  新增发货单 并同时发货 V2
		// /api/f/dispatchs  查询发货单列表


		private String dispatchPlanPermTpl = "0_dispatchingmng:dispatchingmng_plan:"; //发货管理
		private String dispatchBillPermTpl = "0_dispatchingmng:dispatchingmng_dispatchbill:"; //发货单管理

		private Pattern dispatchBillPattern = Pattern.compile(LwxfStringUtils.format("/api/f/dispatchs",WebConstant.REG_ID_MATCH));

		public DispatchPermissionValidator() {
			this.apiPrefix = "/api/f/dispatchs";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//发货单管理 验证
			Matcher matcher = dispatchBillPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,dispatchBillPermTpl);
			}
			return null;
		}
	}

	/**
	 * 产品信息管理的API 验证
	 */
	class ProductPermissionValidator extends BasePermissionValidator{

		// /api/f/productcategories //查询产品分类列表
		// /api/f/productcategories //添加产品分类
		// /api/f/productcategories/{id} //修改产品分类信息
		// /api/f/productcategories/{id} //删除产品分类

		// /api/f/products //查询产品列表
		// /api/f/products //新增产品
		// /api/f/products/{id} //修改产品信息
		// /api/f/products/{id}/files/{type} //通过类型 区分上传不同的图片资源
		// /api/f/products/{resources} //通过不同条件查询提示资源列表
		// /api/f/products/{id}/info //查询产品详情 产品信息 以及 图片信息
		// /api/f/products/{productId}/files/{fileId} //删除图片资源

		private String productCategoryPermTpl = "0_prodmng:prodmng_prodcategory:"; //产品分类
		private String productPermTpl = "0_prodmng:prodmng_prod:"; //产品信息

		private Pattern productCategoryPattern = Pattern.compile(LwxfStringUtils.format("/api/f/productcategories(/{0})?",WebConstant.REG_ID_MATCH));
		private Pattern productPattern = Pattern.compile(LwxfStringUtils.format("/api/f/products(/(({0}(/info|/files/({0}|{1}))?)|product_spec|product_color|product_material))?",WebConstant.REG_ID_MATCH,"\\d{1}"));

		@Override
		protected boolean permPath(String servletPath) {
			if (servletPath.startsWith("/api/f/products")||servletPath.startsWith("/api/f/productcategories")){
				return true;
			}
			return false;
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//产品分类管理 验证
			Matcher matcher = productCategoryPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,productCategoryPermTpl);
			}
			//产品信息管理 验证
			matcher = productPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,productPermTpl);
			}
			return null;
		}
	}

	/**
	 * 供应商管理 API 验证
	 * 供应商产品管理 API 验证
	 */
	class SupplierPermissionValidator extends BasePermissionValidator{

		// /api/f/suppliers //查询供应商列表
		// /api/f/suppliers //新增供应商
		// /api/f/suppliers/{id} //供应商添加负责人


		// /api/f/suppliers/{id}/products //供应商下新增产品
		// /api/f/suppliers/{id}/products //查询供应商下的产品列表
		// /api/f/suppliers/{id}/products/{productId} //修改供应商下的产品价格及备注
		// /api/f/suppliers/{id}/products/{productId} //删除供应商下的产品

		private String supplierPermTpl = "0_suppliermng:suppliermng_supplier:"; //供应商信息管理
		private String supplierProductPermTpl = "0_suppliermng:suppliermng_prod:"; //供应商产品管理

		private Pattern supplierPattern = Pattern.compile(LwxfStringUtils.format("/api/f/suppliers(/{0})?",WebConstant.REG_ID_MATCH));
		private Pattern supplierProductPattern = Pattern.compile(LwxfStringUtils.format("/api/f/suppliers/products(/{0})?",WebConstant.REG_ID_MATCH));

		public SupplierPermissionValidator() {
			this.apiPrefix = "/api/f/suppliers";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//供应商管理 验证
			Matcher matcher = supplierPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,supplierPermTpl);
			}
			//供应商产品管理 验证
			matcher = supplierProductPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,supplierProductPermTpl);
			}
			return null;
		}
	}


	/**
	 * 菜单管理API 验证
	 */
	class MenusPermissionValidator extends BasePermissionValidator{

		// /api/f/menus //查询菜单列表
		// /api/f/menus //新增菜单
		// /api/f/menus/{id} //修改菜单信息
		// /api/f/menus/{id} //删除菜单

		private String menusPermTpl = "0_sysmng:sysmng_menu:";

		private Pattern menusPattern = Pattern.compile(LwxfStringUtils.format("/api/f/menus(/{0})?",WebConstant.REG_ID_MATCH));

		public MenusPermissionValidator() {
			this.apiPrefix = "/api/f/menus";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//菜单模块 验证
			Matcher matcher = menusPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,menusPermTpl);
			}
			return null;
		}
	}

	/**
	 * 按钮管理 API 验证
	 */
	class OperationsPermissionValidator extends BasePermissionValidator{

		// /api/f/operations //查询操作按钮列表
		// /api/f/operations //新增操作按钮
		// /api/f/operations/{id} //删除操作按钮
		// /api/f/operations/{id} //修改按钮信息

		private String operationsPermTpl = "0_sysmng:sysmng_button:"; //按钮管理

		private Pattern operationsPattern = Pattern.compile(LwxfStringUtils.format("/api/f/operations(/{0})?",WebConstant.REG_ID_MATCH));

		public OperationsPermissionValidator() {
			this.apiPrefix = "/api/f/operations";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//按钮管理模块 验证
			Matcher matcher = operationsPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,operationsPermTpl);
			}
			return null;
		}
	}

	/**
	 * 角色管理 API 验证
	 * 权限管理 API 验证
	 */
	class RolePermissionValidator extends BasePermissionValidator{

		// /api/f/roles //根据类型查询角色列表
		// /api/f/roles //新增角色
		// /api/f/roles/{id} //修改角色信息(名称 以及 是否是管理权限)
		// /api/f/roles/{id} //删除角色

		// /api/f/roles/{id}/permissions //查询角色下的全部权限
		// /api/f/roles/{id}/permissions //修改角色下权限信息
		// /api/f/roles/all //根据分类查询全部按钮菜单

		private String rolePermTpl = "0_sysmng:sysmng_role:"; //角色管理
		private String permssionPermTpl = "0_sysmng:sysmng_authority:"; //权限管理

		private Pattern rolePattern = Pattern.compile(LwxfStringUtils.format("/api/f/roles(/{0})?",WebConstant.REG_ID_MATCH));
		private Pattern permissionPattern = Pattern.compile(LwxfStringUtils.format("/api/f/roles/(({0}/permissions)|all)",WebConstant.REG_ID_MATCH));

		public RolePermissionValidator() {
			this.apiPrefix = "/api/f/roles";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//权限管理 验证
			Matcher  matcher = permissionPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,permssionPermTpl);
			}
			//角色管理 验证
			matcher = rolePattern.matcher(servletPath);
			if (matcher.matches()){
				return this.validateOperate(servletPath,action,rolePermTpl);
			}
			return null;
		}
	}

	/**
	 * 采购模块权限验证
	 */
	class PurchasePermissionValidator extends BasePermissionValidator{

		// /api/f/purchases/requests //查询申请采购单列表
		// /api/f/purchases/requests //新增申请采购单
		// /api/f/purchases/requests/{id}/{status} //修改采购申请单状态


		// /api/f/purchases //查询采购单列表
		// /api/f/purchases/{id} //修改	采购单信息
		// /api/f/purchases //新增 采购单
		// /api/f/purchases/{id}/{status} //修改采购单状态
		// /api/f/purchases/{id}/products //采购单下新增采购产品
		// /api/f/purchases/{id}/products/{purchaseProductId} //修改采购单产品的价格和数量
		// /api/f/purchases/{id}/products/{purchaseProductId} //删除采购单下的产品
		// /api/f/purchases/{id}/outputins //生成入库单
		// /api/f/purchases/{id}/products/{purchaseProductId}/{status} //修改采购商品的状态
		// /api/f/purchases/{id}/products/{purchaseProductId}/{status} //修改采购商品的状态

		private String purchaseRequestsPermTpl = "0_purchasemng:purchasemng_purchasereq:"; //采购单申请管理
		private String purchasePermTpl = "0_purchasemng:purchasemng_purchase:"; //采购单管理

		private Pattern requestPattern = Pattern.compile(LwxfStringUtils.format("/api/f/purchases/requests(/{0}/{1})?",WebConstant.REG_ID_MATCH,"\\d{1}"));
		private Pattern purchasePattern = Pattern.compile(LwxfStringUtils.format("/api/f/purchases(/{0}(/({1}|outputins|products(/{0}(/{1})?)?))?)?",WebConstant.REG_ID_MATCH,"\\d{1}"));

		public PurchasePermissionValidator() {
			this.apiPrefix = "/api/f/purchases";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//采购申请管理 验证
			Matcher matcher = requestPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,purchaseRequestsPermTpl);
			}
			//采购单管理 验证
			matcher = purchasePattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,purchasePermTpl);
			}
			return null;
		}
	}

	class DesignSchemePermissionValidator extends BasePermissionValidator{

		// /api/f/designs/schemes //根据条件查询设计案例列表
		// /api/f/designs/schemes //新增设计案例
		// /api/f/designs/schemes/{id} //查询案例详情
		// /api/f/designs/schemes/{id} //修改设计案例
		// /api/f/designs/schemes/{id}/files //上传案例封面
		// /api/f/designs/schemes/{id}/submit //案例提交
		// /api/f/designs/schemes/{id}/disabled //案例下架
		// /api/f/designs/schemes/{id}/delete //案例删除
		// /api/f/designs/schemes/{id}/enable //案例启用


		// /api/f/designs/schemes/{id}/contents //新增案例内容
		// /api/f/designs/schemes/{id}/contents/{contentId} //修改案例内容
		// /api/f/designs/schemes/{id}/contents/{contentId}/files //上传案例内容图片
		// /api/f/designs/schemes/{id}/contents/{contentId}/files //删除案例内容中的图片
		// /api/f/designs/schemes/{id}/contents/{contentId} //删除某段内容


		// /api/f/designs/schemes/{id}/examine //审核案例



		// /api/f/designs/styles //根据条件查询全部的风格
		// /api/f/designs/styles //新增设计风格
		// /api/f/designs/styles/{id} //修改风格信息
		// /api/f/designs/styles/{id} //修改风格信息

		// /api/f/designs/doors //查询户型列表
		// /api/f/designs/doors //新增户型
		// /api/f/designs/doors/{id} //修改户型
		// /api/f/designs/doors/{id} //删除户型

		private String designSchemePermTpl = "0_schememng:schememng_design:"; //案例管理
		private String designSchemeExaminePermTpl = "0_schememng:schememng_examine:"; //案例审核管理
		private String designStylesPermTpl = "0_schememng:schememng_style:";//案例风格
		private String designDoorPermTpl = "0_schememng:schememng_door:";//案例户型

		private Pattern designSchemePattern = Pattern.compile(LwxfStringUtils.format("/api/f/designs/schemes(/{0}(/(files|submit|disabled|delete|enable|contents(/{0}(/files)?)?)?)?)?",WebConstant.REG_ID_MATCH));
		private Pattern designSchemeExaminePattern = Pattern.compile(LwxfStringUtils.format("/api/f/designs/schemes/{0}/examine",WebConstant.REG_ID_MATCH));
		private Pattern designStylesPattern = Pattern.compile(LwxfStringUtils.format("/api/f/designs/styles(/{0})?",WebConstant.REG_ID_MATCH));
		private Pattern designDoorPattern = Pattern.compile(LwxfStringUtils.format("/api/f/designs/doors(/{0})?",WebConstant.REG_ID_MATCH));

		public DesignSchemePermissionValidator() {
			this.apiPrefix = "/api/f/designs";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//案例验证
			Matcher matcher = designSchemePattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,designSchemePermTpl);
			}
			//案例审核验证
			matcher = designSchemeExaminePattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,designSchemeExaminePermTpl);
			}
			//案例风格验证
			matcher = designStylesPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,designStylesPermTpl);
			}
			//案例户型验证
			matcher = designDoorPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,designDoorPermTpl);
			}
			return null;
		}
	}

	class PaymentSimplePermissionValidator extends BasePermissionValidator{

		// /api/f/payment_simples 条件查询记账信息
		// /api/f/payment_simples 添加简单支付信息
		// /api/f/payment_simples/viewIndex 日常账首页展示信息
		// /api/f/payment_simples/uploadImages 日常账附件图片上传
		// /api/f/payment_simples/{paymentSimpleId} 根据ID查询日常账详细信息
		// /api/f/payment_simples/funds 获得科目信息
		// /api/f/payment_simples/{paymentSimpleId} 更新日常记账信息
		// /api/f/payment_simples/{paymentSimpleId} 删除记账信息
		// /api/f/payment_simples/users 获取财务人员信息列表

		private String paymentSimplePermTpl = "0_financingmng:financingmng_DailyAccount:"; //日常账

		private Pattern paymentSimplePattern = Pattern.compile(LwxfStringUtils.format("/api/f/payment_simples(/(viewIndex|uploadImages|funds|users|{0}))?",WebConstant.REG_ID_MATCH));

		public PaymentSimplePermissionValidator() {
			this.apiPrefix = "/api/f/payment_simples";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//日常账管理 验证
			Matcher matcher = paymentSimplePattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validateOperate(servletPath,action,paymentSimplePermTpl);
			}
			return null;
		}
	}

	class CompanyFinancePermissionValidator extends BasePermissionValidator{

		// /api/f/companyFinances/companies/payments 条件查询经销商
		// /api/f/companyFinances/{paymentId} 根据ID查询经销商详细信息
		// /api/f/companyFinances/add 添加经销商财务信息
		// /api/f/companyFinances/funds 获得科目信息
		// /api/f/companyFinances/ways 获得支付方式
		// /api/f/companyFinances/uploadImages 经销商管理图片上传

		private String companyFinancePermTpl = "0_financingmng:financingmng_dealerfinancing:";//经销商财务管理

		private Pattern companyPattern = Pattern.compile(LwxfStringUtils.format("/api/f/companyFinances/({0}|add|funds|ways|uploadImages|(companies/payments))",WebConstant.REG_ID_MATCH));

		public CompanyFinancePermissionValidator() {
			this.apiPrefix = "/api/f/companyFinances";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//经销商财务管理 验证
			Matcher matcher = companyPattern.matcher(servletPath);
			if (matcher.matches()){
				return this.validateOperate(servletPath,action,companyFinancePermTpl);
			}
			return null;
		}
	}

	class EmployeeDailyRecordPermissionValidator extends BasePermissionValidator{

		// /api/f/employees/dailyrecords  //新增日报
		// /api/f/employees/dailyrecords  //查询日报列表
		// /api/f/employees/dailyrecords/{id}  //修改日报列表
		// /api/f/employees/dailyrecords/{id}  //删除日志
		// /api/f/employees/dailyrecords/{id}/comment  //对日志评论
		// /api/f/employees/dailyrecords/{id}/comment/{commentId}  //删除评论

		private String dailyRecordPermTpl = "0_dailyrecordmng:dailyrecordsmng_dailyrecords:";//日志管理

		private Pattern dailyRecordPattern = Pattern.compile(LwxfStringUtils.format("/api/f/employees/dailyrecords(/{0}(/comment(/{0})?)?)?",WebConstant.REG_ID_MATCH));

		public EmployeeDailyRecordPermissionValidator(){
			this.apiPrefix = "/api/f/employees/dailyrecords";
		}

		@Override
		protected String doValidate(HttpServletRequest request, String action, String servletPath) {
			//日志管理验证
			Matcher matcher = dailyRecordPattern.matcher(servletPath);
			if(matcher.matches()){
				return this.validate(request,action,dailyRecordPermTpl);
			}
			return null;
		}
	}
}
