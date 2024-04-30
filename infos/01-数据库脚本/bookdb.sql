/*
 Navicat Premium Data Transfer

 Source Server         : mysqlconn
 Source Server Type    : MySQL
 Source Server Version : 50519
 Source Host           : 127.0.0.1:3306
 Source Schema         : bookdb

 Target Server Type    : MySQL
 Target Server Version : 50519
 File Encoding         : 65001

 Date: 15/12/2021 18:16:57
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`
(
    `id`     int(11) NOT NULL AUTO_INCREMENT,
    `typeId` int(11) NOT NULL,
    `name`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `price`  float(10, 2
) NOT NULL,
  `desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publish` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stock` int(255) NOT NULL,
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book`
VALUES (1, 1, '罗小黑战记', 39.90,
        '雨夜，一只流落街头的小黑猫被少女罗小白带回家，起名罗小黑。\r\n\r\n　　罗小黑不是一只普通的猫咪，它极通人性，会蹲马桶，不吃猫粮，长长的尾巴甚至能分裂成多个名为“黑咻”的生物体。与此同时，名为“谛听”的神秘人物，发动手下三匹长着翅膀的狼，搜寻着罗小黑的下落。\r\n\r\n　　不久，罗小白带着小黑到乡下探望堂哥阿根和爷爷，由此发生了种种离奇玄幻事件……',
        'Images/cover/101-1.png', '北京联合出版社', 'MTJJ', 100, '东区-01-02');
INSERT INTO `book`
VALUES (2, 5, '制冷少女', 36.80,
        '《制冷少女》以吃货、大学、闺蜜、生活四个方面入手，以轻松的语言和段子式的小故事，讲述制冷少女的日常生活。\r\n漫画主角的造型是一个皮肤有点黑的女汉子，形象鲜明简单，风格突出，标示性强，马尾，T恤，短裤人字拖，充满浓浓的生活气息。\r\n这是一个可爱女孩的故事，也是你的、我的、她的，或许也是每一个女孩曾经经历的、即将经历的或即将经历的生活和故事。故事里的女汉子，生活里的真女神。',
        'Images/cover/101-2.png', '中国友谊出版公司出版社', '制冷少女', 94, '东区-01-03');
INSERT INTO `book`
VALUES (3, 2, '百年孤独', 39.60,
        '多年以后，奥雷连诺上校站在行刑队面前，准会想起父亲带他去参观\r\n冰块的那个遥远的下午。当时，马孔多是个二十户人家的村庄，一座座土\r\n房都盖在河岸上，河水清澈，沿着遍布石头的河床流去，河里的石头光滑\r\n、洁白，活象史前的巨蛋。这块天地还是新开辟的，许多东西都叫不出名\r\n字，不得不用手指指点点。每年三月，衣衫褴楼的吉卜赛人都要在村边搭\r\n起帐篷，在笛鼓的喧嚣声中，向马孔多的居民介绍科学家的***新发明。他\r\n们首先带来的是磁铁。一个身躯高大的吉卜赛人，自称梅尔加德斯，满脸\r\n络腮胡子，手指瘦得象鸟的爪子，向观众出色地表演了他所谓的马其顿炼\r\n金术士创造的世界第八奇迹。',
        'Images/cover/101-3.png', '南海出版社', '加西亚·马尔克斯 ', 105, '东区-01-04');
INSERT INTO `book`
VALUES (4, 2, '平凡的世界', 138.00,
        '他一个人在山里劳动歇息的时候，头枕手掌仰面躺在黄土地上，长久地望着高远的蓝天和悠悠飘飞的白云，眼里便会莫名地盈满了泪水。\r\n\r\n山野寂静无声，甚至能听见自己鬓角的血管在哏哏地跳动。这样的时候，他记忆的风帆会反复驶进往日的岁月。石圪节中学、原西县高中……尽管那时饥肠辘辘，有无数的愁苦，但现在想起来，那倒是他一生中度过的美妙的时光。他也不时地想起高中时班上的同学们：金波、顾养民、郝红梅、田晓霞、侯玉英……眼下，这些人都各走了各的路。金波正在黄原跟他父亲学开汽车。红梅和他一样，回村后当了小学教师，听说现在仍然当着。侯玉英的情况他现在不很清楚—他和跛女子早已断绝了“关系”。顾养民和田晓霞如同学们预料的那样，去年秋天都考上了大学。养民如愿地考进了省医学院，晓霞进了黄原师专中文系。',
        'Images/cover/101-5.png', '北京十月文艺出版社', '路遥', 97, '东区-01-04');
INSERT INTO `book`
VALUES (5, 2, '活着', 35.00, '我比现在年轻十岁的时候，获得了一个游手好闲的职业，去乡间收集民间歌谣。那一年的整个夏天，我如同一只乱飞', 'Images/cover/101-4.png', '北京十月文艺出版社',
        '余华', 100, '东区-01-02');
INSERT INTO `book`
VALUES (7, 2, '文城', 29.50,
        '关于一个人和他一生的寻找\r\n以及一群人和一个汹涌的年代\r\n“文城在哪里？”\r\n“总会有一个地方叫文城。”\r\n◆在溪镇人ZUI初的印象里，林祥福是一个身上披戴雪花，头发和胡子遮住脸庞的男人，有着垂柳似的谦卑和田地般的沉默寡言。哪怕后来成了万亩荡和木器社的主人，他身上的谦卑和沉默依旧没有变。他的过去和一座谜一样的城联系在了一起，没人知道他为什么要找一个不存在的地方。\r\n他原本不属于这里，他的家乡在遥远的北方。为了一个承诺他将自己连根拔起，漂泊至此。往后的日子，他见识过温暖赤诚的心，也见识过冰冷无情的血。ZUI终他徒劳无获，但许多人的牵挂和眼泪都留在了他身上。\r\n北京十月文艺出版社',
        'Images/cover/20211119180702949.png', '北京十月文艺出版社', '余华', 99, '');
INSERT INTO `book`
VALUES (8, 6, '漫画中国史', 39.90, '诙谐 幽默', 'Images/cover/20211119102713141.png', '中国人民出版社', '铲屎官', 100, '东区101-03');
INSERT INTO `book`
VALUES (9, 3, '凤凰架构', 99.90, '大型分布式系统', 'Images/cover/20211119103702345.png', '机械工业出版社', '周志明', 100, '东区101-03');
INSERT INTO `book`
VALUES (10, 4, '法制的细节new', 39.00,
        '《法治的细节》是中国政法大学法学教授罗翔新作的法学随笔，面向大众读者，从热点案件解读、法学理念科普、经典名著讲解等6大板块，普及法律常识与法治观念。内容包括辛普森案、电车难题、性同意制度等法律基本常识，或N号房、张玉环案等时事热点的案件，多维度培育法律思维，助力法治社会的构建，点亮每个人心中的法治之光。\r\n　　此外，本书还收录了罗翔在爆红后的心路历程，分享其求学成长经历，袒露其心境的变化与成熟，与读者一起通过阅读与思辨，走出生活中的迷茫时刻，在现实中共同完善法治的细节。',
        'Images/cover/20211119104154377.png', '云南出版社', '罗翔', 100, '东区101-03');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `name`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `pwd`     varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `typeId`  int(255) NOT NULL,
    `balance` float(255, 0
) NOT NULL,
  `regdate` date NOT NULL,
  `tel` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idNumber` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member`
VALUES (1, 'andy', 'andyliu', 1, 187, '2021-08-01', '13374645654', '300312199506150011');
INSERT INTO `member`
VALUES (2, 'ada', 'ada123', 3, 617, '2021-10-26', '13678096544', '430311199909120054');
INSERT INTO `member`
VALUES (3, 'tom', 'tom123', 1, 340, '2021-11-19', '13374645654', '430311199909120054');
INSERT INTO `member`
VALUES (4, 'kitty', '123', 1, 200, '2021-12-12', '13678096546', '331091199802140011');

-- ----------------------------
-- Table structure for membertype
-- ----------------------------
DROP TABLE IF EXISTS `membertype`;
CREATE TABLE `membertype`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `name`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `amount`   int(255) NOT NULL,
    `discount` int(255) NOT NULL,
    `recharge` int(255) NOT NULL,
    `keepDay`  int(255) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of membertype
-- ----------------------------
INSERT INTO `membertype`
VALUES (1, '普通会员', 5, 100, 100, 30);
INSERT INTO `membertype`
VALUES (2, '白银会员', 8, 80, 200, 50);
INSERT INTO `membertype`
VALUES (3, '黄金会员', 10, 60, 400, 90);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `memberId` int(11) NOT NULL,
    `bookId`   int(11) NOT NULL,
    `rentDate` date NOT NULL,
    `backDate` date NULL DEFAULT NULL,
    `deposit`  float(255, 0
) NULL DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `isbn` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record`
VALUES (1, 1, 3, '2021-10-30', '2021-12-14', 15, 1, '	\r\n978-7-302-12260-9');
INSERT INTO `record`
VALUES (2, 1, 1, '2021-12-13', '2021-12-14', 0, 1, '978-7-302-12260-9');
INSERT INTO `record`
VALUES (3, 1, 2, '2021-12-14', '2021-12-15', 0, 1, '978-7-302-12260-9');
INSERT INTO `record`
VALUES (4, 1, 3, '2021-11-13', '2021-12-15', 2, 1, '978-7-302-12260-9');
INSERT INTO `record`
VALUES (5, 1, 4, '2021-12-15', NULL, 41, 1, '978-7-302-12260-9');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `name`     varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `parentId` int(11) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type`
VALUES (1, '童书', 0);
INSERT INTO `type`
VALUES (2, '小说', 0);
INSERT INTO `type`
VALUES (3, '科技', 0);
INSERT INTO `type`
VALUES (4, '社科', 0);
INSERT INTO `type`
VALUES (5, '文艺', 0);
INSERT INTO `type`
VALUES (6, '动漫', 0);
INSERT INTO `type`
VALUES (7, '人文社科', 0);
INSERT INTO `type`
VALUES (8, '经管', 0);
INSERT INTO `type`
VALUES (13, '杂志', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`    int(11) NOT NULL AUTO_INCREMENT,
    `name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `pwd`   char(15) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL,
    `state` int(255) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, 'super', '123', 1);
INSERT INTO `user`
VALUES (2, 'admin', 'admin123', 1);
INSERT INTO `user`
VALUES (3, 'laowang', 'hehe', 0);

-- ----------------------------
-- View structure for recordview
-- ----------------------------
DROP VIEW IF EXISTS `recordview`;
CREATE
ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `recordview` AS
select r.id,
       m.name                                        as memberName,
       b.name                                        as bookName,
       date_format(r.rentDate, '%Y-%m-%d')           as rentDate,
       date_format(r.backDate, '%Y-%m-%d')           as backDate,
       date_add(r.rentDate, interval mt.keepDay DAY) as returnDate,
       r.deposit
from record r,
     book b,
     member m,
     memberType mt
where r.memberId = m.id
  and r.bookId = b.id
  and m.typeId = mt.id;

SET
FOREIGN_KEY_CHECKS = 1;
