package com.andrew.rental.controller.grpc;

import com.andrew.rental.*;
import com.andrew.rental.model.ActiveRent;
import com.andrew.rental.repository.ActiveRentRepository;
import com.andrew.rental.service.RentService;
import com.andrew.rental.service.impl.GrpcCarService;
import com.andrew.rental.service.impl.GrpcPaymentService;
import com.andrew.rental.service.impl.GrpcUserService;
import com.andrew.rental.service.impl.RentServiceImpl;
import io.grpc.stub.StreamObserver;
import javassist.NotFoundException;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GRpcService
public class GrpcRentController extends RentServiceGrpc.RentServiceImplBase {
    @Autowired
    private RentService rentService;

    @PostConstruct
    public void start() {
        rentService.setCarService(new GrpcCarService());
        rentService.setPaymentService(new GrpcPaymentService());
        rentService.setUserService(new GrpcUserService());
    }

    @Override
    public void allForUser(AllRentsForUserRequest request,
                           StreamObserver<AllRentsForUserResponse>
                                   responseObserver) throws NotFoundException {
        List<ActiveRent> rents = rentService.activeRentsForUserId(UUID.
                fromString(request.getClientId()));
        List<GetRentResponse> convertedRents = rents.stream().
                map(ActiveRent::toGetRentResponse).
                collect(Collectors.toList());

        AllRentsForUserResponse response = AllRentsForUserResponse.
                newBuilder().
                addAllRents(convertedRents).
                build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void get(GetRentRequest request,
                    StreamObserver<GetRentResponse> responseObserver)
            throws NotFoundException {
        ActiveRent rent = rentService.
                getActiveRentById(UUID.fromString(request.getRentId()));
        responseObserver.onNext(rent.toGetRentResponse());
        responseObserver.onCompleted();
    }

    @Override
    public void add(AddRentRequest request,
                    StreamObserver<AddRentResponse> responseObserver) throws NotFoundException, IllegalAccessException {
        ActiveRent rent = ActiveRent.fromAddRequest(request);
        rentService.rent(rent);
        responseObserver.onNext(AddRentResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void close(DeleteRentRequest request, StreamObserver<DeleteRentResponse> responseObserver) throws NotFoundException {
        rentService.closeRentById(UUID.fromString(request.getId()));
        responseObserver.onNext(DeleteRentResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
