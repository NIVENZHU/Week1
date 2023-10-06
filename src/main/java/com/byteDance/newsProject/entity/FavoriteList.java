package com.byteDance.newsProject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_favorite_list")
public class FavoriteList {
    @TableId(type = IdType.AUTO)
    private Long favoriteListId;
    private Long newsId;
    private Long userId;

    public Long getFavoriteListId() {
        return favoriteListId;
    }

    public void setFavoriteListId(Long favoriteListId) {
        this.favoriteListId = favoriteListId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "favoriteList{" +
                "favoriteListId=" + favoriteListId +
                ", newsId=" + newsId +
                ", userId=" + userId +
                '}';
    }
}
