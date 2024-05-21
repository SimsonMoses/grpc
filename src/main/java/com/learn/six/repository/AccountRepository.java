package com.learn.six.repository;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountRepository {

    public static final Map<Integer, Integer> db = IntStream.rangeClosed(1, 10)
            .boxed().collect(Collectors.toConcurrentMap(
                    Function.identity(),
                    v -> 490
            ));

    public static Integer getAccountBalance(int accountNumber) {
        return db.get(accountNumber);
    }

    public static Map<Integer, Integer> getAllAccounts() {
        return Collections.unmodifiableMap(db);
    }

    public static void deductAmount(int accountNumber, int amount) {
        db.computeIfPresent(accountNumber, (k, v) -> v - amount);
    }

    public static void dopositAmount(int accountNumber, int amount) {
        db.computeIfPresent(accountNumber, (k, v) -> v + amount);
    }
}
