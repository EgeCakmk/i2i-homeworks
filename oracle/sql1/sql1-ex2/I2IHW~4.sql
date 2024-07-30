--EX02 SELF JOIN

SELECT E1.employee_id,
       E2.employee_id
FROM employees E1
JOIN employees E2
ON E1.manager_id = E2.employee_id
ORDER BY 2;
