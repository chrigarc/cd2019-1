import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import org.graphstream.stream.SourceBase;

import org.graphstream.algorithm.generator.BaseGenerator;
import org.graphstream.algorithm.generator.RandomGenerator;

import java.util.Random;

public class P1 {

	public static void Ejercicio1(){
		Graph grafo1 = new SingleGraph("P1-EJ1");

			grafo1.addNode("A");
			grafo1.addNode("B");
			grafo1.addNode("C");
			grafo1.addNode("D");
			grafo1.addNode("E");

			grafo1.addEdge("AB", "A", "B");
			grafo1.addEdge("BC", "B", "C");
			grafo1.addEdge("CD", "C", "D");
			grafo1.addEdge("DE", "D", "E");
			grafo1.addEdge("EA", "E", "A");

			grafo1.getNode("A").addAttribute("ui.class", "rojo");
			grafo1.getNode("B").addAttribute("ui.class", "amarillo");
			grafo1.getNode("C").addAttribute("ui.class", "azul");
			grafo1.getNode("D").addAttribute("ui.class", "verde");
			grafo1.getNode("E").addAttribute("ui.class", "negro");

			String css = "node.rojo{fill-color:red;}" + "node.azul{fill-color:blue;}" + "node.verde{fill-color:green;}" + "node.amarillo{fill-color:yellow;}" ;
			grafo1.addAttribute("ui.stylesheet", css);

			grafo1.display();
	}

	public static void Ejercicio2(){

		Graph grafo2 = new SingleGraph("P1-EJ2");
		RandomGenerator g2 = new RandomGenerator(2);
		g2.addSink(grafo2);
		g2.begin();

		int numNodos = (int)(Math.random() * 20 + 11);

		for(int i=0; i <= numNodos; i++){
			g2.nextEvents();
		}
			g2.end();

		for(Node n:grafo2){
			if(n.getDegree() == 1){
				n.addAttribute("ui.class", "azul");
			}else if ( n.getDegree() == 2){
				n.addAttribute("ui.class", "rojo");
			}else if ( n.getDegree() == 3){
				n.addAttribute("ui.class", "verde");
			}else if ( n.getDegree() > 3) {
				n.addAttribute("ui.class", "amarillo");
			}else {
				n.addAttribute("ui.class", "rosa");
			}
		}

		String css = "node.rojo{fill-color:red;}" + "node.azul{fill-color:blue;}" + "node.verde{fill-color:green;}" + "node.amarillo{fill-color:yellow;}" + "node.rosa{fill-color:pink;}" ;
		grafo2.addAttribute("ui.stylesheet", css);

		grafo2.display();
	}

	public static void main(String[] args) {
		Ejercicio1();
		Ejercicio2();
	}
}