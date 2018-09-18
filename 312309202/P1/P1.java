import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
public class P1{
	
	public static void main(String[] pps){

		Graph graphT = new SingleGraph("Tutorial 1");
		
		graphT.addNode("Nodo1"); //agregar nodo
		graphT.addNode("Nodo2"); //agregar nodo
		graphT.addNode("Nodo3"); //agregar nodo
		graphT.addNode("Nodo4"); //agregar nodo
		graphT.addNode("Nodo5"); //agregar nodo
		graphT.addNode("Nodo6"); //agregar nodo
		graphT.addNode("Nodo7"); //agregar nodo
		graphT.addNode("Nodo8"); //agregar nodo
		graphT.addNode("Nodo9"); //agregar nodo
		graphT.addNode("Nodo10"); //agregar nodo



		graphT.addEdge("Arista1", "Nodo1", "Nodo2"); //Agregar aristas
		graphT.addAttribute("ui.stylesheet", "node {fill-color:red;} node#Nodo1{fill-color:blue;}"); // pintar nodo especifico
		//graph.addAttribute("ui.stylesheet", "node {fill-color:red;} "); // pintar nodos

		//graphT.display(); //		

		/*1. Crear una gráfica de al menos 5 nodos, conectados por medio de una arista con al menos otro nodo.
		Los nodos deben ser coloreados de colores diferentes*/

		Graph grafica1 = new SingleGraph("Ejercicio 1");

		Node nodoUno = grafica1.addNode("nodoUno");
		Node nodoDos = grafica1.addNode("nodoDos");
		Node nodoTres = grafica1.addNode("nodoTres");
		Node nodoCuatro= grafica1.addNode("nodoCuatro");	
		Node nodoCinco = grafica1.addNode("nodoCinco");

		grafica1.addEdge("arista1", "nodoUno", "nodoDos");
		grafica1.addEdge("arista2", "nodoDos", "nodoTres");
		grafica1.addEdge("arista3", "nodoTres", "nodoCuatro");
		grafica1.addEdge("arista4", "nodoCuatro", "nodoCinco");
		grafica1.addEdge("arista5", "nodoUno", "nodoTres");

		grafica1.addAttribute("ui.stylesheet", "node#nodoUno{fill-color:blue;} node#nodoDos{fill-color:green;} node#nodoTres{fill-color:grey;} node#nodoCuatro{fill-color:yellow;}"  );

		grafica1.display();

		/*2. Crear una gráfica aleatoria de al menos 10 nodos, es decir que cada que se ejecute el programa se tiene que generar 
		una gráfica diferente.

		Los nodos que tengan al menos un color deberan ser coloreados siguiendo las siguientes reglas: si tiene un solo vecino azul,
		dos vecinos rojo, tres vecinos verde, mas de tres amarillo, y por último ningún vecino rosa.
		*/

		Graph grafica2 = new SingleGraph("Ejercicio 2");

		Node nodo1 = grafica2.addNode("nodo1");
		grafica2.addNode("nodo2");		
		grafica2.addNode("nodo3");
		grafica2.addNode("nodo4");
		grafica2.addNode("nodo5");
		grafica2.addNode("nodo6");
		grafica2.addNode("nodo7");		
		grafica2.addNode("nodo8");
		grafica2.addNode("nodo9");
		grafica2.addNode("nodo10");

		grafica2.addEdge("arista1", "nodo1", "nodo2");
//		System.out.println(nodo1.getInDegree());

		if(nodo1.getInDegree() == 1){
			nodo1.addAttribute("ui.class","azul");
		}

		grafica2.addAttribute("ui.stylesheet", "node.azul{fill-color:blue;}");	

		//grafica2.display();
	}
}

// javac -cp ..LISTAJAR P1.java
// java -cp.:LISTAJAR P1

// .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/-algo-1.3.jar
//Class RandomGenerator git pull upstream master

//javac -cp .:../lib/gs-core-1.3.jar P1.java    Compilar
//java -cp .:../lib/gs-core-1.3.jar P1			Ejecutar


//git pull upstream master - Descargar actualizaciones de GitHub
