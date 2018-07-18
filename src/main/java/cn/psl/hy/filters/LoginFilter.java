/**  
 * <b>Project Name:</b>blogboot  
 * <b>File Name:</b>LoginFilter.java  
 * <b>Package Name:</b>cn.psl.hy.blog.filters  
 * <b>Date:</b>2018年7月16日上午11:28:38  
 * Copyright (c) All Rights Reserved, 2018.   
 *  
 */
package cn.psl.hy.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;

/**
 * @author Huangyong
 * @version
 * @since JDK 1.6
 * @Note <b>ClassName:</b>LoginFilter <br/>
 *       <b>Function:</b>过滤器<br/>
 *       <b>Date:</b>2018年7月16日 上午11:28:38 <br/>
 */
@Order(value = 1)
@WebFilter(filterName = "loginFilter", urlPatterns = {"*.action"})
public class LoginFilter implements Filter
{

    private final String[] goUrl = {"login.action", "index.action", "check.action"};

    /**
     * TODO
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    /**
     * TODO
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
            FilterChain filterChain) throws IOException, ServletException
    {
        filterChain.doFilter(arg0, arg1);
        /*HttpServletRequest request = (HttpServletRequest)arg0;
        HttpServletResponse response = (HttpServletResponse)arg1;
        System.out.println(request.getRequestURL());
        for (String string : goUrl)
        {
            if(request.getRequestURL().indexOf(string) > -1)
            {
                filterChain.doFilter(arg0, arg1);
                return;
            }
        }

        Cookie[] cookie = request.getCookies();
        
        // 如果没有cookie， 说明用户没有登录, 那么就直接跳转到登录页面
         
        Map<String, Object> map = new HashMap<String, Object>();
        if(cookie == null)
        {
            request.getRequestDispatcher("/login.action").forward(arg0, arg1);
        }
        else
        {

            for (Cookie cookie2 : cookie)
            {
                map.put(cookie2.getName(), cookie2.getValue());
            }
            System.out.println(map.toString());
        }
*/
    }

    /**
     * TODO
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException
    {
        // TODO Auto-generated method stub

    }

}
