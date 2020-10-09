package com.andrew.rental.service.impl;

import com.andrew.rental.model.User;
import com.andrew.rental.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(UUID id) {
        return null;
    }
}
