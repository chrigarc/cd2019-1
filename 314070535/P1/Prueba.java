import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.*;
import java.awt.Color;

public class Prueba{

	public static void main(String[] pps){
		Graph graph = new SingleGraph("Tutorial");
		Generator gen = new RandomGenerator();
		//gen.addSink(graph);
		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");

		for( Node i : graph.getEachNode()){
			i.addAttribute("ui.label", i.getId());
  		}
		graph.display();


    



	}
}