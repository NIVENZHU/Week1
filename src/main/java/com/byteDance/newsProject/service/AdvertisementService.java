package com.byteDance.newsProject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byteDance.newsProject.entity.Advertisement;

import java.util.Date;
import java.util.List;

public interface AdvertisementService {
    /**
     * 16. 按照内容类型获取广告，分页输出
     * @param category 广告内容类型
     * @param page 页数
     * @param row 行数
     * @return
     */
    public IPage<Advertisement> selectAdvByCategory(String category,Integer page, Integer row);

    /**
     * 17. 投放广告
     * @param title 广告标题
     * @param content 广告内容
     * @param category 广告类型
     * @param imageUrl 图片url
     * @param pushPosition 投放位置
     * @param pushParty 投放方
     * @param expireDate 过期日期
     * @param pushWeight 投放权重
     * @param deviceLimit 设备限制
     * @param locationLimit 地区限制
     * @return
     */
    public Advertisement insertAdv(String title, String content,String category,
                                   String imageUrl,String pushPosition, String pushParty, Date expireDate,
                                   Float pushWeight, List<String> locationLimit,List<String> deviceLimit);



}
