package com.byteDance.newsProject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("adv_location_limit")
public class AdvLocationLimit {
    @TableId(type = IdType.AUTO)
    private Long limitId;
    private Long advId;
    private String locationLimit;

    public Long getLimitId() {
        return limitId;
    }

    public void setLimitId(Long limitId) {
        this.limitId = limitId;
    }

    public Long getAdvId() {
        return advId;
    }

    public void setAdvId(Long advId) {
        this.advId = advId;
    }

    public String getLocationLimit() {
        return locationLimit;
    }

    public void setLocationLimit(String locationLimit) {
        this.locationLimit = locationLimit;
    }

    @Override
    public String toString() {
        return "AdvLocationLimit{" +
                "limitId=" + limitId +
                ", advId=" + advId +
                ", locationLimit='" + locationLimit + '\'' +
                '}';
    }
}
