-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 25, 2024 at 07:23 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `year` int(11) NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT 1,
  `borrow_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `title`, `author`, `year`, `available`, `borrow_date`, `return_date`) VALUES
(4, 'Harry Potter', 'JK Rowling', 2007, 1, NULL, NULL),
(5, 'Snow White', 'Jacod Grimm', 1937, 1, NULL, NULL),
(6, 'The Great Gatsby', 'F. Scott Fitzgerald', 1925, 1, NULL, NULL),
(7, '1984', 'George Orwell', 1949, 1, NULL, NULL),
(8, 'To Kill a Mockingbird', 'Harper Lee', 1960, 1, NULL, NULL),
(9, 'Pride and Prejudice', 'Jane Austen', 1813, 1, NULL, NULL),
(10, 'The Catcher in the Rye', 'J.D. Salinger', 1951, 1, NULL, NULL),
(11, 'Moby-Dick', 'Herman Melville', 1851, 1, NULL, NULL),
(12, 'War and Peace', 'Leo Tolstoy', 1869, 1, NULL, NULL),
(13, 'The Hobbit', 'J.R.R. Tolkien', 1937, 1, NULL, NULL),
(14, 'Crime and Punishment', 'Fyodor Dostoevsky', 1866, 1, NULL, NULL),
(15, 'The Lord of the Rings', 'J.R.R. Tolkien', 1954, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`) VALUES
(1, 'Pavithran', 'pavi8', 'user'),
(2, 'pavi', '1234', 'user'),
(3, 'Vishinu', '12345', 'admin'),
(4, 'Kavi', '1234', 'user'),
(5, 'Afiq', 'afiq4321', 'admin'),
(6, 'Purnishaa', '8888', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
