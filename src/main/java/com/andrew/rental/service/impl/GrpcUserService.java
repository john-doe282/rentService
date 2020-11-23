package com.andrew.rental.service.impl;

import com.andrew.rental.GetRequest;
import com.andrew.rental.UserServiceGrpc;
import com.andrew.rental.UsersShort;
import com.andrew.rental.model.User;
import com.andrew.rental.service.UserService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.annotation.PreDestroy;
import java.util.UUID;

public class GrpcUserService implements UserService {
    private final ManagedChannel channel;
    private final UserServiceGrpc.UserServiceBlockingStub stub;

    public GrpcUserService() {
        channel = ManagedChannelBuilder.forAddress(System.
                getenv("USERS_URL"), 6556).
                usePlaintext().build();
        stub = UserServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public User getUserById(UUID id) {
        GetRequest request = GetRequest.newBuilder().
                setId(id.toString()).build();
        UsersShort response = stub.shortGet(request);
        return User.fromUsersShort(response);
    }

    @PreDestroy
    public void close() {
        channel.shutdown();
    }
}
