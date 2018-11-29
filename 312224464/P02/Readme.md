Equipo
312224464 Estrada Gómez César Derian
311175318 Muñiz Patiño Andrea Fernanda
312242930 Rivera González Damián

# Práctica 2 - Broadcast

### Objetivo
Realizar simulaciones de envio por broadcast en una red de nodos 

### Marco teórico
En Informática, la difusión amplia, difusión ancha o broadcast, es una forma de transmisión de información donde un nodo emisor envía información a una multitud de nodos receptores de manera simultánea, sin necesidad de reproducir la misma transmisión nodo por nodo. 

### Material
- CDNode.java
- CDGraph.java
- Message.java
- Trasporte.java
- Main.java

### Ejercicios
1. Actualizar el repositorio
```
git pull upstream master
```
2. Implementar el codigo faltante en cada una de las clases que se encuentran en dentro la práctica 2
3. Subir los cambios al repositorio y realizar un pull request


## Apendice
``` 
javac -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar Main.java
java -cp .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar Main
```

***** Implementación - Clases modificadas *****

*** CDGraph ***
Para detener los hilos que se encuentran en ejecución, en el método stopAction() realizamos una llamada a la función stop().

*** CDNode ***

¿Qué necesitas que haga para que un nodo genere un mensaje en cada iteración y lo mande a todos sus vecinos, y despues lea un mensaje de y lo reenvie a todos sus vecinos siempre y cuando su tiempo de vida, del mensaje, lo permita?

Mientras el atributo 'activo' de un nodo de tipo CDNode tenga valor 'true', creamos un iterador del nodo para el envío de mensajes hacia todos sus vecinos.
Realizamos lo siguiente: Por cada iteración sobre un vecino, creamos un mensaje, y mediante la función sendMessage(), realizamos el envío del mensaje; dormimos el proceso para que la gráfica pueda mostrar los cambios

Para el reenvío de mensajes realizamos lo siguiente. Mediante la función readMessage(), recibimos el mensaje, hacemos un verificación de que el mensaje no sea nulo y su tiempo de vida sea mayor o igual 0. Después, creamos un nuevo iterador del nodo para reenviar el mensaje hacia todos sus vecinos auxiliándonos de la función sendMessage(), también, es importante señalar que por cada reenvío actualizamos el tiempo de vida del mensaje; dormimos el proceso para que la gráfica pueda mostrar los cambios.