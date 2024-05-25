package com.learn.seven;

import com.learn.grpc.seven.v1.FlowControlServiceGrpc;
import com.learn.grpc.seven.v1.Output;
import com.learn.grpc.seven.v1.RequestSize;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class FlowControlService extends FlowControlServiceGrpc.FlowControlServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(FlowControlService.class);

    @Override
    public StreamObserver<RequestSize> getMessages(StreamObserver<Output> responseObserver) {
        return new RequestHandler(responseObserver);
    }

    public static class RequestHandler implements StreamObserver<RequestSize> {

        private final StreamObserver<Output> responseObserver;

        private Integer emitted;

        public RequestHandler(StreamObserver<Output> responseObserver) {
            this.responseObserver = responseObserver;
            this.emitted = 0;
        }

        @Override
        public void onNext(RequestSize requestSize) {
            IntStream.rangeClosed((emitted + 1), 100)
                    .mapToObj(i -> Output.newBuilder().setValue(i).build())
                    .limit(requestSize.getSize())
                    .forEach(responseObserver::onNext);
            emitted = emitted+requestSize.getSize();
            log.info("Emitted : {}",emitted);
            if (emitted >= 100) {
                responseObserver.onCompleted();
            }
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onCompleted() {
            this.responseObserver.onCompleted();
        }
    }
}
