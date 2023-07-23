CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `money` varchar(10) NOT NULL,
  `create_time` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `account`(`id`, `name`, `money`, `create_time`) VALUES (1, 'jack', '200', '2022-06-01');
INSERT INTO `account`(`id`, `name`, `money`, `create_time`) VALUES (2, 'rose', '1000', '2022-06-01');
