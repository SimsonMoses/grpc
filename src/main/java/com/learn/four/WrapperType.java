package com.learn.four;

import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import com.learn.grpc.four.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class WrapperType {

    private static final Logger log = LoggerFactory.getLogger(WrapperType.class);
    public static void main(String[] args) {
        Sample sample = Sample.newBuilder()
                .setNumber(Int32Value.of(21))
                .setTime(Timestamp.newBuilder().setSeconds(Instant.now().getEpochSecond()).build())
                .build();
        log.info("sample: {}",sample);
        log.info("Seconds: {}",Instant.ofEpochSecond(sample.getTime().getSeconds()));
    }
}
