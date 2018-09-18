import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

public class P1{
	public static void main(String[]args){
		Graph graph = new SingleGraph("Tutorial 1");
		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");
		graph.addNode("D");
		graph.addNode("E");
		graph.addEdge("AB","A","B");
		graph.addEdge("BC","B","C");
		graph.addEdge("CA","C","A");
		graph.addEdge("AE", "A", "E");
		graph.addEdge("ED","E","D");
		Node e1 = graph.getNode("A");
		e1.addAttribute("ui.style","shape:circle;fill-color:yellow;size:30px;");
		Node e2 = graph.getNode("B");
		e2.addAttribute("ui.style","shape:circle;fill-color:red;size:30px;");
		graph.display();
		
	}

}