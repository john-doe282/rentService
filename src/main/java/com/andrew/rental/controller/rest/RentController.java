package com.andrew.rental.controller.rest;

import com.andrew.rental.model.ActiveRent;
import com.andrew.rental.service.RentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rents")
public class RentController {
    @Autowired
    private RentService rentService;

    @GetMapping("/user/{id}")
    List<ActiveRent> getActiveRentsForUser(@PathVariable(name = "id") UUID userId) throws NotFoundException {
        return rentService.activeRentsForUserId(userId);
    }

    @PostMapping
    void rent(@Validated @RequestBody ActiveRent rent) throws IllegalAccessException, NotFoundException {
        rentService.rent(rent);
    }

    @GetMapping("{id}")
    ActiveRent getActiveRent(@PathVariable("id") UUID id) throws NotFoundException {
        return rentService.getActiveRentById(id);
    }

    @DeleteMapping("{id}")
    void closeRent(@PathVariable("id") UUID id) throws NotFoundException {
        rentService.closeRentById(id);
    }

}