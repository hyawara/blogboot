/**  
 * <b>Project Name:</b>blogboot  
 * <b>File Name:</b>PageTest.java  
 * <b>Package Name:</b>cn.psl.hy.blog.test  
 * <b>Date:</b>2018年7月18日下午3:31:49  
 * Copyright (c) All Rights Reserved, 2018.   
 *  
 */
package cn.psl.hy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;

import cn.psl.hy.blog.mapper.TBlogUserMapper;
import cn.psl.hy.blog.model.TBlogUser;
import cn.psl.hy.utils.PageBean;

/**  
 * @author Huangyong
 * @version   
 * @since JDK 1.6  
 * @Note 
 * <b>ClassName:</b>PageTest <br/>  
 * <b>Function:</b>TODO<br/> 
 * <b>Date:</b>2018年7月18日 下午3:31:49 <br/>  
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageTest
{
    @Autowired
    private TBlogUserMapper userMapper;
    
    @Test
    public void findAll () 
    {
        PageHelper.startPage(1,5);
        PageBean<TBlogUser> pageBean = new PageBean<>();
        pageBean.setItems(this.userMapper.selectAll());
        System.out.println(pageBean.toString());
    }
}
