CREATE DATABASE  IF NOT EXISTS `jwt_o2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jwt_o2`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: jwt_o2
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `partim`
--

DROP TABLE IF EXISTS `partim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partim` (
  `id` int(9) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(3) NOT NULL,
  `name` varchar(48) NOT NULL,
  `credits` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partim`
--

LOCK TABLES `partim` WRITE;
/*!40000 ALTER TABLE `partim` DISABLE KEYS */;
INSERT INTO `partim` VALUES (1,'C01','Java Web Technology',3),(2,'C02','Advanced JavaScript',2),(3,'C03','Business Solutions and Digital Marketing',2),(4,'C04','User Experience Design',2),(5,'C05','Information Modeling',2);
/*!40000 ALTER TABLE `partim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(9) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `password` varchar(512) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'demo','ef36d6838767048a54988fe8521f41692c910ea3bfb018c47143c44ed95c53aa1d565f0cfcdb93d95d1d497ffe7def104f5bacd3291edb108d5f1fc483a849f2'),(7,'hayk','ea78ed0947743f520f90315179babeb3494962a6cd5f2cc04d322b7871f38ae5fbfc5b91b1c3a743d5a2c7803cb9a924b83afe9cba2c9f1d900100bb177c8a7a'),(8,'alice','5e348aa660f4c4584557d61937719f67e7b631369492192160776d4e3575ddf45e358cba5b29c07b8a47027b20b6183a98305013d4e3a18ddf507fbd1273436b');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_partim`
--

DROP TABLE IF EXISTS `user_partim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_partim` (
  `userid` int(9) unsigned NOT NULL,
  `partimid` int(9) unsigned NOT NULL,
  PRIMARY KEY (`userid`,`partimid`),
  KEY `fk_user_partim$partimid_idx` (`partimid`),
  CONSTRAINT `fk_user_partim$partimid` FOREIGN KEY (`partimid`) REFERENCES `partim` (`id`),
  CONSTRAINT `fk_user_partim$userid` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_partim`
--

LOCK TABLES `user_partim` WRITE;
/*!40000 ALTER TABLE `user_partim` DISABLE KEYS */;
INSERT INTO `user_partim` VALUES (1,1),(7,3),(1,4),(7,4);
/*!40000 ALTER TABLE `user_partim` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-23 17:51:24
