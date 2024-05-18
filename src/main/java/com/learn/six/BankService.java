package com.learn.six;

import com.google.protobuf.Empty;
import com.learn.grpc.six.AccountBalance;
import com.learn.grpc.six.AllAccountResponse;
import com.learn.grpc.six.BalanceRequest;
import com.learn.grpc.six.BankServiceGrpc;
import com.learn.six.repository.AccountRepository;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class BankService extends BankServiceGrpc.BankServiceImplBase {
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
}
