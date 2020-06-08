CREATE TABLE `blog` (
  `bid` int(11) NOT NULL,
  `author_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `blog` (`bid`, `author_id`, `name`) VALUES (1, 1001, 'MYSQL从入门到改行');