-- create databases
CREATE DATABASE IF NOT EXISTS `customer_db`;
CREATE USER IF NOT EXISTS 'customer'@'%' IDENTIFIED BY 'customer';
GRANT ALL ON `customer_db`.* TO 'customer'@'%' ;

CREATE DATABASE IF NOT EXISTS `product_db`;
CREATE USER IF NOT EXISTS 'product'@'%' IDENTIFIED BY 'product';
GRANT ALL ON `product_db`.* TO 'product'@'%' ;

-- create root user and grant rights
-- CREATE USER 'root'@'localhost' IDENTIFIED BY 'local';
-- GRANT ALL ON *.* TO 'root'@'%';
