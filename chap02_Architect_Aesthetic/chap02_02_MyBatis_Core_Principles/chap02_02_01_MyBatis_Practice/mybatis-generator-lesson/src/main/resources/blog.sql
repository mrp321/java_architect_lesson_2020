CREATE TABLE `blog` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `blog` (`bid`, `name`, `author_id`) VALUES (1, 'RabbitMQ延时消息', 1001);
INSERT INTO `blog` (`bid`, `name`, `author_id`) VALUES (2, 'MyBatis源码分析', 1008);