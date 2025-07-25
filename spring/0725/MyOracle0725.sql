drop table users cascade constraints;
drop table address cascade constraints;
drop table geo cascade constraints;
drop table company cascade constraints;
CREATE TABLE USERS (
  ID NUMBER PRIMARY KEY,
  NAME VARCHAR2(100),
  USERNAME VARCHAR2(100),
  EMAIL VARCHAR2(100),
  PHONE VARCHAR2(50),
  WEBSITE VARCHAR2(100)
);

CREATE TABLE ADDRESS (
  ID NUMBER PRIMARY KEY,
  USER_ID NUMBER REFERENCES USERS(ID),
  STREET VARCHAR2(100),
  SUITE VARCHAR2(100),
  CITY VARCHAR2(100),
  ZIPCODE VARCHAR2(20)
);

CREATE TABLE GEO (
  ID NUMBER PRIMARY KEY,
  ADDRESS_ID NUMBER REFERENCES ADDRESS(ID),
  LAT VARCHAR2(20),
  LNG VARCHAR2(20)
);

CREATE TABLE COMPANY (
  ID NUMBER PRIMARY KEY,
  USER_ID NUMBER REFERENCES USERS(ID),
  NAME VARCHAR2(100),
  CATCH_PHRASE VARCHAR2(255),
  BS VARCHAR2(255)
);

create sequence geo_seq start with 1 increment by 1;
create sequence address_seq start with 1 increment by 1;
create sequence company_seq start with 1 increment by 1;

drop sequence geo_seq;
drop sequence address_seq;
drop sequence company_seq;

select * from users;
select * from address;
select * from geo;
select * from company;

delete from users;
delete from address;
delete from geo;
delete from company;
commit;
CREATE TABLE USERS (
  ID NUMBER PRIMARY KEY,
  NAME VARCHAR2(100),
  USERNAME VARCHAR2(100),
  EMAIL VARCHAR2(100),
  PHONE VARCHAR2(50),
  WEBSITE VARCHAR2(100),
  ADDRESS_ID NUMBER REFERENCES ADDRESS(ID),
  COMPANY_ID NUMBER REFERENCES COMPANY(ID)
);

CREATE TABLE ADDRESS (
  ID NUMBER PRIMARY KEY,
  STREET VARCHAR2(100),
  SUITE VARCHAR2(100),
  CITY VARCHAR2(100),
  ZIPCODE VARCHAR2(20),
  GEO_ID NUMBER REFERENCES GEO(ID)
);

CREATE TABLE GEO (
  ID NUMBER PRIMARY KEY,
  LAT VARCHAR2(20),
  LNG VARCHAR2(20)
);

CREATE TABLE COMPANY (
  ID NUMBER PRIMARY KEY,
  NAME VARCHAR2(100),
  CATCH_PHRASE VARCHAR2(255),
  BS VARCHAR2(255)
);