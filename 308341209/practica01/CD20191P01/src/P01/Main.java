package P01;

import java.util.Iterator;
import java.util.Random;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;


public class Main {

    public static void exercise1() {
        
        Graph graph = new SingleGraph("Computación Distribuida - Práctica 1");
        
        graph.addAttribute("ui.stylesheet", 
                "node {size: 30px, 30px; }"
                + "node#A { fill-color: red; }"
                + "node#B { fill-color: blue; }"
                + "node#C { fill-color: magenta; }"
                + "node#D { fill-color: yellow; }"
                + "node#E { fill-color: green; }");
        
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        
        graph.addEdge("E1", "A", "B");
        graph.addEdge("E2", "A", "C");
        graph.addEdge("E3", "A", "D");
        graph.addEdge("E4", "A", "E");
        graph.addEdge("E5", "B", "C");
        graph.addEdge("E6", "B", "D");
        graph.addEdge("E7", "B", "E");
        graph.addEdge("E8", "C", "D");
        graph.addEdge("E9", "C", "E");
        graph.addEdge("E10", "D", "E");
        
        graph.display();
    }
    
    public static void exercise2() {
    
        int nodesNum = 20;
        Random rndm= new Random();
        
        Graph graph = new SingleGraph("Random");
        
        graph.addAttribute("ui.stylesheet", 
                "node {size: 30px, 30px; }");
        
        Generator gen = new RandomGenerator();
        gen.addSink(graph);
        
        gen.begin();
        while (graph.getNodeCount() < nodesNum && gen.nextEvents());
        gen.end();

        
        Iterator it = graph.getNodeIterator();
        Node node;
        
        while (it.hasNext()) {
            node = (Node) it.next();
            
            if (node.getDegree() == 0) {
                node.addAttribute("ui.style", "fill-color: magenta;");
            } else {
                node.addAttribute("ui.style", "fill-color: rgb(" + 
                        rndm.nextInt(256) + "," + 
                        rndm.nextInt(256) + "," + 
                        rndm.nextInt(256) + ");");
            }
            
        }
        
        
        graph.display();
        
    }
    
    
    public static void main(String[] args) {
        
        exercise1();
        
        exercise2();
        
    }
    
    
}
