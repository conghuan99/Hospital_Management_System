-- MA NGUON GIUA KY
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
----------------------------------------------------- USER -----------------------------------------------------
DROP USER U_QLBV_QUANTRIVIEN CASCADE;
/
CREATE USER U_QLBV_QUANTRIVIEN IDENTIFIED BY 1234;
/
GRANT CONNECT, RESOURCE, DBA TO U_QLBV_QUANTRIVIEN WITH ADMIN OPTION;
GRANT CREATE PROCEDURE TO U_QLBV_QUANTRIVIEN WITH ADMIN OPTION;
GRANT CREATE TABLE TO U_QLBV_QUANTRIVIEN WITH ADMIN OPTION;
GRANT CREATE USER, DROP USER, ALTER USER TO U_QLBV_QUANTRIVIEN CONTAINER = ALL;
 
SET SERVEROUTPUT ON;
/

----------------------------------------------------- ROLE -----------------------------------------------------
GRANT CREATE ROLE TO U_QLBV_QUANTRIVIEN CONTAINER = ALL;
/
GRANT DROP ANY ROLE TO U_QLBV_QUANTRIVIEN CONTAINER = ALL;
/
GRANT ALTER ANY ROLE TO U_QLBV_QUANTRIVIEN CONTAINER = ALL;
/

SET SERVEROUTPUT ON;
/
