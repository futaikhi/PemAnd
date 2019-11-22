-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2019 at 11:14 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pem_and`
--

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `ID` int(11) NOT NULL,
  `USERS_ID` int(11) NOT NULL,
  `NAMA` varchar(10) NOT NULL,
  `A` int(2) NOT NULL,
  `B` int(2) NOT NULL,
  `C` int(2) NOT NULL,
  `GAMBAR` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`ID`, `USERS_ID`, `NAMA`, `A`, `B`, `C`, `GAMBAR`) VALUES
(1, 1, 'Samsung', 4, 5, 3, 'https://asset.kompas.com/crops/xd8_OkCtAQuojPCP1qhofJGDnaY=/0x0:0x0/780x390/data/photo/2015/02/23/1540277logo-samsung780x390.jpg'),
(2, 1, 'Iphone', 4, 4, 4, 'https://image.flaticon.com/icons/png/512/23/23656.png'),
(3, 1, 'Xiaomi', 3, 4, 2, 'https://icons-for-free.com/iconfiles/png/512/company+mi+mobile+xiaomi+icon-1320168262452948540.png');

-- --------------------------------------------------------

--
-- Table structure for table `data_grid`
--

CREATE TABLE `data_grid` (
  `ID` int(11) NOT NULL,
  `USERS_ID` int(11) NOT NULL,
  `NAMA` varchar(12) NOT NULL,
  `GAMBAR` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `USERNAME` varchar(12) NOT NULL,
  `PASSWORD` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `USERNAME`, `PASSWORD`) VALUES
(1, 'Futaikhi', 'fiki');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `USERS_ID` (`USERS_ID`);

--
-- Indexes for table `data_grid`
--
ALTER TABLE `data_grid`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `USERS_ID` (`USERS_ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data`
--
ALTER TABLE `data`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `data_grid`
--
ALTER TABLE `data_grid`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `data`
--
ALTER TABLE `data`
  ADD CONSTRAINT `data_ibfk_1` FOREIGN KEY (`USERS_ID`) REFERENCES `users` (`ID`);

--
-- Constraints for table `data_grid`
--
ALTER TABLE `data_grid`
  ADD CONSTRAINT `data_grid_ibfk_1` FOREIGN KEY (`USERS_ID`) REFERENCES `users` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
