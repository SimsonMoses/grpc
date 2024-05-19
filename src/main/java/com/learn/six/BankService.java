package com.learn.six;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.protobuf.Empty;
import com.learn.grpc.six.*;
import com.learn.six.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BankService.class);

    @Override
    public void getAccountBalance(BalanceRequest request, StreamObserver<AccountBalance> responseObserver) {
        int accountNumber = request.getAccountNumber();
        int balance = AccountRepository.getAccountBalance(accountNumber);
        var accountBalance = AccountBalance.newBuilder().setAccountNumber(accountNumber).setBalance(balance).build();
        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllAccount(Empty request, StreamObserver<AllAccountResponse> responseObserver) {

        List<AccountBalance> accounts = AccountRepository.getAllAccounts()
                .entrySet().stream()
                .map(a->AccountBalance.newBuilder()
                        .setAccountNumber(a.getKey())
                        .setBalance(a.getValue())
                        .build())
                .toList();
        AllAccountResponse allAccountResponse = AllAccountResponse.newBuilder().addAllAccounts(accounts).build();
        responseObserver.onNext(allAccountResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void withDraw(WithDrawRequest request, StreamObserver<Money> responseObserver) {
        /*
        * */
        var accountNumber = request.getAccountNumber();
        var requestAmount = request.getAmount();

        var accountBalance = AccountRepository.getAccountBalance(accountNumber);

        if(requestAmount > accountBalance){
            responseObserver.onCompleted(); // need to change error
            return;
        }

        for (int i = 0; i < requestAmount/10; i++) {
            var money = Money.newBuilder().setAmount(10).build();
            responseObserver.onNext(money);
            log.info("money sent {}",money);
            AccountRepository.deductAmount(accountNumber,10);
//            Uninterruptibles.sleepUninterruptibly(1 , TimeUnit.SECONDS); //
        }
        responseObserver.onCompleted();
    }
}
