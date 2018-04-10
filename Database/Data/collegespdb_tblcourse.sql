-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: collegespdb
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
-- Dumping data for table `tblcourse`
--

LOCK TABLES `tblcourse` WRITE;
/*!40000 ALTER TABLE `tblcourse` DISABLE KEYS */;
INSERT INTO `tblcourse` VALUES (1,'CS110','Essentials of Computer Science',3,3,'1|2','-1'),(2,'CS112','Introduction to Computer Programming',4,3,'1|2','-1'),(3,'CS211','Object-Oriented Programming',3,3,'1|2','-1'),(4,'CS262','Introduction to Low-Level Programming',3,3,'1|2','-1'),(5,'CS306','Synthesis of Ethics and Law for the Computing Professiona',3,3,'1|2','-1'),(6,'CS310','Data Structures',3,3,'1|2','-1'),(7,'CS321','Software Engineering',3,3,'1|2','-1'),(8,'CS330','Formal Methods and Models',3,3,'1|2','-1'),(9,'CS367','Computer Systems and Programming',4,3,'1|2','-1'),(10,'CS471','Operating Systems',3,3,'1|2','-1'),(11,'CS483','Analysis of Algorithms',3,3,'1|2','-1'),(12,'CS455','Computer Communications and Networking',3,3,'1','-1'),(13,'CS468','Secure Programming and Systems',3,3,'1','-1'),(14,'CS475','Concurrent and Distributed Systems',3,3,'1','-1'),(15,'CS425','Game Programming I',3,3,'1','-1'),(16,'CS440','Language Processors and Programming Environments',3,3,'1','-1'),(17,'CS450','Database Concepts',3,3,'1','-1'),(18,'CS451','Computer Graphics',3,3,'1','-1'),(19,'CS455','Computer Communications and Networking',3,3,'1','-1'),(20,'CS463','Comparative Programming Languages',3,3,'1','-1'),(21,'CS465','Computer Systems Architecture',3,3,'1','-1'),(22,'CS468','Secure Programming and Systems',3,3,'1','-1'),(23,'CS469','Security Engineering',3,3,'1','-1'),(24,'CS475','Concurrent and Distributed Systems',3,3,'1','-1'),(25,'CS477','Mobile Application Development',3,3,'1','-1'),(26,'CS480','Introduction to Artificial Intelligence',3,3,'1','-1'),(27,'CS482','Computer Vision',3,3,'1','-1'),(28,'CS484','Data Mining',3,3,'1','-1'),(29,'CS485','Autonomous Robotics',3,3,'1','-1'),(30,'CS490','Design Exhibition',3,3,'1','-1'),(31,'CS491','Industry-Sponsored Senior Design Project 1',3,3,'1','-1'),(32,'CS499','Special Topics in Computer Science 1',3,3,'1','-1'),(33,'MATH446','Numerical Analysis I',3,3,'1','-1'),(34,'MATH113','Analytic Geometry and Calculus I (Mason Core)',4,3,'1|2','-1'),(35,'MATH114','Analytic Geometry and Calculus II',4,3,'1|2','-1'),(36,'MATH125','Discrete Mathematics I (Mason Core)',3,3,'1|2|6','-1'),(37,'MATH203','Linear Algebra',3,3,'1|2','-1'),(38,'MATH213','Analytic Geometry and Calculus III',3,3,'1','-1'),(39,'STAT344','Probability and Statistics for Engineers and Scientists I 1',3,3,'1','-1'),(40,'OR481','Numerical Methods in Engineering',3,3,'1','-1'),(41,'STAT354','Probability and Statistics for Engineers and Scientists II',3,3,'1','-1'),(42,'OR335','Discrete Systems Modeling and Simulation',3,3,'1','-1'),(43,'OR441','Deterministic Operations Research',3,3,'1','-1'),(44,'OR442','Stochastic Operations Research',3,3,'1','-1'),(45,'ECE301','Digital Electronics',3,3,'1','-1'),(46,'ECE431','Digital Circuit Design',3,3,'1','-1'),(47,'ECE447','Single-Chip Microcomputers',3,3,'1','-1'),(48,'ECE450','Mobile Robots',3,3,'1','-1'),(49,'ECE511','Microprocessors',3,3,'1','-1'),(50,'SWE432','Web Application Development',3,3,'1','-1'),(51,'SWE437','Software Testing and Maintenance',3,3,'1','-1'),(52,'SWE443','Software Architectures',3,3,'1','-1'),(53,'SYST371','Systems Engineering Management',3,3,'1','-1'),(54,'SYST470','Human Factors Engineering',3,3,'1','-1'),(55,'PHIL371','Philosophy of Natural Sciences',3,3,'1','-1'),(56,'PHIL376','Symbolic Logic',3,3,'1','-1'),(57,'ENGH388','Professional and Technical Writing',3,3,'1','-1'),(58,'PHYS160','University Physics I (Mason Core)',3,3,'2','-1'),(59,'PHYS161','University Physics I Laboratory (Mason Core)',1,3,'2','-1'),(60,'CHEM201','Introductory Chemistry I (Mason Core)',3,3,'2','-1'),(61,'BIOL213','Cell Structure and Function (Mason Core)',4,3,'2','-1'),(62,'BINF450','Bioinformatics for Life Sciences',4,3,'2','-1'),(63,'BIOL482','Introduction to Molecular Genetics',3,3,'2','-1'),(64,'BIOL580','Computer Applications for the Life Sciences',3,3,'2','-1'),(65,'CS450','Database Concepts',3,3,'2','-1'),(66,'BINF401','Bioinformatics and Computational Biology I',3,3,'2','-1'),(67,'CS444','Introduction to Computational Biology',3,3,'2','-1'),(68,'BINF402','Bioinformatics and Computational Biology II',3,3,'2','-1'),(69,'CS445','Computational Methods for Genomics',3,3,'2','-1'),(70,'GAME230','History of Computer Game Design',3,3,'3','-1'),(71,'CS306','Synthesis of Ethics and Law for the Computing Professional (Mason Core) 1',3,3,'3','-1'),(72,'CS325','Introduction to Game Design',3,3,'3','-1'),(73,'CS351','Visual Computing',3,3,'3','-1'),(74,'AVT104','Two-Dimensional Design and Color (Mason Core)',4,3,'3','-1'),(75,'STAT344','Probability and Statistics for Engineers and Scientists I',3,3,'3','-1'),(76,'CS425','Game Programming I',3,3,'3','-1'),(77,'CS426','Game Programming II',3,3,'3','-1'),(78,'CS451','Computer Graphics',3,3,'3','-1'),(79,'AVT382','2D Experimental Animation',3,3,'3','-1'),(80,'AVT383','3D Experimental Animation',3,3,'3','-1'),(81,'ENG302','Advanced Composition',3,3,'1','-1');
/*!40000 ALTER TABLE `tblcourse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-08 21:40:22
