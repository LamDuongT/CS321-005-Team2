-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema CLASSPLANERDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CLASSPLANERDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CLASSPLANERDB` DEFAULT CHARACTER SET utf8 ;
USE `CLASSPLANERDB` ;

-- -----------------------------------------------------
-- Table `CLASSPLANERDB`.`tblCatalog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLASSPLANERDB`.`tblCatalog` (
  `catalogID` INT(11) NOT NULL AUTO_INCREMENT,
  `catalogName` VARCHAR(100) NULL DEFAULT NULL,
  `catalogDescription` LONGTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`catalogID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CLASSPLANERDB`.`tblClass`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLASSPLANERDB`.`tblClass` (
  `classID` INT(11) NOT NULL AUTO_INCREMENT,
  `className` VARCHAR(100) NULL DEFAULT NULL,
  `classDescription` LONGTEXT NULL DEFAULT NULL,
  `creditHours` INT(11) NULL DEFAULT NULL,
  `prerequisiteClassID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`classID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CLASSPLANERDB`.`tblClassStudent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLASSPLANERDB`.`tblClassStudent` (
  `classID` INT(11) NOT NULL,
  `studentID` VARCHAR(12) NOT NULL,
  `semesterID` INT(11) NULL DEFAULT NULL,
  `grade` VARCHAR(2) NULL DEFAULT NULL,
  `status` VARCHAR(2) NULL DEFAULT NULL COMMENT 'P = pass\nF = fail\nW = withdraw\n',
  PRIMARY KEY (`classID`, `studentID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CLASSPLANERDB`.`tblMajor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLASSPLANERDB`.`tblMajor` (
  `majorID` INT(11) NOT NULL AUTO_INCREMENT,
  `majorName` VARCHAR(100) NULL DEFAULT NULL,
  `majorDescription` LONGTEXT NULL DEFAULT NULL,
  `catalogID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`majorID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CLASSPLANERDB`.`tblMajorClass`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLASSPLANERDB`.`tblMajorClass` (
  `majorID` INT(11) NOT NULL,
  `classID` INT(11) NOT NULL,
  `majorclassDescription` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`majorID`, `classID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CLASSPLANERDB`.`tblPrerequisite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLASSPLANERDB`.`tblPrerequisite` (
  `classID` INT(11) NOT NULL,
  `prerequisiteClassID` INT(11) NOT NULL,
  `prerequisiteDescription` LONGTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`classID`, `prerequisiteClassID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CLASSPLANERDB`.`tblStudent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLASSPLANERDB`.`tblStudent` (
  `studentID` VARCHAR(12) NOT NULL,
  `studentName` VARCHAR(50) NULL DEFAULT NULL,
  `studentEmail` VARCHAR(45) NULL DEFAULT NULL,
  `studentAddress` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`studentID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
