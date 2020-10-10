package com.andrew.rental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private UUID id;

    private String model;
    private String type;
    private int pricePerHour;

    private Status status;

    private UUID ownerId;
}
