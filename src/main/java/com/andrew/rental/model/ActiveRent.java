package com.andrew.rental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "active_rent")
@Data
@RequiredArgsConstructor
@DynamicUpdate
public class ActiveRent {
    @Id
    @GeneratedValue
    private UUID id;

    private int duration;

    @JsonIgnore
    @Column(name = "car_id")
    private UUID carId;


    @JsonIgnore
    @Column(name = "client_id")
    private UUID clientId;

}