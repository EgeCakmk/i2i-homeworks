--EX02 GRADES WITH DECODE STRUCTURE 

SELECT JOB_ID,
       DECODE(JOB_ID, 
              'AD_PRES', 'A', 
              'ST_MAN', 'B', 
              'IT_PROG', 'C',
              'SA_REP', 'D',
              'ST_CLERK', 'E',
              '0') AS GRADE
FROM EMPLOYEES
GROUP BY JOB_ID;
