package es.unex.giiis.aos.grpc.client.callbacks;

import es.unex.giiis.aos.grpc.generated.Chat;
import io.grpc.stub.StreamObserver;

// clase callback para las peticiones de ping.
public class PingCallback implements StreamObserver<Chat.PongMessage> {
    final ValueCallback<Boolean> handler;

// constructor con asignación del handler.
    public PingCallback(ValueCallback<Boolean> handler) {
        this.handler = handler;
    }

// métodos de la clase que implementa el callback (flujo de mensajes).
// comportamiento en la llegada de un nuevo mensaje.
    @Override
    public void onNext(Chat.PongMessage value) {
        System.out.println("PONG RECEIVED");
    }

//comportamiento ante errores en la llegada de mensajes.
    @Override
    public void onError(Throwable t) {
        System.out.println("PING ERROR");
        handler.onValue(false);
    }

//comportamiento ante finalización de la llegada de mensajes.
    @Override
    public void onCompleted() {
        System.out.println("PING OK");
        handler.onValue(true);
    }
}
