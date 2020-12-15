package com.andrew.rental.model;

import com.andrew.rental.AddRentRequest;
import com.andrew.rental.GetRentResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "active_rent")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Builder
public class ActiveRent {
    @Id
    @GeneratedValue
    private UUID id;

    private int duration;

    @Column(name = "car_id")
    @JsonProperty("car_id")
    private UUID carId;


    @Column(name = "client_id")
    @JsonProperty("client_id")
    private UUID clientId;

    public GetRentResponse toGetRentResponse() {
        return GetRentResponse.newBuilder().
                setId(id.toString()).
                setDuration(duration).
                setCarId(carId.toString()).
                setClientId(clientId.toString()).
                build();
    }

    public static ActiveRent fromAddRequest (AddRentRequest rentRequest) {
        return new ActiveRentBuilder().
                duration(rentRequest.getDuration()).
                carId(UUID.fromString(rentRequest.getCarId())).
                clientId(UUID.fromString(rentRequest.getClientId())).
                build();
    }
}