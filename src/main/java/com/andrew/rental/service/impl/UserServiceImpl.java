package com.andrew.rental.service.impl;

import com.andrew.rental.model.User;
import com.andrew.rental.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private String baseUrl = System.getenv("USERS_URL") + ":8082/users";
    private final RestTemplate restTemplate = new RestTemplate();

    public UserServiceImpl() {
        String host = System.getenv("USERS_URL");
        if (!host.startsWith("http://")) {
            baseUrl = "http://" + host + ":8082/users";
        }
    }
    @Override
    public User getUserById(UUID id) {
        String requestUrl = baseUrl + "/" + id.toString();
        return restTemplate.getForObject(requestUrl, User.class);
    }
}
