/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package lab.graph;


import com.sun.org.apache.bcel.internal.generic.GOTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import static jdk.internal.org.objectweb.asm.Opcodes.GOTO;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class App{
    
    protected static ConcurrentHashMap<Integer,NodeProcess> nodosPasados = new ConcurrentHashMap<Integer, NodeProcess>();
    protected static ConcurrentHashMap<Integer,Message> mensajesEnviados = new ConcurrentHashMap<Integer,Message>();
    protected static ConcurrentHashMap<Integer,Message> mensajesCompletos = new ConcurrentHashMap<Integer,Message>();
    
    private static List<NodeProcess> procs1 = new ArrayList<NodeProcess>(0);
    
    public static void main(String args[]) {
        Graph graph = new SingleGraph("Random");
        Generator gen = new RandomGenerator(10);
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 10; i++) {
            gen.nextEvents();
        }
        setCSS(graph);
        gen.end();
        procs1 = creaRed(procs1,graph);
        List<NodeProcess> procs;
        procs = procs1;
        
        //Run distributed system
        int w, procesos,m,n;
        w = procesos = m = n = 0;
        boolean seEnviaronTodos = false;
        boolean ventana = false;
        while(!seEnviaronTodos || !ventana){
            if(!ventana == true){
                for (m = n; m < n+10; m++) {
                    if (m == procs.size())
                        break;
                    procs.get(m).start();
                }
                ventana = false;
            }
            //Wait until all processes are finished
            
            boolean isAlive = true;
            while(isAlive){
                /*if(w++ % 250000 == 0)
                System.out.println("Mensajes enviados: "+ Math.round(((double)(w-1)/1250000)*80));*/
                isAlive = false;
                for(m = n; m < n+10; m++){
                    if(m == procs.size())
                        break;
                    if(!isAlive){
                        isAlive = procs.get(m).isAlive();
                    }
                    
                }
            }
            if(sigueVentana(mensajesEnviados)){
                ventana = true;
            }
            
            if(m == procs.size())
                break;
            n = m;
        }
        pintaRed(graph,procs);
        //No mostramos la gráfica.
        graph.display();
        System.out.println(mensajesCompletos.size());
        for(Message msg : mensajesCompletos.values()){
            System.out.println("Mensaje id= "+msg.getId()+ " Llegó al destino:"+msg.isArrivo());
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
    
    private static boolean sigueVentana(ConcurrentHashMap<Integer,Message> conjunto){
        if(conjunto.isEmpty())
            return true;
        for(Message msg : conjunto.values()){
            if(msg.isArrivo()){
                continue;
            }else{
                if(msg.intentos == 0)
                    continue;
                else{
                    msg.intentos = msg.intentos-1;
                    msg.setTTL(10);
                    System.out.println("Msg: "+msg.getId()+" NodeI: "+msg.getInitialNode()+" NodeF: "+msg.getFinalDestUID()+ " Intentos: "+msg.getIntentos());
                    nodosPasados.get(msg.getInitialNode()).enviaMsg(msg);
                    return false;
                }
            }
        }
        //conjunto.clear();
        return true;
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
            NodeProcess pro = new NodeProcess(Integer.parseInt(nodo.getId()), new HashSet<Integer>(nodosVecinos), new HashSet<Integer>(nodosVecinos));
            procs.add(pro);
            nodosPasados.put(pro.getUid(), pro);
        }
        return procs;
    }
    
}