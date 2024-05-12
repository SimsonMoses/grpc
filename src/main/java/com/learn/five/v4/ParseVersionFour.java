package com.learn.five.v4;

import com.google.protobuf.InvalidProtocolBufferException;
import com.learn.grp.five.v4.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseVersionFour {
    private static final Logger log = LoggerFactory.getLogger(ParseVersionFour.class);

    public static void parseObject(byte[] bytes) throws InvalidProtocolBufferException {
        var laptop = Laptop.parseFrom(bytes);
        log.info("Brand: {}", laptop.getBrand());
        log.info("Model: {}", laptop.getModelNumber());
        log.info("Type: {}", laptop.getType());
        log.info("Four: {}", laptop.getPrice());
    }
}
