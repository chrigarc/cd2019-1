import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import org.graphstream.graph.Node;
import java.util.concurrent.ConcurrentLinkedDeque;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.View;


public class CDGraph extends Thread{

    private Graph graph;
    private Set<CDNode> nodes;
    private ConcurrentLinkedDeque<String[]> list;
    private boolean active;
    private JFrame frame;
    private String source;
    private String destination;
    private CDNode comp; 

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
                cdn = new CDNode(this, i, CDNode.Type.SOURCE);
            if(i.getId().equals(source)){
            }else if(i.getId().equals(destination)){
                cdn = new CDNode(this, i, CDNode.Type.DESTINATION);
                comp = cdn; 
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

        Graph equivalentes = new SingleGraph("Equivalentes");
        LinkedList<Message> logrados = comp.getLogrados();
        LinkedList<Message> equiv = new LinkedList<Message>();
        for(Message mensaje: logrados){
            Set<String> recorrido = new HashSet<String>(mensaje.getRecorrido());
            for(Message comparar: logrados){
                Set<String> recorridoComparar = new HashSet<String>(comparar.getRecorrido());
                if(!recorrido.equals(recorridoComparar) && !equiv.contains(mensaje))
                    equiv.add(mensaje);
                }
        }

        if(equivalentes != null){
            int noComp = 0;
            int indN = 0;
            for(Message mensaje: equiv){

                Node anterior = null;
                Node actual = null;
                for(String str: mensaje.getRecorrido()){
                    this.graph.getNode(str).addAttribute("ui.class", "equivalente");
                    actual = equivalentes.addNode(noComp + " " + indN + " " + str);
                    actual.addAttribute("ui.label",actual.getId());
                    if(anterior != null){
                        equivalentes.addEdge(anterior.getId() + " " + actual.getId() + " " + noComp, anterior.getId(), actual.getId());
                    }
                    anterior = actual;
                    ++indN;
                }
                noComp++;
            }
            equivalentes.display();
            equivalentes.addAttribute("ui.stylesheet", "node { fill-color: pink; }");
            this.graph.addAttribute("ui.stylesheet", "node.equivalente { fill-color: green; }");
        }
    }
}

