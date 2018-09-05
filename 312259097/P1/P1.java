import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

public class P1{
	public static void main(String[] pps){
	Graph graph = new SingleGraph("Tutorial 1");
	graph.addNode("Nodo1");
	graph.addNode("Nodo2");
	graph.addNode("Nodo3");
	graph.addNode("Nodo4");
	graph.addNode("Nodo5");
	graph.addNode("Nodo6");
	graph.addNode("Nodo7");
	graph.addNode("Nodo8");
	graph.addNode("Nodo9");
	graph.addNode("Nodo10");


	graph.addEdge("Arista1", "Nodo1", "Nodo2"); //agregar aristas.
	graph.addEdge("Arista2","Nodo2","Nodo3");
	graph.addAttribute("ui.stylesheet", "node {fill-color:red;} node#Nodo1{fill-color:blue;}"); //agregar color


	graph.display();
	}

}