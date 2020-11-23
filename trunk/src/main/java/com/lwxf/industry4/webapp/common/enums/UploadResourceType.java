package com.lwxf.industry4.webapp.common.enums;

/**
 * 功能：上传文件的资源类型
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-06-15 17:48
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum UploadResourceType {
	SYSCFG(0,"syscfg"),
	STORECFG(1,"storecfg"),
	STORECARD(4,"storecard"),
	GOODS(2,"goods"),
	GOODSCOMMENT(3,"goodscomment"),
	QUICKSHARE(4,"quickshare"),
	GOODSSPEC(5,"goodsspec"),
	BRAND(6,"brand"),

	BACKGROUND(8,"background"),
	GOODSSHOW(9,"goodsshow"),
	STOREHOMENAV(10,"storehomenav"),
	VIDEOFILE(11,"videofile"),

	ADVERTISING(13,"advertising"),
	SCHEME_COVER(14,"scheme_cover"),
	SCHEME_PANORAMA(15,"scheme_panorama"),
	HEADLINKCOMMENT(16,"headline_comment"),
	HEADLINE(17,"headline"),
	RESERVATIONDESIGNRECORD(18,"reservation_design_record"),
	SCHEME_CONTENT(19,"scheme_content"),
	CUSTOM_ORDER(20,"custom_order"),
	PAYMENT(21,"payment"),
	DISPATCH_LIST(22,"dispatch_list"),
	AFTERSALE_APPLY(23,"aftersale_apply"),
	DESIGN_SCHEMES(24,"design_schemes"),
	PRODUCT(25,"product"),
	ACTIVITY(26,"activity"),
	COMPANY(27,"company"),
	FACTIVITY(28,"factivity"),
	CONTENT(29,"content"),
	PAYMENT_SIMPLE(30,"payment_simple"),
	DISPATCH_BILL(31,"dispatch_bill"),
	FCOMPANY(32,"fcompany"),
	EMPLOYEE(33,"employee"),//员工相关
	FCOMPANY_FINANCE(34,"fcompany_finance"), //F端后台财务经销商上传
	UPDATE_VERSION(35,"update_version"),//版本更新
	VISITING_CARD(36,"visiting_card"),//名片
	CONTRACT_FILE(37,""),//合同附件
	SUPPLIER_PRODUCT_FILE(38,"supplier_product_file"),//供應商商品圖片
	MATERIAL(39,"material"),//原材料图片
	BUSINESS_LICENSE(40,"business_license"),//营业执照
	FRONT_OF_ID_CARD(41,"front_of_id_card"),//身份证正面
	REVERSE_SIDE_OF_ID_CARD(42,"reverse_side_of_id_card"),//身份证反面
	DESIGN_CHART(43,"design_chart"),//设计图
	INSTALLATION_DIAGRAM(44,"installation_diagram"),//安装图
	DESIGN_SKETCH(45,"design_sketch"),//效果图
	RENOVATION(45,"design_sketch"),//店铺装修图
	COVER(12,"cover"),//封面
	AVATAR(7,"avatar"),//头像




	;



	/*LOGO(11,"logo"),
	HEADER(12,"header"),
	POSTER(13,"poster"),
	QRCODE(14,"qrcode")*/
	private int type;
	private String module; // 存数文件的路径

	UploadResourceType(int type,String module) {
		this.type = type;
		this.module = module;
	}

	public int getType() {
		return this.type;
	}

	public String getModule() {
		return module;
	}

	public static boolean validType(int type){
		UploadResourceType[] arr = values();
		for (int m=0,len = arr.length;m<len;m++){
			if(arr[m].type == type){
				return true;
			}
		}
		return false;
	}
}
