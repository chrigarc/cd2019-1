<<<<<<< HEAD
=======

>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
import org.graphstream.graph.Node;
import java.util.Iterator;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.LinkedList;

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
<<<<<<< HEAD
     private LinkedList<Message> recibidos;
      private LinkedList<Message> exitosos;

=======
    private LinkedList<Message> recibidos; /
    private LinkedList<Message> recibidoDestinatario; 
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9

    public CDNode(CDGraph g,Node n){
        super();
        this.node = n;
        this.activo = true;
        transport = Transport.getInstance();
        this.graph = g;
        this.setFillColor(COLOR_DEFAULT);
<<<<<<< HEAD
        recibidos = new LinkedList<Message>();
=======
        this.recibidos = new LinkedList<>();
    
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
    }

    public CDNode(CDGraph g,Node n, CDNode.Type type){
        this(g, n);
        this.type = type;
<<<<<<< HEAD
=======
        if(type == CDNode.Type.DESTINATION){
            this.recibidoDestinatario = new LinkedList<Message>();
        }
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
    }

    public Node getNode(){
        return node;
    }

<<<<<<< HEAD


    public LinkedList<Message> getExitosos(){
        return this.exitosos;
    }
    public void run(){
        while(this.activo){
=======
    public void run(){
         while(this.activo){
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
            if(type != null && type == CDNode.Type.SOURCE){
                Iterator<Node> nNeigh = node.getNeighborNodeIterator();
                while(nNeigh.hasNext()) {
                    Node n = nNeigh.next();
<<<<<<< HEAD
=======
                    
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
                    Message m = new Message(node.getId(), n.getId(), CDNode.CreateMessage(node.getId(), n.getId()));
                    this.sendMessage(m);
                }
            }

<<<<<<< HEAD
            Message m = readMessage();
            if(m!=null){
                recibidos.add(m);
                if(type != null && type == CDNode.Type.DESTINATION){
                    // TODO
                }else{
=======
            Message m = this.readMessage();
            if(m!=null){
                recibidos.add(m);
                if(type != null && type == CDNode.Type.DESTINATION){
                    recibidoDestinatario.add(m); 
                    
                }else{
                    
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
                    Iterator<Node> nNeigh = node.getNeighborNodeIterator();
                    while(nNeigh.hasNext()) {
                        Node n = nNeigh.next();
                        Message reenvio = m.clone();
<<<<<<< HEAD
                        reenvio.setSource(this.node.getId());
                        reenvio.setDestination(n.getId());
=======
                        reenvio.setRecorrido((LinkedList<String>) m.getRecorrido().clone());
                        reenvio.setSource(this.node.getId());
                        reenvio.setDestination(n.getId());
                        reenvio.getRecorrido().add(n.getId());
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
                        reenvio.setTTL(m.getTTL()-1);
                        this.sendMessage(reenvio);
                    }
                }
            }
            sleep(100);
        }
    }

<<<<<<< HEAD
=======
    //getRecibidoDestino: Mensajes recibidos del destinatario
    public LinkedList<Message> getRecibidoDestino(){
        return this.recibidoDestinatario;
    }

>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9

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


<<<<<<< HEAD
     public LinkedList<Message> getMessages(){
        return recibidos;
}


=======
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
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
<<<<<<< HEAD
=======

>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
