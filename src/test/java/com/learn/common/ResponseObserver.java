package com.learn.common;

import io.grpc.stub.StreamObserver;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ResponseObserver<T> implements StreamObserver<T> {

    private static final Logger log = LoggerFactory.getLogger(ResponseObserver.class);
    @Getter
    private final List<T> list = new ArrayList<>();
    private final CountDownLatch latch;
    @Getter
    private Throwable throwable;

    public ResponseObserver(int count) {
        this.latch = new CountDownLatch(count);
    }

    public static <T> ResponseObserver<T> create(){
        return new ResponseObserver<>(1);
    }

    public static <T> ResponseObserver<T> create(int count){
        return new ResponseObserver<>(count);
    }
    @Override
    public void onNext(T t) {
        log.info("Received {} of list: {}",t.getClass(),t);
        this.list.add(t);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("Received Error: {}",throwable.getMessage());
        this.throwable = throwable;
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        log.info("completed");
        this.latch.countDown();
    }

    public void await(){
        try {
            latch.await(5, TimeUnit.SECONDS);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
