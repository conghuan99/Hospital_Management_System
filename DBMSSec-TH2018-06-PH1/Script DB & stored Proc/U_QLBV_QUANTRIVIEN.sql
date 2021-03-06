ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
/
SET DEFINE ON
/

DROP TABLE NHANVIEN;
/
------------------------- CREATE TABLE ------------------------
CREATE TABLE NHANVIEN (
    MANV      VARCHAR2(5) NOT NULL,
    HONV      VARCHAR2(10),
    TENLOT    VARCHAR2(10),
    TENNV     VARCHAR2(10),
    GIOITINH  VARCHAR2(3),
    LUONG     NUMBER(10, 2),
    DIACHI    VARCHAR2(30),
    NGAYSINH  DATE,
    MANQL     VARCHAR2(5),
    PHG       VARCHAR2(5),
    CONSTRAINT PK_NHANVIEN PRIMARY KEY ( MANV )
)
/

------------------------- TAO + GRANT PRIVILEGE CHO USER, ROLE MAU -------------------------

DROP ROLE R_QLBV_TIEPTAN;
/

CREATE ROLE R_QLBV_TIEPTAN;
/

DROP USER U_QLBV_TIEPTAN1 CASCADE;
/

CREATE USER U_QLBV_TIEPTAN1 IDENTIFIED BY 1234;
/

GRANT SELECT, UPDATE ON NHANVIEN TO R_QLBV_TIEPTAN;
GRANT INSERT(MANV, PHG) ON NHANVIEN TO R_QLBV_TIEPTAN;
/

GRANT CREATE TABLE TO U_QLBV_TIEPTAN1;
GRANT CONNECT TO U_QLBV_TIEPTAN1;
GRANT UPDATE(MANV, TENNV) ON  U_QLBV_QUANTRIVIEN.NHANVIEN TO U_QLBV_TIEPTAN1;
GRANT INSERT(MANV) ON  U_QLBV_QUANTRIVIEN.NHANVIEN TO U_QLBV_TIEPTAN1;

GRANT DELETE ON  U_QLBV_QUANTRIVIEN.NHANVIEN TO U_QLBV_TIEPTAN1;
/
GRANT R_QLBV_TIEPTAN TO U_QLBV_TIEPTAN1;
/

-------------------------------------------------- TAO PROCEDURE --------------------------------------------------
------------------------- CREATE + UPDATE + DROP USER ------------------------
CREATE OR REPLACE PROCEDURE SP_CREATE_USER(USERNAME VARCHAR2, PASSWORD VARCHAR2)
    AS
    BEGIN
    EXECUTE IMMEDIATE ('CREATE USER U_QLBV_' || USERNAME ||' IDENTIFIED BY '||PASSWORD);
    END;
/

CREATE OR REPLACE PROCEDURE SP_UPDATE_USER(USERNAME VARCHAR2, NEWPASSWORD VARCHAR2)
    AS
    BEGIN
    EXECUTE IMMEDIATE ('ALTER USER ' || USERNAME ||' IDENTIFIED BY '||NEWPASSWORD);
    END;
/

CREATE OR REPLACE PROCEDURE SP_DROP_USER(USERNAME VARCHAR2)
AS
BEGIN
    EXECUTE IMMEDIATE ('DROP USER ' || USERNAME); 
END;
/

------------------------- CREATE + UPDATE + DROP ROLE ------------------------
-- SELECT ROLE_ID, ROLE FROM DBA_ROLES WHERE ROLE LIKE '%QLBV%';
-- TAO ROLE CO PASSWORD
CREATE OR REPLACE PROCEDURE SP_CREATE_ROLE(ROLENAME VARCHAR2, PASSWORD VARCHAR2)
    AS
    BEGIN
    EXECUTE IMMEDIATE ('CREATE ROLE R_QLBV_' || ROLENAME ||' IDENTIFIED BY '||PASSWORD);
    END;
/
-- TAO ROLE KHONG CO PASSWORD
CREATE OR REPLACE PROCEDURE SP_CREATE_ROLE_NO_PASS(ROLENAME VARCHAR2)
    AS
    BEGIN
    EXECUTE IMMEDIATE ('CREATE ROLE R_QLBV_' || ROLENAME);
    END;
/

CREATE OR REPLACE PROCEDURE SP_UPDATE_ROLE(ROLENAME VARCHAR2, NEWPASSWORD VARCHAR2)
    AS
    BEGIN
    EXECUTE IMMEDIATE ('ALTER ROLE ' || ROLENAME ||' IDENTIFIED BY '||NEWPASSWORD);
    END;
/
CREATE OR REPLACE PROCEDURE SP_DROP_ROLE(ROLENAME VARCHAR2)
    AS
    BEGIN
    EXECUTE IMMEDIATE ('DROP ROLE ' || ROLENAME); 
    END;
/

------------------------- GRANT PRIVILEGE HOAC ROLE --->> USER/ROLE ------------------------
DROP PROCEDURE SP_GRANT_PR_TO_UR;
CREATE OR REPLACE PROCEDURE SP_GRANT_PR_TO_UR(PRIVILEGE_ROLE VARCHAR2, USER_ROLE VARCHAR2)
    AS
    BEGIN
    EXECUTE IMMEDIATE ('GRANT ' || PRIVILEGE_ROLE ||' TO '|| USER_ROLE);
    END;
