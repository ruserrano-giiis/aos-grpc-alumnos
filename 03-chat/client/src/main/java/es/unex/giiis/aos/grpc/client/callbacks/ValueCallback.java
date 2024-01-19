package es.unex.giiis.aos.grpc.client.callbacks;

// interfaz para callback genérico
public interface ValueCallback<T> {
    void onValue(T data);
}
