package com.andrew.rental.service.impl;

import com.andrew.rental.BankAccountServiceGrpc;
import com.andrew.rental.TransactionResponse;
import com.andrew.rental.dto.PaymentDTO;
import com.andrew.rental.service.PaymentService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.annotation.PreDestroy;

public class GrpcPaymentService implements PaymentService {
    private final ManagedChannel channel;
    private final BankAccountServiceGrpc.BankAccountServiceBlockingStub stub;

    public GrpcPaymentService() {
        channel = ManagedChannelBuilder.
                forAddress(System.getenv("BANK_URL"), 6567).
                usePlaintext().build();
        stub = BankAccountServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void transaction(PaymentDTO paymentDTO) {
        TransactionResponse response = stub.
                transaction(paymentDTO.toTransactionRequest());
    }

    @PreDestroy
    void close() {
        channel.shutdown();
    }
}
