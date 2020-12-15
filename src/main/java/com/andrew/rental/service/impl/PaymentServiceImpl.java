package com.andrew.rental.service.impl;

import com.andrew.rental.dto.PaymentDTO;
import com.andrew.rental.service.PaymentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final RestTemplate restTemplate = new RestTemplate();
    private String baseUrl = System.getenv("BANK_URL") + ":8081/payment";

    public PaymentServiceImpl() {
        String host = System.getenv("BANK_URL");
        if (!host.startsWith("http://")) {
            baseUrl = "http://" + host + ":8081/payment";
        }
    }

    private void performPostRequest(String url, Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        restTemplate.postForObject(url, entity, String.class);

    }

    @Override
    public void transaction(PaymentDTO paymentDTO) {
        Map<String, Object> paymentHMap = new HashMap<>();
        paymentHMap.put("senderId", paymentDTO.getSenderId());
        paymentHMap.put("receiverId", paymentDTO.getReceiverId());
        paymentHMap.put("amount", paymentDTO.getAmount());

        performPostRequest(baseUrl, paymentHMap);
    }
}
