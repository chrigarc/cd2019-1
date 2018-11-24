/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.awt.Color;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;

public class Main{


    public static void main(String[] pps){

	Graph graph = new SingleGraph("Grafica aleatoria");
	graph.addAttribute("ui.stylesheet", "node.red { fill-color: red; } node.blue { fill-color: blue; } node.green { fill-color: green; }");

	
	Generator gen = new RandomGenerator(3);
	gen.addSink(graph);
	gen.begin();
	while (graph.getNodeCount() < 20 && gen.nextEvents());
	gen.end();

	CDGraph cdg = new CDGraph(graph);
        cdg.start();
        //(new Thread(cdg)).start();
    }
    
}