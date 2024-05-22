package com.learn.six;

import com.learn.grpc.six.TransferRequest;
import com.learn.grpc.six.TransferResponse;
import com.learn.grpc.six.TransferServiceGrpc;
import com.learn.six.requesthandler.TransferRequestHandler;
import io.grpc.stub.StreamObserver;

public class TransferService extends TransferServiceGrpc.TransferServiceImplBase {

    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {
        return new TransferRequestHandler(responseObserver);
    }
}
