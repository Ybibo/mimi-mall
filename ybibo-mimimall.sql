DROP DATABASE IF EXISTS xiaomissm;
CREATE DATABASE xiaomissm DEFAULT CHARSET utf8;

/*打开DB*/
USE xiaomissm;
DROP TABLE IF EXISTS `orderdetail`;
DROP TABLE IF EXISTS `xmorder`;
DROP TABLE IF EXISTS `carshop`;
DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS product_info;
DROP TABLE IF EXISTS product_type;
DROP TABLE IF EXISTS admin;


#DROP TABLE xiaomi_admin;
#################################管理员表
CREATE TABLE admin(
a_id INT AUTO_INCREMENT PRIMARY KEY,
a_name VARCHAR(20),
a_pass VARCHAR(50)
)ENGINE=INNODB DEFAULT CHARACTER SET=utf8;

#已用加密工具加密，明文密码123456
INSERT  INTO `admin`(`a_id`,`a_name`,`a_pass`) VALUES (1,'admin','7c4a8d09ca3762af61e59520943dc26494f8941b');

#update `xiaomissm`.`admin` set a_pass='123456' where a_id=1;

##########################商品类型表
CREATE TABLE product_type
(
type_id INT AUTO_INCREMENT PRIMARY KEY,
type_name VARCHAR(20)
)ENGINE=INNODB DEFAULT CHARACTER SET=utf8;

####################添加数据
INSERT INTO product_type(type_name) VALUES('手机');
INSERT INTO product_type(type_name) VALUES('电脑');
INSERT INTO product_type(type_name) VALUES('电视');
INSERT INTO product_type(type_name) VALUES('ybibo测试');


#############################商品表
CREATE TABLE product_info
(
p_id INT AUTO_INCREMENT PRIMARY KEY,
p_name VARCHAR(20),
p_content VARCHAR(200), ##############33商品规格/简介
p_price INT, ###############价格
p_image VARCHAR(200), #############图片
p_number INT, ########数量
type_id INT,
p_date DATE,
FOREIGN KEY(type_id) REFERENCES product_type(type_id)
)ENGINE=INNODB DEFAULT CHARACTER SET=utf8;

##添加
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米Note2','双曲面 黑色 6GB内存 64GB闪存',2899,'xmNote2.jpg',500,1,'2018-01-04');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('红米Note5A','5.5英寸 粉色 2GB内存 16GB闪存',699,'hmNote5A.jpg',500,1,'2018-01-05');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('红米Note4X','5.5英寸 绿色 4GB内存 64GB闪存',1299,'hmNote4X.jpg',500,1,'2018-01-06');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('红米4','5英寸 金色 3GB内存 32GB闪存',999,'hm4.jpg',500,1,'2018-01-07');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('红米4X','5英寸 黑色 3GB内存 32GB闪存',899,'hm4X.jpg',500,1,'2018-01-08');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米平板3','7.9英寸 金色 4GB内存 64GB闪存',1499,'xmPad3.jpg',500,2,'2018-01-09');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米Air12','12.5英寸 银色 4GB内存 128GB闪存',3599,'xmAir12.jpg',500,2,'2018-01-18');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米Air13','13.3英寸 银色 8GB内存 256GB闪存',4999,'xmAir13.jpg',500,2,'2018-01-17');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米Pro','15.6英寸 灰色 16GB内存 256GB闪存',6999,'xmPro.jpg',500,2,'2018-01-16');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米电视4','49英寸 原装LG屏 3840×2160 真4K',3299,'xmTV4-49.jpg',500,3,'2018-01-15');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米电视4','55英寸 原装三星屏 3840×2160 真4K',3999,'xmTV4-55.jpg',500,3,'2018-01-13');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米电视4','65英寸 原装三星屏 3840×2160 真4K',8999,'xmTV4-65.jpg',500,3,'2018-01-22');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米电视4A','43英寸 FHD全高清屏 1920*1080',1999,'xmTV4A-43.jpg',500,3,'2018-01-11');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米电视4A','49英寸 FHD全高清屏 1920*1080',2299,'xmTV4A-49.jpg',500,3,'2018-01-21');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米MIX2','全陶瓷 黑色 8GB内存 128GB闪存',4699,'xmMIX2.jpg',500,1,'2018-04-01');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米Note3','全网通 蓝色 6GB内存 64GB闪存',2499,'xmNote3.jpg',500,1,'2018-03-01');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米6','玻璃金属 白色 6GB内存 128GB闪存',2899,'xm6.jpg',500,1,'2018-02-01');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米MAX2','全金属 金色 4GB内存 64GB闪存',1599,'xmMAX2.jpg',500,1,'2018-01-02');
INSERT INTO product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) VALUES('小米5X','全金属 金色 4GB内存 64GB闪存',1499,'xm5X.jpg',500,1,'2018-01-03');

