
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


***** Implementación - Clases Modificadas *****

*** Message ***
Se tuvo que cambiar el tipo de Object a LinkedList<String> de la variable 'recorrido' con el fin de capturar
los identificadores de los nodos por los cuales el mensaje realiza su recorrido.
En el constructor, a 'recorrido' se le agregó los nodos 'source' y 'destination'.
También, a 'recorrido' se le implementó su respectivo getter y setter.

*** CDNode ***
Se le agregaron dos variables de tipo LinkedList<Message>: 'recibidos' y 'recibidosDestinatario', esta última se almacenan los mensajes que han llegado al nodo destino.

En el método run(), se hizo la implementación necesaria para que un nodo pueda enviar mensajes a sus vecinos y, también si recibe un mensaje, poder reenviarlo.

Se agregó el getter getRecibidoDestinatario() para poder obtener los mensajes que llegaron a su destino. 

*** CDGraph ***
Aquí implementamos lo necesario para desplegar la gráfica con las computaciones equivalentes.
Se utilizan dos LinkedList<Message>:
	'completados' - Mensajes que llegaron a su destino.
	'compEquivalentes' - Guarda las computaciones equivalentes.

Para localizar las computaciones equivalentes, se realiza la verificación en 'completados'. Para ello, se realiza en cada mensaje una comparación sobre su lista 'recorrido'. Mapeamos la lista a un HashSet para poder comparar los recorrdos entre mensajes diferentes, cuando dos recorridos sean iguales, guardamos el mensaje que se esta comparando a la lista de 'compEquivalentes' siempre y cuando el mensaje no se encuentre previamente para agregar computaciones diferentes.

Una vez obtenidas las computaciones las graficamos. Creamos una gráfica.
Una vez que tengamos la lista de computaciones equivalentes, hacemos un recorrido sobre su respectiva lista 'recorrido' y se agrega un nodo a la gráfica por cada elemento del recorrido.
También por cada dos nodos consecutivos agregamos una arista que una dichos nodos.
Al terminar de recorrer toda la lista de computaciones y graficar cada recorrido mostramos la gráfica.