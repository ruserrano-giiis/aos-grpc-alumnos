package es.unex.giiis.aos.grpc.server;

import es.unex.giiis.aos.grpc.generated.Chat.*;
import es.unex.giiis.aos.grpc.generated.ChatServiceGrpc;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;

import java.util.List;

/** Implementación del servicio grpc */
public class ChatServerImpl extends ChatServiceGrpc.ChatServiceImplBase {

    private final ChatServerService service = ChatServerService.get();

    @Override
    public void ping(EmptyMessage request, StreamObserver<PongMessage> responseObserver) {
        // TODO: Implementar ping
    }

    @Override
    public void getUsers(EmptyMessage request, StreamObserver<UsersResponse> responseObserver) {
        final List<String> subscribers = service.getSubscribers();
        // TODO: Implementar getUsers
    }

    @Override
    public void sendMessage(SentChatMessage request, StreamObserver<EmptyMessage> responseObserver) {
        try {
            // TODO: Obtener datos de la petición
            final String username = "";
            final String message = "";
            service.send(username, message);
            // TODO: Implementar sendMessage
        } catch (StatusException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void subscribe(UsernameMessage request, StreamObserver<ReceivedChatMessage> responseObserver) {
        try {
            // TODO: Obtener datos de la petición
            final String username = "";
            service.subscribe(username, responseObserver);
        } catch (StatusException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void unsubscribe(UsernameMessage request, StreamObserver<EmptyMessage> responseObserver) {
        try {
            // TODO: Obtener datos de la petición
            final String username = "";
            StreamObserver<ReceivedChatMessage> subscriptionHandler = service.unsubscribe(username);
            // TODO: Implementar unsubscribe
        } catch (StatusException e) {
            responseObserver.onError(e);
        }
    }
}
