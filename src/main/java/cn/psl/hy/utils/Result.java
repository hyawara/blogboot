/**  
 * <b>Project Name:</b>blog  
 * <b>File Name:</b>Result.java  
 * <b>Package Name:</b>cn.hy.blog.utils  
 * <b>Date:</b>2018年5月26日上午9:59:29  
 * Copyright (c) All Rights Reserved, 2018.   
 *  
 */
package cn.psl.hy.utils;

import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * @author huangyong
 * @version
 * @since JDK 1.6
 * @Note <b>ClassName:</b>Result <br/>
 *       <b>Function:</b>返回统一的json格式. <br/>
 *       <b>Date:</b>2018年5月26日 上午9:59:29 <br/>
 */
public class Result
{

    // 状态码
    private String              code;
    // 返回的消息
    private String              message;
    // 返回的json数据
    private Map<String, Object> data;

    /**
     * @author huangyong
     * @return Result
     * @Note <b>success:</b>操作成功方法<br/>
     */
    public static Result success()
    {
        Result result = new Result();
        result.setCode("000000");
        result.setMessage("操作成功");
        return result;
    }

    /**
     * @author huangyong
     * @param msg
     * @return Result
     * @Note <b>error:</b>操作失败方法<br/>
     */
    public static Result error(String msg)
    {
        Result result = new Result();
        result.setCode("999999");
        if(StringUtils.isEmpty(msg))
        {
            result.setMessage("操作失败");
        }
        else
        {
            result.setMessage(msg);
        }
        return result;

    }

    /**
     * code.
     * 
     * @return the code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * code.
     * 
     * @param code
     *            the code to set
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * message.
     * 
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * message.
     * 
     * @param message
     *            the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * data.
     * 
     * @return the data
     */
    public Map<String, Object> getData()
    {
        return data;
    }

    /**
     * data.
     * 
     * @param data
     *            the data to set
     */
    public void setData(Map<String, Object> data)
    {
        this.data = data;
    }

}
