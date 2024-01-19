import grpc

import example_pb2_grpc, example_pb2

local_uri = 'localhost:50051'
remote_uri = None  # TODO: Asignar a la uri especificada durante la sesión para realizar la conexión


# Clase auxiliar que implementa un iterador bloqueante que lee de stdin
class LineIter:
    def __iter__(self):
        return self

    def __next__(self):
        message = input()
        if message != "exit":
            return example_pb2.EchoMessage(message=message)
        raise StopIteration


def main():
    uri = remote_uri if remote_uri is not None else local_uri
    print("Write \"exit\" to stop sending messages")
    # Se inicia la conexión con el servidor
    with grpc.insecure_channel(uri) as channel:
        # Se inicializa el stub que realizará las diferentes peticiones
        stub = example_pb2_grpc.ExampleServiceStub(channel)
        # Se realiza la llamada remota. Al tratarse de un stream bidireccional, tanto parámetro como respuesta son iteradores.
        response_stream = stub.StreamingEcho(LineIter())
        for echo_message in response_stream:
            print("Received echo: " + echo_message.message)


if __name__ == "__main__":
    main()
