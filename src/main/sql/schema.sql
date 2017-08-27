CREATE DATABASE `sell`;

USE `sell`;

CREATE TABLE 'product_info' (
  `product_id`          VARCHAR(32)   NOT NULL,
  `product_name`        VARCHAR(64)   NOT NULL,
  `product_price`       DECIMAL(8, 2) NOT NULL,
  `product_stock`       INT           NOT NULL,
  `product_description` VARCHAR(64),
  `create_time`         TIMESTAMP     NOT NULL  DEFAULT current_timestamp,
  `update_time`         TIMESTAMP     NOT NULL  DEFAULT current_timestamp ON UPDATE current_timestamp,
  PRIMARY KEY (`product_id`)
)
  COMMENT 'products';

CREATE TABLE `product_category` (
  `category_id`   INT         NOT NULL  AUTO_INCREMENT,
  `category_name` VARCHAR(64) NOT NULL,
  `category_type` INT         NOT NULL,
  `create_time`   TIMESTAMP   NOT NULL  DEFAULT current_timestamp,
  `update_time`   TIMESTAMP   NOT NULL  DEFAULT current_timestamp ON UPDATE current_timestamp,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uqe_category_type` (`category_type`)
)
  COMMENT 'categories';

CREATE TABLE `order_master` (
  `order_id`      VARCHAR(32)   NOT NULL,
  `buyer_name`    VARCHAR(32)   NOT NULL,
  `buer_phone`    VARCHAR(32)   NOT NULL,
  `buyer_address` VARCHAR(128)  NOT NULL,
  `buyer_openid`  VARCHAR(64)   NOT NULL
  COMMENT 'wechat openId',
  `order_amout`   DECIMAL(8, 2) NOT NULL,
  `order_status`  TINYINT(3)    NOT NULL  DEFAULT '0'
  COMMENT ' default 0 order begin',
  `create_time`   TIMESTAMP     NOT NULL  DEFAULT current_timestamp,
  `update_time`   TIMESTAMP     NOT NULL  DEFAULT current_timestamp ON UPDATE current_timestamp,
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
)
  COMMENT 'orders';

CREATE TABLE `order_detail` (
  `detail_id`        VARCHAR(32)   NOT NULL,
  `order_id`         VARCHAR(32)   NOT NULL,
  `product_id`       VARCHAR(32)   NOT NULL,
  `product_name`     VARCHAR(32)   NOT NULL,
  `product_price`    DECIMAL(8, 2) NOT NULL,
  `product_quantity` INT           NOT NULL,
  `product_icon`     VARCHAR(512),
  `create_time`      TIMESTAMP     NOT NULL  DEFAULT current_timestamp,
  `update_time`      TIMESTAMP     NOT NULL  DEFAULT current_timestamp ON UPDATE current_timestamp,
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)

)
  COMMENT 'details for unique order';

