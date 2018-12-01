package lab.graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class App{

   public static LinkedList<Node> componentes;

    public static void main(String args[]) {
      componentes = new LinkedList<Node>();
    	Graph graph = new SingleGraph("Random");
        Generator gen = new RandomGenerator(2);
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 100; i++) {
            gen.nextEvents();
        }
        setCSS(graph);
        gen.end();

    Iterator<? extends Node> nodes = graph.getNodeIterator();
    pintaComponente(graph);
    graph.display();
    List<NodeProcess> procs = new ArrayList<NodeProcess>(0);
    procs = creaRed(procs, graph);

  	// inicia el sistema distribuido
  	for(NodeProcess proc : procs) proc.start();
    	// espera a que terminen los procesos
    	boolean vida = true;
    	while(vida){
    		vida = false;
    		for(NodeProcess proc : procs) vida = vida ? true : proc.vida();
    	}
    	for(NodeProcess proc : procs)System.out.println("Proceso " + proc.getUid() + " terminado con " + proc.getexitStatus());
    }

    /**
     * Agrega el color (CSS) a la grafica.
     */
    public static void setCSS(Graph graph){
        String css = "node {fill-color: grey;} "
                    + "node.red {fill-color: red;size: 15px;}"
                    + "node.blue {fill-color: blue;size: 15px;}"
                    + "node.green {fill-color: green;size: 15px;}"
                    + "node.black {fill-color: black; size: 15px;}";
                graph.addAttribute("ui.stylesheet", css);
                graph.addAttribute("ui.quality");
                graph.addAttribute("ui.antialias");
    }
    
    /* pinta las componentes conexas de la grafica */
    private static void pintaComponente(Graph graph){
        Iterator<? extends Node> nodesG = graph.getNodeIterator();
        String[] tipos = {"red", "blue", "green", "black"};
        LinkedList<Integer> nodos = new LinkedList<Integer>();
        int i = 0;
        while(nodesG.hasNext()){
            Node node = nodesG.next();
            if(nodos.contains(node.getIndex())){
                continue;
            }
            node.addAttribute("ui.class", tipos[i%4]);
            componentes.add(node);
            Iterator<? extends Node> nodes = node.getNeighborNodeIterator();
            while(nodes.hasNext()){
                Node next = nodes.next();
                    if(!nodos.contains(next.getIndex())){
                        nodos.add(next.getIndex());
                        nodos = vecinos(next, tipos[i%4], nodos);
                        next.addAttribute("ui.class", tipos[i%4]);
                    }
            }
        i++;
        }
    }

    /* recorre los vecinos en la grafica y los pinta */
    private static LinkedList<Integer> vecinos(Node node, String tipo, LinkedList<Integer> nodos){
        Iterator<? extends Node> nodes = node.getNeighborNodeIterator();
        while(nodes.hasNext()){
            Node next = nodes.next();
                if(!nodos.contains(next.getIndex())){
                    nodos.add(next.getIndex());
                    nodos = vecinos(next, tipo, nodos);
                    next.addAttribute("ui.class", tipo);

                }
        }
        return nodos;
    }

        /**
         * Crea una red enviando mensajes a todos los vecinos
         */
    public static List<NodeProcess> creaRed(List<NodeProcess> procs, Graph graph){
        Node nodo1;
        LinkedList<Integer> nodosVecinos = new LinkedList<Integer>();
        Iterator<? extends Node> nodos = graph.getNodeIterator();
        while (nodos.hasNext()) {
            Node nodo = nodos.next();
            Iterator<? extends Node> vecinos = nodo.getNeighborNodeIterator();
            while (vecinos.hasNext()) {
                nodosVecinos.add(vecinos.next().getIndex());
            }
            procs.add(new NodeProcess(nodo.getIndex(), new HashSet<Integer>(nodosVecinos), new HashSet<Integer>(nodosVecinos)));
        }
        return procs;
    }

}
