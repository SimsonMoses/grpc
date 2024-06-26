package com.learn.three;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.grpc.three.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Performance {
    private static Logger log = LoggerFactory.getLogger(Performance.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        Person person = Person.newBuilder()
                /*.setPersonId(1)
                .setName("simson")
                .setAddress("india")
                .setAge(21)*/
                .setIsActive(true).build();
        log.info("Person: {}", person);

        var jsonPerson = new PersonJson(1,"simson",21,12000000,"cbe",654321098);
//        var jsonPerson = new PersonJson();
        for(int i = 0 ;i<1;i++){
            runTest("proto",()->proto(person));
            runTest("json",()->json(jsonPerson));
        }

    }

    public static void proto(Person person) {
        try {
            var personBytes = person.toByteArray();
            log.info("Proto Person bytes length: {}",personBytes.length);
            Person.parseFrom(personBytes);
        } catch (Exception ex) {
            log.error("Error: {}", ex.getMessage());
        }
    }

    public static void json(PersonJson personJson) {
        try {
            var bytes = mapper.writeValueAsBytes(personJson);
            log.info("Proto Person bytes length: {}",bytes.length);
            mapper.readValue(bytes, PersonJson.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void runTest(String testName, Runnable runnable){
        var start = System.currentTimeMillis();
//        for(int i = 0 ;i<1_000_000;i++){
//            runnable.run();
//        }
        runnable.run();
        var end = System.currentTimeMillis();
        log.info("Time take for Start and end time:{} - {}ms",testName,(end- start));
    }

}
