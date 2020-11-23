package com.lwxf.industry4.webapp;

import com.github.binarywang.wxpay.util.SignUtils;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.security.DesTool;
import com.lwxf.commons.uniquekey.IIdGenerator;
import com.lwxf.commons.uniquekey.IdGererateFactory;
import com.lwxf.commons.uniquekey.LwxfWorkerIdGenerator;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.commons.utils.MD5Util;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.UserExtraUtil;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    private static Logger logger = LoggerFactory.getLogger(AppTest.class);
    private static final DesTool desTool = new DesTool();
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    @org.junit.Test
    public void testApp()
    {
        /*User user = new User();
        user.setName("aaa");
        user.setId("110");
        user.setMetadata("safsdfsadfsadf");
        User user2 = new User();
        user2.setName("bbb");
        user2.setId(222L);
        user2.setMetadata("bbbbbbb");
        Map<String, Object> map = new HashMap<>();
        map.put("user", user2);
        map.put("users", new ArrayList<User>() {{
            add(user);
        }});
        RequestResult rs = ResultFactory.generateRequestResult(map);
        Map<String,Object> rs2 = new HashedMap();
        rs2.put("data",map);
        JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
        jsonMapper.addFilterInclude(User.class, "id","metadata");
        System.out.println(jsonMapper.toJson(rs));
        System.out.println(jsonMapper.toJson(rs2));
        jsonMapper.addFilterAllExclude(User.class, "id","name");
        System.out.println(jsonMapper.toJson(rs));
        System.out.println(jsonMapper.toJson(rs2));*/

        System.out.println(this.desTool.decrypt("3ecf840c3186b7e57a97628198428853"));
    }

    @org.junit.Test
    public void testLongEauql(){
//        Long l1= 0L;
//        Assert.isTrue(l1.equals(0));

    }


    @org.junit.Test
    public void testByteEqual(){
      /*  byte b1=1;
        byte b2 = 1;
        Byte b3 = new Byte(b2);
        Assert.isTrue(b3==b1,"byte数据等于比较");*/
    }

    @org.junit.Test
    public void testEncryptDatabase(){
        //String localtest = "ip=port=dbname=user=password";
        /*String localtest = "192.168.1.4=3306=app=root=lwxf@123";
        String localtestAfter=desTool.encrypt(localtest);
        System.out.println("localtest 加密后的值：" + localtestAfter);
        Assert.isTrue(localtest.equals(desTool.decrypt(localtestAfter)));*/
//        String proptest = "127.0.0.1=3306=industry4_wisdom_cs=root=root";
//        String proptestAfter=desTool.encrypt(proptest);
//        System.out.println("industry4_fsec 加密后的值：" + proptestAfter);
//        Assert.isTrue(proptest.equals(desTool.decrypt(proptestAfter)));
//        String decrypt = desTool.decrypt("48f957191a2525992bedff2c8d0e796bb1e5e9a6d34fda92b610656a619b56c3f303fd37df618cfa85b9af73471e59866ce851423857b895a17c2436c68e1c94");
//        System.out.println(" 解密后的值：" + decrypt);


        //String localtest = "ip=port=dbname=user=password";
        String localtest = "127.0.0.1=3306=industry4_xzmw=lwxfdb=25J@z^m5aK";
        String localtestAfter=desTool.encrypt(localtest);
        System.out.println("localtest 加密后的值：" + localtestAfter);
        Assert.isTrue(localtest.equals(desTool.decrypt(localtestAfter)));

//        // 造易数据库配置
//        String zyLocal = "192.168.1.4=3306=demo=demo=demo@211";
//        String zyLocalAfter=desTool.encrypt(zyLocal);
//        System.out.println("zyLocal 加密后的值：" + zyLocalAfter);
//        Assert.isTrue(zyLocal.equals(desTool.decrypt(zyLocalAfter)));
//
//        String zyOnline = "127.0.0.1=3306=demo=demo=demo@211";
//        String zyOnlineAfter=desTool.encrypt(zyOnline);
//        System.out.println("zyOnline 加密后的值：" + zyOnlineAfter);
//        Assert.isTrue(zyOnline.equals(desTool.decrypt(zyOnlineAfter)));
       /* String dev = "192.168.1.4=3306=app=root=lwxf@123";
        String devAfter=desTool.encrypt(dev);
        System.out.println("dev 加密后的值：" + devAfter);
        Assert.isTrue(dev.equals(desTool.decrypt(devAfter)));

        String prod = "192.168.1.4=3306=app=root=lwxf@123";
        String prodAfter=desTool.encrypt(prod);
        System.out.println("prod 加密后的值：" + prodAfter);
        Assert.isTrue(prod.equals(desTool.decrypt(prodAfter)));

        String innertest = "192.168.1.4=3306=app=root=lwxf@123";
        String innertestAfter=desTool.encrypt(innertest);
        System.out.println("innertest 加密后的值：" + innertestAfter);
        Assert.isTrue(innertest.equals(desTool.decrypt(innertestAfter)));

        String test = "192.168.1.4=3306=app=root=lwxf@123";
        String testAfter=desTool.encrypt(test);
        System.out.println("test 加密后的值：" + testAfter);
        Assert.isTrue(test.equals(desTool.decrypt(testAfter)));

        System.out.println(" 解密："+desTool.decrypt("a1f1be81f87e3f97f9537754e12198eca1d150942ab22395159684fd5f5da4c11ba2e66411c5d95b7ec9ea1f6fd7ecc58ad04511d5a5a76c33e9fe1e421ca984ee2fcc5611753bd13e48136f45f1d90d51bd6b2bb4481527"));

        String localtestZzh = "192.168.1.4=3306=app=root=lwxf@123";
        String localtestZzhAfter=desTool.encrypt(localtestZzh);
        System.out.println("localtestZzhAfter 加密后的值：" + localtestZzhAfter);
        Assert.isTrue(localtestZzh.equals(desTool.decrypt(localtestZzhAfter)));*/
    }

    @org.junit.Test
    public void testPreventXssAttacks(){
        String ss = "萨拉的精神负担<script>alert(123);</script>上帝发誓电>风扇的风<是故事发生的<撒但双方都是对方";
        System.out.println(LwxfStringUtils.preventXssAttacks(ss));
    }

    @org.junit.Test
    public void testBooleanEquals(){
//        Boolean b = null;
//        System.out.println("instanceof : ".concat(String.valueOf(b instanceof Boolean)));
//        System.out.println("equals : ".concat(String.valueOf(Boolean.TRUE.equals(b))));
    }

    private static final String EMAIL_REG_PART="[^@<>\\./\\\\|{}, ^?#$%&*(){}+]";
    private static final String EMAIL_REG_TPL = "^{0}+@({0}+\\.)+{0}+$";
    private static final Pattern EAMIL_PATTERN = Pattern.compile(LwxfStringUtils.format(EMAIL_REG_TPL,EMAIL_REG_PART));
    private static final String email_valid_result_tpl = "验证结果：{} ->>> {}";
    @org.junit.Test
    public void testEmailReg(){
        String email = "abc123@sss.sasdfsdf.com";
        logger.debug(email_valid_result_tpl,email,EAMIL_PATTERN.matcher(email).matches());
        email = "abc123@sss.sas四大发生的dfsdf.com";
        logger.debug(email_valid_result_tpl,email,EAMIL_PATTERN.matcher(email).matches());
        email = "abc123@s\\ss.sas四大发生的dfsdf.com";
        logger.debug(email_valid_result_tpl,email,EAMIL_PATTERN.matcher(email).matches());
        email = "abc123@s^ss.sas四大发生的dfsdf.com";
        logger.debug(email_valid_result_tpl,email,EAMIL_PATTERN.matcher(email).matches());
        email = "ab@c123@s^ss.sas四大发生的dfsdf.com";
        logger.debug(email_valid_result_tpl,email,EAMIL_PATTERN.matcher(email).matches());
        email = "ab$c123@s^ss.sas四大发生的dfsdf.com";
        logger.debug(email_valid_result_tpl,email,EAMIL_PATTERN.matcher(email).matches());
        email = "ab:c123@s^ss.sas四大发生的dfsdf.com";
        logger.debug(email_valid_result_tpl,email,EAMIL_PATTERN.matcher(email).matches());
        email = "ab;c123@s^ss.sas四大发生的dfsdf.com";
        logger.debug(email_valid_result_tpl,email,EAMIL_PATTERN.matcher(email).matches());
        email = "ab|c123@s^ss.sas四大发生的dfsdf.com";
        logger.debug(email_valid_result_tpl,email,EAMIL_PATTERN.matcher(email).matches());
    }

    @org.junit.Test
    public void testGeneralEntityId(){
        IIdGenerator idGenerator = new LwxfWorkerIdGenerator(1);
        for (int i = 0; i < 10; i++) {
//            System.out.println(idGenerator.nextId().toString());
            System.out.println(idGenerator.nextStringId());
        }
    }
    @org.junit.Test
    public void testRedisEncryhpt(){
//        String s ="3ecf840c3186b7e52616dd1814c8d786ea8047057981cde8a934e90ee71cb28b0e13b51d4177efe1748e64f4598acab93a464a29bc3cd70d";
//        System.out.println("解密后："+desTool.decrypt(s));
//        String redisHost ="192.168.3.4";
//        String redisHostAfter =desTool.encrypt(redisHost);
//        System.out.println("redisHost："+redisHostAfter);
//        System.out.println("redisHost："+desTool.decrypt(redisHostAfter));
//        String redisPort ="6379";
//        String redisPortAfter =desTool.encrypt(redisPort);
//        System.out.println("redisPortAfter："+redisPortAfter);
//        System.out.println("redisPortAfter："+desTool.decrypt(redisPortAfter));
//        String redisPassword ="123456";
//        String redisPasswordAfter =desTool.encrypt(redisPassword);
//        System.out.println("redisPasswordAfter："+redisPasswordAfter);
//        System.out.println("redisPassword："+desTool.decrypt(redisPasswordAfter));

        String redisHost ="127.0.0.1";
        String redisHostAfter =desTool.encrypt(redisHost);
        System.out.println("redisHostAfter："+redisHostAfter);
        System.out.println("redisHost："+desTool.decrypt(redisHostAfter));
        String redisPort ="6379";
        String redisPortAfter =desTool.encrypt(redisPort);
        System.out.println("redisPortAfter："+redisPortAfter);
        System.out.println("redisPort："+desTool.decrypt(redisPortAfter));
        String redisPassword ="r2^8Lp@*xVi*";
        String redisPasswordAfter =desTool.encrypt(redisPassword);
        System.out.println("redisPasswordAfter："+redisPasswordAfter);
        System.out.println("redisPassword："+desTool.decrypt(redisPasswordAfter));

        /*String qnCfg = "a0452918574ee6c2f6d2c2f2b6ba795c1d679e1b4c05bea971d9d55b693a22aa9339f860f7fcb8acd3826650ff61659d0ad236f6963df6d975fd6f3edd43f636f06b5cc237ff1e2baf4c3b9f6369ee8f34c4c5e81c9a8a12";
        System.out.println("七牛配置："+desTool.decrypt(qnCfg));
        String qnCfg2 = "a0452918574ee6c2f6d2c2f2b6ba795c1d679e1b4c05bea971d9d55b693a22aa9339f860f7fcb8acd3826650ff61659d0ad236f6963df6d975fd6f3edd43f636f06b5cc237ff1e2baf4c3b9f6369ee8f3e3e0d2c54e06036a17c2436c68e1c94";
        System.out.println("七牛配置2："+desTool.decrypt(qnCfg2));

        String qn = "abc=123=ddd";
        System.out.println("qn = "+desTool.encrypt(qn));*/

    }

    protected final static long FILE_SIZE_LIMIT_UNIT_G = 1024 * 1024 * 1024;
    @org.junit.Test
    public void testInteger(){
        Integer limit = 200;
        long result = limit * FILE_SIZE_LIMIT_UNIT_G;
        System.out.println("result = "+result);
    }

    @org.junit.Test
    public void testWeixinConnectionEncrypt(){
        // 加密获取access token的配置（appId=appsecret=token=epm_secret）
        // --- 本地配置加密串
        String local = "wx728eff0c32996471=c58de1ebc11662d40e5c1bac6e4b4721=LMBMa70YX3=%D!y0%84BH";
        //String local = "wx86b7f7059a5b75a3=f18238aaf49919f0695eab833418d49e=LMBMa70YX3=%D!y0%84BH";
        String localAfter=desTool.encrypt(local);
        System.out.println("local加密后的值：" + localAfter);
        Assert.isTrue(local.equals(desTool.decrypt(localAfter)));
        System.out.println(" 红田解密："+desTool.decrypt("4913523b5d29f590084be9a5521ebbf6f32f44b6abe7a25ef76a47ecd13f6b6bc5cf377b9f42b4c554b67e79c9827f2ad30c174401d8d5a13be2fb6d80427dd672c7eaafca17cb88208ea9bac002f6aea9048d1d69576c9e531b34ef7a658b53"));
        System.out.println(" 老屋新房解密："+desTool.decrypt("312e193ed2d2776830de821410b68fe43b1d02abe8735095bed2f34dd45ad80bf9658ff63b9c713b0f89119b0c0ff3e2be8905051fabfa85095dabf38cc8013b07a8ac3e57c9ff33a17c2436c68e1c94"));
        // --- dev配置配置加密串
        String dev = "wx12e28b6b53d4541a=03f332b9897922c149440853d1b7e2eb=LMBMa70YX3=%D!y0%84BH";
        String devAfter=desTool.encrypt(dev);
        System.out.println("dev加密后的值：" + devAfter);
        Assert.isTrue(dev.equals(desTool.decrypt(devAfter)));

        // --- pub配置配置加密串(将pub串中微信配置信息改为服务号的)
        String pub = "wxbad0d3ebdb4e26dc=da2341014f7a8ca8c07368686017d518=p8hdhldpcOAqShDPqhdcoc9pt8taq2TV=b2@Xp^#Y%D";
        String pubAfter=desTool.encrypt(pub);
        System.out.println("pub加密后的值：" + pubAfter);
        Assert.isTrue(pub.equals(desTool.decrypt(pubAfter)));

        // --- pub配置配置加密串(将pub串中微信配置信息改为服务号的)
        String localtest = "wx9ba117a481ddb5ca=98e054b965199b180f646e4b2a7b8d08=fWhj17LBQ2=b2@Xp^#Y%D";
        String localhostAfter=desTool.encrypt(localtest);
        System.out.println("localtest加密后的值：" + localhostAfter);
        Assert.isTrue(localtest.equals(desTool.decrypt(localhostAfter)));

        // --- pub配置配置加密串(将pub串中微信配置信息改为服务号的)_王明远
        String localtest_wmy = "wx5001bf5cda52cbe9=4da8c56e389b5075b309055ce4d60277=LMBMa70YX3=%D!y0%84BH";
        String localtest_wmyAfter=desTool.encrypt(localtest_wmy);
        System.out.println("localtest_wmy加密后的值：" + localtest_wmyAfter);
        Assert.isTrue(localtest_wmy.equals(desTool.decrypt(localtest_wmyAfter)));

        System.out.println("当前配置：" + desTool.decrypt("108fac82c817d783d4cb118ededb4cc4735b24ff50312e9deb9889641d427f7d09d47822adcd4b0da14f9915061107446dfd98c222730b24f154577d99d620da88c3d12759d582c5d6a04a8eb602c403"));

        // --- pub配置配置加密串(将pub串中微信配置信息改为服务号的)_董世博
        String localtest_dsb = "wx4a896ded367c5e4a=050f9b9f9dbda3a96e1cdf517981aaff=LMBMa70YX3=%D!y0%84BH";
        String localtest_dsbAfter=desTool.encrypt(localtest_dsb);
        System.out.println("localtest_dsb加密后的值：" + localtest_dsbAfter);
        Assert.isTrue(localtest_dsb.equals(desTool.decrypt(localtest_dsbAfter)));
        System.out.println("当前配置：" + desTool.decrypt("22ba9a13a9d79d65c52558a730a74faae15081409feb75e36adffa624fc91fb2564b4c257278c42988ce53261f525302fe6cd52e0c8fa93df154577d99d620da88c3d12759d582c5d6a04a8eb602c403"));

        // --- pub配置配置加密串(将pub串中微信配置信息改为服务号的)_zjl
        String localtest_zjl = "wxddfea2b5449b32f7=1a2168292c1774e9bf30255d0eb3d3d4=LMBMa70YX3=%D!y0%84BH";
        String localtest_zjlAfter=desTool.encrypt(localtest_zjl);
        System.out.println("localtest_zjl加密后的值：" + localtest_zjlAfter);
        Assert.isTrue(localtest_zjl.equals(desTool.decrypt(localtest_zjlAfter)));
        System.out.println("当前配置：" + desTool.decrypt("22ba9a13a9d79d65c52558a730a74faae15081409feb75e36adffa624fc91fb2564b4c257278c42988ce53261f525302fe6cd52e0c8fa93df154577d99d620da88c3d12759d582c5d6a04a8eb602c403"));

        // --- pub配置配置加密串(将pub串中微信配置信息改为服务号的)_潘晨霄
        String localtest_pcx = "wxb7c5535e33a2d0a2=4d31e8aebefc260aade88f817726ffea=LMBMa70YX3=%D!y0%84BH";
        String localtest_pcxAfter=desTool.encrypt(localtest_pcx);
        System.out.println("localtest_pcx加密后的值：" + localtest_pcxAfter);
        Assert.isTrue(localtest_pcx.equals(desTool.decrypt(localtest_pcxAfter)));
        System.out.println("当前配置：" + desTool.decrypt("22ba9a13a9d79d65c52558a730a74faae15081409feb75e36adffa624fc91fb2564b4c257278c42988ce53261f525302fe6cd52e0c8fa93df154577d99d620da88c3d12759d582c5d6a04a8eb602c403"));
    }

    @org.junit.Test
    public void testRedisConnection(){
        Integer limit = 200;
        long result = limit * FILE_SIZE_LIMIT_UNIT_G;
        System.out.println("result = "+result);
    }

    @org.junit.Test
    public void testIdGenerate(){
        IdGererateFactory factory = new IdGererateFactory(1L);
        System.out.println("id = ".concat(factory.nextStringId()));
        System.out.println("id = ".concat(factory.nextStringId()));
        System.out.println("id = ".concat(factory.nextStringId()));
        System.out.println("id = ".concat(factory.nextStringId()));
    }

    @org.junit.Test
    public void testUserExtroEntry(){
        UserExtra extra = new UserExtra();
       // Md5Hash md5 = new Md5Hash("Id#p%4puuj6@");
        Md5Hash md5 = new Md5Hash("lwxf@26370");
        String md5Sring = md5.toString();
        System.out.println("md5加密后："+md5Sring);
        UserExtraUtil.saltingPassword(extra,md5Sring);
        System.out.println("salt : "+extra.getSalt());
        System.out.println("password : "+extra.getPassword());
        System.out.println("token : "+extra.getToken());
    }

    @org.junit.Test
    public void testShiroUtilMD5(){
        System.out.println("加密后："+ShiroUtil.generateMD5("abc123"));
    }

    @org.junit.Test
    public void testDecrypt(){
        // rabbit mq
        String port = desTool.decrypt("80ed74d61ca8fc66");
        System.out.println("port = "+port);
        String username = desTool.decrypt("bc1dba167e108172d883adef46542521");
        System.out.println("username = "+username);
        String password = desTool.decrypt("9b4305ffb9bd280a725f52141d726e5c");
        System.out.println("password = "+password);
        String host = desTool.decrypt("ae2b8105f3a9aca41000f7bb08025bf4");
        System.out.println("host = "+host);
    }

    @org.junit.Test
    public void testEncryptFomMQ(){
        // rabbit mq
        String port = desTool.encrypt("5672");
        System.out.println("port = "+port);
        String username = desTool.encrypt("lwxf");
        System.out.println("username = "+username);
        String password = desTool.encrypt("lwxf123");
        System.out.println("password = "+password);
        String host = desTool.encrypt("192.168.1.4");
        System.out.println("host = "+host);
    }

    /*@org.junit.Test
    public void testUnipueCodeGenerator(){
        UniquneCodeGenerator generator = new UniquneCodeGenerator();
        for(int i = 0;i<20;i++){
            System.out.println("生成订单编号：".concat(generator.getNextNo(UniquneCodeGenerator.UniqueResource.ORDER_NO)));
            System.out.println("生成物流编号：".concat(generator.getNextNo(UniquneCodeGenerator.UniqueResource.LOGISTICS_NO)));
            System.out.println("生成支付记录编号：".concat(generator.getNextNo(UniquneCodeGenerator.UniqueResource.PAID_RECORDS_NO)));
        }
    }*/

    @org.junit.Test
    public void testSignUtils(){
        Map<String,String> signMap = new HashMap<>();
        signMap.put("appId","wxbad0d3ebdb4e26dc");
        signMap.put("timeStamp","1533262469");
        signMap.put("nonceStr","898M5B5VH1H4I8432GWO8LFQV04MWM");
        signMap.put("package","prepay_id=wx03101445134921f6494afa6e2782002882");
        signMap.put("signType","MD5");

        System.out.println("paySign = ".concat(SignUtils.createSign(signMap,"MD5","ge9a8c811kjj97llshu5711bwq8mn6yn",false)));
    }

    @org.junit.Test
    public void testEncryptFomRongCloud(){
        // 格式：appKey=appSecret
        String rongCfg="8w7jv4qb82uzy=z2OTrmvNU6gM";
        System.out.println("rongCfg加密前：".concat(rongCfg));
        String rongCfgEncryptAfter=desTool.encrypt(rongCfg);
        System.out.println("rongCfg加密后：".concat(rongCfgEncryptAfter));
        Assert.isTrue(rongCfg.equals(desTool.decrypt(rongCfgEncryptAfter)),"加密和解密不一致");
    }

    @org.junit.Test
    public void testGenerateRongToken(){
        String atoken = MD5Util.textToMD5L32("0rbQTsyZamC+VfqqKWXmsLs3al8I9PnuMsLNjLnE2/BoeC2mq8zNE+VPyasW83D+9rQdLfVxeKjUgI5kYb7n8XJrf2ADgZQq");
        System.out.println("atoken = ".concat(atoken));
    }

    @org.junit.Test
    public void testEncryptYunpian(){
        // 格式：appKey=appSecret
        String apiKey="7cfa17600f19ca1b85669ca795eb3e88";
        System.out.println("apiKey加密前：".concat(apiKey));
        String apiKeyEncryptAfter=desTool.encrypt(apiKey);
        System.out.println("apiKey加密后：".concat(apiKeyEncryptAfter));
        Assert.isTrue(apiKey.equals(desTool.decrypt(apiKeyEncryptAfter)),"加密和解密不一致");
    }

    @org.junit.Test
    public void testJsonMapper(){
        JsonMapper jsonMapper = JsonMapper.createCommonMapper();
        JsonMapper jsonMapper2 = new JsonMapper();
        JsonMapper jsonMapper3 = JsonMapper.createAllToStringMapper();
        User user = new User();
        user.setFollowers(20);
        user.setId("sdfsds43312323");
        user.setCreated(new Date());
        user.setSex(1);
        user.setChangedLoginName(Boolean.FALSE);
        user.setMobile("1367890000");
        System.out.println("toJson：".concat(jsonMapper.toJson(user)));
        System.out.println("toJson2：".concat(jsonMapper2.toJson(user)));
        System.out.println("toJson3：".concat(jsonMapper3.toJson(user)));
    }

    @org.junit.Test
    public void testPattern(){
        Pattern p1 = Pattern.compile(LwxfStringUtils.format("/api/f/customorders(/finishedorders(/all)?|/{0}(/(info|amount|aftersales|demands/{0}|designs/{0}))?)?",WebConstant.REG_ID_MATCH));
        Pattern p2 = Pattern.compile(LwxfStringUtils.format("/api/f/customorders/{0}/orderevaluation$",WebConstant.REG_ID_MATCH));

        String servletPath = "/api/f/customorders/tewjwjwjwj3wq/orderevaluation";

       Matcher m =  p1.matcher(servletPath);
        if(m.matches()){
           System.out.println(servletPath.concat("路径匹配成功"));
        }else{
            System.out.println(servletPath.concat("路径匹配失败"));
        }
    }

    @org.junit.Test
    public void testPatternIdCard(){
        Pattern pattern = Pattern.compile("^[1-9][0-7]\\d{4}((19\\d{2}(0[13-9]|1[012])(0[1-9]|[12]\\d|30))|(19\\d{2}(0[13578]|1[02])31)|(19\\d{2}02(0[1-9]|1\\d|2[0-8]))|(19([13579][26]|[2468][048]|0[48])0229))\\d{3}(\\d|X|x)?");
        Matcher matcher = pattern.matcher("410721199510011074");
        if(matcher.matches()){
            System.out.println("是正确的");
        }else{
            System.out.println("NO!!!");
        }
    }

    @org.junit.Test
    public void testOrderNo(){
        String b = AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.ORDER_NO);
        System.out.println(b);
    }
}
