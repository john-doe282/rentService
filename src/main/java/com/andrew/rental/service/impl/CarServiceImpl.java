package com.andrew.rental.service.impl;

import com.andrew.rental.model.Car;
import com.andrew.rental.model.Status;
import com.andrew.rental.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {
    private final String baseUrl = "http://localhost:8060/cars";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Car getCarById(UUID id) {
        String requestUrl = baseUrl + "/" + id.toString();
        return restTemplate.getForObject(requestUrl, Car.class);
    }

    @Override
    public void setStatusById(UUID id, Status status) {
        String requestUrl = baseUrl + "/" + id.toString();
        Map<String, Object> statusMap = new HashMap<>();
        statusMap.put("status", status);
        restTemplate.patchForObject(requestUrl, statusMap, String.class);
    }
}
