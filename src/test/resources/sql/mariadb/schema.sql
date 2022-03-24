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

-- Create MaterialType table
DROP TABLE IF EXISTS `material_type`;
CREATE TABLE `material_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create Dress table
DROP TABLE IF EXISTS `dress`;
CREATE TABLE `dress` (
  `id` bigint(20) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `dress_type_id` bigint(20) DEFAULT NULL,
  `material_type_id` bigint(20) DEFAULT NULL,
  `model_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp4jca6lvr5oq2wly2jygs5gth` (`dress_type_id`),
  KEY `FKpk4wu3874dg6pnxjh3kktbawp` (`material_type_id`),
  KEY `FKmc203tdqetcmrfa6htoacv22k` (`model_type_id`),
  CONSTRAINT `FKmc203tdqetcmrfa6htoacv22k` FOREIGN KEY (`model_type_id`) REFERENCES `model_type` (`id`),
  CONSTRAINT `FKp4jca6lvr5oq2wly2jygs5gth` FOREIGN KEY (`dress_type_id`) REFERENCES `dress_type` (`id`),
  CONSTRAINT `FKpk4wu3874dg6pnxjh3kktbawp` FOREIGN KEY (`material_type_id`) REFERENCES `material_type` (`id`)
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

-- Create Login table
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `login_category` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create Employee table
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `login_id` bigint(20),
  PRIMARY KEY (`id`),
  KEY `FKqyykoai09ggqx43yxgy5uj059` (`login_id`),
  CONSTRAINT `FKqyykoai09ggqx43yxgy5uj059` FOREIGN KEY (`login_id`) REFERENCES `login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create Order table
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK17yo6gry2nuwg2erwhbaxqbs9` (`client_id`),
  CONSTRAINT `FK17yo6gry2nuwg2erwhbaxqbs9` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;