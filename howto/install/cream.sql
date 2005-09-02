 

-----------------------------------------------------------------------------
-- TURBINE_PERMISSION
-----------------------------------------------------------------------------
DROP TABLE TURBINE_PERMISSION;
DROP SEQUENCE TURBINE_PERMISSION_SEQ;

CREATE SEQUENCE TURBINE_PERMISSION_SEQ;

CREATE TABLE TURBINE_PERMISSION
(
                            PERMISSION_ID integer DEFAULT nextval('TURBINE_PERMISSION_SEQ') NOT NULL,
                            PERMISSION_NAME varchar (99) NOT NULL,
                            OBJECTDATA bytea,
    PRIMARY KEY (PERMISSION_ID),
    CONSTRAINT TURBINE_PERMISSION_U_1 UNIQUE (PERMISSION_NAME)
);


-----------------------------------------------------------------------------
-- TURBINE_ROLE
-----------------------------------------------------------------------------
DROP TABLE TURBINE_ROLE;
DROP SEQUENCE TURBINE_ROLE_SEQ;

CREATE SEQUENCE TURBINE_ROLE_SEQ minvalue 1001;

CREATE TABLE TURBINE_ROLE
(
                            ROLE_ID integer DEFAULT nextval('TURBINE_ROLE_SEQ') NOT NULL,
                            ROLE_NAME varchar (99) NOT NULL,
                            OBJECTDATA bytea,
    PRIMARY KEY (ROLE_ID),
    CONSTRAINT TURBINE_ROLE_U_1 UNIQUE (ROLE_NAME)
);


-----------------------------------------------------------------------------
-- TURBINE_GROUP
-----------------------------------------------------------------------------
DROP TABLE TURBINE_GROUP;
DROP SEQUENCE TURBINE_GROUP_SEQ;

CREATE SEQUENCE TURBINE_GROUP_SEQ;

CREATE TABLE TURBINE_GROUP
(
                            GROUP_ID integer DEFAULT nextval('TURBINE_GROUP_SEQ') NOT NULL,
                            GROUP_NAME varchar (99) NOT NULL,
                            OBJECTDATA bytea,
    PRIMARY KEY (GROUP_ID),
    CONSTRAINT TURBINE_GROUP_U_1 UNIQUE (GROUP_NAME)
);


-----------------------------------------------------------------------------
-- TURBINE_ROLE_PERMISSION
-----------------------------------------------------------------------------
DROP TABLE TURBINE_ROLE_PERMISSION;


CREATE TABLE TURBINE_ROLE_PERMISSION
(
                              -- REFERENCES TURBINE_ROLE (ROLE_ID)
    ROLE_ID integer NOT NULL,
                              -- REFERENCES TURBINE_PERMISSION (PERMISSION_ID)
    PERMISSION_ID integer NOT NULL,
    PRIMARY KEY (ROLE_ID,PERMISSION_ID)
);


-----------------------------------------------------------------------------
-- TURBINE_USER
-----------------------------------------------------------------------------
DROP TABLE TURBINE_USER;
DROP SEQUENCE TURBINE_USER_SEQ;

CREATE SEQUENCE TURBINE_USER_SEQ minvalue 1001;

CREATE TABLE TURBINE_USER
(
                            USER_ID integer DEFAULT nextval('TURBINE_USER_SEQ') NOT NULL,
                            LOGIN_NAME varchar (32) NOT NULL,
                            PASSWORD_VALUE varchar (32) NOT NULL,
                            FIRST_NAME varchar (99) NOT NULL,
                            LAST_NAME varchar (99) NOT NULL,
                            EMAIL varchar (99),
                            CONFIRM_VALUE varchar (99),
                            MODIFIED timestamp,
                            CREATED timestamp,
                            LAST_LOGIN timestamp,
                            OBJECTDATA bytea,
    PRIMARY KEY (USER_ID),
    CONSTRAINT TURBINE_USER_U_1 UNIQUE (LOGIN_NAME)
);


-----------------------------------------------------------------------------
-- TURBINE_USER_GROUP_ROLE
-----------------------------------------------------------------------------
DROP TABLE TURBINE_USER_GROUP_ROLE;


CREATE TABLE TURBINE_USER_GROUP_ROLE
(
                              -- REFERENCES TURBINE_USER (USER_ID)
    USER_ID integer NOT NULL,
                              -- REFERENCES TURBINE_GROUP (GROUP_ID)
    GROUP_ID integer NOT NULL,
                              -- REFERENCES TURBINE_ROLE (ROLE_ID)
    ROLE_ID integer NOT NULL,
    PRIMARY KEY (USER_ID,GROUP_ID,ROLE_ID)
);


-----------------------------------------------------------------------------
-- TURBINE_SCHEDULED_JOB
-----------------------------------------------------------------------------
DROP TABLE TURBINE_SCHEDULED_JOB;
DROP SEQUENCE TURBINE_SCHEDULED_JOB_SEQ;

CREATE SEQUENCE TURBINE_SCHEDULED_JOB_SEQ;

CREATE TABLE TURBINE_SCHEDULED_JOB
(
                            JOB_ID integer DEFAULT nextval('TURBINE_SCHEDULED_JOB_SEQ') NOT NULL,
                            SECOND integer default -1 NOT NULL,
                            MINUTE integer default -1 NOT NULL,
                            HOUR integer default -1 NOT NULL,
                            WEEK_DAY integer default -1 NOT NULL,
                            DAY_OF_MONTH integer default -1 NOT NULL,
                            TASK varchar (99) NOT NULL,
                            EMAIL varchar (99),
                            PROPERTY bytea,
    PRIMARY KEY (JOB_ID)
);


----------------------------------------------------------------------
-- TURBINE_SCHEDULED_JOB                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- TURBINE_PERMISSION                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- TURBINE_ROLE                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- TURBINE_GROUP                                                      
----------------------------------------------------------------------

ALTER TABLE TURBINE_ROLE_PERMISSION
    ADD CONSTRAINT TURBINE_ROLE_PERMISSION_FK_1 FOREIGN KEY (ROLE_ID)
    REFERENCES TURBINE_ROLE (ROLE_ID)
    ON DELETE CASCADE 
;
ALTER TABLE TURBINE_ROLE_PERMISSION
    ADD CONSTRAINT TURBINE_ROLE_PERMISSION_FK_2 FOREIGN KEY (PERMISSION_ID)
    REFERENCES TURBINE_PERMISSION (PERMISSION_ID)
;

----------------------------------------------------------------------
-- TURBINE_ROLE_PERMISSION                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- TURBINE_USER                                                      
----------------------------------------------------------------------

ALTER TABLE TURBINE_USER_GROUP_ROLE
    ADD CONSTRAINT TURBINE_USER_GROUP_ROLE_FK_1 FOREIGN KEY (USER_ID)
    REFERENCES TURBINE_USER (USER_ID)
    ON DELETE CASCADE 
;
ALTER TABLE TURBINE_USER_GROUP_ROLE
    ADD CONSTRAINT TURBINE_USER_GROUP_ROLE_FK_2 FOREIGN KEY (GROUP_ID)
    REFERENCES TURBINE_GROUP (GROUP_ID)
;
ALTER TABLE TURBINE_USER_GROUP_ROLE
    ADD CONSTRAINT TURBINE_USER_GROUP_ROLE_FK_3 FOREIGN KEY (ROLE_ID)
    REFERENCES TURBINE_ROLE (ROLE_ID)
;

----------------------------------------------------------------------
-- TURBINE_USER_GROUP_ROLE                                                      
----------------------------------------------------------------------


-----------------------------------------------------------------------------
-- CREAM_USER
-----------------------------------------------------------------------------
DROP TABLE CREAM_USER;

CREATE TABLE CREAM_USER
(
                            USER_ID integer NOT NULL,
                            LOGIN_NAME varchar (32) NOT NULL,
                            WELCOME varchar (32) NOT NULL,
                            DEFAULT_LIST integer default 1000 NOT NULL,
                            INBOX_FID integer default 1000 NOT NULL,
                            OUTBOX_FID integer default 1000 NOT NULL,
                            NEWS_SUBS_FID integer default 1000 NOT NULL,
                            NEWSLETTER_FID integer default 1000 NOT NULL,
                            ONLINE_SUBS_FID integer default 1000 NOT NULL,
                            PRINT_SUBS_FID integer default 1000 NOT NULL,
                            SERVICE_FID integer default 1000 NOT NULL,
                            SHIPMENT_FID integer default 1000 NOT NULL,
                            PAYMENT_FID integer default 1000 NOT NULL,
                            SORDER_FID integer default 1000 NOT NULL,
                            PROJECT_FID integer default 1000 NOT NULL,
                            CUSTOMER_FID integer default 1000 NOT NULL,
                            PRODUCT_FID integer default 1000 NOT NULL,
    PRIMARY KEY (USER_ID),
    CONSTRAINT CREAM_USER_U_1 UNIQUE (LOGIN_NAME)
);


-----------------------------------------------------------------------------
-- NOTIFICATION
-----------------------------------------------------------------------------
DROP TABLE NOTIFICATION;
DROP SEQUENCE NOTIFICATION_SEQ;

CREATE SEQUENCE NOTIFICATION_SEQ minvalue 1001;

CREATE TABLE NOTIFICATION
(
                            NOTIFICATION_ID integer DEFAULT nextval('NOTIFICATION_SEQ') NOT NULL,
                            NOTIFICATION_TYPE integer default 10 NOT NULL,
                              -- REFERENCES LANGUAGE (LANGUAGE_ID)
    LANGUAGE_ID integer default 1000 NOT NULL,
                            EMAIL_FORMAT integer default 10 NOT NULL,
                            SUBJECT varchar (254) NOT NULL,
                            BODY text,
    PRIMARY KEY (NOTIFICATION_ID),
    CONSTRAINT NOTIFICATION_U_1 UNIQUE (NOTIFICATION_TYPE, LANGUAGE_ID, EMAIL_FORMAT)
);


