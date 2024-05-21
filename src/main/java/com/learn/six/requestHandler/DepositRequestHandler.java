package com.learn.six.requestHandler;

import com.learn.grpc.six.AccountBalance;
import com.learn.grpc.six.DepositeRequest;
import com.learn.six.repository.AccountRepository;
import io.grpc.stub.StreamObserver;

public class DepositRequestHandler implements StreamObserver<DepositeRequest> {

    private final StreamObserver<AccountBalance> responseObserver;

    private int accountNumber;

    public DepositRequestHandler(StreamObserver<AccountBalance> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(DepositeRequest depositeRequest) {
        switch (depositeRequest.getRequestCase()) {
            case ACCOUNTNUMBER -> this.accountNumber = depositeRequest.getAccountNumber();
            case MONEY -> AccountRepository.dopositAmount(accountNumber, depositeRequest.getMoney().getAmount());
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        var accountBalance = AccountBalance.newBuilder()
                .setAccountNumber(accountNumber)
                .setBalance(AccountRepository.getAccountBalance(accountNumber))
                .build();
        this.responseObserver.onNext(accountBalance);
        this.responseObserver.onCompleted();
    }
}
