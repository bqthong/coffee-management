CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) UNIQUE NOT NULL,
  `price` decimal(10, 0) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 0,
  `image_url` varchar(255),
  `active` tinyint(1) NOT NULL DEFAULT 1,
  `category_id` bigint(20),
  FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `product` VALUES (1,'Espresso Coffee', 40000, 0, null, 1, 1);