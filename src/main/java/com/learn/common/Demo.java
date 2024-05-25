package com.learn.common;

import com.learn.seven.FlowControlService;
import com.learn.six.BankService;
import com.learn.six.TransferService;

public class Demo {
    public static void main(String[] args) {
        GrpcServer.create(new BankService(),new TransferService(),new FlowControlService())
                .start()
                .await();
    }
}

/*section 3 v: 48
* */