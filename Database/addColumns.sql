USE `collegespdb`;
ALTER TABLE `collegespdb`.`tblplan` 
ADD COLUMN `planName` MEDIUMTEXT NULL AFTER `planID`;
ALTER TABLE `collegespdb`.`tblcreditstaken`
ADD COLUMN `isPass` INT(1) NULL DEFAULT 1 AFTER `isChangable`;
