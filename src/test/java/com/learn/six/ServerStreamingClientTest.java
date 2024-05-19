package com.learn.six;

import com.learn.common.ResponseObserver;
import com.learn.grpc.six.AccountBalance;
import com.learn.grpc.six.Money;
import com.learn.grpc.six.WithDrawRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerStreamingClientTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(ServerStreamingClientTest.class);

    @Test
    public void clientWithdraw() {
        var request = WithDrawRequest.newBuilder().setAccountNumber(2).setAmount(30).build();
        var iterator = this.blockingStub.withDraw(request);
        int count = 0;
        while (iterator.hasNext()) {
            log.info("Received money: {}", iterator.next());
            count++;
        }
        Assertions.assertEquals(3,count);
    }

    @Test
    public void asyncClientWithdraw(){
        var request = WithDrawRequest.newBuilder().setAccountNumber(2).setAmount(30).build();
        var observer = ResponseObserver.<Money>create();
        stub.withDraw(request,observer);
        observer.await();
        Assertions.assertEquals(3,observer.getList().size());
        Assertions.assertEquals(10,observer.getList().get(0).getAmount());
        Assertions.assertNull(observer.getThrowable());
    }

}
