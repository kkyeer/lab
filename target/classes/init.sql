-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS t_user_0;
CREATE TABLE `t_user_0`  (
                           `id` int(64) NOT NULL,
                           `user_id` int(64) NOT NULL,
                           `username` varchar(20) NULL,
                           `create_time` timestamp(0) NOT NULL DEFAULT current_timestamp,
                           PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS t_user_1;
CREATE TABLE `t_user_1`  (
                           `id` int(64) NOT NULL,
                           `user_id` int(64) NOT NULL,
                           `username` varchar(20) NULL,
                           `create_time` timestamp(0) NOT NULL DEFAULT current_timestamp,
                           PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS t_order_0;
CREATE TABLE `t_order_0`  (
                            `id` int(64) NOT NULL,
                            `order_id` int(64) NOT NULL,
                            `user_id` int(64) NOT NULL,
                            `create_time` timestamp(0) NOT NULL DEFAULT current_timestamp,
                            PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS t_order_1;
CREATE TABLE `t_order_1`  (
                            `id` int(64) NOT NULL,
                            `order_id` int(64) NOT NULL,
                            `user_id` int(64) NOT NULL,
                            `create_time` timestamp(0) NOT NULL DEFAULT current_timestamp,
                            PRIMARY KEY (`id`)
);