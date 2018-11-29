import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.awt.Color;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;

public class Propuesta{


    public static void main(String[] pps){

        SingleGraph graph = new SingleGraph("Propuesta Práctica 4");
        graph.addAttribute("ui.stylesheet", "node{ fill-color: purple; }");
        graph.addNode("A");
        graph.addNode("B");

        graph.addEdge("AB", "A", "B");

        graph.display();
    }

}