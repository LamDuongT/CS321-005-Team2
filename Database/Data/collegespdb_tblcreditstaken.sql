-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: collegespdb
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `tblcreditstaken`
--

DROP TABLE IF EXISTS `tblcreditstaken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblcreditstaken` (
  `creditstakenID` int(11) NOT NULL AUTO_INCREMENT,
  `studentID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL,
  `semesterID` int(11) DEFAULT NULL,
  `isChangable` int(1) DEFAULT '0',
  PRIMARY KEY (`creditstakenID`),
  KEY `credittaken_course_idx` (`courseID`),
  KEY `credittaken_profile_idx` (`studentID`),
  KEY `credittaken_semester_idx` (`semesterID`),
  CONSTRAINT `credittaken_course` FOREIGN KEY (`courseID`) REFERENCES `tblcourse` (`courseID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `credittaken_profile` FOREIGN KEY (`studentID`) REFERENCES `tblprofile` (`studentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `credittaken_semester` FOREIGN KEY (`semesterID`) REFERENCES `tblsemester` (`semesterID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='The credit taken for a student';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcreditstaken`
--

LOCK TABLES `tblcreditstaken` WRITE;
/*!40000 ALTER TABLE `tblcreditstaken` DISABLE KEYS */;
INSERT INTO `tblcreditstaken` VALUES (1,9,1,2,0),(2,9,2,2,0),(3,9,3,3,0),(4,9,6,3,0),(5,9,7,4,0),(6,9,81,4,0),(7,9,179,2,0),(8,9,213,5,0),(9,9,212,5,0),(10,9,202,5,0),(11,9,204,5,0),(12,9,34,2,0),(13,9,35,3,0),(14,9,36,3,0),(15,9,37,4,0),(16,9,33,4,0),(17,9,211,3,0),(18,9,199,6,0),(19,9,200,6,0),(20,9,175,2,0);
/*!40000 ALTER TABLE `tblcreditstaken` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-29 17:33:52
