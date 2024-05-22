package com.learn.six.requesthandler;

import com.learn.grpc.six.AccountBalance;
import com.learn.grpc.six.TransferRequest;
import com.learn.grpc.six.TransferResponse;
import com.learn.grpc.six.TransferStatus;
import com.learn.six.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferRequestHandler implements StreamObserver<TransferRequest> {

    private static final Logger log = LoggerFactory.getLogger(TransferRequestHandler.class);

    private final StreamObserver<TransferResponse> responseStreamObserver;

    public TransferRequestHandler(StreamObserver<TransferResponse> responseStreamObserver) {
        this.responseStreamObserver = responseStreamObserver;
    }

    @Override
    public void onNext(TransferRequest transferRequest) {
        var status = transfer(transferRequest);
        if(TransferStatus.COMPLETED.equals(status)) {
            var response = TransferResponse.newBuilder()
                    .setFromAccount(toAccountBalance(transferRequest.getFromAccount()))
                    .setToAccount(toAccountBalance(transferRequest.getToAccount()))
                    .setTransferStatus(status)
                    .build();
            responseStreamObserver.onNext(response);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error: {}",throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        log.info("Transfer has Completed by client");
        responseStreamObserver.onCompleted();
    }

    private TransferStatus transfer(TransferRequest transferRequest) {
        var toAccount = transferRequest.getToAccount();
        var fromAccount = transferRequest.getFromAccount();
        var amount = transferRequest.getAmount();
        var status = TransferStatus.REJECTED;
        if (AccountRepository.getAccountBalance(toAccount) > amount && toAccount != fromAccount) {
            AccountRepository.deductAmount(fromAccount, amount);
            AccountRepository.depositAmount(toAccount, amount);
            status = TransferStatus.COMPLETED;
        }
        return status;
    }

    private AccountBalance toAccountBalance(int accountNumber) {
        return AccountBalance.newBuilder()
                .setAccountNumber(accountNumber)
                .setBalance(AccountRepository.getAccountBalance(accountNumber))
                .build();
    }
}
