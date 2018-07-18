/**  
 * <b>Project Name:</b>blog  
 * <b>File Name:</b>DateUtils.java  
 * <b>Package Name:</b>cn.hy.blog.utils  
 * <b>Date:</b>2018年5月29日下午9:53:27  
 * Copyright (c) All Rights Reserved, 2018.   
 *  
 */
package cn.psl.hy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 蒹葭白露
 * @version
 * @since JDK 1.6
 * @Note <b>ClassName:</b>DateUtils <br/>
 *       <b>Function:</b>时间工具<br/>
 *       <b>Date:</b>2018年5月29日 下午9:53:27 <br/>
 */
public class DateUtils
{

	private static Date date;

	/**    
	 * @author 蒹葭白露    
	 * @return
	 * @Note 
	 * <b>getDateNo:</b>获取时间戳当ID<br/> 
	 */
	public static String getDateNo()
	{
		date = new Date();
		String no = date.getTime() + System.currentTimeMillis() + "";
		
		return no;
	}

	/**    
	 * @author 蒹葭白露    
	 * @return
	 * @Note 
	 * <b>getFmtDate:</b>获取带分秒的时间<br/> 
	 */
	public static String getFmtDate()
	{
		date = new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return df.format(date);
	}

}
