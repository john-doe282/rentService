package com.andrew.rental.service.impl;

import com.andrew.rental.dto.PaymentDTO;
import com.andrew.rental.model.*;
import com.andrew.rental.repository.ActiveRentRepository;
import com.andrew.rental.service.CarService;
import com.andrew.rental.service.PaymentService;
import com.andrew.rental.service.RentService;
import com.andrew.rental.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    private ActiveRentRepository activeRentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private PaymentService paymentService;


    private final double tax = 0.3;

    @Override
    public void rent(ActiveRent rent) throws IllegalAccessException, NotFoundException {
        UUID carId = rent.getCarId();
        UUID clientId = rent.getClientId();

        User client = userService.getUserById(clientId);
        Car car = carService.getCarById(carId);

        if (client.getRole() == Role.OWNER) {
            throw new IllegalAccessException("This user is not a client");
        }

        if (car.getStatus() != Status.AVAILABLE) {
            throw new IllegalAccessException("This car is not available");
        }

        UUID ownerId = car.getOwnerId();
        int duration = rent.getDuration();

        int amount = (int) (car.getPricePerHour() * duration * (1 - tax));

        PaymentDTO paymentDTO = PaymentDTO.builder()
                .senderId(clientId)
                .receiverId(ownerId)
                .amount(amount)
                .build();
        paymentService.transaction(paymentDTO);

        carService.setStatusById(carId, Status.RENTED);
        activeRentRepository.save(rent);

    }

    @Override
    public ActiveRent getActiveRentById(UUID id) throws NotFoundException {
        Optional<ActiveRent> rentOptional = activeRentRepository.findById(id);
        if (!rentOptional.isPresent()) {
            throw new NotFoundException("Rent does not exist");
        }

        return rentOptional.get();
    }

    @Override
    public void closeRentById(UUID id) throws NotFoundException {
        Optional<ActiveRent> activeRentOptional = activeRentRepository.findById(id);
        if (!activeRentOptional.isPresent()) {
            throw new NotFoundException("Rent does not exist");
        }

        UUID carId = activeRentOptional.get().getCarId();
        carService.setStatusById(carId, Status.AVAILABLE);

        activeRentRepository.deleteById(id);
    }

    @Override
    public List<ActiveRent> activeRentsForUserId(UUID id) throws NotFoundException {
        return activeRentRepository.findActiveRentsByClientId(id);
    }
}

