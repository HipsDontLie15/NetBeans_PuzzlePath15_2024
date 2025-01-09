-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 09, 2024 at 08:46 AM
-- Server version: 8.0.30
-- PHP Version: 8.2.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vp_puzzle15`
--

-- --------------------------------------------------------

--
-- Table structure for table `score`
--

CREATE TABLE `score` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `level` char(5) DEFAULT NULL,
  `moves` smallint DEFAULT NULL,
  `time_seconds` time DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `score`
--

INSERT INTO `score` (`id`, `user_id`, `level`, `moves`, `time_seconds`, `timestamp`) VALUES
(1, 1, '2', 62, '00:12:50', '2024-08-09 06:16:11'),
(2, 2, '2', 75, '01:59:20', '2024-08-09 06:16:11'),
(3, 4, '3', 25, '00:22:59', '2024-08-09 06:19:05'),
(4, 6, '3', 22, '00:05:40', '2024-08-09 06:19:05'),
(5, 7, '1', 53, '00:00:50', '2024-08-09 06:19:53'),
(6, 8, '3', 12, '00:00:06', '2024-08-09 08:44:59'),
(7, 10, '1', 42, '00:00:23', '2024-08-09 06:20:54'),
(8, 11, '1', 10, '00:00:03', '2024-08-09 06:20:54'),
(9, 12, '2', 296, '00:02:41', '2024-08-09 06:21:19'),
(10, 8, '2', 16, '00:00:13', '2024-08-09 08:42:16'),
(11, 8, '1', 14, '00:00:06', '2024-08-09 08:45:42');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `first_name` varchar(127) NOT NULL,
  `last_name` varchar(127) NOT NULL,
  `email` varchar(127) NOT NULL,
  `password` varchar(127) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `timestamp`) VALUES
(1, 'tet', 'qwe', 'qwe@gm', '123', '2024-08-08 14:52:49'),
(2, 'mr', 'apple', 'apple@gm.com', '123', '2024-08-08 14:52:49'),
(4, 'mrs', 'paer', 'paer@gmcom', '321', '2024-08-08 14:52:49'),
(6, 'jr.', 'apple', 'jrapple@gm', '321', '2024-08-08 14:52:49'),
(7, 'ron', 'ripple', 'rr@gm', '312', '2024-08-08 14:52:49'),
(8, 'harry', 'potter', 'hp@hp', '1243', '2024-08-08 14:52:49'),
(9, 'pop', 'yui', 'yui@gm', '123', '2024-08-08 15:22:37'),
(10, 'kai liang', 'david suan', 'klds@gm', '123', '2024-08-08 17:00:20'),
(11, 'InteresTeng', '.', 'abcd', 'abcd', '2024-08-09 05:28:24'),
(12, 'so', 'hai', 'sohai', 'sohai', '2024-08-09 05:44:22');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `score`
--
ALTER TABLE `score`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `score`
--
ALTER TABLE `score`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
