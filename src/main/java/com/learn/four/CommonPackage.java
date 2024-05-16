package com.learn.four;

import com.learn.grpc.common.Address;
import com.learn.grpc.common.Car; // make note that Car is imported from the common package
import com.learn.grpc.four.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonPackage {
    private static final Logger log = LoggerFactory.getLogger(CommonPackage.class);
    public static void main(String[] args) {
        // example for using the common class
        //
        Car car = Car.newBuilder().setMake("tata").setCarId(1).build();
        Address address = Address.newBuilder().setCity("cbe").build();
        Person person = Person.newBuilder().setAddress(address).setCar(car).build();
        person.hasAge();
        log.info("Person Object: {}",person);

    }
}
