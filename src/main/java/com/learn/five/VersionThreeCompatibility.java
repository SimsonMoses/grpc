package com.learn.five;

import com.google.protobuf.InvalidProtocolBufferException;
import com.learn.five.v1.ParseVersionOne;
import com.learn.five.v2.ParseVersionTwo;
import com.learn.five.v3.ParseVersionThree;
import com.learn.grp.five.v3.Laptop;
import com.learn.grp.five.v3.Type;

public class VersionThreeCompatibility {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        // a field is removed "manufacturingYear"
        Laptop laptop = Laptop.newBuilder().setBrand("MSI").setModelNumber("GF6").setType(Type.GAMING).build();
        ParseVersionOne.parseObject(laptop.toByteArray());
        ParseVersionTwo.parseObject(laptop.toByteArray());
        ParseVersionThree.parseObject(laptop.toByteArray());
    }
}
