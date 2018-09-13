import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.stream.SourceBase;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.BaseGenerator;
import org.graphstream.algorithm.generator.RandomGenerator;

import java.util.Random;

public class Practica01 {

	public static void Ejercicio1(){
		Graph grafo1 = new SingleGraph("Ejercicio 1");

		//Creamos 5 Nodos
		grafo1.addNode("A");
		grafo1.addNode("B");
		grafo1.addNode("C");
		grafo1.addNode("D");
		grafo1.addNode("E");

		//Hacemos una gráfica Conexa
		grafo1.addEdge("AB","A","B");
		grafo1.addEdge("BC","B","C");
		grafo1.addEdge("CD","C","D");
		grafo1.addEdge("DE","D","E");
		grafo1.addEdge("EA","E","A");

		//Coloreamos los nodos
		Node n1 = grafo1.getNode("A");
        n1.addAttribute("ui.style", "fill-color: red;");
        Node n2 = grafo1.getNode("B");
        n2.addAttribute("ui.style", "fill-color: blue;");
        Node n3 = grafo1.getNode("C");
        n3.addAttribute("ui.style", "fill-color: yellow;");
        Node n4 = grafo1.getNode("D");
        n4.addAttribute("ui.style", "fill-color: pink;");
        Node n5 = grafo1.getNode("E");
        n5.addAttribute("ui.style", "fill-color: green;");

		grafo1.display();
	}

	public static void Ejercicio2(){
		//Generamos un número Random entre 10 y 29
    	int m = (int) (Math.random() * 10) +10;

		//Nueva gráfica random
		Graph graph = new SingleGraph("Random");
    	Generator gen = new RandomGenerator(2);

    	gen.addSink(graph);
    	gen.begin(); 

    	//Generamos los nodos random dependiendo del número oobtenido anteriormente
    	for(int i=0; i<m; i++)
        	gen.nextEvents();

    	gen.end();

    	/*Hacemos un for a través de cada uno de los Nodos de la Gráfica y 
		 * y coloreamos cada uno de su color correspondiente según su grado
		 */
    	
    	for(Node n:graph) {

			Node nodoActual = n;

			if (nodoActual.getDegree() == 0){
				nodoActual.addAttribute("ui.style", "fill-color: pink;");

			}else if (nodoActual.getDegree() == 1){
				nodoActual.addAttribute("ui.style", "fill-color: blue;");

			}else if (nodoActual.getDegree() == 2){
				nodoActual.addAttribute("ui.style", "fill-color: red;");

			}else if (nodoActual.getDegree() == 3){
				nodoActual.addAttribute("ui.style", "fill-color: green;");

			}else if (nodoActual.getDegree() <= 4){
				nodoActual.addAttribute("ui.style", "fill-color: yellow;");

			}
		}

    	graph.display();
	}

	 public static void main(String[] args) {
		Ejercicio1();
		Ejercicio2();
	}
}

	
