package com.learn.three;

import com.learn.grpc.three.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultValue {
    private static final Logger log = LoggerFactory.getLogger(DefaultValue.class);
    public static void main(String[] args) {
        School school = School.newBuilder().build();
        log.info("School: {}",school); // never null will be assigned in grpc
        log.info("School id: {}",school.getId()); // for the primitive type default value will be same as the java
        log.info(school.getName()); // default value of the string will be empty

        // To check the default value
        // compostion
        School sc = School.newBuilder().build();
        log.info("School default value: {}",School.getDefaultInstance());
        log.info("Address default value: {}", Address.getDefaultInstance());
        // to check whether the field is assigned to the default value
        log.info("Comparing the address in the school with Address defaultInstance: {}",school.getAddress().equals(Address.getDefaultInstance()));
        log.info("Comparing using has method: {}",sc.hasAddress()); // return true if the address preset or assinged

        //collection
        Book bookOne = Book.newBuilder().setTitle("JAVA 1.8").setAuthor("James").setPublishedYear(2012).build();
        Library library = Library.newBuilder().setGroup("java").addBooks(0,bookOne).build();
        log.info("get method:{}",library.getBooksList());
        Library libraryOne = Library.newBuilder().build();
        log.info("get method:{}",libraryOne.getBooksList());
        // map
        // empty list
        Car car = Car.newBuilder().setCarId(2).setMake("Tata").setModel("sedan").build();
        Dealer inventoryMap = Dealer.newBuilder().build();
        log.info("While map data is empty: {}",inventoryMap.getDealerMap());

        //enum
        log.info("Car enum default value: {}",car.getStyle());
    }
}
