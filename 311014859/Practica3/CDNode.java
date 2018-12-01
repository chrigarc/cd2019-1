import org.graphstream.graph.Node;
import java.util.Iterator;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.LinkedList;
/*
* 
* Codigo hecho por Mauricio Javier Salas Martínez.
*
*
*/
public class CDNode extends JLabel implements Runnable{

    public enum Type{
        SOURCE, DESTINATION, NORMAL;
    }

    public static final String COLOR_DEFAULT = "blue";
    public static final String COLOR_SEND = "red";
    public static final String COLOR_READ = "green";

    public static String CreateMessage(String s, String d){
        return s + " -> " + d + " : " + System.nanoTime();
    }

    private Node nodo;
    private boolean bandera;
    private Transport transportar;
    private CDGraph grafica;
    private LinkedList<Message> obtenido;
    private CDNode.Type tipo;
    private LinkedList<Message> pasaron;

    public CDNode(CDGraph grafica,Node nodo){
        super();
        this.nodo = nodo;
        this.bandera = true;
        this.transportar = Transport.getInstance();
        this.grafica = grafica;
        this.obtenido = new LinkedList<Message>();
        this.setFillColor(COLOR_DEFAULT);
    
    }

    public CDNode(CDGraph grafica, Node nodoAux, CDNode.Type tipo){
        this(grafica, nodoAux);
        this.tipo = tipo;
        if(tipo == CDNode.Type.DESTINATION)
            this.pasaron = new LinkedList<Message>();
    }
    
    public LinkedList<Message> getMessages(){
        return obtenido;
    }
    
    public void run(){
        while(this.bandera){
            if(tipo != null && tipo == CDNode.Type.SOURCE){
                Iterator<Node> nNeigh = nodo.getNeighborNodeIterator();
                while(nNeigh.hasNext()) {
                    Node nodoTemp = nNeigh.next();
                    Message mensaje = new Message(nodo.getId(), nodoTemp.getId(), CDNode.CreateMessage(nodo.getId(), nodoTemp.getId()));
                    this.sendMessage(mensaje);
                }
            }

            Message mensaje = readMessage();
            if(mensaje!=null){
                obtenido.add(mensaje);
                if(tipo != null && tipo == CDNode.Type.DESTINATION){
                    pasaron.add(mensaje);
                }else{
                    Iterator<Node> vecino = nodo.getNeighborNodeIterator();
                    while(vecino.hasNext()) {
                        Node n = vecino.next();
                        Message devolver = mensaje.clone();
                        devolver.setSource(this.nodo.getId());
                        devolver.setDestination(n.getId());
                        devolver.setTTL(mensaje.getTTL()-1);
                        this.sendMessage(devolver);
                    }
                }
            }
            sleep(100);
        }
    }

    public Node getNode(){
        return nodo;
    }
    
    public LinkedList<Message> getExitosos(){
        return this.pasaron;
    }

    public String getText(){
        String cadena = super.getText();
        if(nodo!=null){
            cadena+="ID: " + nodo.getId();
        }
        if(obtenido != null && !obtenido.isEmpty()){
            cadena+=", ultimo mensaje recibido: " + obtenido.getLast().getSource();
        }
        return cadena;
    }

    private synchronized void  setFillColor(String color){
        this.grafica.addChangeColor(this.nodo.getId(), color);
        Color colorTemporal = Color.BLUE;
        switch(color){
            case "red":
            colorTemporal = Color.RED;
            break;
            case "green":
            colorTemporal = Color.GREEN;
            break;
        }
        setForeground(colorTemporal);
    }

    public boolean sendMessage(Message mensaje){
        this.setFillColor(COLOR_SEND);
        boolean estatus = transportar.put(mensaje);
        this.setFillColor(COLOR_DEFAULT);
        return estatus;
    }

    public Message readMessage(){
        this.setFillColor(COLOR_READ);
        Message mensaje = transportar.pop(nodo.getId());
        this.setFillColor(COLOR_DEFAULT);
        return mensaje;
    }


    public void stop(){
        this.bandera = false;
    }
    private void sleep(int mensaje){
        try{
            Thread.sleep(mensaje);
        }catch(Exception ex){
        }
    }
}