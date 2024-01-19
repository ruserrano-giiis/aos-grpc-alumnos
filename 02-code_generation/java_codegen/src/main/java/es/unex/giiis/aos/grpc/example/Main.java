package es.unex.giiis.aos.grpc.example;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Inicialización del servidor haciendo uso de la implementación por defecto
        int port = 50051;
        final Server server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(ProtoReflectionService.newInstance())
                .addService(new ExampleServiceImpl()) // Se configura la implementación del servicio en el servidor
                .build()
                .start();
        server.awaitTermination();
    }
}
