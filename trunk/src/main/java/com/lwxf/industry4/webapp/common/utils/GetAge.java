package com.lwxf.industry4.webapp.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/1/2 0002 18:55
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class GetAge {

	public Integer getAgeByBirth(Date birthday) {
		Integer age = 0;
		try {
			Calendar now = Calendar.getInstance();
			// 当前时间
			now.setTime(new Date());
			Calendar birth = Calendar.getInstance();
			birth.setTime(birthday);
			//如果传入的时间，在当前时间的后面，返回0岁
			if (birth.after(now)) {
				age = 0;
			} else {
				age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
				if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
					age += 1;
				}
			}
			return age;
		} catch (Exception e) {
			return 0;
		}
	}

}
