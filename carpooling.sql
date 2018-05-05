-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 05, 2018 at 03:55 PM
-- Server version: 5.7.19
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carpooling`
--

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
CREATE TABLE IF NOT EXISTS `driver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `posx` double NOT NULL,
  `posy` double NOT NULL,
  `destx` double NOT NULL,
  `desty` double NOT NULL,
  `passengers` int(3) NOT NULL,
  `free_seats` int(3) NOT NULL,
  `regulations` text NOT NULL,
  `cost` int(4) NOT NULL,
  `lastUpdate` int(11) NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`id`, `posx`, `posy`, `destx`, `desty`, `passengers`, `free_seats`, `regulations`, `cost`, `lastUpdate`, `available`) VALUES
(10, 7.589466384404111, 2.5298221281347035, 600, 200, 2, 1, 'd', 4, 1525527489, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pedestrian`
--

DROP TABLE IF EXISTS `pedestrian`;
CREATE TABLE IF NOT EXISTS `pedestrian` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `posx` double NOT NULL,
  `posy` double NOT NULL,
  `destx` double NOT NULL,
  `desty` double NOT NULL,
  `preferences` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
