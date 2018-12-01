import org.graphstream.graph.Node;
import java.util.Iterator;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.LinkedList;
import java.lang.Math;
/*
*
*Codigo escrito por Ian Eduardo Chavez Munioa
*
*/
@SuppressWarnings("unchecked")
public class CDNode extends JLabel implements Runnable{

    public enum Type{
        SOURCE, DESTINATION;
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
    private CDNode.Type tipo;
    private LinkedList<Message> almacena;
    private LinkedList<Message> recibidoDestinatario; 
    public int tiempo;
    public int tempMar;

    public CDNode(CDGraph g,Node n){
        super();
        this.nodo = n;
        this.bandera = true;
        this.transportar = Transport.getInstance();
        this.grafica = g;
        this.setFillColor(COLOR_DEFAULT);
        this.almacena = new LinkedList<>();
        this.tiempo = 0;
        this.tempMar = 0;
    }

    public CDNode(CDGraph g,Node n, CDNode.Type tipo){
        this(g, n);
        this.tipo = tipo;
        if(tipo == CDNode.Type.DESTINATION){
            this.recibidoDestinatario = new LinkedList<Message>();
        }
    }

    public void run(){ 
        while(this.bandera){
            if(tipo != null && tipo == CDNode.Type.SOURCE){
                Iterator<Node> itVecinos = nodo.getNeighborNodeIterator();
                while(itVecinos.hasNext()) {
                    Node n = itVecinos.next();
                    
                    Message m = new Message(nodo.getId(), n.getId(), CDNode.CreateMessage(nodo.getId(), n.getId()));
                    sendMessage(m, getReloj()); 
                }
            }

            Message m = this.readMessage();
            setReloj ( Math.max( getMarcaTemporal(), getReloj() ) +1);
            if(m!=null){
                almacena.add(m);
                if(tipo != null && tipo == CDNode.Type.DESTINATION){
                    recibidoDestinatario.add(m); 
                }else{
                    Iterator<Node> itVecinos = nodo.getNeighborNodeIterator();
                    while(itVecinos.hasNext()) {
                        Node n = itVecinos.next();
                        Message reenvio = m.clone();
                        reenvio.setRecorrido((LinkedList<String>) m.getRecorrido().clone());

                        reenvio.setSource(this.nodo.getId());
                        reenvio.setDestination(n.getId());
                        
                        reenvio.getRecorrido().add(n.getId());
                        reenvio.setTTL(m.getTTL()-1);
                        sendMessage(reenvio, getReloj()); 
                    }
                }
            }
            sleep(100);
        }
    }

    public Node getNode(){
        return nodo;
    }

    
    public int getReloj(){
        return this.tiempo;
    }

    public void setReloj(int tiempo){
        this.tiempo = tiempo;
    }

    
    public int getMarcaTemporal(){
        return this.tempMar;
    }

    
    public void setMarcaTemporal(int tempMar){
        this.tempMar = tempMar;
    }
    public LinkedList<Message> getRecibidoDestinatario(){
        return this.recibidoDestinatario;
    }

    public String getText(){
        String s = super.getText();

        if(nodo!=null){
            s+="ID: " + nodo.getId();
        }

        if(almacena != null && !almacena.isEmpty()){
            s+=", ultimo mensaje recibido de: " + almacena.getLast().getSource();
        }

        s+= " - Reloj: " + getReloj();
        return s;
    }


    public boolean sendMessage(Message m, int tiempo){
        setReloj(tiempo + 1);
        setMarcaTemporal(tiempo);

        this.setFillColor(COLOR_SEND);
        boolean estado = transportar.put(m);
        this.setFillColor(COLOR_DEFAULT);
        return estado;
    }
    private synchronized void  setFillColor(String color){
        this.grafica.addChangeColor(this.nodo.getId(), color);
        Color c = Color.BLUE;
        switch(color){
            case "red":
            c = Color.RED;
            break;
            case "green":
            c = Color.GREEN;
            break;
        }
        setForeground(c);
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