-----------------------------------------------------------------------------
-- PROJECT_CATEGORY
-----------------------------------------------------------------------------
DROP TABLE PROJECT_CATEGORY;
DROP SEQUENCE PROJECT_CATEGORY_SEQ;

CREATE SEQUENCE PROJECT_CATEGORY_SEQ minvalue 1001;

CREATE TABLE PROJECT_CATEGORY
(
                            PROJECT_CAT_ID integer DEFAULT nextval('PROJECT_CATEGORY_SEQ') NOT NULL,
                            PROJECT_CAT_NAME varchar (50) NOT NULL,
    PRIMARY KEY (PROJECT_CAT_ID),
    CONSTRAINT PROJECT_CATEGORY_U_1 UNIQUE (PROJECT_CAT_NAME)
);


-----------------------------------------------------------------------------
-- PRODUCT_CATEGORY
-----------------------------------------------------------------------------
DROP TABLE PRODUCT_CATEGORY;
DROP SEQUENCE PRODUCT_CATEGORY_SEQ;

CREATE SEQUENCE PRODUCT_CATEGORY_SEQ minvalue 1001;

CREATE TABLE PRODUCT_CATEGORY
(
                            PRODUCT_CAT_ID integer DEFAULT nextval('PRODUCT_CATEGORY_SEQ') NOT NULL,
                            PRODUCT_CAT_NAME varchar (50) NOT NULL,
    PRIMARY KEY (PRODUCT_CAT_ID),
    CONSTRAINT PRODUCT_CATEGORY_U_1 UNIQUE (PRODUCT_CAT_NAME)
);


-----------------------------------------------------------------------------
-- CUSTOMER_CATEGORY
-----------------------------------------------------------------------------
DROP TABLE CUSTOMER_CATEGORY;
DROP SEQUENCE CUSTOMER_CATEGORY_SEQ;

CREATE SEQUENCE CUSTOMER_CATEGORY_SEQ minvalue 1001;

CREATE TABLE CUSTOMER_CATEGORY
(
                            CUSTOMER_CAT_ID integer DEFAULT nextval('CUSTOMER_CATEGORY_SEQ') NOT NULL,
                            CUSTOMER_CAT_NAME varchar (50) NOT NULL,
    PRIMARY KEY (CUSTOMER_CAT_ID),
    CONSTRAINT CUSTOMER_CATEGORY_U_1 UNIQUE (CUSTOMER_CAT_NAME)
);


-----------------------------------------------------------------------------
-- COUNTRY
-----------------------------------------------------------------------------
DROP TABLE COUNTRY;
DROP SEQUENCE COUNTRY_SEQ;

CREATE SEQUENCE COUNTRY_SEQ minvalue 1001;

CREATE TABLE COUNTRY
(
                            COUNTRY_ID integer DEFAULT nextval('COUNTRY_SEQ') NOT NULL,
                            COUNTRY_NAME varchar (50) NOT NULL,
                            COUNTRY_CODE char (2) NOT NULL,
    PRIMARY KEY (COUNTRY_ID),
    CONSTRAINT COUNTRY_U_1 UNIQUE (COUNTRY_NAME),
    CONSTRAINT COUNTRY_U_2 UNIQUE (COUNTRY_CODE)
);


-----------------------------------------------------------------------------
-- REGION
-----------------------------------------------------------------------------
DROP TABLE REGION;
DROP SEQUENCE REGION_SEQ;

CREATE SEQUENCE REGION_SEQ minvalue 1001;

CREATE TABLE REGION
(
                            REGION_ID integer DEFAULT nextval('REGION_SEQ') NOT NULL,
                            REGION_NAME varchar (50) NOT NULL,
    PRIMARY KEY (REGION_ID),
    CONSTRAINT REGION_U_1 UNIQUE (REGION_NAME)
);


-----------------------------------------------------------------------------
-- LANGUAGE
-----------------------------------------------------------------------------
DROP TABLE LANGUAGE;
DROP SEQUENCE LANGUAGE_SEQ;

CREATE SEQUENCE LANGUAGE_SEQ minvalue 1001;

CREATE TABLE LANGUAGE
(
                            LANGUAGE_ID integer DEFAULT nextval('LANGUAGE_SEQ') NOT NULL,
                            LANGUAGE_NAME varchar (50) NOT NULL,
                            LANGUAGE_CODE char (2) NOT NULL,
    PRIMARY KEY (LANGUAGE_ID),
    CONSTRAINT LANGUAGE_U_1 UNIQUE (LANGUAGE_NAME),
    CONSTRAINT LANGUAGE_U_2 UNIQUE (LANGUAGE_CODE)
);


-----------------------------------------------------------------------------
-- CURRENCY
-----------------------------------------------------------------------------
DROP TABLE CURRENCY;
DROP SEQUENCE CURRENCY_SEQ;

CREATE SEQUENCE CURRENCY_SEQ minvalue 1001;

CREATE TABLE CURRENCY
(
                            CURRENCY_ID integer DEFAULT nextval('CURRENCY_SEQ') NOT NULL,
                            CURRENCY_NAME varchar (50) NOT NULL,
                            CURRENCY_CODE char (3) NOT NULL,
                            CURRENCY_RATE decimal (15,6) default 1 NOT NULL,
    PRIMARY KEY (CURRENCY_ID),
    CONSTRAINT CURRENCY_U_1 UNIQUE (CURRENCY_NAME),
    CONSTRAINT CURRENCY_U_2 UNIQUE (CURRENCY_CODE)
);


-----------------------------------------------------------------------------
-- CARRIER
-----------------------------------------------------------------------------
DROP TABLE CARRIER;
DROP SEQUENCE CARRIER_SEQ;

CREATE SEQUENCE CARRIER_SEQ minvalue 1001;

CREATE TABLE CARRIER
(
                            CARRIER_ID integer DEFAULT nextval('CARRIER_SEQ') NOT NULL,
                            CARRIER_NAME varchar (50) NOT NULL,
    PRIMARY KEY (CARRIER_ID),
    CONSTRAINT CARRIER_U_1 UNIQUE (CARRIER_NAME)
);


-----------------------------------------------------------------------------
-- VENDOR
-----------------------------------------------------------------------------
DROP TABLE VENDOR;
DROP SEQUENCE VENDOR_SEQ;

CREATE SEQUENCE VENDOR_SEQ minvalue 1001;

CREATE TABLE VENDOR
(
                            VENDOR_ID integer DEFAULT nextval('VENDOR_SEQ') NOT NULL,
                            VENDOR_NAME varchar (50) NOT NULL,
    PRIMARY KEY (VENDOR_ID),
    CONSTRAINT VENDOR_U_1 UNIQUE (VENDOR_NAME)
);


-----------------------------------------------------------------------------
-- UOM
-----------------------------------------------------------------------------
DROP TABLE UOM;
DROP SEQUENCE UOM_SEQ;

CREATE SEQUENCE UOM_SEQ minvalue 1001;

CREATE TABLE UOM
(
                            UOM_ID integer DEFAULT nextval('UOM_SEQ') NOT NULL,
                            UOM_NAME varchar (50) NOT NULL,
                            UOM_CODE char (3) NOT NULL,
    PRIMARY KEY (UOM_ID),
    CONSTRAINT UOM_U_1 UNIQUE (UOM_NAME),
    CONSTRAINT UOM_U_2 UNIQUE (UOM_CODE)
);


-----------------------------------------------------------------------------
-- EDUCATION_CATEGORY
-----------------------------------------------------------------------------
DROP TABLE EDUCATION_CATEGORY;
DROP SEQUENCE EDUCATION_CATEGORY_SEQ;

CREATE SEQUENCE EDUCATION_CATEGORY_SEQ minvalue 1001;

CREATE TABLE EDUCATION_CATEGORY
(
                            EDUCATION_CAT_ID integer DEFAULT nextval('EDUCATION_CATEGORY_SEQ') NOT NULL,
                            EDUCATION_CAT_NAME varchar (50) NOT NULL,
    PRIMARY KEY (EDUCATION_CAT_ID),
    CONSTRAINT EDUCATION_CATEGORY_U_1 UNIQUE (EDUCATION_CAT_NAME)
);


-----------------------------------------------------------------------------
-- HOUSEHOLD_CATEGORY
-----------------------------------------------------------------------------
DROP TABLE HOUSEHOLD_CATEGORY;
DROP SEQUENCE HOUSEHOLD_CATEGORY_SEQ;

CREATE SEQUENCE HOUSEHOLD_CATEGORY_SEQ minvalue 1001;

CREATE TABLE HOUSEHOLD_CATEGORY
(
                            HOUSEHOLD_CAT_ID integer DEFAULT nextval('HOUSEHOLD_CATEGORY_SEQ') NOT NULL,
                            HOUSEHOLD_CAT_NAME varchar (50) NOT NULL,
    PRIMARY KEY (HOUSEHOLD_CAT_ID),
    CONSTRAINT HOUSEHOLD_CATEGORY_U_1 UNIQUE (HOUSEHOLD_CAT_NAME)
);


-----------------------------------------------------------------------------
-- CUSTOMER
-----------------------------------------------------------------------------
DROP TABLE CUSTOMER;
DROP SEQUENCE CUSTOMER_SEQ;

CREATE SEQUENCE CUSTOMER_SEQ minvalue 1001;

