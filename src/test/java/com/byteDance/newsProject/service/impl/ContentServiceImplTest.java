package com.byteDance.newsProject.service.impl;

import com.byteDance.newsProject.entity.Content;
import com.byteDance.newsProject.service.ContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ContentServiceImplTest {
    @Resource
    private ContentService contentService;

    /**
     * 15.投放新闻
     * 返回content对象
     */
    @Test
    public void createNews() {
        Content content=contentService.createNews("标题2","image2","图书",
                "mike","text1",(byte)0);
        System.out.println(content);
    }

}