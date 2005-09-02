-----------------------------------------------------------------------------
-- INBOX_EVENT
-----------------------------------------------------------------------------
ALTER TABLE INBOX_EVENT DROP COLUMN SENDER;
ALTER TABLE INBOX_EVENT DROP COLUMN RECEIVED_TIME;

ALTER TABLE INBOX_EVENT ADD COLUMN SENDER_EMAIL varchar (254);
ALTER TABLE INBOX_EVENT ADD COLUMN SENDER_NAME varchar (254);
ALTER TABLE INBOX_EVENT ADD COLUMN SENDER_REPLY_TO varchar (254);
ALTER TABLE INBOX_EVENT ADD COLUMN SENDER_TO text;
ALTER TABLE INBOX_EVENT ADD COLUMN SENDER_CC text;

-----------------------------------------------------------------------------
-- OUTBOX_EVENT
-----------------------------------------------------------------------------

ALTER TABLE OUTBOX_EVENT DROP COLUMN RECEIVER;
ALTER TABLE OUTBOX_EVENT ADD COLUMN RECEIVER_TO text;
ALTER TABLE OUTBOX_EVENT ADD COLUMN RECEIVER_CC text;


-----------------------------------------------------------------------------
-- NEWSLETTER
-----------------------------------------------------------------------------

ALTER TABLE NEWSLETTER ADD COLUMN REL_DOCUMENT integer;
ALTER TABLE NEWSLETTER ADD COLUMN REL_DOC_STATUS integer;
 
ALTER TABLE NEWSLETTER ALTER COLUMN REL_DOCUMENT SET DEFAULT  10;
ALTER TABLE NEWSLETTER ALTER COLUMN REL_DOC_STATUS SET DEFAULT  1;

UPDATE NEWSLETTER SET REL_DOCUMENT=20;
UPDATE NEWSLETTER SET REL_DOC_STATUS=1;

ALTER TABLE NEWSLETTER ALTER COLUMN REL_DOCUMENT SET NOT NULL;
ALTER TABLE NEWSLETTER ALTER COLUMN REL_DOC_STATUS SET NOT NULL;


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


ALTER TABLE INBOX_ATTACHMENT
    ADD CONSTRAINT INBOX_ATTACHMENT_FK_1 FOREIGN KEY (INBOX_EVENT_ID)
    REFERENCES INBOX_EVENT (INBOX_EVENT_ID)
    ON DELETE CASCADE 
;


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


ALTER TABLE NEWS_SUBSCRIPTION
    ADD CONSTRAINT NEWS_SUBSCRIPTION_FK_1 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE NEWS_SUBSCRIPTION
    ADD CONSTRAINT NEWS_SUBSCRIPTION_FK_2 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;

-----------------------------------------------------------------------------
-- CREAM_USER
-----------------------------------------------------------------------------

ALTER TABLE CREAM_USER ADD COLUMN NEWS_SUBS_FID integer;
ALTER TABLE CREAM_USER ALTER COLUMN NEWS_SUBS_FID SET DEFAULT  1000;
UPDATE CREAM_USER SET NEWS_SUBS_FID=1000;
ALTER TABLE CREAM_USER ALTER COLUMN NEWS_SUBS_FID SET NOT NULL;
ALTER TABLE CREAM_USER ALTER COLUMN DEFAULT_LIST SET DEFAULT  1000;


----------------------------------------------------------------------
-- NEWS_SUBSCRIPTION                                                      
----------------------------------------------------------------------

INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1280,'NEWS_SUBSCRIPTION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1281,'NEWS_SUBSCRIPTION_MODIFY');

INSERT INTO TURBINE_SCHEDULED_JOB (SECOND, MINUTE, HOUR, WEEK_DAY, TASK) VALUES (0,5,-1,-1, 'Pop3Job');
grant all on inbox_attachment to cream;


grant all on inbox_attachment to cream;
grant all on news_subscription to cream;

