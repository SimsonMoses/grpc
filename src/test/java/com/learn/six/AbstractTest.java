package com.learn.six;

import com.learn.common.AbstractChannelTest;
import com.learn.common.GrpcServer;
import com.learn.grpc.six.BankServiceGrpc;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractTest extends AbstractChannelTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    private final GrpcServer grpcServer = GrpcServer.create(new BankService());

    protected BankServiceGrpc.BankServiceStub stub;

    protected BankServiceGrpc.BankServiceBlockingStub blockingStub;

    @BeforeAll
    public void setUp() throws InterruptedException {
        log.info("Server Starting");
        this.grpcServer.start(); // starting the server
        Thread.sleep(5);
        log.info("Server Started");
        this.blockingStub = BankServiceGrpc.newBlockingStub(channel); // getting stub for client to make the request to the server
        this.stub = BankServiceGrpc.newStub(channel);
    }

    @AfterAll
    public void stop() throws InterruptedException {
        Thread.sleep(3000);
        this.grpcServer.stop();
    }


}
