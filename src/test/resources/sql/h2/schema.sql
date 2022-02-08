-- Create Client table
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) primary key auto_increment,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Create DressType table
DROP TABLE IF EXISTS `dress_type`;
CREATE TABLE `dress_type` (
  `id` bigint(20) primary key auto_increment,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Create ModelType table
DROP TABLE IF EXISTS `model_type`;
CREATE TABLE `model_type` (
  `id` bigint(20) primary key auto_increment,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
