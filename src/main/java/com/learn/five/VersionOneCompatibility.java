package com.learn.five;

import com.google.protobuf.InvalidProtocolBufferException;
import com.learn.five.v1.ParseVersionOne;
import com.learn.five.v2.ParseVersionTwo;
import com.learn.grpc.five.v1.Laptop;

public class VersionOneCompatibility {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Laptop laptop = Laptop.newBuilder().setBrand("ASUS").setModelNumber("F15").setProductionYear(2021).build();
        ParseVersionOne.parseObject(laptop.toByteArray());
        ParseVersionTwo.parseObject(laptop.toByteArray()); // it's matching the number fields, we don't have the productionYear number field of production year mapped with the manufacturing year
        // it's all about the number fields we assigned in the proto field
    }
}
