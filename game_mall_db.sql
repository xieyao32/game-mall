/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : game_mall_db

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 03/03/2023 23:58:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员用户名',
  `admin_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `locked` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 0);

-- ----------------------------
-- Table structure for game_carousel
-- ----------------------------
DROP TABLE IF EXISTS `game_carousel`;
CREATE TABLE `game_carousel`  (
  `game_id` bigint(0) NOT NULL COMMENT '游戏id',
  `game_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏名称',
  `game_img` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏图片',
  PRIMARY KEY (`game_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of game_carousel
-- ----------------------------
INSERT INTO `game_carousel` VALUES (2, 'PUBG: BATTLEGROUNDS', '/gameImg/2/p1.jpg');
INSERT INTO `game_carousel` VALUES (3, '巫师 3：狂猎', '/gameImg/3/p1.jpg');
INSERT INTO `game_carousel` VALUES (4, 'Divinity: Original Sin 2 - Definitive Edition', '/gameImg/4/p1.jpg');

-- ----------------------------
-- Table structure for game_img
-- ----------------------------
DROP TABLE IF EXISTS `game_img`;
CREATE TABLE `game_img`  (
  `game_id` bigint(0) NOT NULL COMMENT '游戏id',
  `game_img` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏图片'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of game_img
-- ----------------------------
INSERT INTO `game_img` VALUES (1, '/gameImg/1/p1.jpg');
INSERT INTO `game_img` VALUES (1, '/gameImg/1/p2.jpg');
INSERT INTO `game_img` VALUES (1, '/gameImg/1/p3.jpg');
INSERT INTO `game_img` VALUES (1, '/gameImg/1/p4.jpg');
INSERT INTO `game_img` VALUES (2, '/gameImg/2/p1.jpg');
INSERT INTO `game_img` VALUES (2, '/gameImg/2/p2.jpg');
INSERT INTO `game_img` VALUES (2, '/gameImg/2/p3.jpg');
INSERT INTO `game_img` VALUES (2, '/gameImg/2/p4.jpg');
INSERT INTO `game_img` VALUES (3, '/gameImg/3/p1.jpg');
INSERT INTO `game_img` VALUES (3, '/gameImg/3/p2.jpg');
INSERT INTO `game_img` VALUES (3, '/gameImg/3/p3.jpg');
INSERT INTO `game_img` VALUES (3, '/gameImg/3/p4.jpg');
INSERT INTO `game_img` VALUES (4, '/gameImg/4/p1.jpg');
INSERT INTO `game_img` VALUES (4, '/gameImg/4/p2.jpg');
INSERT INTO `game_img` VALUES (4, '/gameImg/4/p3.jpg');
INSERT INTO `game_img` VALUES (4, '/gameImg/4/p4.jpg');
INSERT INTO `game_img` VALUES (5, '/gameImg/5/p1.jpg');
INSERT INTO `game_img` VALUES (5, '/gameImg/5/p2.jpg');
INSERT INTO `game_img` VALUES (5, '/gameImg/5/p3.jpg');
INSERT INTO `game_img` VALUES (5, '/gameImg/5/p4.jpg');
INSERT INTO `game_img` VALUES (6, '/gameImg/6/p1.jpg');
INSERT INTO `game_img` VALUES (6, '/gameImg/6/p2.jpg');
INSERT INTO `game_img` VALUES (6, '/gameImg/6/p3.jpg');
INSERT INTO `game_img` VALUES (7, '/gameImg/7/p1.jpg');
INSERT INTO `game_img` VALUES (7, '/gameImg/7/p2.jpg');
INSERT INTO `game_img` VALUES (7, '/gameImg/7/p3.jpg');
INSERT INTO `game_img` VALUES (8, '/gameImg/8/p1.jpg');
INSERT INTO `game_img` VALUES (8, '/gameImg/8/p2.jpg');
INSERT INTO `game_img` VALUES (8, '/gameImg/8/p3.jpg');
INSERT INTO `game_img` VALUES (8, '/gameImg/8/p4.jpg');
INSERT INTO `game_img` VALUES (8, '/gameImg/8/p5.jpg');
INSERT INTO `game_img` VALUES (8, '/gameImg/8/p6.jpg');
INSERT INTO `game_img` VALUES (9, '/gameImg/9/p2.jpg');
INSERT INTO `game_img` VALUES (9, '/gameImg/9/p3.jpg');
INSERT INTO `game_img` VALUES (9, '/gameImg/9/p5.jpg');
INSERT INTO `game_img` VALUES (9, '/gameImg/9/p6.jpg');
INSERT INTO `game_img` VALUES (9, '/gameImg/9/p7.jpg');
INSERT INTO `game_img` VALUES (9, '/gameImg/9/p8.jpg');
INSERT INTO `game_img` VALUES (9, '/gameImg/9/p9.jpg');
INSERT INTO `game_img` VALUES (10, '/gameImg/10/p1.jpg');
INSERT INTO `game_img` VALUES (10, '/gameImg/10/p2.jpg');
INSERT INTO `game_img` VALUES (10, '/gameImg/10/p3.jpg');
INSERT INTO `game_img` VALUES (10, '/gameImg/10/p4.jpg');
INSERT INTO `game_img` VALUES (10, '/gameImg/10/p5.jpg');
INSERT INTO `game_img` VALUES (11, '/gameImg/11/p1.jpg');
INSERT INTO `game_img` VALUES (11, '/gameImg/11/p2.jpg');
INSERT INTO `game_img` VALUES (11, '/gameImg/11/p3.jpg');
INSERT INTO `game_img` VALUES (11, '/gameImg/11/p4.jpg');
INSERT INTO `game_img` VALUES (11, '/gameImg/11/p5.jpg');
INSERT INTO `game_img` VALUES (11, '/gameImg/11/p6.jpg');
INSERT INTO `game_img` VALUES (12, '/gameImg/12/p1.jpg');
INSERT INTO `game_img` VALUES (12, '/gameImg/12/p2.jpg');
INSERT INTO `game_img` VALUES (12, '/gameImg/12/p3.jpg');
INSERT INTO `game_img` VALUES (9, '/gameImg/9/p1.jpg');
INSERT INTO `game_img` VALUES (18, 'http://localhost:8888/upload/9f2944702bf747099c6d9c9800d7365c-p1.jpg');
INSERT INTO `game_img` VALUES (18, 'http://localhost:8888/upload/9cf0471f10a5413684af1844c29d4d29-cosmos-6693008_1920.jpg');
INSERT INTO `game_img` VALUES (19, 'http://localhost:8888/upload/3eb73ba8e77a49329256586e2204ad33-p1.jpg');
INSERT INTO `game_img` VALUES (19, 'http://localhost:8888/upload/db8a888ac6104b348921c8c4d46228da-stripes-6528728_1280.png');
INSERT INTO `game_img` VALUES (19, 'http://localhost:8888/upload/d21b042585a945a388d69a9a66bfd82f-tree-736885.jpg');
INSERT INTO `game_img` VALUES (19, 'http://localhost:8888/upload/1f75ca753d3b46688e29527bf50b01f7-sunflower-g927245aee_1920.jpg');
INSERT INTO `game_img` VALUES (21, 'http://localhost:8888/upload/002783acbdda4c6a8fd881b699929038-p1.jpg');
INSERT INTO `game_img` VALUES (21, 'http://localhost:8888/upload/9489b845647e424bbc220629d9c20db5-cosmos-6693008_1920.jpg');
INSERT INTO `game_img` VALUES (21, 'http://localhost:8888/upload/38c6e35575584ec5bd039dd16d63ca64-cherry-blossoms-6943659_1920.jpg');
INSERT INTO `game_img` VALUES (24, 'http://localhost:8888/upload/ceb998bd6fa44815ad2440e28e1f384d-p1.jpg');
INSERT INTO `game_img` VALUES (24, 'http://localhost:8888/upload/974c4e9336144cabb6c4f983444da227-tree-736885.jpg');
INSERT INTO `game_img` VALUES (24, 'http://localhost:8888/upload/ca37808408444dc9933655a57e20be33-wind-mills-6928590_1920.jpg');
INSERT INTO `game_img` VALUES (26, 'http://localhost:8888/upload/1b5bd1c109b44bceaf849100ea41da9e-p1.jpg');
INSERT INTO `game_img` VALUES (26, 'http://localhost:8888/upload/7b2248a46481404091501518d6d2359b-blue-morpho-butterfly-7007884_1920.jpg');
INSERT INTO `game_img` VALUES (26, 'http://localhost:8888/upload/bfd7065312d34bfa9bc2e027085bc7d2-aa.jpg');
INSERT INTO `game_img` VALUES (26, 'http://localhost:8888/upload/0a7b11de08cb4ff490834bd8dd923eee-cat-4894153_1920.jpg');
INSERT INTO `game_img` VALUES (25, 'http://localhost:8888/upload/aaf4b35d3c0945c58af97f045cd6557c-p1.jpg');
INSERT INTO `game_img` VALUES (25, 'http://localhost:8888/upload/d6d8d29d2cb44211bacdc22537f471c0-tree-736885.jpg');
INSERT INTO `game_img` VALUES (25, 'http://localhost:8888/upload/69e5e9e4b60a4d7bbba9096f6d55f091-rough-horn-g70131b975_1920.jpg');
INSERT INTO `game_img` VALUES (25, 'http://localhost:8888/upload/6bfd1573be164e3c94a99d76e2853206-sunflower-g927245aee_1920.jpg');
INSERT INTO `game_img` VALUES (27, 'http://localhost:8888/upload/b6b20b189f2f4ceab4aa9c39b1c87e8b-p1.jpg');
INSERT INTO `game_img` VALUES (27, 'http://localhost:8888/upload/05a4c4c855854ff6a73b38272f7ed20a-winter-gf9268ee09_1920.jpg');
INSERT INTO `game_img` VALUES (27, 'http://localhost:8888/upload/451ecbc0cb81423f880a109ea0af0543-cat-4894153_1920.jpg');
INSERT INTO `game_img` VALUES (23, 'http://localhost:8888/upload/a9b1bc02d59d4bf7aa5638c789f1af43-p1.jpg');
INSERT INTO `game_img` VALUES (23, 'http://localhost:8888/upload/d6d8d29d2cb44211bacdc22537f471c0-tree-736885.jpg');
INSERT INTO `game_img` VALUES (23, 'http://localhost:8888/upload/69e5e9e4b60a4d7bbba9096f6d55f091-rough-horn-g70131b975_1920.jpg');
INSERT INTO `game_img` VALUES (23, 'http://localhost:8888/upload/6bfd1573be164e3c94a99d76e2853206-sunflower-g927245aee_1920.jpg');
INSERT INTO `game_img` VALUES (13, 'http://localhost:8888/upload/8faf2772281241c3bfc3e16aad97ef9c-p1.jpg');
INSERT INTO `game_img` VALUES (13, 'http://localhost:8888/upload/85322a6fc3494188a07c695f3c4011b3-aa.jpg');
INSERT INTO `game_img` VALUES (13, 'http://localhost:8888/upload/0b538b7001824d4b9cb9e2952fc4e871-a.jpg');
INSERT INTO `game_img` VALUES (13, 'http://localhost:8888/upload/bc4de1be45294fd28067279cd37d0a56-animal-7000661_1920.jpg');
INSERT INTO `game_img` VALUES (16, 'http://localhost:8888/upload/1d1fcb85cbea453ab9944fbbb521e548-p1.jpg');
INSERT INTO `game_img` VALUES (16, 'http://localhost:8888/upload/1fa364163dbf48a68fb146cbe9d70072-flower-g6981d9425_1920.jpg');
INSERT INTO `game_img` VALUES (28, 'http://localhost:8888/upload/ff6afd6074234c7fbec521737bed6485-p1.jpg');
INSERT INTO `game_img` VALUES (28, 'http://localhost:8888/upload/175ac1b98480478d8184713292ae0b9f-a.jpg');
INSERT INTO `game_img` VALUES (28, 'http://localhost:8888/upload/749e24c5dd8244aab634eb81edb46c65-tree-gcf943ebeb_1920.jpg');
INSERT INTO `game_img` VALUES (28, 'http://localhost:8888/upload/9a4b62c5db3a43d5ba1e4f4f96e14e67-wallhaven-0pepqj.jpg');
INSERT INTO `game_img` VALUES (29, 'http://localhost:8888/upload/ef2de1877aeb4b1782a63c15a7ddc3dc-p1.jpg');
INSERT INTO `game_img` VALUES (29, 'http://localhost:8888/upload/e4c8acd914324451a72964d3f996d79d-lavender-6398415_1920.jpg');
INSERT INTO `game_img` VALUES (29, 'http://localhost:8888/upload/53c197523a8e4485b0132a1537027384-raindrop-6544618_1920.jpg');
INSERT INTO `game_img` VALUES (29, 'http://localhost:8888/upload/89e80f83f9bf4db6bfcf1ac6a1cb6433-rough-horn-g70131b975_1920.jpg');
INSERT INTO `game_img` VALUES (29, 'http://localhost:8888/upload/af76795ded574e89a6738b901741794d-OMEN Forest - Mystical Portal.jpg');
INSERT INTO `game_img` VALUES (29, 'http://localhost:8888/upload/05c225d4a69a4841a96246a12cd2e585-Brawler.jpg');
INSERT INTO `game_img` VALUES (30, 'http://localhost:8888/upload/abb323f02354492fb06f6ee37864d22d-p1.jpg');
INSERT INTO `game_img` VALUES (30, 'http://localhost:8888/upload/8264be641afc4cb486c9b3842cf5e3d2-OMEN Forest - Mystical Portal.jpg');
INSERT INTO `game_img` VALUES (30, 'http://localhost:8888/upload/652f1714b2b64582a3d6af1fb3f8db17-Saw Launcher.jpg');
INSERT INTO `game_img` VALUES (31, 'http://localhost:8888/upload/3ebb3046d3e1451084b29b1a5d069ce0-p1.jpg');
INSERT INTO `game_img` VALUES (31, 'http://localhost:8888/upload/e1faf89aa31848ca843712bab5990088-OMEN Forest - Mystical Portal.jpg');
INSERT INTO `game_img` VALUES (31, 'http://localhost:8888/upload/b8abab7781a34ddc998352d922e3cb36-The Chop Shop.jpg');
INSERT INTO `game_img` VALUES (31, 'http://localhost:8888/upload/0b67276cf8d543b8ac5bb33c886c3d2b-caucasus-g8963d8667_1920.jpg');
INSERT INTO `game_img` VALUES (31, 'http://localhost:8888/upload/fed9093adf5e44378383e417feec4f17-raindrop-6544618_1920.jpg');
INSERT INTO `game_img` VALUES (32, 'http://localhost:8888/upload/e333422b833840979c61c9b618331dc1-p1.jpg');
INSERT INTO `game_img` VALUES (32, 'http://localhost:8888/upload/cdb78a82a8704b418f1150f4f84404e8-rough-horn-g70131b975_1920.jpg');
INSERT INTO `game_img` VALUES (32, 'http://localhost:8888/upload/946b28b1ac8f412d8e004907d5019d41-school-6982073_1920.jpg');
INSERT INTO `game_img` VALUES (33, 'http://localhost:8888/upload/57691d6c55fc46949b727f8d74af30a4-p1.jpg');
INSERT INTO `game_img` VALUES (33, 'http://localhost:8888/upload/28fb09c4d4c044cb8eefd83263b0e10c-animal-7000661_1920.jpg');
INSERT INTO `game_img` VALUES (33, 'http://localhost:8888/upload/ea339d7e877848fd8d54198d78a429df-aa.jpg');
INSERT INTO `game_img` VALUES (34, 'http://localhost:8888/upload/53059f8b23a648b58a56cb59fa721001-p1.jpg');
INSERT INTO `game_img` VALUES (34, 'http://localhost:8888/upload/62c6d789da874c2bab67386098d66a44-ss_967a6e4318040094313e7c0cd02948fa428032a1.1920x1080.jpg');
INSERT INTO `game_img` VALUES (34, 'http://localhost:8888/upload/487b3d09efc348b1aa455bb48f4b86c7-ss_69deba1e5680d9eefccf62332aff7554b3a8300e.jpg');
INSERT INTO `game_img` VALUES (34, 'http://localhost:8888/upload/3955cc27a706431f943c3254c468340b-ss_17a92f289bb32a76ac114538e31a7137b15ce576.jpg');
INSERT INTO `game_img` VALUES (35, 'http://localhost:8888/upload/451735814f2f432d8d5f38b1e1ee4ef4-p1.jpg');
INSERT INTO `game_img` VALUES (35, 'http://localhost:8888/upload/1c8ba96e0ce4409385758f9a413486aa-ss_6c771fd2bff6a09d39541d0aee04a7f663927e0f.1920x1080.jpg');
INSERT INTO `game_img` VALUES (35, 'http://localhost:8888/upload/801ecc91b9ba4038936eddedb36cc250-ss_a098061e7aaf52410030a012d07582b11ff4cb72.1920x1080.jpg');
INSERT INTO `game_img` VALUES (36, 'http://localhost:8888/upload/3443d1e67992498394b3006a40f1f9cf-p1.jpg');
INSERT INTO `game_img` VALUES (36, 'http://localhost:8888/upload/6f7c6aee0cb14f029de13a5893211931-ss_967a6e4318040094313e7c0cd02948fa428032a1.1920x1080.jpg');
INSERT INTO `game_img` VALUES (37, 'http://localhost:8888/upload/957c9bf05aec4085ae7c37ff14d30a39-p1.jpg');
INSERT INTO `game_img` VALUES (37, 'http://localhost:8888/upload/a77add364ea54f1cb6f536cf058bd01f-2.jpg');
INSERT INTO `game_img` VALUES (37, 'http://localhost:8888/upload/26e19a9fdd524a1db148b6e9337abde1-3.jpg');
INSERT INTO `game_img` VALUES (38, 'http://localhost:8888/upload/e6db85116ab94f7caf6e741f283e63f3-p1.jpg');
INSERT INTO `game_img` VALUES (38, 'http://localhost:8888/upload/70de5cac15144534a65e6e12655ee3b9-3.jpg');
INSERT INTO `game_img` VALUES (38, 'http://localhost:8888/upload/1afd80af68b642eca1e9a753c6950771-2.jpg');
INSERT INTO `game_img` VALUES (39, 'http://localhost:8888/upload/519f6855ca374d06bdd02e6620cf6f47-p1.jpg');
INSERT INTO `game_img` VALUES (39, 'http://localhost:8888/upload/6217569e87d642e58a597f0400469d67-2.jpg');
INSERT INTO `game_img` VALUES (39, 'http://localhost:8888/upload/76d970abfee24f68a4622f14deaeb148-3.jpg');
INSERT INTO `game_img` VALUES (40, 'http://localhost:8888/upload/3f59fb1f37dd45e0bc947e6b6c821a74-p1.jpg');
INSERT INTO `game_img` VALUES (40, 'http://localhost:8888/upload/0ea6bbd86f1c420cbc78bf586eb1b1a0-2.jpg');
INSERT INTO `game_img` VALUES (40, 'http://localhost:8888/upload/25e098167cdb41ecbfc60d57e3aa2954-3.jpg');

-- ----------------------------
-- Table structure for game_info
-- ----------------------------
DROP TABLE IF EXISTS `game_info`;
CREATE TABLE `game_info`  (
  `game_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '游戏id',
  `type_id` bigint(0) NOT NULL COMMENT '游戏类型id',
  `game_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏名称',
  `game_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏描述',
  `game_req` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏要求',
  `game_price` double NOT NULL COMMENT '游戏价格',
  `game_discount` double NOT NULL DEFAULT 0 COMMENT '游戏折扣',
  `quantity_sale` int(0) NOT NULL DEFAULT 0 COMMENT '卖出数量',
  `game_ctime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '游戏添加时间',
  `game_utime` datetime(0) NULL DEFAULT NULL COMMENT '游戏上架时间',
  `game_status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '游戏上架状态（0-未上架，1-上架）',
  PRIMARY KEY (`game_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of game_info
-- ----------------------------
INSERT INTO `game_info` VALUES (1, 1, 'Counter-Strike: Global Offensive', '《反恐精英：全球攻势》（CS: GO）延续了 1999 年原作在团队竞技类游戏上取得的傲人成就。CS: GO 的特色包含全新的地图、人物、武器、全新的游戏模式，并提供翻新后的 CS 经典内容（de_dust2 等）。', '操作系统: Windows® 7/Vista/XP 或更新版本;处理器: 英特尔® 酷睿™2 双核处理器 E6600 或超威羿龙™ X3 8750 以上;内存: 2 GB RAM;显卡: 显卡须为 256 MB 以上，且应兼容 DirectX 9 并支持 Pixel Shader 3.0;DirectX 版本: 9.0c;存储空间: 需要 15 GB 可用空间', 96, 0, 84, '2022-08-02 19:41:54', '2022-08-02 19:41:54', 1);
INSERT INTO `game_info` VALUES (2, 1, 'PUBG: BATTLEGROUNDS', '着陆，搜寻，生存！免费游玩《PUBG绝地求生》。选取最佳战略位置着陆，搜寻武器和补给，并成为最后的幸存者小队，屹立于千变万化的战场之上。组队加入战场，体验原汁原味的战术竞技，尽在《PUBG绝地求生》。', '需要 64 位处理器和操作系统;操作系统: 64-bit Windows 7, Windows 8.1, Windows 10;处理器: Intel Core i5-4430 / AMD FX-6300;内存: 8 GB RAM;显卡: NVIDIA GeForce GTX 960 2GB / AMD Radeon R7 370 2GB;DirectX 版本: 11;网络: 宽带互联网连接,存储空间: 需要 40 GB 可用空间', 0, 0, 85, '2022-08-02 19:55:06', '2022-08-02 19:55:06', 1);
INSERT INTO `game_info` VALUES (3, 2, '巫师 3：狂猎', '《巫师：狂猎》是一款情节驱动的开放世界角色扮演游戏。本作的故事发生在一个令人目眩神迷的奇幻宇宙里，玩家做出的每一个选择都意义重大，同时也要面对影响深远的后果。在《巫师》这个广袤的开放世界中，您可以尽情探索商业都市、海盗岛屿、危险的山隘和被遗忘的洞窟。', '操作系统: 64-bit Windows 7, 64-bit Windows 8 (8.1) or 64-bit Windows 10;处理器: Intel CPU Core i5-2500K 3.3GHz / AMD CPU Phenom II X4 940;内存: 6 GB RAM;显卡: Nvidia GPU GeForce GTX 660 / AMD GPU Radeon HD 7870;存储空间: 需要 35 GB 可用空间', 127, 0, 805, '2022-08-02 20:01:55', '2022-08-02 20:01:55', 1);
INSERT INTO `game_info` VALUES (4, 2, 'Divinity: Original Sin 2 - Definitive Edition', '圣者已逝，虚空迫近。而你体内隐藏的力量很快就要觉醒。捍卫神性的战斗已经开始，请你做出明智的选择，对一切保持警惕：黑暗正潜伏于每个人的心中。', '需要 64 位处理器和操作系统;操作系统: Windows 7 SP1 64-bit or Windows 8.1 64-bit or Windows 10 64-bit;处理器: Intel Core i5 or equivalent;内存: 4 GB RAM;显卡: NVIDIA® GeForce® GTX 550 or ATI™ Radeon™ HD 6XXX or higher;DirectX 版本: 11;存储空间: 需要 60 GB 可用空间', 179, 0, 102, '2022-08-02 20:06:37', '2022-08-02 20:06:37', 1);
INSERT INTO `game_info` VALUES (5, 3, 'Sid Meier’s Civilization® VI', '《文明VI》带给玩家与世界互动的新方式：城市现在可以在地图上实际地扩张，积极钻研科技和文化可以解锁新潜能，互相竞争的领袖会依据历史所记载的个人特征去追求自我宗旨，而且玩家可通过五种方式之一取得游戏胜利。', '需要 64 位处理器和操作系统;操作系统: Windows 7x64 / Windows 8.1x64 / Windows 10x64;处理器: Intel Core i3 2.5 Ghz or AMD Phenom II 2.6 Ghz or greater;内存: 4 GB RAM;显卡: 1 GB & AMD 5570 or nVidia 450 or Intel Integrated Graphics 530;DirectX 版本: 11;存储空间: 需要 12 GB 可用空间;声卡: DirectX Compatible Sound Device', 199, 0, 94, '2022-08-02 20:10:36', '2022-08-02 20:10:36', 1);
INSERT INTO `game_info` VALUES (6, 3, 'Stellaris', '准备好展开您的旅程，在星际间探索、发掘多样物种，并与其互动。派出科学飞船进行探索调查，并让建筑飞船在新发现的星球周边建设基地站，打造您的星系帝国。为您的国度确立发展方向的同时，发掘埋藏的秘宝和星系奇观，为探险者设下限制和进化条件。您会在过程中建立互助的联盟，也会面临战争爆发的情况。', '操作系统: Windows® 7 SP1 64 Bit;处理器: Intel® iCore™ i3-530 or AMD® FX-6350;内存: 4 GB RAM;显卡: Nvidia® GeForce™ GTX 460 or AMD® ATI Radeon™ HD 5870 (1GB VRAM), or AMD® Radeon™ RX Vega 11 or Intel® HD Graphics 4600;DirectX 版本: 9.0c;网络: 宽带互联网连接;存储空间: 需要 10 GB 可用空间;声卡: Direct X 9.0c- compatible sound card;附注事项: Controller support: 3-button mouse', 148, 0, 58, '2022-08-02 20:15:17', '2022-12-28 21:49:35', 1);
INSERT INTO `game_info` VALUES (7, 4, 'Red Dead Redemption 2', '亚瑟·摩根和范德林德帮众是一群逃亡在外的亡命之徒。联邦侦探和全国顶尖的赏金猎人在他们的身后穷追不舍，亚瑟一行人必须在广袤蛮荒的美国腹地上四处劫掠、挣扎求生。而帮派内部的矛盾分化日渐加深，摆在亚瑟面前的将是他无法避免的抉择：究竟是选择自己的理想，还是选择效忠于抚养了自己的帮派。', '需要 64 位处理器和操作系统;操作系统: Windows 10 - April 2018 Update (v1803);处理器: Intel® Core™ i7-4770K / AMD Ryzen 5 1500X;内存: 12 GB RAM;显卡: Nvidia GeForce GTX 1060 6GB / AMD Radeon RX 480 4GB;网络: 宽带互联网连接;存储空间: 需要 150 GB 可用空间;声卡: Direct X Compatible', 124, 0, 80, '2022-08-02 20:38:43', '2022-08-02 20:38:43', 1);
INSERT INTO `game_info` VALUES (8, 4, 'Stray', '《Stray》是一款以猫为主角的第三人称冒险游戏。故事发生在一座日渐衰败的赛博城市，这里有着刻画细致的霓虹闪烁的小巷，以及脏乱阴暗的下层环境。在高高低低的环境中流浪，抵御无法预见的威胁，探索这个只有平凡的机器人和危险生物的冷漠之地，并设法解开这里的谜团。', '需要 64 位处理器和操作系统;操作系统: Windows 10;处理器: Intel Core i5-2300 | AMD FX-6350;内存: 8 GB RAM;显卡: NVIDIA GeForce GTX 650 Ti, 2 GB | AMD Radeon R7 360, 2 GB;DirectX 版本: 12;存储空间: 需要 10 GB 可用空间', 95, 0, 61, '2022-08-02 20:41:43', '2022-08-02 20:41:43', 1);
INSERT INTO `game_info` VALUES (9, 5, 'Stardew Valley', '你继承了你爷爷在星露谷留下的老旧农场。带着爷爷留下的残旧工具和几枚硬币开始了你的新生活。你能适应这小镇上的生活并且将这个杂草丛生的老旧农场变成一个繁荣的家吗？这不是一件容易的事情。尤其是当Joja企业进驻镇上，导致以前旧的生活方式都消失了。交流中心这个以前举办过众多活动并充满活力的地方现在变成再也无人愿意踏进的一片废墟。但这山谷似乎充满机会，只要做出一点奉献你就可能是会成为让星露谷重回繁荣的人之一！', '操作系统: Windows Vista or greater;处理器: 2 Ghz;内存: 2 GB RAM;显卡: 256 mb video memory, shader model 3.0+DirectX 版本: 10;存储空间: 需要 500 MB 可用空间', 38, 0, 80, '2022-08-02 20:45:40', '2022-08-02 20:45:40', 1);
INSERT INTO `game_info` VALUES (10, 5, 'Euro Truck Simulator 2', '像公路之王一样在欧洲穿行， 将价值不菲的货物完美送抵远方！往返于英国，比利时，德国，意大利，荷兰，波兰等众多城市；您的耐力，技巧，速度都被您发挥到了极致！想知道如何成为一名卡车运输界的行家里手，用您的车轮来证明吧！', 'OS: Windows 7;Processor: Dual core CPU 2.4 GHz;Memory: 4 GB RAM;Graphics: GeForce GTS 450-class (Intel HD 4000);Hard Drive: 12 GB available space', 108, 0, 51, '2022-08-02 20:48:52', '2022-08-02 20:48:52', 1);
INSERT INTO `game_info` VALUES (11, 6, '极限竞速：地平线 5', '这是您的地平线冒险，在墨西哥充满活力并不断变化的开放世界景色中，驾驶百辆世界级好车，享受无拘无束又有趣的驾驶体验，并开始令人屏息的探险。', '需要 64 位处理器和操作系统;操作系统: Windows 10 version 15063.0 or higher;处理器: Intel i5-4460 or AMD Ryzen 3 1200;内存: 8 GB RAM;显卡: NVidia GTX 970 OR AMD RX 470;DirectX 版本: 12;网络: 宽带互联网连接;存储空间: 需要 110 GB 可用空间', 389, 0.98, 1535, '2022-08-02 20:53:08', '2022-08-02 20:53:08', 1);
INSERT INTO `game_info` VALUES (12, 6, 'Football Manager 2022', '足球的乐趣不仅仅在于赢得胜利。你还需要克服各种突发状况，实现梦想并取得成功。拼尽全力登上巅峰，或挽狂澜于既倒；这些都是最甜蜜的时刻。', '需要 64 位处理器和操作系统;操作系统: Windows 7 64-bit, 8/8.1, 10;处理器: Intel Core 2 or AMD Athlon 64 1.8GHz+内存: 4 GB RAM;显卡: Intel GMA X4500, NVIDIA GeForece 9600M GT, AMD/ATI Mobility Raedon HD 3650 - 256MB VRAM;DirectX 版本: 11;存储空间: 需要 7 GB 可用空间', 249, 0, 467, '2022-08-02 20:55:57', '2022-08-02 20:55:57', 1);
INSERT INTO `game_info` VALUES (34, 6, 'NBA 2K23', '在《NBA 2K23》中把握机会。发掘你的全部潜力。在辉煌生涯中展示你的天赋。在梦幻球队中将全明星与永恒传奇配对。作为一名传奇经理，建立属于你自己的王朝，或在终极联盟中领导NBA走向新的方向。现在就开始游戏，与NBA或WNBA球队进行较量，感受逼真的游戏体验。', '最低配置:需要 64 位处理器和操作系统;操作系统: Windows 7 64-bit, Windows 8.1 64-bit or Windows 10 64-bit;\r\n处理器: Intel® Core™ i3-2100 @ 3.10 GHz/ AMD FX-4100 @ 3.60 GHz or better;\r\n内存: 4 GB RAM;\r\n显卡: NVIDIA® GeForce® GT 450 1GB/ ATI® Radeon™ HD 7770 1 GB or better;\r\nDirectX 版本: 11;\r\n网络: 宽带互联网连接;\r\n存储空间: 需要 110 GB 可用空间;\r\n声卡: Directx 9.0x;', 199, 0.5, 0, '2022-11-28 23:31:26', '2022-11-28 23:31:42', 1);
INSERT INTO `game_info` VALUES (35, 6, '神力科莎 Assetto Corsa', '《神力科莎 Assetto Corsa》 使用先进的 DirectX 11 图形引擎，可重现沉浸式环境、动态光照以及逼真的车辆材质。这种先进的物理引擎旨在提供非常逼真的驾驶体验，包括真实汽车的各方面特性，在任何其他竞速赛车模拟上都还没有实现这样的功能，例如轮胎漏气点、包括纹理和起泡在内的热循环以及非常先进的空气动力学模拟，具有通过遥测输入通道、带有动能回收系统（KERS）和能量回收模拟的混合系统实时控制的主动可移动的空气动力学部件。非常详尽的单人和多人模式选项。由于获得了赛车制造商的官方合作，拥有独家授权的赛车，以高水准的精确细节呈现于游戏中。', '操作系统: Windows 7 Sp1 - 8 - 8.1 - 10;\r\n处理器: AMD Athlon X2 2.8 GHZ, Intel Core 2 Duo 2.4 GHZ;\r\n内存: 2 GB RAM;\r\n显卡: DirectX 10.1 (e.g. AMD Radeon HD 6450, Nvidia GeForce GT 460);\r\nDirectX 版本: 11;\r\n网络: 宽带互联网连接;\r\n存储空间: 需要 15 GB 可用空间;\r\n声卡: Integrated', 70, 0.2, 1, '2022-11-28 23:37:11', '2022-11-28 23:37:15', 1);

-- ----------------------------
-- Table structure for game_order
-- ----------------------------
DROP TABLE IF EXISTS `game_order`;
CREATE TABLE `game_order`  (
  `order_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `game_id` bigint(0) NOT NULL COMMENT '游戏id',
  `game_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏名称',
  `game_img` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏图片',
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏类型名称',
  `total_price` double NOT NULL COMMENT '支付金额',
  `game_discount` double NOT NULL COMMENT '游戏折扣',
  `pay_type` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付方式',
  `pay_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '支付时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of game_order
-- ----------------------------
INSERT INTO `game_order` VALUES (1, 1, 'aaaaaa', 3, '巫师 3：狂猎', 'gameImg/3/p1.jpg', '角色扮演', 127, 0, '支付宝', '2022-08-08 11:20:17');
INSERT INTO `game_order` VALUES (9, 1, 'aaaaaa', 2, 'PUBG: BATTLEGROUNDS', 'gameImg/2/p1.jpg', '动作', 0, 0, '微信', '2022-09-22 17:21:51');
INSERT INTO `game_order` VALUES (10, 1, 'aaaaaa', 1, 'Counter-Strike: Global Offensive', 'gameImg/1/p1.jpg', '动作', 96, 0, '微信', '2022-10-10 16:36:20');
INSERT INTO `game_order` VALUES (11, 1, 'aaaaaa', 5, 'Sid Meier’s Civilization® VI', 'gameImg/5/p1.jpg', '策略', 199, 0, '支付宝', '2022-10-10 16:40:17');
INSERT INTO `game_order` VALUES (12, 2, 'bbbbbb', 1, 'Counter-Strike: Global Offensive', 'gameImg/1/p1.jpg', '动作', 96, 0, '微信', '2022-10-18 16:42:43');
INSERT INTO `game_order` VALUES (13, 2, 'bbbbbb', 5, 'Sid Meier’s Civilization® VI', 'gameImg/5/p1.jpg', '策略', 199, 0, '支付宝', '2022-10-18 16:43:55');
INSERT INTO `game_order` VALUES (14, 2, 'bbbbbb', 6, 'Stellaris', 'gameImg/6/p1.jpg', '策略', 148, 0, '支付宝', '2022-10-18 16:46:10');
INSERT INTO `game_order` VALUES (15, 2, 'bbbbbb', 11, '极限竞速：地平线 5', 'gameImg/11/p1.jpg', '体育及竞速', 381.22, 0.98, '支付宝', '2022-10-18 16:47:19');
INSERT INTO `game_order` VALUES (16, 2, 'bbbbbb', 10, 'Euro Truck Simulator 2', 'gameImg/10/p1.jpg', '模拟', 108, 0, '微信', '2022-10-18 16:47:44');
INSERT INTO `game_order` VALUES (17, 2, 'bbbbbb', 7, 'Red Dead Redemption 2', 'gameImg/7/p1.jpg', '冒险', 124, 0, '微信', '2022-10-18 16:48:03');
INSERT INTO `game_order` VALUES (18, 1, 'aaaaaa', 11, '极限竞速：地平线 5', 'gameImg/11/p1.jpg', '体育及竞速', 381.22, 0.98, '微信', '2022-11-02 22:11:10');
INSERT INTO `game_order` VALUES (20, 1, 'aaaaaa', 8, 'Stray', 'gameImg/8/p1.jpg', '冒险', 95, 0, '微信', '2022-11-04 23:13:08');
INSERT INTO `game_order` VALUES (21, 3, 'cccccc', 1, 'Counter-Strike: Global Offensive', '/gameImg/1/p1.jpg', '动作', 96, 0, '微信', '2022-11-28 20:01:53');
INSERT INTO `game_order` VALUES (22, 3, 'cccccc', 2, 'PUBG: BATTLEGROUNDS', '/gameImg/2/p1.jpg', '动作', 0, 0, '支付宝', '2022-11-28 20:02:01');
INSERT INTO `game_order` VALUES (23, 3, 'cccccc', 3, '巫师 3：狂猎', '/gameImg/3/p1.jpg', '角色扮演', 127, 0, '支付宝', '2022-11-28 20:02:08');
INSERT INTO `game_order` VALUES (24, 3, 'cccccc', 11, '极限竞速：地平线 5', '/gameImg/11/p1.jpg', '体育及竞速', 381.22, 0.98, '支付宝', '2022-11-28 20:02:20');
INSERT INTO `game_order` VALUES (25, 4, 'dddddd', 4, 'Divinity: Original Sin 2 - Definitive Edition', '/gameImg/4/p1.jpg', '角色扮演', 179, 0, '支付宝', '2022-11-28 20:23:31');
INSERT INTO `game_order` VALUES (26, 4, 'dddddd', 3, '巫师 3：狂猎', '/gameImg/3/p1.jpg', '角色扮演', 127, 0, '微信', '2022-11-28 20:23:37');
INSERT INTO `game_order` VALUES (27, 4, 'dddddd', 10, 'Euro Truck Simulator 2', '/gameImg/10/p1.jpg', '模拟', 108, 0, '支付宝', '2022-11-28 20:23:45');
INSERT INTO `game_order` VALUES (28, 4, 'dddddd', 7, 'Red Dead Redemption 2', '/gameImg/7/p1.jpg', '冒险', 124, 0, '微信', '2022-11-28 20:23:54');
INSERT INTO `game_order` VALUES (29, 5, 'eeeeee', 2, 'PUBG: BATTLEGROUNDS', '/gameImg/2/p1.jpg', '动作', 0, 0, '支付宝', '2022-11-28 20:24:38');
INSERT INTO `game_order` VALUES (30, 5, 'eeeeee', 6, 'Stellaris', '/gameImg/6/p1.jpg', '策略', 148, 0, '微信', '2022-11-28 20:24:45');
INSERT INTO `game_order` VALUES (31, 5, 'eeeeee', 9, 'Stardew Valley', '/gameImg/9/p2.jpg', '模拟', 38, 0, '微信', '2022-11-28 20:24:52');
INSERT INTO `game_order` VALUES (32, 6, 'ffffff', 2, 'PUBG: BATTLEGROUNDS', '/gameImg/2/p1.jpg', '动作', 0, 0, '支付宝', '2022-11-28 20:25:32');
INSERT INTO `game_order` VALUES (33, 6, 'ffffff', 1, 'Counter-Strike: Global Offensive', '/gameImg/1/p1.jpg', '动作', 96, 0, '微信', '2022-11-28 20:25:39');
INSERT INTO `game_order` VALUES (34, 6, 'ffffff', 3, '巫师 3：狂猎', '/gameImg/3/p1.jpg', '角色扮演', 127, 0, '微信', '2022-11-28 20:29:51');
INSERT INTO `game_order` VALUES (35, 3, 'cccccc', 10, 'Euro Truck Simulator 2', '/gameImg/10/p1.jpg', '模拟', 108, 0, '微信', '2022-11-28 20:35:48');
INSERT INTO `game_order` VALUES (36, 1, 'aaaaaa', 7, 'Red Dead Redemption 2', '/gameImg/7/p1.jpg', '冒险', 124, 0, '微信', '2022-12-07 13:08:19');
INSERT INTO `game_order` VALUES (37, 2, 'bbbbbb', 4, 'Divinity: Original Sin 2 - Definitive Edition', '/gameImg/4/p1.jpg', '角色扮演', 179, 0, '支付宝', '2022-12-07 13:12:31');
INSERT INTO `game_order` VALUES (38, 3, 'cccccc', 8, 'Stray', '/gameImg/8/p1.jpg', '冒险', 95, 0, '微信', '2022-12-07 13:18:17');
INSERT INTO `game_order` VALUES (39, 1, 'aaaaaa', 9, 'Stardew Valley', '/gameImg/9/p2.jpg', '模拟', 38, 0, '微信', '2022-12-09 13:55:40');
INSERT INTO `game_order` VALUES (40, 6, 'ffffff', 35, '神力科莎 Assetto Corsa', 'http://localhost:8888/upload/451735814f2f432d8d5f38b1e1ee4ef4-p1.jpg', '体育及竞速', 14, 0.2, '支付宝', '2022-12-29 20:05:52');
INSERT INTO `game_order` VALUES (41, 7, 'aaabbb', 3, '巫师 3：狂猎', '/gameImg/3/p1.jpg', '角色扮演', 127, 0, '微信', '2022-12-29 20:07:09');
INSERT INTO `game_order` VALUES (42, 9, 'aaaqqq', 1, 'Counter-Strike: Global Offensive', '/gameImg/1/p1.jpg', '动作', 96, 0, '支付宝', '2023-01-04 21:49:52');
INSERT INTO `game_order` VALUES (43, 9, 'aaaqqq', 3, '巫师 3：狂猎', '/gameImg/3/p1.jpg', '角色扮演', 127, 0, '微信', '2023-01-04 22:06:37');
INSERT INTO `game_order` VALUES (44, 9, 'aaaqqq', 4, 'Divinity: Original Sin 2 - Definitive Edition', '/gameImg/4/p1.jpg', '角色扮演', 179, 0, '支付宝', '2023-01-05 08:45:22');

-- ----------------------------
-- Table structure for game_type
-- ----------------------------
DROP TABLE IF EXISTS `game_type`;
CREATE TABLE `game_type`  (
  `type_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '游戏类型id',
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏类型名称',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of game_type
-- ----------------------------
INSERT INTO `game_type` VALUES (1, '动作');
INSERT INTO `game_type` VALUES (2, '角色扮演');
INSERT INTO `game_type` VALUES (3, '策略');
INSERT INTO `game_type` VALUES (4, '冒险');
INSERT INTO `game_type` VALUES (5, '模拟');
INSERT INTO `game_type` VALUES (6, '体育及竞速');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `password_md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'MD5加密后的密码',
  `is_deleted` tinyint(0) NOT NULL DEFAULT 0 COMMENT '注销标识字段(0-正常 1-已注销)',
  `register_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'aaaaaa', '0b4e7a0e5fe84ad35fb5f95b9ceeac79', 0, '2022-08-01 23:58:33');
INSERT INTO `user` VALUES (2, 'bbbbbb', '875f26fdb1cecf20ceb4ca028263dec6', 0, '2022-08-02 09:34:16');
INSERT INTO `user` VALUES (3, 'cccccc', 'c1f68ec06b490b3ecb4066b1b13a9ee9', 0, '2022-08-02 09:35:11');
INSERT INTO `user` VALUES (4, 'dddddd', '980ac217c6b51e7dc41040bec1edfec8', 0, '2022-08-02 09:49:17');
INSERT INTO `user` VALUES (5, 'eeeeee', 'cd87cd5ef753a06ee79fc75dc7cfe66c', 0, '2022-10-16 13:55:40');
INSERT INTO `user` VALUES (6, 'ffffff', 'eed8cdc400dfd4ec85dff70a170066b7', 0, '2022-10-16 13:57:47');
INSERT INTO `user` VALUES (7, 'aaabbb', '6547436690a26a399603a7096e876a2d', 0, '2022-10-19 15:44:19');
INSERT INTO `user` VALUES (8, 'aaaccc', '4b2332c3d211823914c3abf82c5e5087', 0, '2022-10-19 15:44:56');
INSERT INTO `user` VALUES (9, 'aaaqqq', '997828492b979a3253d819dcd25922c4', 0, '2022-10-19 15:45:31');

-- ----------------------------
-- Table structure for wishlist
-- ----------------------------
DROP TABLE IF EXISTS `wishlist`;
CREATE TABLE `wishlist`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `game_id` bigint(0) NOT NULL COMMENT '游戏id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wishlist
-- ----------------------------
INSERT INTO `wishlist` VALUES (1, 6);
INSERT INTO `wishlist` VALUES (1, 11);
INSERT INTO `wishlist` VALUES (3, 6);
INSERT INTO `wishlist` VALUES (9, 1);
INSERT INTO `wishlist` VALUES (9, 3);
INSERT INTO `wishlist` VALUES (9, 4);

SET FOREIGN_KEY_CHECKS = 1;
