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

*** Clase Cliente ***

En donde hacemos la implementación de una entidad Cliente, la cuál se encargará de enviar el mensaje desde los diferentes puertos disponibles. Para el caso específico de esta práctica, el mensaje que se enviará será el número de cuenta que variará entre los diferentes miembros del equipo.
El número de cuenta de tipo String, se usará cómo parámetro para una entidad Dato; esta entidad nos auxiliará para convertir el String a bytes.
El mensaje es enviado a través de un DatagramSocket, desde los diferentes puestos disponibles hacia la dirección 'localhost'.
Además se prepara un DatagramPacket, que recibe el conjunto de bytes del mensaje que se enviará, el tamaño de los bytes, la dirección del host del servidor 'localhost', y el puerto en el cual el servidor estaŕa escuchando.
El envío del paquete se realizará a través del socket.

*** Clase Servidor ***

Clase en la que hacemos la implementación de un Servidor, la cuál se encargará de escuchar el mensaje que envía el la entidad Cliente.
Contiene un DatagramPacket que será el datagrama en el cual recibirá los mensajes que escucha.
También tiene un DatagramSocket con el puerto en el cual estará escuchando. Con DatagramSocket se recibe un paquete y transforma el contenido del paquete (bytes) a un String. Una vez que se tiene el valor del mensaje recibido, se va contruyendo la gráfica de GraphStream con base en los mensajes recibidos agregando los nodos con etiquetas iguales al contenido del mensaje y realizando adyacencias entre el primer nodo que se genera (el primer mensaje que se recibio) y el último nodo que se crea (el último mensaje que se ha recibido).

*** Clase Dato ***
Esta clase auxiliará a convertir Strings a bytes y viceversa.

Contiene el atributo:
	String contenido. Con su respectivo getter y setter

Contiene los métodos:
	toByteArray()
		Genera un arreglo de bytes para el atributo contenido.
	fromByteArray(byte [] bytes)
		Genera un objeto de tipo Dato desde un arreglo de bytes.

*** Clase Puertos ***

Contiene los valores de las constantes sobre los puertos que se usarán para el envío y recepción de mensajes.
	PUERTO_DEL_SERVIDOR = 25000 - puerto en el que escuchará el servidor
    PUERTOS_DEL_CLIENTE = {25000, 25001, 25002, 25003, 25004, 25005} - puertos desde los que puede enviar el cliente mensajes (excepto el puerto 25000)
    HOST_SERVIDOR = "255.255.255.255";
	HOST_CLIENTE = "localhost"; 


## Ejecución del programa.
1.- Estando localizados en la carpeta donde se encuentren los cuatro archivos (Cliente.java, Servidor.java, Dato.java, Puertos.java). Compilamos:

	javac -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar *.java 

	(Carpeta 'lib' al mismo nivel de la carpeta donde se encuentran los archivos)

2.- Primero ejecutamos la clase Servidor con la línea:

	java -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar Servidor

3.-	Una vez que se encuentre en ejecución el código del Servidor. Desde otra terminal se realiza la ejecución de la clase Cliente:

	java Cliente

	**Este código se deberá ejecutar cuantas veces se desee para enviar mensajes al servidor y poder generar la gráfica de las conexiones.

4.- El programa se termina dando Ctrl + C desde la terminal en la que está recibiendo el servidor o cerrando la ventana de la gráfica de GraphStream.
