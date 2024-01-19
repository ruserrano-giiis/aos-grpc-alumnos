package es.unex.giiis.aos.grpc.client.callbacks;

import es.unex.giiis.aos.grpc.client.utils.AlertUtils;
import es.unex.giiis.aos.grpc.generated.Chat;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import javafx.application.Platform;

// clase callback para las peticiones de suscripción al chat (conexión al chat).
public class SubscribeCallback implements StreamObserver<Chat.ReceivedChatMessage> {
    private final ValueCallback<Chat.ReceivedChatMessage> handler;

// constructor con asignación del handler.
    public SubscribeCallback(ValueCallback<Chat.ReceivedChatMessage> handler) {
        this.handler = handler;
    }

// métodos de la clase que implementa el callback (flujo de mensajes).
// comportamiento en la llegada de un nuevo mensaje.
    @Override
    public void onNext(Chat.ReceivedChatMessage value) {
        handler.onValue(value);
        System.out.println(" ONNEXT SUBSCRIBE");
    }

//comportamiento ante errores en la llegada de mensajes.
    @Override
    public void onError(Throwable t) {
        System.out.println(" ERROR ON SUBSCRIBE");
        if(t instanceof StatusRuntimeException) {
            Platform.runLater(() -> {
                AlertUtils.showAlert(((StatusRuntimeException) t).getStatus().getDescription());
            });
        }
    }

//comportamiento ante finalización de la llegada de mensajes.
    @Override
    public void onCompleted() {
        System.out.println(" COMPLETED ON SUBSCRIBE ");
    }
}
