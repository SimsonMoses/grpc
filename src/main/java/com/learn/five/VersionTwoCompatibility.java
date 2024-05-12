package com.learn.five;

import com.google.protobuf.InvalidProtocolBufferException;
import com.learn.five.v1.ParseVersionOne;
import com.learn.five.v2.ParseVersionTwo;
import com.learn.five.v3.ParseVersionThree;
import com.learn.grp.five.v2.Laptop;
import com.learn.grp.five.v2.Type;

public class VersionTwoCompatibility {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        // New field "Type" is added
        Laptop laptop = Laptop.newBuilder().setBrand("Lenovo").setModelNumber("L390").setManufacturingYear(2019).setType(Type.OFFICE).build();
        ParseVersionOne.parseObject(laptop.toByteArray());
        ParseVersionTwo.parseObject(laptop.toByteArray());
        ParseVersionThree.parseObject(laptop.toByteArray());

    }
}
