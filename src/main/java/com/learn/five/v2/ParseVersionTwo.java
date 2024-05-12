package com.learn.five.v2;

import com.google.protobuf.InvalidProtocolBufferException;
import com.learn.grp.five.v2.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseVersionTwo {
    private static final Logger log = LoggerFactory.getLogger(ParseVersionTwo.class);

    public static void parseObject(byte[] bytes) throws InvalidProtocolBufferException {
        var laptop = Laptop.parseFrom(bytes);
        log.info("Brand: {}", laptop.getBrand());
        log.info("Model: {}", laptop.getModelNumber());
        log.info("Manufacturing Year: {}", laptop.getManufacturingYear());
        log.info("Type: {}", laptop.getType());
    }
}
