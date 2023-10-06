package com.byteDance.newsProject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;

@TableName("content")
public class Content {
    @TableId(type = IdType.AUTO)
    private Long newsId;
    private String title;
    private String cover;
    private Date createTime;
    private String category;
    private String author;
    private String newsText;
    private Integer browseQuantity;
    private Integer favoriteQuantity;
    private Integer forwardQuantity;
    private Byte pushMarker;


    public Integer getForwardQuantity() {
        return forwardQuantity;
    }

    public void setForwardQuantity(Integer forwardQuantity) {
        this.forwardQuantity = forwardQuantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public Integer getBrowseQuantity() {
        return browseQuantity;
    }

    public void setBrowseQuantity(Integer browseQuantity) {
        this.browseQuantity = browseQuantity;
    }

    public Integer getFavoriteQuantity() {
        return favoriteQuantity;
    }

    public void setFavoriteQuantity(Integer favoriteQuantity) {
        this.favoriteQuantity = favoriteQuantity;
    }


    public Byte getPushMarker() {
        return pushMarker;
    }

    public void setPushMarker(Byte pushMarker) {
        this.pushMarker = pushMarker;
    }

    @Override
    public String toString() {
        return "Content{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", createTime=" + createTime +
                ", category='" + category + '\'' +
                ", author='" + author + '\'' +
                ", newsText='" + newsText + '\'' +
                ", browseQuantity=" + browseQuantity +
                ", favoriteQuantity=" + favoriteQuantity +
                ", forwardQuantity=" + forwardQuantity +
                ", pushMarker=" + pushMarker +
                '}';
    }
}
