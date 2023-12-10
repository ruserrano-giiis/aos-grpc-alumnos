package es.unex.giiis.aos.grpc.client;

import es.unex.giiis.aos.grpc.client.callbacks.*;
import es.unex.giiis.aos.grpc.generated.Chat;
import es.unex.giiis.aos.grpc.generated.ChatServiceGrpc;
import io.grpc.*;

import java.util.List;

public class ClientController {
    private final ChatServiceGrpc.ChatServiceStub serviceStub;
    private static ClientController instance = null;

    private ClientController() {
        String target = "localhost:50051"; // TODO: CAMBIAR URL AQUI
        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create())
                .build();
        // TODO: Instanciar stub
        serviceStub = null;
    }

    public static ClientController getClientController() {
        if (ClientController.instance == null) {
            ClientController.instance = new ClientController();
        }
        return ClientController.instance;
    }

    public void ping(ValueCallback<Boolean> onResult) {
        // TODO: Implementar
    }

    public void getUsers(ValueCallback<List<String>> onResult) {
        // TODO: Implementar
    }

    public void sendMessage(String username, String message, ValueCallback<Boolean> onComplete) {
        // TODO: Implementar
    }

    public void subscribe(String username, ValueCallback<Chat.ReceivedChatMessage> onValue) {
        // TODO: Implementar
    }

    public void unsubscribe(String username, ValueCallback<Boolean> onValue) {
        // TODO: Implementar
    }
}
