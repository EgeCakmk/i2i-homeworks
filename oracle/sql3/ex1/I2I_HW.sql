--ex01

--ADD COLUMN
ALTER TABLE EMPLOYEES
ADD MAX_SALARY NUMBER;

--UPDATE
UPDATE EMPLOYEES
SET MAX_SALARY=
(SELECT MAX(SALARY) FROM EMPLOYEES);

--DELETE 
DELETE FROM EMPLOYEES
WHERE SALARY=
(SELECT MIN(SALARY) FROM EMPLOYEES);
