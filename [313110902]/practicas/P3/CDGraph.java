import org.graphstream.graph.Graph;
<<<<<<< HEAD
import org.graphstream.graph.implementations.SingleGraph;
=======
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
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
<<<<<<< HEAD
import java.util.LinkedList;


=======
import org.graphstream.graph.implementations.SingleGraph;
import java.awt.Color;
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9


public class CDGraph extends Thread{

    private Graph graph;
    private Set<CDNode> nodes;
    private ConcurrentLinkedDeque<String[]> list;
    private boolean active;
    private JFrame frame;
    private String source;
    private String destination;
<<<<<<< HEAD
    private CDNode nodeDestination;
=======
    private CDNode eq;
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9

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
<<<<<<< HEAD
                cdn = new CDNode(this, i, CDNode.Type.SOURCE);
                if(i.getId().equals(source)){
            }else if(i.getId().equals(destination)){
                cdn = new CDNode(this, i, CDNode.Type.DESTINATION);
=======
                 cdn = new CDNode(this, i, CDNode.Type.SOURCE);
            if(i.getId().equals(source)){
            }else if(i.getId().equals(destination)){
                cdn = new CDNode(this, i, CDNode.Type.DESTINATION);
                eq = cdn;
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
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
        frame = new JFrame("Práctica 3");
        frame.setSize(800, 800);
<<<<<<< HEAD
        frame.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
=======
        frame.setLocationRelativeTo(null);                     
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9

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
<<<<<<< HEAD
        Graph equivalentes = new SingleGraph("equivalentes");
        LinkedList<Message> exitosos = nodeDestination.getExitosos();
        LinkedList<Message> l = new LinkedList<Message>();
        for(Message i:exitosos){
            if(isEquivalente(i, exitosos) && !l.contains(i)){
                l.add(i);
            }
        }
        if(equivalentes != null){
            int index_g = 0;
            int index = 0;
            for(Message i:l){

                Node anterior = null;
                for(String j:i.getRecorrido()){
                    Node n = equivalentes.addNode(index + "__G" + index_g + "_N_" + j);
                    n.addAttribute("ui.label",n.getId());
                    if(anterior != null){
                        equivalentes.addEdge("G" + index_g + "_E" + anterior + j, anterior.getId(), n.getId());
                    }
                    anterior = n;
                    index++;
                }
                index_g++;
            }
            equivalentes.display();
        }
    }

    private boolean isEquivalente(Message m, LinkedList<Message> l){
        boolean status = false;
        for(Message i:l){
            if(!m.equals(i) && m.getRecorrido().size() == i.getRecorrido().size()){
                boolean tmp = true;
                for(String j:m.getRecorrido()){
                    if(!i.getRecorrido().contains(j)){
                        tmp = false;
                    }
                }
                boolean tmp2 = true;
                for(String j:i.getRecorrido()){
                    if(!m.getRecorrido().contains(j)){
                        tmp2 = false;
                    }
                }
                if(tmp && tmp2){
                    status = tmp;
                }
            }
        }
        return status;
	}
}
=======

        Graph equivalentes = new SingleGraph("CompuEquivalentes");
        LinkedList<Message> completados = eq.getRecibidoDestino();
        LinkedList<Message> compEquivalentes = new LinkedList<Message>();
        for(Message mensaje: completados){
            Set<String> recorrido = new HashSet<String>(mensaje.getRecorrido());
            for(Message comparar: completados){
                Set<String> recorridoComparar = new HashSet<String>(comparar.getRecorrido());
                /
                if(!recorrido.equals(recorridoComparar) && !compEquivalentes.contains(mensaje))
                    compEquivalentes.add(mensaje);
                }
        }

      
        if(equivalentes != null){

            int noComputacion = 0;
            int iNodo = 0;
            
            for(Message mensaje: compEquivalentes){

                Node anterior = null;
                Node actual = null;
                for(String s: mensaje.getRecorrido()){

                    this.graph.getNode(s).addAttribute("ui.class", "equivalente");
                    actual = equivalentes.addNode(noComputacion+""+iNodo+""+s);
                    actual.addAttribute("ui.label",actual.getId());

                    if(anterior != null){
                        
                        equivalentes.addEdge(anterior.getId()+""+actual.getId()+""+noComputacion, anterior.getId(), actual.getId());
                    }
                    anterior = actual;
                    ++iNodo;
                }
                noComputacion++;
            }
            equivalentes.display();
            equivalentes.addAttribute("ui.stylesheet", "node { fill-color: blue; }");
            this.graph.addAttribute("ui.stylesheet", "node.equivalente { fill-color: brown; }");
        }
    }


}
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
