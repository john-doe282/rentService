package com.andrew.rental.service;

import com.andrew.rental.dto.PaymentDTO;

public interface PaymentService {
    void transaction(PaymentDTO paymentDTO);
}
