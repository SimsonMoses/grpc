package com.learn.three;

import com.learn.grp.three.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoIgnore {
    private static final Logger log = LoggerFactory.getLogger(ProtoIgnore.class);
    public static void main(String[] args) {
        // note that tostring return field name as in the person proto file
        Person person = Person.newBuilder()
                .setPersonId(1)
                .setName("simson")
                .setAddress("india")
                .setAge(21).build();
        log.info("Person: {}",person);


        // if the value is not set then the field will be ignored
        Person person1 = Person.newBuilder()
                .setPersonId(2).setName("moses").build();
        log.info("Person: {}",person1);
    }
}
