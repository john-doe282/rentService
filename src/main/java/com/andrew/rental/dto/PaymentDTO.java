package com.andrew.rental.dto;

import com.andrew.rental.TransactionRequest;
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

    public TransactionRequest toTransactionRequest() {
        return TransactionRequest.newBuilder().
                setSenderId(senderId.toString()).
                setReceiverId(receiverId.toString()).
                setAmount(amount).
                build();
    }
}
