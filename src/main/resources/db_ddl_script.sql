CREATE DATABASE `cruise` /*!40100 DEFAULT CHARACTER SET utf8 */;


CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) NOT NULL,
  `create_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ship` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `count_passengers` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `id_ship` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `speciality` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_ship_staff_idx` (`id_ship`),
  CONSTRAINT `id_ship_staff` FOREIGN KEY (`id_ship`) REFERENCES `ship` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cruise` (
  `id` int(11) NOT NULL,
  `id_ship` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `city_from` int(11) NOT NULL,
  `city_to` int(11) NOT NULL,
  `date_start` datetime NOT NULL,
  `date_finish` datetime NOT NULL,
  `ports_count` int(11) DEFAULT NULL,
  `category` varchar(45) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_ship_cruise_idx` (`id_ship`),
  KEY `id_city_from_idx` (`city_from`),
  KEY `id_city_to_idx` (`city_to`),
  CONSTRAINT `id_city_from` FOREIGN KEY (`city_from`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_city_to` FOREIGN KEY (`city_to`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_ship_cruise` FOREIGN KEY (`id_ship`) REFERENCES `ship` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_cruise` int(11) NOT NULL,
  `ticket` varchar(45) NOT NULL,
  `price` decimal(50,0) NOT NULL,
  `bonus` varchar(45) DEFAULT NULL,
  `excursion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_user_order_idx` (`id_user`),
  KEY `id_cruise_order_idx` (`id_cruise`),
  CONSTRAINT `id_cruise_order` FOREIGN KEY (`id_cruise`) REFERENCES `cruise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_user_order` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bucket` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_cruise` int(11) NOT NULL,
  `ticket` varchar(45) NOT NULL,
  `price` decimal(50,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_user_bucket_idx` (`id_user`),
  KEY `id_cruise_bucket_idx` (`id_cruise`),
  CONSTRAINT `id_cruise_bucket` FOREIGN KEY (`id_cruise`) REFERENCES `cruise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_user_bucket` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `excursion` (
  `id` int(11) NOT NULL,
  `id_cruise` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` decimal(50,0) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_cruise_ex_idx` (`id_cruise`),
  CONSTRAINT `id_cruise_ex` FOREIGN KEY (`id_cruise`) REFERENCES `cruise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

