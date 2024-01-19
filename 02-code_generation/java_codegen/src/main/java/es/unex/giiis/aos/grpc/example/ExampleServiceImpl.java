package es.unex.giiis.aos.grpc.example;


import es.unex.giiis.aos.grpc.example.generated.EchoMessage;
import es.unex.giiis.aos.grpc.example.generated.ExampleServiceGrpc;
import es.unex.giiis.aos.grpc.example.generated.RandomRequest;
import es.unex.giiis.aos.grpc.example.generated.RandomResponse;
import io.grpc.stub.StreamObserver;

import java.util.Random;

/** Implementación del servicio definido en el .proto */
public class ExampleServiceImpl extends ExampleServiceGrpc.ExampleServiceImplBase {
    @Override
    public void getEcho(EchoMessage request, StreamObserver<EchoMessage> responseObserver) {
        System.out.println(request.getMessage());
        // Se notifica de la respuesta
        responseObserver.onNext(request);
        // Se notifica del final correcto de la comunicación
        responseObserver.onCompleted();
    }

    @Override
    public void getRandom(RandomRequest request, StreamObserver<RandomResponse> responseObserver) {
        // Se obtienen los valores, comprobando previamente si estaban definidos para aquellos campos cuyo valor por defecto no sea 0
        final int numbers = request.hasNumbers() ? request.getNumbers() : 1;
        final int min = request.getMin();
        final int max = request.hasMax() ? request.getMax() : min+1;
        for(int i = 0; i < numbers; i++) {
            final float value = new Random().nextFloat(min, max);
            // Se construye la respuesta
            final RandomResponse response = RandomResponse.newBuilder().setValue(value).build();
            // Se notifica de la respuesta tantas veces como sea necesario
            responseObserver.onNext(response);
        }
        // Se notifica del final correcto de la comunicación
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<EchoMessage> streamingEcho(StreamObserver<EchoMessage> responseObserver) {
        // Al tratarse de un stream bidireccional se debe retornar un StreamObserver que atenderá los eventos enviados por el cliente.
        //   en caso de streams cliente se procede de la misma forma, aunque el servidor podrá contestar, como máximo, una vez
        return new StreamObserver<>() {
            @Override
            public void onNext(EchoMessage value) {
                responseObserver.onNext(value);
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
