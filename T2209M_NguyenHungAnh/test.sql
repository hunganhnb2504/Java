-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 20, 2024 at 06:10 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `student_score_t`
--

CREATE TABLE `student_score_t` (
  `student_score_id` int(11) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `score1` decimal(5,2) DEFAULT NULL,
  `score2` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `student_score_t`
--

INSERT INTO `student_score_t` (`student_score_id`, `student_id`, `subject_id`, `score1`, `score2`) VALUES
(1, 1, 1, 8.50, 7.00);

-- --------------------------------------------------------

--
-- Table structure for table `student_t`
--

CREATE TABLE `student_t` (
  `student_id` int(11) NOT NULL,
  `student_code` varchar(20) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `student_t`
--

INSERT INTO `student_t` (`student_id`, `student_code`, `full_name`, `address`) VALUES
(1, 'T2209M', 'Nguyen Hoang Phuong', 'Ha Noi'),
(2, 'T2209M', 'Hieu', 'Ninh Binh'),
(3, 'T2209M', 'Hung Anh', 'Ninh Binh');

-- --------------------------------------------------------

--
-- Table structure for table `subject_t`
--

CREATE TABLE `subject_t` (
  `subject_id` int(11) NOT NULL,
  `subject_code` varchar(20) NOT NULL,
  `subject_name` varchar(100) NOT NULL,
  `credit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `subject_t`
--

INSERT INTO `subject_t` (`subject_id`, `subject_code`, `subject_name`, `credit`) VALUES
(1, 'JAVA', 'Java Programming', 4),
(2, 'PHP', 'PHP Programming', 3),
(3, 'WDA', 'Web Development and Applications', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `student_score_t`
--
ALTER TABLE `student_score_t`
  ADD PRIMARY KEY (`student_score_id`),
  ADD KEY `fk_student_id` (`student_id`),
  ADD KEY `fk_subject_id` (`subject_id`);

--
-- Indexes for table `student_t`
--
ALTER TABLE `student_t`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `subject_t`
--
ALTER TABLE `subject_t`
  ADD PRIMARY KEY (`subject_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student_score_t`
--
ALTER TABLE `student_score_t`
  MODIFY `student_score_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `student_t`
--
ALTER TABLE `student_t`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `subject_t`
--
ALTER TABLE `subject_t`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `student_score_t`
--
ALTER TABLE `student_score_t`
  ADD CONSTRAINT `fk_student_id` FOREIGN KEY (`student_id`) REFERENCES `student_t` (`student_id`),
  ADD CONSTRAINT `fk_subject_id` FOREIGN KEY (`subject_id`) REFERENCES `subject_t` (`subject_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
