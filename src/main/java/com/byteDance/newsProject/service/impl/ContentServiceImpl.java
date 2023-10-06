package com.byteDance.newsProject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.byteDance.newsProject.entity.Content;
import com.byteDance.newsProject.mapper.ContentMapper;
import com.byteDance.newsProject.service.ContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("ContentService")
@Transactional
public class ContentServiceImpl implements ContentService {
    @Resource
    private ContentMapper contentMapper;


    /**
     * 15. 投放新闻
     * @param title 新闻标题
     * @param cover 新闻封面图片url
     * @param category 类型
     * @param author 作者
     * @param newsText 内容
     * @param pushMarker 推广标记（0没付费，1付费）
     * @return 该新闻的对象
     */
    public Content createNews(String title,String cover,String category,String author,String newsText,Byte pushMarker){
        Content content = new Content();
        Date date = new Date();
        content.setTitle(title);
        content.setCover(cover);
        content.setCategory(category);
        content.setAuthor(author);
        content.setNewsText(newsText);
        content.setPushMarker(pushMarker);
        content.setCreateTime(date);
        contentMapper.insert(content);
        return content;
    }






}