CREATE TABLE CUSTOMER
(
                            CUSTOMER_ID integer DEFAULT nextval('CUSTOMER_SEQ') NOT NULL,
                            CUSTOMER_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            CUSTOMER_TYPE integer default 10 NOT NULL,
                              -- REFERENCES CUSTOMER_CATEGORY (CUSTOMER_CAT_ID)
    CUSTOMER_CAT_ID integer default 1000 NOT NULL,
                            CUSTOMER_NAME_1 varchar (70) NOT NULL,
                            CUSTOMER_NAME_2 varchar (70),
                            CUSTOMER_DISPLAY varchar (70) NOT NULL,
                            DEAR varchar (70),
                            ADDRESS_1 varchar (55),
                            ADDRESS_2 varchar (55),
                            CITY varchar (35),
                            ZIP varchar (12),
                            STATE varchar (35),
                              -- REFERENCES COUNTRY (COUNTRY_ID)
    COUNTRY_ID integer default 1000 NOT NULL,
                              -- REFERENCES REGION (REGION_ID)
    REGION_ID integer default 1000 NOT NULL,
                            PHONE_1 varchar (30),
                            PHONE_2 varchar (30),
                            FAX varchar (30),
                            EMAIL varchar (70),
                            EMAIL_FORMAT integer default 10 NOT NULL,
                            SEND_NEWS integer default 20 NOT NULL,
                            WEB_URL varchar (70),
                              -- REFERENCES LANGUAGE (LANGUAGE_ID)
    LANGUAGE_ID integer default 1000 NOT NULL,
                            GENDER integer default 10 NOT NULL,
                              -- REFERENCES EDUCATION_CATEGORY (EDUCATION_CAT_ID)
    EDUCATION_CAT_ID integer default 1000 NOT NULL,
                              -- REFERENCES HOUSEHOLD_CATEGORY (HOUSEHOLD_CAT_ID)
    HOUSEHOLD_CAT_ID integer default 1000 NOT NULL,
                            CUSTOM_1 varchar (55),
                            CUSTOM_2 varchar (55),
                            CUSTOM_3 varchar (55),
                            CUSTOM_4 varchar (55),
                            CUSTOM_5 varchar (55),
                            CUSTOM_6 varchar (55),
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (CUSTOMER_ID),
    CONSTRAINT CUSTOMER_U_1 UNIQUE (CUSTOMER_CODE),
    CONSTRAINT CUSTOMER_U_2 UNIQUE (CUSTOMER_DISPLAY)
);
CREATE INDEX CUSTOMER_I_1 ON CUSTOMER (CUSTOMER_TYPE);
CREATE INDEX CUSTOMER_I_2 ON CUSTOMER (STATUS);
CREATE INDEX CUSTOMER_I_3 ON CUSTOMER (COUNTRY_ID);
CREATE INDEX CUSTOMER_I_4 ON CUSTOMER (REGION_ID);
CREATE INDEX CUSTOMER_I_5 ON CUSTOMER (LANGUAGE_ID);
CREATE INDEX CUSTOMER_I_6 ON CUSTOMER (CUSTOMER_CAT_ID);
CREATE INDEX CUSTOMER_I_7 ON CUSTOMER (EMAIL);
CREATE INDEX CUSTOMER_I_8 ON CUSTOMER (CITY);
CREATE INDEX CUSTOMER_I_9 ON CUSTOMER (CREATED_BY);


-----------------------------------------------------------------------------
-- PRODUCT
-----------------------------------------------------------------------------
DROP TABLE PRODUCT;
DROP SEQUENCE PRODUCT_SEQ;

CREATE SEQUENCE PRODUCT_SEQ minvalue 1001;

CREATE TABLE PRODUCT
(
                            PRODUCT_ID integer DEFAULT nextval('PRODUCT_SEQ') NOT NULL,
                            PRODUCT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            PRODUCT_TYPE integer default 10 NOT NULL,
                              -- REFERENCES PRODUCT_CATEGORY (PRODUCT_CAT_ID)
    PRODUCT_CAT_ID integer default 1000 NOT NULL,
                            PRODUCT_DESCRIPTION varchar (254) NOT NULL,
                            PRODUCT_DISPLAY varchar (70) NOT NULL,
                            BASE_PRICE float default 0 NOT NULL,
                              -- REFERENCES UOM (UOM_ID)
    UOM_ID integer default 1000 NOT NULL,
                            WEB_URL varchar (70),
                            SHOW_ON_PRICELIST integer default 20 NOT NULL,
                              -- REFERENCES VENDOR (VENDOR_ID)
    VENDOR_ID integer default 1000 NOT NULL,
                            VENDORS_CODE varchar (20),
                            EAN_UPC_CODE varchar (20),
                            LOCATION varchar (55),
                            CUSTOM_1 varchar (55),
                            CUSTOM_2 varchar (55),
                            CUSTOM_3 varchar (55),
                            CUSTOM_4 varchar (55),
                            CUSTOM_5 varchar (55),
                            CUSTOM_6 varchar (55),
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (PRODUCT_ID),
    CONSTRAINT PRODUCT_U_1 UNIQUE (PRODUCT_CODE),
    CONSTRAINT PRODUCT_U_2 UNIQUE (PRODUCT_DISPLAY)
);
CREATE INDEX PRODUCT_I_1 ON PRODUCT (PRODUCT_CAT_ID);
CREATE INDEX PRODUCT_I_2 ON PRODUCT (UOM_ID);
CREATE INDEX PRODUCT_I_3 ON PRODUCT (PRODUCT_TYPE);
CREATE INDEX PRODUCT_I_4 ON PRODUCT (STATUS);
CREATE INDEX PRODUCT_I_5 ON PRODUCT (VENDOR_ID);
CREATE INDEX PRODUCT_I_6 ON PRODUCT (BASE_PRICE);
CREATE INDEX PRODUCT_I_7 ON PRODUCT (CREATED_BY);


-----------------------------------------------------------------------------
-- PROJECT
-----------------------------------------------------------------------------
DROP TABLE PROJECT;
DROP SEQUENCE PROJECT_SEQ;

CREATE SEQUENCE PROJECT_SEQ minvalue 1001;

CREATE TABLE PROJECT
(
                            PROJECT_ID integer DEFAULT nextval('PROJECT_SEQ') NOT NULL,
                            PROJECT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                              -- REFERENCES PROJECT_CATEGORY (PROJECT_CAT_ID)
    PROJECT_CAT_ID integer default 1000 NOT NULL,
                            PROJECT_NAME varchar (70) NOT NULL,
                            START_DATE date NOT NULL,
                            END_DATE date,
                            EXPENSES decimal (15,2) default 0 NOT NULL,
                            REVENUES decimal (15,2) default 0 NOT NULL,
                            CUSTOM_1 varchar (55),
                            CUSTOM_2 varchar (55),
                            CUSTOM_3 varchar (55),
                            CUSTOM_4 varchar (55),
                            CUSTOM_5 varchar (55),
                            CUSTOM_6 varchar (55),
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (PROJECT_ID),
    CONSTRAINT PROJECT_U_1 UNIQUE (PROJECT_CODE),
    CONSTRAINT PROJECT_U_2 UNIQUE (PROJECT_NAME)
);
CREATE INDEX PROJECT_I_1 ON PROJECT (PROJECT_CAT_ID);
CREATE INDEX PROJECT_I_2 ON PROJECT (STATUS);
CREATE INDEX PROJECT_I_3 ON PROJECT (START_DATE);
CREATE INDEX PROJECT_I_4 ON PROJECT (EXPENSES);
CREATE INDEX PROJECT_I_5 ON PROJECT (REVENUES);
CREATE INDEX PROJECT_I_6 ON PROJECT (CREATED_BY);


-----------------------------------------------------------------------------
-- SORDER
-----------------------------------------------------------------------------
DROP TABLE SORDER;
DROP SEQUENCE SORDER_SEQ;

CREATE SEQUENCE SORDER_SEQ minvalue 1001;

CREATE TABLE SORDER
(
                            SORDER_ID integer DEFAULT nextval('SORDER_SEQ') NOT NULL,
                            SORDER_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    RECIPIENT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES CARRIER (CARRIER_ID)
    CARRIER_ID integer default 1000 NOT NULL,
                            PAY_TERM integer default 10 NOT NULL,
                            PAY_METHOD integer default 10 NOT NULL,
                              -- REFERENCES CURRENCY (CURRENCY_ID)
    CURRENCY_ID integer default 1000 NOT NULL,
                            CURRENCY_AMOUNT decimal (15,2) default 0 NOT NULL,
                            SUBJECT varchar (254) default '---' NOT NULL,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (SORDER_ID),
    CONSTRAINT SORDER_U_1 UNIQUE (SORDER_CODE)
);
CREATE INDEX SORDER_I_1 ON SORDER (CUSTOMER_ID);
CREATE INDEX SORDER_I_2 ON SORDER (PROJECT_ID);
CREATE INDEX SORDER_I_3 ON SORDER (CURRENCY_ID);
CREATE INDEX SORDER_I_4 ON SORDER (ISSUED_DATE);
CREATE INDEX SORDER_I_5 ON SORDER (CLOSED_DATE);
CREATE INDEX SORDER_I_6 ON SORDER (STATUS);
CREATE INDEX SORDER_I_7 ON SORDER (CURRENCY_AMOUNT);
CREATE INDEX SORDER_I_8 ON SORDER (CREATED_BY);


-----------------------------------------------------------------------------
-- PAYMENT
-----------------------------------------------------------------------------
DROP TABLE PAYMENT;
DROP SEQUENCE PAYMENT_SEQ;

CREATE SEQUENCE PAYMENT_SEQ minvalue 1001;

