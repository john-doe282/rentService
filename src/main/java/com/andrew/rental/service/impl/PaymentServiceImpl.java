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
    private final String baseUrl = "http://localhost:8050/payment";

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
