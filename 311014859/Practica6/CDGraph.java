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
import org.graphstream.graph.implementations.SingleGraph;
import java.util.LinkedList;
import java.awt.Color;
/*
*
*Codigo escrito por Luis Pulido Alvarez
*
*/

public class CDGraph extends Thread{

    private Graph grafica;
    private Set<CDNode> nodo;
    private ConcurrentLinkedDeque<String[]> graficaLista;
    private boolean bandera;
    private JFrame pinta;
    private String desarrollo;
    private String destino;
    private CDNode compuEquivalente;

    public CDGraph(Graph g){
        this.grafica = g;
        this.nodo = new HashSet<CDNode>();
        this.graficaLista = new ConcurrentLinkedDeque<String[]>();
        this.bandera = true;
    }

    public void run(){
        grafica.display();
        for( Node cadaNodo : grafica.getEachNode() ){
            cadaNodo.addAttribute("ui.label", cadaNodo.getId());
            CDNode cdNodo = null;
                cdNodo = new CDNode(this, cadaNodo, CDNode.Type.SOURCE);
            if(cadaNodo.getId().equals(desarrollo)){
            }else if(cadaNodo.getId().equals(destino)){
                cdNodo = new CDNode(this, cadaNodo, CDNode.Type.DESTINATION);
                compuEquivalente = cdNodo; 
            }else{
                cdNodo = new CDNode(this, cadaNodo);
            }
            new Thread(cdNodo).start();
            nodo.add(cdNodo);
        }
        this.createFrame();
        while(bandera || !graficaLista.isEmpty()){
            while(!graficaLista.isEmpty()){
                String[] tmp = graficaLista.poll();
                grafica.getNode(tmp[0]).setAttribute("ui.class",  tmp[1]);
            }
            sleep(100);
        }
    }
        public void setSource(String desarrollo){
        this.desarrollo = desarrollo;
    }

    public void setDestination(String destino){
        this.destino = destino;
    }

    private void createFrame(){
        pinta = new JFrame("Práctica 4");
        pinta.setSize(800, 800);
        pinta.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla

        javax.swing.JScrollPane pintaPanel = new javax.swing.JScrollPane();
        JPanel almacenaPanel = new JPanel();
        almacenaPanel.setLayout(null);

        pinta.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelPintaAlmacena = new javax.swing.GroupLayout(almacenaPanel);
        almacenaPanel.setLayout(panelPintaAlmacena);
        panelPintaAlmacena.setHorizontalGroup(
        panelPintaAlmacena.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 517, Short.MAX_VALUE)
        );
        panelPintaAlmacena.setVerticalGroup(
        panelPintaAlmacena.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 342, Short.MAX_VALUE)
        );

        pintaPanel.setViewportView(almacenaPanel);

        javax.swing.GroupLayout escritura = new javax.swing.GroupLayout(pinta.getContentPane());
        pinta.getContentPane().setLayout(escritura);
        escritura.setHorizontalGroup(
        escritura.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(pintaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
        );
        escritura.setVerticalGroup(
        escritura.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(pintaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
        );

        pinta.pack();

        JButton boton = new JButton("Parar ");
        boton.setSize(800, 30);
        boton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stopAction();
            }
        });
        almacenaPanel.add(boton);

        Iterator<CDNode> it = nodo.iterator();
        int y = 30;
        while(it.hasNext()){
            JComponent component = it.next();
            component.setSize(760, 30);
            component.setLocation(30, y);
            y+=35;
            almacenaPanel.add(component);
        }
        pinta.setVisible(true);

    }

    private void sleep(int ms){
        try{
            Thread.sleep(ms);
        }catch(Exception ex){
        }
    }

    public void addChangeColor(String idNodo, String color){
        graficaLista.add(new String[]{idNodo, color});
    }
    
    public void stopAll(){
        Iterator<CDNode> it = nodo.iterator();
        while(it.hasNext()){
            it.next().stop();
        }
        bandera = false;
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

        Graph iguales = new SingleGraph("Computaciones Equivalentes");
        LinkedList<Message> listos = compuEquivalente.getRecibidoDestinatario();
        LinkedList<Message> equComps = new LinkedList<Message>();
        
        for(Message mensaje: listos){
            Set<String> visitas = new HashSet<String>(mensaje.getRecorrido());

            for(Message comparar: listos){
                Set<String> recorridoComparar = new HashSet<String>(comparar.getRecorrido());
                if(!visitas.equals(recorridoComparar) && !equComps.contains(mensaje))
                    equComps.add(mensaje);
                }
        }

        if(iguales != null){

            int noComputacion = 0;
            int iNodo = 0;
            for(Message mensaje: equComps){

                Node anterior = null;
                Node ultimo = null;
                for(String s: mensaje.getRecorrido()){

                    this.grafica.getNode(s).addAttribute("ui.class", "equivalente");
                    ultimo = iguales.addNode(noComputacion+""+iNodo+""+s);
                    ultimo.addAttribute("ui.label",ultimo.getId());

                    
                    if(anterior != null){
                        
                        iguales.addEdge(anterior.getId()+""+ultimo.getId()+""+noComputacion, anterior.getId(), ultimo.getId());
                    }
                    anterior = ultimo;
                    ++iNodo;
                }
                noComputacion++;
            }
            iguales.display();
            iguales.addAttribute("ui.stylesheet", "node { fill-color: orange; }");
            this.grafica.addAttribute("ui.stylesheet", "node.equivalente { fill-color: red; }");
        }
    }


}

