import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import org.graphstream.stream.SourceBase;

import org.graphstream.algorithm.generator.BaseGenerator;
import org.graphstream.algorithm.generator.RandomGenerator;

import java.util.Random;

public class Grafica02{

	public static void main(String[] args) {
		
		Graph graph = new SingleGraph("Graph 2");
		RandomGenerator random_gen = new RandomGenerator();
		random_gen.addSink(graph);
		random_gen.begin();

		//Producimos un número aleatorio entre 10 y 50.
		//Este número indicará el número de nodos de nuestra gráfica.
		Random random = new Random();
		int r = random.nextInt(40)+11;

		//Creamos los nodos
		for(int i=0; i<r; i++){
			random_gen.nextEvents();
		}
		random_gen.end();

		//Checamos el grado de cada nodo
		for(int i=0; i < graph.getNodeCount(); i++){

			//Si tiene un vecino el nodo será azul.
			if (graph.getNode(i).getDegree() == 1){
				graph.getNode(i).addAttribute("ui.style", "fill-color: rgb(7,3,61);");

			//Si tiene dos vecinos el nodo será rojo.
			} else if (graph.getNode(i).getDegree() == 2){
				graph.getNode(i).addAttribute("ui.class", "rojo");

			//Si tiene tres vecinos el nodo será verde.
			} else if (graph.getNode(i).getDegree() == 3){
				graph.getNode(i).addAttribute("ui.style", "fill-color: rgb(76,145,65);");

			//Si tiene más de tres vecinos el nodo será amarillo.
			} else if (graph.getNode(i).getDegree() >= 3){
				graph.getNode(i).addAttribute("ui.class", "amarillo");

			//En otro caso el nodo será rosa.
			} else {
				graph.getNode(i).addAttribute("ui.style", "fill-color: rgb(230,50,68);");
			}
		}

		String css = "node.rojo{fill-color:red;}" + "node.azul{fill-color:blue;}" + "node.verde{fill-color:green;}" + "node.amarillo{fill-color:yellow;}" ;
		graph.addAttribute("ui.stylesheet", css);
		
		graph.display();
	}
}