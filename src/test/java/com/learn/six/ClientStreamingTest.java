package com.learn.six;

import com.learn.common.ResponseObserver;
import com.learn.grpc.six.AccountBalance;
import com.learn.grpc.six.DepositeRequest;
import com.learn.grpc.six.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class ClientStreamingTest extends AbstractTest {

    @Test
    public void depositAmount(){
        var responseObserver = ResponseObserver.<AccountBalance>create();
        var requestStreamObserver = this.bankStub.depositAmount(responseObserver);

        requestStreamObserver.onNext(DepositeRequest.newBuilder().setAccountNumber(1).build());

        // stopping in b/w
//        requestStreamObserver.onError(new RuntimeException());

        IntStream.rangeClosed(1,10)
                .mapToObj(m-> Money.newBuilder().setAmount(10).build())
                .map(money -> DepositeRequest.newBuilder().setMoney(money).build())
                .forEach(requestStreamObserver::onNext);
        requestStreamObserver.onCompleted(); // notify the server request has been completed

        responseObserver.await(); // to get the responseObserver from the server

        Assertions.assertEquals(1,responseObserver.getList().size());
        Assertions.assertNull(responseObserver.getThrowable());
    }

}
