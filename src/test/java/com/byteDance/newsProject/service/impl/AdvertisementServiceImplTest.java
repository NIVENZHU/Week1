package com.byteDance.newsProject.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byteDance.newsProject.entity.AdvDeviceLimit;
import com.byteDance.newsProject.entity.AdvLocationLimit;
import com.byteDance.newsProject.entity.Advertisement;
import com.byteDance.newsProject.service.AdvertisementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AdvertisementServiceImplTest {
    @Resource
    private AdvertisementService advertisementService;

    /**
     * 16. 按照内容类型获取广告
     * 返回Ipage对象
     */
    @Test
    public void selectAdvByCategory() {
        IPage<Advertisement> pageObj = advertisementService.selectAdvByCategory("游戏",1,6);
        for(Advertisement adv:pageObj.getRecords()){
            System.out.println(adv);
        }
    }

    /**
     * 17. 投放广告
     * 返回Advertisement对象
     */
    @Test
    public void insertAdv() {
        List<String> locationLimits = new ArrayList<String>();
        List<String> deviceLimits = new ArrayList<String>();
        locationLimits.add("北京");
        locationLimits.add("上海");
        deviceLimits.add("iphoneX");
        deviceLimits.add("mate60");
        Advertisement adv = advertisementService.insertAdv("标题1","content1","car","image1",
                "header","mike",new Date(),0.22222f,locationLimits,deviceLimits);
        System.out.println(adv);
    }
}