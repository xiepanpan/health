/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : health

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2020-03-12 20:05:26
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `cmt`
-- ----------------------------
DROP TABLE IF EXISTS `cmt`;
CREATE TABLE `cmt` (
  `cmt_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `content` text,
  `repeat` text,
  `topic_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cmt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cmt
-- ----------------------------
INSERT INTO cmt VALUES ('2', '4', 'fdsaf', 'asdfasdfadsf', '1');
INSERT INTO cmt VALUES ('5', '2', 'fdsafsaf', '111111111111111', '4');
INSERT INTO cmt VALUES ('7', '2', 'asdfasdf', null, '1');
INSERT INTO cmt VALUES ('8', '2', 'asdfsadf', null, '4');
INSERT INTO cmt VALUES ('9', '2', '111', '2222', '1');

-- ----------------------------
-- Table structure for `exam`
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `exam_id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(31) DEFAULT NULL,
  `exam_content` text,
  `exam_type` int(11) DEFAULT '1' COMMENT '1 生活习惯  2 心理健康',
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO exam VALUES ('3', '2019年生活习惯调查表', '<ol>\r\n	<li>\r\n		每日的起床时间（）<br />\r\nA.8点以前&nbsp; B 10点以前 C不一定\r\n	</li>\r\n	<li>\r\n		每日几顿饭 （）<br />\r\nA 2~3 B 3 C 不一定\r\n	</li>\r\n	<li>\r\n		吃粗粮（玉米、小米、番薯等）的频率（）<br />\r\nA 每天 B经常 C偶尔（一俩周一次）D很少吃\r\n	</li>\r\n	<li>\r\n		每日水果摄入量（）<br />\r\nA 200G以上 B 一星期一俩次 C 一个月一俩次\r\n	</li>\r\n	<li>\r\n		经常吃的肉类又哪些（）<br />\r\nA 飞禽（鸡鸭）B鱼类 C 猪肉 D 牛羊 E 很少\r\n	</li>\r\n	<li>\r\n		每天水的摄入量（）<br />\r\nA 500ml B 800~1000ml C 2000ml D 3000ml\r\n	</li>\r\n	<li>\r\n		吃豆制品的情况（）<br />\r\nA 偶尔吃 B很少吃 C 经常吃\r\n	</li>\r\n	<li>\r\n		运动情况（）<br />\r\nA 每天运动半小时以上 B 一周偶尔有一些运动 C很少运动&nbsp;\r\n	</li>\r\n	<li>\r\n		晚上睡觉时间（）<br />\r\nA 8~9点 B 9~10点 C 11~12 D 12点以后\r\n	</li>\r\n	<li>\r\n		每天排便次数（）<br />\r\nA 1~2&nbsp; B 1~3 C 2天1此&nbsp; D一周\r\n	</li>\r\n	<li>\r\n		每\r\n	</li>\r\n</ol>', '1');
INSERT INTO exam VALUES ('4', '2019年心理健康调查', '<ol>\r\n	<li>\r\n		每日的起床时间（）<br />\r\nA.8点以前&nbsp; B 10点以前 C不一定\r\n	</li>\r\n	<li>\r\n		每日几顿饭 （）<br />\r\nA 2~3 B 3 C 不一定\r\n	</li>\r\n	<li>\r\n		吃粗粮（玉米、小米、番薯等）的频率（）<br />\r\nA 每天 B经常 C偶尔（一俩周一次）D很少吃\r\n	</li>\r\n	<li>\r\n		每日水果摄入量（）<br />\r\nA 200G以上 B 一星期一俩次 C 一个月一俩次\r\n	</li>\r\n	<li>\r\n		经常吃的肉类又哪些（）<br />\r\nA 飞禽（鸡鸭）B鱼类 C 猪肉 D 牛羊 E 很少\r\n	</li>\r\n	<li>\r\n		每天水的摄入量（）<br />\r\nA 500ml B 800~1000ml C 2000ml D 3000ml\r\n	</li>\r\n	<li>\r\n		吃豆制品的情况（）<br />\r\nA 偶尔吃 B很少吃 C 经常吃\r\n	</li>\r\n	<li>\r\n		运动情况（）<br />\r\nA 每天运动半小时以上 B 一周偶尔有一些运动 C很少运动&nbsp;\r\n	</li>\r\n	<li>\r\n		晚上睡觉时间（）<br />\r\nA 8~9点 B 9~10点 C 11~12 D 12点以后\r\n	</li>\r\n	<li>\r\n		每天排便次数（）<br />\r\nA 1~2&nbsp; B 1~3 C 2天1此&nbsp; D一周俩此\r\n	</li>\r\n</ol>', '2');

-- ----------------------------
-- Table structure for `physical_exam`
-- ----------------------------
DROP TABLE IF EXISTS `physical_exam`;
CREATE TABLE `physical_exam` (
  `test_id` int(11) NOT NULL AUTO_INCREMENT,
  `height` decimal(10,2) DEFAULT NULL,
  `weight` decimal(10,2) DEFAULT NULL,
  `heartbeat` varchar(15) DEFAULT NULL,
  `vital_capacity` decimal(10,2) DEFAULT NULL,
  `sex` varchar(15) DEFAULT NULL,
  `stu_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `left_eye` varchar(15) DEFAULT NULL,
  `right_eye` varchar(15) DEFAULT NULL,
  `hearing` varchar(31) DEFAULT NULL,
  `advice` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of physical_exam
-- ----------------------------
INSERT INTO physical_exam VALUES ('81', null, null, null, null, null, '2', null, null, null, null, null);
INSERT INTO physical_exam VALUES ('82', null, null, null, null, null, '4', null, null, null, null, null);
INSERT INTO physical_exam VALUES ('83', null, null, null, null, null, '5', null, null, null, null, null);
INSERT INTO physical_exam VALUES ('84', null, null, null, null, null, '8', null, null, null, null, null);
INSERT INTO physical_exam VALUES ('85', null, null, null, null, null, '9', null, null, null, null, null);
INSERT INTO physical_exam VALUES ('86', null, null, null, null, null, '10', null, null, null, null, null);
INSERT INTO physical_exam VALUES ('87', null, null, null, null, null, '11', null, null, null, null, null);

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(127) DEFAULT NULL,
  `content` text,
  `user_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO topic VALUES ('1', 'ffff', 'fff烦烦烦fdsafs11111', '1', null);
INSERT INTO topic VALUES ('3', 'ffff', '<p>\r\n	fff\r\n</p>\r\n<h2>\r\n	asdfasdf\r\n</h2>\r\n<p>\r\n	asdfsadf\r\n</p>', '1', '2020-01-15 16:24:03');
INSERT INTO topic VALUES ('4', '121212', '<p style=\"font-size:17px;text-align:justify;color:#333333;font-family:PingFangSC-Regular, PingFangSC-Light, sans-serif;\">\r\n	在抗击新冠肺炎疫情的关键时刻，中共中央总书记、国家主席、中央军委主席习近平10日专门赴湖北省武汉市考察疫情防控工作。\r\n</p>\r\n<p style=\"font-size:17px;text-align:justify;color:#333333;font-family:PingFangSC-Regular, PingFangSC-Light, sans-serif;\">\r\n	一下飞机，习近平就专程前往火神山医院，了解医院建设运行、患者收治、医务人员防护保障、科研攻关等情况，亲切看望正在接受治疗的患者，关心慰问日夜奋战在抗击疫情第一线的医务工作者，勉励大家坚定信心，战胜疫情。离开火神山医院后，习近平又赶赴武汉市东湖新城社区，看望居家隔离的社区群众，实地察看社区卫生防疫、社区服务、群众生活保障等情况。在实地考察结束后，习近平主持召开会议，听取中央指导组、湖北省委和省政府关于疫情防控工作汇报，并发表重要讲话，部署战疫。\r\n</p>\r\n<p style=\"font-size:17px;text-align:justify;color:#333333;font-family:PingFangSC-Regular, PingFangSC-Light, sans-serif;\">\r\n	自岁末年初新冠肺炎疫情在武汉暴发至今，习近平总书记高度关注武汉的疫情防控工作，始终亲自指挥、亲自部署，始终把人民群众生命安全和身体健康放在第一位。\r\n</p>\r\n<p style=\"font-size:17px;text-align:justify;color:#333333;font-family:PingFangSC-Regular, PingFangSC-Light, sans-serif;\">\r\n	1月7日，习近平总书记主持召开中央政治局常委会会议时，就对做好疫情防控工作提出明确要求。1月25日，正月初一，习近平主持召开中央政治局常委会会议，专题研究疫情防控工作。这次会议决定，党中央向湖北等疫情严重地区派出指导组，推动有关地方全面加强防控一线工作。\r\n</p>\r\n<p style=\"font-size:17px;text-align:justify;color:#333333;font-family:PingFangSC-Regular, PingFangSC-Light, sans-serif;\">\r\n	正月初一以来，习近平总书记先后6次主持召开中央政治局常委会会议，强调湖北省特别是武汉市的疫情防控在全国大局中的重要性和紧迫性，就驰援武汉、决胜湖北连续作出动员和部署。\r\n</p>\r\n<p style=\"font-size:17px;text-align:justify;color:#333333;font-family:PingFangSC-Regular, PingFangSC-Light, sans-serif;\">\r\n	在以习近平同志为核心的党中央坚强领导下，一场防控新冠肺炎疫情的人民战争、总体战、阻击战迅速打响。全国上下闻令而动，而武汉也在全国各地的支持下，同时间赛跑，与病魔较量。面对疫情，武汉人民克服种种困难，积极支持配合各项防控措施。\r\n</p>\r\n<p style=\"font-size:17px;text-align:justify;color:#333333;font-family:PingFangSC-Regular, PingFangSC-Light, sans-serif;\">\r\n	在3月10日习近平总书记发表的重要讲话中，他强调，正是因为有了武汉人民的牺牲和奉献，有了武汉人民的坚持和努力，才有了今天疫情防控的积极向好态势。武汉不愧为英雄的城市，武汉人民不愧为英雄的人民，必将通过打赢这次抗击新冠肺炎疫情斗争再次被载入史册！全党全国各族人民都为你们而感动、而赞叹！党和人民感谢武汉人民！\r\n</p>\r\n<p style=\"font-size:17px;text-align:justify;color:#333333;font-family:PingFangSC-Regular, PingFangSC-Light, sans-serif;\">\r\n	湖北和武汉是这次疫情防控斗争的重中之重和决胜之地。经过艰苦努力，湖北和武汉疫情防控形势发生积极向好变化，取得阶段性重要成果，但疫情防控任务依然艰巨繁重。\r\n</p>\r\n<p style=\"font-size:17px;text-align:justify;color:#333333;font-family:PingFangSC-Regular, PingFangSC-Light, sans-serif;\">\r\n	习近平强调，越是在这个时候，越是要保持头脑清醒，越是要慎终如始，越是要再接再厉、善作善成，继续把疫情防控作为当前头等大事和最重要的工作，不麻痹、不厌战、不松劲，毫不放松抓紧抓实抓细各项防控工作，坚决打赢湖北保卫战、武汉保卫战！\r\n</p>', '1', '2020-03-05 21:22:15');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(15) DEFAULT NULL,
  `login_pwd` varchar(31) DEFAULT NULL,
  `user_type` int(11) DEFAULT '1' COMMENT '0管理员1学生2医生',
  `state` smallint(6) DEFAULT '1',
  `tel` varchar(31) DEFAULT NULL,
  `email` varchar(31) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `school` varchar(31) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_login_Name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO users VALUES ('1', 'admin', '123123', '0', '1', null, null, null, null);
INSERT INTO users VALUES ('2', 'stu1', '123123', '1', '1', null, null, null, null);
INSERT INTO users VALUES ('3', 'doct1', '123123', '2', '1', null, null, null, null);
INSERT INTO users VALUES ('4', '张三', '123123', '1', '1', '15005050505', '123@123.com', null, '北京大学');
INSERT INTO users VALUES ('5', '王五', '123123', '1', '1', '123123', null, null, null);
INSERT INTO users VALUES ('6', '王医生', '123123', '2', '1', '123123123', null, null, null);
INSERT INTO users VALUES ('7', '张医生', '123123', '2', '1', '4354554545', null, null, null);
INSERT INTO users VALUES ('8', 'ffffffff', '123123', '1', '1', '12312312123', null, null, null);
INSERT INTO users VALUES ('9', 'aaaaaa', '123123', '1', '1', '12121212', '123@12223.com', null, '北京123123');
INSERT INTO users VALUES ('10', 'fdsafsa', '123123', '1', '1', '1231231231', '43991766@qq.com', null, '北京大学222');
INSERT INTO users VALUES ('11', 'stu2', '123123', '1', '1', '555', '555@qq.com', null, '北京大学');

-- ----------------------------
-- Table structure for `user_exam`
-- ----------------------------
DROP TABLE IF EXISTS `user_exam`;
CREATE TABLE `user_exam` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `exam_id` int(11) NOT NULL DEFAULT '0',
  `answer` text,
  `advice` text,
  `doctor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_exam
-- ----------------------------
INSERT INTO user_exam VALUES ('2', '3', null, null, '7');
INSERT INTO user_exam VALUES ('2', '4', null, null, '6');
INSERT INTO user_exam VALUES ('4', '3', null, null, '7');
INSERT INTO user_exam VALUES ('4', '4', null, null, '6');
INSERT INTO user_exam VALUES ('5', '3', null, null, '6');
INSERT INTO user_exam VALUES ('5', '4', null, null, '6');
INSERT INTO user_exam VALUES ('8', '3', null, null, '6');
INSERT INTO user_exam VALUES ('8', '4', null, null, '6');
INSERT INTO user_exam VALUES ('9', '3', null, null, '6');
INSERT INTO user_exam VALUES ('9', '4', null, null, '6');
INSERT INTO user_exam VALUES ('10', '3', null, null, '6');
INSERT INTO user_exam VALUES ('10', '4', null, null, '6');
INSERT INTO user_exam VALUES ('11', '3', null, null, '6');
INSERT INTO user_exam VALUES ('11', '4', null, null, '6');
