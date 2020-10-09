package com.andrew.rental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Car {
    @NonNull
    private UUID id;

    private String model;
    private String type;
    private int pricePerHour;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NonNull
    private UUID ownerId;
}
