package com.example.hiring_app1.service;

import com.example.hiring_app1.dto.WebhookRequest;
import com.example.hiring_app1.dto.WebhookResponse;
import com.example.hiring_app1.dto.SolutionRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WebhookService {
    
    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);
    private final WebClient webClient;
    
    public WebhookService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://bfhldevapigw.healthrx.co.in")
                .build();
    }
    
    public WebhookResponse generateWebhook(String name, String regNo, String email) {
        try {
            WebhookRequest request = new WebhookRequest(name, regNo, email);
            
            logger.info("Sending webhook generation request for regNo: {}", regNo);
            
            WebhookResponse response = webClient
                    .post()
                    .uri("/hiring/generateWebhook/JAVA")
                    .header("Content-Type", "application/json")
                    .body(BodyInserters.fromValue(request))
                    .retrieve()
                    .bodyToMono(WebhookResponse.class)
                    .block();
            
            logger.info("Received webhook response: {}", response != null ? "Success" : "Failed");
            return response;
            
        } catch (Exception e) {
            logger.error("Error generating webhook: ", e);
            throw new RuntimeException("Failed to generate webhook", e);
        }
    }
    
    public void submitSolution(String webhookUrl, String accessToken, String finalQuery) {
        try {
            SolutionRequest request = new SolutionRequest(finalQuery);
            
            logger.info("Submitting solution to webhook: {}", webhookUrl);
            
            String response = webClient
                    .post()
                    .uri("/hiring/testWebhook/JAVA")
                    .header("Authorization", accessToken)
                    .header("Content-Type", "application/json")
                    .body(BodyInserters.fromValue(request))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            
            logger.info("Solution submitted successfully. Response: {}", response);
            
        } catch (Exception e) {
            logger.error("Error submitting solution: ", e);
            throw new RuntimeException("Failed to submit solution", e);
        }
    }
}
