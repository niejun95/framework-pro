CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `gmt_create` datetime(6) NOT NULL,
  `gmt_modified` datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

INSERT INTO `user` (`id`, `name`, `gmt_create`, `gmt_modified`) VALUES (1, 'nie', '2022-07-28 10:09:43.000000', '2022-07-28 10:09:48.000000');
INSERT INTO `user` (`id`, `name`, `gmt_create`, `gmt_modified`) VALUES (2, 'jun', '2022-07-26 10:10:05.000000', '2022-07-27 10:10:12.000000');
