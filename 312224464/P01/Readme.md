Computación Distribuida 2019-1
Práctica 01

312224464 Estrada Gómez César Derian
311175318 Muñiz Patiño Andrea Fernanda
312242930 Rivera González Damián

*** Herramientas y ejecución ***

Para poder realizar esta práctica es necesario descargar los archivos JAR que
se encuentran en el siguiente link:

	http://graphstream-project.org/doc/

Para compilar alguno de los dos programas, es necesario escribir en consola:

	javac -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar Grafica01.java
	javac -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar Grafica02.java

Para ejecutar alguno de los dos programas, es necesario escribir en consola:

	java -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar Grafica01
	java -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar Grafica02

*** Ejercicio 1 ***
Para la gráfica del primer ejercicio, decidimos crear la gráfica "a pie", es decir, cuando se ejecute este
programa siempre aparecerá la misma gráfica con la misma cantidad de nodos (5) y exactamente las mismas aristas.
Esto nos facilitó cumplir con la especificación de que cada nodo tendría que ser de color diferente.

*** Ejercicio 2 ***
Para la gráfica del segundo ejercicio, para dar como resultado una gráfica diferente siempre que se ejecute ocupamos
la clase Random que nos proporciona Java, para generar un número aleatorio entre 10 y 50.
Después, exploramos el grado de cada nodo para poder colorearlo de acuerdo a las especificaciones de esta práctica.
Por último, gracias a la función display() mostramos la gráfica en la ejecución.