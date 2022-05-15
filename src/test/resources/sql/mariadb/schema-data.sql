-- Create Client table
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create DressType table
DROP TABLE IF EXISTS `dress_type`;
CREATE TABLE `dress_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create ModelType table
DROP TABLE IF EXISTS `model_type`;
CREATE TABLE `model_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create MeasureType table
DROP TABLE IF EXISTS `measure_type`;
CREATE TABLE `measure_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create MaterialType table
DROP TABLE IF EXISTS `material_type`;
CREATE TABLE `material_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create Dress table
DROP TABLE IF EXISTS `dress`;
CREATE TABLE `dress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `login_role` varchar(255) NOT NULL,
  `credentials_non_expired` bool DEFAULT TRUE,
  `account_non_expired` bool DEFAULT TRUE,
  `account_non_locked` bool DEFAULT TRUE,
  `enabled` bool DEFAULT TRUE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create Employee table
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK17yo6gry2nuwg2erwhbaxqbs9` (`client_id`),
  CONSTRAINT `FK17yo6gry2nuwg2erwhbaxqbs9` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Inserting data in Client table
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(1, 'EMMANUEL', 'JEANS', 'MALE', '418-888-9988');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(2, 'STEPHEN', 'CURRY', 'MALE', '418-000-9090');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(3, 'LEBRON', 'JAMES', 'MALE', '514-261-4210');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(4, 'EMILIE', 'ROSE', 'FEMALE', '615-777-6030');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(5, 'JAQUELINE', 'KENT', 'FEMALE', '418-555-2040');

-- Inserting data in DressType table
INSERT INTO dress_type(id, name) VALUES(1,'PANTS');
INSERT INTO dress_type(id, name) VALUES(2, 'SHIRT');
INSERT INTO dress_type(id, name) VALUES(3, 'SMOKING');
INSERT INTO dress_type(id, name) VALUES(4, 'T-SHIRT');

-- Inserting data in ModelType table
INSERT INTO model_type(id, name) VALUES(1,'SHERPA');
INSERT INTO model_type(id, name) VALUES(2, 'JACKET');
INSERT INTO model_type(id, name) VALUES(3, 'SWEAT');
INSERT INTO model_type(id, name) VALUES(4, 'TINY');

-- Inserting data in MeasureType table
INSERT INTO measure_type(id, name) VALUES(1,'SHOULDER');
INSERT INTO measure_type(id, name) VALUES(2, 'HIPS');
INSERT INTO measure_type(id, name) VALUES(3, 'HAND');
INSERT INTO measure_type(id, name) VALUES(4, 'LEG');

-- Inserting data in MaterialType table
INSERT INTO material_type(id, name, image) VALUES(1, 'COTTON', '/image/path');
INSERT INTO material_type(id, name, image) VALUES(2, 'POLYESTER', '/image/path');
INSERT INTO material_type(id, name, image) VALUES(3, 'SILK', '/image/path');
INSERT INTO material_type(id, name, image) VALUES(4, 'VELVET', '/image/path');

-- Inserting data in Dress table
INSERT INTO dress(id, amount, dress_type_id, material_type_id, model_type_id) VALUES(1, 500, 1, 3, 3);
INSERT INTO dress(id, amount, dress_type_id, material_type_id, model_type_id) VALUES(2, 1000, 2, 2, 3);
INSERT INTO dress(id, amount, dress_type_id, material_type_id, model_type_id) VALUES(3, 750, 1, 3, 2);
INSERT INTO dress(id, amount, dress_type_id, material_type_id, model_type_id) VALUES(4, 1500, 1, 2, 1);

-- Inserting data in Measure table
INSERT INTO measure(id, value, dress_id, measure_type_id) VALUES(1, 5, 1, 3);
INSERT INTO measure(id, value, dress_id, measure_type_id) VALUES(2, 5, 1, 3);
INSERT INTO measure(id, value, dress_id, measure_type_id) VALUES(3, 5, 1, 3);
INSERT INTO measure(id, value, dress_id, measure_type_id) VALUES(4, 5, 1, 3);

-- Inserting data in Login table
INSERT INTO login(id, username, password, login_role, credentials_non_expired, account_non_expired, account_non_locked, enabled) VALUES(1, 'username_1', 'password_1', 'ADMIN', true, true, true, true);
INSERT INTO login(id, username, password, login_role, credentials_non_expired, account_non_expired, account_non_locked, enabled) VALUES(2, 'username_2', 'password_2', 'EMPLOYEE', true, true, true, true);
INSERT INTO login(id, username, password, login_role, credentials_non_expired, account_non_expired, account_non_locked, enabled) VALUES(3, 'username_3', 'password_3', 'MANAGER', true, true, true, true);
INSERT INTO login(id, username, password, login_role, credentials_non_expired, account_non_expired, account_non_locked, enabled) VALUES(4, 'username_4', 'password_4', 'ADMIN', true, true, true, true);

-- Inserting data in Employee table
INSERT INTO employee(id, first_name, last_name, gender, telephone, login_id) VALUES(1, 'KOKOU', 'KOFFI', 'MALE', '99990262', 1);
INSERT INTO employee(id, first_name, last_name, gender, telephone, login_id) VALUES(2, 'EMMANUEL', 'SAMI', 'MALE', '99880210', 2);
INSERT INTO employee(id, first_name, last_name, gender, telephone, login_id) VALUES(3, 'JEANNE', 'SOSSOU', 'FEMALE', '99110952', 3);
INSERT INTO employee(id, first_name, last_name, gender, telephone, login_id) VALUES(4, 'ERIC', 'CANTONA', 'MALE', '92990252', 4);

-- Inserting data in Order table
INSERT INTO `order`(id, number, date, delivery_date, client_id) VALUES(1, 'order-00001', '2021-09-20 18:00:00', '2021-09-30 18:00:00', 1);
INSERT INTO `order`(id, number, date, delivery_date, client_id) VALUES(2, 'order-00002', '2021-06-07 18:00:00', '2021-06-20 18:00:00', 1);
INSERT INTO `order`(id, number, date, delivery_date, client_id) VALUES(3, 'order-00003', '2021-09-20 18:00:00', '2021-09-30 18:00:00', 2);
INSERT INTO `order`(id, number, date, delivery_date, client_id) VALUES(4, 'order-00004', '2021-09-20 18:00:00', '2021-09-30 18:00:00', 3);