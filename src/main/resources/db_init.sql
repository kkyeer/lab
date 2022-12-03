-- test.tx_test definition

CREATE TABLE `tx_test` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `update_time` datetime DEFAULT NULL,
                           `version` int DEFAULT '1',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;