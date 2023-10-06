package com.byteDance.newsProject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("user_browse_history")
public class BrowseHistory {
    @TableId(type = IdType.AUTO)
    private Long browseHistoryId;
    private Long userId;
    private Long newsId;
    private Date browseTime;

    public Long getBrowseHistoryId() {
        return browseHistoryId;
    }

    public void setBrowseHistoryId(Long browseHistoryId) {
        this.browseHistoryId = browseHistoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    @Override
    public String toString() {
        return "BrowseHistory{" +
                "browseHistoryId=" + browseHistoryId +
                ", userId=" + userId +
                ", newsId=" + newsId +
                ", browseTime=" + browseTime +
                '}';
    }
}