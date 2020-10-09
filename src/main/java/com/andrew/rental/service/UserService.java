package com.andrew.rental.service;

import com.andrew.rental.model.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
