import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.awt.Color;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;

public class Propuesta{


	SingleGraph graph = new SingleGraph("Práctica 3");
        // Termina la hoja de estilo para que las computaciones equivalentes tengan los nodos de diferentes colores
        graph.addAttribute("ui.stylesheet", "node.red { fill-color: red; } node.blue { fill-color: blue; } node.green { fill-color: green; }");
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");


        graph.addEdge("GH", "G", "H");
        graph.addEdge("HF", "H", "F");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("AE", "A", "E");
        graph.addEdge("BE", "B", "E");
        graph.addEdge("BF", "B", "F");
        graph.addEdge("CD", "C", "D");
        graph.addEdge("EF", "E", "F");
        graph.addEdge("DF", "D", "F");

        CDGraph cdg = new CDGraph(graph);
        cdg.setSource("A");
        cdg.setDestination("D");
        cdg.start();

}
