-- Create Client table
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create DressType table
DROP TABLE IF EXISTS `dress_type`;
CREATE TABLE `dress_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create ModelType table
DROP TABLE IF EXISTS `model_type`;
CREATE TABLE `model_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create MeasureType table
DROP TABLE IF EXISTS `measure_type`;
CREATE TABLE `measure_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create Measure table
DROP TABLE IF EXISTS `measure`;
CREATE TABLE `measure` (
  `id` bigint(20) NOT NULL,
  `value` int(11) DEFAULT NULL,
  `dress_id` bigint(20) DEFAULT NULL,
  `measure_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqbqhyekc1h4n9h9pagdldjuq9` (`dress_id`),
  KEY `FK2merkfyt7udl8y3g0hsip3rdm` (`measure_type_id`),
  CONSTRAINT `FK2merkfyt7udl8y3g0hsip3rdm` FOREIGN KEY (`measure_type_id`) REFERENCES `measure_type` (`id`),
  CONSTRAINT `FKqbqhyekc1h4n9h9pagdldjuq9` FOREIGN KEY (`dress_id`) REFERENCES `dress` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;