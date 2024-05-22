package com.learn.six;

import com.learn.common.ResponseObserver;
import com.learn.grpc.six.AccountBalance;
import com.learn.grpc.six.BalanceRequest;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class UnaryAsyncBlockingClientTest extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(UnaryBlockingClientTest.class);


    @Test
    public void getAccountBalance() throws InterruptedException {
        var account = BalanceRequest.newBuilder().setAccountNumber(1).build();

        var latch = new CountDownLatch(1); // intro java 5 onwards
        bankStub.getAccountBalance(account, new StreamObserver<AccountBalance>() {
            @Override
            public void onNext(AccountBalance accountBalance) {
                log.info("Account Balance: {}", accountBalance.getBalance());
                // if the test case failed, there will be an exception so next step will not be executed, program will never. so we are using the try finally block here
                try {
                    Assertions.assertEquals(491, accountBalance.getBalance());
                } finally {
                    latch.countDown();
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });

        latch.await();
    }


    // Using the Generic Class for the async call
    @Test
    public void getAccountDetail(){
        var account = BalanceRequest.newBuilder().setAccountNumber(1).build();
        var observer = ResponseObserver.<AccountBalance>create();
        bankStub.getAccountBalance(account,observer);
        observer.await();
        Assertions.assertEquals(1,observer.getList().size());
        Assertions.assertEquals(490,observer.getList().get(0).getBalance());
        Assertions.assertNull(observer.getThrowable());
    }
}
