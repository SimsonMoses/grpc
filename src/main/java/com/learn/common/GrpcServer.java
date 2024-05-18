package com.learn.common;

import com.learn.six.BankService;
import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class GrpcServer {
//    public static void main(String[] args) throws InterruptedException, IOException {
//        var server = ServerBuilder.forPort(5000).addService(new BankService()).build();
//        server.start();
//        server.awaitTermination();
//    }

    private static final Logger log = LoggerFactory.getLogger(GrpcServer.class);
    private final Server server;


    private GrpcServer(Server server) {
        this.server = server;
    }

    public static GrpcServer create(BindableService... bindableServices) {
        return create(5000, bindableServices);
    }

    public static GrpcServer create(int port, BindableService... bindableServices) {
        var builder = ServerBuilder.forPort(port);
        Arrays.asList(bindableServices).forEach(builder::addService);
        return new GrpcServer(builder.build());
    }

    public GrpcServer start() {
        var service = server.getServices()
                .stream()
                .map(ServerServiceDefinition::getServiceDescriptor)
                .map(ServiceDescriptor::getName)
                .toList();

        try {
            server.start();
            log.info("GRPC Server start listening on port: {}, service:{}", server.getPort(), service);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void await() {
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        server.shutdown();
        log.info("GRPC Server has stopped");
    }

}
