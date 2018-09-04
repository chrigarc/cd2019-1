/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import java.util.Random;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author Daniel Beltrán Hernández
 */
public class P1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //single graph
        Graph graph = new SingleGraph("Tutorial 1");
        graph.addNode("A" ).setAttribute("ui.style", "fill-color: rgb(255, 0, 0);");
	graph.addNode("B" ).setAttribute("ui.style", "fill-color: rgb(0, 0, 255);");
	graph.addNode("C" ).setAttribute("ui.style", "fill-color: rgb(0,255,0);");
	graph.addNode("D" ).setAttribute("ui.style", "fill-color: rgb(255, 153, 0);");
	graph.addNode("E" ).setAttribute("ui.style", "fill-color: rgb(0, 0, 102);");
	graph.addNode("F" ).setAttribute("ui.style", "fill-color: rgb(0,0,0);");
	graph.addEdge("AB", "A", "B");
	graph.addEdge("AC", "A", "C");
	graph.addEdge("AD", "A", "D");
	graph.addEdge("AE", "A", "E");
	graph.addEdge("AF", "A", "F");
        graph.display();

        //random graph
        Graph graph2 = new SingleGraph("Random");
        Generator gen = new RandomGenerator(2);
        gen.addSink(graph2);
        gen.begin();
        for(int i=0; i<200; i++)
            gen.nextEvents();
        gen.end();
        //graph2.getNodeSet()
        graph2.getNodeSet().forEach((n) -> {

            if(n.getDegree() == 0){
                n.setAttribute("ui.style", "fill-color: rgb(128, 0, 128);");
            }else{
                Random rand = new Random();
                int c1 = rand.nextInt(250);
                int c2 = rand.nextInt(250);
                int c3 = rand.nextInt(250);
                String color = "fill-color: rgb(" + c1+ "," + c2+ "," + c3+ ");";
                n.setAttribute("ui.style", color);
            }
        });
        graph2.display();
    }
}
