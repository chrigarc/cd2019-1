--Compilar
javac P2P.java ServidorUdp.java 

--Ejecutar
java P2P


--Pre condiciones para ejecutar 
export_JAVA_OPTIONS="-Djava.net.preferIPv4Stack=true"

#####Integrantes y número de cuenta de los miembros del equipo:

Miguel Andres Guevara Castro # 314091057

Erick Gonzalez Duran # 314271103

Rivas Leon Alexis # 314215323

Ángeles Martínez Ángela Janín # 314201009


Esta practica tuvimos muchas complicaciones por que la comunicacion entre los computadores del ayudante y la mia no estaban en la misma red y cuando estaban en la misma red estos utilizaban protocolos de red diferentes el del ayudante, esto lo solucionamos con la sentencia en terminal ' export_JAVA_OPTIONS="-Djava.net.preferIPv4Stack=true" '

P2P
Una red peer-to-peer, red de pares, red entre iguales o red entre pares (P2P, por sus siglas en inglés) es una red de ordenadores en la que todos o algunos aspectos funcionan sin clientes ni servidores fijos, sino una serie de nodos que se comportan como iguales entre sí.

UDP
Permite el envío de mensajes (datagramas) a través de la red sin que se haya establecido previamente una conexión, ya que el propio datagrama incorpora suficiente información de direccionamiento en su cabecera. Tampoco tiene confirmación ni control de flujo, por lo que los paquetes pueden adelantarse unos a otros; y tampoco se sabe si ha llegado correctamente, ya que no hay confirmación de entrega o recepción.

Ejercicios
Crear una aplicación que envie mensajes por medio de UDP a los siguientes puertos 25000, 25001, 25002, 25003, 25004, 25005
El único mensaje a enviar es una cadena que varia dependiendo del número de cuenta del alumno
Se debe escuchar el mensaje en solo uno de los siguientes puertos 25000, 25001, 25002, 25003, 25004, 25005
El se debe poder indicar al inicio que puerto y que identificador se va a utilizar
La aplicación debe generar una grafica con GraphStream donde el único nodo existente debe ser el nodo con el identificador del numero de cuenta
Cada que la aplicación escuche un mensaje debe agregar en caso de no existir a la grafica un nodo con ese identificador y conectarlo al primer con el que inicio la gráfica
Bibliografia
http://www.chuidiang.org/java/sockets/udp/socket_udp.php http://www.it.uc3m.es/celeste/docencia/cr/2003/PracticaSocketsUDP/ https://docs.oracle.com/javase/7/docs/api/

