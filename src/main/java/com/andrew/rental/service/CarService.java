package com.andrew.rental.service;

import com.andrew.rental.model.Car;
import com.andrew.rental.model.Status;

import java.util.UUID;

public interface CarService {
    Car getCarById(UUID id);
    void setStatusById(UUID id, Status status);
}
