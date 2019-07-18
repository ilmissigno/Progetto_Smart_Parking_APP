CREATE DATABASE  IF NOT EXISTS `dbsmartparking` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbsmartparking`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: dbsmartparking
-- ------------------------------------------------------
-- Server version	8.0.16

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
-- Table structure for table `affereze_auto`
--

DROP TABLE IF EXISTS `affereze_auto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `affereze_auto` (
  `CF` char(16) NOT NULL,
  `Targa` varchar(7) NOT NULL,
  KEY `CF` (`CF`),
  KEY `Targa` (`Targa`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `affereze_auto`
--

LOCK TABLES `affereze_auto` WRITE;
/*!40000 ALTER TABLE `affereze_auto` DISABLE KEYS */;
/*!40000 ALTER TABLE `affereze_auto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areaparcheggio`
--

DROP TABLE IF EXISTS `areaparcheggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `areaparcheggio` (
  `CodiceArea` int(5) NOT NULL AUTO_INCREMENT,
  `CostoOrario` float NOT NULL,
  PRIMARY KEY (`CodiceArea`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areaparcheggio`
--

LOCK TABLES `areaparcheggio` WRITE;
/*!40000 ALTER TABLE `areaparcheggio` DISABLE KEYS */;
INSERT INTO `areaparcheggio` VALUES (0,2);
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
  PRIMARY KEY (`Targa`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto`
--

LOCK TABLES `auto` WRITE;
/*!40000 ALTER TABLE `auto` DISABLE KEYS */;
INSERT INTO `auto` VALUES ('1','NPLGPP');
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
  `Conto` int(5) DEFAULT NULL,
  PRIMARY KEY (`CF`),
  UNIQUE KEY `Username` (`Username`),
  KEY `Conto` (`Conto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `automobilisti`
--

LOCK TABLES `automobilisti` WRITE;
/*!40000 ALTER TABLE `automobilisti` DISABLE KEYS */;
INSERT INTO `automobilisti` VALUES ('NPL','FLAVIO ','Insinna','ppeppenapo@live.it','sy','lar',12345);
/*!40000 ALTER TABLE `automobilisti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conto`
--

DROP TABLE IF EXISTS `conto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `conto` (
  `IDconto` int(5) NOT NULL,
  `Saldo` int(5) NOT NULL,
  PRIMARY KEY (`IDconto`),
  KEY `IDconto` (`IDconto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conto`
--

LOCK TABLES `conto` WRITE;
/*!40000 ALTER TABLE `conto` DISABLE KEYS */;
INSERT INTO `conto` VALUES (12345,25);
/*!40000 ALTER TABLE `conto` ENABLE KEYS */;
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
  KEY `Targa` (`Targa`),
  KEY `IDTicket` (`IDTicket`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
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
  `Durata` time(5) NOT NULL,
  `Data` date NOT NULL,
  `Targa` varchar(7) NOT NULL,
  `CF` char(16) DEFAULT NULL,
  `CodiceArea` int(5) NOT NULL,
  PRIMARY KEY (`IDTicket`),
  UNIQUE KEY `CF` (`CF`),
  KEY `Targa` (`Targa`),
  KEY `CodiceArea` (`CodiceArea`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (0,'00:00:02.00000','0000-00-00','1','NPL',0);
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
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

-- Dump completed on 2019-07-18 15:55:21
