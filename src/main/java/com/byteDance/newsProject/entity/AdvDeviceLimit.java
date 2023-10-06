package com.byteDance.newsProject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("adv_device_limit")
public class AdvDeviceLimit {
    @TableId(type = IdType.AUTO)
    private Long deviceId;
    private Long advId;
    private String deviceLimit;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getAdvId() {
        return advId;
    }

    public void setAdvId(Long advId) {
        this.advId = advId;
    }

    public String getDeviceLimit() {
        return deviceLimit;
    }

    public void setDeviceLimit(String deviceLimit) {
        this.deviceLimit = deviceLimit;
    }

    @Override
    public String toString() {
        return "advDeviceLimit{" +
                "deviceId=" + deviceId +
                ", advId=" + advId +
                ", deviceLimit='" + deviceLimit + '\'' +
                '}';
    }
}