CREATE TABLE PAYMENT
(
                            PAYMENT_ID integer DEFAULT nextval('PAYMENT_SEQ') NOT NULL,
                            PAYMENT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES SORDER (SORDER_ID)
    SORDER_ID integer default 1000 NOT NULL,
                            INVOICE_CODE varchar (20),
                            PAY_TERM integer default 10 NOT NULL,
                            PAY_METHOD integer default 10 NOT NULL,
                              -- REFERENCES CURRENCY (CURRENCY_ID)
    CURRENCY_ID integer default 1000 NOT NULL,
                            CURRENCY_RATE decimal (15,6) default 1 NOT NULL,
                            CURRENCY_AMOUNT decimal (15,2) default 0 NOT NULL,
                            TOTAL_AMOUNT decimal (15,2) default 0 NOT NULL,
                            SUBJECT varchar (254) default '---' NOT NULL,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (PAYMENT_ID),
    CONSTRAINT PAYMENT_U_1 UNIQUE (PAYMENT_CODE)
);
CREATE INDEX PAYMENT_I_1 ON PAYMENT (CUSTOMER_ID);
CREATE INDEX PAYMENT_I_2 ON PAYMENT (SORDER_ID);
CREATE INDEX PAYMENT_I_3 ON PAYMENT (PROJECT_ID);
CREATE INDEX PAYMENT_I_4 ON PAYMENT (CURRENCY_ID);
CREATE INDEX PAYMENT_I_5 ON PAYMENT (ISSUED_DATE);
CREATE INDEX PAYMENT_I_6 ON PAYMENT (CLOSED_DATE);
CREATE INDEX PAYMENT_I_7 ON PAYMENT (STATUS);
CREATE INDEX PAYMENT_I_8 ON PAYMENT (CURRENCY_AMOUNT);
CREATE INDEX PAYMENT_I_9 ON PAYMENT (CREATED_BY);


-----------------------------------------------------------------------------
-- SERVICE
-----------------------------------------------------------------------------
DROP TABLE SERVICE;
DROP SEQUENCE SERVICE_SEQ;

CREATE SEQUENCE SERVICE_SEQ minvalue 1001;

CREATE TABLE SERVICE
(
                            SERVICE_ID integer DEFAULT nextval('SERVICE_SEQ') NOT NULL,
                            SERVICE_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    RECIPIENT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES SORDER (SORDER_ID)
    SORDER_ID integer default 1000 NOT NULL,
                            INVOICE_CODE varchar (20),
                            SUBJECT varchar (254) default '---' NOT NULL,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (SERVICE_ID),
    CONSTRAINT SERVICE_U_1 UNIQUE (SERVICE_CODE)
);
CREATE INDEX SERVICE_I_1 ON SERVICE (CUSTOMER_ID);
CREATE INDEX SERVICE_I_2 ON SERVICE (SORDER_ID);
CREATE INDEX SERVICE_I_3 ON SERVICE (PROJECT_ID);
CREATE INDEX SERVICE_I_4 ON SERVICE (ISSUED_DATE);
CREATE INDEX SERVICE_I_5 ON SERVICE (CLOSED_DATE);
CREATE INDEX SERVICE_I_6 ON SERVICE (STATUS);
CREATE INDEX SERVICE_I_7 ON SERVICE (CREATED_BY);


-----------------------------------------------------------------------------
-- SHIPMENT
-----------------------------------------------------------------------------
DROP TABLE SHIPMENT;
DROP SEQUENCE SHIPMENT_SEQ;

CREATE SEQUENCE SHIPMENT_SEQ minvalue 1001;

CREATE TABLE SHIPMENT
(
                            SHIPMENT_ID integer DEFAULT nextval('SHIPMENT_SEQ') NOT NULL,
                            SHIPMENT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    RECIPIENT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES SORDER (SORDER_ID)
    SORDER_ID integer default 1000 NOT NULL,
                            INVOICE_CODE varchar (20),
                              -- REFERENCES CARRIER (CARRIER_ID)
    CARRIER_ID integer default 1000 NOT NULL,
                            SUBJECT varchar (254) default '---' NOT NULL,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (SHIPMENT_ID),
    CONSTRAINT SHIPMENT_U_1 UNIQUE (SHIPMENT_CODE)
);
CREATE INDEX SHIPMENT_I_1 ON SHIPMENT (CUSTOMER_ID);
CREATE INDEX SHIPMENT_I_2 ON SHIPMENT (SORDER_ID);
CREATE INDEX SHIPMENT_I_3 ON SHIPMENT (PROJECT_ID);
CREATE INDEX SHIPMENT_I_4 ON SHIPMENT (CARRIER_ID);
CREATE INDEX SHIPMENT_I_5 ON SHIPMENT (ISSUED_DATE);
CREATE INDEX SHIPMENT_I_6 ON SHIPMENT (CLOSED_DATE);
CREATE INDEX SHIPMENT_I_7 ON SHIPMENT (STATUS);
CREATE INDEX SHIPMENT_I_8 ON SHIPMENT (CREATED_BY);


-----------------------------------------------------------------------------
-- PRINT_SUBSCRIPTION
-----------------------------------------------------------------------------
DROP TABLE PRINT_SUBSCRIPTION;
DROP SEQUENCE PRINT_SUBSCRIPTION_SEQ;

CREATE SEQUENCE PRINT_SUBSCRIPTION_SEQ minvalue 1001;

CREATE TABLE PRINT_SUBSCRIPTION
(
                            PRINT_SUBS_ID integer DEFAULT nextval('PRINT_SUBSCRIPTION_SEQ') NOT NULL,
                            PRINT_SUBS_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                              -- REFERENCES SORDER (SORDER_ID)
    SORDER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    RECIPIENT_ID integer default 1000 NOT NULL,
                              -- REFERENCES CARRIER (CARRIER_ID)
    CARRIER_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            QUANTITY integer default 1 NOT NULL,
                            START_DATE date NOT NULL,
                            END_DATE date NOT NULL,
                            SUBJECT varchar (254) default '---' NOT NULL,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (PRINT_SUBS_ID),
    CONSTRAINT PRINT_SUBSCRIPTION_U_1 UNIQUE (PRINT_SUBS_CODE)
);
CREATE INDEX PRINT_SUBSCRIPTION_I_1 ON PRINT_SUBSCRIPTION (CUSTOMER_ID);
CREATE INDEX PRINT_SUBSCRIPTION_I_2 ON PRINT_SUBSCRIPTION (SORDER_ID);
CREATE INDEX PRINT_SUBSCRIPTION_I_3 ON PRINT_SUBSCRIPTION (PROJECT_ID);
CREATE INDEX PRINT_SUBSCRIPTION_I_4 ON PRINT_SUBSCRIPTION (PRODUCT_ID);
CREATE INDEX PRINT_SUBSCRIPTION_I_5 ON PRINT_SUBSCRIPTION (ISSUED_DATE);
CREATE INDEX PRINT_SUBSCRIPTION_I_6 ON PRINT_SUBSCRIPTION (CLOSED_DATE);
CREATE INDEX PRINT_SUBSCRIPTION_I_7 ON PRINT_SUBSCRIPTION (STATUS);
CREATE INDEX PRINT_SUBSCRIPTION_I_8 ON PRINT_SUBSCRIPTION (START_DATE);
CREATE INDEX PRINT_SUBSCRIPTION_I_9 ON PRINT_SUBSCRIPTION (END_DATE);
CREATE INDEX PRINT_SUBSCRIPTION_I_10 ON PRINT_SUBSCRIPTION (CREATED_BY);


-----------------------------------------------------------------------------
-- ONLINE_SUBSCRIPTION
-----------------------------------------------------------------------------
DROP TABLE ONLINE_SUBSCRIPTION;
DROP SEQUENCE ONLINE_SUBSCRIPTION_SEQ;

CREATE SEQUENCE ONLINE_SUBSCRIPTION_SEQ minvalue 1001;

CREATE TABLE ONLINE_SUBSCRIPTION
(
                            ONLINE_SUBS_ID integer DEFAULT nextval('ONLINE_SUBSCRIPTION_SEQ') NOT NULL,
                            ONLINE_SUBS_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                              -- REFERENCES SORDER (SORDER_ID)
    SORDER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    RECIPIENT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            QUANTITY integer default 1 NOT NULL,
                            START_DATE date NOT NULL,
                            END_DATE date NOT NULL,
                            SUBJECT varchar (254) default '---' NOT NULL,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (ONLINE_SUBS_ID),
    CONSTRAINT ONLINE_SUBSCRIPTION_U_1 UNIQUE (ONLINE_SUBS_CODE)
);
CREATE INDEX ONLINE_SUBSCRIPTION_I_1 ON ONLINE_SUBSCRIPTION (CUSTOMER_ID);
CREATE INDEX ONLINE_SUBSCRIPTION_I_2 ON ONLINE_SUBSCRIPTION (SORDER_ID);
CREATE INDEX ONLINE_SUBSCRIPTION_I_3 ON ONLINE_SUBSCRIPTION (PROJECT_ID);
CREATE INDEX ONLINE_SUBSCRIPTION_I_4 ON ONLINE_SUBSCRIPTION (PRODUCT_ID);
CREATE INDEX ONLINE_SUBSCRIPTION_I_5 ON ONLINE_SUBSCRIPTION (ISSUED_DATE);
CREATE INDEX ONLINE_SUBSCRIPTION_I_6 ON ONLINE_SUBSCRIPTION (CLOSED_DATE);
CREATE INDEX ONLINE_SUBSCRIPTION_I_7 ON ONLINE_SUBSCRIPTION (STATUS);
CREATE INDEX ONLINE_SUBSCRIPTION_I_8 ON ONLINE_SUBSCRIPTION (START_DATE);
CREATE INDEX ONLINE_SUBSCRIPTION_I_9 ON ONLINE_SUBSCRIPTION (END_DATE);
CREATE INDEX ONLINE_SUBSCRIPTION_I_10 ON ONLINE_SUBSCRIPTION (CREATED_BY);


-----------------------------------------------------------------------------
-- INBOX_EVENT
-----------------------------------------------------------------------------
DROP TABLE INBOX_EVENT;
DROP SEQUENCE INBOX_EVENT_SEQ;

CREATE SEQUENCE INBOX_EVENT_SEQ minvalue 1001;

