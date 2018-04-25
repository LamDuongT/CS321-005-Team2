-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: collegespdb
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `tblprereq`
--

DROP TABLE IF EXISTS `tblprereq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblprereq` (
  `prereqID` int(11) NOT NULL AUTO_INCREMENT,
  `courseID` int(11) DEFAULT NULL,
  `prereqCourseID` int(11) DEFAULT NULL,
  PRIMARY KEY (`prereqID`),
  KEY `prereq_course_idx` (`courseID`),
  KEY `prereq_prereq_idx` (`prereqCourseID`),
  CONSTRAINT `prereq_course` FOREIGN KEY (`courseID`) REFERENCES `tblcourse` (`courseID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prereq_prereq` FOREIGN KEY (`prereqCourseID`) REFERENCES `tblcourse` (`courseID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='Prerequisite Courses		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblprereq`
--

LOCK TABLES `tblprereq` WRITE;
/*!40000 ALTER TABLE `tblprereq` DISABLE KEYS */;
INSERT INTO `tblprereq` VALUES (1,3,2),(2,4,2),(3,4,3),(4,5,1),(5,6,3),(6,6,34),(7,7,6),(8,7,81),(9,8,3),(10,8,36),(11,9,4),(12,9,36),(13,10,6),(14,10,9),(15,11,6),(16,11,8),(17,11,36),(18,12,6),(19,12,9),(20,12,39),(21,22,6),(22,22,9),(23,24,6),(24,24,9),(25,184,183),(26,186,185),(27,189,35),(28,190,189),(29,81,179);
/*!40000 ALTER TABLE `tblprereq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-25 12:22:03
