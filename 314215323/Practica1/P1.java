import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.*;

public class P1{

    public static void main(String[] args){
	
	Graph graph = new SingleGraph("Tutorial 1");

	graph.addNode("a");
	graph.addNode("b");
	graph.addNode("c");
	graph.addNode("d");
	graph.addNode("e");

	graph.addEdge("ab","a","b");
	graph.addEdge("cd","c","d");	
	graph.addEdge("de","d","e");
	graph.addEdge("da","d","a");
	
	graph.addEdge("ea","e","a");
	graph.addEdge("bc","b","c");

	Node e1 = graph.getNode("a");
	e1.addAttribute("ui.style","shape:circle;fill-color:blue;size:30px;");
	Node e2 = graph.getNode("b");
	e2.addAttribute("ui.style","shape:circle;fill-color:red;size:30px;");
	Node e3 = graph.getNode("c");
	e3.addAttribute("ui.style","shape:circle;fill-color:yellow;size:30px;");
	Node e4 = graph.getNode("d");
	e4.addAttribute("ui.style","shape:circle;fill-color:orange;size:30px;");
	Node e5 = graph.getNode("e");
	e5.addAttribute("ui.style","shape:circle;fill-color:brown;size:30px;");
	graph.display();
    }
}
