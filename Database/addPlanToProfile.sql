USE collegespdb;
-- ALTER TABLE collegespdb.tblcourse
-- ADD COLUMN semesterID INT(1) NULL DEFAULT 1 AFTER minorID;
SET FOREIGN_KEY_CHECKS=0;
insert into collegespdb.tblplan(`planName`,`catalogID`,`majorID`,`minorID`,`majorID2`,`minorID2`,`profileID`)
values ("Plan55",1,1,9999,9999,9999,1);
select last_insert_id() as planID