import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.awt.Color;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;

/*
* Generamos una gráfica con dos nodos A y B, donde el nodo incial es A y enviará mensajes al nodo final B, 
* al ser unicamente dos nodos se asegura que el nodo inicial y el nodo final siempre serían los mismo y todos los nodos se repetiran en la misma cantidad.
*/
public class Propuesta{


    public static void main(String[] pps){

        SingleGraph graph = new SingleGraph("Propuesta Práctica 3");
        graph.addAttribute("ui.stylesheet", "node{ fill-color: purple; }");
        graph.addNode("A");
        graph.addNode("B");

        graph.addEdge("AB", "A", "B");

        graph.display();
    }

}