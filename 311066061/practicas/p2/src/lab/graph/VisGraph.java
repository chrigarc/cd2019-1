package lab.graph;

import java.util.Iterator;


import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class VisGraph {
	public void showWindow(){
    	Graph graph = new SingleGraph("Random");
    	Generator gen = new RandomGenerator(2);
    	gen.addSink(graph);
    	gen.begin();
    	for(int i=0; i<100; i++)
    	    gen.nextEvents();
    	gen.end();
    	setCSS(graph);
    	Iterator<? extends Node> nodes = graph.getNodeIterator();
    	Node node;

    	while(nodes.hasNext()){
    		node =  nodes.next();
    		if(node.getIndex() > 50){
    			node.addAttribute("ui.class", "important");
    		}
    	}

    	graph.display();
	}

	private void setCSS(Graph graph){
    	String css =
    			"node {fill-color: grey;} "
    			+ "node.important {fill-color: red;size: 15px;} ";

    	graph.addAttribute("ui.stylesheet", css);
    	graph.addAttribute("ui.quality");
    	graph.addAttribute("ui.antialias");
    }
}
