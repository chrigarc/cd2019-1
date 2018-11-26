## Equipo
312224464 Estrada Gómez César Derian
311175318 Muñiz Patiño Andrea Fernanda
312242930 Rivera González Damián

## Práctica 04 - Reloj de Lamport

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

### Implementación (Clases modificadas)

*** CDNode ***
Nos basamos en el pseudocódigo que ya se encontraba en la especificación.
Agregamos dos variables de clase una que indicará el tiempo temporal del nodo 'marca_temporal', y otro para el valor global del algoritmo 'reloj' (ambos de tipo int).
También agregamos dos métodos de asignación y modificación para cada variable.
Realizamos la modificación al método 'sendMessage(Message m)' añadiendole un parametro 'reloj int' para que al enviar un mensaje también se haga envío del valor del reloj actual aumentandole una unidad, y actualizamos el valor temporal del nodo cuando recibe con el valor del reloj global. 
Dentro del método 'run()' agregamos actualizaciones al reloj cuando el nodo recibe un mensaje, asignamos el valor al reloj global con el valor máximo entre su valor actual y el valor de 'marca_temporal' además de aumentarle una unidad al reloj. 
De igual manera hicimos la modificación del texto que se imprime en pantalla para mostrar la actualización de los tiempos del reloj cuando los nodos se envían y reciben.

