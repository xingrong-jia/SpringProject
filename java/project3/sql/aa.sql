/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.26-log : Database - guns_rest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`guns_rest` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `guns_rest`;

/*Table structure for table `mooc_order_2017_t` */

DROP TABLE IF EXISTS `mooc_order_2017_t`;

CREATE TABLE `mooc_order_2017_t` (
  `UUID` varchar(100) DEFAULT NULL COMMENT '主键编号',
  `cinema_id` int(11) DEFAULT NULL COMMENT '影院编号',
  `field_id` int(11) DEFAULT NULL COMMENT '放映场次编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `seats_ids` varchar(50) DEFAULT NULL COMMENT '已售座位编号',
  `seats_name` varchar(200) DEFAULT NULL COMMENT '已售座位名称',
  `film_price` double DEFAULT NULL COMMENT '影片售价',
  `order_price` double DEFAULT NULL COMMENT '订单总金额',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `order_user` int(11) DEFAULT NULL COMMENT '下单人',
  `order_status` int(11) DEFAULT '0' COMMENT '0-待支付,1-已支付,2-已关闭'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单信息表';

/*Data for the table `mooc_order_2017_t` */

LOCK TABLES `mooc_order_2017_t` WRITE;

insert  into `mooc_order_2017_t`(`UUID`,`cinema_id`,`field_id`,`film_id`,`seats_ids`,`seats_name`,`film_price`,`order_price`,`order_time`,`order_user`,`order_status`) values ('329123812gnfn31',1,1,2,'1,2,3,4','第一排1座,第一排2座,第一排3座,第一排4座',63.2,126.4,'2017-05-03 12:13:42',1,0);

UNLOCK TABLES;

/*Table structure for table `mooc_order_2018_t` */

DROP TABLE IF EXISTS `mooc_order_2018_t`;

CREATE TABLE `mooc_order_2018_t` (
  `UUID` varchar(100) DEFAULT NULL COMMENT '主键编号',
  `cinema_id` int(11) DEFAULT NULL COMMENT '影院编号',
  `field_id` int(11) DEFAULT NULL COMMENT '放映场次编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `seats_ids` varchar(50) DEFAULT NULL COMMENT '已售座位编号',
  `seats_name` varchar(200) DEFAULT NULL COMMENT '已售座位名称',
  `film_price` double DEFAULT NULL COMMENT '影片售价',
  `order_price` double DEFAULT NULL COMMENT '订单总金额',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `order_user` int(11) DEFAULT NULL COMMENT '下单人',
  `order_status` int(11) DEFAULT '0' COMMENT '0-待支付,1-已支付,2-已关闭'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单信息表';

/*Data for the table `mooc_order_2018_t` */

LOCK TABLES `mooc_order_2018_t` WRITE;

insert  into `mooc_order_2018_t`(`UUID`,`cinema_id`,`field_id`,`film_id`,`seats_ids`,`seats_name`,`film_price`,`order_price`,`order_time`,`order_user`,`order_status`) values ('124583135asdf81',1,1,2,'1,2,3,4','第一排1座,第一排2座,第一排3座,第一排4座',63.2,126.4,'2018-02-12 11:53:42',1,0);

UNLOCK TABLES;

/*Table structure for table `mooc_order_t` */

DROP TABLE IF EXISTS `mooc_order_t`;

CREATE TABLE `mooc_order_t` (
  `UUID` varchar(100) DEFAULT NULL COMMENT '主键编号',
  `cinema_id` int(11) DEFAULT NULL COMMENT '影院编号',
  `field_id` int(11) DEFAULT NULL COMMENT '放映场次编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `seats_ids` varchar(50) DEFAULT NULL COMMENT '已售座位编号',
  `seats_name` varchar(200) DEFAULT NULL COMMENT '已售座位名称',
  `film_price` double DEFAULT NULL COMMENT '影片售价',
  `order_price` double DEFAULT NULL COMMENT '订单总金额',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `order_user` int(11) DEFAULT NULL COMMENT '下单人',
  `order_status` int(11) DEFAULT '0' COMMENT '0-待支付,1-已支付,2-已关闭'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单信息表';

/*Data for the table `mooc_order_t` */

LOCK TABLES `mooc_order_t` WRITE;

insert  into `mooc_order_t`(`UUID`,`cinema_id`,`field_id`,`film_id`,`seats_ids`,`seats_name`,`film_price`,`order_price`,`order_time`,`order_user`,`order_status`) values ('415sdf58ew12ds5fe1',1,1,2,'1,2,3,4','第一排1座,第一排2座,第一排3座,第一排4座',63.2,126.4,'2020-01-11 09:19:42',4,0),('860448a1832c386044',1,1,2,'9, 8','2排2座 2排1座',60,120,'2020-01-11 17:15:04',4,0),('2564923426b3846358',2,5,2,'13,14','3排3座 3排4座',79,158,'2020-01-12 21:20:04',6,0),('096852791ef5846358',1,9,2,'7,11,12','2排2座 3排1座 3排2座',99,297,'2020-01-12 21:50:33',6,0),('8786509f73aa846358',3,17,2,'15,16,21,22,19,20','3排5座 3排6座 4排5座 4排6座 4排3座 4排4座',79,474,'2020-01-12 21:51:58',6,1),('26942039b025846358',1,9,2,'15,16','3排5座 3排6座',99,198,'2020-01-13 21:19:00',6,2),('885854a495c5053162',1,15,2,'40,33,26,19,12','6排5座 5排5座 4排5座 3排5座 2排5座',99,495,'2020-01-15 14:19:41',8,1),('2017764b1267053162',6,25,2,'17,18,15,16,13,14','3排5座 3排6座 3排3座 3排4座 3排1座 3排2座',99,594,'2020-01-15 14:20:57',8,1),('403102c64b7a053162',1,3,2,'15,16','3排3座 3排4座',99,198,'2020-01-16 20:18:36',8,0),('766672cb17ef053162',1,3,2,'15,16','3排3座 3排4座',99,198,'2020-01-16 20:19:08',8,2),('552034960f42053162',7,33,2,'4,12,20,28,36','1排4座 2排4座 3排4座 4排4座 5排4座',199,995,'2020-01-16 20:57:56',8,0),('7801404166fe053162',7,33,2,'4,12,20,28,36','1排4座 2排4座 3排4座 4排4座 5排4座',199,995,'2020-01-16 20:58:27',8,0),('10950017b8da053162',7,33,2,'4,12,20,28,36','1排4座 2排4座 3排4座 4排4座 5排4座',199,995,'2020-01-16 20:58:41',8,0),('313020a71f51053162',5,24,2,'11,12,17,18','2排5座 2排6座 3排5座 3排6座',99,396,'2020-01-16 21:10:30',8,0),('762936cb13f5053162',5,24,2,'15,16,21,22','3排3座 3排4座 4排3座 4排4座',99,396,'2020-01-16 21:12:25',8,1),('024554ca0247053162',1,9,2,'13,14','3排3座 3排4座',99,198,'2020-01-16 21:37:30',8,0),('10215252a998053162',1,3,2,'17,18','3排5座 3排6座',99,198,'2020-01-16 22:23:53',8,0),('1669780e4ef7053162',1,3,2,'17,18','3排5座 3排6座',99,198,'2020-01-16 22:23:53',8,0);

UNLOCK TABLES;

/*Table structure for table `mtime_actor_t` */

DROP TABLE IF EXISTS `mtime_actor_t`;

CREATE TABLE `mtime_actor_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `actor_name` varchar(50) DEFAULT NULL COMMENT '演员名称',
  `actor_img` varchar(200) DEFAULT NULL COMMENT '演员图片位置',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='演员表';

/*Data for the table `mtime_actor_t` */

LOCK TABLES `mtime_actor_t` WRITE;

insert  into `mtime_actor_t`(`UUID`,`actor_name`,`actor_img`) values (1,'徐峥','xuzheng.png'),(2,'王传君','wangchuanjun.png'),(3,'谭卓','tanzhuo.png'),(4,'黄渤','huangbo.png'),(5,'舒淇','shuqi.png'),(6,'张艺兴','zhangyixing.png'),(7,'强森','qiangsen.png'),(8,'杰森·斯坦森','sitansen.png'),(9,'李冰冰','libingbing.png'),(10,'汤姆·克鲁斯','kelusi.png');

UNLOCK TABLES;

/*Table structure for table `mtime_area_dict_t` */

DROP TABLE IF EXISTS `mtime_area_dict_t`;

CREATE TABLE `mtime_area_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='地域信息表';

/*Data for the table `mtime_area_dict_t` */

LOCK TABLES `mtime_area_dict_t` WRITE;

insert  into `mtime_area_dict_t`(`UUID`,`show_name`) values (1,'朝阳区'),(2,'海淀区'),(3,'丰台区'),(4,'大兴区'),(5,'东城区'),(6,'西城区'),(7,'通州区'),(8,'房山区'),(9,'昌平区'),(10,'顺义区'),(11,'怀柔区'),(12,'门头沟'),(13,'石景山区'),(14,'密云区'),(15,'平谷区'),(16,'延庆区'),(99,'全部');

UNLOCK TABLES;

/*Table structure for table `mtime_banner_t` */

DROP TABLE IF EXISTS `mtime_banner_t`;

CREATE TABLE `mtime_banner_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `banner_address` varchar(50) DEFAULT NULL COMMENT 'banner图存放路径',
  `banner_url` varchar(200) DEFAULT NULL COMMENT 'banner点击跳转url',
  `is_valid` int(11) DEFAULT '0' COMMENT '是否弃用 0-失效,1-失效',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='banner信息表';

/*Data for the table `mtime_banner_t` */

LOCK TABLES `mtime_banner_t` WRITE;

insert  into `mtime_banner_t`(`UUID`,`banner_address`,`banner_url`,`is_valid`) values (2,'banner1.png','www.meetingshop.cn',0),(3,'banner2.png','www.meetingshop.cn',0),(4,'banner3.png','www.meetingshop.cn',0);

UNLOCK TABLES;

/*Table structure for table `mtime_brand_dict_t` */

DROP TABLE IF EXISTS `mtime_brand_dict_t`;

CREATE TABLE `mtime_brand_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='品牌信息表';

/*Data for the table `mtime_brand_dict_t` */

LOCK TABLES `mtime_brand_dict_t` WRITE;

insert  into `mtime_brand_dict_t`(`UUID`,`show_name`) values (1,'大地影院'),(2,'万达影城'),(3,'耀莱成龙国际影城'),(4,'保利国际影城'),(5,'博纳国际影城'),(6,'金逸影城'),(7,'中影国际影城'),(8,'CGV影城'),(9,'橙天嘉禾影城'),(10,'新华国际影城'),(11,'星美国际影城'),(12,'百老汇影城'),(13,'UME国际影城'),(14,'幸福蓝海国际影城'),(15,'首都电影院'),(16,'华谊兄弟影院'),(17,'卢米埃影城'),(18,'沃美影城'),(19,'美嘉欢乐影城'),(20,'嘉华国际影城'),(21,'17.5影城'),(22,'太平洋电影城'),(23,'SFC上影影城'),(24,'嘉美国际影城'),(25,'东都影城'),(26,'鲁信影城'),(27,'华影国际影城'),(28,'搜秀影城'),(29,'横店电影城'),(99,'全部');

UNLOCK TABLES;

/*Table structure for table `mtime_cat_dict_t` */

DROP TABLE IF EXISTS `mtime_cat_dict_t`;

CREATE TABLE `mtime_cat_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='类型信息表';

/*Data for the table `mtime_cat_dict_t` */

LOCK TABLES `mtime_cat_dict_t` WRITE;

insert  into `mtime_cat_dict_t`(`UUID`,`show_name`) values (1,'爱情'),(2,'喜剧'),(3,'动画'),(4,'剧情'),(5,'恐怖'),(6,'惊悚'),(7,'科幻'),(8,'动作'),(9,'悬疑'),(10,'犯罪'),(11,'冒险'),(12,'战争'),(13,'奇幻'),(14,'运动'),(15,'家庭'),(16,'古装'),(17,'武侠'),(18,'西部'),(19,'历史'),(20,'传记'),(21,'歌舞'),(22,'短片'),(23,'纪录片'),(24,'黑色电影'),(99,'全部');

UNLOCK TABLES;

/*Table structure for table `mtime_cinema_t` */

DROP TABLE IF EXISTS `mtime_cinema_t`;

CREATE TABLE `mtime_cinema_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `cinema_name` varchar(50) DEFAULT NULL COMMENT '影院名称',
  `cinema_phone` varchar(50) DEFAULT NULL COMMENT '影院电话',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌编号',
  `area_id` int(11) DEFAULT NULL COMMENT '地域编号',
  `hall_ids` varchar(200) DEFAULT NULL COMMENT '包含的影厅类型,以#作为分割',
  `img_address` varchar(500) DEFAULT NULL COMMENT '影院图片地址',
  `cinema_address` varchar(200) DEFAULT NULL COMMENT '影院地址',
  `minimum_price` int(11) DEFAULT '0' COMMENT '最低票价',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影院信息表';

/*Data for the table `mtime_cinema_t` */

LOCK TABLES `mtime_cinema_t` WRITE;

insert  into `mtime_cinema_t`(`UUID`,`cinema_name`,`cinema_phone`,`brand_id`,`area_id`,`hall_ids`,`img_address`,`cinema_address`,`minimum_price`) values (1,'大地影院(顺义店)','18500003333',1,1,'#1#3#5#6#','cinema6.jpg','北京市顺义区华联金街购物中心',60),(2,'大地影院(中关村店)','010-58391939',1,2,'#1#2#3#4#','cinema6.jpg','北京市中关村海龙大厦',60),(3,'万达影院(大屯店)','010-58391939',2,3,'#5#6#7#8#','cinema2.jpg','北京市朝阳区大屯路50号金街商场',60),(4,'万达影院(奥体中心店)','010-58391231',2,4,'#1#3#5#6#','cinema3.jpg','北京市朝阳区奥林匹克公园新奥购物中心',60),(5,'万达影院(中南海店)','010-58398521',2,5,'#1#5#7#8#','cinema5.jpg','北京市东城区中南海52号',60),(6,'万达影院(国贸店)','010-96385274',2,6,'#1#2#3#7#','cinema1.jpg','北京市朝阳区国贸CBD核心商场5012',60),(7,'CGV大屯店)','010-98765432',3,7,'#1#5#8#9#','cinema4.jpg','北京市朝阳区大屯路50号王道大厦',60);

UNLOCK TABLES;

/*Table structure for table `mtime_field_t` */

DROP TABLE IF EXISTS `mtime_field_t`;

CREATE TABLE `mtime_field_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `cinema_id` int(11) DEFAULT NULL COMMENT '影院编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `begin_time` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(50) DEFAULT NULL COMMENT '结束时间',
  `hall_id` int(11) DEFAULT NULL COMMENT '放映厅类型编号',
  `hall_name` varchar(200) DEFAULT NULL COMMENT '放映厅名称',
  `price` int(11) DEFAULT NULL COMMENT '票价',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='放映场次表';

/*Data for the table `mtime_field_t` */

LOCK TABLES `mtime_field_t` WRITE;

insert  into `mtime_field_t`(`UUID`,`cinema_id`,`film_id`,`begin_time`,`end_time`,`hall_id`,`hall_name`,`price`) values (1,2,2,'09:50','11:20',1,'IMAX厅',60),(2,1,3,'11:50','13:20',2,'CGS中国巨幕厅',60),(3,1,2,'13:50','15:20',3,'杜比全景声厅',99),(4,1,3,'11:50','13:20',4,'Dolby Cinema厅',89),(5,2,2,'11:50','13:20',5,'RealD厅',79),(6,5,3,'11:50','13:20',6,'RealD 6FL厅',60),(7,6,2,'11:50','13:20',7,'LUXE巨幕厅',75),(8,1,3,'09:50','11:20',8,'4DX厅',70),(9,1,2,'13:50','15:20',9,'DTS:X 临境音厅',99),(10,1,3,'09:50','11:20',10,'儿童厅',55),(11,2,2,'13:50','15:20',11,'4K厅',199),(12,5,3,'11:50','13:20',13,'巨幕厅',239),(13,2,2,'11:50','13:20',12,'4D厅',99),(14,3,2,'11:50','13:20',12,'4D厅',99),(15,1,2,'11:50','13:20',12,'4D厅',99),(16,2,2,'13:50','15:20',9,'DTS:X 临境音厅',99),(17,3,2,'11:50','13:20',5,'RealD厅',79),(18,2,2,'13:50','15:20',3,'杜比全景声厅',99),(19,2,3,'11:50','13:20',4,'Dolby Cinema厅',89),(20,1,2,'09:50','11:20',1,'IMAX厅',60),(21,3,2,'13:50','15:20',3,'杜比全景声厅',99),(22,3,3,'11:50','13:20',4,'Dolby Cinema厅',89),(23,4,2,'13:50','15:20',3,'杜比全景声厅',99),(24,5,2,'13:50','15:20',3,'杜比全景声厅',99),(25,6,2,'13:50','15:20',3,'杜比全景声厅',99),(26,2,3,'09:50','11:20',10,'儿童厅',55),(27,3,3,'09:50','11:20',10,'儿童厅',55),(28,5,3,'09:50','11:20',10,'儿童厅',55),(29,5,3,'11:50','13:20',2,'CGS中国巨幕厅',60),(30,2,2,'11:50','13:20',7,'LUXE巨幕厅',75),(31,4,3,'11:50','13:20',2,'CGS中国巨幕厅',60),(32,6,2,'13:50','15:20',11,'4K厅',199),(33,7,2,'13:50','15:20',11,'4K厅',199),(34,3,2,'11:50','13:20',5,'RealD厅',79),(35,6,3,'09:50','11:20',8,'4DX厅',70),(36,4,3,'09:50','11:20',8,'4DX厅',70),(37,3,3,'09:50','11:20',8,'4DX厅',70),(38,2,3,'09:50','11:20',8,'4DX厅',70),(39,3,3,'11:50','13:20',2,'CGS中国巨幕厅',60),(40,1,2,'11:50','13:20',5,'RealD厅',79),(41,1,2,'11:50','13:20',7,'LUXE巨幕厅',75),(42,5,2,'13:50','15:20',9,'DTS:X 临境音厅',99);

UNLOCK TABLES;

/*Table structure for table `mtime_film_actor_t` */

DROP TABLE IF EXISTS `mtime_film_actor_t`;

CREATE TABLE `mtime_film_actor_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_id` int(11) DEFAULT NULL COMMENT '影片编号,对应mtime_film_t',
  `actor_id` int(11) DEFAULT NULL COMMENT '演员编号,对应mtime_actor_t',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影片与演员映射表';

/*Data for the table `mtime_film_actor_t` */

LOCK TABLES `mtime_film_actor_t` WRITE;

insert  into `mtime_film_actor_t`(`UUID`,`film_id`,`actor_id`,`role_name`) values (1,2,1,'演员1'),(2,2,2,'演员2'),(3,2,3,'演员3'),(4,2,4,'演员4');

UNLOCK TABLES;

/*Table structure for table `mtime_film_info_t` */

DROP TABLE IF EXISTS `mtime_film_info_t`;

CREATE TABLE `mtime_film_info_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_id` varchar(100) DEFAULT NULL COMMENT '影片编号',
  `film_en_name` varchar(50) DEFAULT NULL COMMENT '影片英文名称',
  `film_score` varchar(20) DEFAULT NULL COMMENT '影片评分',
  `film_score_num` int(11) DEFAULT NULL COMMENT '评分人数,以万为单位',
  `film_length` int(11) DEFAULT NULL COMMENT '播放时长，以分钟为单位，不足取整',
  `biography` text COMMENT '影片介绍',
  `director_id` int(11) DEFAULT NULL COMMENT '导演编号',
  `film_imgs` text COMMENT '影片图片集地址,多个图片以逗号分隔',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影片主表';

/*Data for the table `mtime_film_info_t` */

LOCK TABLES `mtime_film_info_t` WRITE;

insert  into `mtime_film_info_t`(`UUID`,`film_id`,`film_en_name`,`film_score`,`film_score_num`,`film_length`,`biography`,`director_id`,`film_imgs`) values (2,'2','Dying To Survive','9.7',225,117,'一位不速之客的意外到访，打破了神油店老板程勇（徐峥 饰）的平凡人生，他从一个交不起房租的男性保健品商贩，一跃成为印度仿制药“格列宁”的独家代理商。收获巨额利润的他，生活剧烈变化，被病患们冠以“药神”的称号。但是，一场关于救赎的拉锯战也在波涛暗涌中慢慢展开......',1,'yaoshen1.jpeg,yaoshen2.jpeg,yaoshen3.jpeg,yaoshen4.png,yaoshen5.jpeg,yaoshen6.jpg'),(3,'3','Dancing Elephant','8.8',345,134,'热爱跳舞的13岁少女黎春夏不幸遭遇车祸，沉睡15年后竟奇迹般地苏醒，醒来后却发现生活发生了天翻地覆的变化，不仅丧失了十五年的生活常识，体重也飙升至200斤。但是为了完成在舞台上翩翩起舞的梦想，她与童年的三位同伴再次合体，巧遇灵魂舞者培训班创始人皮鲍十，一场华丽丽的魔鬼特训就此开启。',1,'daxiang1.jpg,daxiang2.jpg,daxiang3.jpg,daxiang4.jpg,daxiang5.jpg'),(4,'4','機動戦士ガンダム NT（ナラティブ）','9.0',34,54,'U. C.0097时期，“拉普拉斯事变”的纷争终结后，2架独角兽高达被秘密封印，新吉翁残党“袖章”也随之瓦解。就在人类逐渐走出战争带来的阴影之时，失踪两年的独角兽三号机“凤凰”突然现身，拥有颠覆世界秩序力量的它即刻引发了地球联邦军、吉翁袖章残党以及路欧商会三方势力的争夺狩猎，一场宇宙对决也即将拉开帷幕......',1,'fate1.jpg,fate2.jpg,fate3.jpg,fate4.jpg,fate5.jpg'),(5,'5','The Legend of Pig Warrior','9.2',34,55,'故事从天蓬元帅调戏嫦娥被贬下凡，开启了一段以屁哥寻找公主，公主寻找美食的离奇、搞笑的爱情故事，这也是天蓬元帅成为猪八戒前的一段密史。',1,'bajie1.jpg,bajie2.jpg,bajie3.jpg,bajie4.jpg,bajie5.jpg'),(6,'6','Looking up','9.1',56,2,'浩瀚太空，航天员意外失联，生命最大的绝境中，他回忆起自己那个最了不起的爸爸。一对父子跨越漫长的时光，守护爱和亲情，故事充满了欢乐、温暖、泪水与奇观。',1,'yinhe1.jpg,yinhe2.jpg,yinhe3.jpg,yinhe4.jpg,yinhe5.jpg'),(7,'7','Next Gen','7.8',345,435,'一个即将被用于拯救人类的新型机器人7723（韩莹棣 配音），因缘际会遇上了热爱足球的调皮少女苏小麦（郑海音 配音）。而看似顽劣的小麦生长在一个不算完整的家庭里，父亲为追梦离家，使得小麦的成长也受到了影响，性格冷漠孤僻。7723的出现也使得这对欢喜冤家逐渐在相爱相杀中成为了一对挚友。然而好景不长，心怀毁灭人类之梦的庞老板（冯远征 配音）追踪到了7723的下落，然而那些印刻着小麦名字的温柔颜色和绚烂时光早已在他的生命里留下了不可磨灭的痕迹……',1,'weilai1.jpg,weilai2.jpg,weilai3.jpg,weilai4.jpg,weilai5.jpg'),(8,'8','Three Changes','7.1',323,543,'影片讲述了白灵（李海娜 饰）与恋人卫军（李川 饰）在去马来西亚云顶高原赌场度假的过程中，阴差阳错住进了传言中亚洲十大猛鬼酒店“彩云阁”，遭遇了一系列离奇诡异的恐怖事件。本片取材于网上流传甚久的“马来西亚云顶高原女鬼啃人头”灵异事件，有关女鬼的身世传言也众说纷纭。二人究竟是消除女鬼怨念逃出生天，还是被女鬼吞噬？',1,'yuanling1.jpg,yuanling2.jpg,yuanling3.jpg,yuanling4.jpg,yuanling5.jpg'),(9,'9','Spider-Man: Far From Home','8.8',3242,45323,'故事全面延续“复联4”，蜘蛛侠志承钢铁侠远征欧洲，独挑大梁对抗群敌！新角色“神秘客”穿越多元宇宙霸气登场！神盾局局长尼克·弗瑞回归领军！“漫威新铁三角”组合强势出击！全新蜘蛛战衣酷炫升级！史诗对决燃爆今夏突破想象极限！',1,'zhizhuxia1.jpg,zhizhuxia2.jpg,zhizhuxia3.jpg,zhizhuxia4.jpg,zhizhuxia5.jpg'),(10,'10','Fate/stay night','9.4',523,523,'「圣杯战争」相隔10年再度在冬木市开战，随着被称作「圣杯战争」御三家之一的间桐家当主‧间桐脏砚（津嘉山正种 配音）的加入，战争变得错综复杂。不知名的黑影在城市内蠢蠢欲动，将御主及从者相继打倒。作为魔术师（御主）加入战争的卫宫士郎（杉山纪彰 配音）也再次受伤，并失去了他的从者Saber（川澄绫子 配音）。尽管如此，士郎为了守护间桐樱（下屋则子 配音），并没有退出战争，但担心着士郎的间桐樱却再次被魔术师的宿命所束缚…',1,'fate1.jpg,fate2.jpg,fate3.jpg,fate4.jpg,fate5.jpg'),(11,'11','The Lion King','9.5',324,312,'小狮子王辛巴（唐纳德·格洛弗 配音）在众多热情的朋友的陪伴下，不但经历了生命中最光荣的时刻，也遭遇了最艰难的挑战，最后终于成为了森林之王，也在周而复始生生不息的自然中体会出生命的真义。非洲大草原上一轮红日冉冉升起，为高大的乞力马扎罗山披上层金色的光纱，所有的动物涌向了同一个地方——荣耀石，兴奋地等待着一个重大消息的宣布：它们的国王木法沙将迎来自己的新生儿。这个新生儿就是小狮子辛巴，它是木法沙的法定接班人、荣耀石未来的国王。',1,'lion1.jpg,lion2.jpg,lion3.jpg,lion4,jpg,lion5.jpg'),(12,'12','The White Storm 2','9.0',234,1234,'毒品市场维持四分天下的格局已久，但自从地藏（古天乐 饰）与墨西哥大毒枭跨境合作扩展势力，再加上一连串黑吃黑的动作，毒界变得风声鹤唳。另一方面，因儿时亲眼目睹父亲被毒品所毁而嫉毒如仇的慈善家兼金融巨子余顺天（刘德华 饰），正悬赏一亿歼灭香港最大毒贩，此举在社会上引起轩然大波。警员林正风（苗侨伟 饰）本致力搜证拘捕地藏，毒贩却因巨额悬赏导致人身安全受威胁，他在执行保护毒贩的任务时深感无奈，但又被余顺天的出价所诱惑，陷入黑白正邪的心理挣扎。原来，余顺天和地藏有不可告人的同门关系，一场天地对决一触即发。',1,'saodu1.jpg,saodu2.jpg,saodu3.jpg,saodu4.jpg,saodu5.jpg'),(13,'13','The Rookies','9.5',342,4534,'极限运动达人赵风（王大陆 饰），误打误撞闯入了一场国际犯罪交易，不得不跟随国际特工（米拉·乔沃维奇 饰）一起前往布达佩斯。在这里他与废柴刑警淼淼（张榕容 饰）、民间科学家丁山（许魏洲 饰）与待业医生LV（刘美彤 饰）组成一支素人特工小队。这四个特工小白和高级国际特攻米拉一起，与恐怖分子开启了一场又惊又喜的斗争。',1,'tegong1.jpg,tegong2.jpg,tegong3.jpg,tegong4.jpg');

UNLOCK TABLES;

/*Table structure for table `mtime_film_t` */

DROP TABLE IF EXISTS `mtime_film_t`;

CREATE TABLE `mtime_film_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_name` varchar(100) DEFAULT NULL COMMENT '影片名称',
  `film_type` int(11) DEFAULT NULL COMMENT '片源类型: 0-2D,1-3D,2-3DIMAX,4-无',
  `img_address` varchar(500) DEFAULT NULL COMMENT '影片主图地址',
  `film_score` varchar(20) DEFAULT NULL COMMENT '影片评分',
  `film_preSaleNum` int(11) DEFAULT NULL COMMENT '影片预售数量',
  `film_box_office` int(11) DEFAULT NULL COMMENT '影片票房：每日更新，以万为单位',
  `film_source` int(11) DEFAULT NULL COMMENT '影片片源，参照片源字典表',
  `film_cats` varchar(50) DEFAULT NULL COMMENT '影片分类，参照分类表,多个分类以#分割',
  `film_area` int(11) DEFAULT NULL COMMENT '影片区域，参照区域表',
  `film_date` int(11) DEFAULT NULL COMMENT '影片上映年代，参照年代表',
  `film_time` timestamp NULL DEFAULT NULL COMMENT '影片上映时间',
  `film_status` int(11) DEFAULT NULL COMMENT '影片状态,1-正在热映，2-即将上映，3-经典影片',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影片主表';

/*Data for the table `mtime_film_t` */

LOCK TABLES `mtime_film_t` WRITE;

insert  into `mtime_film_t`(`UUID`,`film_name`,`film_type`,`img_address`,`film_score`,`film_preSaleNum`,`film_box_office`,`film_source`,`film_cats`,`film_area`,`film_date`,`film_time`,`film_status`) values (2,'我不是药神',0,'238e2dc36beae55a71cabfc14069fe78236351.jpg','9.7',231432491,309600,1,'#2#4#22#',1,13,'2018-07-05 00:00:00',1),(3,'跳舞吧大象',0,'1813b306280c0f37f9812afbbe631ae33681369.jpg','8.8',349835,425352,1,'#2#4#22#',1,13,'2018-07-12 00:00:00',1),(4,'机动战士高达',2,'2bd43dce9113181254b2d21aa10929c29845750.jpg','9.0',32567345,23456543,2,'#3#4#18#',2,12,'2018-07-13 00:00:00',2),(5,'猪八戒传说',1,'99b46395a2675e1cf3510032a1088fa54610159.jpg','7.2',23234234,34764524,1,'#2#5#11#',1,13,'2018-07-14 00:00:00',2),(6,'银河补习班',2,'bb9f75599bfbb2c4cf77ad9abae1b95c1376927.jpg','8.0',5475854,657678,1,'#2#4#22#',1,13,'2018-07-13 00:00:00',2),(7,'未来机械城',2,'bb9f75599bfbb2c4cf77ad9abae1b95c1376927.png','8.2',2343534,64565723,1,'#3#6#17#',2,13,'2018-07-19 00:00:00',2),(8,'怨灵3',1,'d143039b1b72205ffb08b1de6ef38ffe1324616.jpg','7.7',32233,23453452,2,'#2#4#22#',1,13,'2018-07-19 00:00:00',2),(9,'蜘蛛侠英雄远征',2,'5dac476535359b7bb951ff15d37a9d0b153821.jpg','8.0',86756434,33546234,2,'#2#5#11#',2,13,'2018-07-12 00:00:00',1),(10,'命运之夜',1,'7b40e56e644cd04915e6e9cc09c1bdb1331242.jpg','9.0',5467523,464563,3,'#2#5#11#',5,13,'2018-07-12 00:00:00',1),(11,'狮子王',3,'7b9b0725ab5feae642e1fbba9fbb90fe3702078.jpg','9.5',43756734,24123155,3,'#2#4#15#',8,13,'2018-07-12 00:00:00',1),(12,'扫毒2',2,'8d3efdc44af04c3254fc9e4ad5334ae32660685.jpg','8.2',1227657,45364642,2,'#2#4#22#',3,13,'2018-07-12 00:00:00',1),(13,'素人特工',0,'c0bec212d759ad52f22bbb280e551c065000875.jpg','8.0',234235,123421,2,'#3#4#18#',1,13,'2018-07-12 00:00:00',1),(14,'回到过去拥抱你',1,'huidaoguoqu_main.jpg','8.1',23453,3252425,3,'#2#4#22#',1,16,'2018-07-16 00:00:00',1),(15,'看不见的你',1,'untold_main.jpg','8.5',323452,534231,3,'#3#6#17#',1,16,'2018-07-19 00:00:00',1),(16,'使徒行者',2,'walker_main.jpg','8.6',545241,767654,2,'#2#4#15#',4,19,'2018-07-19 00:00:00',1),(17,'速度与激情',1,'fastandfate_main.jpg','9.2',35535243,43534321,2,'#5#6#18#',1,19,'2018-07-19 00:00:00',2);

UNLOCK TABLES;

/*Table structure for table `mtime_hall_dict_t` */

DROP TABLE IF EXISTS `mtime_hall_dict_t`;

CREATE TABLE `mtime_hall_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  `seat_address` varchar(200) DEFAULT NULL COMMENT '座位文件存放地址',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='地域信息表';

/*Data for the table `mtime_hall_dict_t` */

LOCK TABLES `mtime_hall_dict_t` WRITE;

insert  into `mtime_hall_dict_t`(`UUID`,`show_name`,`seat_address`) values (1,'IMAX厅','seats/imax.json'),(2,'CGS中国巨幕厅','seats/cgs.json'),(3,'杜比全景声厅','seats/dubiquanjingsheng.json'),(4,'Dolby Cinema厅','seats/dolby-cinema.json'),(5,'RealD厅','seats/reald-6fl.json'),(6,'RealD 6FL厅','seats/reald-6fl.json'),(7,'LUXE巨幕厅','seats/luxe.json'),(8,'4DX厅','seats/4dx.json'),(9,'DTS:X 临境音厅','seats/dts-x.json'),(10,'儿童厅','seats/kids.json'),(11,'4K厅','seats/4k.json'),(12,'4D厅','seats/4d.json'),(13,'巨幕厅','seats/jumu.json'),(99,'全部',NULL);

UNLOCK TABLES;

/*Table structure for table `mtime_hall_film_info_t` */

DROP TABLE IF EXISTS `mtime_hall_film_info_t`;

CREATE TABLE `mtime_hall_film_info_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `film_name` varchar(50) DEFAULT NULL COMMENT '电影名称',
  `film_length` varchar(50) DEFAULT NULL COMMENT '电影时长',
  `film_cats` varchar(200) DEFAULT NULL COMMENT '电影类型',
  `film_language` varchar(50) DEFAULT NULL COMMENT '电影语言',
  `actors` varchar(200) DEFAULT NULL COMMENT '演员列表',
  `img_address` varchar(500) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影厅电影信息表';

/*Data for the table `mtime_hall_film_info_t` */

LOCK TABLES `mtime_hall_film_info_t` WRITE;

insert  into `mtime_hall_film_info_t`(`UUID`,`film_id`,`film_name`,`film_length`,`film_cats`,`film_language`,`actors`,`img_address`) values (1,2,'我不是药神','117','喜剧,剧情','国语2D','程勇,曹斌,吕受益,刘思慧','238e2dc36beae55a71cabfc14069fe78236351.jpg'),(2,3,'跳舞吧大象','123','喜剧,动作,冒险','国语3DIMAX','汤姆克鲁斯,舒淇,黄渤','1813b306280c0f37f9812afbbe631ae33681369.jpg');

UNLOCK TABLES;

/*Table structure for table `mtime_promo` */

DROP TABLE IF EXISTS `mtime_promo`;

CREATE TABLE `mtime_promo` (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cinema_id` int(11) NOT NULL COMMENT '秒杀活动影院id',
  `price` decimal(10,2) NOT NULL COMMENT '秒杀价格',
  `start_time` datetime NOT NULL COMMENT '秒杀开始时间',
  `end_time` datetime NOT NULL COMMENT '秒杀结束时间',
  `status` tinyint(4) NOT NULL COMMENT '秒杀活动状态',
  `description` varchar(255) DEFAULT NULL COMMENT '活动描述',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `mtime_promo` */

LOCK TABLES `mtime_promo` WRITE;

insert  into `mtime_promo`(`uuid`,`cinema_id`,`price`,`start_time`,`end_time`,`status`,`description`) values (1,1,'10.00','2020-01-17 15:35:12','2020-01-19 15:35:12',1,'大地影院周年庆，等你来兑换'),(2,2,'15.00','2020-01-17 15:35:12','2020-01-19 15:35:12',1,'大地影院酬宾活动'),(3,3,'20.00','2019-08-01 15:36:54','2020-01-19 15:35:12',1,'万达大酬宾');

UNLOCK TABLES;

/*Table structure for table `mtime_promo_order` */

DROP TABLE IF EXISTS `mtime_promo_order`;

CREATE TABLE `mtime_promo_order` (
  `uuid` varchar(255) NOT NULL COMMENT '主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `cinema_id` int(11) NOT NULL COMMENT '影院id',
  `exchange_code` varchar(255) NOT NULL COMMENT '兑换码',
  `amount` int(11) NOT NULL COMMENT '购买数量',
  `price` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '兑换开始时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '兑换结束时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mtime_promo_order` */

LOCK TABLES `mtime_promo_order` WRITE;

insert  into `mtime_promo_order`(`uuid`,`user_id`,`cinema_id`,`exchange_code`,`amount`,`price`,`start_time`,`create_time`,`end_time`) values ('1782842089f4053162',8,1,'JXR2020011720413005316200531620000001',1,'10.00','2020-01-17 20:41:31','2020-01-17 20:41:31','2020-01-24 20:41:30'),('6070365da82f053162',8,1,'JXR2020011720413005316200531620000002',1,'10.00','2020-01-17 20:41:31','2020-01-17 20:41:31','2020-01-24 20:41:30');

UNLOCK TABLES;

/*Table structure for table `mtime_promo_stock` */

DROP TABLE IF EXISTS `mtime_promo_stock`;

CREATE TABLE `mtime_promo_stock` (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `promo_id` int(11) NOT NULL COMMENT ' 秒杀活动id',
  `stock` int(11) NOT NULL COMMENT '库存',
  PRIMARY KEY (`uuid`),
  KEY `idx_promo_id` (`promo_id`) USING BTREE COMMENT '秒杀索引'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `mtime_promo_stock` */

LOCK TABLES `mtime_promo_stock` WRITE;

insert  into `mtime_promo_stock`(`uuid`,`promo_id`,`stock`) values (1,1,99895),(2,2,99927),(3,3,99969);

UNLOCK TABLES;

/*Table structure for table `mtime_source_dict_t` */

DROP TABLE IF EXISTS `mtime_source_dict_t`;

CREATE TABLE `mtime_source_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='区域信息表';

/*Data for the table `mtime_source_dict_t` */

LOCK TABLES `mtime_source_dict_t` WRITE;

insert  into `mtime_source_dict_t`(`UUID`,`show_name`) values (1,'大陆'),(2,'美国'),(3,'韩国'),(4,'日本'),(5,'中国香港'),(6,'中国台湾'),(7,'印度'),(8,'法国'),(9,'英国'),(10,'俄罗斯'),(11,'意大利'),(12,'西班牙'),(13,'德国'),(14,'波兰'),(15,'澳大利亚'),(16,'伊朗'),(99,'全部');

UNLOCK TABLES;

/*Table structure for table `mtime_stock_log` */

DROP TABLE IF EXISTS `mtime_stock_log`;

CREATE TABLE `mtime_stock_log` (
  `uuid` varchar(255) NOT NULL COMMENT '主键id',
  `promo_id` int(11) NOT NULL COMMENT '秒杀活动id',
  `amount` int(11) NOT NULL COMMENT '数量',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mtime_stock_log` */

LOCK TABLES `mtime_stock_log` WRITE;

UNLOCK TABLES;

/*Table structure for table `mtime_user_t` */

DROP TABLE IF EXISTS `mtime_user_t`;

CREATE TABLE `mtime_user_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户账号',
  `user_pwd` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `user_sex` int(11) DEFAULT NULL COMMENT '用户性别 0-男，1-女',
  `birthday` varchar(50) DEFAULT NULL COMMENT '出生日期',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `user_phone` varchar(50) DEFAULT NULL COMMENT '用户手机号',
  `address` varchar(50) DEFAULT NULL COMMENT '用户住址',
  `head_url` varchar(50) DEFAULT NULL COMMENT '头像URL',
  `biography` varchar(200) DEFAULT NULL COMMENT '个人介绍',
  `life_state` int(11) DEFAULT NULL COMMENT '生活状态 0-单身，1-热恋中，2-已婚，3-为人父母',
  `begin_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `mtime_user_t` */

LOCK TABLES `mtime_user_t` WRITE;

insert  into `mtime_user_t`(`UUID`,`user_name`,`user_pwd`,`nick_name`,`user_sex`,`birthday`,`email`,`user_phone`,`address`,`head_url`,`biography`,`life_state`,`begin_time`,`update_time`) values (2,'aaaaa','0192023a7bbd73250516f069df18b500','隔壁泰山',0,'2018-07-31','admin@mtime.com','13888888888','北京市海淀区朝阳北路中南海国宾馆','films/img/head-img.jpg','没有合适的伞，我宁可淋着雨',0,'2020-01-07 10:55:24','2020-01-15 14:15:20'),(3,'jiangzh','5e2de6bd1c9b50f6e27d4e55da43b917','阿里郎',0,'2018-08-20','jiangzh@mtime.com','13866666666','北京市朝阳区大望路万达广场','films/img/head-img.jpg','我喜欢隔壁泰山',1,'2020-01-07 10:55:24','2020-01-07 10:55:24'),(4,'aaa','aaaaaa',NULL,NULL,NULL,'aaa@qq.com','123456789','bbb',NULL,NULL,NULL,'2020-01-08 17:39:25','2020-01-12 12:02:39'),(5,'aaaasdad','aaaaaa','asdasfasd阿斯顿',0,'','123sew@qq.com','13242312234','按时打发撒旦',NULL,'asda胜多负少阿斯顿发射点',1,'2020-01-08 19:51:42','2020-01-08 19:44:18'),(6,'bbb','123456','啊实打',1,'','766718726@qq.com','13569832913','按时打发撒旦',NULL,'福娃鹅嘎违法违',2,'2020-01-12 14:14:05','2020-01-12 14:14:05'),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2020-01-15 14:15:15','2020-01-15 14:15:15'),(8,'admin','admin123','啊实打实法大师傅',0,'','rewr@qq.com','13212345423','而发生的全微分个人服务器二恶歌曲二',NULL,'福娃鹅嘎胜多负少都465儿发',0,'2020-01-15 14:15:23','2020-01-15 14:15:23');

UNLOCK TABLES;

/*Table structure for table `mtime_year_dict_t` */

DROP TABLE IF EXISTS `mtime_year_dict_t`;

CREATE TABLE `mtime_year_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='年代信息表';

/*Data for the table `mtime_year_dict_t` */

LOCK TABLES `mtime_year_dict_t` WRITE;

insert  into `mtime_year_dict_t`(`UUID`,`show_name`) values (1,'更早'),(2,'70年代'),(3,'80年代'),(4,'90年代'),(5,'2000-2010'),(6,'2011'),(7,'2012'),(8,'2013'),(9,'2014'),(10,'2015'),(11,'2016'),(12,'2017'),(13,'2018'),(14,'2018以后'),(99,'全部');

UNLOCK TABLES;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `user` */

LOCK TABLES `user` WRITE;

insert  into `user`(`id`,`userName`) values (1,'admin');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
