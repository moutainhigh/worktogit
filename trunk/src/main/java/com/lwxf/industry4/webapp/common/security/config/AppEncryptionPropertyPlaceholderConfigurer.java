package com.lwxf.industry4.webapp.common.security.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import com.lwxf.commons.security.DesTool;
import com.lwxf.commons.security.EncryptionPropertyPlaceholderConfigurer;
import com.lwxf.commons.utils.LwxfStringUtils;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-09-14 16:42
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class AppEncryptionPropertyPlaceholderConfigurer extends EncryptionPropertyPlaceholderConfigurer {
	private DesTool desTool = new DesTool();
	private String rongCfgKey="lwxf.rongcloud.cfg";
	private String zyCfgKey="zy.db";
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		/*String rong = props.getProperty(rongCfgKey);
		if (LwxfStringUtils.isNotEmpty(rong)) {
			props.remove(rongCfgKey);
			String info = this.desTool.decrypt(rong);
			String[] arr = info.split("=");
			props.setProperty("lwxf.rongcloud.appKey", arr[0]);
			props.setProperty("lwxf.rongcloud.appSecret", arr[1]);
		}*/
		String db = props.getProperty(zyCfgKey);
		if (LwxfStringUtils.isNotEmpty(db)) {
			props.remove(zyCfgKey);
			String info = desTool.decrypt(db);
			String[] arr = info.split("=");
			props.setProperty("zy.db.host", arr[0]);
			props.setProperty("zy.db.port", arr[1]);
			props.setProperty("zy.db.database", arr[2]);
			props.setProperty("zy.db.username", arr[3]);
			props.setProperty("zy.db.password", arr[4]);
		}
		super.processProperties(beanFactoryToProcess, props);
	}
}
