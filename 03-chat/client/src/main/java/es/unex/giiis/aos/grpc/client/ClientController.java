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
        String target = "localhost:50051"; // CAMBIAR URL AQUI
        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create())
                .build();
        this.serviceStub = ChatServiceGrpc.newStub(channel);
    }

    public static ClientController getClientController() {
        if (ClientController.instance == null) {
            ClientController.instance = new ClientController();
        }
        return ClientController.instance;
    }

// método para la realización de ping al servidor.
    public void ping(ValueCallback<Boolean> onResult) {
        final Chat.EmptyMessage request = Chat.EmptyMessage.newBuilder().build();
        serviceStub.ping(request, new PingCallback(onResult));
    }

//método para la obtención de los usuarios del chat.
    public void getUsers(ValueCallback<List<String>> onResult) {
        final Chat.EmptyMessage request = Chat.EmptyMessage.newBuilder().build();
        serviceStub.getUsers(request, new GetUsersCallback(onResult));
    }

//método para el envio de mensajes del cliente.
    public void sendMessage(String username, String message, ValueCallback<Boolean> onComplete) {
        final Chat.SentChatMessage sentChatMessage = Chat.SentChatMessage
                .newBuilder()
                .setUser(username)
                .setMessage(message)
                .build();
        serviceStub.sendMessage(sentChatMessage, new SendCallback(onComplete));
    }

//método para unirse al chat.
    public void subscribe(String username, ValueCallback<Chat.ReceivedChatMessage> onValue) {
        final Chat.UsernameMessage usernameMessage = Chat.UsernameMessage.newBuilder().setUsername(username).build();
        serviceStub.subscribe(usernameMessage, new SubscribeCallback(onValue));
    }

//método para cerrar sesión del chat.
    public void unsubscribe(String username, ValueCallback<Boolean> onValue) {
        final Chat.UsernameMessage usernameMessage = Chat.UsernameMessage.newBuilder().setUsername(username).build();
        serviceStub.unsubscribe(usernameMessage, new UnsubscribeCallback(onValue));
    }
}
