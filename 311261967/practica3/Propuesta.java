import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.awt.Color;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;

public class Propuesta{

    public static void propuesta(){
	SingleGraph graph = new SingleGraph("Propuesta");
        graph.addAttribute("ui.stylesheet", "node{ fill-color: pink; }");
        graph.addNode("A");
        graph.addNode("B");

        graph.addEdge("AB", "A", "B");

	CDGraph cdg = new CDGraph(graph);
        cdg.setSource("A");
        cdg.setDestination("D");
        cdg.start();
    }
    
}
