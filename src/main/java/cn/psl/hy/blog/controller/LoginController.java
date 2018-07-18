/**  
 * <b>Project Name:</b>blogboot  
 * <b>File Name:</b>LoginController.java  
 * <b>Package Name:</b>cn.psl.hy.blog.controller  
 * <b>Date:</b>2018年7月13日下午4:44:28  
 * Copyright (c) All Rights Reserved, 2018.   
 *  
 */
package cn.psl.hy.blog.controller;

import static org.mockito.Mockito.ignoreStubs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.assertj.core.data.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.psl.hy.blog.mapper.TBlogUserMapper;
import cn.psl.hy.blog.model.TBlogUser;

/**
 * @author Huangyong
 * @version 1.0
 * @since JDK 1.6
 * @Note <b>ClassName:</b>LoginController <br/>
 *       <b>Function:</b>登录控制器<br/>
 *       <b>Date:</b>2018年7月13日 下午4:44:28 <br/>
 */
@Controller
@RequestMapping(value = "/")
public class LoginController
{

    @Autowired
    private TBlogUserMapper userMapper;

    /**
     * @author Huangyong
     * @return
     * @Note <b>index:</b>登录<br/>
     */
    @RequestMapping(value = "login.action")
    public String login()
    {
        return "/page/login";
    }

    /**
     * @author Huangyong
     * @param userName
     *            用户名
     * @param password
     *            密码
     * @param response
     * @return map集合
     * @Note <b>checkUser:</b>检验用户登录<br/>
     */
    @RequestMapping(value = "check.action")
    @ResponseBody
    public Map<String, Object> checkUser(
            @RequestParam String userName,
            @RequestParam String password, 
            HttpServletResponse response,
            HttpServletRequest request)
    {
        
        
        Map<String, Object> map = new HashMap<String, Object>();

        TBlogUser blogUser = new TBlogUser();
        blogUser = this.userMapper.checkUser(userName, password);
        if(blogUser != null)
        {
            map.put("flag", "1");
            Cookie cookie = new Cookie("userName", userName);
            cookie.setMaxAge(3600 * 24 * 3);// 设置其生命周期
            Cookie cookie1 = new Cookie("password", password);
            cookie1.setMaxAge(3600 * 24 * 3);// 设置其生命周期
        }
        else
        {
            map.put("flag", "0");
        }
        return map;
    }

    /**
     * @author Huangyong
     * @return
     * @Note <b>login:</b>跳转到首页<br/>
     */
    @RequestMapping(value = "index.action")
    public String login(HttpServletRequest request)
    {
        return "/user/index";
    }
}
