package com.learn.three;

import com.learn.grpc.three.Address;
import com.learn.grpc.three.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Composition {
    private static final Logger log = LoggerFactory.getLogger(Composition.class);

    public static void main(String[] args) {
        var address = Address.newBuilder().setStreet("16").setCity("tnj").setState("TN").build();
        var student = Student.newBuilder().setName("simson").setAddress(address).buildPartial();
        log.info("Address: {}",address);
        log.info("student: {}",student);
    }
}
