-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 02, 2022 at 08:21 PM
-- Server version: 8.0.22
-- PHP Version: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `arvi22_spyware`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int NOT NULL,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `client_logs`
--

CREATE TABLE `client_logs` (
  `id` int NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `pcno` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `empid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `department` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `login_datetime` datetime DEFAULT NULL,
  `logout_datetime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `client_logs`
--

INSERT INTO `client_logs` (`id`, `name`, `pcno`, `empid`, `department`, `login_datetime`, `logout_datetime`) VALUES
(21, 'asdsa', 'as', 'asd', 'HR', '2022-04-29 09:19:35', NULL),
(22, 'dfsdq', 'sdfsd', 'sdfsdf', 'HR', '2022-04-30 00:38:39', NULL),
(23, 'sdfsdq', 'sdfs', 'sdfsdf', 'HR', '2022-04-30 00:40:52', NULL),
(24, 'sdfsd', 'qsdfsdq', 'sdfsdf', 'HR', '2022-04-30 01:09:00', NULL),
(25, 'jhjhj', 'jhgjh', 'jhjh', 'HR', '2022-04-30 01:11:07', NULL),
(26, 'hkjhg', 'hg', 'h', 'HR', '2022-04-30 01:12:49', NULL),
(27, 'das', 'asdas', 'asd', 'Support', '2022-04-30 01:15:57', NULL),
(28, 'fsdf', 'sdsa', 'adasd', 'HR', '2022-04-30 01:20:07', NULL),
(29, 'sdfsd', 'f', '11', 'HR', '2022-04-30 01:27:59', NULL),
(30, 'sdfsd', 'sdfsd', '1', 'HR', '2022-04-30 01:30:49', NULL),
(31, 'hbh', 'hbhb', '2', 'HR', '2022-04-30 01:33:19', NULL),
(32, 'hbh', 'hb', '3', 'HR', '2022-04-30 01:36:54', NULL),
(33, 'dfdsq', 'fgdq', '4', 'HR', '2022-04-30 01:38:51', NULL),
(34, 'jhgjkh', 'jghgh', '5', 'Development', '2022-04-30 01:55:46', NULL),
(35, 'FDSGSD', 'DFSD', '6', 'HR', '2022-04-30 01:58:32', '2022-04-30 01:59:20'),
(36, 'sdfsd', 'sdfsd', 'sd', 'HR', '2022-04-30 02:29:20', NULL),
(37, 'asdas', 's', 'sds', 'HR', '2022-04-30 02:33:58', NULL),
(38, 'dfsd', 'sdfs', 'req', 'HR', '2022-04-30 02:37:28', NULL),
(39, 'sdfsd', 'dfsd', 'lk', 'HR', '2022-04-30 02:40:18', NULL),
(40, 'sdfsd', 'sdfsd', 'ew', 'HR', '2022-04-30 02:42:20', NULL),
(41, 'asdasd', 'asas', 'ssdfsdf', 'HR', '2022-04-30 02:49:17', NULL),
(42, 'vbdf', 'dgsd', 'yu', 'HR', '2022-04-30 03:04:35', NULL),
(43, 'kkjhh', 'hvhg', 'ol', 'HR', '2022-04-30 03:09:41', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_logs`
--
ALTER TABLE `client_logs`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `client_logs`
--
ALTER TABLE `client_logs`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
