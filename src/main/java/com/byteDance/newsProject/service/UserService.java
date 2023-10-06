package com.byteDance.newsProject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byteDance.newsProject.entity.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     *1.微信通过微信绑定小程序，创建用户
     * @param openId QQ/weChat
     * @param userName 姓名（认证）
     * @param birthday 生日（认证）
     * @param idCardNumber 身份证号（认证）
     * @return
     */
    public User createUserByOpenId(String openId, String userName, Date birthday, Long idCardNumber);

    /**
     * 2.用户编辑手机号,头像，昵称，密码,以及绑定微信/qq
     */
    public User editPhoneNumById(Long userId,Long phoneNumber);
    public User editProfileById(Long userId,String profilePicture );
    public User editNicknameById(Long userId,String nickname);
    public User editOpenIdById(Long userId,String openId);
    public User editPasswordById(Long userId,String password);

    /**
     * 3. 手机验证注册
     * （验证操作通过controller中对比session来判断，此处只负责执行创建用户）
     * @param phoneNumber
     * @param userName
     * @param birthday
     * @param idCardNumber
     * @return
     */
    public User createUserByPhoneNum(Long phoneNumber,String userName, Date birthday, Long idCardNumber);

    /**
     * 4. 用户登录有三种，用户名密码，手机，openid
     */
    public User loginByPassword(String userName,String password);
    public User loginByPhoneNum(Long phoneNumber);
    public User loginByOpenid(String openId);

    /**
     * 5. 获取新闻列表，进行分页处理
     * @param page 页数
     * @param rows 行数
     * @return
     */
    public IPage<Content> contentPaging(Integer page, Integer rows);

    /**
     * 6.用户按类型浏览
     * @param page 页数
     * @param rows 行数
     * @return
     */
    public IPage<Content>categoryContentPaging(Integer page, Integer rows,String category);

    /**
     * 7.用户按照标题和内容搜索新闻列表
     * @param page 页数
     * @param rows 行数
     * @param input 用户的输入
     * @return
     */
    public IPage<Content>contentPagingByTitleAndContent(Integer page, Integer rows,String input);

    /**
     * 8/1. 用户访问新闻
     * @param userId
     * @param newsId
     * @return
     */
    public BrowseHistory visitNewsByUser(Long userId,Long newsId);

    /**
     * 8/2. 用户下载新闻下的评论
     * @param newsId 新闻id
     * @return
     */
    public List<Comment> selectCommentByNews(Long newsId);

    /**
     * 9/1. 用户对新闻进行收藏
     * @param userId 用户id
     * @param newsId 新闻id
     * @return 新闻对象
     */
    public Content insertFavoriteList(Long userId, Long newsId);

    /**
     * 9/2. 用户对新闻进行分享
     * @param newsId
     * @return
     */
    public Content forwardContentToUser(Long newsId);

    /**
     * 10.用户输入评论
     * @param userId 用户id
     * @param newsId 新闻id
     * @param content 评论内容
     * @param referenceId 引用的评论id
     * @return
     */
    public Comment insertComment(Long userId,Long newsId,String content,Long referenceId);

    /**
     * 11. 用户对评论点赞和点踩
     * @param commentId
     * @return
     */
    public Comment updateLikeCount(Long commentId);
    public Comment updateDislikeCount(Long commentId);



    /**
     * 12.用户删除自己评论
     * @param commentId
     * @return
     */
    public Comment deleteComment(Long commentId,Long userId);

    /**
     * 13.用户观看自己的评论历史,分页显示
     * @param userId 用户id
     * @param page 页数
     * @param row 行数
     * @return
     */
    public IPage<Comment> selectAllCommentsByUser(Long userId,Integer page,Integer row);


    /**
     * 14.用户查看自己的浏览记录
     * @param userId
     * @param page
     * @param row
     * @return
     */
    public List<Content> selectAllBrowseHistoryByUser(Long userId, Integer page, Integer row);
}