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

**** Implementación

*** Modificaciones para el archivo CDGraph.java

¿Qué necesitas que haga para detener todo?

En el método 'stopAction()' para detener todos los hilos que se encuentran en ejecución,
hacemos la llamada de la función 'stop()', ya que esa función recorre mediante un iterador cada elemento CDNode del conjunto de CDGraph y detiene el hilo ejecutandose por cada CDNode en la gráfica.
Con eso detenemos todos los procesos de la gráfica.

*** Modificaciones para el archivo CDNode.java

¿Qué necesitas que haga para que un nodo genere un mensaje en cada iteración y lo mande a todos sus vecinos, y despues lea un mensaje de y lo reenvie a todos sus vecinos siempre y cuando su tiempo de vida, del mensaje, lo permita?

Mientras la entidad de CDNode se encuentra con la bandera 'activo' con valor de 'true', generamos un iterador de nodo para hacer el envio de mensajes nuevos a todos los vecinos del nodo de cada CDNode, iteramos sobre los vecinos del nodo, creamos un mensaje y hacemos el envio con la función 'sedMessage()', dormimos el proceso para que sea visible los cambios en la gráfica.

Después hacemos el recibo de un mensaje con la función 'readMessage()', hacemos la verficación sobre el mensaje recibido, si no es un mensaje nulo y su tiempo de vida es mayor a cero (para que sea un mensaje válido), creamos un nuevo iterador para reenviar el mensaje recibido sobre los vecinos del nodo de CDNode, actualizamos el tiempo de vida del mensaje recibido y lo enviamos con la función 'sendMessage()'. Después de la iteración dormimos el proceso para que sean visibles los cambios en la gráfica. 



 
