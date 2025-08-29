package com.example.hiring_app1.runner;

import com.example.hiring_app1.dto.WebhookResponse;
import com.example.hiring_app1.service.WebhookService;
import com.example.hiring_app1.service.SqlSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class HiringAppRunner implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(HiringAppRunner.class);
    
    @Autowired
    private WebhookService webhookService;
    
    @Autowired
    private SqlSolutionService sqlSolutionService;
    
    // User details - you can modify these as needed
    private static final String USER_NAME = "Karnena Shanmukha sai";
    private static final String REG_NO = "22BCE5011";
    private static final String EMAIL = "shanmukhsaikarnena@gmail.com";
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting Hiring App workflow...");
        
        try {
            // Step 1: Generate webhook
            logger.info("Step 1: Generating webhook...");
            WebhookResponse webhookResponse = webhookService.generateWebhook(USER_NAME, REG_NO, EMAIL);
            
            if (webhookResponse == null) {
                logger.error("Failed to get webhook response");
                return;
            }
            
            logger.info("Webhook generated successfully");
            logger.info("Webhook URL: {}", webhookResponse.getWebhook());
            
            // Step 2: Solve SQL problem based on regNo
            logger.info("Step 2: Solving SQL problem...");
            String sqlSolution = sqlSolutionService.solveSqlProblem(REG_NO);
            
            // Step 3: Submit solution
            logger.info("Step 3: Submitting solution...");
            webhookService.submitSolution(
                webhookResponse.getWebhook(), 
                webhookResponse.getAccessToken(), 
                sqlSolution
            );
            
            logger.info("Hiring App workflow completed successfully!");
            
        } catch (Exception e) {
            logger.error("Error in hiring app workflow: ", e);
        }
    }
}
