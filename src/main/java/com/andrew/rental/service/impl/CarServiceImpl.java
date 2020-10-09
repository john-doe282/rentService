package com.andrew.rental.service.impl;

import com.andrew.rental.model.Car;
import com.andrew.rental.service.CarService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {
    @Override
    public Car getCarById(UUID id) {
        return null;
    }
}
