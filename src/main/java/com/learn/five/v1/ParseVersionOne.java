package com.learn.five.v1;

import com.google.protobuf.InvalidProtocolBufferException;
import com.learn.grp.five.v1.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseVersionOne {
    private static final Logger log = LoggerFactory.getLogger(ParseVersionOne.class);

    public static void parseObject(byte[] bytes) throws InvalidProtocolBufferException {
        var laptop = Laptop.parseFrom(bytes);
        log.info("Brand: {}", laptop.getBrand());
        log.info("Model: {}", laptop.getModelNumber());
        log.info("Production Year: {}", laptop.getProductionYear());
        log.info("Unknown fields: {}", laptop.getUnknownFields());

    }
}
