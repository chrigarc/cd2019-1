
## Equipo
312224464 Estrada Gómez César Derian
311175318 Muñiz Patiño Andrea Fernanda
312242930 Rivera González Damián


## Práctica 3 - Computaciones equivalentes
### Marco teorico
Una computación equivalante son todas aquellas donde todos los nodos se repiten en cantidad de pasos, y donde los nodos iniciales son los mismos y los nodos finales son los mismos

### Material
Se entregan las clases que simulan el envio por broadcast desde un nodo inicial A hasta un nodo final D

### Ejercicios
* Realizar los cambios en las clases para poder localizar todas aquellas computaciones que son equivalentes.
* Generar una nueva grafica que muestre todas las computaciones que generaron una computación equivalente
* En la clase propuesta generar una grafica diferente a la del ejercicio que esten seguros que genere computaciones equivalentes


**** Implementación

** Modificaciones a la Clase Message.
Se tuvo que modificar la variable 'recorrido' de Object a LinkedList<String> para ir capturando los identificadores de los nodos por los que va pasando el mensaje.
A este mismo recorrido se le agrego desde un inicio los identificadores de los nodos 'source' y 'destination', además se agregaron dos métodos uno de acceso y uno de asignación para la misma variable (recorrido).

(la clase Transport no se modificó)

** Modificaciones a la clase CDNode.
Se le agregaron dos variables de tipo LinkedList<Message> una lista llamada 'recibidos' (la cual era necesaria para la compilación del programa, desde un incio hacia falta esa variable) y una lista llamada 'recibidosDestinatario' en la cual se agregarán los mensajes que llegen a su nodo destino.

Dentro del método 'run()', se hace la implementación de enviar un mensaje desde un nodo hacia sus vecinos y en caso de recibir uno reenviarlo a sus vecinos. 
Se agrego una línea para guardar los mensajes a la lista 'recibidoDestinatario' que son mensajes han llegado a su nodo destino. En caso contrario se reenvia un clon del mensaje recibido actualizando la lista de su recorrido y agregando el nodo por el que ha pasado a su lista de recorrido.

Se agregó un Método 'getRecibidoDestinatario()' es un método de acceso para poder obtener los mensajes que llegaron a su destino. 

** Modificaciones a la clase CDGraph.
Para hacer el despliegue de la gráfica con las computaciones equivalentes.
Se utilizan dos listas de mensajes, una llamada 'completados' que son los mensajes que llegaron a su destion, y otra lista 'compEquivalentes' dondes se guardaran las computaciones equivalentes.
Primero se realiza la verificación entre los mensajes que llegaron a su destino. Para ello se hace la comparación sobre su lista 'recorrido' de cada mensaje. Mapeamos la lista a un HashSet para poder comparar los recorrdos entre mensajes diferentes, cuando dos recorridos sean iguales,  guardamos el mensaje que se esta comparando a la lista de 'compEquivalentes' siempre y cuando el mensaje no se encuentre previamente para agregar computaciones diferentes.

Una vez obtenidas las computaciones procedemos a graficarlas.
Ya que tenemos la lista de computaciones, vamos recorriendo su lista de 'recorrido' y se agrega un nodo a la gráfica por cada elemento del recorrido, además por cada dos nodos consecutivos agregamos una arista que una dichos nodos. Al final de recorrer toda la lista de computaciones y graficar cada recorrido desplegamos la gráfica.


**** Propuesta
Generamos una gráfica con dos nodos A y B, donde el nodo incial es A y enviará mensajes al nodo final B, al ser unicamente dos nodos se asegura que el nodo inicial y el nodo final siempre serían los mismo y todos los nodos se repetiran en la misma cantidad.