-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema collegespdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema collegespdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `collegespdb` DEFAULT CHARACTER SET utf8 ;
USE `collegespdb` ;

-- -----------------------------------------------------
-- Table `collegespdb`.`tblcatalog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegespdb`.`tblcatalog` (
  `catalogID` INT(11) NOT NULL AUTO_INCREMENT,
  `catalogName` VARCHAR(45) NULL DEFAULT NULL,
  `catalogDesc` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`catalogID`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COMMENT = 'Catalog year';


-- -----------------------------------------------------
-- Table `collegespdb`.`tblcourse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegespdb`.`tblcourse` (
  `courseID` INT(11) NOT NULL AUTO_INCREMENT,
  `courseName` VARCHAR(45) NULL DEFAULT NULL,
  `courseDesc` MEDIUMTEXT NULL DEFAULT NULL,
  `creditHours` INT(11) NULL DEFAULT NULL,
  `catalogID` INT(11) NOT NULL,
  `majorID` VARCHAR(50) NULL DEFAULT NULL,
  `minorID` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`courseID`),
  INDEX `course_catalog_idx` (`catalogID` ASC),
  INDEX `course_major_idx` (`majorID` ASC),
  INDEX `course_minor_idx` (`minorID` ASC),
  CONSTRAINT `course_catalog`
    FOREIGN KEY (`catalogID`)
    REFERENCES `collegespdb`.`tblcatalog` (`catalogID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 82
DEFAULT CHARACTER SET = utf8
COMMENT = 'Courses	';


-- -----------------------------------------------------
-- Table `collegespdb`.`tblprofile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegespdb`.`tblprofile` (
  `studentID` INT(11) NOT NULL AUTO_INCREMENT,
  `netID` VARCHAR(45) NULL DEFAULT NULL,
  `studentName` VARCHAR(45) NOT NULL,
  `studentEmail` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `profileName` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`studentID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COMMENT = 'Profile';


-- -----------------------------------------------------
-- Table `collegespdb`.`tblsemester`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegespdb`.`tblsemester` (
  `semesterID` INT(11) NOT NULL AUTO_INCREMENT,
  `semesterName` VARCHAR(45) NULL DEFAULT NULL,
  `semesterDesc` MEDIUMTEXT NULL DEFAULT NULL,
  `creditMin` INT(11) NULL DEFAULT NULL,
  `creditMax` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`semesterID`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8
COMMENT = 'Semester';


-- -----------------------------------------------------
-- Table `collegespdb`.`tblcreditstaken`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegespdb`.`tblcreditstaken` (
  `creditstakenID` INT(11) NOT NULL AUTO_INCREMENT,
  `studentID` INT(11) NOT NULL,
  `courseID` INT(11) NOT NULL,
  `semesterID` INT(11) NULL DEFAULT NULL,
  `isChangable` INT(1) NULL DEFAULT '0',
  PRIMARY KEY (`creditstakenID`),
  INDEX `credittaken_course_idx` (`courseID` ASC),
  INDEX `credittaken_profile_idx` (`studentID` ASC),
  INDEX `credittaken_semester_idx` (`semesterID` ASC),
  CONSTRAINT `credittaken_course`
    FOREIGN KEY (`courseID`)
    REFERENCES `collegespdb`.`tblcourse` (`courseID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `credittaken_profile`
    FOREIGN KEY (`studentID`)
    REFERENCES `collegespdb`.`tblprofile` (`studentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `credittaken_semester`
    FOREIGN KEY (`semesterID`)
    REFERENCES `collegespdb`.`tblsemester` (`semesterID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'The credit taken for a student';


-- -----------------------------------------------------
-- Table `collegespdb`.`tblmajor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegespdb`.`tblmajor` (
  `majorID` INT(11) NOT NULL AUTO_INCREMENT,
  `majorName` VARCHAR(45) NULL DEFAULT NULL,
  `majorDesc` MEDIUMTEXT NULL DEFAULT NULL,
  `catalogID` INT(11) NOT NULL,
  PRIMARY KEY (`majorID`),
  INDEX `major_catalog_idx` (`catalogID` ASC),
  CONSTRAINT `major_catalog`
    FOREIGN KEY (`catalogID`)
    REFERENCES `collegespdb`.`tblcatalog` (`catalogID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COMMENT = 'Major';


-- -----------------------------------------------------
-- Table `collegespdb`.`tblminor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegespdb`.`tblminor` (
  `minorID` INT(11) NOT NULL AUTO_INCREMENT,
  `minorName` VARCHAR(45) NULL DEFAULT NULL,
  `minorDesc` MEDIUMTEXT NULL DEFAULT NULL,
  `catalogID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`minorID`),
  INDEX `minor_catalog_idx` (`catalogID` ASC),
  CONSTRAINT `minor_catalog`
    FOREIGN KEY (`catalogID`)
    REFERENCES `collegespdb`.`tblcatalog` (`catalogID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COMMENT = 'Minor	';


-- -----------------------------------------------------
-- Table `collegespdb`.`tblgradreqcourse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegespdb`.`tblgradreqcourse` (
  `gradreqcourseID` INT(11) NOT NULL AUTO_INCREMENT,
  `majorID` INT(11) NULL DEFAULT NULL,
  `minorID` INT(11) NULL DEFAULT NULL,
  `courseName` VARCHAR(20) NULL DEFAULT NULL,
  `Desc` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`gradreqcourseID`),
  INDEX `gradreqcourse_major_idx` (`majorID` ASC),
  INDEX `gradreqcourse_minor_idx` (`minorID` ASC),
  CONSTRAINT `gradreqcourse_major`
    FOREIGN KEY (`majorID`)
    REFERENCES `collegespdb`.`tblmajor` (`majorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `gradreqcourse_minor`
    FOREIGN KEY (`minorID`)
    REFERENCES `collegespdb`.`tblminor` (`minorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 84
DEFAULT CHARACTER SET = utf8
COMMENT = 'The courses that is required for certain major, minor to graduate	';


-- -----------------------------------------------------
-- Table `collegespdb`.`tblgradrequirement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegespdb`.`tblgradrequirement` (
  `gradrequirementID` INT(11) NOT NULL AUTO_INCREMENT,
  `gradrequirementName` VARCHAR(45) NULL DEFAULT NULL,
  `gradrequirementDesc` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`gradrequirementID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Graduate Requirement courses';


-- -----------------------------------------------------
-- Table `collegespdb`.`tblplan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegespdb`.`tblplan` (
  `planID` INT(11) NOT NULL AUTO_INCREMENT,
  `catalogID` INT(11) NOT NULL,
  `majorID` INT(11) NOT NULL,
  `minorID` INT(11) NOT NULL,
  `majorID2` INT(11) NULL DEFAULT NULL,
  `minorID2` INT(11) NULL DEFAULT NULL,
  `profileID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`planID`),
  INDEX `plan_catalog_idx` (`catalogID` ASC),
  INDEX `plan_major_idx` (`majorID` ASC),
  INDEX `plan_minor_idx` (`minorID` ASC),
  INDEX `plan_major2_idx` (`majorID2` ASC),
  INDEX `plan_minor2_idx` (`minorID2` ASC),
  INDEX `plan_profile_idx` (`profileID` ASC),
  CONSTRAINT `plan_catalog`
    FOREIGN KEY (`catalogID`)
    REFERENCES `collegespdb`.`tblcatalog` (`catalogID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `plan_major`
    FOREIGN KEY (`majorID`)
    REFERENCES `collegespdb`.`tblmajor` (`majorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `plan_major2`
    FOREIGN KEY (`majorID2`)
    REFERENCES `collegespdb`.`tblmajor` (`majorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `plan_minor`
    FOREIGN KEY (`minorID`)
    REFERENCES `collegespdb`.`tblminor` (`minorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `plan_minor2`
    FOREIGN KEY (`minorID2`)
    REFERENCES `collegespdb`.`tblminor` (`minorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `plan_profile`
    FOREIGN KEY (`profileID`)
    REFERENCES `collegespdb`.`tblprofile` (`studentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Plan';


-- -----------------------------------------------------
-- Table `collegespdb`.`tblprereq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegespdb`.`tblprereq` (
  `prereqID` INT(11) NOT NULL AUTO_INCREMENT,
  `courseID` INT(11) NULL DEFAULT NULL,
  `prereqCourseID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`prereqID`),
  INDEX `prereq_course_idx` (`courseID` ASC),
  INDEX `prereq_prereq_idx` (`prereqCourseID` ASC),
  CONSTRAINT `prereq_course`
    FOREIGN KEY (`courseID`)
    REFERENCES `collegespdb`.`tblcourse` (`courseID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `prereq_prereq`
    FOREIGN KEY (`prereqCourseID`)
    REFERENCES `collegespdb`.`tblcourse` (`courseID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8
COMMENT = 'Prerequisite Courses		';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
