DELIMITER $$

USE `sitedev-fee` $$

DROP PROCEDURE IF EXISTS `pro_TableCreate` $$

CREATE DEFINER = `root`@`%` PROCEDURE `pro_TableCreate` ( )

BEGIN
DECLARE i INT;
DECLARE table_name VARCHAR ( 20 );

SET i = 1;


WHILE i <= 12 DO

SET table_name = CONCAT('fee_2020', IF( CHAR_LENGTH( i ) = 2, i, CONCAT( '0', i ) ));

SET @csql = CONCAT(
'CREATE TABLE ', table_name, ' (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fee_amt` decimal(10, 2) DEFAULT NULL,
  `fee_date` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;'
);

PREPARE create_stmt FROM @csql;

EXECUTE create_stmt;

SET i = i + 1;

END WHILE;

END $$

DELIMITER;