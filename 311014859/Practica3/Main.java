import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.awt.Color;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;
/*
* 
* Codigo hecho por Ian Eduardo Chavez Munioa.
*
*
*/


public class Main{


    public static void main(String[] args){

        SingleGraph grafica = new SingleGraph("Práctica 3");
        grafica.addAttribute("ui.stylesheet", "node.red { fill-color: red; } node.blue { fill-color: blue; } node.green { fill-color: green; }");
        grafica.addNode("C");
        grafica.addNode("F");
        grafica.addNode("A");
        grafica.addNode("B");
        grafica.addNode("D");
        grafica.addNode("E");


        grafica.addEdge("AB", "A", "B");
        grafica.addEdge("AE", "A", "E");
        grafica.addEdge("BE", "B", "E");
        grafica.addEdge("BF", "B", "F");
        grafica.addEdge("CD", "C", "D");
        grafica.addEdge("EF", "E", "F");
        grafica.addEdge("DF", "D", "F");

        CDGraph cdGrafica = new CDGraph(grafica);
        cdGrafica.setSource("A");
        cdGrafica.setDestination("D");
        cdGrafica.start();
    }

}