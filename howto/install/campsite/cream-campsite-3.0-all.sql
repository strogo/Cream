Alter table Subscriptions rename OldSubscriptions;
Alter table SubsSections rename OldSubsSections;

drop table if exists CMS_SECTION;
drop view if exists CMS_SECTION;
create view CMS_SECTION (CMS_PUBLICATION_ID, CMS_SECTION_ID, CMS_LANGUAGE_ID, CMS_SECTION_NAME) as Select Distinct IdPublication, Number, IdLanguage, CONCAT_WS( ' - ',(Select REPLACE(Name, '\'', '&#39;') from Publications where Id=IdPublication), REPLACE(Name, '\'', '&#39;'),(Select REPLACE(Name, '\'', '&#39;') from Languages where Id=IdLanguage)) from Sections order by 4;


drop view if exists Subscriptions;
create view Subscriptions (Id, IdUser, IdPublication, Active, ToPay, Currency, Type) as select DISTINCT ((1000000+ONLINE_SUBSCRIPTION.RECIPIENT_ID)*1000+PRODUCT_CMS_SECTION.CMS_PUBLICATION_ID), (Select Id from Users where UName=CUSTOMER.LOGIN_NAME), PRODUCT_CMS_SECTION.CMS_PUBLICATION_ID, 'Y', 0.00, '', 'P' from ONLINE_SUBSCRIPTION LEFT JOIN PRODUCT_CMS_SECTION on (PRODUCT_CMS_SECTION.PRODUCT_ID=ONLINE_SUBSCRIPTION.PRODUCT_ID) LEFT JOIN CUSTOMER ON (CUSTOMER.CUSTOMER_ID=ONLINE_SUBSCRIPTION.RECIPIENT_ID) where ONLINE_SUBSCRIPTION.STATUS=30 AND START_DATE<= CURDATE() AND END_DATE>CURDATE() AND CUSTOMER.ALLOW_LOGIN=20;

drop view if exists SubsSections;
create view SubsSections (IdSubscription, SectionNumber, IdLanguage, StartDate, Days, PaidDays, NoticeSent) as select DISTINCT ((1000000+ONLINE_SUBSCRIPTION.RECIPIENT_ID)*1000+PRODUCT_CMS_SECTION.CMS_PUBLICATION_ID), PRODUCT_CMS_SECTION.CMS_SECTION_ID, PRODUCT_CMS_SECTION.CMS_LANGUAGE_ID, CURDATE(), 33, 33, 'Y' from ONLINE_SUBSCRIPTION LEFT JOIN PRODUCT_CMS_SECTION on (PRODUCT_CMS_SECTION.PRODUCT_ID=ONLINE_SUBSCRIPTION.PRODUCT_ID) LEFT JOIN CUSTOMER ON (CUSTOMER.CUSTOMER_ID=ONLINE_SUBSCRIPTION.RECIPIENT_ID) where ONLINE_SUBSCRIPTION.STATUS=30 AND START_DATE<= CURDATE() AND END_DATE>CURDATE() AND CUSTOMER.ALLOW_LOGIN=20;



drop trigger CUSTOMER_ON_INSERT;
DELIMITER //
CREATE TRIGGER CUSTOMER_ON_INSERT after INSERT ON CUSTOMER
  FOR EACH ROW BEGIN
	IF NEW.ALLOW_LOGIN=20 THEN
		INSERT INTO Users SET Name = NEW.CUSTOMER_NAME_1, UName=NEW.LOGIN_NAME, Password=NEW.PASSWORD_VALUE, Reader='Y', EMail=NEW.EMAIL, time_created=NEW.CREATED;
	END IF;
  END;//
DELIMITER ;

drop trigger CUSTOMER_ON_UPDATE;
DELIMITER //
CREATE TRIGGER CUSTOMER_ON_UPDATE after UPDATE ON CUSTOMER
  FOR EACH ROW BEGIN
	IF NEW.ALLOW_LOGIN=20 && OLD.LOGIN_EXISTED=10 THEN
		INSERT INTO Users SET Name = NEW.CUSTOMER_NAME_1, UName=NEW.LOGIN_NAME, Password=NEW.PASSWORD_VALUE, Reader='Y', EMail=NEW.EMAIL, time_created=NEW.CREATED;
	ELSEIF NEW.ALLOW_LOGIN=20 THEN
		UPDATE Users SET Name = NEW.CUSTOMER_NAME_1, UName=NEW.LOGIN_NAME, Password=NEW.PASSWORD_VALUE, EMail=NEW.EMAIL where UName=OLD.LOGIN_NAME;
		
	END IF;
  END;//
