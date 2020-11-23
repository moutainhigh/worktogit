package com.lwxf.industry4.webapp.common.crawlerUtil;

import com.lwxf.industry4.webapp.bizservice.jdproduct.JdProductInfoService;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.jdproduct.JdProductInfoDao;
import com.lwxf.industry4.webapp.domain.entity.jdproduct.JdProductInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lyh on 2019/12/2
 */
@Component
public class JDProduct {

    private static final Logger logger = LoggerFactory.getLogger(JDProduct.class);

    @Resource(name = "jdProductInfoService")
    private JdProductInfoService jdProductInfoService;

    public  void  insertJdProductInfo() {
        //分页查找手机数据 共10页商品数据
        for (int i = 1; i <= 10; i++) {
            //京东分页page为 1 3 5 7 .....  对应第一页 第二页....
            String url = "https://search.jd.com/Search?keyword=红田橱柜&enc=utf-8&wq=红田橱柜&pvid=&page=" + (2 * i - 1);
            try {
                String html = HttpClientUtils.doGet(url);
                parseIndex(html);
            }catch (Exception e){
                logger.error("京东数据抓取失败+++++++++++url:"+url);
            }

        }

    }


    //解析商品列表页
    private void parseIndex(String html) throws IOException, InterruptedException {
        Document document = Jsoup.parse(html);
        Elements productImage = document.select("img[source-data-lazy-img]");
        for (Element el : productImage) {
            Element title = el.parent();
            Elements select = title.select("a[title]");
            String producTitle = select.attr("title");//商品内容

            String productPicture = removeHeadStr(el.attr("source-data-lazy-img"));//获取商品图片
            String productHref = removeHeadStr(title.attr("href"));//京东商品连接
            Elements select1 = title.parent().parent().select("div.p-price");
            String productPrice = select1.select("i").text();//金额
            System.out.println("商品图片:\t" + productPicture);
            System.out.println("商品描述:\t" + producTitle);
            System.out.println("商品连接:\t" + productHref);
            System.out.println("商品价格:\t" + productPrice);
            JdProductInfo jdProductInfo = new JdProductInfo();
            jdProductInfo.setFetchingTime(new Date());
            jdProductInfo.setProductPicture(productPicture);
            jdProductInfo.setProductPrice(productPrice);
            jdProductInfo.setProducTitle(producTitle);
            jdProductInfo.setProductHref(productHref);
            jdProductInfo.setOrderBy(6);
            jdProductInfoService.add(jdProductInfo);
        }
    }

    /**
     * 去除双斜杠
     * @param str
     * @return
     */
    public static String removeHeadStr(String str) {
        int i = str.indexOf("//");
        String substring = "";
        if (i == 0) {
            substring = str.substring(2);
        }
        return substring;
    }
}