INSERT INTO `product_info` (`p_id`, `p_name`, `p_content`, `p_price`, `p_image`, `p_number`, `type_id`, `p_date`) VALUES('20','ybibo','努力版','666666','a656eca7c4d14e9bb76f6aa069b8fbea.jpg','666666','4','2022-02-23');
INSERT INTO `product_info` (`p_id`, `p_name`, `p_content`, `p_price`, `p_image`, `p_number`, `type_id`, `p_date`) VALUES('21','ybibo','反内卷版','999999','c69dcb7239be474a8aca495896003920.jpg','999999','4','2022-02-23');
INSERT INTO `product_info` (`p_id`, `p_name`, `p_content`, `p_price`, `p_image`, `p_number`, `type_id`, `p_date`) VALUES('22','ybibo','摸鱼版','88888888','336f7ed1ccae426ea1eb500cb8229b20.jpg','88888888','4','2022-02-23');
INSERT INTO `product_info` (`p_id`, `p_name`, `p_content`, `p_price`, `p_image`, `p_number`, `type_id`, `p_date`) VALUES('23','ybibo','本人照片','1','c21c5befc3684305b8c4c9a16ea14951.jpg','1','4','2022-02-23');

#创建前台用户表

CREATE TABLE `users` (
                       `uid` INT(11) NOT NULL AUTO_INCREMENT,
                       `uname` VARCHAR(50) DEFAULT NULL,
                       `upass` VARBINARY(50) DEFAULT NULL,
                       `ustatus` INT(11) DEFAULT NULL,
                       `ulevel` INT(11) DEFAULT NULL,
                       `score` INT(11) DEFAULT NULL,
                       PRIMARY KEY  (`uid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
#增加用户数据
INSERT INTO `users` VALUES ('1', 'zar', 0x313233343536, '0', '0', '0');
INSERT INTO `users` VALUES ('2', 'zhangsan', 0x313233343536, '1', '0', '0');
#创建地址表

CREATE TABLE `address` (
                         `addressId` INT(11) NOT NULL AUTO_INCREMENT,
                         `uid` INT(11) DEFAULT NULL,
                         `cnee` VARCHAR(50) DEFAULT NULL,
                         `phone` VARCHAR(11) DEFAULT NULL,
                         `address` VARCHAR(100) DEFAULT NULL,
                         PRIMARY KEY  (`addressId`),
                         KEY `FK_Reference_1` (`uid`),
                         CONSTRAINT `FK_Reference_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
#增加地址表数据
INSERT INTO `address` VALUES ('1', '1', 'zar', '15266676667', '北京海淀甲骨文');
INSERT INTO `address` VALUES ('2', '1', 'oracle', '15266678888', '北京朝阳科技文化一条街');
INSERT INTO `address` VALUES ('3', '2', '张三', '15290888162', '北京大兴西红门');

#创建购物车表
CREATE TABLE `carshop` (
                         `cid` INT(11) NOT NULL AUTO_INCREMENT,
                         `uid` INT(11) DEFAULT NULL,
                         `pid` INT(11) DEFAULT NULL,
                         `numbers` INT(11) DEFAULT NULL,
                         PRIMARY KEY  (`cid`),
                         KEY `FK_Reference_3` (`uid`),
                         KEY `FK_Reference_4` (`pid`),
                         CONSTRAINT `FK_Reference_4` FOREIGN KEY (`pid`) REFERENCES `product_info` (`p_id`),
                         CONSTRAINT `FK_Reference_3` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
#增加购物车数据
INSERT INTO carshop (uid,pid,numbers) VALUES (1,1,2);
#创建订单表
CREATE TABLE `xmorder` (
                         `oid` CHAR(32) NOT NULL ,
                         `uid` INT(11) DEFAULT NULL,
                         `addressId` INT(11) DEFAULT NULL,
                         `totalprice` DOUBLE(10,2) DEFAULT NULL,
                         `remarks` VARCHAR(200) DEFAULT NULL,
                         `status` VARCHAR(6) DEFAULT NULL,
                         `odate` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ,
                         PRIMARY KEY  (`oid`),
                         KEY `FK_Reference_5` (`uid`),
                         KEY `FK_Reference_6` (`addressId`),
                         CONSTRAINT `FK_Reference_6` FOREIGN KEY (`addressId`) REFERENCES `address` (`addressId`),
                         CONSTRAINT `FK_Reference_5` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)

) ENGINE=INNODB DEFAULT CHARSET=utf8;
#订单表增加数据
INSERT INTO xmorder(oid,uid,addressid,totalprice,remarks,STATUS,odate) VALUES('abcd111222333444777888999000wwww',1,1,9996,'尽快送到','待发货',DEFAULT);
#创建订单明细表

CREATE TABLE `orderdetail` (
                             `odid` INT(11) NOT NULL AUTO_INCREMENT,
                             `oid` CHAR(32) DEFAULT NULL,
                             `pid` INT(11) DEFAULT NULL,
                             `pnumber` INT(11) DEFAULT NULL,
                             `ptotal` DOUBLE(10,2) DEFAULT NULL,
                             PRIMARY KEY  (`odid`),
                             KEY `FK_Reference_7` (`oid`),
                             KEY `FK_Reference_8` (`pid`),
                             CONSTRAINT `FK_Reference_8` FOREIGN KEY (`pid`) REFERENCES `product_info` (`p_id`),
                             CONSTRAINT `FK_Reference_9` FOREIGN KEY (`oid`) REFERENCES `xmorder` (`oid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO orderdetail(oid,pid,pnumber,ptotal) VALUES ('abcd111222333444777888999000wwww',1,2,9996);


SELECT * FROM admin;
SELECT * FROM users;
SELECT * FROM product_type;
SELECT * FROM product_info ;
SELECT * FROM orderdetail;
SELECT * FROM xmorder;
SELECT * FROM carshop;
SELECT * FROM address;
