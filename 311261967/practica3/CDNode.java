import org.graphstream.graph.Node;
import java.util.Iterator;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.LinkedList;

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


    private Node node;
    private boolean activo;
    private Transport transport;
    private CDGraph graph;
    private CDNode.Type type;
    private LinkedList<Message> recibidos; 
    private LinkedList<Message> recibidoDestinatario; 

    public CDNode(CDGraph g,Node n){
        super();
        this.node = n;
        this.activo = true;
        transport = Transport.getInstance();
        this.graph = g;
        this.setFillColor(COLOR_DEFAULT);
        this.recibidos = new LinkedList<>();
    
    }

    public CDNode(CDGraph g,Node n, CDNode.Type type){
        this(g, n);
        this.type = type;
        if(type == CDNode.Type.DESTINATION){
            this.recibidoDestinatario = new LinkedList<Message>();
        }
    }

    public Node getNode(){
        return node;
    }

    /* Mandamos mensajes a todos los vecinos del nodo  */
    public void run(){
        while(this.activo){
            if(type != null && type == CDNode.Type.SOURCE){
                Iterator<Node> nNeigh = node.getNeighborNodeIterator();
                while(nNeigh.hasNext()) {
                    Node n = nNeigh.next();
                    Message m = new Message(node.getId(), n.getId(), CDNode.CreateMessage(node.getId(), n.getId()));
                    this.sendMessage(m);
                }
            }

            Message m = this.readMessage();
            if(m!=null){
                recibidos.add(m);
                if(type != null && type == CDNode.Type.DESTINATION){
                    recibidoDestinatario.add(m); 
                }else{
                    Iterator<Node> nNeigh = node.getNeighborNodeIterator();
                    while(nNeigh.hasNext()) {
                        Node n = nNeigh.next();
                        Message reenvio = m.clone();
                        reenvio.setRecorrido((LinkedList<String>) m.getRecorrido().clone());
                        reenvio.setSource(this.node.getId());
                        reenvio.setDestination(n.getId());
                        reenvio.getRecorrido().add(n.getId());
                        reenvio.setTTL(m.getTTL()-1);
                        this.sendMessage(reenvio);
                    }
                }
            }
            sleep(100);
        }
    }

    public LinkedList<Message> getRecibidoDestinatario(){
        return this.recibidoDestinatario;
    }


    public String getText(){
        String s = super.getText();
        if(node!=null){
            s+="ID: " + node.getId();
        }
        if(recibidos != null && !recibidos.isEmpty()){
            s+=", ultimo mensaje recibido de: " + recibidos.getLast().getSource();
        }
        return s;
    }


    public boolean sendMessage(Message m){
        this.setFillColor(COLOR_SEND);
        boolean status = transport.put(m);
        this.setFillColor(COLOR_DEFAULT);
        return status;
    }

    public Message readMessage(){
        this.setFillColor(COLOR_READ);
        Message m = transport.pop(node.getId());
        this.setFillColor(COLOR_DEFAULT);
        return m;
    }


    public void stop(){
        this.activo = false;
    }

    private synchronized void  setFillColor(String color){
        this.graph.addChangeColor(this.node.getId(), color);
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

    private void sleep(int ms){
        try{
            Thread.sleep(ms);
        }catch(Exception ex){
        }
    }
}

