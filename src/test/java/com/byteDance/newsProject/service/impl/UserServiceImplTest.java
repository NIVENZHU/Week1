package com.byteDance.newsProject.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byteDance.newsProject.entity.BrowseHistory;
import com.byteDance.newsProject.entity.Comment;
import com.byteDance.newsProject.entity.Content;
import com.byteDance.newsProject.entity.User;
import com.byteDance.newsProject.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceImplTest {
    @Resource
    private UserService userService;

    /**
     * 1. 用户通过微信登录绑定小程序，创建用户
     * 返回User对象
     */
    @Test
    public void createUserByOpenId() {
        User user = userService.createUserByOpenId("weChat1",
                "张一",new Date(),320503198704001991L);
        System.out.println(user);
    }

    /**
     * 2. 用户编辑个人信息
     * 返回用户对象
     */

    @Test
    public void editPhoneNumById() {
        userService.editPhoneNumById(1L,13862133133L);
    }

    @Test
    public void editProfileById() {
        userService.editProfileById(1L,"image1");
    }

    @Test
    public void editNicknameById() {
        userService.editNicknameById(1L,"mike");
    }

    @Test
    public void editOpenIdById() {
        userService.editOpenIdById(1L,"QQ1");
    }

    @Test
    public void editPasswordById() {
        userService.editPasswordById(1L,"123asd");
    }

    /**
     * 3. 用户通过手机验证注册
     * 返回User对象
     */
    @Test
    public void createUserByPhoneNum() {
        userService.createUserByPhoneNum(13862181110L,
                "张二",new Date(),320503199911112533L);
    }

    /**
     * 4. 用户通过密码，手机，以及微信或qq登录
     */
    @Test
    public void loginByPassword() {
        User user = userService.loginByPassword("张一","123asd");
        System.out.println(user);
    }

    @Test
    public void loginByPhoneNum() {
        User user = userService.loginByPhoneNum(1386218110L);
        System.out.println(user);
    }

    @Test
    public void loginByOpenid() {
        User user=userService.loginByOpenid("QQ1");
        System.out.println(user);
    }

    /**
     * 5. 获取主页新闻表
     * 返回Ipage对象
     */
    @Test
    public void contentPaging() {
        IPage<Content> pageObj= userService.contentPaging(1,2);
        List<Content> contentList = pageObj.getRecords();
        for(Content content:contentList){
            System.out.println(content);
        }
    }

    /**
     * 6. 用户按类型浏览新闻
     * 返回Ipage对象
     */
    @Test
    public void categoryContentPaging() {
        IPage<Content> pageObj = userService.categoryContentPaging(1,2,"游戏");
        for(Content content:pageObj.getRecords()){
            System.out.println(content);
        }
    }

    /**
     * 7. 用户按照标题和内容搜索新闻
     * 返回Ipage对象
     */
    @Test
    public void contentPagingByTitleAndContent() {
        IPage<Content> pageObj = userService.contentPagingByTitleAndContent(1,2,"内");
        for(Content content:pageObj.getRecords()){
            System.out.println(content);
        }
    }

    /**
     * 8.1 用户访问新闻，添加进访问历史记录，并对该新闻的访问量+1
     */
    @Test
    public void visitNewsByUser() {
        BrowseHistory browseHistory = userService.visitNewsByUser(1L,1L);
        System.out.println(browseHistory);
    }

    /**
     * 8.2 用户下载该新闻下的所有评论
     * 返回List对象
     */
    @Test
    public void selectCommentByNews() {
        List<Comment> commentList=userService.selectCommentByNews(1L);
        for(Comment comment:commentList){
            System.out.println(comment);
        }
    }

    /**
     * 9.1 用户对新闻收藏
     * 返回content对象
     */
    @Test
    public void insertFavoriteList() {
       Content content= userService.insertFavoriteList(1L,1L);
        System.out.println(content);
    }

    /**
     * 9.2 用户分享新闻
     * 返回content对象
     */
    @Test
    public void forwardContentToUser() {
        Content content = userService.forwardContentToUser(1L);
        System.out.println(content);
    }

    /**
     * 10. 用户对新闻添加评论
     * 返回comment对象
     */
    @Test
    public void insertComment() {
        Comment comment = userService.insertComment(1L,1L,"真好玩",null);
        System.out.println(comment);
    }

    /**
     * 11.用户对评论点赞或者点踩
     * 返回comment对象
     */
    @Test
    public void updateLikeCount() {
        Comment comment = userService.updateLikeCount(1L);
        System.out.println(comment);
    }

    @Test
    public void updateDislikeCount() {
        Comment comment = userService.updateDislikeCount(1L);
        System.out.println(comment);
    }

    /**
     * 12. 用户删除自己的评论
     * 返回comment对象
     */
    @Test
    public void deleteComment() {
        Comment comment = userService.deleteComment(3L,1L);
        System.out.println(comment);
    }

    /**
     * 13.用户查看自己的评论历史(分页，按评论创建时间降序排列)
     * 返回Ipage对象
     */
    @Test
    public void selectAllCommentsByUser() {
        IPage<Comment> pageObj = userService.selectAllCommentsByUser(1L,1,6);
        for(Comment comment:pageObj.getRecords()){
            System.out.println(comment);
        }
    }

    /**
     * 14.用户观看自己的浏览记录
     * 返回List对象
     */
    @Test
    public void selectAllBrowseHistoryByUser() {
        List<Content> contentList = userService.selectAllBrowseHistoryByUser(1L,1,6);
        for(Content content:contentList){
            System.out.println(content);
        }
    }

}