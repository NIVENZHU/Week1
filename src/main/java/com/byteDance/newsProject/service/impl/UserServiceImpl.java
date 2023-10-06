package com.byteDance.newsProject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.byteDance.newsProject.entity.*;
import com.byteDance.newsProject.mapper.*;
import com.byteDance.newsProject.service.UserService;
import com.byteDance.newsProject.service.exception.BussinessException;
import com.byteDance.newsProject.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private BrowseHistoryMapper browseHistoryMapper;
    @Resource
    private ContentMapper contentMapper;
    @Resource
    private FavoriteListMapper favoriteListMapper;
    @Resource
    private CommentMapper commentMapper;

    /**
     * 1.微信通过微信绑定小程序，创建用户
     *
     * @param openId       QQ/weChat
     * @param userName     姓名（认证）
     * @param birthday     生日（认证）
     * @param idCardNumber 身份证号（认证）
     * @return User对象
     */
    public User createUserByOpenId(String openId, String userName, Date birthday, Long idCardNumber) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("open_id", openId);
        List<User> userList = userMapper.selectList(queryWrapper);
        if (userList.size() > 0) {
            throw new BussinessException("e1", "用户已经存在");
        }
        User user = new User();
        user.setOpenId(openId);
        user.setUserName(userName);
        user.setBirthday(birthday);
        user.setIdCardNumber(idCardNumber);
        userMapper.insert(user);
        return user;
    }

    /**
     * 2. 用户编辑手机号,头像，昵称，openId，密码
     */
    public User editPhoneNumById(Long userId, Long phoneNumber) {
        User user = userMapper.selectById(userId);
        user.setPhoneNumber(phoneNumber);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_id", user.getUserId());
        userMapper.update(user, queryWrapper);
        return user;
    }

    public User editProfileById(Long userId, String profilePicture) {
        User user = userMapper.selectById(userId);
        user.setProfilePicture(profilePicture);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_id", user.getUserId());
        userMapper.update(user, queryWrapper);
        return user;
    }

    public User editNicknameById(Long userId, String nickname) {
        User user = userMapper.selectById(userId);
        user.setNickname(nickname);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_id", user.getUserId());
        userMapper.update(user, queryWrapper);
        return user;
    }

    public User editOpenIdById(Long userId, String openId) {
        User user = userMapper.selectById(userId);
        user.setOpenId(openId);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_id", user.getUserId());
        userMapper.update(user, queryWrapper);
        return user;
    }

    public User editPasswordById(Long userId, String password) {
        User user = userMapper.selectById(userId);
        //生成盐值随机数范围在1000-1999
        int salt = new Random().nextInt(1000) + 1000;
        String md5 = MD5Utils.md5Digest(password, salt);
        user.setPassword(md5);
        user.setSalt(salt);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_id", user.getUserId());
        userMapper.update(user, queryWrapper);
        return user;
    }

    /**
     * 3. 手机验证注册
     * （验证操作通过controller中对比session来判断，此处只负责执行创建用户）
     *
     * @param phoneNumber
     * @param userName
     * @param birthday
     * @param idCardNumber
     * @return
     */
    public User createUserByPhoneNum(Long phoneNumber, String userName, Date birthday, Long idCardNumber) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("phone_number", phoneNumber);
        List<User> userList = userMapper.selectList(queryWrapper);
        if (userList.size() > 0) {
            throw new BussinessException("e1", "用户已经存在");
        }
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setUserName(userName);
        user.setBirthday(birthday);
        user.setIdCardNumber(idCardNumber);
        userMapper.insert(user);
        return user;
    }

    /**
     * 4. 用户登录有三种，用户名密码，手机，openid
     *
     * @return User对象
     */
    public User loginByPassword(String userName, String password) {
        Boolean isExist = false;
        User user = new User();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_name", userName);
        List<User> userList = userMapper.selectList(queryWrapper);
        if (userList.size() == 0) {
            throw new BussinessException("e2", "用户不存在");
        }
        //可能存在重名的人
        for (User u : userList) {
            String md5 = MD5Utils.md5Digest(password, u.getSalt());
            if (md5.equals(u.getPassword())) {
                user = u;
                isExist = true;
            }
        }
        //如果密码不与任何用户匹配，则抛出异常
        if(!isExist){
            throw new BussinessException("e3","密码不正确");
        }
        return user;
    }
    //验证码对比在controller中实现
    public User loginByPhoneNum(Long phoneNumber){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("phone_number", phoneNumber);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            throw new BussinessException("e2", "用户不存在");
        }
        return user;
    }
    public User loginByOpenid(String openId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("open_id", openId);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            throw new BussinessException("e2", "用户不存在");
        }
        return user;
    }

    /**
     * 5. 获取新闻列表，进行分页处理
     * @param page 页数
     * @param rows 行数
     * @return
     */
    public IPage<Content> contentPaging(Integer page, Integer rows){
        Page<Content> p = new Page<Content>(page,rows);
        IPage<Content> pageObj = contentMapper.selectPage(p,new QueryWrapper<Content>());
        return pageObj;
    }

    /**
     * 6. 用户按类型浏览
     * @param page 页数
     * @param rows 行数
     * @return
     */
    public IPage<Content>categoryContentPaging(Integer page, Integer rows,String category){
        Page<Content> p = new Page<Content>(page,rows);
        QueryWrapper<Content> queryWrapper = new QueryWrapper<Content>();
        queryWrapper.eq("category",category);
        IPage<Content> pageObj = contentMapper.selectPage(p,queryWrapper);
        return pageObj;
    }

    /**
     * 7.用户按照标题和内容搜索新闻列表
     * @param page 页数
     * @param rows 行数
     * @param input 用户的输入
     * @return
     */
    public IPage<Content>contentPagingByTitleAndContent(Integer page, Integer rows,String input){
        List<Content> newContentList = new ArrayList<Content>();
        Page<Content> p = new Page<Content>(page,rows);
        QueryWrapper<Content> queryWrapper = new QueryWrapper<Content>();
        List<Content> contentList = contentMapper.selectList(queryWrapper);
        for(Content c:contentList){
            if(c.getTitle().indexOf(input)==-1&&c.getNewsText().indexOf(input)==-1){
                continue;
            }
            newContentList.add(c);
        }
        p.setRecords(newContentList);
        IPage<Content> pageObj = p;
        return pageObj;
    }

    /**
     * 8/1. 用户访问新闻
     * @param userId
     * @param newsId
     * @return
     */
    public BrowseHistory visitNewsByUser(Long userId, Long newsId){
        BrowseHistory browseHistory = new BrowseHistory();
        browseHistory.setNewsId(newsId);
        browseHistory.setUserId(userId);
        browseHistory.setBrowseTime(new Date());
        browseHistoryMapper.insert(browseHistory);
        //新闻的访问量加一
        Content content = contentMapper.selectById(newsId);
        content.setBrowseQuantity(content.getBrowseQuantity()+1);
        contentMapper.updateById(content);
        return browseHistory;
    }
    /**
     * 8/2. 用户下载新闻下的评论
     * @param newsId 新闻id
     * @return
     */
    public List<Comment> selectCommentByNews(Long newsId){
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>();
        queryWrapper.eq("news_id",newsId);
        List<Comment> commentList = commentMapper.selectList(queryWrapper);
        return commentList;
    }
    /**
     * 9/1. 用户对新闻进行收藏
     * @param userId 用户id
     * @param newsId 新闻id
     * @return 新闻对象
     */
    public Content insertFavoriteList(Long userId, Long newsId){
        FavoriteList favorite = new FavoriteList();
        favorite.setUserId(userId);
        favorite.setNewsId(newsId);
        favoriteListMapper.insert(favorite);
        Content content = contentMapper.selectById(newsId);
        content.setFavoriteQuantity(content.getFavoriteQuantity()+1);
        contentMapper.updateById(content);
        return content;
    }

    /**
     * 9/2. 用户对新闻进行分享
     * @param newsId
     * @return
     */
    public Content forwardContentToUser(Long newsId){
        Content content = contentMapper.selectById(newsId);
        content.setForwardQuantity(content.getForwardQuantity()+1);
        contentMapper.updateById(content);
        return content;
    }

    /**
     * 10.用户输入评论
     * @param userId 用户id
     * @param newsId 新闻id
     * @param content 评论内容
     * @param referenceId 引用的评论id
     * @return
     */
    public Comment insertComment(Long userId,Long newsId,String content,Long referenceId){
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setNewsId(newsId);
        comment.setContent(content);
        Date date = new Date();
        comment.setCreateTime(date);
        comment.setReferenceId(referenceId);
        //添加该评论进数据库
        commentMapper.insert(comment);
        return comment;
    }

    /**
     * 11. 用户对评论点赞和点踩
     * @param commentId
     * @return
     */
    public Comment updateLikeCount(Long commentId){
        Comment comment = commentMapper.selectById(commentId);
        comment.setLikeCount(comment.getLikeCount()+1);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>();
        queryWrapper.eq("comment_id",commentId);
        commentMapper.update(comment,queryWrapper);
        return comment;
    }
    public Comment updateDislikeCount(Long commentId){
        Comment comment = commentMapper.selectById(commentId);
        comment.setDislikeCount(comment.getDislikeCount()+1);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>();
        queryWrapper.eq("comment_id",commentId);
        commentMapper.update(comment,queryWrapper);
        return comment;
    }

    /**
     * 12.用户删除评论
     * @param commentId
     * @return
     */
    public Comment deleteComment(Long commentId,Long userId){
        Comment comment = commentMapper.selectById(commentId);
        if(comment.getUserId()!=userId){
            throw new BussinessException("e3","没有删除权限");
        }
        commentMapper.deleteById(commentId);
        return comment;
    }

    /**
     * 13.用户观看自己的评论历史,分页显示
     * @param userId 用户id
     * @param page 页数
     * @param row 行数
     * @return
     */
    public IPage<Comment> selectAllCommentsByUser(Long userId,Integer page,Integer row){
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.orderByDesc("create_time");
        Page<Comment> p = new Page<Comment>(page,row);
        IPage<Comment> pageObj = commentMapper.selectPage(p,queryWrapper);
        return pageObj;
    }

    /**
     * 14.用户查看自己的浏览记录
     * @param userId
     * @param page
     * @param row
     * @return
     */
    public List<Content> selectAllBrowseHistoryByUser(Long userId, Integer page, Integer row){
        List<Content> contentList = new ArrayList<Content>();
        Page<BrowseHistory> p = new Page<BrowseHistory>(page,row);
        QueryWrapper<BrowseHistory> queryWrapper1 = new QueryWrapper<BrowseHistory>();
        queryWrapper1.eq("user_id",userId);
        queryWrapper1.orderByDesc("browse_time");
        IPage<BrowseHistory> pageObj = browseHistoryMapper.selectPage(p,queryWrapper1);
        List<BrowseHistory> browseHistoryList = pageObj.getRecords();
        for(BrowseHistory browseHistory:browseHistoryList ){
            Long newsId = browseHistory.getNewsId();
            QueryWrapper<Content> queryWrapper2 = new QueryWrapper<Content>();
            queryWrapper2.eq("news_id",newsId);
            contentList.add(contentMapper.selectOne(queryWrapper2));
        }
        return contentList;
}


}



