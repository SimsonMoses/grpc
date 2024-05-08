package com.learn.three;

import com.learn.grp.three.Car;
import com.learn.grp.three.Dealer;
import com.learn.grp.three.Style;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapClass {
    private static final Logger  log = LoggerFactory.getLogger(MapClass.class);
    public static void main(String[] args) {
//        Car car = Car.newBuilder().setCarId(1).setMake("tata").setModel("m2").build();
        Car carTwo = Car.newBuilder()
                .setCarId(2)
                .setMake("tata")
                .setStyle(Style.COUPE)
                .setModel("m3")
                .build();
        Car carThree = Car.newBuilder().setCarId(3).setMake("tata").setModel("m4").setStyle(Style.sedan).build();
        Dealer dealer = Dealer.newBuilder().putDealer(1,carThree).putDealer(2,carTwo).build();
        log.info("Dealer: {}",dealer);
        log.info("Dealer contains: {}",dealer.containsDealer(1));
        log.info("Dealer Map: {}",dealer.getDealerMap());

    }
}
