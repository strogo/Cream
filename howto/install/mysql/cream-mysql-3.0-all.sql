
# -----------------------------------------------------------------------
# TURBINE_USER
# -----------------------------------------------------------------------
drop table if exists TURBINE_USER;

CREATE TABLE TURBINE_USER
(
		            USER_ID INTEGER NOT NULL AUTO_INCREMENT,
		            LOGIN_NAME VARCHAR (64) NOT NULL,
		            PASSWORD_VALUE VARCHAR (16) NOT NULL,
		            FIRST_NAME VARCHAR (64) NOT NULL,
		            LAST_NAME VARCHAR (64) NOT NULL,
		            EMAIL VARCHAR (64),
		            CONFIRM_VALUE VARCHAR (16),
		            MODIFIED TIMESTAMP (0),
		            CREATED TIMESTAMP (0),
		            LAST_LOGIN TIMESTAMP (0),
		            OBJECTDATA MEDIUMBLOB,
    PRIMARY KEY(USER_ID),
    UNIQUE (LOGIN_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# TURBINE_PERMISSION
# -----------------------------------------------------------------------
drop table if exists TURBINE_PERMISSION;

CREATE TABLE TURBINE_PERMISSION
(
		            PERMISSION_ID INTEGER NOT NULL AUTO_INCREMENT,
		            PERMISSION_NAME VARCHAR (64) NOT NULL,
    PRIMARY KEY(PERMISSION_ID),
    UNIQUE (PERMISSION_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# TURBINE_ROLE
# -----------------------------------------------------------------------
drop table if exists TURBINE_ROLE;

CREATE TABLE TURBINE_ROLE
(
		            ROLE_ID INTEGER NOT NULL AUTO_INCREMENT,
		            ROLE_NAME VARCHAR (64) NOT NULL,
    PRIMARY KEY(ROLE_ID),
    UNIQUE (ROLE_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# TURBINE_GROUP
# -----------------------------------------------------------------------
drop table if exists TURBINE_GROUP;

CREATE TABLE TURBINE_GROUP
(
		            GROUP_ID INTEGER NOT NULL AUTO_INCREMENT,
		            GROUP_NAME VARCHAR (64) NOT NULL,
    PRIMARY KEY(GROUP_ID),
    UNIQUE (GROUP_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# TURBINE_ROLE_PERMISSION
# -----------------------------------------------------------------------
drop table if exists TURBINE_ROLE_PERMISSION;

CREATE TABLE TURBINE_ROLE_PERMISSION
(
		            ROLE_ID INTEGER NOT NULL,
		            PERMISSION_ID INTEGER NOT NULL,
    PRIMARY KEY(ROLE_ID,PERMISSION_ID),
    FOREIGN KEY (ROLE_ID) REFERENCES TURBINE_ROLE (ROLE_ID)
    ,
    FOREIGN KEY (PERMISSION_ID) REFERENCES TURBINE_PERMISSION (PERMISSION_ID)
    
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# TURBINE_USER_GROUP_ROLE
# -----------------------------------------------------------------------
drop table if exists TURBINE_USER_GROUP_ROLE;

CREATE TABLE TURBINE_USER_GROUP_ROLE
(
		            USER_ID INTEGER NOT NULL,
		            GROUP_ID INTEGER NOT NULL,
		            ROLE_ID INTEGER NOT NULL,
    PRIMARY KEY(USER_ID,GROUP_ID,ROLE_ID),
    FOREIGN KEY (USER_ID) REFERENCES TURBINE_USER (USER_ID)
    ,
    FOREIGN KEY (GROUP_ID) REFERENCES TURBINE_GROUP (GROUP_ID)
    ,
    FOREIGN KEY (ROLE_ID) REFERENCES TURBINE_ROLE (ROLE_ID)
    
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# TURBINE_SCHEDULED_JOB
# -----------------------------------------------------------------------
drop table if exists TURBINE_SCHEDULED_JOB;

CREATE TABLE TURBINE_SCHEDULED_JOB
(
		            JOB_ID INTEGER NOT NULL AUTO_INCREMENT,
		            SECOND INTEGER default -1 NOT NULL,
		            MINUTE INTEGER default -1 NOT NULL,
		            HOUR INTEGER default -1 NOT NULL,
		            WEEK_DAY INTEGER default -1 NOT NULL,
		            DAY_OF_MONTH INTEGER default -1 NOT NULL,
		            TASK VARCHAR (99) NOT NULL,
		            EMAIL VARCHAR (99),
		            PROPERTY MEDIUMBLOB,
    PRIMARY KEY(JOB_ID)
) ENGINE=InnoDB;


# -----------------------------------------------------------------------
# CREAM_USER
# -----------------------------------------------------------------------
drop table if exists CREAM_USER;

CREATE TABLE CREAM_USER
(
		            USER_ID INTEGER NOT NULL,
		            LOGIN_NAME VARCHAR (32) NOT NULL,
		            WELCOME VARCHAR (32) NOT NULL,
		            DEFAULT_LIST INTEGER default 1000 NOT NULL,
		            INBOX_FID INTEGER default 1000 NOT NULL,
		            OUTBOX_FID INTEGER default 1000 NOT NULL,
		            NEWS_SUBS_FID INTEGER default 1000 NOT NULL,
		            NEWSLETTER_FID INTEGER default 1000 NOT NULL,
		            ONLINE_SUBS_FID INTEGER default 1000 NOT NULL,
		            PRINT_SUBS_FID INTEGER default 1000 NOT NULL,
		            SERVICE_FID INTEGER default 1000 NOT NULL,
		            SHIPMENT_FID INTEGER default 1000 NOT NULL,
		            PAYMENT_FID INTEGER default 1000 NOT NULL,
		            SORDER_FID INTEGER default 1000 NOT NULL,
		            PROJECT_FID INTEGER default 1000 NOT NULL,
		            CUSTOMER_FID INTEGER default 1000 NOT NULL,
		            PRODUCT_FID INTEGER default 1000 NOT NULL,
		            CONTACT_FID INTEGER default 1000 NOT NULL,
		            TASK_FID INTEGER default 1000 NOT NULL,
		            FILE_FID INTEGER default 1000 NOT NULL,
		            LEAD_FID INTEGER default 1000 NOT NULL,
		            OPPORTUNITY_FID INTEGER default 1000 NOT NULL,
    PRIMARY KEY(USER_ID),
    UNIQUE (LOGIN_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# PROJECT_CATEGORY
# -----------------------------------------------------------------------
drop table if exists PROJECT_CATEGORY;

CREATE TABLE PROJECT_CATEGORY
(
		            PROJECT_CAT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            PROJECT_CAT_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(PROJECT_CAT_ID),
    UNIQUE (PROJECT_CAT_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# PRODUCT_CATEGORY
# -----------------------------------------------------------------------
drop table if exists PRODUCT_CATEGORY;

CREATE TABLE PRODUCT_CATEGORY
(
		            PRODUCT_CAT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            PRODUCT_CAT_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(PRODUCT_CAT_ID),
    UNIQUE (PRODUCT_CAT_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# CUSTOMER_CATEGORY
# -----------------------------------------------------------------------
drop table if exists CUSTOMER_CATEGORY;

CREATE TABLE CUSTOMER_CATEGORY
(
		            CUSTOMER_CAT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            CUSTOMER_CAT_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(CUSTOMER_CAT_ID),
    UNIQUE (CUSTOMER_CAT_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# CONTACT_CATEGORY
# -----------------------------------------------------------------------
drop table if exists CONTACT_CATEGORY;

CREATE TABLE CONTACT_CATEGORY
(
		            CONTACT_CAT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            CONTACT_CAT_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(CONTACT_CAT_ID),
    UNIQUE (CONTACT_CAT_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# INDUSTRY
# -----------------------------------------------------------------------
drop table if exists INDUSTRY;

CREATE TABLE INDUSTRY
(
		            INDUSTRY_ID INTEGER NOT NULL AUTO_INCREMENT,
		            INDUSTRY_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(INDUSTRY_ID),
    UNIQUE (INDUSTRY_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# COUNTRY
# -----------------------------------------------------------------------
drop table if exists COUNTRY;

CREATE TABLE COUNTRY
(
		            COUNTRY_ID INTEGER NOT NULL AUTO_INCREMENT,
		            COUNTRY_NAME VARCHAR (50) NOT NULL,
		            COUNTRY_CODE CHAR (2) NOT NULL,
    PRIMARY KEY(COUNTRY_ID),
    UNIQUE (COUNTRY_NAME),
    UNIQUE (COUNTRY_CODE)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# REGION
# -----------------------------------------------------------------------
drop table if exists REGION;

CREATE TABLE REGION
(
		            REGION_ID INTEGER NOT NULL AUTO_INCREMENT,
		            REGION_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(REGION_ID),
    UNIQUE (REGION_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# LANGUAGE
# -----------------------------------------------------------------------
drop table if exists LANGUAGE;

CREATE TABLE LANGUAGE
(
		            LANGUAGE_ID INTEGER NOT NULL AUTO_INCREMENT,
		            LANGUAGE_NAME VARCHAR (50) NOT NULL,
		            LANGUAGE_CODE CHAR (2) NOT NULL,
    PRIMARY KEY(LANGUAGE_ID),
    UNIQUE (LANGUAGE_NAME),
    UNIQUE (LANGUAGE_CODE)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# NOTIFICATION
# -----------------------------------------------------------------------
drop table if exists NOTIFICATION;

CREATE TABLE NOTIFICATION
(
		            NOTIFICATION_ID INTEGER NOT NULL AUTO_INCREMENT,
		            NOTIFICATION_TYPE INTEGER default 10 NOT NULL,
		            LANGUAGE_ID INTEGER default 1000 NOT NULL,
		            EMAIL_FORMAT INTEGER default 10 NOT NULL,
		            SUBJECT VARCHAR (254) NOT NULL,
		            BODY LONGTEXT,
    PRIMARY KEY(NOTIFICATION_ID),
    FOREIGN KEY (LANGUAGE_ID) REFERENCES LANGUAGE (LANGUAGE_ID)
    ,
    UNIQUE (NOTIFICATION_TYPE, LANGUAGE_ID, EMAIL_FORMAT)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# CURRENCY
# -----------------------------------------------------------------------
drop table if exists CURRENCY;

CREATE TABLE CURRENCY
(
		            CURRENCY_ID INTEGER NOT NULL AUTO_INCREMENT,
		            CURRENCY_NAME VARCHAR (50) NOT NULL,
		            CURRENCY_CODE CHAR (3) NOT NULL,
		            CURRENCY_RATE DECIMAL (15,6) default 1 NOT NULL,
    PRIMARY KEY(CURRENCY_ID),
    UNIQUE (CURRENCY_NAME),
    UNIQUE (CURRENCY_CODE)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# CARRIER
# -----------------------------------------------------------------------
drop table if exists CARRIER;

CREATE TABLE CARRIER
(
		            CARRIER_ID INTEGER NOT NULL AUTO_INCREMENT,
		            CARRIER_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(CARRIER_ID),
    UNIQUE (CARRIER_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# VENDOR
# -----------------------------------------------------------------------
drop table if exists VENDOR;

CREATE TABLE VENDOR
(
		            VENDOR_ID INTEGER NOT NULL AUTO_INCREMENT,
		            VENDOR_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(VENDOR_ID),
    UNIQUE (VENDOR_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# UOM
# -----------------------------------------------------------------------
drop table if exists UOM;

CREATE TABLE UOM
(
		            UOM_ID INTEGER NOT NULL AUTO_INCREMENT,
		            UOM_NAME VARCHAR (50) NOT NULL,
		            UOM_CODE CHAR (3) NOT NULL,
    PRIMARY KEY(UOM_ID),
    UNIQUE (UOM_NAME),
    UNIQUE (UOM_CODE)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# EDUCATION_CATEGORY
# -----------------------------------------------------------------------
drop table if exists EDUCATION_CATEGORY;

CREATE TABLE EDUCATION_CATEGORY
(
		            EDUCATION_CAT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            EDUCATION_CAT_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(EDUCATION_CAT_ID),
    UNIQUE (EDUCATION_CAT_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# HOUSEHOLD_CATEGORY
# -----------------------------------------------------------------------
drop table if exists HOUSEHOLD_CATEGORY;

CREATE TABLE HOUSEHOLD_CATEGORY
(
		            HOUSEHOLD_CAT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            HOUSEHOLD_CAT_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(HOUSEHOLD_CAT_ID),
    UNIQUE (HOUSEHOLD_CAT_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# AGE_CATEGORY
# -----------------------------------------------------------------------
drop table if exists AGE_CATEGORY;

CREATE TABLE AGE_CATEGORY
(
		            AGE_CAT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            AGE_CAT_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(AGE_CAT_ID),
    UNIQUE (AGE_CAT_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# REVENUE_CATEGORY
# -----------------------------------------------------------------------
drop table if exists REVENUE_CATEGORY;

CREATE TABLE REVENUE_CATEGORY
(
		            REVENUE_CAT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            REVENUE_CAT_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(REVENUE_CAT_ID),
    UNIQUE (REVENUE_CAT_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# EMPLOYEE_NO_CATEGORY
# -----------------------------------------------------------------------
drop table if exists EMPLOYEE_NO_CATEGORY;

CREATE TABLE EMPLOYEE_NO_CATEGORY
(
		            EMPLOYEE_NO_CAT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            EMPLOYEE_NO_CAT_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(EMPLOYEE_NO_CAT_ID),
    UNIQUE (EMPLOYEE_NO_CAT_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# LEAD_SOURCE
# -----------------------------------------------------------------------
drop table if exists LEAD_SOURCE;

CREATE TABLE LEAD_SOURCE
(
		            LEAD_SOURCE_ID INTEGER NOT NULL AUTO_INCREMENT,
		            LEAD_SOURCE_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(LEAD_SOURCE_ID),
    UNIQUE (LEAD_SOURCE_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# JOB_POSITION
# -----------------------------------------------------------------------
drop table if exists JOB_POSITION;

CREATE TABLE JOB_POSITION
(
		            JOB_POSITION_ID INTEGER NOT NULL AUTO_INCREMENT,
		            JOB_POSITION_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(JOB_POSITION_ID),
    UNIQUE (JOB_POSITION_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# SALUTATION
# -----------------------------------------------------------------------
drop table if exists SALUTATION;

CREATE TABLE SALUTATION
(
		            SALUTATION_ID INTEGER NOT NULL AUTO_INCREMENT,
		            SALUTATION_NAME VARCHAR (15) NOT NULL,
    PRIMARY KEY(SALUTATION_ID),
    UNIQUE (SALUTATION_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# OPPORTUNITY_CATEGORY
# -----------------------------------------------------------------------
drop table if exists OPPORTUNITY_CATEGORY;

CREATE TABLE OPPORTUNITY_CATEGORY
(
		            OPPORTUNITY_CAT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            OPPORTUNITY_CAT_NAME VARCHAR (50) NOT NULL,
    PRIMARY KEY(OPPORTUNITY_CAT_ID),
    UNIQUE (OPPORTUNITY_CAT_NAME)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# CUSTOMER
# -----------------------------------------------------------------------
drop table if exists CUSTOMER;

CREATE TABLE CUSTOMER
(
		            CUSTOMER_ID INTEGER NOT NULL AUTO_INCREMENT,
		            CUSTOMER_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            CUSTOMER_TYPE INTEGER default 10 NOT NULL,
		            CUSTOMER_CAT_ID INTEGER default 1000 NOT NULL,
		            LEAD_SOURCE_ID INTEGER default 1000 NOT NULL,
		            CUSTOMER_NAME_1 VARCHAR (70) NOT NULL,
		            CUSTOMER_NAME_2 VARCHAR (70),
		            CUSTOMER_DISPLAY VARCHAR (128) NOT NULL,
		            DEAR VARCHAR (128),
		            INDUSTRY_ID INTEGER default 1000 NOT NULL,
		            ADDRESS_1 VARCHAR (55),
		            ADDRESS_2 VARCHAR (55),
		            ADDRESS_3 VARCHAR (55),
		            CITY VARCHAR (35),
		            ZIP VARCHAR (12),
		            STATE VARCHAR (35),
		            COUNTRY_ID INTEGER default 1000 NOT NULL,
		            REGION_ID INTEGER default 1000 NOT NULL,
		            SHIPTO_ADDRESS_1 VARCHAR (55),
		            SHIPTO_ADDRESS_2 VARCHAR (55),
		            SHIPTO_ADDRESS_3 VARCHAR (55),
		            SHIPTO_CITY VARCHAR (35),
		            SHIPTO_ZIP VARCHAR (12),
		            SHIPTO_STATE VARCHAR (35),
		            SHIPTO_COUNTRY_ID INTEGER default 1000 NOT NULL,
		            SHIPTO_REGION_ID INTEGER default 1000 NOT NULL,
		            PHONE_1 VARCHAR (30),
		            PHONE_2 VARCHAR (30),
		            FAX VARCHAR (30),
		            EMAIL VARCHAR (254),
		            EMAIL_FORMAT INTEGER default 10 NOT NULL,
		            SEND_NEWS INTEGER default 20 NOT NULL,
		            WEB_URL VARCHAR (254),
		            LANGUAGE_ID INTEGER default 1000 NOT NULL,
		            GENDER INTEGER default 10 NOT NULL,
		            AGE_CAT_ID INTEGER default 1000 NOT NULL,
		            EDUCATION_CAT_ID INTEGER default 1000 NOT NULL,
		            HOUSEHOLD_CAT_ID INTEGER default 1000 NOT NULL,
		            TAX_ID_NO VARCHAR (30),
		            OWNERSHIP VARCHAR (70),
		            REVENUE_CAT_ID INTEGER default 1000 NOT NULL,
		            EMPLOYEE_NO_CAT_ID INTEGER default 1000 NOT NULL,
		            CUSTOM_1 VARCHAR (55),
		            CUSTOM_2 VARCHAR (55),
		            CUSTOM_3 VARCHAR (55),
		            CUSTOM_4 VARCHAR (55),
		            CUSTOM_5 VARCHAR (55),
		            CUSTOM_6 VARCHAR (55),
		            ALLOW_LOGIN INTEGER default 10 NOT NULL,
		            ALLOW_PROFILE_EDIT INTEGER default 10 NOT NULL,
		            LOGIN_EXISTED INTEGER default 10 NOT NULL,
		            LOGIN_NAME VARCHAR (70) NOT NULL,
		            PASSWORD_VALUE VARCHAR (64) NOT NULL,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(CUSTOMER_ID),
    FOREIGN KEY (CUSTOMER_CAT_ID) REFERENCES CUSTOMER_CATEGORY (CUSTOMER_CAT_ID)
    ,
    FOREIGN KEY (INDUSTRY_ID) REFERENCES INDUSTRY (INDUSTRY_ID)
    ,
    FOREIGN KEY (LEAD_SOURCE_ID) REFERENCES LEAD_SOURCE (LEAD_SOURCE_ID)
    ,
    FOREIGN KEY (COUNTRY_ID) REFERENCES COUNTRY (COUNTRY_ID)
    ,
    FOREIGN KEY (REGION_ID) REFERENCES REGION (REGION_ID)
    ,
    FOREIGN KEY (SHIPTO_COUNTRY_ID) REFERENCES COUNTRY (COUNTRY_ID)
    ,
    FOREIGN KEY (SHIPTO_REGION_ID) REFERENCES REGION (REGION_ID)
    ,
    FOREIGN KEY (LANGUAGE_ID) REFERENCES LANGUAGE (LANGUAGE_ID)
    ,
    FOREIGN KEY (EDUCATION_CAT_ID) REFERENCES EDUCATION_CATEGORY (EDUCATION_CAT_ID)
    ,
    FOREIGN KEY (HOUSEHOLD_CAT_ID) REFERENCES HOUSEHOLD_CATEGORY (HOUSEHOLD_CAT_ID)
    ,
    FOREIGN KEY (AGE_CAT_ID) REFERENCES AGE_CATEGORY (AGE_CAT_ID)
    ,
    FOREIGN KEY (REVENUE_CAT_ID) REFERENCES REVENUE_CATEGORY (REVENUE_CAT_ID)
    ,
    FOREIGN KEY (EMPLOYEE_NO_CAT_ID) REFERENCES EMPLOYEE_NO_CATEGORY (EMPLOYEE_NO_CAT_ID)
    ,
    UNIQUE (CUSTOMER_CODE),
    UNIQUE (CUSTOMER_DISPLAY),
    UNIQUE (LOGIN_NAME),
    INDEX CUSTOMER_I_1 (CUSTOMER_TYPE),
    INDEX CUSTOMER_I_2 (STATUS),
    INDEX CUSTOMER_I_3 (COUNTRY_ID),
    INDEX CUSTOMER_I_4 (REGION_ID),
    INDEX CUSTOMER_I_5 (LANGUAGE_ID),
    INDEX CUSTOMER_I_6 (CUSTOMER_CAT_ID),
    INDEX CUSTOMER_I_7 (INDUSTRY_ID),
    INDEX CUSTOMER_I_8 (LEAD_SOURCE_ID),
    INDEX CUSTOMER_I_9 (EMAIL),
    INDEX CUSTOMER_I_10 (CITY),
    INDEX CUSTOMER_I_11 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# CONTACT
# -----------------------------------------------------------------------
drop table if exists CONTACT;

CREATE TABLE CONTACT
(
		            CONTACT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            CONTACT_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            CONTACT_CAT_ID INTEGER default 1000 NOT NULL,
		            SALUTATION_ID INTEGER default 1000 NOT NULL,
		            FIRST_NAME VARCHAR (35),
		            LAST_NAME VARCHAR (35) NOT NULL,
		            SUFFIX VARCHAR (15),
		            CONTACT_DISPLAY VARCHAR (128) NOT NULL,
		            DEAR VARCHAR (128),
		            ADDRESS_1 VARCHAR (55),
		            ADDRESS_2 VARCHAR (55),
		            ADDRESS_3 VARCHAR (55),
		            CITY VARCHAR (35),
		            ZIP VARCHAR (12),
		            STATE VARCHAR (35),
		            COUNTRY_ID INTEGER default 1000 NOT NULL,
		            REGION_ID INTEGER default 1000 NOT NULL,
		            SEC_ADDRESS_1 VARCHAR (55),
		            SEC_ADDRESS_2 VARCHAR (55),
		            SEC_ADDRESS_3 VARCHAR (55),
		            SEC_CITY VARCHAR (35),
		            SEC_ZIP VARCHAR (12),
		            SEC_STATE VARCHAR (35),
		            SEC_COUNTRY_ID INTEGER default 1000 NOT NULL,
		            SEC_REGION_ID INTEGER default 1000 NOT NULL,
		            PHONE_HOME VARCHAR (30),
		            PHONE_WORK VARCHAR (30),
		            PHONE_MOBILE VARCHAR (30),
		            FAX VARCHAR (30),
		            EMAIL VARCHAR (254),
		            EMAIL_2 VARCHAR (254),
		            EMAIL_FORMAT INTEGER default 10 NOT NULL,
		            SEND_NEWS INTEGER default 20 NOT NULL,
		            WEB_URL VARCHAR (254),
		            LANGUAGE_ID INTEGER default 1000 NOT NULL,
		            GENDER INTEGER default 10 NOT NULL,
		            BIRTHDATE DATE,
		            ANNIVERSARY DATE,
		            JOB_TITLE VARCHAR (70),
		            JOB_POSITION_ID INTEGER default 1000 NOT NULL,
		            DEPARTMENT VARCHAR (70),
		            ASSISTANT_NAME VARCHAR (70),
		            ASSISTANT_PHONE VARCHAR (30),
		            ASSISTANT_EMAIL VARCHAR (254),
		            CUSTOM_1 VARCHAR (55),
		            CUSTOM_2 VARCHAR (55),
		            CUSTOM_3 VARCHAR (55),
		            CUSTOM_4 VARCHAR (55),
		            CUSTOM_5 VARCHAR (55),
		            CUSTOM_6 VARCHAR (55),
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(CONTACT_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (SALUTATION_ID) REFERENCES SALUTATION (SALUTATION_ID)
    ,
    FOREIGN KEY (CONTACT_CAT_ID) REFERENCES CONTACT_CATEGORY (CONTACT_CAT_ID)
    ,
    FOREIGN KEY (COUNTRY_ID) REFERENCES COUNTRY (COUNTRY_ID)
    ,
    FOREIGN KEY (REGION_ID) REFERENCES REGION (REGION_ID)
    ,
    FOREIGN KEY (SEC_COUNTRY_ID) REFERENCES COUNTRY (COUNTRY_ID)
    ,
    FOREIGN KEY (SEC_REGION_ID) REFERENCES REGION (REGION_ID)
    ,
    FOREIGN KEY (LANGUAGE_ID) REFERENCES LANGUAGE (LANGUAGE_ID)
    ,
    FOREIGN KEY (JOB_POSITION_ID) REFERENCES JOB_POSITION (JOB_POSITION_ID)
    ,
    UNIQUE (CONTACT_CODE),
    UNIQUE (CONTACT_DISPLAY),
    INDEX CONTACT_I_1 (CUSTOMER_ID),
    INDEX CONTACT_I_2 (STATUS),
    INDEX CONTACT_I_3 (COUNTRY_ID),
    INDEX CONTACT_I_4 (REGION_ID),
    INDEX CONTACT_I_5 (LANGUAGE_ID),
    INDEX CONTACT_I_6 (CONTACT_CAT_ID),
    INDEX CONTACT_I_7 (EMAIL),
    INDEX CONTACT_I_8 (CITY),
    INDEX CONTACT_I_9 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# PRODUCT
# -----------------------------------------------------------------------
drop table if exists PRODUCT;

CREATE TABLE PRODUCT
(
		            PRODUCT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            PRODUCT_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            PRODUCT_TYPE INTEGER default 10 NOT NULL,
		            PRODUCT_CAT_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_DESCRIPTION VARCHAR (254) NOT NULL,
		            PRODUCT_DISPLAY VARCHAR (70) NOT NULL,
		            BASE_PRICE FLOAT default 0 NOT NULL,
		            UOM_ID INTEGER default 1000 NOT NULL,
		            WEB_URL VARCHAR (254),
		            SHOW_ON_PRICELIST INTEGER default 20 NOT NULL,
		            VENDOR_ID INTEGER default 1000 NOT NULL,
		            VENDORS_CODE VARCHAR (20),
		            EAN_UPC_CODE VARCHAR (20),
		            LOCATION VARCHAR (55),
		            CUSTOM_1 VARCHAR (128),
		            CUSTOM_2 VARCHAR (128),
		            CUSTOM_3 VARCHAR (128),
		            CUSTOM_4 VARCHAR (128),
		            CUSTOM_5 VARCHAR (128),
		            CUSTOM_6 VARCHAR (128),
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(PRODUCT_ID),
    FOREIGN KEY (PRODUCT_CAT_ID) REFERENCES PRODUCT_CATEGORY (PRODUCT_CAT_ID)
    ,
    FOREIGN KEY (UOM_ID) REFERENCES UOM (UOM_ID)
    ,
    FOREIGN KEY (VENDOR_ID) REFERENCES VENDOR (VENDOR_ID)
    ,
    UNIQUE (PRODUCT_CODE),
    UNIQUE (PRODUCT_DISPLAY),
    INDEX PRODUCT_I_1 (PRODUCT_CAT_ID),
    INDEX PRODUCT_I_2 (UOM_ID),
    INDEX PRODUCT_I_3 (PRODUCT_TYPE),
    INDEX PRODUCT_I_4 (STATUS),
    INDEX PRODUCT_I_5 (VENDOR_ID),
    INDEX PRODUCT_I_6 (BASE_PRICE),
    INDEX PRODUCT_I_7 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# PROJECT
# -----------------------------------------------------------------------
drop table if exists PROJECT;

CREATE TABLE PROJECT
(
		            PROJECT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            PROJECT_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            PROJECT_CAT_ID INTEGER default 1000 NOT NULL,
		            PROJECT_NAME VARCHAR (70) NOT NULL,
		            START_DATE DATE NOT NULL,
		            END_DATE DATE,
		            EXPENSES DECIMAL (15,2) default 0 NOT NULL,
		            REVENUES DECIMAL (15,2) default 0 NOT NULL,
		            CUSTOM_1 VARCHAR (128),
		            CUSTOM_2 VARCHAR (128),
		            CUSTOM_3 VARCHAR (128),
		            CUSTOM_4 VARCHAR (128),
		            CUSTOM_5 VARCHAR (128),
		            CUSTOM_6 VARCHAR (128),
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(PROJECT_ID),
    FOREIGN KEY (PROJECT_CAT_ID) REFERENCES PROJECT_CATEGORY (PROJECT_CAT_ID)
    ,
    UNIQUE (PROJECT_CODE),
    UNIQUE (PROJECT_NAME),
    INDEX PROJECT_I_1 (PROJECT_CAT_ID),
    INDEX PROJECT_I_2 (STATUS),
    INDEX PROJECT_I_3 (START_DATE),
    INDEX PROJECT_I_4 (EXPENSES),
    INDEX PROJECT_I_5 (REVENUES),
    INDEX PROJECT_I_6 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# OPPORTUNITY
# -----------------------------------------------------------------------
drop table if exists OPPORTUNITY;

CREATE TABLE OPPORTUNITY
(
		            OPPORTUNITY_ID INTEGER NOT NULL AUTO_INCREMENT,
		            OPPORTUNITY_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            OPPORTUNITY_TYPE INTEGER default 10 NOT NULL,
		            OPPORTUNITY_NAME VARCHAR (70) NOT NULL,
		            OPPORTUNITY_CAT_ID INTEGER default 1000 NOT NULL,
		            LEAD_SOURCE_ID INTEGER default 1000 NOT NULL,
		            ISSUED_DATE DATE NOT NULL,
		            EXPECTED_DATE DATE NOT NULL,
		            CLOSED_DATE DATE,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            CURRENCY_ID INTEGER default 1000 NOT NULL,
		            CURRENCY_AMOUNT DECIMAL (15,2) default 0 NOT NULL,
		            SALES_STAGE INTEGER default 10 NOT NULL,
		            PROBABILITY INTEGER default 0 NOT NULL,
		            SUBJECT VARCHAR (254) default '---' NOT NULL,
		            NEXT_STEPS LONGTEXT,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(OPPORTUNITY_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (CURRENCY_ID) REFERENCES CURRENCY (CURRENCY_ID)
    ,
    FOREIGN KEY (LEAD_SOURCE_ID) REFERENCES LEAD_SOURCE (LEAD_SOURCE_ID)
    ,
    FOREIGN KEY (OPPORTUNITY_CAT_ID) REFERENCES OPPORTUNITY_CATEGORY (OPPORTUNITY_CAT_ID)
    ,
    UNIQUE (OPPORTUNITY_CODE),
    INDEX OPPORTUNITY_I_1 (CUSTOMER_ID),
    INDEX OPPORTUNITY_I_2 (PROJECT_ID),
    INDEX OPPORTUNITY_I_3 (CURRENCY_ID),
    INDEX OPPORTUNITY_I_4 (ISSUED_DATE),
    INDEX OPPORTUNITY_I_5 (EXPECTED_DATE),
    INDEX OPPORTUNITY_I_6 (CLOSED_DATE),
    INDEX OPPORTUNITY_I_7 (STATUS),
    INDEX OPPORTUNITY_I_8 (CURRENCY_AMOUNT),
    INDEX OPPORTUNITY_I_9 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# SORDER
# -----------------------------------------------------------------------
drop table if exists SORDER;

CREATE TABLE SORDER
(
		            SORDER_ID INTEGER NOT NULL AUTO_INCREMENT,
		            SORDER_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            ISSUED_DATE DATE NOT NULL,
		            CLOSED_DATE DATE,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            OPPORTUNITY_ID INTEGER default 1000 NOT NULL,
		            RECIPIENT_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            CARRIER_ID INTEGER default 1000 NOT NULL,
		            PAY_TERM INTEGER default 10 NOT NULL,
		            PAY_METHOD INTEGER default 10 NOT NULL,
		            CURRENCY_ID INTEGER default 1000 NOT NULL,
		            CURRENCY_AMOUNT DECIMAL (15,2) default 0 NOT NULL,
		            SUBJECT VARCHAR (254) default '---' NOT NULL,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(SORDER_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (RECIPIENT_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (OPPORTUNITY_ID) REFERENCES OPPORTUNITY (OPPORTUNITY_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (CARRIER_ID) REFERENCES CARRIER (CARRIER_ID)
    ,
    FOREIGN KEY (CURRENCY_ID) REFERENCES CURRENCY (CURRENCY_ID)
    ,
    UNIQUE (SORDER_CODE),
    INDEX SORDER_I_1 (CUSTOMER_ID),
    INDEX SORDER_I_2 (OPPORTUNITY_ID),
    INDEX SORDER_I_3 (PROJECT_ID),
    INDEX SORDER_I_4 (CURRENCY_ID),
    INDEX SORDER_I_5 (ISSUED_DATE),
    INDEX SORDER_I_6 (CLOSED_DATE),
    INDEX SORDER_I_7 (STATUS),
    INDEX SORDER_I_8 (CURRENCY_AMOUNT),
    INDEX SORDER_I_9 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# PAYMENT
# -----------------------------------------------------------------------
drop table if exists PAYMENT;

CREATE TABLE PAYMENT
(
		            PAYMENT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            PAYMENT_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            ISSUED_DATE DATE NOT NULL,
		            CLOSED_DATE DATE,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            SORDER_ID INTEGER default 1000 NOT NULL,
		            INVOICE_CODE VARCHAR (20),
		            PAY_TERM INTEGER default 10 NOT NULL,
		            PAY_METHOD INTEGER default 10 NOT NULL,
		            CURRENCY_ID INTEGER default 1000 NOT NULL,
		            CURRENCY_RATE DECIMAL (15,6) default 1 NOT NULL,
		            CURRENCY_AMOUNT DECIMAL (15,2) default 0 NOT NULL,
		            TOTAL_AMOUNT DECIMAL (15,2) default 0 NOT NULL,
		            SUBJECT VARCHAR (254) default '---' NOT NULL,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(PAYMENT_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (SORDER_ID) REFERENCES SORDER (SORDER_ID)
    ,
    FOREIGN KEY (CURRENCY_ID) REFERENCES CURRENCY (CURRENCY_ID)
    ,
    UNIQUE (PAYMENT_CODE),
    INDEX PAYMENT_I_1 (CUSTOMER_ID),
    INDEX PAYMENT_I_2 (SORDER_ID),
    INDEX PAYMENT_I_3 (PROJECT_ID),
    INDEX PAYMENT_I_4 (CURRENCY_ID),
    INDEX PAYMENT_I_5 (ISSUED_DATE),
    INDEX PAYMENT_I_6 (CLOSED_DATE),
    INDEX PAYMENT_I_7 (STATUS),
    INDEX PAYMENT_I_8 (CURRENCY_AMOUNT),
    INDEX PAYMENT_I_9 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# SERVICE
# -----------------------------------------------------------------------
drop table if exists SERVICE;

CREATE TABLE SERVICE
(
		            SERVICE_ID INTEGER NOT NULL AUTO_INCREMENT,
		            SERVICE_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            ISSUED_DATE DATE NOT NULL,
		            CLOSED_DATE DATE,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            RECIPIENT_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            SORDER_ID INTEGER default 1000 NOT NULL,
		            INVOICE_CODE VARCHAR (20),
		            SUBJECT VARCHAR (254) default '---' NOT NULL,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(SERVICE_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (RECIPIENT_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (SORDER_ID) REFERENCES SORDER (SORDER_ID)
    ,
    UNIQUE (SERVICE_CODE),
    INDEX SERVICE_I_1 (CUSTOMER_ID),
    INDEX SERVICE_I_2 (SORDER_ID),
    INDEX SERVICE_I_3 (PROJECT_ID),
    INDEX SERVICE_I_4 (ISSUED_DATE),
    INDEX SERVICE_I_5 (CLOSED_DATE),
    INDEX SERVICE_I_6 (STATUS),
    INDEX SERVICE_I_7 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# SHIPMENT
# -----------------------------------------------------------------------
drop table if exists SHIPMENT;

CREATE TABLE SHIPMENT
(
		            SHIPMENT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            SHIPMENT_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            ISSUED_DATE DATE NOT NULL,
		            CLOSED_DATE DATE,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            RECIPIENT_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            SORDER_ID INTEGER default 1000 NOT NULL,
		            INVOICE_CODE VARCHAR (20),
		            CARRIER_ID INTEGER default 1000 NOT NULL,
		            SUBJECT VARCHAR (254) default '---' NOT NULL,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(SHIPMENT_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (RECIPIENT_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (SORDER_ID) REFERENCES SORDER (SORDER_ID)
    ,
    FOREIGN KEY (CARRIER_ID) REFERENCES CARRIER (CARRIER_ID)
    ,
    UNIQUE (SHIPMENT_CODE),
    INDEX SHIPMENT_I_1 (CUSTOMER_ID),
    INDEX SHIPMENT_I_2 (SORDER_ID),
    INDEX SHIPMENT_I_3 (PROJECT_ID),
    INDEX SHIPMENT_I_4 (CARRIER_ID),
    INDEX SHIPMENT_I_5 (ISSUED_DATE),
    INDEX SHIPMENT_I_6 (CLOSED_DATE),
    INDEX SHIPMENT_I_7 (STATUS),
    INDEX SHIPMENT_I_8 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# PRINT_SUBSCRIPTION
# -----------------------------------------------------------------------
drop table if exists PRINT_SUBSCRIPTION;

CREATE TABLE PRINT_SUBSCRIPTION
(
		            PRINT_SUBS_ID INTEGER NOT NULL AUTO_INCREMENT,
		            PRINT_SUBS_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            ISSUED_DATE DATE NOT NULL,
		            CLOSED_DATE DATE,
		            SORDER_ID INTEGER default 1000 NOT NULL,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            RECIPIENT_ID INTEGER default 1000 NOT NULL,
		            CARRIER_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            QUANTITY INTEGER default 1 NOT NULL,
		            START_DATE DATE NOT NULL,
		            END_DATE DATE NOT NULL,
		            SUBJECT VARCHAR (254) default '---' NOT NULL,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(PRINT_SUBS_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (RECIPIENT_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (CARRIER_ID) REFERENCES CARRIER (CARRIER_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    FOREIGN KEY (SORDER_ID) REFERENCES SORDER (SORDER_ID)
    ,
    UNIQUE (PRINT_SUBS_CODE),
    INDEX PRINT_SUBSCRIPTION_I_1 (CUSTOMER_ID),
    INDEX PRINT_SUBSCRIPTION_I_2 (SORDER_ID),
    INDEX PRINT_SUBSCRIPTION_I_3 (PROJECT_ID),
    INDEX PRINT_SUBSCRIPTION_I_4 (PRODUCT_ID),
    INDEX PRINT_SUBSCRIPTION_I_5 (ISSUED_DATE),
    INDEX PRINT_SUBSCRIPTION_I_6 (CLOSED_DATE),
    INDEX PRINT_SUBSCRIPTION_I_7 (STATUS),
    INDEX PRINT_SUBSCRIPTION_I_8 (START_DATE),
    INDEX PRINT_SUBSCRIPTION_I_9 (END_DATE),
    INDEX PRINT_SUBSCRIPTION_I_10 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# ONLINE_SUBSCRIPTION
# -----------------------------------------------------------------------
drop table if exists ONLINE_SUBSCRIPTION;

CREATE TABLE ONLINE_SUBSCRIPTION
(
		            ONLINE_SUBS_ID INTEGER NOT NULL AUTO_INCREMENT,
		            ONLINE_SUBS_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            ISSUED_DATE DATE NOT NULL,
		            CLOSED_DATE DATE,
		            SORDER_ID INTEGER default 1000 NOT NULL,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            RECIPIENT_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            QUANTITY INTEGER default 1 NOT NULL,
		            START_DATE DATE NOT NULL,
		            END_DATE DATE NOT NULL,
		            SUBJECT VARCHAR (254) default '---' NOT NULL,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(ONLINE_SUBS_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (RECIPIENT_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    FOREIGN KEY (SORDER_ID) REFERENCES SORDER (SORDER_ID)
    ,
    UNIQUE (ONLINE_SUBS_CODE),
    INDEX ONLINE_SUBSCRIPTION_I_1 (CUSTOMER_ID),
    INDEX ONLINE_SUBSCRIPTION_I_2 (SORDER_ID),
    INDEX ONLINE_SUBSCRIPTION_I_3 (PROJECT_ID),
    INDEX ONLINE_SUBSCRIPTION_I_4 (PRODUCT_ID),
    INDEX ONLINE_SUBSCRIPTION_I_5 (ISSUED_DATE),
    INDEX ONLINE_SUBSCRIPTION_I_6 (CLOSED_DATE),
    INDEX ONLINE_SUBSCRIPTION_I_7 (STATUS),
    INDEX ONLINE_SUBSCRIPTION_I_8 (START_DATE),
    INDEX ONLINE_SUBSCRIPTION_I_9 (END_DATE),
    INDEX ONLINE_SUBSCRIPTION_I_10 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# INBOX_EVENT
# -----------------------------------------------------------------------
drop table if exists INBOX_EVENT;

CREATE TABLE INBOX_EVENT
(
		            INBOX_EVENT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            INBOX_EVENT_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            ISSUED_DATE DATE NOT NULL,
		            CLOSED_DATE DATE,
		            EVENT_CHANNEL INTEGER default 20 NOT NULL,
		            EVENT_TYPE INTEGER default 30 NOT NULL,
		            EMAIL_FORMAT INTEGER default 10 NOT NULL,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            CONTACT_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            SENDER_NAME VARCHAR (254),
		            SENDER_EMAIL VARCHAR (254),
		            SENDER_REPLY_TO VARCHAR (254),
		            SENT_TIME TIMESTAMP,
		            SENDER_TO LONGTEXT,
		            SENDER_CC LONGTEXT,
		            CUSTOM_1 VARCHAR (128),
		            CUSTOM_2 VARCHAR (128),
		            CUSTOM_3 VARCHAR (128),
		            CUSTOM_4 VARCHAR (128),
		            CUSTOM_5 VARCHAR (128),
		            CUSTOM_6 VARCHAR (128),
		            SUBJECT VARCHAR (254) NOT NULL,
		            BODY LONGTEXT,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(INBOX_EVENT_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (CONTACT_ID) REFERENCES CONTACT (CONTACT_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    UNIQUE (INBOX_EVENT_CODE),
    INDEX INBOX_EVENT_I_1 (CUSTOMER_ID),
    INDEX INBOX_EVENT_I_2 (CONTACT_ID),
    INDEX INBOX_EVENT_I_3 (ISSUED_DATE),
    INDEX INBOX_EVENT_I_4 (STATUS),
    INDEX INBOX_EVENT_I_5 (SUBJECT),
    INDEX INBOX_EVENT_I_6 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# INBOX_ATTACHMENT
# -----------------------------------------------------------------------
drop table if exists INBOX_ATTACHMENT;

CREATE TABLE INBOX_ATTACHMENT
(
		            INBOX_ATTACHMENT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            INBOX_EVENT_ID INTEGER default 1000 NOT NULL,
		            CONTENT_TYPE VARCHAR (254) NOT NULL,
		            CONTENT_DISPOSITION VARCHAR (254),
		            CONTENT_ID VARCHAR (254),
		            FILE_NAME VARCHAR (254),
		            CONTENT LONGTEXT,
    PRIMARY KEY(INBOX_ATTACHMENT_ID),
    FOREIGN KEY (INBOX_EVENT_ID) REFERENCES INBOX_EVENT (INBOX_EVENT_ID)
        ON DELETE CASCADE 
  ,
    INDEX INBOX_ATTACHMENT_I_1 (INBOX_EVENT_ID)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# OUTBOX_EVENT
# -----------------------------------------------------------------------
drop table if exists OUTBOX_EVENT;

CREATE TABLE OUTBOX_EVENT
(
		            OUTBOX_EVENT_ID INTEGER NOT NULL AUTO_INCREMENT,
		            OUTBOX_EVENT_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            ISSUED_DATE DATE NOT NULL,
		            CLOSED_DATE DATE,
		            SENT_TIME TIMESTAMP,
		            EVENT_CHANNEL INTEGER default 10 NOT NULL,
		            EVENT_TYPE INTEGER default 20 NOT NULL,
		            EMAIL_FORMAT INTEGER default 10 NOT NULL,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            CONTACT_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            RECEIVER_TO LONGTEXT,
		            RECEIVER_CC LONGTEXT,
		            SUBJECT VARCHAR (254) NOT NULL,
		            BODY LONGTEXT,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(OUTBOX_EVENT_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (CONTACT_ID) REFERENCES CONTACT (CONTACT_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    UNIQUE (OUTBOX_EVENT_CODE),
    INDEX OUTBOX_EVENT_I_1 (CUSTOMER_ID),
    INDEX OUTBOX_EVENT_I_2 (CONTACT_ID),
    INDEX OUTBOX_EVENT_I_3 (ISSUED_DATE),
    INDEX OUTBOX_EVENT_I_4 (STATUS),
    INDEX OUTBOX_EVENT_I_5 (SUBJECT),
    INDEX OUTBOX_EVENT_I_6 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# NEWS_SUBSCRIPTION
# -----------------------------------------------------------------------
drop table if exists NEWS_SUBSCRIPTION;

CREATE TABLE NEWS_SUBSCRIPTION
(
		            NEWS_SUBS_ID INTEGER NOT NULL AUTO_INCREMENT,
		            NEWS_SUBS_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            ISSUED_DATE DATE NOT NULL,
		            CLOSED_DATE DATE,
		            EMAIL VARCHAR (254) NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            SUBJECT VARCHAR (254) default '---' NOT NULL,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(NEWS_SUBS_ID),
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    UNIQUE (NEWS_SUBS_CODE),
    INDEX NEWS_SUBSCRIPTION_I_1 (EMAIL),
    INDEX NEWS_SUBSCRIPTION_I_2 (PROJECT_ID),
    INDEX NEWS_SUBSCRIPTION_I_3 (PRODUCT_ID),
    INDEX NEWS_SUBSCRIPTION_I_4 (ISSUED_DATE),
    INDEX NEWS_SUBSCRIPTION_I_5 (CLOSED_DATE),
    INDEX NEWS_SUBSCRIPTION_I_6 (STATUS),
    INDEX NEWS_SUBSCRIPTION_I_7 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# NEWSLETTER
# -----------------------------------------------------------------------
drop table if exists NEWSLETTER;

CREATE TABLE NEWSLETTER
(
		            NEWSLETTER_ID INTEGER NOT NULL AUTO_INCREMENT,
		            NEWSLETTER_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            ISSUED_DATE DATE NOT NULL,
		            CLOSED_DATE DATE,
		            SENT_TIME TIMESTAMP,
		            EMAIL_FORMAT INTEGER default 10 NOT NULL,
		            LANGUAGE_ID INTEGER default 1000 NOT NULL,
		            CUSTOMER_CAT_ID INTEGER default 999 NOT NULL,
		            CUSTOMER_TYPE INTEGER default 1 NOT NULL,
		            CUST_LANGUAGE_ID INTEGER default 999 NOT NULL,
		            CUST_COUNTRY_ID INTEGER default 999 NOT NULL,
		            REL_DOCUMENT INTEGER default 10 NOT NULL,
		            REL_DOC_STATUS INTEGER default 1 NOT NULL,
		            REL_PROJECT_ID INTEGER default 999 NOT NULL,
		            REL_PRODUCT_ID INTEGER default 999 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            SUBJECT VARCHAR (254) NOT NULL,
		            BODY LONGTEXT,
		            NOTES LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(NEWSLETTER_ID),
    FOREIGN KEY (CUSTOMER_CAT_ID) REFERENCES CUSTOMER_CATEGORY (CUSTOMER_CAT_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    FOREIGN KEY (REL_PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (REL_PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    FOREIGN KEY (CUST_COUNTRY_ID) REFERENCES COUNTRY (COUNTRY_ID)
    ,
    FOREIGN KEY (CUST_LANGUAGE_ID) REFERENCES LANGUAGE (LANGUAGE_ID)
    ,
    FOREIGN KEY (LANGUAGE_ID) REFERENCES LANGUAGE (LANGUAGE_ID)
    ,
    UNIQUE (NEWSLETTER_CODE),
    INDEX NEWSLETTER_I_1 (CUSTOMER_CAT_ID),
    INDEX NEWSLETTER_I_2 (ISSUED_DATE),
    INDEX NEWSLETTER_I_3 (STATUS),
    INDEX NEWSLETTER_I_4 (SUBJECT),
    INDEX NEWSLETTER_I_5 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# TASK
# -----------------------------------------------------------------------
drop table if exists TASK;

CREATE TABLE TASK
(
		            TASK_ID INTEGER NOT NULL AUTO_INCREMENT,
		            TASK_CODE VARCHAR (20) default 'AUTO' NOT NULL,
		            STATUS INTEGER default 30 NOT NULL,
		            PRIORITY INTEGER default 30 NOT NULL,
		            START_DATE DATE,
		            DUE_DATE DATE,
		            PROGRESS INTEGER default 0 NOT NULL,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            CONTACT_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            ACCESS INTEGER default 10 NOT NULL,
		            ASSIGNED_TO VARCHAR (32) NOT NULL,
		            SUBJECT VARCHAR (254) NOT NULL,
		            DESCRIPTION LONGTEXT,
		            CREATED TIMESTAMP NOT NULL,
		            MODIFIED TIMESTAMP NOT NULL,
		            CREATED_BY VARCHAR (32) NOT NULL,
		            MODIFIED_BY VARCHAR (32) NOT NULL,
    PRIMARY KEY(TASK_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (CONTACT_ID) REFERENCES CONTACT (CONTACT_ID)
    ,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    UNIQUE (TASK_CODE),
    INDEX TASK_I_1 (CUSTOMER_ID),
    INDEX TASK_I_2 (CONTACT_ID),
    INDEX TASK_I_3 (PROJECT_ID),
    INDEX TASK_I_4 (PRODUCT_ID),
    INDEX TASK_I_5 (START_DATE),
    INDEX TASK_I_6 (DUE_DATE),
    INDEX TASK_I_7 (STATUS),
    INDEX TASK_I_8 (ACCESS),
    INDEX TASK_I_9 (PRIORITY),
    INDEX TASK_I_10 (ASSIGNED_TO),
    INDEX TASK_I_11 (CREATED_BY)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# SORDER_ITEM
# -----------------------------------------------------------------------
drop table if exists SORDER_ITEM;

CREATE TABLE SORDER_ITEM
(
		            SORDER_ITEM_ID INTEGER NOT NULL AUTO_INCREMENT,
		            SORDER_ID INTEGER default 1000 NOT NULL,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            RECIPIENT_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            DESCRIPTION VARCHAR (254) NOT NULL,
		            UNIT_PRICE DECIMAL (15,2) default 0 NOT NULL,
		            CURRENCY_ID INTEGER default 1000 NOT NULL,
		            QUANTITY INTEGER default 1 NOT NULL,
		            ITEM_CURR_TOTAL DECIMAL (15,2) default 0 NOT NULL,
    PRIMARY KEY(SORDER_ITEM_ID),
    FOREIGN KEY (SORDER_ID) REFERENCES SORDER (SORDER_ID)
        ON DELETE CASCADE 
  ,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (RECIPIENT_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    FOREIGN KEY (CURRENCY_ID) REFERENCES CURRENCY (CURRENCY_ID)
    ,
    INDEX SORDER_ITEM_I_1 (CUSTOMER_ID),
    INDEX SORDER_ITEM_I_2 (SORDER_ID),
    INDEX SORDER_ITEM_I_3 (PROJECT_ID),
    INDEX SORDER_ITEM_I_4 (PRODUCT_ID)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# PAYMENT_ITEM
# -----------------------------------------------------------------------
drop table if exists PAYMENT_ITEM;

CREATE TABLE PAYMENT_ITEM
(
		            PAYMENT_ITEM_ID INTEGER NOT NULL AUTO_INCREMENT,
		            PAYMENT_ID INTEGER default 1000 NOT NULL,
		            SORDER_ID INTEGER default 1000 NOT NULL,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            DESCRIPTION VARCHAR (254) NOT NULL,
		            UNIT_PRICE DECIMAL (15,2) default 0 NOT NULL,
		            QUANTITY INTEGER default 1 NOT NULL,
		            CURRENCY_ID INTEGER default 1000 NOT NULL,
		            ITEM_CURR_TOTAL DECIMAL (15,2) default 0 NOT NULL,
		            ITEM_TOTAL DECIMAL (15,2) default 0 NOT NULL,
    PRIMARY KEY(PAYMENT_ITEM_ID),
    FOREIGN KEY (PAYMENT_ID) REFERENCES PAYMENT (PAYMENT_ID)
        ON DELETE CASCADE 
  ,
    FOREIGN KEY (SORDER_ID) REFERENCES SORDER (SORDER_ID)
    ,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    FOREIGN KEY (CURRENCY_ID) REFERENCES CURRENCY (CURRENCY_ID)
    ,
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    INDEX PAYMENT_ITEM_I_1 (CUSTOMER_ID),
    INDEX PAYMENT_ITEM_I_2 (SORDER_ID),
    INDEX PAYMENT_ITEM_I_3 (PROJECT_ID),
    INDEX PAYMENT_ITEM_I_4 (PRODUCT_ID),
    INDEX PAYMENT_ITEM_I_5 (PAYMENT_ID)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# SHIPMENT_ITEM
# -----------------------------------------------------------------------
drop table if exists SHIPMENT_ITEM;

CREATE TABLE SHIPMENT_ITEM
(
		            SHIPMENT_ITEM_ID INTEGER NOT NULL AUTO_INCREMENT,
		            SHIPMENT_ID INTEGER default 1000 NOT NULL,
		            SORDER_ID INTEGER default 1000 NOT NULL,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            RECIPIENT_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            DESCRIPTION VARCHAR (254) NOT NULL,
		            QUANTITY INTEGER default 1 NOT NULL,
    PRIMARY KEY(SHIPMENT_ITEM_ID),
    FOREIGN KEY (SHIPMENT_ID) REFERENCES SHIPMENT (SHIPMENT_ID)
        ON DELETE CASCADE 
  ,
    FOREIGN KEY (SORDER_ID) REFERENCES SORDER (SORDER_ID)
    ,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (RECIPIENT_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    INDEX SHIPMENT_ITEM_I_1 (CUSTOMER_ID),
    INDEX SHIPMENT_ITEM_I_2 (SORDER_ID),
    INDEX SHIPMENT_ITEM_I_3 (PROJECT_ID),
    INDEX SHIPMENT_ITEM_I_4 (PRODUCT_ID),
    INDEX SHIPMENT_ITEM_I_5 (SHIPMENT_ID)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# SERVICE_ITEM
# -----------------------------------------------------------------------
drop table if exists SERVICE_ITEM;

CREATE TABLE SERVICE_ITEM
(
		            SERVICE_ITEM_ID INTEGER NOT NULL AUTO_INCREMENT,
		            SORDER_ID INTEGER default 1000 NOT NULL,
		            CUSTOMER_ID INTEGER default 1000 NOT NULL,
		            RECIPIENT_ID INTEGER default 1000 NOT NULL,
		            PROJECT_ID INTEGER default 1000 NOT NULL,
		            SERVICE_ID INTEGER default 1000 NOT NULL,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            DESCRIPTION VARCHAR (254) NOT NULL,
		            QUANTITY INTEGER default 1 NOT NULL,
    PRIMARY KEY(SERVICE_ITEM_ID),
    FOREIGN KEY (SERVICE_ID) REFERENCES SERVICE (SERVICE_ID)
        ON DELETE CASCADE 
  ,
    FOREIGN KEY (SORDER_ID) REFERENCES SORDER (SORDER_ID)
    ,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
    ,
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (RECIPIENT_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
    ,
    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
    ,
    INDEX SERVICE_ITEM_I_1 (CUSTOMER_ID),
    INDEX SERVICE_ITEM_I_2 (SORDER_ID),
    INDEX SERVICE_ITEM_I_3 (PROJECT_ID),
    INDEX SERVICE_ITEM_I_4 (PRODUCT_ID),
    INDEX SERVICE_ITEM_I_5 (SERVICE_ID)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# ONLINE_SUBSCRIPTION_IP
# -----------------------------------------------------------------------
drop table if exists ONLINE_SUBSCRIPTION_IP;

CREATE TABLE ONLINE_SUBSCRIPTION_IP
(
		            ONLINE_SUBS_IP_ID INTEGER NOT NULL AUTO_INCREMENT,
		            ONLINE_SUBS_ID INTEGER default 1000 NOT NULL,
		            IP_1 INTEGER default 0 NOT NULL,
		            IP_2 INTEGER default 0 NOT NULL,
		            IP_3 INTEGER default 0 NOT NULL,
		            IP_4 INTEGER default 0 NOT NULL,
		            ADDRESS_NO INTEGER default 1 NOT NULL,
    PRIMARY KEY(ONLINE_SUBS_IP_ID),
    FOREIGN KEY (ONLINE_SUBS_ID) REFERENCES ONLINE_SUBSCRIPTION (ONLINE_SUBS_ID)
        ON DELETE CASCADE 
  ,
    INDEX ONLINE_SUBSCRIPTION_IP_I_1 (ONLINE_SUBS_ID)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# PRODUCT_CMS_SECTION
# -----------------------------------------------------------------------
drop table if exists PRODUCT_CMS_SECTION;

CREATE TABLE PRODUCT_CMS_SECTION
(
		            PRODUCT_CMS_SEC_ID INTEGER NOT NULL AUTO_INCREMENT,
		            PRODUCT_ID INTEGER default 1000 NOT NULL,
		            CMS_PUBLICATION_ID INTEGER default 0 NOT NULL,
		            CMS_SECTION_ID INTEGER default 0 NOT NULL,
		            CMS_LANGUAGE_ID INTEGER default 0 NOT NULL,
    PRIMARY KEY(PRODUCT_CMS_SEC_ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
        ON DELETE CASCADE 
  ,
    INDEX PRODUCT_CMS_SECTION_I_1 (PRODUCT_ID, CMS_PUBLICATION_ID, CMS_SECTION_ID, CMS_LANGUAGE_ID)
) ENGINE=InnoDB;

# -----------------------------------------------------------------------
# CMS_SECTION instead of views
# -----------------------------------------------------------------------
drop table if exists CMS_SECTION;

CREATE TABLE CMS_SECTION
(
		CMS_PUBLICATION_ID INTEGER default 0 NOT NULL,
		CMS_SECTION_ID INTEGER default 0 NOT NULL,
		CMS_LANGUAGE_ID INTEGER default 0 NOT NULL,
		CMS_SECTION_NAME VARCHAR (254)
) ENGINE=InnoDB;


INSERT INTO TURBINE_GROUP (GROUP_ID,GROUP_NAME)
    VALUES (1,'global');
    

INSERT INTO TURBINE_GROUP (GROUP_ID,GROUP_NAME)
    VALUES (1000,'system');
    

INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME)
    VALUES (1,'admin_users');
    

INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME)
    VALUES (2,'homepage_access');
    

INSERT INTO TURBINE_ROLE (ROLE_ID,ROLE_NAME)
    VALUES (1,'turbine_root');
    

INSERT INTO TURBINE_ROLE (ROLE_ID,ROLE_NAME)
    VALUES (2,'Site Administrator');
    

INSERT INTO TURBINE_ROLE (ROLE_ID,ROLE_NAME)
    VALUES (1000,'System');
    

INSERT INTO TURBINE_ROLE_PERMISSION (ROLE_ID,PERMISSION_ID)
    VALUES (1,1);
    

INSERT INTO TURBINE_ROLE_PERMISSION (ROLE_ID,PERMISSION_ID)
    VALUES (1,2);
    

INSERT INTO TURBINE_USER (USER_ID,LOGIN_NAME,PASSWORD_VALUE,FIRST_NAME,LAST_NAME)
    VALUES (1,'admin','admn00','Administrator','Adminic');
    

INSERT INTO TURBINE_USER (USER_ID,LOGIN_NAME,PASSWORD_VALUE,FIRST_NAME,LAST_NAME)
    VALUES (1000,'system','system','System','System');
    

INSERT INTO TURBINE_USER_GROUP_ROLE (USER_ID,GROUP_ID,ROLE_ID)
    VALUES (1,1,1);
    

INSERT INTO TURBINE_USER_GROUP_ROLE (USER_ID,GROUP_ID,ROLE_ID)
    VALUES (1000,1000,1000);
    

INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1100,'CUSTOMER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1101,'CUSTOMER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1110,'CONTACT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1111,'CONTACT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1120,'PRODUCT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1121,'PRODUCT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1130,'PROJECT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1131,'PROJECT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2010,'INBOX_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2011,'INBOX_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2020,'OUTBOX_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2021,'OUTBOX_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2030,'TASK_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2031,'TASK_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (3010,'OPPORTUNITY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (3011,'OPPORTUNITY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (3020,'NEWS_SUBSCRIPTION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (3021,'NEWS_SUBSCRIPTION_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (3030,'NEWSLETTER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (3031,'NEWSLETTER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4010,'SORDER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4011,'SORDER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4020,'SHIPMENT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4021,'SHIPMENT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4030,'SERVICE_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4031,'SERVICE_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4040,'ONLINE_SUBSCRIPTION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4041,'ONLINE_SUBSCRIPTION_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4050,'PRINT_SUBSCRIPTION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4051,'PRINT_SUBSCRIPTION_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4060,'PAYMENT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (4061,'PAYMENT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (5000,'LOOKUP_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (5001,'LOOKUP_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (6010,'NOTIFICATION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (6011,'NOTIFICATION_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (6020,'TURBINE_USER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (6021,'TURBINE_USER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (6030,'TURBINE_ROLE_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (6031,'TURBINE_ROLE_MODIFY');

INSERT INTO TURBINE_SCHEDULED_JOB (JOB_ID, SECOND, MINUTE, HOUR, WEEK_DAY, TASK) VALUES (1,0,7,0,-1, 'OnlineSubscriptionJob');
INSERT INTO TURBINE_SCHEDULED_JOB (JOB_ID, SECOND, MINUTE, HOUR, WEEK_DAY, TASK) VALUES (2,0,5,-1,-1, 'Pop3Job');

INSERT INTO CREAM_USER (USER_ID, LOGIN_NAME, WELCOME) VALUES (1, 'admin', 'Welcome Admin');
INSERT INTO CREAM_USER (USER_ID, LOGIN_NAME, WELCOME) VALUES (1000, 'system', 'Welcome System');

INSERT INTO CARRIER (CARRIER_ID,CARRIER_NAME)
    VALUES (1000,' ---');
INSERT INTO CARRIER (CARRIER_ID,CARRIER_NAME)
    VALUES (999,' (*)');
INSERT INTO REGION (REGION_ID,REGION_NAME)
    VALUES (1000,' ---');
INSERT INTO REGION (REGION_ID,REGION_NAME)
    VALUES (999,' (*)');
INSERT INTO VENDOR (VENDOR_ID,VENDOR_NAME)
    VALUES (1000,' ---');
INSERT INTO VENDOR (VENDOR_ID,VENDOR_NAME)
    VALUES (999,' (*)');
INSERT INTO COUNTRY (COUNTRY_ID,COUNTRY_NAME,COUNTRY_CODE)
    VALUES (1000,' ---', ' -');
INSERT INTO COUNTRY (COUNTRY_ID,COUNTRY_NAME,COUNTRY_CODE)
    VALUES (999,' (*)', ' *');
INSERT INTO CURRENCY (CURRENCY_ID,CURRENCY_NAME,CURRENCY_CODE, CURRENCY_RATE)
    VALUES (1000,' ---', ' --', 1);
INSERT INTO CURRENCY (CURRENCY_ID,CURRENCY_NAME,CURRENCY_CODE, CURRENCY_RATE)
    VALUES (999,' (*)', ' *', 1);
INSERT INTO CUSTOMER_CATEGORY (CUSTOMER_CAT_ID,CUSTOMER_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO CUSTOMER_CATEGORY (CUSTOMER_CAT_ID,CUSTOMER_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO CONTACT_CATEGORY (CONTACT_CAT_ID,CONTACT_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO CONTACT_CATEGORY (CONTACT_CAT_ID,CONTACT_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO INDUSTRY (INDUSTRY_ID,INDUSTRY_NAME)
    VALUES (1000,' ---');
INSERT INTO INDUSTRY (INDUSTRY_ID,INDUSTRY_NAME)
    VALUES (999,' (*)');
INSERT INTO EDUCATION_CATEGORY (EDUCATION_CAT_ID,EDUCATION_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO EDUCATION_CATEGORY (EDUCATION_CAT_ID,EDUCATION_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO HOUSEHOLD_CATEGORY (HOUSEHOLD_CAT_ID,HOUSEHOLD_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO HOUSEHOLD_CATEGORY (HOUSEHOLD_CAT_ID,HOUSEHOLD_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO AGE_CATEGORY (AGE_CAT_ID,AGE_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO AGE_CATEGORY (AGE_CAT_ID,AGE_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO REVENUE_CATEGORY (REVENUE_CAT_ID,REVENUE_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO REVENUE_CATEGORY (REVENUE_CAT_ID,REVENUE_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO EMPLOYEE_NO_CATEGORY (EMPLOYEE_NO_CAT_ID,EMPLOYEE_NO_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO EMPLOYEE_NO_CATEGORY (EMPLOYEE_NO_CAT_ID,EMPLOYEE_NO_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO LEAD_SOURCE (LEAD_SOURCE_ID,LEAD_SOURCE_NAME)
    VALUES (1000,' ---');
INSERT INTO LEAD_SOURCE (LEAD_SOURCE_ID,LEAD_SOURCE_NAME)
    VALUES (999,' (*)');
INSERT INTO JOB_POSITION (JOB_POSITION_ID,JOB_POSITION_NAME)
    VALUES (1000,' ---');
INSERT INTO JOB_POSITION (JOB_POSITION_ID,JOB_POSITION_NAME)
    VALUES (999,' (*)');
INSERT INTO SALUTATION (SALUTATION_ID,SALUTATION_NAME)
    VALUES (1000,' ---');
INSERT INTO SALUTATION (SALUTATION_ID,SALUTATION_NAME)
    VALUES (999,' (*)');
INSERT INTO OPPORTUNITY_CATEGORY (OPPORTUNITY_CAT_ID,OPPORTUNITY_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO OPPORTUNITY_CATEGORY (OPPORTUNITY_CAT_ID,OPPORTUNITY_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO PROJECT_CATEGORY (PROJECT_CAT_ID,PROJECT_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO PROJECT_CATEGORY (PROJECT_CAT_ID,PROJECT_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO PRODUCT_CATEGORY (PRODUCT_CAT_ID,PRODUCT_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO PRODUCT_CATEGORY (PRODUCT_CAT_ID,PRODUCT_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO LANGUAGE (LANGUAGE_ID,LANGUAGE_NAME,LANGUAGE_CODE)
    VALUES (1000,' ---', ' -');
INSERT INTO LANGUAGE (LANGUAGE_ID,LANGUAGE_NAME,LANGUAGE_CODE)
    VALUES (999,' (*)', ' *');
INSERT INTO UOM (UOM_ID,UOM_NAME,UOM_CODE)
    VALUES (1000,' ---', '---');
INSERT INTO UOM (UOM_ID,UOM_NAME,UOM_CODE)
    VALUES (999,' (*)', ' *');
INSERT INTO UOM (UOM_ID,UOM_NAME,UOM_CODE)
    VALUES (101,'Day', 'DAY');
INSERT INTO UOM (UOM_ID,UOM_NAME,UOM_CODE)
    VALUES (102,'Week', 'WEE');
INSERT INTO UOM (UOM_ID,UOM_NAME,UOM_CODE)
    VALUES (103,'Month', 'MON');
INSERT INTO UOM (UOM_ID,UOM_NAME,UOM_CODE)
    VALUES (104,'Quarter of a year', 'QAN');
INSERT INTO UOM (UOM_ID,UOM_NAME,UOM_CODE)
    VALUES (105,'Half of a year', 'SAN');
INSERT INTO UOM (UOM_ID,UOM_NAME,UOM_CODE)
    VALUES (106,'Year', 'ANN');

INSERT INTO CUSTOMER (CUSTOMER_ID,CUSTOMER_CODE, STATUS, CUSTOMER_TYPE, CUSTOMER_NAME_1, CUSTOMER_DISPLAY, SEND_NEWS, LOGIN_NAME, PASSWORD_VALUE, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---', 1, 1, ' ---', ' ---', 0,' ---', 'blank',  '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO CUSTOMER (CUSTOMER_ID,CUSTOMER_CODE, STATUS, CUSTOMER_TYPE, CUSTOMER_NAME_1, CUSTOMER_DISPLAY, SEND_NEWS, LOGIN_NAME, PASSWORD_VALUE, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (999,' (*)', 1, 1, ' (*)', ' (*)', 0,' (*)', 'blank',  '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO CONTACT (CONTACT_ID,CONTACT_CODE, STATUS, LAST_NAME, CONTACT_DISPLAY, SEND_NEWS, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---', 1, ' ---', ' ---', 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO CONTACT (CONTACT_ID,CONTACT_CODE, STATUS, LAST_NAME, CONTACT_DISPLAY, SEND_NEWS, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (999,' (*)', 1, ' (*)', ' (*)', 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CODE, STATUS, PRIORITY, PRODUCT_TYPE, PRODUCT_CAT_ID, PRODUCT_DESCRIPTION, PRODUCT_DISPLAY, BASE_PRICE, UOM_ID, SHOW_ON_PRICELIST, VENDOR_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---', 1, 30, 1, 1000, ' ---', ' ---', 0, 1000, 0, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CODE, STATUS, PRIORITY, PRODUCT_TYPE, PRODUCT_CAT_ID, PRODUCT_DESCRIPTION, PRODUCT_DISPLAY, BASE_PRICE, UOM_ID, SHOW_ON_PRICELIST, VENDOR_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (999,' (*)', 1, 30, 1, 1000, ' (*)', ' (*)', 0, 1000, 0, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO PROJECT (PROJECT_ID,PROJECT_CODE, STATUS, PRIORITY, PROJECT_CAT_ID, PROJECT_NAME, START_DATE, EXPENSES, REVENUES, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---', 1, 30, 1000, ' ---', '1990-1-1', 0, 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO PROJECT (PROJECT_ID,PROJECT_CODE, STATUS, PRIORITY, PROJECT_CAT_ID, PROJECT_NAME, START_DATE, EXPENSES, REVENUES, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (999,' (*)', 1, 30, 1000, ' (*)', '1990-1-1', 0, 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO OPPORTUNITY (OPPORTUNITY_ID,OPPORTUNITY_CODE, OPPORTUNITY_NAME, SUBJECT, STATUS, ISSUED_DATE, EXPECTED_DATE, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---',' ---', 1, '1990-1-1', '1990-1-1', '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO SORDER (SORDER_ID,SORDER_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, PROJECT_ID, CARRIER_ID, PAY_TERM, PAY_METHOD, CURRENCY_ID, CURRENCY_AMOUNT, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 30, '1990-1-1', 1000, 1000, 1000, 1000, 10, 10, 1000, 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO SHIPMENT (SHIPMENT_ID,SHIPMENT_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, PROJECT_ID, SORDER_ID, CARRIER_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 30, '1990-1-1', 1000, 1000, 1000, 1000, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO SERVICE (SERVICE_ID, SERVICE_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, PROJECT_ID, SORDER_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 30, '1990-1-1', 1000, 1000, 1000, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO PAYMENT (PAYMENT_ID, PAYMENT_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, CUSTOMER_ID, PROJECT_ID, PAY_TERM, PAY_METHOD, CURRENCY_ID, CURRENCY_AMOUNT, TOTAL_AMOUNT, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 30, '1990-1-1', 1000, 1000, 10, 10, 1000, 0, 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO PRINT_SUBSCRIPTION (PRINT_SUBS_ID, PRINT_SUBS_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, PROJECT_ID, PRODUCT_ID, SORDER_ID, START_DATE, END_DATE, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 30, '1990-1-1', 1000, 1000, 1000, 1000, 1000, '1990-1-1', '1990-1-1', '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO ONLINE_SUBSCRIPTION (ONLINE_SUBS_ID, ONLINE_SUBS_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, PROJECT_ID, PRODUCT_ID, SORDER_ID, START_DATE, END_DATE, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 30, '1990-1-1', 1000, 1000, 1000, 1000, 1000, '1990-1-1', '1990-1-1', '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO INBOX_EVENT (INBOX_EVENT_ID, INBOX_EVENT_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, CUSTOMER_ID, PROJECT_ID, PRODUCT_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 30, '1990-1-1', 1000, 1000, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO OUTBOX_EVENT (OUTBOX_EVENT_ID, OUTBOX_EVENT_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, CUSTOMER_ID, PROJECT_ID, PRODUCT_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 30, '1990-1-1', 1000, 1000, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO NEWS_SUBSCRIPTION (NEWS_SUBS_ID, NEWS_SUBS_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, EMAIL, PROJECT_ID, PRODUCT_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 30, '1990-1-1', 'root@localhost', 1000, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO NEWSLETTER (NEWSLETTER_ID, NEWSLETTER_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, PROJECT_ID, PRODUCT_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 30, '1990-1-1', 1000, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO TASK (TASK_ID, TASK_CODE, SUBJECT, STATUS, ACCESS, ASSIGNED_TO, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 1, 'system', '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO NOTIFICATION VALUES (1001,10, 1000, 10, 'Some subject', 'Some text');
INSERT INTO NOTIFICATION VALUES (1002,20, 1000, 10, 'Some subject', 'Some text');
INSERT INTO NOTIFICATION VALUES (1003,30, 1000, 10, 'Some subject', 'Some text');


drop view if exists CUSTOMER_DOC;
create view CUSTOMER_DOC (DOC_ID, DOC_TYPE, DOC_CODE, STATUS, SUBJECT, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, CREATED) as (Select INBOX_EVENT_ID, EVENT_TYPE, INBOX_EVENT_CODE, STATUS, SUBJECT, ISSUED_DATE, CUSTOMER_ID, CUSTOMER_ID, CREATED from INBOX_EVENT) UNION (Select OUTBOX_EVENT_ID, 100, OUTBOX_EVENT_CODE, STATUS, SUBJECT, ISSUED_DATE, CUSTOMER_ID, CUSTOMER_ID, CREATED from OUTBOX_EVENT) UNION (Select ONLINE_SUBS_ID, 110, ONLINE_SUBS_CODE, STATUS, SUBJECT, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, CREATED from ONLINE_SUBSCRIPTION) UNION (Select PRINT_SUBS_ID, 120, PRINT_SUBS_CODE, STATUS, SUBJECT, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, CREATED from PRINT_SUBSCRIPTION) UNION (Select SERVICE_ID, 130, SERVICE_CODE, STATUS, SUBJECT, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, CREATED from SERVICE) UNION (Select SHIPMENT_ID, 140, SHIPMENT_CODE, STATUS, SUBJECT, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, CREATED from SHIPMENT) UNION (Select PAYMENT_ID, 150, PAYMENT_CODE, STATUS, SUBJECT, ISSUED_DATE, CUSTOMER_ID, CUSTOMER_ID, CREATED from PAYMENT) UNION (Select SORDER_ID, 160, SORDER_CODE, STATUS, SUBJECT, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, CREATED from SORDER) UNION (Select OPPORTUNITY_ID, 170, OPPORTUNITY_CODE, STATUS, SUBJECT, ISSUED_DATE, CUSTOMER_ID, CUSTOMER_ID, CREATED from OPPORTUNITY) ORDER BY 6 DESC, 9 DESC;

drop view if exists CONTACT_DOC;
create view CONTACT_DOC (DOC_ID, DOC_TYPE, DOC_CODE, STATUS, SUBJECT, ISSUED_DATE, CONTACT_ID, CREATED) as (Select INBOX_EVENT_ID, EVENT_TYPE, INBOX_EVENT_CODE, STATUS, SUBJECT, ISSUED_DATE, CONTACT_ID, CREATED from INBOX_EVENT) UNION (Select OUTBOX_EVENT_ID, 100, OUTBOX_EVENT_CODE, STATUS, SUBJECT, ISSUED_DATE, CONTACT_ID, CREATED from OUTBOX_EVENT) ORDER BY 6 DESC, 8 DESC;

drop view if exists PROJECT_DOC;
create view PROJECT_DOC (DOC_ID, DOC_TYPE, DOC_CODE, STATUS, SUBJECT, ISSUED_DATE, PROJECT_ID, CREATED) as (Select INBOX_EVENT_ID, EVENT_TYPE, INBOX_EVENT_CODE, STATUS, SUBJECT, ISSUED_DATE, PROJECT_ID, CREATED from INBOX_EVENT) UNION (Select OUTBOX_EVENT_ID, 100, OUTBOX_EVENT_CODE, STATUS, SUBJECT, ISSUED_DATE, PROJECT_ID, CREATED from OUTBOX_EVENT) UNION (Select ONLINE_SUBS_ID, 110, ONLINE_SUBS_CODE, STATUS, SUBJECT, ISSUED_DATE, PROJECT_ID, CREATED from ONLINE_SUBSCRIPTION) UNION (Select PRINT_SUBS_ID, 120, PRINT_SUBS_CODE, STATUS, SUBJECT, ISSUED_DATE, PROJECT_ID, CREATED from PRINT_SUBSCRIPTION) UNION (Select SERVICE_ID, 130, SERVICE_CODE, STATUS, SUBJECT, ISSUED_DATE, PROJECT_ID, CREATED from SERVICE) UNION (Select SHIPMENT_ID, 140, SHIPMENT_CODE, STATUS, SUBJECT, ISSUED_DATE, PROJECT_ID, CREATED from SHIPMENT) UNION (Select PAYMENT_ID, 150, PAYMENT_CODE, STATUS, SUBJECT, ISSUED_DATE, PROJECT_ID, CREATED from PAYMENT) UNION (Select SORDER_ID, 160, SORDER_CODE, STATUS, SUBJECT, ISSUED_DATE, PROJECT_ID, CREATED from SORDER) UNION (Select OPPORTUNITY_ID, 170, OPPORTUNITY_CODE, STATUS, SUBJECT, ISSUED_DATE, PROJECT_ID, CREATED from OPPORTUNITY) ORDER BY 6 DESC, 8 DESC;

drop view if exists SORDER_DOC;
create view SORDER_DOC (DOC_ID, DOC_TYPE, DOC_CODE, STATUS, SUBJECT, ISSUED_DATE, SORDER_ID, CREATED) as (Select ONLINE_SUBS_ID, 110, ONLINE_SUBS_CODE, STATUS, SUBJECT, ISSUED_DATE, SORDER_ID, CREATED from ONLINE_SUBSCRIPTION) UNION (Select PRINT_SUBS_ID, 120, PRINT_SUBS_CODE, STATUS, SUBJECT, ISSUED_DATE, SORDER_ID, CREATED from PRINT_SUBSCRIPTION) UNION (Select SERVICE_ID, 130, SERVICE_CODE, STATUS, SUBJECT, ISSUED_DATE, SORDER_ID, CREATED from SERVICE) UNION (Select SHIPMENT_ID, 140, SHIPMENT_CODE, STATUS, SUBJECT, ISSUED_DATE, SORDER_ID, CREATED from SHIPMENT) UNION (Select PAYMENT_ID, 150, PAYMENT_CODE, STATUS, SUBJECT, ISSUED_DATE, SORDER_ID, CREATED from PAYMENT) ORDER BY 6 DESC, 8 DESC;
