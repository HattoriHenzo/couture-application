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

-- Create MeasureType table
DROP TABLE IF EXISTS `measure_type`;
CREATE TABLE `measure_type` (
  `id` bigint(20) primary key auto_increment,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Create MaterialType table
DROP TABLE IF EXISTS `material_type`;
CREATE TABLE `material_type` (
  `id` bigint(20) primary key auto_increment,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Create Dress table
DROP TABLE IF EXISTS `dress`;
CREATE TABLE `dress` (
  `id` bigint(20) primary key auto_increment,
  `amount` int(11) DEFAULT NULL,
  `dress_type_id` bigint(20) DEFAULT NULL,
  `material_type_id` bigint(20) DEFAULT NULL,
  `model_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`model_type_id`) REFERENCES `model_type` (`id`),
  FOREIGN KEY (`dress_type_id`) REFERENCES `dress_type` (`id`),
  FOREIGN KEY (`material_type_id`) REFERENCES `material_type` (`id`)
);

-- Create Measure table
DROP TABLE IF EXISTS `measure`;
CREATE TABLE `measure` (
  `id` bigint(20) primary key auto_increment,
  `value` int(11) DEFAULT NULL,
  `dress_id` bigint(20) DEFAULT NULL,
  `measure_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`measure_type_id`) REFERENCES `measure_type` (`id`),
  FOREIGN KEY (`dress_id`) REFERENCES `dress` (`id`)
);

-- Create Login table
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` bigint(20) primary key auto_increment,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `login_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Create Employee table
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) primary key auto_increment,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `login_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`login_id`) REFERENCES `login` (`id`)
);

-- Create Order table
DROP TABLE IF EXISTS "order";
CREATE TABLE "order" (
  `id` bigint(20) primary key auto_increment,
  `number` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
);
