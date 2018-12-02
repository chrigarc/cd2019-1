## Equipo
312224464 Estrada Gómez César Derian
311175318 Muñiz Patiño Andrea Fernanda
312242930 Rivera González Damián

## Práctica 6 - Ventanas corredizas o corredizas

### Marco teorico
La Ventana deslizante es un mecanismo dirigido al control de flujo de datos que existe entre un emisor y un receptor pertenecientes a una red informática.

El Protocolo de Ventana Deslizante es un protocolo de transmisión de datos bidireccional de la capa del nivel de enlace (modelo OSI).

La ventana deslizante es un dispositivo de control de flujo de tipo software, es decir, el control del flujo se lleva a cabo mediante el intercambio específico de caracteres o tramas de control, con los que el receptor indica al emisor cuál es su estado de disponibilidad para recibir datos.

Este dispositivo es necesario para no inundar al receptor con envíos de tramas de datos. El receptor al recibir datos debe procesarlo, si no lo realiza a la misma velocidad que el transmisor los envía se verá saturado de datos, y parte de ellos se pueden perder. Para evitar tal situación la ventana deslizante controla este ritmo de envíos del emisor al receptor.

Con este dispositivo se resuelven dos grandes problemas: el control de flujo de datos y la eficiencia en la transmisión.

### Ejercicios
Con las clases anexas implementar el algoritmo de ventanas deslizantes. Se califica con 9 con una implementación correcta y con 10 si se agrega una interfaz gráfica que represente el comportamiento del algoritmo.

El contenido que se envia debe ser leído de un archivo de texto y almacenado dentro de otro.

## Bibliografia
https://es.wikipedia.org/wiki/Ventana_deslizante

### Implementación.

Archivos:

*** Receptor ***

Contiene dos sockets y dos paquetes (uno para enviar las señales y otro para recibir las ventanas)
	'socketRecibo' - recibirá las ventanas desde el Transmisor.
	'socketEnviado' - enviará las señales al Transmisor que indique si puede recibir más ventanas (señal "esperando")
					  En otro caso puede continuar recibiendo ya que está procesando alguna ventana recibida (señal "bloqueado").
					  También se puede dar el caso donde el Receptor encontró un error en algún Segmento de la ventana pide su reenvío de la ventana (señal "reenviar").

	Cuando el Receptor recibe una Ventana sin errores guarda su contenido en el archivo "archivoRecibido.txt".

*** Transmisor ***

Contiene dos sockets y dos paquetes.
	'socketEnvio' - enviará las ventanas que se van generando según el contenido de la lista que contiene la información 					completa del texto para traspasar

	'socketRecibo' - recibirá las señales que recibe del Receptor.
				     Puede recibir señal "bloqueado" para no continuar enviando ventas, se queda esperando una nueva confirmación para enviar una ventana nueva o para reenviar la última ventana enviada. Cada que envía una nueva ventana crea una ventana según el contenido de la lista que tiene la información de texto.

*** Window ***

Crea objetos de tipo Ventana, que dado un tamaño (en esta práctica: el tamaño de CDNode fijo WINDOW_SIZE  = 5)
Sus atributos son objetos de tipo Segment.
Está ventana implementa la clase Serializable para mantener la persistencia de los datos y facilitar el envío a través de los sockets.

*** Segement ***
Crea objetos de tipo Segment
Tiene un atributo 'contenido' de tipo String el cual se encargará de almacenar las líneas del texto que se envían desde el Transmisor al Receptor.

Se agregadon dos archivos de texto para ejecutar la implementación.

### Ejecución.

## Pasos:

Si se desea cambiar de archivo que será traspasado hay que modificar la línea 127 del archivo Transmisor.java
Si se desea cambiar el archivo donde se guardarán las ventanas hay que modificar la línea 115 del archivo Receptor.java 

1.- Estando localizados en la carpeta donde se encuentran los archivo con extensión java. Compilar los archivos con el comando:

	$ javac *.java

2.- Una vez compiladas las clases. Se deberán abrir dos terminales.
	En la primera terminal se deberá ejecutar el comando:

	$ java Receptor

	En la segunda terminal se deberá ejecutar el comando:

	$ java Transmisor.

3.- Para finalizar el programa se deberá dar Ctrl + C en la primer ventana.