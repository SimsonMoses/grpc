package com.learn.seven;

import com.google.common.util.concurrent.Uninterruptibles;
import com.learn.grpc.seven.v1.Output;
import com.learn.grpc.seven.v1.RequestSize;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RequestHandler implements StreamObserver<Output> {

    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
    private final CountDownLatch latch = new CountDownLatch(1);
    private StreamObserver<RequestSize> requestSizeStreamObserver;
    private int size;

    @Override
    public void onNext(Output output) {
        this.size--;
        this.process(output);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        this.requestSizeStreamObserver.onCompleted();
        log.info("completed");
        latch.countDown();
    }

    public void setRequestObserver(StreamObserver<RequestSize> requestObserver){
        this.requestSizeStreamObserver = requestObserver;
    }

    private void request(int size){
        log.info("Request Size: ");
        this.size = size;
        requestSizeStreamObserver.onNext(RequestSize.newBuilder().setSize(this.size).build());
    }

    private void process(Output output){
        log.info("recieved: {}",output);
        Uninterruptibles.sleepUninterruptibly(
                ThreadLocalRandom.current().nextInt(50,200),
                TimeUnit.MILLISECONDS
        );
    }

    private void await(){
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(){
        this.request(3);
        log.info("Grpc server has been started");
    }
}
