package com.learn.seven;

import com.learn.common.AbstractChannelTest;
import com.learn.common.GrpcServer;
import com.learn.grpc.seven.v1.FlowControlServiceGrpc;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class FlowControlTest extends AbstractChannelTest {

    private final GrpcServer grpcServer = GrpcServer.create(5001,new FlowControlService());
    private FlowControlServiceGrpc.FlowControlServiceStub flowControlServiceStub;

    @BeforeAll
    public void start(){
        this.grpcServer.start();
        this.flowControlServiceStub = FlowControlServiceGrpc.newStub(channel);
    }

    @AfterAll
    public void test(){
        this.grpcServer.stop();

    }
}
