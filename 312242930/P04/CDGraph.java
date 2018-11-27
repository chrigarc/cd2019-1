import org.graphstream.graph.Graph;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import org.graphstream.graph.Node;
import java.util.concurrent.ConcurrentLinkedDeque;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.View;
import org.graphstream.graph.implementations.SingleGraph;//agregamos la dependecia para crear las computaciones equivalentes
import java.util.LinkedList;
import java.awt.Color;


public class CDGraph extends Thread{

    private Graph graph;
    private Set<CDNode> nodes;
    private ConcurrentLinkedDeque<String[]> list;
    private boolean active;
    private JFrame frame;
    private String source;
    private String destination;
    private CDNode general;//agregamos un CDNode con el que trabajaremos para optener las computaciones equivalentes

    public CDGraph(Graph g){
        this.graph = g;
        this.nodes = new HashSet<CDNode>();
        list = new ConcurrentLinkedDeque<String[]>();
        this.active = true;
    }

    public void setSource(String source){
        this.source = source;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    public void run(){
        graph.display();
        for( Node i : graph.getEachNode() ){
            i.addAttribute("ui.label", i.getId());
            CDNode cdn = null;
            //cambio entre las lineas 52 y 53
                cdn = new CDNode(this, i, CDNode.Type.SOURCE);
            if(i.getId().equals(source)){
            }else if(i.getId().equals(destination)){
                cdn = new CDNode(this, i, CDNode.Type.DESTINATION);
                general = cdn;//Cada uno nodo sea el mismo que el valor destion actaulizamos el CDNode general. 
            }else{
                cdn = new CDNode(this, i);
            }
            new Thread(cdn).start();
            nodes.add(cdn);
        }
        this.createFrame();
        while(active || !list.isEmpty()){
            while(!list.isEmpty()){
                String[] tmp = list.poll();
                graph.getNode(tmp[0]).setAttribute("ui.class",  tmp[1]);
            }
            sleep(100);
        }
    }

    public void addChangeColor(String nodeId, String color){
        list.add(new String[]{nodeId, color});
    }

    private void createFrame(){
        frame = new JFrame("Práctica 4");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla

        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(null);

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 517, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 342, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
        );

        frame.pack();

        JButton button = new JButton("Detener");
        button.setSize(800, 30);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stopAction();
            }
        });
        jPanel1.add(button);

        Iterator<CDNode> iterator = nodes.iterator();
        int y = 30;
        while(iterator.hasNext()){
            JComponent component = iterator.next();
            component.setSize(760, 30);
            component.setLocation(30, y);
            y+=35;
            jPanel1.add(component);
        }
        frame.setVisible(true);

    }

    private void sleep(int ms){
        try{
            Thread.sleep(ms);
        }catch(Exception ex){
        }
    }

    public void stopAll(){
        Iterator<CDNode> iterator = nodes.iterator();
        while(iterator.hasNext()){
            iterator.next().stop();
        }
        active = false;
        while(this.isAlive()){
            System.out.println("Finalizando cambios en la grafica espere...");
            sleep(1000);
        }
        renderComputacionesEquivalentes();
    }

    private void stopAction(){
        this.stopAll();
    }

    /**
    *  Termina el metedo para localizar todas las computaciones equivalentes
    *  Las computaciones equivalentes que tienen que encontrar de ley son [A, B, E, F, D] y [A, E, B, F, D]
    */
    private void renderComputacionesEquivalentes(){

        Graph equivalentes = new SingleGraph("Computaciones Equivalentes");
        LinkedList<Message> completados = general.getRecibidoDestinatario();
        LinkedList<Message> compEquivalentes = new LinkedList<Message>();
        //Para cada mensaje que llego a su destino 
        for(Message mensaje: completados){
            Set<String> recorrido = new HashSet<String>(mensaje.getRecorrido());

            //comparamos un cada mensaje con los demás que han sido completados.
            for(Message comparar: completados){
                Set<String> recorridoComparar = new HashSet<String>(comparar.getRecorrido());
                //Si hay dos recorridos iguales y la computacion del mensaje no esta en la lista
                if(!recorrido.equals(recorridoComparar) && !compEquivalentes.contains(mensaje))
                    compEquivalentes.add(mensaje);//agregamos el mensaje a la lista para graficar.
                }
        }


        //Hacemos la configuración de la gráfica a desplegar
        if(equivalentes != null){

            int noComputacion = 0;
            int iNodo = 0;
            //para cada mensaje que generó una computación equivalente
            for(Message mensaje: compEquivalentes){

                Node anterior = null;
                Node actual = null;
                //Agregamos el nodo del recorrido de la computación y su arista que lo conecta con su nodo anterior.
                for(String s: mensaje.getRecorrido()){

                    this.graph.getNode(s).addAttribute("ui.class", "equivalente");//le ponemos una clase a los nodos de la gráfica para distinguirlos.
                    actual = equivalentes.addNode(noComputacion+""+iNodo+""+s);//agregamos cada nodo del recorrido
                    actual.addAttribute("ui.label",actual.getId());//le ponemos su etiqueta

                    //Cuando tengamos dos nodos consecutivos del recorrido agregamos su arista
                    if(anterior != null){
                        //agregamos la arista entre los dos nodos consecutivos
                        equivalentes.addEdge(anterior.getId()+""+actual.getId()+""+noComputacion, anterior.getId(), actual.getId());
                    }
                    anterior = actual;
                    ++iNodo;
                }
                noComputacion++;
            }
            equivalentes.display();//desplegamos la gráfica
            equivalentes.addAttribute("ui.stylesheet", "node { fill-color: orange; }");//Agregamos un color a los nodos
            this.graph.addAttribute("ui.stylesheet", "node.equivalente { fill-color: red; }");//Cambiamos de color para los nodos que forman parte de una computación equivalente
        }
    }


}

