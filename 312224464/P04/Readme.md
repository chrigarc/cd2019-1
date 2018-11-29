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
De acuerdo al pseudocódigo, implementamos las variables de clase: reloj (int) y marca_temporal(int), así como sus respectivos getter y setter.
Modificamos el método sendMessage() para que, además del mensaje, reciba el valor del reloj. Es necesario aclarar que el método sendMessage() va a actualizar el valor del reloj.
En el método run(), también se actualizó la parte de la recepción del mensaje de acuerdo al pseudocódigo.
Para obtener el max(marca_temporal_mensaje,reloj), nos auxiliamos de la biblioteca Math de Java, que ya tiene un método max() incluido.
Por último, modificamos el método getText() para mostrar, en todo momento, el valor del reloj en la pantalla.

