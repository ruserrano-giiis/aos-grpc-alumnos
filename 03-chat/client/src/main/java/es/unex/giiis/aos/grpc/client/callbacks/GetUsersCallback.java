package es.unex.giiis.aos.grpc.client.callbacks;

import es.unex.giiis.aos.grpc.generated.Chat;
import io.grpc.stub.StreamObserver;

import java.util.List;

// clase callback para las peticiones de petición de la lista de usuarios del chat.
public class GetUsersCallback implements StreamObserver<Chat.UsersResponse> {
    final ValueCallback<List<String>> handler;

    // constructor con asignación del handler.
    public GetUsersCallback(ValueCallback<List<String>> handler) {
        this.handler = handler;
    }

    // métodos de la clase que implementa el callback (flujo de mensajes).
    // comportamiento en la llegada de un nuevo mensaje.
    @Override
    public void onNext(Chat.UsersResponse value) {
        handler.onValue(value.getUsersList().subList(0, value.getUsersCount()));
    }

    //comportamiento ante errores en la llegada de mensajes.
    @Override
    public void onError(Throwable t) {
        System.out.println("GETUSERS ERROR");
    }

    //comportamiento ante finalización de la llegada de mensajes.
    @Override
    public void onCompleted() {
        System.out.println("GETUSERS OK");
    }
}