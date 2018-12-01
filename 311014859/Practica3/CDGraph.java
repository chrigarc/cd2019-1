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

/*
* 
* Codigo hecho por Ian Eduardo Chavez Munioa.
*
*
*/
public class CDGraph extends Thread{

    private Graph grafica;
    private Set<CDNode> nodos;
    private ConcurrentLinkedDeque<String[]> graficaLista;
    private boolean bandera;
    private JFrame pintar;
    private String resultados;
    private String destino;
    private CDNode destinoNodo;

    public CDGraph(Graph grafica){
        this.grafica = grafica;
        this.nodos = new HashSet<CDNode>();
        this.graficaLista = new ConcurrentLinkedDeque<String[]>();
        this.bandera = true;
    }

    public void run(){
        grafica.display();
        for( Node cadeNodo : grafica.getEachNode() ){
            cadeNodo.addAttribute("ui.label", cadeNodo.getId());
            CDNode cdNodo = null;
            cdNodo = new CDNode(this, cadeNodo, CDNode.Type.SOURCE);
            if(cadeNodo.getId().equals(resultados)){
            }else if(cadeNodo.getId().equals(destino)){
                cdNodo = new CDNode(this, cadeNodo, CDNode.Type.DESTINATION);
                destinoNodo = cdNodo;
            }else{
                cdNodo = new CDNode(this, cadeNodo);
            }
            new Thread(cdNodo).start();
            nodos.add(cdNodo);
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

    public void addChangeColor(String idNodo, String color){
        graficaLista.add(new String[]{idNodo, color});
    }

    public void setSource(String resultados){
        this.resultados = resultados;
    }

    public void setDestination(String destino){
        this.destino = destino;
    }

    private void createFrame(){
        pintar = new JFrame("Practica 3");
        pintar.setSize(820, 820);
        pintar.setLocationRelativeTo(null);                       

        javax.swing.JScrollPane arrastaPanel = new javax.swing.JScrollPane();
        JPanel guardaImagen = new JPanel();
        guardaImagen.setLayout(null);

        pintar.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout guardaImagenLayout = new javax.swing.GroupLayout(guardaImagen);
        guardaImagen.setLayout(guardaImagenLayout);
        guardaImagenLayout.setHorizontalGroup(
                                         guardaImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                         .addGap(0, 517, Short.MAX_VALUE)
                                         );
        guardaImagenLayout.setVerticalGroup(
                                       guardaImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                       .addGap(0, 342, Short.MAX_VALUE)
                                       );

        arrastaPanel.setViewportView(guardaImagen);

        javax.swing.GroupLayout input = new javax.swing.GroupLayout(pintar.getContentPane());
        pintar.getContentPane().setLayout(input);
        input.setHorizontalGroup(
                                  input.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                  .addComponent(arrastaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                                  );
        input.setVerticalGroup(
                                input.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(arrastaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                                );

        pintar.pack();

        JButton boton = new JButton("Parar");
        boton.setSize(800, 30);
        boton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    stopAction();
                }
            });
        guardaImagen.add(boton);

        Iterator<CDNode> it = nodos.iterator();
        int y = 30;
        while(it.hasNext()){
            JComponent componenteAuxiliar = it.next();
            componenteAuxiliar.setSize(760, 30);
            componenteAuxiliar.setLocation(30, y);
            y+=35;
            guardaImagen.add(componenteAuxiliar);
        }
        pintar.setVisible(true);

    }

    private void sleep(int mensaje){
        try{
            Thread.sleep(mensaje);
        }catch(Exception ex){
        }
    }

    public void stopAll(){
        Iterator<CDNode> it = nodos.iterator();
        while(it.hasNext()){
            it.next().stop();
        }
        bandera = false;
        while(this.isAlive()){
            System.out.println("Finalizando cambios...");
            sleep(1000);
        }
        renderComputacionesgraficaIgual();
    }

    private void stopAction(){
        this.stopAll();
    }

    private boolean esEquivalente(Message msg, LinkedList<Message> l){
        Set<String> visitas = new HashSet<String>(msg.getRecorrido());
        for(Message mensajeAhora: l){
            Set<String> recorridoActual = new HashSet<String>(mensajeAhora.getRecorrido());
            if(!visitas.equals(recorridoActual))
                return false;
        }
        return true;
    }
    
    private void renderComputacionesgraficaIgual(){
        Graph graficaIgual = new SingleGraph("compgraficaIgual");
        LinkedList<Message> pasaron = destinoNodo.getExitosos();
        LinkedList<Message> esIgual = new LinkedList<Message>();
        for(Message m: pasaron){
            if(esEquivalente(m, pasaron) && !esIgual.contains(m))
                esIgual.add(m);
        }
        if(graficaIgual != null){
            int indGrafica = 0;
            int indNodo = 0;
            for(Message m: pasaron){
                Node pasadoNodo = null;
                for(String s: i.getRecorrido()){
                    Node nodoMedio = graficaIgual.addNode("G" + indGrafica + "N" + indNodo + "_" + s);
                    nodoMedio.addAttribute("ui.label",nodoMedio.getId());
                    if(pasadoNodo != null){
                        graficaIgual.addEdge("G" + index_g + pasadoNodo + s, pasadoNodo.getId(), nodoMedio.getId());
                    }
                    pasadoNodo = nodoMedio;
                    indNodo++;
                }
                indGrafica++;
            }
            graficaIgual.display();
        }
    }


}