package es.unex.giiis.aos.grpc.client.callbacks;

import es.unex.giiis.aos.grpc.generated.Chat;
import io.grpc.stub.StreamObserver;

// clase callback para las peticiones de envio de mensajes al chat.
public class SendCallback implements StreamObserver<Chat.EmptyMessage> {
    private final ValueCallback<Boolean> handler;

    // constructor con asignación del handler.
    public SendCallback(ValueCallback<Boolean> handler) {
        this.handler = handler;
    }

    // métodos de la clase que implementa el callback (flujo de mensajes).
    // comportamiento en la llegada de un nuevo mensaje.
    @Override
    public void onNext(Chat.EmptyMessage value) {

    }

    //comportamiento ante errores en la llegada de mensajes.
    @Override
    public void onError(Throwable t) {
        handler.onValue(false);
    }

    //comportamiento ante finalización de la llegada de mensajes.
    @Override
    public void onCompleted() {
        handler.onValue(true);
    }
}
