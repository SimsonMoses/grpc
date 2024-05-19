package com.learn.common;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstractChannelTest {

    protected ManagedChannel channel;

    @BeforeAll
    public void setUpChannel() {
        this.channel = ManagedChannelBuilder.forAddress("localhost", 5000)
                .usePlaintext()
                .build();
    }

    @AfterAll
    public void stopChannel() throws InterruptedException {
        this.channel.awaitTermination(5, TimeUnit.SECONDS);
    }

}

/* Channel are created here */