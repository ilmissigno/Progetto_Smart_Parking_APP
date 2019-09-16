CREATE DATABASE  IF NOT EXISTS `dbsmartparking` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `dbsmartparking`;
-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: dbsmartparking
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `areaparcheggio`
--

DROP TABLE IF EXISTS `areaparcheggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `areaparcheggio` (
  `CodiceArea` int(5) NOT NULL AUTO_INCREMENT,
  `CostoOrario` float NOT NULL,
  `Dopo20` tinyint(1) DEFAULT NULL,
  `disponibilita` int(11) DEFAULT NULL,
  PRIMARY KEY (`CodiceArea`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areaparcheggio`
--

LOCK TABLES `areaparcheggio` WRITE;
/*!40000 ALTER TABLE `areaparcheggio` DISABLE KEYS */;
INSERT INTO `areaparcheggio` VALUES (0,2,0,200);
/*!40000 ALTER TABLE `areaparcheggio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auto`
--

DROP TABLE IF EXISTS `auto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `auto` (
  `Targa` varchar(7) NOT NULL,
  `CFProprietario` varchar(16) NOT NULL,
  `Attivo` int(2) DEFAULT NULL,
  PRIMARY KEY (`Targa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto`
--

LOCK TABLES `auto` WRITE;
/*!40000 ALTER TABLE `auto` DISABLE KEYS */;
INSERT INTO `auto` VALUES ('1','NPLGPP',NULL),('11','1111111111111111',NULL),('1234','asdfghjklqwerty1',NULL),('2','asdfghjklqwerty1',NULL),('3','asdfghjklqwerty2',NULL),('4','asdfghjklqwerty3',NULL),('5','asdfghjklqwerty1',NULL),('50','1234567891234567',NULL),('6','asdfghjklqwerty1',NULL),('7','asdfghjklqwerty9',NULL),('88','1111111111111111',NULL),('aasss11','asdfghjklqwerty1',NULL),('AS123DD','asdfghjklqwerty1',NULL),('AW771AE','asdfghjklqwerty1',NULL),('BN342MN','asdfghjklqwerty1',NULL),('CV123SD','asdfghjklqwerty1',NULL),('D','asdfghjklqwerty1',NULL),('ES851SV','asdfghjklqwerty1',NULL),('L','asdfghjklqwerty1',NULL),('QW112AA','asdfghjklqwerty1',NULL),('thanks','asdfghjklqwerty1',NULL);
/*!40000 ALTER TABLE `auto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `automobilisti`
--

DROP TABLE IF EXISTS `automobilisti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `automobilisti` (
  `CF` char(16) NOT NULL,
  `Nome` varchar(20) NOT NULL,
  `Cognome` varchar(20) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Credito` double DEFAULT NULL,
  `Attivo` int(2) DEFAULT NULL,
  PRIMARY KEY (`CF`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `automobilisti`
--

LOCK TABLES `automobilisti` WRITE;
/*!40000 ALTER TABLE `automobilisti` DISABLE KEYS */;
INSERT INTO `automobilisti` VALUES ('asdfghjklqwerty1','Mario','Nobile','mario@mario.it','marionobile1','1234',0,0),('NPL','FLAVIO ','Insinna','ppeppenapo@live.it','sy','lar',33.43333333333335,NULL);
/*!40000 ALTER TABLE `automobilisti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corrispondenza`
--

DROP TABLE IF EXISTS `corrispondenza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `corrispondenza` (
  `username` varchar(20) DEFAULT NULL,
  `Targa` varchar(7) NOT NULL,
  KEY `CF` (`username`),
  KEY `Targa` (`Targa`),
  CONSTRAINT `Targa` FOREIGN KEY (`Targa`) REFERENCES `auto` (`targa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corrispondenza`
--

LOCK TABLES `corrispondenza` WRITE;
/*!40000 ALTER TABLE `corrispondenza` DISABLE KEYS */;
INSERT INTO `corrispondenza` VALUES ('sy','4'),('sy','5'),('sy','6'),('marionobile1','ES851SV'),('sy','7'),('sy','AW771AE'),('sy','AS123DD'),(NULL,'D'),('sy','BN342MN');
/*!40000 ALTER TABLE `corrispondenza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multa`
--

DROP TABLE IF EXISTS `multa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `multa` (
  `IDMulta` char(5) NOT NULL,
  `DataEmissione` date NOT NULL,
  `DataScadenza` date NOT NULL,
  `Importo` int(5) NOT NULL,
  `Targa` varchar(7) NOT NULL,
  `IDVigile` int(10) DEFAULT NULL,
  `IDTicket` int(5) NOT NULL,
  PRIMARY KEY (`IDMulta`),
  UNIQUE KEY `IDVigile` (`IDVigile`),
  KEY `Targa` (`Targa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multa`
--

LOCK TABLES `multa` WRITE;
/*!40000 ALTER TABLE `multa` DISABLE KEYS */;
/*!40000 ALTER TABLE `multa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamenti_auto`
--

DROP TABLE IF EXISTS `pagamenti_auto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pagamenti_auto` (
  `IDMulta` char(5) NOT NULL,
  `CF` char(16) NOT NULL,
  `DataPagamento` date DEFAULT NULL,
  KEY `CF` (`CF`),
  KEY `IDMulta` (`IDMulta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamenti_auto`
--

LOCK TABLES `pagamenti_auto` WRITE;
/*!40000 ALTER TABLE `pagamenti_auto` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamenti_auto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ticket` (
  `IDTicket` int(5) NOT NULL AUTO_INCREMENT,
  `durata` double DEFAULT NULL,
  `datafine` varchar(30) DEFAULT NULL,
  `Targa` varchar(7) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `CodiceArea` int(5) NOT NULL,
  PRIMARY KEY (`IDTicket`),
  KEY `Targa` (`Targa`),
  KEY `CodiceArea` (`CodiceArea`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (19,NULL,'2019-07-20 18:57:44','5','sy',0),(21,NULL,'2019-07-20 18:59:28','5','sy',0),(22,0,'2019-07-22 17:45:09','4','sy',0),(23,NULL,'2019-07-22 02:34:19','5','sy',0),(24,NULL,'2019-07-22 02:38:51','4','sy',0),(25,NULL,'2019-07-21 24:48:38','4','sy',0),(26,1,'2019-07-22 12:54:35','4','sy',0),(27,1,'2019-07-22 12:58:24','4','sy',0),(28,1,'2019-07-22 13:05:36','4','sy',0),(29,1,'2019-07-22 13:07:07','4','sy',0),(30,1,'2019-07-22 13:09:04','4','sy',0),(31,1,'2019-07-22 13:12:10','4','sy',0),(32,1,'2019-07-22 13:15:19','4','sy',0),(33,1,'2019-07-22 13:24:33','4','sy',0),(34,1,'2019-07-22 13:26:43','4','sy',0),(35,1,'2019-07-22 13:34:22','4','sy',0),(36,1,'2019-07-22 14:10:30','4','sy',0),(37,1,'2019-07-22 14:11:44','4','sy',0),(38,1,'2019-07-22 14:13:35','4','sy',0),(39,1,'2019-07-22 14:14:20','4','sy',0),(40,1,'2019-07-22 14:15:24','4','sy',0),(41,1,'2019-07-22 14:26:50','4','sy',0),(42,1,'2019-07-22 14:30:14','4','sy',0),(43,1,'2019-07-22 14:36:43','4','sy',0),(44,1,'2019-07-22 17:16:28','4','sy',0),(45,1,'2019-07-22 18:36:25','4','sy',0),(46,1,'2019-07-22 18:44:34','4','sy',0),(47,0,'2019-07-22 17:51:17','4','sy',0),(48,1,'2019-07-22 19:04:01','4','sy',0),(49,1,'2019-07-22 19:10:19','4','sy',0),(50,1,'2019-07-22 19:22:08','4','sy',0),(51,1,'2019-07-22 19:26:12','4','sy',0),(52,1,'2019-07-22 21:19:39','4','sy',0),(53,1,'2019-07-23 00:03:15','6','sy',0),(54,2,'2019-07-23 01:07:31','5','sy',0),(55,1,'2019-07-23 12:57:38','4','sy',0),(56,1,'2019-07-23 13:06:12','4','sy',0),(57,1,'2019-07-23 13:23:44','4','sy',0),(58,2,'2019-07-23 14:39:35','4','sy',0),(59,1,'2019-07-23 13:44:24','6','sy',0),(60,1,'2019-07-24 00:22:11','50','sy',0),(61,1,'2019-07-24 00:36:06','4','sy',0),(62,1,'2019-07-24 00:39:33','4','sy',0),(63,2,'2019-07-24 01:43:28','4','sy',0),(64,2,'2019-07-24 01:48:20','4','sy',0),(68,1,'2019-07-23 23:36:51','4','sy',0),(69,2,'2019-07-26 11:47:40','4','sy ',0),(70,3,'2019-08-30 22:46:13','4','sy',0),(71,2,'2019-08-30 22:08:10','4','sy',0),(72,2,'2019-08-30 22:14:26','4','sy',0),(73,2,'2019-08-30 22:17:46','4','sy',0),(74,2,'2019-08-30 22:20:42','4','sy',0),(75,3,'2019-08-30 23:30:07','4','sy',0),(76,3,'2019-08-30 23:30:38','4','sy',0),(77,2,'2019-08-30 22:33:58','4','sy',0),(78,2,'2019-08-30 22:41:40','4','sy',0),(79,2,'2019-08-30 23:28:18','4','sy',0),(80,2,'2019-08-30 23:30:10','4','sy',0),(82,3,'2019/09/10 16:40:41','AW771AE','sy',0),(93,3,'2019/09/11 18:32:55','AW771AE','sy',0),(95,1,'2019/09/11 16:55:33','4','sy',0),(96,1,'2019/09/11 17:00:46','4','sy',0),(97,1,'2019/09/11 17:05:39','4','sy',0),(98,1,'2019/09/11 17:09:25','4','sy',0),(99,1,'2019/09/11 17:18:59','4','sy',0),(100,1,'2019/09/11 17:19:17','4','sy',0);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vigile`
--

DROP TABLE IF EXISTS `vigile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vigile` (
  `CF` varchar(16) NOT NULL,
  `IDVigile` int(10) NOT NULL,
  `Nome` varchar(20) NOT NULL,
  `Cognome` varchar(20) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  PRIMARY KEY (`CF`,`IDVigile`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vigile`
--

LOCK TABLES `vigile` WRITE;
/*!40000 ALTER TABLE `vigile` DISABLE KEYS */;
/*!40000 ALTER TABLE `vigile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-16 17:17:59