CREATE TABLE INBOX_EVENT
(
                            INBOX_EVENT_ID integer DEFAULT nextval('INBOX_EVENT_SEQ') NOT NULL,
                            INBOX_EVENT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                            RECEIVED_TIME timestamp,
                            SENT_TIME timestamp,
                            EVENT_CHANNEL integer default 20 NOT NULL,
                            EVENT_TYPE integer default 30 NOT NULL,
                            EMAIL_FORMAT integer default 10 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            SENDER_NAME varchar (254),
                            SENDER_EMAIL varchar (254),
                            SENDER_REPLY_TO varchar (254),
                            SENDER_TO text,
                            SENDER_CC text,
                            SUBJECT varchar (254) NOT NULL,
                            BODY text,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (INBOX_EVENT_ID),
    CONSTRAINT INBOX_EVENT_U_1 UNIQUE (INBOX_EVENT_CODE)
);
CREATE INDEX INBOX_EVENT_I_1 ON INBOX_EVENT (CUSTOMER_ID);
CREATE INDEX INBOX_EVENT_I_2 ON INBOX_EVENT (ISSUED_DATE);
CREATE INDEX INBOX_EVENT_I_3 ON INBOX_EVENT (STATUS);
CREATE INDEX INBOX_EVENT_I_4 ON INBOX_EVENT (SUBJECT);
CREATE INDEX INBOX_EVENT_I_5 ON INBOX_EVENT (CREATED_BY);

-----------------------------------------------------------------------------
-- INBOX_ATTACHMENT
-----------------------------------------------------------------------------
DROP TABLE INBOX_ATTACHMENT CASCADE;
DROP SEQUENCE INBOX_ATTACHMENT_SEQ;

CREATE SEQUENCE INBOX_ATTACHMENT_SEQ minvalue 1001;

CREATE TABLE INBOX_ATTACHMENT
(
                                    INBOX_ATTACHMENT_ID integer DEFAULT nextval('INBOX_ATTACHMENT_SEQ') NOT NULL,
                                      -- REFERENCES INBOX_EVENT (INBOX_EVENT_ID)
    INBOX_EVENT_ID integer default 1000 NOT NULL,
                                    CONTENT_TYPE varchar (254) NOT NULL,
                                    CONTENT_DISPOSITION varchar (254),
                                    CONTENT_ID varchar (254),
                                    FILE_NAME varchar (254),
                                    CONTENT text,
    PRIMARY KEY (INBOX_ATTACHMENT_ID)
);
CREATE INDEX INBOX_ATTACHMENT_I_1 ON INBOX_ATTACHMENT (INBOX_EVENT_ID);


-----------------------------------------------------------------------------
-- OUTBOX_EVENT
-----------------------------------------------------------------------------
DROP TABLE OUTBOX_EVENT;
DROP SEQUENCE OUTBOX_EVENT_SEQ;

CREATE SEQUENCE OUTBOX_EVENT_SEQ minvalue 1001;

CREATE TABLE OUTBOX_EVENT
(
                            OUTBOX_EVENT_ID integer DEFAULT nextval('OUTBOX_EVENT_SEQ') NOT NULL,
                            OUTBOX_EVENT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                            SENT_TIME timestamp,
                            EVENT_CHANNEL integer default 10 NOT NULL,
                            EVENT_TYPE integer default 20 NOT NULL,
                            EMAIL_FORMAT integer default 10 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            RECEIVER_TO text,
                            RECEIVER_CC text,
                            SUBJECT varchar (254) NOT NULL,
                            BODY text,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (OUTBOX_EVENT_ID),
    CONSTRAINT OUTBOX_EVENT_U_1 UNIQUE (OUTBOX_EVENT_CODE)
);
CREATE INDEX OUTBOX_EVENT_I_1 ON OUTBOX_EVENT (CUSTOMER_ID);
CREATE INDEX OUTBOX_EVENT_I_2 ON OUTBOX_EVENT (ISSUED_DATE);
CREATE INDEX OUTBOX_EVENT_I_3 ON OUTBOX_EVENT (STATUS);
CREATE INDEX OUTBOX_EVENT_I_4 ON OUTBOX_EVENT (SUBJECT);
CREATE INDEX OUTBOX_EVENT_I_5 ON OUTBOX_EVENT (CREATED_BY);


-----------------------------------------------------------------------------
-- NEWS_SUBSCRIPTION
-----------------------------------------------------------------------------
DROP TABLE NEWS_SUBSCRIPTION CASCADE;
DROP SEQUENCE NEWS_SUBSCRIPTION_SEQ;

CREATE SEQUENCE NEWS_SUBSCRIPTION_SEQ minvalue 1001;

CREATE TABLE NEWS_SUBSCRIPTION
(
                                    NEWS_SUBS_ID integer DEFAULT nextval('NEWS_SUBSCRIPTION_SEQ') NOT NULL,
                                    NEWS_SUBS_CODE varchar (20) default 'AUTO' NOT NULL,
                                    STATUS integer default 30 NOT NULL,
                                    PRIORITY integer default 30 NOT NULL,
                                    ISSUED_DATE date NOT NULL,
                                    CLOSED_DATE date,
                                    EMAIL varchar (70) NOT NULL,
                                      -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                                      -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                                    SUBJECT varchar (254) default '---' NOT NULL,
                                    NOTES text,
                                    CREATED timestamp NOT NULL,
                                    MODIFIED timestamp NOT NULL,
                                    CREATED_BY varchar (32) NOT NULL,
                                    MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (NEWS_SUBS_ID),
    CONSTRAINT NEWS_SUBSCRIPTION_U_1 UNIQUE (NEWS_SUBS_CODE)
);
CREATE INDEX NEWS_SUBSCRIPTION_I_1 ON NEWS_SUBSCRIPTION (EMAIL);
CREATE INDEX NEWS_SUBSCRIPTION_I_2 ON NEWS_SUBSCRIPTION (PROJECT_ID);
CREATE INDEX NEWS_SUBSCRIPTION_I_3 ON NEWS_SUBSCRIPTION (PRODUCT_ID);
CREATE INDEX NEWS_SUBSCRIPTION_I_4 ON NEWS_SUBSCRIPTION (ISSUED_DATE);
CREATE INDEX NEWS_SUBSCRIPTION_I_5 ON NEWS_SUBSCRIPTION (CLOSED_DATE);
CREATE INDEX NEWS_SUBSCRIPTION_I_6 ON NEWS_SUBSCRIPTION (STATUS);
CREATE INDEX NEWS_SUBSCRIPTION_I_7 ON NEWS_SUBSCRIPTION (CREATED_BY);


-----------------------------------------------------------------------------
-- NEWSLETTER
-----------------------------------------------------------------------------
DROP TABLE NEWSLETTER;
DROP SEQUENCE NEWSLETTER_SEQ;

CREATE SEQUENCE NEWSLETTER_SEQ minvalue 1001;

CREATE TABLE NEWSLETTER
(
                            NEWSLETTER_ID integer DEFAULT nextval('NEWSLETTER_SEQ') NOT NULL,
                            NEWSLETTER_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                            SENT_TIME timestamp,
                            EMAIL_FORMAT integer default 10 NOT NULL,
                              -- REFERENCES LANGUAGE (LANGUAGE_ID)
    LANGUAGE_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER_CATEGORY (CUSTOMER_CAT_ID)
    CUSTOMER_CAT_ID integer default 999 NOT NULL,
                            CUSTOMER_TYPE integer default 1 NOT NULL,
                              -- REFERENCES LANGUAGE (LANGUAGE_ID)
    CUST_LANGUAGE_ID integer default 999 NOT NULL,
                              -- REFERENCES COUNTRY (COUNTRY_ID)
    CUST_COUNTRY_ID integer default 999 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
                            REL_DOCUMENT integer default 999 NOT NULL,
                            REL_DOC_STATUS integer default 1 NOT NULL,
    REL_PROJECT_ID integer default 10 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    REL_PRODUCT_ID integer default 999 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            SUBJECT varchar (254) NOT NULL,
                            BODY text,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (NEWSLETTER_ID),
    CONSTRAINT NEWSLETTER_U_1 UNIQUE (NEWSLETTER_CODE)
);
CREATE INDEX NEWSLETTER_I_1 ON NEWSLETTER (CUSTOMER_CAT_ID);
CREATE INDEX NEWSLETTER_I_2 ON NEWSLETTER (ISSUED_DATE);
CREATE INDEX NEWSLETTER_I_3 ON NEWSLETTER (STATUS);
CREATE INDEX NEWSLETTER_I_4 ON NEWSLETTER (SUBJECT);
CREATE INDEX NEWSLETTER_I_5 ON NEWSLETTER (CREATED_BY);


-----------------------------------------------------------------------------
-- SORDER_ITEM
-----------------------------------------------------------------------------
DROP TABLE SORDER_ITEM;
DROP SEQUENCE SORDER_ITEM_SEQ;

CREATE SEQUENCE SORDER_ITEM_SEQ minvalue 1001;

CREATE TABLE SORDER_ITEM
(
                            SORDER_ITEM_ID integer DEFAULT nextval('SORDER_ITEM_SEQ') NOT NULL,
                              -- REFERENCES SORDER (SORDER_ID)
    SORDER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    RECIPIENT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            DESCRIPTION varchar (254) NOT NULL,
                            UNIT_PRICE decimal (15,2) default 0 NOT NULL,
                            QUANTITY integer default 1 NOT NULL,
                              -- REFERENCES CURRENCY (CURRENCY_ID)
    CURRENCY_ID integer default 1000 NOT NULL,
                            ITEM_CURR_TOTAL decimal (15,2) default 0 NOT NULL,
    PRIMARY KEY (SORDER_ITEM_ID)
);
CREATE INDEX SORDER_ITEM_I_1 ON SORDER_ITEM (SORDER_ID);
CREATE INDEX SORDER_ITEM_I_2 ON SORDER_ITEM (PRODUCT_ID);
CREATE INDEX SORDER_ITEM_I_3 ON SORDER_ITEM (CUSTOMER_ID);
CREATE INDEX SORDER_ITEM_I_4 ON SORDER_ITEM (PROJECT_ID);


