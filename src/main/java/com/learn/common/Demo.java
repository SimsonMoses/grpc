package com.learn.common;

import com.learn.six.BankService;

public class Demo {
    public static void main(String[] args) {
        GrpcServer.create(new BankService())
                .start()
                .await();
    }
}

/*section 3 v: 48
* */