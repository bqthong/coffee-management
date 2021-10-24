CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(255) UNIQUE NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255),
  `phone` varchar(255),
  `password` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT 1,
  `role` enum('ADMIN', 'USER') NOT NULL DEFAULT 'USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `user` VALUES (1,'admin', 'Admin', 'Admin', NULL, NULL, '$2a$12$5DrQJVfawCm1Vhk4cUhD1un7M9hgCkNVgYuWeiC//Cqo5nd8fLruS', true, 'ADMIN');