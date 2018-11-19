/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package lab.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class App{
    
    protected static int m = 0;
    
    protected static ConcurrentHashMap<Integer,LinkedList<Integer>> nodosPasados1 = new ConcurrentHashMap<Integer, LinkedList<Integer>>();
    protected static ConcurrentHashMap<Integer,LinkedList<Integer>> nodosPasados2 = new ConcurrentHashMap<Integer, LinkedList<Integer>>();
    
    private static List<NodeProcess> procs1 = new ArrayList<NodeProcess>(0);
    private static List<NodeProcess> procs2 = new ArrayList<NodeProcess>(0);
    
    public static void main(String args[]) {
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
        procs1 = creaRed(procs1,graph);
        procs2 = creaRed(procs2,graph);
        System.out.println("¿La configuración inicial es igual? "+procs1.equals(procs2));
        while(m < 2){
            List<NodeProcess> procs;
            if(m == 0)
                procs = procs1;
            else
                procs = procs2;
            
            //Run distributed system
            int w, procesos;
            w = procesos = 0;
            for(NodeProcess proc : procs){
                proc.start();
                procesos++;
            }
            
            //Wait until all processes are finished
            boolean isAlive = true;
            boolean showed = false;
            while(isAlive){
                //int i = 0;
                if(w++ % 250000 == 0)
                    System.out.println("Iteraciones del proceso "+ (m+1) +": "+ Math.round(((double)(w-1)/1250000)*100)+"%");
                isAlive = false;
                int i = 1-2;
                for(NodeProcess proc : procs){
                    if(!isAlive){
                        isAlive = proc.isAlive();
                    }
                    if(!proc.isAlive()){
                        i++;
                    }
                }
                if((procesos < (i+25)) && !showed){
                    pintaRed(graph,procs);
                    //No mostramos la gráfica.
                    //graph.display();
                    showed = true;
                }
            }
            m++;
        }
        
        System.out.println("¿Es la primera iteración igual a la segunda? "+nodosPasados1.equals(nodosPasados2));
        System.out.println("Tamaño del subconjunto 1: "+nodosPasados1.size());
        System.out.println("Tamaño del subconjunto 2: "+nodosPasados2.size());
        int listaNum = 0;
        for (LinkedList<Integer> list1 : nodosPasados1.values()) {
            for (LinkedList<Integer> list2 : nodosPasados2.values()) {
                if(list1.equals(list2)){
                    System.out.print((listaNum++)+". El mensaje que pasa por los nodos \"");
                    for(Integer inte : list2)
                        System.out.print(inte+" ");
                    System.out.print("\" aparece en las dos iteraciones. \n");
                }
            }
        }
        
    }
    
    /**
     * Metodo para agregar el color (CSS) a la grafica.
     * @param graph Es la grafica a colorear.
     */
    public static void setCSS(Graph graph){
        String css = "node {fill-color: grey;} "
                + "node.red {fill-color: red;size: 20px;}"
                + "node.blue {fill-color: blue;size: 20px;}"
                + "node.green {fill-color: green;size: 20px;}"
                + "node.yellow {fill-color: yellow; size: 20px;}";
        graph.addAttribute("ui.stylesheet", css);
        
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
    }
    
    //Metodo privado para pintar el estado de los nodos.
    private static void pintaRed(Graph graph,List<NodeProcess> proc){
        Iterator<? extends Node> nodesG = graph.getNodeIterator();
        String[] tipos = {"red", "blue", "green", "yellow"};
        
        while(nodesG.hasNext()){
            Node node = nodesG.next();
            switch(proc.get(Integer.parseInt(node.getId())).getExitState()){
                case 0:
                    node.addAttribute("ui.label", node.getId()+" Estado: "+"Corriendo");
                    node.addAttribute("ui.class", tipos[3]);
                    break;
                case 1:
                    node.addAttribute("ui.label", node.getId()+" Estado: "+"Msg recibidos");
                    node.addAttribute("ui.class", tipos[2]);
                    break;
                case 2:
                    node.addAttribute("ui.label", node.getId()+" Estado: "+"Deadline alcanzado");
                    node.addAttribute("ui.class", tipos[1]);
                    break;
                case 3:
                    node.addAttribute("ui.label", node.getId()+" Estado: "+"Error");
                    node.addAttribute("ui.class", tipos[0]);
                    break;
            }
        }
        
        
    }
 
    /**
     * Metodo para enviar a todos los vecinos mensajes
     * @param procs – son los NodeProcess que se anexan.
     * @param graph – es la grafica.
     * @return Una lista con los nodeProcess.
     */
    public static List<NodeProcess> creaRed(List<NodeProcess> procs, Graph graph){
        Iterator<? extends Node> nodos = graph.getNodeIterator();
        while (nodos.hasNext()) {
            LinkedList<Integer> nodosVecinos = new LinkedList<Integer>();
            Node nodo = nodos.next();
            Iterator<? extends Node> vecinos = nodo.getNeighborNodeIterator();
            while (vecinos.hasNext()) {
                Node vecinosNext = vecinos.next();
                if(vecinosNext.getId().equals(nodo.getId()))
                    continue;
                nodosVecinos.add(Integer.parseInt(vecinosNext.getId()));
            }
            procs.add(new NodeProcess(Integer.parseInt(nodo.getId()), new HashSet<Integer>(nodosVecinos), new HashSet<Integer>(nodosVecinos)));
            
        }
        return procs;
    }
    
}