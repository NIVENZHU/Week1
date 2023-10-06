SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户id，主键',
    `user_name` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名（身份认证）',
    `birthday` DATE NOT NULL COMMENT '出生日期（身份认证）',
    `id_card_number` BIGINT(50) UNIQUE NOT NULL COMMENT '身份证号',
    `phone_number` BIGINT(50) UNIQUE COMMENT '手机号',
    `ver_code` varchar(64) COMMENT '手机验证码',
    `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '密码',
    `salt` int(255) COMMENT '盐值',
    `open_id` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci UNIQUE  COMMENT 'openId',
    `profile_picture` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  COMMENT '头像URL',
    `nickname` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '昵称',
    `last_login_time` DATETIME(0) COMMENT '最后一次的登录时间',
    primary key (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for favoriteList(用户偏好列表)
-- ----------------------------
DROP TABLE IF EXISTS `user_preference_list`;
CREATE TABLE `user_preference_list`
(
    `preference_list_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户id',
    `category` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '新闻id',
    primary key (`preference_list_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for favoriteList（用户收藏列表）
-- ----------------------------

DROP TABLE IF EXISTS `user_favorite_list`;
CREATE TABLE `user_favorite_list`
(
    `favorite_list_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '收藏列表id，主键',
    `news_id` BIGINT(20) NOT NULL COMMENT '新闻id',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户id',
    primary key (`favorite_list_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for browseHistory（用户浏览记录）
-- ----------------------------

DROP TABLE IF EXISTS `user_browse_history`;
CREATE TABLE `user_browse_history`
(
    `browse_history_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '观看记录id，主键',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户id',
    `news_id` BIGINT(20) NOT NULL COMMENT '新闻id',
    `browse_time` DATETIME(0) NOT NULL COMMENT '浏览时间',
    primary key (`browse_history_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for content（新闻）
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`
(
    `news_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '新闻id',
    `title` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '新闻标题',
    `cover` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '封面图片URL',
    `create_time` DATETIME(0) NOT NULL COMMENT '创建时间',
    `category` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型',
    `author` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者',
    `news_text` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '新闻正文',
    `browse_quantity` INT(255) NOT NULL DEFAULT 0 COMMENT '浏览量',
    `favorite_quantity` INT(255) NOT NULL  DEFAULT 0 COMMENT '收藏量',
    `forward_quantity` INT(255) NOT NULL DEFAULT 0 COMMENT '转发量',
    `push_marker` TINYINT(3) NOT NULL DEFAULT 0 COMMENT '付费推广标记(0没付费，1付费)',
    primary key (`news_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for comment（评论）
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `comment_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户id',
    `news_id` BIGINT(20) NOT NULL COMMENT '新闻id',
    `content` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
    `create_time` DATETIME(0) NOT NULL COMMENT '评论时间',
    `reference_id` BIGINT(20) COMMENT '引用评论的id',
    `like_count` INT(255) NOT NULL DEFAULT 0 COMMENT '点赞数',
    `dislike_count` INT(255) NOT NULL DEFAULT 0 COMMENT '点踩数',
    primary key (`comment_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for advertisement（广告）
-- ----------------------------
DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement`
(
    `adv_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '广告id',
    `title` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '广告标题',
    `content` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '广告内容',
    `category` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容类型',
    `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '广告图片URL',
    `push_position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '广告投放位置URL',
    `push_party` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '投放方',
    `expire_date` DATETIME(0) NOT NULL COMMENT '有效日期',
    `click_quantity` INT(255) NOT NULL DEFAULT 0 COMMENT '点击量',
    `push_quantity` INT(255) NOT NULL DEFAULT 0 COMMENT '曝光量',
    `push_weight` float(255, 4) NOT NULL DEFAULT 0.0000 COMMENT '投放权重',
    primary key (`adv_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for adv_locationLimit（广告地区限制）
-- ----------------------------

DROP TABLE IF EXISTS `adv_location_limit`;
CREATE TABLE `adv_location_limit`
(
    `limit_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `adv_id`  BIGINT(20) NOT NULL COMMENT '广告id',
    `location_limit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地区限制',
    primary key (`limit_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for adv_deviceLimit（广告设备限制）
-- ----------------------------

DROP TABLE IF EXISTS `adv_device_limit`;
CREATE TABLE `adv_device_limit`
(
    `device_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `adv_id`  BIGINT(20) NOT NULL COMMENT '广告id',
    `device_limit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设备限制',
    primary key (`device_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

