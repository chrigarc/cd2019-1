#Practica 1 - Introducción a Graph

## GraphStream
GraphStream es una biblioteca de Java que se enfoca en los aspectos dinámicos de los gráficas. Su foco principal está en el modelado de redes de interacción dinámica de varios tamaños.

El objetivo de la biblioteca es proporcionar una forma de representar gráficos y trabajar en él. Con este fin, GraphStream propone varias clases de gráficas que permiten modelar gráficas dirigidas y no dirigidas.

GraphStream permite almacenar cualquier tipo de atributo de datos en los elementos de la gráfica: números, cadenas o cualquier objeto.

Además, GraphStream proporciona una forma de manejar la evolución de la gráfica en el tiempo. Esto significa manejar la forma en que los nodos y bordes se agregan y eliminan, y la forma en que los atributos de datos pueden aparecer, desaparecer y evolucionar.

## Preparación de ambiente de trabajo
1. Descargar las tres bibliotecas de GraphStream
2. Crear una carpeta lib dentro de nuestro espacio de trabajo en el repositorio creado en la práctica anterior
3. Extraer solo los .jar de cada biblioteca y colocarlos dentro de la carpeta lib
4. Crear la carpeta para la practica 1 y dentro colocar los fuentes de los siguientes ejercicios.

## Ejercicios

1. Crear una gráfica de al menos 5 nodos, conectados por medio de una arista con al menos otro nodo.
Los nodos deben ser coloreados de colores diferentes
2. Crear una gráfica aleatoria de al menos 10 nodos, es decir que cada que se ejecute el programa se tiene que generar na gráfica diferente.
Los nodos que tengan al menos un color deberan ser coloreados siguiendo las siguientes reglas: si tiene un solo vecino azul, dos vecinos rojo, tres vecinos verde, mas de tres amarillo, y por último ningún vecino rosa.

## Recordatorios importantes
Para compilar
```
javac  -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar
 P1.java
```
Para ejecutar
```
java  -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar
 P1
```
