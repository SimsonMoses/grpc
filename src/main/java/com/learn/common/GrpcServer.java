package com.learn.common;

import com.learn.six.BankService;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        var server = ServerBuilder.forPort(5000).addService(new BankService()).build();
        server.start();
        server.awaitTermination();
    }
}
