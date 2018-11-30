## Práctica 6 - Ventanas corredizas o corredizas

#####Integrantes y número de cuenta de los miembros del equipo:

Miguel Andres Guevara Castro # 314091057

Erick Gonzalez Duran # 314271103

Rivas Leon Alexis # 314215323

Ángeles Martínez Ángela Janín # 314201009


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