DELIMITER ;

drop trigger CUSTOMER_ON_DELETE;
DELIMITER //
CREATE TRIGGER CUSTOMER_ON_DELETE after DELETE ON CUSTOMER
  FOR EACH ROW BEGIN
	Delete from Users where UName=OLD.LOGIN_NAME;
  END;//
DELIMITER ;

drop trigger SECTIONS_ON_DELETE;
DELIMITER //
CREATE TRIGGER SECTIONS_ON_DELETE after DELETE ON Sections
  FOR EACH ROW BEGIN
	SET @secs = 0;
	Select count(IdPublication) INTO @secs from Sections where IdPublication=OLD.IdPublication AND IdLanguage=OLD.IdLanguage AND Number=OLD.Number;
	IF @secs<1 THEN
		Delete from PRODUCT_CMS_SECTION where CMS_PUBLICATION_ID=OLD.IdPublication AND CMS_LANGUAGE_ID=OLD.IdLanguage AND CMS_SECTION_ID=OLD.Number;
	END IF;

  END;//
DELIMITER ;

DROP PROCEDURE cms_to_cream;
DELIMITER //
CREATE PROCEDURE cms_to_cream()

BEGIN
DECLARE tId int;
DECLARE tName varchar(255);
DECLARE tUName varchar(70);
DECLARE tPassword varchar(64);
DECLARE tEMail varchar(255);
DECLARE tCity varchar(100);
DECLARE tStrAddress varchar(255);
DECLARE tState varchar(32);
DECLARE tCountryCode varchar(21);
DECLARE tPhone varchar(20);
DECLARE tFax varchar(20);
DECLARE tContact varchar(64);
DECLARE tPhone2 varchar(20);
DECLARE tTitle varchar(4);
DECLARE tGender char(1);
DECLARE tAge varchar(5);
DECLARE tPostalCode varchar(70);
DECLARE tEmployer varchar(140);
DECLARE tEmployerType varchar(140);
DECLARE tPosition varchar(70);
DECLARE newGender int;
DECLARE newCountryId int;
DECLARE newOnlineSubsId int;
DECLARE newOnlineSubsCode varchar(20);
DECLARE newOnlineSubsStatus int;
DECLARE newProductId int;
DECLARE newCustomerId int;
DECLARE newCustomerCode varchar(20);
DECLARE newStartDate date;
DECLARE newEndDate date;

DECLARE INTCOUNT int;
DECLARE INTNUMROWS int;
DECLARE pos1 int;
DECLARE pos2 int;
DECLARE fName varchar(255);
DECLARE oName varchar(255);
DECLARE lName varchar(255);
DECLARE displayName varchar(255);
DECLARE dearName varchar(255);

DECLARE CmsUsersCUR CURSOR FOR SELECT Id, Name, UName, Password, Email, City, StrAddress, State, CountryCode, Phone, Fax, Contact, Phone2, Title, Gender, Age, PostalCode, Employer, EmployerType, Position FROM Users WHERE Reader='Y';
OPEN CmsUsersCUR;
SELECT COUNT(*) INTO INTNUMROWS FROM Users WHERE Reader='Y';
SET INTCOUNT = INTNUMROWS;

SET newCustomerId = 1001;
SELECT max(CUSTOMER_ID)+1 INTO newCustomerId FROM CUSTOMER;

SET newProductId = 1001;
SELECT max(PRODUCT_ID)+1 INTO newProductId FROM PRODUCT;

SET newOnlineSubsId = 1001;
SELECT max(ONLINE_SUBS_ID)+1 INTO newOnlineSubsId FROM ONLINE_SUBSCRIPTION;

INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_CODE, STATUS, PRIORITY, PRODUCT_TYPE, PRODUCT_CAT_ID, PRODUCT_DESCRIPTION, PRODUCT_DISPLAY, BASE_PRICE, UOM_ID, SHOW_ON_PRICELIST, VENDOR_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (newProductId, 'CAMPSUBS', 30, 30, 30, 1000, 'Campsite Subscription', 'Campsite Subscription', 0, 106, 20, 1000, now(), now(), 'system', 'system');


WHILE INTCOUNT > 0 DO
FETCH CmsUsersCUR INTO tId, tName, tUName, tPassword, tEmail, tCity, tStrAddress, tState, tCountryCode, tPhone, tFax, tContact, tPhone2, tTitle, tGender, tAge, tPostalCode, tEmployer, tEmployerType, tPosition;

CASE tGender
WHEN "M" THEN
SET newGender = 20;
WHEN "F" THEN
SET newGender = 30;
ELSE
SET newGender = 10;
END CASE;

