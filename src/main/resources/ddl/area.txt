CREATE TABLE `area` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `area_id` varchar(15) NOT NULL,
  `area_name` varchar(20) DEFAULT NULL,
  `parent_id` varchar(12) DEFAULT NULL,
  `index_link` varchar(15) DEFAULT NULL,
  `level` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_area_id` (`area_id`) USING BTREE,
  KEY `idx_index_link` (`index_link`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (1, '340000000000', '安徽省', NULL, '1', '1');
INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (2, '340100000000', '合肥市', '340000000000', '1-1', '2');
INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (3, '340200000000', '芜湖市', '340000000000', '1-3', '2');
INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (4, '340300000000', '蚌埠市', '340000000000', '1-4', '2');
INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (5, '340400000000', '淮南市', '340000000000', '1-5', '2');
INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (6, '340800000000', '安庆市', '340000000000', '1-6', '2');
INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (7, '340801000000', '市辖区', '340800000000', '1-6-7', '3');
INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (8, '340802000000', '迎江区', '340800000000', '1-6-8', '3');
INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (9, '340827000000', '望江县', '340800000000', '1-6-9', '3');
INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (10, '340827100000', '华阳镇', '340827000000', '1-6-9-10', '4');
INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (11, '340827104000', '高士镇', '340800000000', '1-6-9-11', '4');
INSERT INTO `area`(`id`, `area_id`, `area_name`, `parent_id`, `index_link`, `level`) VALUES (12, '340827104203', '佩山村委会', '340827104000', '1-6-9-11-12', '5');
