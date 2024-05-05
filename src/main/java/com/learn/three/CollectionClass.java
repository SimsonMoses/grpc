package com.learn.three;

import com.learn.grp.three.Book;
import com.learn.grp.three.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CollectionClass {
    private static final Logger log = LoggerFactory.getLogger(CollectionClass.class);

    public static void main(String[] args) {
        Book bookOne = Book.newBuilder().setTitle("JAVA 1.8").setAuthor("James").setPublishedYear(2012).build();
        Book bookTwo = Book.newBuilder().setTitle("JAVA 11").setAuthor("James").setPublishedYear(2016).build();
        Book bookThree = Book.newBuilder().setTitle("A CURIOUS MIND").setAuthor("Brain grazer").setPublishedYear(2014).build();
//        Library library = Library.newBuilder().setGroup("Programming").addBooks(bookOne).addBooks(bookTwo).build();
        Library library = Library.newBuilder().setGroup("Java").addAllBooks(List.of(bookOne,bookTwo)).build();
        log.info("library: {}",library);

//        log.info("");
    }
}