-----------------------------------------------------------------------------
-- PAYMENT_ITEM
-----------------------------------------------------------------------------
DROP TABLE PAYMENT_ITEM;
DROP SEQUENCE PAYMENT_ITEM_SEQ;

CREATE SEQUENCE PAYMENT_ITEM_SEQ minvalue 1001;

CREATE TABLE PAYMENT_ITEM
(
                            PAYMENT_ITEM_ID integer DEFAULT nextval('PAYMENT_ITEM_SEQ') NOT NULL,
                              -- REFERENCES PAYMENT (PAYMENT_ID)
    PAYMENT_ID integer default 1000 NOT NULL,
                              -- REFERENCES SORDER (SORDER_ID)
    SORDER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            DESCRIPTION varchar (254) NOT NULL,
                            UNIT_PRICE decimal (15,2) default 0 NOT NULL,
                            QUANTITY integer default 1 NOT NULL,
                              -- REFERENCES CURRENCY (CURRENCY_ID)
    CURRENCY_ID integer default 1000 NOT NULL,
                            ITEM_CURR_TOTAL decimal (15,2) default 0 NOT NULL,
                            ITEM_TOTAL decimal (15,2) default 0 NOT NULL,
    PRIMARY KEY (PAYMENT_ITEM_ID)
);
CREATE INDEX PAYMENT_ITEM_I_1 ON PAYMENT_ITEM (SORDER_ID);
CREATE INDEX PAYMENT_ITEM_I_2 ON PAYMENT_ITEM (PRODUCT_ID);
CREATE INDEX PAYMENT_ITEM_I_3 ON PAYMENT_ITEM (PAYMENT_ID);
CREATE INDEX PAYMENT_ITEM_I_4 ON PAYMENT_ITEM (CUSTOMER_ID);
CREATE INDEX PAYMENT_ITEM_I_5 ON PAYMENT_ITEM (PROJECT_ID);


-----------------------------------------------------------------------------
-- SHIPMENT_ITEM
-----------------------------------------------------------------------------
DROP TABLE SHIPMENT_ITEM;
DROP SEQUENCE SHIPMENT_ITEM_SEQ;

CREATE SEQUENCE SHIPMENT_ITEM_SEQ minvalue 1001;

CREATE TABLE SHIPMENT_ITEM
(
                            SHIPMENT_ITEM_ID integer DEFAULT nextval('SHIPMENT_ITEM_SEQ') NOT NULL,
                              -- REFERENCES SHIPMENT (SHIPMENT_ID)
    SHIPMENT_ID integer default 1000 NOT NULL,
                              -- REFERENCES SORDER (SORDER_ID)
    SORDER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    RECIPIENT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            DESCRIPTION varchar (254) NOT NULL,
                            QUANTITY integer default 1 NOT NULL,
    PRIMARY KEY (SHIPMENT_ITEM_ID)
);
CREATE INDEX SHIPMENT_ITEM_I_1 ON SHIPMENT_ITEM (SORDER_ID);
CREATE INDEX SHIPMENT_ITEM_I_2 ON SHIPMENT_ITEM (PRODUCT_ID);
CREATE INDEX SHIPMENT_ITEM_I_3 ON SHIPMENT_ITEM (SHIPMENT_ID);
CREATE INDEX SHIPMENT_ITEM_I_4 ON SHIPMENT_ITEM (CUSTOMER_ID);
CREATE INDEX SHIPMENT_ITEM_I_5 ON SHIPMENT_ITEM (PROJECT_ID);


-----------------------------------------------------------------------------
-- SERVICE_ITEM
-----------------------------------------------------------------------------
DROP TABLE SERVICE_ITEM;
DROP SEQUENCE SERVICE_ITEM_SEQ;

CREATE SEQUENCE SERVICE_ITEM_SEQ minvalue 1001;

CREATE TABLE SERVICE_ITEM
(
                            SERVICE_ITEM_ID integer DEFAULT nextval('SERVICE_ITEM_SEQ') NOT NULL,
                              -- REFERENCES SORDER (SORDER_ID)
    SORDER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    CUSTOMER_ID integer default 1000 NOT NULL,
                              -- REFERENCES CUSTOMER (CUSTOMER_ID)
    RECIPIENT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES SERVICE (SERVICE_ID)
    SERVICE_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            DESCRIPTION varchar (254) NOT NULL,
                            QUANTITY integer default 1 NOT NULL,
    PRIMARY KEY (SERVICE_ITEM_ID)
);
CREATE INDEX SERVICE_ITEM_I_1 ON SERVICE_ITEM (SORDER_ID);
CREATE INDEX SERVICE_ITEM_I_2 ON SERVICE_ITEM (PRODUCT_ID);
CREATE INDEX SERVICE_ITEM_I_3 ON SERVICE_ITEM (SERVICE_ID);
CREATE INDEX SERVICE_ITEM_I_4 ON SERVICE_ITEM (CUSTOMER_ID);
CREATE INDEX SERVICE_ITEM_I_5 ON SERVICE_ITEM (PROJECT_ID);


----------------------------------------------------------------------
-- SERVICE_ITEM                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- CREAM_USER                                                      
----------------------------------------------------------------------

ALTER TABLE NOTIFICATION
    ADD CONSTRAINT NOTIFICATION_FK_1 FOREIGN KEY (LANGUAGE_ID)
    REFERENCES LANGUAGE (LANGUAGE_ID)
;

----------------------------------------------------------------------
-- NOTIFICATION                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- PROJECT_CATEGORY                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- PRODUCT_CATEGORY                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- CUSTOMER_CATEGORY                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- COUNTRY                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- REGION                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- LANGUAGE                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- CURRENCY                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- CARRIER                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- VENDOR                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- UOM                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- EDUCATION_CATEGORY                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- HOUSEHOLD_CATEGORY                                                      
----------------------------------------------------------------------

ALTER TABLE CUSTOMER
    ADD CONSTRAINT CUSTOMER_FK_1 FOREIGN KEY (CUSTOMER_CAT_ID)
    REFERENCES CUSTOMER_CATEGORY (CUSTOMER_CAT_ID)
;
ALTER TABLE CUSTOMER
    ADD CONSTRAINT CUSTOMER_FK_2 FOREIGN KEY (COUNTRY_ID)
    REFERENCES COUNTRY (COUNTRY_ID)
;
ALTER TABLE CUSTOMER
    ADD CONSTRAINT CUSTOMER_FK_3 FOREIGN KEY (REGION_ID)
    REFERENCES REGION (REGION_ID)
;
ALTER TABLE CUSTOMER
    ADD CONSTRAINT CUSTOMER_FK_4 FOREIGN KEY (LANGUAGE_ID)
    REFERENCES LANGUAGE (LANGUAGE_ID)
;
ALTER TABLE CUSTOMER
    ADD CONSTRAINT CUSTOMER_FK_5 FOREIGN KEY (EDUCATION_CAT_ID)
    REFERENCES EDUCATION_CATEGORY (EDUCATION_CAT_ID)
;
ALTER TABLE CUSTOMER
    ADD CONSTRAINT CUSTOMER_FK_6 FOREIGN KEY (HOUSEHOLD_CAT_ID)
    REFERENCES HOUSEHOLD_CATEGORY (HOUSEHOLD_CAT_ID)
;

----------------------------------------------------------------------
-- CUSTOMER                                                      
----------------------------------------------------------------------

ALTER TABLE PRODUCT
    ADD CONSTRAINT PRODUCT_FK_1 FOREIGN KEY (PRODUCT_CAT_ID)
    REFERENCES PRODUCT_CATEGORY (PRODUCT_CAT_ID)
;
ALTER TABLE PRODUCT
    ADD CONSTRAINT PRODUCT_FK_2 FOREIGN KEY (UOM_ID)
    REFERENCES UOM (UOM_ID)
;
ALTER TABLE PRODUCT
    ADD CONSTRAINT PRODUCT_FK_3 FOREIGN KEY (VENDOR_ID)
    REFERENCES VENDOR (VENDOR_ID)
;

----------------------------------------------------------------------
-- PRODUCT                                                      
----------------------------------------------------------------------

ALTER TABLE PROJECT
    ADD CONSTRAINT PROJECT_FK_1 FOREIGN KEY (PROJECT_CAT_ID)
    REFERENCES PROJECT_CATEGORY (PROJECT_CAT_ID)
;

----------------------------------------------------------------------
-- PROJECT                                                      
----------------------------------------------------------------------

