package com.learn.six;

import com.learn.grpc.six.AccountBalance;
import com.learn.grpc.six.BalanceRequest;
import com.learn.grpc.six.BankServiceGrpc;
import io.grpc.Grpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.stub.StreamObservers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcClient {

    private static final Logger log = LoggerFactory.getLogger(GrpcClient.class);

    public static void main(String[] args) throws InterruptedException {
        var channel = ManagedChannelBuilder.forAddress("localhost", 5000).usePlaintext().build();

        //sync
//        var stub = BankServiceGrpc.newBlockingStub(channel);
//        var balance = stub.getAccountBalance(BalanceRequest.newBuilder().setAccountNumber(2).build());
//        log.info("Balance : {}",balance);

        // Async
        var stub = BankServiceGrpc.newStub(channel);
        stub.getAccountBalance(BalanceRequest.newBuilder().setAccountNumber(1).build(), new StreamObserver<AccountBalance>() {
            @Override
            public void onNext(AccountBalance accountBalance) {
                log.info("Account Balance: {}", accountBalance);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                log.info("completed");
            }
        });
        // above call is the async call
        Thread.sleep(500);

    }
}
