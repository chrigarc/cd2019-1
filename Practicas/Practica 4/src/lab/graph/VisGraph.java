/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.graph;

import java.util.Iterator;
import lombok.Data;


import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

@Data public class VisGraph {
	public void showWindow(){
		//Generating a random graph
    	Graph graph = new SingleGraph("Random");
    	Generator gen = new RandomGenerator(2);
    	gen.addSink(graph);
    	gen.begin();
    	for(int i=0; i<100; i++)
    	    gen.nextEvents();
    	gen.end();
    	
    	//Add CSS
    	setCSS(graph);
    	
    	//Color last 50 nodes with blue
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
