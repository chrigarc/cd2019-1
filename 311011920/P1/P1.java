import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class P1 {
	public static void main(String[] args) {

		System.out.println("TE AMO MUCHO, LIZ");
		Graph graph = new SingleGraph("Tutorial 1");

		graph.addNode("A" );
		graph.addNode("B" );
		graph.addNode("C" );
		graph.addEdge("AB", "A", "B");
		graph.addEdge("BC", "B", "C");
		graph.addEdge("CA", "C", "A");

		graph.display();
	}
}