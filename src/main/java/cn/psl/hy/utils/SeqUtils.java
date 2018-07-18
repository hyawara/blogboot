/**  
 * <b>Project Name:</b>dangban  
 * <b>File Name:</b>SeqUtils.java  
 * <b>Package Name:</b>cn.boc.utils  
 * <b>Date:</b>2018年7月17日上午9:41:47  
 * Copyright (c) All Rights Reserved, 2018.   
 *  
 */
package cn.psl.hy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Huangyong
 * @version
 * @since JDK 1.6
 * @Note <b>ClassName:</b>SeqUtils <br/>
 *       <b>Function:</b>TODO<br/>
 *       <b>Date:</b>2018年7月17日 上午9:41:47 <br/>
 */
public class SeqUtils
{

    private static int              COUNT          = 0;

    private static SimpleDateFormat dateFormat     = new SimpleDateFormat(
            "yyyyMMddHHmmssSSS");

    private static int              DEFAULT_LENGTH = 26;

    public static synchronized int getNextValue()
    {
        if(COUNT > 999999999)
        {
            COUNT = 0;
        }
        return ++COUNT;
    }

    /**
     * 获取26位唯一字符串
     * 
     * @return
     */
    public static synchronized String getUniqueID()
    {
        return getUniqueID(null, DEFAULT_LENGTH);
    }

    /**
     * 获取26位唯一字符串，并且添加前缀
     * 
     * @param preStr
     * @return
     */
    public static synchronized String getUniqueID(String preStr)
    {
        return getUniqueID(preStr, DEFAULT_LENGTH);
    }

    /**
     * 获取指定位数的ID，最少位数为17位
     * 
     * @param width
     * @return
     */
    public static synchronized String getUniqueID(int width)
    {
        return getUniqueID(null, width);
    }

    /**
     * 获取指定位数的ID，并且添加前缀，最少位数为17位
     * 
     * @param preStr
     * @param width
     * @return
     */
    public static synchronized String getUniqueID(String preStr, int width)
    {
        if(COUNT > 999999999)
        {
            COUNT = 0;
        }

        if(preStr != null)
        {
            int length = preStr.length();
            int maxLength = width - 17;
            if(length > maxLength)
            {
                if(maxLength < 0)
                {
                    throw new RuntimeException(
                            "SeqUtils.getUniqueID不能生成小于17位的序号");
                }
                preStr = preStr.substring(0, maxLength);
                length = maxLength;
            }
            width -= length;
        }
        else
        {
            preStr = "";
        }

        String timeInfo = null;
        if(width >= 21)
        {
            timeInfo = dateFormat.format(new Date());
            width -= 17;
        }
        else
        {
            timeInfo = num2Str(System.currentTimeMillis(), 13);
            width -= 13;
        }

        return preStr + timeInfo + num2Str(++COUNT, width);
    }

    /**
     * 数字按照指定宽度
     * 
     * @param number
     * @param width
     * @return
     */
    public static String num2Str(long number, int width)
    {
        String numStr = String.valueOf(number);
        width -= numStr.length();
        if(width >= 0)
        {
            StringBuffer zeroBuff = new StringBuffer();
            while (zeroBuff.length() < width)
            {
                zeroBuff.append("0");
            }
            return zeroBuff.toString() + numStr;
        }
        else
        {
            return numStr.substring(Math.abs(width));
        }
    }
}
