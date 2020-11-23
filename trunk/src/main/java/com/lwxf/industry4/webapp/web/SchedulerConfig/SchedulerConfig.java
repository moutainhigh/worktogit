package com.lwxf.industry4.webapp.web.SchedulerConfig;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lyh on 2019/11/11
 */
@Component
public class SchedulerConfig {



    /**

     *

     * 单位ms

     *

     */

//    @Scheduled(fixedDelay = 1* 60 * 1000)
//
//    public void s1() throws Exception {
//
//        System.out.println(new Date());
//
//    }



    /**

     *

     *  0 0 10,14,16 * * ? 每天上午10点，下午2点，4点

     0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时

     0 0 12 ? * WED 表示每个星期三中午12点

     0 0 12 * * ?" 每天中午12点触发

     0 15 10 ? * *" 每天上午10:15触发

     0 15 10 * * ?" 每天上午10:15触发

     0 15 10 * * ? *" 每天上午10:15触发

     0 15 10 * * ? 2005" 2005年的每天上午10:15触发

     0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发

     0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发

     0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发

     0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发

     0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发

     0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发

     0 15 10 15 * ?" 每月15日上午10:15触发

     0 15 10 L * ?" 每月最后一日的上午10:15触发

     0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发

     0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发

     0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发

     */

//    @Scheduled(cron = "0/10 * * * * ?")//十秒钟触发一次
//
//    public void s2() {
//        //写需要定时触发的任务
//        System.out.println(++count);
//
//    }



    private int count = 0;

}

