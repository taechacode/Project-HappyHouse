CREATE DATABASE  IF NOT EXISTS `happyhouse_db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `happyhouse_db`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: happyhouse_db
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `no` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) COLLATE utf8_bin NOT NULL,
  `content` varchar(10000) COLLATE utf8_bin NOT NULL,
  `hit` int NOT NULL DEFAULT '0',
  `regdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `user_id_notice_idx` (`id`),
  CONSTRAINT `notice_to_user_fk` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (4,'테스트 제목','테스트 내용',3,'2022-05-18 15:00:00','admin'),(5,'테스트 제목2','테스트 내용2',1,'2022-05-19 06:10:47','admin'),(6,'테스트 제목2','테스트 내용2',1,'2022-05-19 06:10:50','admin'),(7,'테스트 제목3','테스트 내용3',0,'2022-05-20 05:33:23','admin'),(8,'테스트 제목4','테스트 내용4',0,'2022-05-20 05:35:04','admin'),(9,'테스트 제목6','테스트 내용6',0,'2022-05-20 05:35:30','admin'),(10,'테스트 제목7','테스트 내용7',0,'2022-05-20 05:35:40','admin'),(11,'테스트 제목8','테스트 내용8',0,'2022-05-20 05:53:31','admin'),(12,'테스트 제목9','테스트 내용9',0,'2022-05-20 05:53:44','admin'),(13,'테스트 제목10','테스트 내용10',0,'2022-05-20 05:53:55','admin'),(14,'테스트 제목11','테스트 내용11',1,'2022-05-20 05:54:04','admin'),(15,'테스트 제목12','테스트 내용12',2,'2022-05-20 05:54:14','admin');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-26  9:53:23
