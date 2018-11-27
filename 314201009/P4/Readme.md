## Práctica 04 - Reloj de Lamport

### INTEGRANTES

PARA EJECUTAR:

make exec

(desde dentro de la carpeta de la práctica, la carpeta "lib" de las bibliotecas debe estar en el mismo nivel
que las carpetas de las prácticas)

Integrantes y número de cuenta de los miembros del equipo:

Ángeles Martínez Ángela Janín | 314201009

Guevara Castro  Miguel Andres | 314091057

González Durán Érick 	        | 314271103

Rivas León Alexis             | 314215323

### Reloj de Lamport

El algoritmo de los tiempos lógicos de Lamport, es un algoritmo simple usado para determinar el orden de los eventos en un Sistema Distribuido Informático. Este algoritmo se denomina así debido a que su creador fue uno de los científicos de la computación más relevantes, Leslie Lamport. Como sucede de manera habitual en un Sistema Distribuido, los diferentes nodos o procesos no estarán perfectamente sincronizados, por ello este algoritmo es usado para proporcionar un ordenamiento parcial de los eventos con una mínima sobrecarga de los recursos computacionales.

### Algoritmo

El algoritmo sigue las siguientes reglas:

Un proceso incrementa su contador antes de cada evento que ocurra en ese proceso.
Cuando un proceso envía un mensaje, este incluye su contador en el envío.
Al recibir un mensaje, se actualiza el contador del receptor si es necesario, al mayor entre su propio contador y la marca de tiempo recibida en dicho mensaje.
En pseudocódigo el algoritmo para enviar un mensaje es:
```
reloj = reloj + 1;
marca_temporal_mensaje = reloj;
enviar(mensaje, marca_temporal_mensaje);
```
Algoritmo para la recepción del mensaje:
```
recibir() = (mensaje, marca_temporal_mensaje);
reloj = max(marca_temporal_mensaje,reloj) + 1;
```

### Ejercicios

1. Utilizando el código de la práctica anterior hacer las modificaciones necesarias para que cada nodo implemente el algoritmo del Reloj de Lamport