/
DECLARE
A VARCHAR(40) := 'CREATE TABLE';
B VARCHAR(40) := 'U_QLBV_TIEPTAN1';
BEGIN
    SP_GRANT_PR_TO_UR(A, B);
END;
/

------------------------- REVOKE PRIVILEGE CUA ROLE HOAC USER ------------------------
CREATE OR REPLACE PROCEDURE SP_REVOKE_SYS_PRIVS (USERROLENAME VARCHAR2, SYS_PRIVS VARCHAR2)
    AS
    BEGIN
    EXECUTE IMMEDIATE ('REVOKE ' || SYS_PRIVS ||' FROM '||USERROLENAME);
    END;
/
CREATE OR REPLACE PROCEDURE SP_REVOKE_OBJECT_PRIVS(USERROLENAME VARCHAR2, OBJECTNAME VARCHAR2, PRIVSNAME VARCHAR2)
    AS
    BEGIN
    EXECUTE IMMEDIATE ('REVOKE ' || PRIVSNAME || ' ON U_QLBV_QUANTRIVIEN.'||OBJECTNAME||' FROM '||USERROLENAME); 
    END;
/

CREATE OR REPLACE PROCEDURE SP_REVOKE_ROLE_PRIVS (USERROLENAME VARCHAR2, ROLE_NAME VARCHAR2)
    AS
    BEGIN
    EXECUTE IMMEDIATE ('REVOKE ' || ROLE_NAME ||' FROM '||USERROLENAME); 
    END;
/




------------------------- TEST REVOKE PRIVILEGE TREN BANG NHAN VIEN -------------------------
--REVOKE INSERT ON U_QLBV_QUANTRIVIEN.NHANVIEN FROM U_QLBV_TIEPTAN1;
--REVOKE DELETE ON U_QLBV_QUANTRIVIEN.NHANVIEN FROM U_QLBV_TIEPTAN1;
--REVOKE SELECT ON U_QLBV_QUANTRIVIEN.NHANVIEN FROM R_QLBV_TIEPTAN;
--REVOKE CREATE TABLE FROM U_QLBV_TIEPTAN1;

------------------------- LOAD DATA LEN COMBOBOX -------------------------
-- XEM DANH SACH USER TRONG HE THONG
SELECT DISTINCT USERNAME FROM DBA_USERS WHERE USERNAME LIKE ('%QLBV%');
-- XEM DANH SACH ROLE TRONG HE THONG 
SELECT DISTINCT ROLE FROM DBA_ROLES WHERE ROLE LIKE ('%QLBV%');
/
------------------------- LOAD DATA LEN TABLE2 OBJECT PRIVS (COT COLUMN_NAME DE NULL) -------------------
-- XEM QUYEN CUA C?C USER/ROLE TR?N C?C DOI TUONG
SELECT GRANTEE, TABLE_NAME, PRIVILEGE FROM DBA_TAB_PRIVS WHERE GRANTEE LIKE '%QLBV%';

/
------------------------- LOAD DATA LEN TABLE2 OBJECT PRIVS -------------------------
-- XEM QUYEN CUA C?C USER/ROLE TR?N C?C DOI TUONG DEN MUC COT 
SELECT GRANTEE, TABLE_NAME, COLUMN_NAME, PRIVILEGE FROM DBA_COL_PRIVS WHERE GRANTEE LIKE '%QLBV%';
/

--XEM QUYEN TREN BANG DUOC CAP CHO CAC ROLE
------------------------- LOAD DATA OBJECT PRIVS CUA ROLE LEN TABLE -------------------------
SELECT ROLE, TABLE_NAME, COLUMN_NAME, PRIVILEGE FROM ROLE_TAB_PRIVS WHERE ROLE LIKE '%QLBV%';

------------------------- LOAD DATA LEN TABLE3 ROLE -------------------------
--XEM CAC ROLE DUOC GAN CHO USER/ROLE
SELECT GRANTEE, GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE LIKE '%QLBV%';
SELECT * FROM DBA_ROLE_PRIVS WHERE GRANTEE LIKE '%QLBV%' AND GRANTED_ROLE LIKE '%QLBV%';
------------------------- LOAD DATA LEN TABLE1 SYS -------------------------

------------------------- KIEM TRA SYS PRIVS -------------------------
SELECT GRANTEE, PRIVILEGE FROM DBA_SYS_PRIVS WHERE GRANTEE LIKE '%QLBV%';
SELECT * FROM DBA_SYS_PRIVS WHERE GRANTEE LIKE '%QLBV%';

/*
REVOKE ALL ON U_QLBV_QUANTRIVIEN.NHANVIEN FROM U_QLBV_TIEPTAN1;
/

--1. XEM DANH SACH USER TRONG HE THONG
SELECT USERNAME FROM DBA_USERS WHERE USERNAME LIKE ('%QLBV%');
/
--SELECT * FROM USER_ROLE_PRIVS WHERE USERNAME='%QLBV%';
--2. XEM QUYEN CUA CAC USER/ROLE TREN CAC ??I T??NG
SELECT * FROM DBA_TAB_PRIVS WHERE GRANTEE LIKE '%QLBV%';

SELECT * FROM DBA_ROLE_PRIVS WHERE GRANTEE LIKE '%QLBV%';
--SELECT * FROM USER_SYS_PRIVS WHERE USERNAME = '%QLBV%';*/ * /


