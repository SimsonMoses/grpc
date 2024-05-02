package com.learn.three;

import com.learn.grp.three.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SerializeAndDeserialize {
    private static final Logger log = LoggerFactory.getLogger(SerializeAndDeserialize.class);
    private static Path path = Path.of("person.out");

    public static void main(String[] args) throws IOException {
        Person person = Person.newBuilder()
                .setPersonId(1)
                .setName("simson")
                .setAddress("india")
                .setAge(21).build();
        log.info("Person: {}", person);
        serialze(person);
        log.info("Person after deserialzed: {}", deserialze());
        log.info("Check Equals : {}", person.equals(deserialze()));
    }

    public static void serialze(Person person) throws IOException {
        try (var stream = Files.newOutputStream(path)) {
            person.writeTo(stream);
        }
    }

    public static Person deserialze() throws IOException {
        try (var stream = Files.newInputStream(path)) {
            return Person.parseFrom(stream);
        }
    }
}
