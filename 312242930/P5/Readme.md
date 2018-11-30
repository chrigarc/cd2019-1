# P2P
Una red peer-to-peer, red de pares, red entre iguales o red entre pares (P2P, por sus siglas en inglés) es una red de ordenadores en la que todos o algunos aspectos funcionan sin clientes ni servidores fijos, sino una serie de nodos que se comportan como iguales entre sí.

## UDP

Permite el envío de mensajes (datagramas) a través de la red sin que se haya establecido previamente una conexión, ya que el propio datagrama incorpora suficiente información de direccionamiento en su cabecera. Tampoco tiene confirmación ni control de flujo, por lo que los paquetes pueden adelantarse unos a otros; y tampoco se sabe si ha llegado correctamente, ya que no hay confirmación de entrega o recepción.

### Ejercicios
* Crear una aplicación que envie mensajes por medio de UDP a los siguientes puertos 25000, 25001, 25002, 25003, 25004, 25005
* El único mensaje a enviar es una cadena que varia dependiendo del número de cuenta del alumno
* Se debe escuchar el mensaje en solo uno de los siguientes puertos 25000, 25001, 25002, 25003, 25004, 25005
* El se debe poder indicar al inicio que puerto y que identificador se va a utilizar
* La aplicación debe generar una grafica con GraphStream donde el único nodo existente debe ser el nodo con el identificador del numero de cuenta
* Cada que la aplicación escuche un mensaje debe agregar en caso de no existir a la grafica un nodo con ese identificador y conectarlo al primer con el que inicio la gráfica

## Bibliografia
http://www.chuidiang.org/java/sockets/udp/socket_udp.php
http://www.it.uc3m.es/celeste/docencia/cr/2003/PracticaSocketsUDP/
https://docs.oracle.com/javase/7/docs/api/


## Implementación.

Tenemos cuatro archivos:
* Cliente.java - En donde hacemos la implementación de una entidad Cliente que dada una cadena que es el número de cuenta del Usuario, esta cadena se toma como parametro para una entidad Dato, cone l cual podremos hacer la conversión de una cadena a bytes que enviaremos desde el cliente al servidor desde diferentes puertos disponibles. El mensaje es enviado atraves de un DatagramSocket con un puerto actual (uno por uno de los puertos disponibles para enviar), y la dirección a 255.255.255.255. Además se prepara un DatagramPacket, que recibe el conjunto de bytes del mensaje que se enviará, el tamaño de los bytes, la dirección del host del servidor '255.255.255.255', y el puerto en el cual el servidor estaŕa escuchando '25000'. Y hacemos el envio del paquete atraves del socket.

* Servidor.java - Clase en la que hacemos la implementación de un Servidor, muestra el puerto en el cual estará escuchando. Tiene un DatagramSocket con el puerto en el cual estará escuchando. También contiene un DatagramPacket que será el datagrama en el cual recibirá los mensajes que escucha. Con el datagramsocket recibimos algún paquete, y transformamos el contenido del paquete (bytes) a una cadena de tipo String. Una vez que se tiene el valor del mensaje que recibió, se va contruyendo la gráfica de GraphStream con base en los mensajes recibidos agregando los nodos con etiquetas iguales al contenido del mensaje y realizando adyacencias entre el primer nodo que se genera (el primer mensaje que se recibio) y el último nodo que se crea (el último mensaje que se ha recibido).

* Dato.java - es la clase donde hacemos la implementación de objetos Dato, que contienen un contenido de tipo String, sus métodos de acceso y modificación, y dos métodos: 'toByteArray()' el cual dada la cadena que tiene como 'contenido' el objeto, genera un arreglo de bytes para su posible envío atreves de los socket dentro de los datagramas de paquete, y otro método 'fromByteArray(byte [] bytes)', que nos genera un objeto de tipo Dato desde un arreglo de bytes. Esta clase realiza la conversión de los mensajes (Strings) de bytes a Dato y viceversa.

* Puertos.java - Contiene los valores de las constantes sobre los puertos que se usarán para el envío y recepción de mensajes.
	    int PUERTO_DEL_SERVIDOR = 25000; - puerto en el que escuchará el servidor
    	int [] PUERTOS_DEL_CLIENTE = {25000, 25001, 25002, 25003, 25004, 25005}; - puertos desde los que puede enviar el cliente mensajes (excepto el puerto 25000)
    	String HOST_SERVIDOR="255.255.255.255";
	    String HOST_CLIENTE="localhost"; 


## Ejecución del programa.
1.- Estando localizados en la carpeta donde se encuentren los cuatro archivos (Cliente.java, Servidor.java, Dato.java, Puertos.java), compilamos las clases con la siguiente línea:

	javac -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar *.java 

	Para esto se debe tener la carpeta 'lib' al mismo nivel de la carpeta donde se encuentran los archivos.

2.- Primero ejecutamos la clase Servidor con la línea:

	java -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar Servidor

3.-	Una vez que se encuentre en ejecución el código del Servidor. Desde otra terminal se realiza la ejecución de la clase Cliente con la línea:

	java Cliente

	El cual entra en un ciclo infinito para estar enviando mensajes en la red todo el tiempo.

4.- El programa se termina dando Ctrl + C desde la terminal en la que está recibiendo el servidor o cerrando la ventana de la gráfica ge GraphStream. Además de dar Ctrl +C desde la terminal en la que el cliente está enviando mensajes infinitamente.


