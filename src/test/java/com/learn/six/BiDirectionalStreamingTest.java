package com.learn.six;

import com.learn.common.ResponseObserver;
import com.learn.grpc.six.AccountBalance;
import com.learn.grpc.six.TransferRequest;
import com.learn.grpc.six.TransferResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;

public class BiDirectionalStreamingTest extends AbstractTest {

    @Test
    public void transferTest(){
        var responseObserver = ResponseObserver.<TransferResponse>create();
        var requestObserver = transferServiceStub.transfer(responseObserver);
        var requests = List.of(
                TransferRequest.newBuilder().setFromAccount(1).setToAccount(2).setAmount(20).build(),
                TransferRequest.newBuilder().setFromAccount(2).setToAccount(3).setAmount(20).build(),
                TransferRequest.newBuilder().setFromAccount(1).setToAccount(3).setAmount(20).build(),
                TransferRequest.newBuilder().setFromAccount(2).setToAccount(3).setAmount(20).build(),
                TransferRequest.newBuilder().setFromAccount(4).setToAccount(2).setAmount(20).build()
        );
        requests.forEach(requestObserver::onNext);
        requestObserver.onCompleted();
        responseObserver.await();
        var responseList = responseObserver.getList();
        Assertions.assertEquals(5,responseList.size());
        Assertions.assertNull(responseObserver.getThrowable());

    }

}
