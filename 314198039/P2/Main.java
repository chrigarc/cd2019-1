import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.awt.Color;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;

public class Main{


    public static void main(String[] pps){

	Graph grafica = new SingleGraph("Random graph");
	grafica.addAttribute("ui.stylesheet", "node.red { fill-color: red; }
	 node.blue { fill-color: blue; } 
	 node.green { fill-color: green; }");

	
	Generator generador = new RandomGenerator(3);
	generador.addSink(graph);
	generador.begin();
	while (grafica.getNodeCount() < 20 && generador.nextEvents());
	generador.end();

	CDGraph cdg = new CDGraph(graph);
	(new Thread(cdg)).start();
    }
    
}
