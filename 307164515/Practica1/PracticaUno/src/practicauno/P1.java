/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicauno;

import java.util.Iterator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author inquisidor
 */
public class P1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph graph2 = new SingleGraph("Tutorial 1");
        graph2.addNode("A");
        graph2.addNode("B");
        graph2.addNode("C");
        graph2.addNode("D");
        graph2.addNode("E");

        graph2.addEdge("AB", "A", "B");
        graph2.addEdge("AC", "A", "C");
        graph2.addEdge("AD", "A", "D");
        graph2.addEdge("AE", "A", "E");
        graph2.addEdge("BC", "B", "C");
        graph2.addEdge("CD", "C", "D");

        Node e1 = graph2.getNode("A");
        e1.addAttribute("ui.style", "fill-color: red;");
        Node e2 = graph2.getNode("B");
        e2.addAttribute("ui.style", "fill-color: blue;");
        Node e3 = graph2.getNode("C");
        e3.addAttribute("ui.style", "fill-color: yellow;");
        Node e4 = graph2.getNode("D");
        e4.addAttribute("ui.style", "fill-color: pink;");
        Node e5 = graph2.getNode("E");
        e5.addAttribute("ui.style", "fill-color: green;");
        graph2.display();
        
        String[] colors = {"blue", "green", "yellow", "orange", "purple", "grey", "pink", "brown", "violet", "golden", "silver"};
        Graph graph = new SingleGraph("Random");
        Generator gen = new RandomGenerator(2);
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 50; i++) {
            gen.nextEvents();
        }

        gen.end();

        Iterator<? extends Node> nodes = graph.getNodeIterator();
        Node node;

        while (nodes.hasNext()) {
            node = nodes.next();
            if (node.getDegree() == 0) {
                node.addAttribute("ui.style", "fill-color: rgb(85, 49, 107);");
            }
            if (node.getDegree() >= 1) {
                node.addAttribute("ui.style", "fill-color: " + colors[(int) (Math.random() * colors.length)] +";");
            }
        }
        graph.display();
    }

}
