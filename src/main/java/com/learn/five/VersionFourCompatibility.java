package com.learn.five;

import com.google.protobuf.InvalidProtocolBufferException;
import com.learn.five.v1.ParseVersionOne;
import com.learn.five.v2.ParseVersionTwo;
import com.learn.five.v3.ParseVersionThree;
import com.learn.five.v4.ParseVersionFour;
import com.learn.grp.five.v4.Laptop;
import com.learn.grp.five.v4.Type;

public class VersionFourCompatibility {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        // removed fields is being reserved
        Laptop laptop = Laptop.newBuilder().setBrand("Lenovo").setModelNumber("L390").setType(Type.OFFICE).setPrice(60000).build();
        ParseVersionOne.parseObject(laptop.toByteArray());
        ParseVersionTwo.parseObject(laptop.toByteArray());
        ParseVersionThree.parseObject(laptop.toByteArray());
        ParseVersionFour.parseObject(laptop.toByteArray());

    }
}