ALTER TABLE SORDER
    ADD CONSTRAINT SORDER_FK_1 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SORDER
    ADD CONSTRAINT SORDER_FK_2 FOREIGN KEY (RECIPIENT_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SORDER
    ADD CONSTRAINT SORDER_FK_3 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE SORDER
    ADD CONSTRAINT SORDER_FK_4 FOREIGN KEY (CARRIER_ID)
    REFERENCES CARRIER (CARRIER_ID)
;
ALTER TABLE SORDER
    ADD CONSTRAINT SORDER_FK_5 FOREIGN KEY (CURRENCY_ID)
    REFERENCES CURRENCY (CURRENCY_ID)
;

----------------------------------------------------------------------
-- SORDER                                                      
----------------------------------------------------------------------

ALTER TABLE PAYMENT
    ADD CONSTRAINT PAYMENT_FK_1 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE PAYMENT
    ADD CONSTRAINT PAYMENT_FK_2 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE PAYMENT
    ADD CONSTRAINT PAYMENT_FK_3 FOREIGN KEY (SORDER_ID)
    REFERENCES SORDER (SORDER_ID)
;
ALTER TABLE PAYMENT
    ADD CONSTRAINT PAYMENT_FK_4 FOREIGN KEY (CURRENCY_ID)
    REFERENCES CURRENCY (CURRENCY_ID)
;

----------------------------------------------------------------------
-- PAYMENT                                                      
----------------------------------------------------------------------

ALTER TABLE SERVICE
    ADD CONSTRAINT SERVICE_FK_1 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SERVICE
    ADD CONSTRAINT SERVICE_FK_2 FOREIGN KEY (RECIPIENT_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SERVICE
    ADD CONSTRAINT SERVICE_FK_3 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE SERVICE
    ADD CONSTRAINT SERVICE_FK_4 FOREIGN KEY (SORDER_ID)
    REFERENCES SORDER (SORDER_ID)
;

----------------------------------------------------------------------
-- SERVICE                                                      
----------------------------------------------------------------------

ALTER TABLE SHIPMENT
    ADD CONSTRAINT SHIPMENT_FK_1 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SHIPMENT
    ADD CONSTRAINT SHIPMENT_FK_2 FOREIGN KEY (RECIPIENT_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SHIPMENT
    ADD CONSTRAINT SHIPMENT_FK_3 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE SHIPMENT
    ADD CONSTRAINT SHIPMENT_FK_4 FOREIGN KEY (SORDER_ID)
    REFERENCES SORDER (SORDER_ID)
;
ALTER TABLE SHIPMENT
    ADD CONSTRAINT SHIPMENT_FK_5 FOREIGN KEY (CARRIER_ID)
    REFERENCES CARRIER (CARRIER_ID)
;

----------------------------------------------------------------------
-- SHIPMENT                                                      
----------------------------------------------------------------------

ALTER TABLE PRINT_SUBSCRIPTION
    ADD CONSTRAINT PRINT_SUBSCRIPTION_FK_1 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE PRINT_SUBSCRIPTION
    ADD CONSTRAINT PRINT_SUBSCRIPTION_FK_2 FOREIGN KEY (RECIPIENT_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE PRINT_SUBSCRIPTION
    ADD CONSTRAINT PRINT_SUBSCRIPTION_FK_3 FOREIGN KEY (CARRIER_ID)
    REFERENCES CARRIER (CARRIER_ID)
;
ALTER TABLE PRINT_SUBSCRIPTION
    ADD CONSTRAINT PRINT_SUBSCRIPTION_FK_4 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE PRINT_SUBSCRIPTION
    ADD CONSTRAINT PRINT_SUBSCRIPTION_FK_5 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;
ALTER TABLE PRINT_SUBSCRIPTION
    ADD CONSTRAINT PRINT_SUBSCRIPTION_FK_6 FOREIGN KEY (SORDER_ID)
    REFERENCES SORDER (SORDER_ID)
;

----------------------------------------------------------------------
-- PRINT_SUBSCRIPTION                                                      
----------------------------------------------------------------------

ALTER TABLE ONLINE_SUBSCRIPTION
    ADD CONSTRAINT ONLINE_SUBSCRIPTION_FK_1 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE ONLINE_SUBSCRIPTION
    ADD CONSTRAINT ONLINE_SUBSCRIPTION_FK_2 FOREIGN KEY (RECIPIENT_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE ONLINE_SUBSCRIPTION
    ADD CONSTRAINT ONLINE_SUBSCRIPTION_FK_3 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE ONLINE_SUBSCRIPTION
    ADD CONSTRAINT ONLINE_SUBSCRIPTION_FK_4 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;
ALTER TABLE ONLINE_SUBSCRIPTION
    ADD CONSTRAINT ONLINE_SUBSCRIPTION_FK_5 FOREIGN KEY (SORDER_ID)
    REFERENCES SORDER (SORDER_ID)
;

----------------------------------------------------------------------
-- ONLINE_SUBSCRIPTION                                                      
----------------------------------------------------------------------

ALTER TABLE INBOX_EVENT
    ADD CONSTRAINT INBOX_EVENT_FK_1 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE INBOX_EVENT
    ADD CONSTRAINT INBOX_EVENT_FK_2 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE INBOX_EVENT
    ADD CONSTRAINT INBOX_EVENT_FK_3 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;

----------------------------------------------------------------------
-- INBOX_EVENT                                                      
----------------------------------------------------------------------

ALTER TABLE INBOX_ATTACHMENT
    ADD CONSTRAINT INBOX_ATTACHMENT_FK_1 FOREIGN KEY (INBOX_EVENT_ID)
    REFERENCES INBOX_EVENT (INBOX_EVENT_ID)
    ON DELETE CASCADE 
;

----------------------------------------------------------------------
-- INBOX_ATTACHMENT                                                      
----------------------------------------------------------------------

ALTER TABLE OUTBOX_EVENT
    ADD CONSTRAINT OUTBOX_EVENT_FK_1 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE OUTBOX_EVENT
    ADD CONSTRAINT OUTBOX_EVENT_FK_2 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE OUTBOX_EVENT
    ADD CONSTRAINT OUTBOX_EVENT_FK_3 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;

----------------------------------------------------------------------
-- OUTBOX_EVENT                                                      
----------------------------------------------------------------------

ALTER TABLE NEWS_SUBSCRIPTION
    ADD CONSTRAINT NEWS_SUBSCRIPTION_FK_1 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE NEWS_SUBSCRIPTION
    ADD CONSTRAINT NEWS_SUBSCRIPTION_FK_2 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;

----------------------------------------------------------------------
-- NEWS_SUBSCRIPTION                                                      
----------------------------------------------------------------------

ALTER TABLE NEWSLETTER
    ADD CONSTRAINT NEWSLETTER_FK_1 FOREIGN KEY (CUSTOMER_CAT_ID)
    REFERENCES CUSTOMER_CATEGORY (CUSTOMER_CAT_ID)
;
ALTER TABLE NEWSLETTER
    ADD CONSTRAINT NEWSLETTER_FK_2 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE NEWSLETTER
    ADD CONSTRAINT NEWSLETTER_FK_3 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;
ALTER TABLE NEWSLETTER
    ADD CONSTRAINT NEWSLETTER_FK_4 FOREIGN KEY (REL_PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE NEWSLETTER
    ADD CONSTRAINT NEWSLETTER_FK_5 FOREIGN KEY (REL_PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;
ALTER TABLE NEWSLETTER
    ADD CONSTRAINT NEWSLETTER_FK_6 FOREIGN KEY (CUST_COUNTRY_ID)
    REFERENCES COUNTRY (COUNTRY_ID)
;
ALTER TABLE NEWSLETTER
    ADD CONSTRAINT NEWSLETTER_FK_7 FOREIGN KEY (CUST_LANGUAGE_ID)
    REFERENCES LANGUAGE (LANGUAGE_ID)
;
ALTER TABLE NEWSLETTER
    ADD CONSTRAINT NEWSLETTER_FK_8 FOREIGN KEY (LANGUAGE_ID)
    REFERENCES LANGUAGE (LANGUAGE_ID)
;

----------------------------------------------------------------------
-- NEWSLETTER                                                      
----------------------------------------------------------------------

ALTER TABLE SORDER_ITEM
    ADD CONSTRAINT SORDER_ITEM_FK_1 FOREIGN KEY (SORDER_ID)
    REFERENCES SORDER (SORDER_ID)
    ON DELETE CASCADE 
;
ALTER TABLE SORDER_ITEM
    ADD CONSTRAINT SORDER_ITEM_FK_2 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;
ALTER TABLE SORDER_ITEM
    ADD CONSTRAINT SORDER_ITEM_FK_3 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SORDER_ITEM
    ADD CONSTRAINT SORDER_ITEM_FK_4 FOREIGN KEY (RECIPIENT_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SORDER_ITEM
    ADD CONSTRAINT SORDER_ITEM_FK_5 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE SORDER_ITEM
    ADD CONSTRAINT SORDER_ITEM_FK_6 FOREIGN KEY (CURRENCY_ID)
    REFERENCES CURRENCY (CURRENCY_ID)
;

----------------------------------------------------------------------
-- SORDER_ITEM                                                      
----------------------------------------------------------------------

ALTER TABLE PAYMENT_ITEM
    ADD CONSTRAINT PAYMENT_ITEM_FK_1 FOREIGN KEY (PAYMENT_ID)
    REFERENCES PAYMENT (PAYMENT_ID)
    ON DELETE CASCADE 
;
ALTER TABLE PAYMENT_ITEM
    ADD CONSTRAINT PAYMENT_ITEM_FK_2 FOREIGN KEY (SORDER_ID)
    REFERENCES SORDER (SORDER_ID)
;
ALTER TABLE PAYMENT_ITEM
    ADD CONSTRAINT PAYMENT_ITEM_FK_3 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;
ALTER TABLE PAYMENT_ITEM
    ADD CONSTRAINT PAYMENT_ITEM_FK_4 FOREIGN KEY (CURRENCY_ID)
    REFERENCES CURRENCY (CURRENCY_ID)
;
ALTER TABLE PAYMENT_ITEM
    ADD CONSTRAINT PAYMENT_ITEM_FK_5 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE PAYMENT_ITEM
    ADD CONSTRAINT PAYMENT_ITEM_FK_6 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;

----------------------------------------------------------------------
-- PAYMENT_ITEM                                                      
----------------------------------------------------------------------

ALTER TABLE SHIPMENT_ITEM
    ADD CONSTRAINT SHIPMENT_ITEM_FK_1 FOREIGN KEY (SHIPMENT_ID)
    REFERENCES SHIPMENT (SHIPMENT_ID)
    ON DELETE CASCADE 
;
ALTER TABLE SHIPMENT_ITEM
    ADD CONSTRAINT SHIPMENT_ITEM_FK_2 FOREIGN KEY (SORDER_ID)
    REFERENCES SORDER (SORDER_ID)
;
ALTER TABLE SHIPMENT_ITEM
    ADD CONSTRAINT SHIPMENT_ITEM_FK_3 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;
ALTER TABLE SHIPMENT_ITEM
    ADD CONSTRAINT SHIPMENT_ITEM_FK_4 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SHIPMENT_ITEM
    ADD CONSTRAINT SHIPMENT_ITEM_FK_5 FOREIGN KEY (RECIPIENT_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SHIPMENT_ITEM
    ADD CONSTRAINT SHIPMENT_ITEM_FK_6 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;

----------------------------------------------------------------------
-- SHIPMENT_ITEM                                                      
----------------------------------------------------------------------

ALTER TABLE SERVICE_ITEM
    ADD CONSTRAINT SERVICE_ITEM_FK_1 FOREIGN KEY (SERVICE_ID)
    REFERENCES SERVICE (SERVICE_ID)
    ON DELETE CASCADE 
;
ALTER TABLE SERVICE_ITEM
    ADD CONSTRAINT SERVICE_ITEM_FK_2 FOREIGN KEY (SORDER_ID)
    REFERENCES SORDER (SORDER_ID)
;
ALTER TABLE SERVICE_ITEM
    ADD CONSTRAINT SERVICE_ITEM_FK_3 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;
ALTER TABLE SERVICE_ITEM
    ADD CONSTRAINT SERVICE_ITEM_FK_4 FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SERVICE_ITEM
    ADD CONSTRAINT SERVICE_ITEM_FK_5 FOREIGN KEY (RECIPIENT_ID)
    REFERENCES CUSTOMER (CUSTOMER_ID)
;
ALTER TABLE SERVICE_ITEM
    ADD CONSTRAINT SERVICE_ITEM_FK_6 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;


INSERT INTO TURBINE_GROUP (GROUP_ID,GROUP_NAME)
    VALUES (1,'global');
    

INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME)
    VALUES (1,'admin_users');
    

INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME)
    VALUES (2,'homepage_access');
    

INSERT INTO TURBINE_ROLE (ROLE_ID,ROLE_NAME)
    VALUES (1,'turbine_root');
    

INSERT INTO TURBINE_ROLE (ROLE_ID,ROLE_NAME)
    VALUES (2,'Site Administrator');
    

INSERT INTO TURBINE_ROLE_PERMISSION (ROLE_ID,PERMISSION_ID)
    VALUES (1,1);
    

INSERT INTO TURBINE_ROLE_PERMISSION (ROLE_ID,PERMISSION_ID)
    VALUES (1,2);
    

INSERT INTO TURBINE_USER (USER_ID,LOGIN_NAME,PASSWORD_VALUE,FIRST_NAME,LAST_NAME)
    VALUES (1,'admin','admn00','Administrator','Adminic');
    

INSERT INTO TURBINE_USER_GROUP_ROLE (USER_ID,GROUP_ID,ROLE_ID)
    VALUES (1,1,1);
    

INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1010,'INBOX_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1011,'INBOX_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1020,'OUTBOX_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1021,'OUTBOX_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1030,'NEWSLETTER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1031,'NEWSLETTER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1040,'ONLINE_SUBSCRIPTION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1041,'ONLINE_SUBSCRIPTION_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1050,'PRINT_SUBSCRIPTION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1051,'PRINT_SUBSCRIPTION_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1060,'SERVICE_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1061,'SERVICE_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1070,'SHIPMENT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1071,'SHIPMENT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1080,'PAYMENT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1081,'PAYMENT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1090,'SORDER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1091,'SORDER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1100,'CUSTOMER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1101,'CUSTOMER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1110,'PROJECT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1111,'PROJECT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1120,'PRODUCT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1121,'PRODUCT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1130,'CURRENCY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1131,'CURRENCY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1140,'CARRIER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1141,'CARRIER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1150,'LANGUAGE_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1151,'LANGUAGE_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1160,'COUNTRY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1161,'COUNTRY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1170,'REGION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1171,'REGION_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1180,'HOUSEHOLD_CATEGORY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1181,'HOUSEHOLD_CATEGORY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1190,'EDUCATION_CATEGORY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1191,'EDUCATION_CATEGORY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1200,'UOM_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1201,'UOM_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1210,'VENDOR_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1211,'VENDOR_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1220,'CUSTOMER_CATEGORY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1221,'CUSTOMER_CATEGORY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1230,'PROJECT_CATEGORY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1231,'PROJECT_CATEGORY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1240,'PRODUCT_CATEGORY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1241,'PRODUCT_CATEGORY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1250,'NOTIFICATION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1251,'NOTIFICATION_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1260,'TURBINE_USER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1261,'TURBINE_USER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1270,'TURBINE_ROLE_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1271,'TURBINE_ROLE_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1280,'NEWS_SUBSCRIPTION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1281,'NEWS_SUBSCRIPTION_MODIFY');

INSERT INTO TURBINE_SCHEDULED_JOB (SECOND, MINUTE, HOUR, WEEK_DAY, TASK) VALUES (0,7,0,-1, 'OnlineSubscriptionJob');
INSERT INTO TURBINE_SCHEDULED_JOB (SECOND, MINUTE, HOUR, WEEK_DAY, TASK) VALUES (0,5,-1,-1, 'Pop3Job');


INSERT INTO CREAM_USER VALUES (1,	'admin','Welcome Admin',1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000);

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
INSERT INTO EDUCATION_CATEGORY (EDUCATION_CAT_ID,EDUCATION_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO EDUCATION_CATEGORY (EDUCATION_CAT_ID,EDUCATION_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO HOUSEHOLD_CATEGORY (HOUSEHOLD_CAT_ID,HOUSEHOLD_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO HOUSEHOLD_CATEGORY (HOUSEHOLD_CAT_ID,HOUSEHOLD_CAT_NAME)
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

INSERT INTO CUSTOMER (CUSTOMER_ID,CUSTOMER_CODE, STATUS, PRIORITY, CUSTOMER_TYPE, CUSTOMER_CAT_ID, CUSTOMER_NAME_1, CUSTOMER_DISPLAY, COUNTRY_ID, EMAIL_FORMAT, LANGUAGE_ID, GENDER, EDUCATION_CAT_ID, HOUSEHOLD_CAT_ID, REGION_ID, SEND_NEWS, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---', 1, 30, 1, 1000, ' ---', ' ---', 1000, 10, 1000, 10, 1000, 1000, 1000, 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO CUSTOMER (CUSTOMER_ID,CUSTOMER_CODE, STATUS, PRIORITY, CUSTOMER_TYPE, CUSTOMER_CAT_ID, CUSTOMER_NAME_1, CUSTOMER_DISPLAY, COUNTRY_ID, EMAIL_FORMAT, LANGUAGE_ID, GENDER, EDUCATION_CAT_ID, HOUSEHOLD_CAT_ID, REGION_ID, SEND_NEWS, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (999,' (*)', 1, 30, 1, 1000, ' (*)', ' (*)', 1000, 10, 1000, 10, 1000, 1000, 1000, 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CODE, STATUS, PRIORITY, PRODUCT_TYPE, PRODUCT_CAT_ID, PRODUCT_DESCRIPTION, PRODUCT_DISPLAY, BASE_PRICE, UOM_ID, SHOW_ON_PRICELIST, VENDOR_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---', 1, 30, 1, 1000, ' ---', ' ---', 0, 1000, 0, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CODE, STATUS, PRIORITY, PRODUCT_TYPE, PRODUCT_CAT_ID, PRODUCT_DESCRIPTION, PRODUCT_DISPLAY, BASE_PRICE, UOM_ID, SHOW_ON_PRICELIST, VENDOR_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (999,' (*)', 1, 30, 1, 1000, ' (*)', ' (*)', 0, 1000, 0, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO PROJECT (PROJECT_ID,PROJECT_CODE, STATUS, PRIORITY, PROJECT_CAT_ID, PROJECT_NAME, START_DATE, EXPENSES, REVENUES, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---', 1, 30, 1000, ' ---', '1990-1-1', 0, 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO PROJECT (PROJECT_ID,PROJECT_CODE, STATUS, PRIORITY, PROJECT_CAT_ID, PROJECT_NAME, START_DATE, EXPENSES, REVENUES, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (999,' (*)', 1, 30, 1000, ' (*)', '1990-1-1', 0, 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO SORDER (SORDER_ID,SORDER_CODE, SUBJECT, STATUS, PRIORITY, ISSUED_DATE, CUSTOMER_ID, RECIPIENT_ID, PROJECT_ID, CARRIER_ID, PAY_TERM, PAY_METHOD, CURRENCY_ID, CURRENCY_AMOUNT, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---',' ---', 1, 30, '1990-1-1', 1000, 1000, 1000, 1000, 10, 10, 1000, 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO NOTIFICATION VALUES (1001,10, 1000, 10, 'Some subject', 'Some text');
INSERT INTO NOTIFICATION VALUES (1002,20, 1000, 10, 'Some subject', 'Some text');
INSERT INTO NOTIFICATION VALUES (1003,30, 1000, 10, 'Some subject', 'Some text');

grant all on cream_user to cream;
grant all on notification to cream;
grant all on region to cream;
grant all on education_category to cream;
grant all on household_category to cream;
grant all on product_category to cream;
grant all on uom to cream;
grant all on vendor to cream;
grant all on project_category to cream;
grant all on carrier to cream;
grant all on print_subscription to cream;
grant all on online_subscription to cream;
grant all on inbox_event to cream;
grant all on inbox_attachment to cream;
grant all on outbox_event to cream;
grant all on customer_category to cream;
grant all on country to cream;
grant all on news_subscription to cream;
grant all on newsletter to cream;
grant all on language to cream;
grant all on sorder_item to cream;
grant all on payment to cream;
grant all on currency to cream;
grant all on payment_item to cream;
grant all on shipment to cream;
grant all on shipment_item to cream;
grant all on service to cream;
grant all on sorder to cream;
grant all on product to cream;
grant all on customer to cream;
grant all on service_item to cream;
grant all on project to cream;
grant all on turbine_scheduled_job to cream;
grant all on turbine_role_permission to cream;
grant all on turbine_permission to cream;
grant all on turbine_group to cream;
grant all on turbine_user_group_role to cream;
grant all on turbine_role to cream;
grant all on turbine_user to cream;



