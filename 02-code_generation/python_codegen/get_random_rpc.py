import grpc

import example_pb2_grpc, example_pb2

local_uri = 'localhost:50051'
remote_uri = None  # TODO: Asignar a la uri especificada durante la sesión para realizar la conexión


def main():
    uri = remote_uri if remote_uri is not None else local_uri
    # Se inicia la conexión con el servidor
    with grpc.insecure_channel(uri) as channel:
        # Se inicializa el stub que realizará las diferentes peticiones
        stub = example_pb2_grpc.ExampleServiceStub(channel)
        # Se realiza la llamada remota. Al tratarse de un stream desde servidor, la respuesta será un iterable con cada uno de los mensajes
        for response in stub.GetRandom(example_pb2.RandomRequest(numbers=10, min=0, max=10)):
            print(response.value)


if __name__ == "__main__":
    main()
