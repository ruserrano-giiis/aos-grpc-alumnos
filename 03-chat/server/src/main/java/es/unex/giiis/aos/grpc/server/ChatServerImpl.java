package es.unex.giiis.aos.grpc.server;

import es.unex.giiis.aos.grpc.generated.Chat.*;
import es.unex.giiis.aos.grpc.generated.ChatServiceGrpc;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;

/** Implementación del servicio grpc */
public class ChatServerImpl extends ChatServiceGrpc.ChatServiceImplBase {

    private final ChatServerService service = ChatServerService.get();

    @Override
    public void ping(EmptyMessage request, StreamObserver<PongMessage> responseObserver) {
        // Construcción de la respuesta
        final PongMessage pong = PongMessage.newBuilder().build();
        // Encío de respuesta
        responseObserver.onNext(pong);
        // Notificación de final de conexión
        responseObserver.onCompleted();
    }

    @Override
    public void getUsers(EmptyMessage request, StreamObserver<UsersResponse> responseObserver) {
        // Construcción de la respuesta
        final UsersResponse usersResponse = UsersResponse.newBuilder()
                .addAllUsers(service.getSubscribers())
                .build();
        // Encío de respuesta
        responseObserver.onNext(usersResponse);
        // Notificación de final de conexión
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessage(SentChatMessage request, StreamObserver<EmptyMessage> responseObserver) {
        // Obtención de datos de la petición
        final String username = request.getUser();
        final String message = request.getMessage();

        try {
            service.send(username, message);

            // Encío de respuesta
            responseObserver.onNext(EmptyMessage.newBuilder().build());
            // Notificación de final de conexión
            responseObserver.onCompleted();
        } catch (StatusException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void subscribe(UsernameMessage request, StreamObserver<ReceivedChatMessage> responseObserver) {
        // Obtención de datos de la petición
        final String username = request.getUsername();
        try {
            // No se termina la comunicación, pues se utilizará el observer para enviar los mensajes entrantes a cada uno los clientes activos
            service.subscribe(username, responseObserver);
        } catch (StatusException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void unsubscribe(UsernameMessage request, StreamObserver<EmptyMessage> responseObserver) {
        final String username = request.getUsername();
        try {
            // Notificación de final de conexión de `subscribe`
            service.unsubscribe(username).onCompleted();
            // Encío de respuesta
            responseObserver.onNext(EmptyMessage.newBuilder().build());
            // Notificación de final de conexión de `unsubscribe`
            responseObserver.onCompleted();
        } catch (StatusException e) {
            responseObserver.onError(e);
        }
    }
}
