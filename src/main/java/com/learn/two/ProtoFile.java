package com.learn.two;

import com.learn.grpc.two.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoFile {
    public static final Logger log = LoggerFactory.getLogger(ProtoFile.class);

    public static void main(String[] args) {
        var person = Person.newBuilder().setName("simson").setAge(21).build();
        log.info("Person: {}", person);
    }
}
