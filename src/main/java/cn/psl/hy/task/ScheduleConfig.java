/**  
 * <b>Project Name:</b>blogboot  
 * <b>File Name:</b>ScheduleConfig.java  
 * <b>Package Name:</b>cn.psl.hy.blog.task  
 * <b>Date:</b>2018年7月18日下午2:25:14  
 * Copyright (c) All Rights Reserved, 2018.   
 *  
 */
package cn.psl.hy.task;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;


/**  
 * @author Huangyong
 * @version   
 * @since JDK 1.6  
 * @Note 
 * <b>ClassName:</b>ScheduleConfig <br/>  
 * <b>Function:</b>TODO<br/> 
 * <b>Date:</b>2018年7月18日 下午2:25:14 <br/>  
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer, AsyncConfigurer
{

    //** 异步处理 */
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar){
        TaskScheduler taskScheduler = taskScheduler();
        taskRegistrar.setTaskScheduler(taskScheduler);
    }
 
    /** 定时任务多线程处理 */
    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.setThreadNamePrefix("task-");
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }
 
    /** 异步处理 */
    public Executor getAsyncExecutor(){
        Executor executor = taskScheduler();
        return executor;
    }
 
    /** 异步处理 异常 */
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
        return new SimpleAsyncUncaughtExceptionHandler();
    }


}