IF newCustomerId>100000 THEN
 SET newCustomerCode='CU0';
ELSEIF newCustomerId>10000 THEN
 SET newCustomerCode='CU00';
ELSEIF newCustomerId>1000 THEN
 SET newCustomerCode='CU000';
ELSE
 SET newCustomerCode='CU';
END IF;
SET newCustomerCode = CONCAT(newCustomerCode, newCustomerId);

SET newCountryId=1000;
SELECT COUNTRY_ID INTO newCountryId FROM COUNTRY WHERE COUNTRY_CODE=tCountryCode;

SET tName= TRIM(tName);
SET pos1= INSTR(tName, ' ');

IF LENGTH(tName)>1 AND pos1>0 THEN
 SET fName= SUBSTRING(tName, 1, pos1-1);
 SET oName= SUBSTRING(tName, 1, pos1-1);
 SET lName= TRIM( SUBSTRING(tName, pos1+1));
 SET pos2= INSTR(lName, ' ');
 WHILE pos2>0 DO
  SET oName = CONCAT(oName, ' ', TRIM(SUBSTRING(lName, 1, pos2-1)));
  SET lName = TRIM(SUBSTRING(lName, pos2+1));
  SET pos2 = INSTR(lName, ' ');
 END WHILE;

 SET displayName= CONCAT(lName, ', ', oName);
 SET dearName=fName;
ELSE
 SET displayName=tName;
 SET dearName=tName;
END IF;




INSERT INTO CUSTOMER (CUSTOMER_ID,CUSTOMER_CODE, STATUS, PRIORITY, CUSTOMER_TYPE, CUSTOMER_CAT_ID, CUSTOMER_NAME_1, CUSTOMER_DISPLAY, DEAR, ADDRESS_1, CITY, ZIP, STATE, COUNTRY_ID, EMAIL_FORMAT, EMAIL, PHONE_1, PHONE_2, FAX, LANGUAGE_ID, GENDER, EDUCATION_CAT_ID, HOUSEHOLD_CAT_ID, REGION_ID, SEND_NEWS, LOGIN_NAME, PASSWORD_VALUE, ALLOW_LOGIN, LOGIN_EXISTED, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY) VALUES (newCustomerId, newCustomerCode, 30, 30, 10, 1000, tName, displayName, dearName, tStrAddress, tCity, tPostalCode, tState, newCountryId, 10, tEMail, tPhone, tPhone2, tFax, 1000, newGender, 1000, 1000, 1000, 20, tUName, tPassword, 20, 20, NOW(), NOW(), 'system', 'system');

SELECT min(StartDate) INTO newStartDate FROM SubsSections WHERE IdSubscription IN (SELECT Id FROM Subscriptions WHERE IdUser=tId);
SELECT max(DATE_ADD(StartDate, INTERVAL PaidDays DAY)) INTO newEndDate FROM SubsSections WHERE IdSubscription IN (SELECT Id FROM Subscriptions WHERE IdUser=tId);

IF newOnlineSubsId>100000 THEN
 SET newOnlineSubsCode='OS0';
ELSEIF newOnlineSubsId>10000 THEN
 SET newOnlineSubsCode='OS00';
ELSEIF newOnlineSubsId>1000 THEN
 SET newOnlineSubsCode='OS000';
ELSE
 SET newOnlineSubsCode='OS';
END IF;
SET newOnlineSubsCode = CONCAT(newOnlineSubsCode, newOnlineSubsId);

IF newEndDate IS NULL THEN
 SET newEndDate=now();
END IF;

IF newStartDate IS NULL THEN
 SET newStartDate=now();
END IF;


IF newEndDate>now() THEN
 SET newOnlineSubsStatus=30;
ELSE
 SET newOnlineSubsStatus=50;
END IF;

INSERT INTO ONLINE_SUBSCRIPTION (ONLINE_SUBS_ID, ONLINE_SUBS_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, PROJECT_ID, PRODUCT_ID, SORDER_ID, START_DATE, END_DATE, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY) VALUES (newOnlineSubsId, newOnlineSubsCode,' ---', newOnlineSubsStatus, 30, newStartDate, newCustomerId, newCustomerId, 1000, newProductId, 1000, newStartDate, newEndDate, now(), now(), 'system', 'system');


SET INTCOUNT = INTCOUNT - 1;
SET newCustomerId = newCustomerId + 1;
SET newOnlineSubsId = newOnlineSubsId + 1;

END WHILE;
CLOSE CmsUsersCUR;
END//
DELIMITER ;

CALL cms_to_cream();