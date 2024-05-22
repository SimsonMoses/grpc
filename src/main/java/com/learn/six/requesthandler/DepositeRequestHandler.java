package com.learn.six.requesthandler;

import com.learn.grpc.six.AccountBalance;
import com.learn.grpc.six.DepositeRequest;
import com.learn.six.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepositeRequestHandler implements StreamObserver<DepositeRequest> {

    private static final Logger log = LoggerFactory.getLogger(DepositeRequestHandler.class);

    private final StreamObserver<AccountBalance> responseObserver;

    private int accountNumber;

    public DepositeRequestHandler(StreamObserver<AccountBalance> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(DepositeRequest depositeRequest) {
        switch (depositeRequest.getRequestCase()) {
            case ACCOUNTNUMBER -> this.accountNumber = depositeRequest.getAccountNumber();
            case MONEY -> AccountRepository.depositAmount(accountNumber, depositeRequest.getMoney().getAmount());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Client has stopped Request");
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
