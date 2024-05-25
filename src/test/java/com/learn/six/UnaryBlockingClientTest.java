package com.learn.six;

import com.google.protobuf.Empty;
import com.learn.grpc.six.BalanceRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;


public class UnaryBlockingClientTest extends  AbstractTest{

    private static final Logger log = LoggerFactory.getLogger(UnaryBlockingClientTest.class);

    @Test
    public void getBalanceTest(){
        var request = BalanceRequest.newBuilder().setAccountNumber(1).build();
        var balance = blockingStub.getAccountBalance(request);
        log.info("Account No : {}  Balance:{}",balance.getAccountNumber(),balance.getBalance());
//        Assertions.assertEquals(490,balance.getBalance());
    }

    @Test
    public void getAllAccount(){
        var allAccountRequest = Empty.getDefaultInstance();
        var allAccountResponse =  blockingStub.getAllAccount(allAccountRequest);
        Assertions.assertEquals(10,allAccountResponse.getAccountsList().size());
    }

}
