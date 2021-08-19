-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 18, 2021 at 02:24 PM
-- Server version: 5.7.26
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spring_shopping_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `email`, `password`) VALUES
(6, 'noura', 'noura@gmail.com', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'category 1'),
(2, 'Category 2');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `name`, `email`, `password`) VALUES
(1, 'Noor', 'noor@email.com', '123456'),
(4, 'asdf', 'aa@yahoo.com', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Amount` double NOT NULL,
  `Customer_Address` varchar(255) NOT NULL,
  `Customer_Email` varchar(128) NOT NULL,
  `Customer_Name` varchar(255) NOT NULL,
  `Customer_Phone` varchar(128) NOT NULL,
  `Order_Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Order_Num` int(11) NOT NULL,
  `cust_id` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_OR_NUM ` (`Order_Num`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`ID`, `Amount`, `Customer_Address`, `Customer_Email`, `Customer_Name`, `Customer_Phone`, `Order_Date`, `Order_Num`, `cust_id`) VALUES
(1, 100, 'address', 'wa@yahoo.com', 'Hanan Ahmed', '055555123', '2021-08-15 08:59:33', 1, 1),
(2, 100, 'address', 'wa@yahoo.com', 'Hanan Ahmed', '055555123', '2021-08-15 09:01:13', 2, 2),
(3, 100, 'address', 'wa@yahoo.com', 'Hanan Ahmed', '055555123', '2021-08-15 09:05:32', 3, 4),
(4, 100, 'address', 'wa@yahoo.com', 'Hanan Ahmed', '055555123', '2021-08-14 11:59:55', 4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
CREATE TABLE IF NOT EXISTS `order_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Amount` double NOT NULL,
  `Price` double NOT NULL,
  `Quanity` int(11) NOT NULL,
  `ORDER_ID` varchar(50) NOT NULL,
  `PRODUCT_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ORDER_DETAIL_ORD_FK` (`ORDER_ID`),
  KEY `ORDER_DETAIL_PROD_FK` (`PRODUCT_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`ID`, `Amount`, `Price`, `Quanity`, `ORDER_ID`, `PRODUCT_ID`) VALUES
(1, 100, 100, 1, 'null', 'S001'),
(2, 100, 100, 1, '3', 'S001'),
(3, 100, 100, 2, '4', 'S001');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `Code` varchar(20) NOT NULL,
  `Create_Date` datetime NOT NULL,
  `Image` longblob,
  `Name` varchar(255) NOT NULL,
  `Price` double NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`Code`, `Create_Date`, `Image`, `Name`, `Price`, `category_id`) VALUES
('S001', '2021-08-12 09:55:28', NULL, 'Core Java', 100, 1),
('S002', '2021-08-12 09:55:28', NULL, 'Sport Nike', 50, 1),
('S003', '2021-08-12 09:55:28', NULL, 'Swift for Beginners', 120, 1),
('S004', '2021-08-12 09:55:28', NULL, 'Oracle XML Parser', 120, 1),
('S005', '2021-08-12 09:55:28', NULL, 'CSharp Tutorial for Beginers', 110, 1),
('55240', '2021-08-16 12:15:14', NULL, 'Nike', 11, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
