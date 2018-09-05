import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
public class P1{
	
	public static void main(String[] pps){

		Graph graph = new SingleGraph("Tutorial 1");

		
		graph.addNode("Nodo1"); //agregar nodo
		graph.addNode("Nodo2"); //agregar nodo
		graph.addNode("Nodo3"); //agregar nodo
		graph.addNode("Nodo4"); //agregar nodo
		graph.addNode("Nodo5"); //agregar nodo
		graph.addNode("Nodo6"); //agregar nodo
		graph.addNode("Nodo7"); //agregar nodo
		graph.addNode("Nodo8"); //agregar nodo
		graph.addNode("Nodo9"); //agregar nodo
		graph.addNode("Nodo10"); //agregar nodo



		graph.addEdge("Arista1", "Nodo1", "Nodo2"); //Agregar aristas
		graph.addAttribute("ui.stylesheet", "node {fill-color:red;} node#Nodo1{fill-color:blue;}"); // pintar nodo especifico
		//graph.addAttribute("ui.stylesheet", "node {fill-color:red;} "); // pintar nodos





		graph.display(); //




	}





}



// javac -cp ..LISTAJAR P1.java
// java -cp.:LISTAJAR P1

// .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/-algo-1.3.jar
//Class RandomGenerator git pull upstream master




//javac -cp .:../lib/gs-core-1.3.jar P1.java    Compilar
//java -cp .:../lib/gs-core-1.3.jar P1			Ejecutar


//git pull upstream master - Descargar actualizaciones de GitHub
