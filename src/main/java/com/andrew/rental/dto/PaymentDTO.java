package com.andrew.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private UUID senderId;
    private UUID receiverId;
    private int amount;
}
