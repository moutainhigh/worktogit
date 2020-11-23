package com.lwxf.industry4.webapp;

import com.lwxf.industry4.webapp.domain.entity.company.CompanyCertificates;
import com.lwxf.mybatis.tool.MySqlTool;
import com.lwxf.mybatis.tool.MySqlToolParams;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * 功能：根据数据库自动生成实体和mapper
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-26 10:07:46
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DaoMapper {
    @Test
    public void test_createOrAlterDb() {
        MySqlTool.initDbObjects();
    }
	/**
	 * wangmingyuan
	 * 自动生成microblog及microblog相关的实体和mapper
	 * @throws IOException
	 */
	@Test
	public void test_batchGenerateMicroblogDaoClassAndServiceClassAndDaoXmlFiles() throws IOException {
		// 生成的java和xml文件的输出目录（默认为桌面）
		String outputDir = "F:\\gencode\\app";
		//生成的包前缀
		String microblogPackageName  = "company";
		// 是否使用带分页的 selectByFilter
		boolean usePagedFilter = true;
		MySqlToolParams params = new MySqlToolParams("SunXianWei(17838625030@163.com)","2018 V1.0","老屋新房","com.lwxf.industry4");
		List<Class> entityList = Arrays.asList(CompanyCertificates.class);
		if (entityList.size() > 0) {
			for (Class c : entityList) {
				MySqlTool.generateDaoClassAndServiceClassAndXmlFiles(c, usePagedFilter, outputDir, microblogPackageName,params);
			}
		}

	}


}
