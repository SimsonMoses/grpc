package com.learn.one;

import com.learn.grpc.one.PersonOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleProto {

    public static final Logger log = LoggerFactory.getLogger(SimpleProto.class);

    public static void main(String[] args) {

        var person = PersonOuterClass.Person.newBuilder().setName("simson").setAge(21).build();
        log.info("Person {}",person);

    }

}
