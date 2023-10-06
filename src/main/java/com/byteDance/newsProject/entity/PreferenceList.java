package com.byteDance.newsProject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_preference_list")
public class PreferenceList {
    @TableId(type = IdType.AUTO)
    private Long preferenceListId;
    private Long userId;
    private String category;

    public Long getPreferenceListId() {
        return preferenceListId;
    }

    public void setPreferenceListId(Long preferenceListId) {
        this.preferenceListId = preferenceListId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "PreferenceList{" +
                "preferenceListId=" + preferenceListId +
                ", userId=" + userId +
                ", category='" + category + '\'' +
                '}';
    }
}
