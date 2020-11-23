package com.andrew.rental.service.impl;

import com.andrew.rental.*;
import com.andrew.rental.model.Car;
import com.andrew.rental.model.Status;
import com.andrew.rental.service.CarService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.annotation.PreDestroy;
import java.util.UUID;

public class GrpcCarService implements CarService {
    private final ManagedChannel channel;
    private final CarServiceGrpc.CarServiceBlockingStub stub;

    public GrpcCarService() {
        channel = ManagedChannelBuilder.forAddress(
                System.getenv("CARS_URL"), 6568).
                usePlaintext().build();
        stub = CarServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public Car getCarById(UUID id) {
        GetCarRequest request = GetCarRequest.newBuilder().
                setId(id.toString()).build();
        GetCarResponse response = stub.get(request);
        return Car.fromGetCarResponse(response);
    }

    @Override
    public void setStatusById(UUID id, Status status) {
        SetStatusRequest request = SetStatusRequest.newBuilder().
                setId(id.toString()).
                setStatus(com.andrew.rental.Status.valueOf(status.toString())).
                build();
        SetStatusResponse response = stub.setStatus(request);
    }

    @PreDestroy
    public void close() {
        channel.shutdown();
    }
}
