Computación Distribuida 2019-1
Práctica 01 - Introducción a Graph

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

La graica creada es la siguiente: (estatica) 
Vertices = {A, B, C, D, E}
Aristas = {(A, B), (A, C), (B, C), (B, D), (B, E), (C,E)}

Agregamos un color específico a cada uno de los vértices. 

*** Ejercicio 2 ***

Para el segundo ejercicio realizamos una gráfica aleatoria y pintamos sus vértices según su grado.
la cual estará formada por una cantidad de 10 a 50 vértces, utilizamos la clase "RandomGenerator" 
para la generación de una gráfica aleatoria de manera que agregue una cantidad de vértices entre 10 a 50.
De manera que la gráfica generada por "RandomGenerator" agrega aristas aleatoriamente entre 
todos los vértices de la gráfica. 
Una vez que la gráfica se crea, hacemos la verificación del grado de los vértices para colorearlos 
de un color especifico:

- Si tiene un vecino el nodo será azul.
- Si tiene dos vecinos el nodo será rojo.
- Si tiene tres vecinos el nodo será verde.
- Si tiene más de tres vecinos el nodo será amarillo.
- Si tiene no tiene vecinos el nodo será rosa.  