-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- By : henry azer
-- Date : 07-05-2020
-- College ID : 220190314
-- WhatsApp : +201207885279
-- Mail : henryazer@outlook.com
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
--
-- Crate Database
--

DROP DATABASE IF EXISTS library;
CREATE DATABASE library;  
USE library;

--
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) DEFAULT NULL,
  `email` varchar(120) DEFAULT NULL,
  `user_name` varchar(100) NOT NULL,
  `a_password` varchar(100) NOT NULL,
  `updation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'Henry Azer','henryazer@yahoo.com','henryazer','henryazer','2020-04-14 22:00:00');
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `author_name` varchar(159) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Anuj kumar'),(2,'Chetan Bhagatt'),(3,'Anita Desai'),(4,'HC Verma'),(5,'R.D. Sharma '),(6,'Edwardo'),(7,'Delperto'),(8,'Adam Robenz'),(9,'Nageeb Mahfouz'),(10,'Joanne Rowling'),(11,'John Melton'),(12,'William Shakespeare');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) NOT NULL,
  `category_id` int NOT NULL,
  `author_id` int NOT NULL,
  `book_serial_number` int NOT NULL,
  `BookPrice` int DEFAULT NULL,
  `copies` int DEFAULT NULL,
  `borrow` int DEFAULT NULL,
  `release_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `author_id` FOREIGN KEY (`id`) REFERENCES `authors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'MySql',5,1,222333,20,14,15,'2017-07-07 22:00:00'),(2,'Math',4,2,2325752,50,10,2,'2015-06-07 22:00:00'),(3,'Physics',4,4,1111,15,3,5,'2017-07-07 22:00:00'),(4,'Biology',4,5,1258653,120,10,3,'2020-04-18 07:05:59'),(5,'English',1,3,23214,40,9,1,'2020-04-19 05:13:53');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyer`
--

DROP TABLE IF EXISTS `buyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `buyer_code` varchar(100) NOT NULL,
  `user_name` varchar(120) NOT NULL,
  `email` varchar(120) NOT NULL,
  `a_password` varchar(120) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `registry_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `StudentId` (`buyer_code`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer`
--

LOCK TABLES `buyer` WRITE;
/*!40000 ALTER TABLE `buyer` DISABLE KEYS */;
INSERT INTO `buyer` VALUES (1,'BID01','Andrew Braza','andrew1@gmail.com','656867','9865472555','2020-03-10 22:00:00'),(2,'BID02','John Roberts','john@yahoo.com','56238','8569710025','2020-02-10 22:00:00'),(3,'BID03','Rey Tejada','rey@gmail.com','hello235','8585856224','2020-04-14 22:00:00'),(4,'BID04','Clide Louie','CLIDE@gmail.com','562s65','4672423754','2020-05-14 22:00:00'),(5,'BID05','Clive Dela Cruz','clive21@yahoo.com','23sql5','0945208280','2020-01-18 22:00:00'),(6,'BID06','nader atef','nader@yahoo.com','2567458','01201584458','2020-04-18 01:05:08'),(7,'BID07','kerolos wahed','kero@yahoo.com','df6582','01135654788','2020-04-18 01:10:32'),(8,'BID08','Engy kalli','engyk@gmail.com','123654','01023658742','2020-04-18 01:58:16'),(9,'BID09','Ahmed hassan','ahmed645@hotmail.com','123987','01106095874','2020-04-18 02:02:47'),(10,'BID091','Alper Aziz','alper@hotmail.com','258963','0112595874','2020-04-18 07:09:02'),(11,'BID0101','Abdelraman','abdo@gmail.com','156321','01203652145','2020-04-18 06:37:09'),(12,'BID0111','Khaled waleed','khaledwa@outlook.com','258963','01236258954','2020-04-19 11:00:54'),(13,'BID0121','Yassmid Said','yassmina@outlook.com','25841','01025425968','2020-05-05 23:39:05'),(14,'BID0131','Alper Ashraf','alper@yahoo.com','1592005','01523658951','2020-05-07 17:54:32'),(16,'BID0151','henryazer','henryazer@outlook.com','henry02565#','01207885279','2020-05-16 23:09:39');
/*!40000 ALTER TABLE `buyer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_name_UNIQUE` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (9,'Drama'),(6,'Fantasy'),(1,'Language'),(2,'Management'),(3,'Romantic'),(4,'Science'),(8,'Stories'),(5,'Technology');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-17  1:47:06
