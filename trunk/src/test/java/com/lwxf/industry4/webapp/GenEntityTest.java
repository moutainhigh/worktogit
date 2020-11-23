package com.lwxf.industry4.webapp;

import com.lwxf.industry4.webapp.common.utils.GenEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：根据配置，数据库表生成java实体
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-26 10:07:46
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class GenEntityTest {
	public static void main(String[] args) {
		//默认生成java文件路径
		String appointPath = "F:\\gencode\\industry4";
		//不允许修改的字段配置表　key 数据库表 value数据库字段列名
		Map<String, String> mapDisabledUpdate = new HashMap<>();
		mapDisabledUpdate.put("brand", "id,companyId");
		mapDisabledUpdate.put("goods_comment","id,memberId,created");
		mapDisabledUpdate.put("goods","id,created,creator,companyId");
		mapDisabledUpdate.put("goods_extend","id");
		mapDisabledUpdate.put("goods_tag","id,goods,tag");
		mapDisabledUpdate.put("tag","id,companyId");
		mapDisabledUpdate.put("good_type","id,companyId");
		mapDisabledUpdate.put("spec_option","id");
		mapDisabledUpdate.put("goods_spec","id,companyId");
		mapDisabledUpdate.put("goods_show","id");
		mapDisabledUpdate.put("cart", "id,created");
		mapDisabledUpdate.put("store_home_nav", "id,companyId");
		mapDisabledUpdate.put("system_config", "id");
		mapDisabledUpdate.put("store_config", "id,companyId");
		mapDisabledUpdate.put("video_file","id,created,creator");
		mapDisabledUpdate.put("video_type","id,created,creator");
		mapDisabledUpdate.put("advertising", "id,created,creator");
		// 公共类的表处理
		mapDisabledUpdate.put("upload_files", "path,name,mime,originalMime,creator,created");
		// 活动日志
		mapDisabledUpdate.put("system_activity", "id,company_id,event,created,creator,r1,r2,r3,scope");
		// 用户通知
		mapDisabledUpdate.put("user_notify", "id,companyId,systemActivityId,userId,grouping,created");
		// 行政区划
		mapDisabledUpdate.put("city_area","id,parentId,name,mergerName,shortName,mergerShortName,levelType,cityCode,zipCode,namePinyin,nameJianpin,fisrtChar,lng,lat");




		/************************ advertising********************/
		//包
		String cityareaPackageName = "cityarea";
		//表名集合
		List<String> cityareaList = Arrays.asList("city_area");
		cityareaList.stream().forEach(
				tableName -> new GenEntity(tableName, cityareaPackageName, appointPath, mapDisabledUpdate,"SunXianWei(17838625030@163.com)")
		);


//包
		String contentmngPackageName = "tgsm";
		//表名集合
		List<String> contentmngNameList = Arrays.asList("tgsm_attribute");
		contentmngNameList.stream().forEach(
				tableName -> new GenEntity(tableName, contentmngPackageName, appointPath, mapDisabledUpdate,"SunXianWei(17838625030@163.com)")
		);


	}
}
