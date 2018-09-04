import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import org.graphstream.stream.SourceBase;

import org.graphstream.algorithm.generator.BaseGenerator;
import org.graphstream.algorithm.generator.RandomGenerator;

import java.util.Random;

public class Grafica01{

	public static void main(String[] args) {
		
		Graph graph = new SingleGraph("Random Graph");
		
		//Creamos los 5 nodos de la gráfica
		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");
		graph.addNode("D");
		graph.addNode("E");

		//Forzamos a la gráfica a ser conexa
		graph.addEdge("AB","A","B");
		graph.addEdge("AC","A","C");
		graph.addEdge("BC","B","C");
		graph.addEdge("BD","B","D");
		graph.addEdge("BE","B","E");
		graph.addEdge("CE","C","E");

		//Los 5 nodos los coloreamos de un color diferente
		graph.getNode("A").addAttribute("ui.class", "rojo");
		graph.getNode("B").addAttribute("ui.class", "amarillo");
		graph.getNode("C").addAttribute("ui.class", "azul");
		graph.getNode("D").addAttribute("ui.class", "verde");
		graph.getNode("E").addAttribute("ui.class", "negro");

		String css = "node.rojo{fill-color:red;}" + "node.azul{fill-color:blue;}" + "node.verde{fill-color:green;}" + "node.amarillo{fill-color:yellow;}" ;
		graph.addAttribute("ui.stylesheet", css);
		
		graph.display();
	}
}