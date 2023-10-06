package com.byteDance.newsProject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byteDance.newsProject.entity.Content;

public interface ContentService {


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
    public Content createNews(String title,String cover,String category,String author,String newsText,Byte pushMarker);

}
