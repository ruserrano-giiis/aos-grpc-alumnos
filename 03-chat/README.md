# Inicio

Realizaremos una aplicación de chat sentilla utilizando grpc para la comunicación entre lciente y servidor

El proyecto cuenta con 3 módulos:
 * `protos`: Módulo auxiliar que configura la generación de código y hace de librería del código generado
 * `server`: Código relativo al servidor de chat
 * `client`: Código relativo al cliente de chat

# Guión

Trabajaremos con un proyecto generado a partir de un fichero ```.proto``` sobre el cual,  basándonos en el resto del código de la aplicación tendremos que definir los mensajes y servicios que componen el fichero.

Para completar el archivo ```.proto``` deben seguirse los siguientes casos de uso:
1. Realizar ping al servidor, el cual responderá con un pong, tanto el mensaje enviado por el cliente como por el servidor estarán vacios.
2. Obtener los usuarios del chat pasando un mensaje vacío y obteniendo un **mensaje** con una lista de string.
3. Suscribirse al chat utilizando un mensaje compuesto por el nombre de usuario (username) que se va a utilizar y obteniendo un flujo de mensajes por parte del servidor.
   * Los mensajes recibidos en el flujo debe contener nombre de usuario (user), mensaje (message) y timestamp
4. Desuscribirse del chat utilizando un mensaje que contenga el nombre de usuario (username) a desuscribir y obteniendo un mensaje vacío por parte del servidor.
5. Envio de mensajes utilizando un mensaje compuesto por el nombre de usuario (user) y mensaje (message) a enviar y obteniendo un mensaje vacío por parte del servidor.

## Cliente
En el fichero `ClientController` encontraremos una clase que sigue el patrón singleton y un stub asíncrono para la comunicación cliente-servidor. 

Al utilizar un stub asíncrono es necesario especificar un callback para cada llamada remota, este callback se ejecutará cuando el servidor responda a nuestra petición. En estos callbacks se pueden ver los métodos que manejan los distintos eventos del servidor (mensaje, finalización o error).

También encontraremos una serie de métodos vacíos a implementar para completar la gestión de mensajes del cliente, es decir, los objetos necesarios en cada llamada a servidor y el uso de las respuestas obtenidas del servidor.

Para completar estos métodos necesitaremos:

1. Construir un mensaje utilizando los builder de las clases generadas.
2. Realizar la llamada a través del stub definido en la clase utilizando dos parámetros:
   1. El mensaje (objeto) construido
   2. Una instancia de callback (definidos en la carpeta callbacks para cada uno de los métodos) construida con el parámetro de la función en la que nos encontramos.

Una vez construidos los métodos podremos realizar la conexión a un servidor previamente inicializado cuya url se especificará en la sesión.

## Servidor

En el fichero `ChatServerImpl` encontraremos la implementación del servidor, esta clase además posee una instancia de ChatServerService encargada de la validación y almacenamiento de datos en el servidor.

Al igual que con el cliente encontraremos una serie de métodos a implementar de la misma forma:
1. Obtener los datos de la petición
2. Construir un mensaje utilizando los builder de las clases generadas añadiendo campos en caso que fuera necesario.
3. Realizar las llamadas a los métodos correspondintes para envío de mensaje y finalización de conexión cuando sea necesario.

# Compilación y lanzamiento

Ejecutando todos los comandos desde la raiz del proyecto.

## Cliente

### Linux y Windows powershell

```bash
./gradlew :client:installDist
./client/build/install/client/bin/client
```


### Windows cmd

```bat
.\gradlew.bat :client:installDist
.\client\build\install\client\bin\client.bat
```

## Servidor

### Linux y Windows powershell

```bash
./gradlew :server:installDist
./server/build/install/server/bin/server
```


### Windows cmd
```bat
.\gradlew.bat :server:run
.\server\build\install\server\bin\server.bat
```
