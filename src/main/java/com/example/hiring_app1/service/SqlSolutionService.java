package com.example.hiring_app1.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SqlSolutionService {
    
    private static final Logger logger = LoggerFactory.getLogger(SqlSolutionService.class);
    
    public String solveSqlProblem(String regNo) {
        // Extract last two digits from registration number
        String lastTwoDigits = regNo.substring(regNo.length() - 2);
        int lastTwoDigitsInt = Integer.parseInt(lastTwoDigits);
        
        logger.info("Registration number: {}, Last two digits: {}", regNo, lastTwoDigits);
        
        String sqlQuery;
        
        if (lastTwoDigitsInt % 2 == 1) { // Odd
            logger.info("Solving Question 1 (Odd case)");
            sqlQuery = solveQuestion1();
        } else { // Even
            logger.info("Solving Question 2 (Even case)");
            sqlQuery = solveQuestion2();
        }
        
        logger.info("Generated SQL Query: {}", sqlQuery);
        return sqlQuery;
    }
    
    private String solveQuestion1() {
        // Example SQL for Question 1 (Odd case)
        // Since we can't access the actual questions, I'm providing common SQL scenarios
        // This should be replaced with the actual solution based on the question
        return """
                SELECT 
                    e.employee_id,
                    e.first_name,
                    e.last_name,
                    d.department_name,
                    e.salary
                FROM employees e
                INNER JOIN departments d ON e.department_id = d.department_id
                WHERE e.salary > (
                    SELECT AVG(salary) 
                    FROM employees 
                    WHERE department_id = e.department_id
                )
                ORDER BY e.salary DESC;
                """;
    }
    
    private String solveQuestion2() {
        // Example SQL for Question 2 (Even case)
        // Since we can't access the actual questions, I'm providing common SQL scenarios
        // This should be replaced with the actual solution based on the question
        return """
                SELECT 
                    c.customer_id,
                    c.customer_name,
                    COUNT(o.order_id) as total_orders,
                    SUM(o.order_amount) as total_amount
                FROM customers c
                LEFT JOIN orders o ON c.customer_id = o.customer_id
                WHERE o.order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
                GROUP BY c.customer_id, c.customer_name
                HAVING COUNT(o.order_id) > 5
                ORDER BY total_amount DESC;
                """;
    }
}
