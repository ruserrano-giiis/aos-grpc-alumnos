package es.unex.giiis.aos.grpc.client;

import es.unex.giiis.aos.grpc.client.callbacks.*;
import es.unex.giiis.aos.grpc.generated.Chat;
import es.unex.giiis.aos.grpc.generated.ChatServiceGrpc;
import io.grpc.*;

import java.util.List;

// clase controladora del cliente
public class ClientController {
    private final ChatServiceGrpc.ChatServiceStub serviceStub;
    private static ClientController instance = null;

// constructor con modelo singleton para el cliente.
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

// método para la realización de ping al servidor.
    public void ping(ValueCallback<Boolean> onResult) {
        // TODO: Implementar
    }

//método para la obtención de los usuarios del chat.
    public void getUsers(ValueCallback<List<String>> onResult) {
        // TODO: Implementar
    }

//método para el envio de mensajes del cliente.
    public void sendMessage(String username, String message, ValueCallback<Boolean> onComplete) {
        // TODO: Implementar
    }

//método para unirse al chat.
    public void subscribe(String username, ValueCallback<Chat.ReceivedChatMessage> onValue) {
        // TODO: Implementar
    }

//método para cerrar sesión del chat.
    public void unsubscribe(String username, ValueCallback<Boolean> onValue) {
        // TODO: Implementar
    }
}
