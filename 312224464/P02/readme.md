# Computación Distribuida 2019-1
# Práctica 02

# Integrantes
312224464 Estrada Gómez César Derian
311175318 Muñiz Patiño Andrea Fernanda
312242930 Rivera González Damián

# *** Ejecución ***

Es necesario escribir en consola:

	javac -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar *.java
	java -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar Main

# *** CDGraph stopAction() ***
Para detener todo el proceso optamos por utilizar el método stop()
Si bien es una manera insegura de hacerlo, para esta práctica es necesario realizarlo de
esta forma debido a que detiene de manera instantánea todos los nodos y el envío de mensajes.

# *** Message ***
Creamos el objeto Message que representa un mensaje que se envían los distintos nodos.
Sus atributos son los siguientes:
 - ID del nodo que envía el mensaje
 - ID del nodo que recibe el mensaje
 - Tiempo de vida del mensaje

# *** Transport ***
Esta estructura nos ayudará a almacenar los mensajes que envía cada nodo.
Funcionará por medio de diccionarios: <Nodo, Mensajes suyos enviados>
Para realizar esto, nos auxiliamos de la estructura HashMap que provee Java.

# *** CDNode run() **
 Para cumplir con los requisitos que pide la práctica realizamos lo siguiente:
 Creamos un iterador para todos los vecinos de un nodo n de la gráfica y mientras haya vecinos,
 para cada uno generará un mensaje distinto y se lo enviará (Auxiliándose del método sendMessage()).

 Después, leerá un mensaje (readMessage()) y el nodo creará una copia del mensaje y lo enviará a cada uno de sus vecinos disminuyendo en 1 su tiempo de vida cada vez.


