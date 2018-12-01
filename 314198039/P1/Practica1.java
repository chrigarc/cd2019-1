import java.util.Iterator;
import java.util.Random;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;


public class Practica1 {

	public static void graficaEjercicio1() {
        
        Graph grafica = new SingleGraph("Computación Distribuida - Práctica 1");
        
        grafica.addAttribute("ui.stylesheet", 
                "node {size: 30px, 30px; }"
                + "node#A { fill-color: orange; }"
                + "node#B { fill-color: red; }"
                + "node#C { fill-color: yellow; }"
                + "node#D { fill-color: blue; }"
                + "node#E { fill-color: grey; }");
        
        grafica.addNode("A");
        grafica.addNode("B");
        grafica.addNode("C");
        grafica.addNode("D");
		grafica.addNode("E");
		

        grafica.addEdge("A1", "A", "C");
        grafica.addEdge("A2", "A", "B");
		grafica.addEdge("A3", "B", "C");
        grafica.addEdge("A4", "A", "D");
        grafica.addEdge("A5", "A", "E");
        grafica.addEdge("A6", "D", "E");
        grafica.addEdge("A7", "B", "D");
        grafica.addEdge("A8", "B", "E");
        grafica.addEdge("A9", "C", "D");
        grafica.addEdge("A10", "C", "E");
        
        
        graph.display();
    }
    
    public static void randomGraphEj2() {
    
        int nodesNum = 20;
        Random rand= new Random();
        
        Graph graph = new SingleGraph("Random");
        
        grafica.addAttribute("ui.stylesheet", 
                "node {size: 30px, 30px; }");
        
        Generator generador = new RandomGenerator();
        generador.addSink(graph);
        
        generador.begin();
        while (grafica.getNodeCount() < nodesNum && gen.nextEvents());
        generador.end();

        
        Iterator it = grafica.getNodeIterator();
        Node node;
        
        while (it.hasNext()) {
            node = (Node) it.next();
            
            if (node.getDegree() == 0) {
                node.addAttribute("ui.style", "fill-color: magenta;");
            } else {
                node.addAttribute("ui.style", "fill-color: rgb(" + 
                        rand.nextInt(256) + "," + 
                        rand.nextInt(256) + "," + 
                        rand.nextInt(256) + ");");
            }
            
        }
        
        
        grafica.display();
        
    }
    
    
    public static void main(String[] args) {
        
        graficaEjercicio1();
        
        randomGraphEj2();
        
    }
    
    
}