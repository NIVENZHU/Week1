package com.byteDance.newsProject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.byteDance.newsProject.entity.AdvDeviceLimit;
import com.byteDance.newsProject.entity.AdvLocationLimit;
import com.byteDance.newsProject.entity.Advertisement;
import com.byteDance.newsProject.mapper.AdvDeviceLimitMapper;
import com.byteDance.newsProject.mapper.AdvLocationLimitMapper;
import com.byteDance.newsProject.mapper.AdvertisementMapper;
import com.byteDance.newsProject.service.AdvertisementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("AdvertisementService")
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {
    @Resource
    private AdvertisementMapper advertisementMapper;
    @Resource
    private AdvLocationLimitMapper advLocationLimitMapper;
    @Resource
    private AdvDeviceLimitMapper advDeviceLimitMapper;
    /**
     * 16. 按照内容类型获取广告，分页输出
     * @param category 广告内容类型
     * @param page 页数
     * @param row 行数
     * @return
     */
    public IPage<Advertisement> selectAdvByCategory(String category, Integer page, Integer row){
        Page<Advertisement> p = new Page<Advertisement>(page,row);
        QueryWrapper<Advertisement> queryWrapper = new QueryWrapper<Advertisement>();
        queryWrapper.eq("category",category);
        IPage<Advertisement> pageObj = advertisementMapper.selectPage(p,queryWrapper);
        return pageObj;
    }

    /**
     * 17. 投放广告
     * @param title 广告标题
     * @param content 广告内容
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
            Float pushWeight, List<String> locationLimit,List<String> deviceLimit){

        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(title);
        advertisement.setContent(content);
        advertisement.setCategory(category);
        advertisement.setImageUrl(imageUrl);
        advertisement.setPushPosition(pushPosition);
        advertisement.setPushParty(pushParty);
        advertisement.setExpireDate(expireDate);
        advertisement.setPushWeight(pushWeight);
        advertisementMapper.insert(advertisement);
        //获取id
        Long id = advertisement.getAdvId();
        if(locationLimit.size()!=0){
            for(String location:locationLimit){
                AdvLocationLimit advLocationLimit = new AdvLocationLimit();
                advLocationLimit.setAdvId(id);
                advLocationLimit.setLocationLimit(location);
                advLocationLimitMapper.insert(advLocationLimit);
            }
        }
        if(deviceLimit.size()!=0){
            for(String device:deviceLimit){
                AdvDeviceLimit advDeviceLimit = new AdvDeviceLimit();
                advDeviceLimit.setAdvId(id);
                advDeviceLimit.setDeviceLimit(device);
                advDeviceLimitMapper.insert(advDeviceLimit);
            }
        }
        return advertisement;

    }







}
