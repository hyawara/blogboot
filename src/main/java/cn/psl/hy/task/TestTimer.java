/**  
 * <b>Project Name:</b>blogboot  
 * <b>File Name:</b>TestTimer.java  
 * <b>Package Name:</b>cn.psl.hy.blog.task  
 * <b>Date:</b>2018年7月18日下午2:36:14  
 * Copyright (c) All Rights Reserved, 2018.   
 *  
 */
package cn.psl.hy.task;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**  
 * @author Huangyong
 * @version   
 * @since JDK 1.6  
 * @Note 
 * <b>ClassName:</b>TestTimer <br/>  
 * <b>Function:</b>TODO<br/> 
 * <b>Date:</b>2018年7月18日 下午2:36:14 <br/>  
 */
@Configuration
@Component
@EnableScheduling
public class TestTimer

{
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Scheduled(cron="${timers.cron}")
    public void say () 
    {
        
    }
}
