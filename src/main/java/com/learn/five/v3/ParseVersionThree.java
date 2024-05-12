package com.learn.five.v3;

import com.google.protobuf.InvalidProtocolBufferException;
import com.learn.grp.five.v3.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseVersionThree {
    private static final Logger log = LoggerFactory.getLogger(ParseVersionThree.class);

    public static void parseObject(byte[] bytes) throws InvalidProtocolBufferException {
        var laptop = Laptop.parseFrom(bytes);
        log.info("Brand: {}", laptop.getBrand());
        log.info("Model: {}", laptop.getModelNumber());
        log.info("Type: {}", laptop.getType());
    }
}
