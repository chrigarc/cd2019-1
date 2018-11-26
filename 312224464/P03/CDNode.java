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
    public static final String COLOR_COMPUTACIONAL = "pink";

    public static String CreateMessage(String s, String d){
        return s + " -> " + d + " : " + System.nanoTime();
    }


    private Node node;
    private boolean activo;
    private Transport transport;
    private CDGraph graph;
    private CDNode.Type type;
    private LinkedList<Message> recibidos; //Debemos tener una lista de mensajes recibidos
    private LinkedList<Message> recibidoDestinatario; //Debemos tener un lista de mensajes que hayan llegado a su destinatario

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

    public void run(){
        //Mientras que el estado de CDNode este activo 
        while(this.activo){
            if(type != null && type == CDNode.Type.SOURCE){
                Iterator<Node> nNeigh = node.getNeighborNodeIterator();//Iterador para los vecinos del node.
                while(nNeigh.hasNext()) {//Para cada vecino
                    Node n = nNeigh.next();//Tomamos el el vecino
                    //Creamos un mensaje desde el nodo actual hacia el vecino n
                    Message m = new Message(node.getId(), n.getId(), CDNode.CreateMessage(node.getId(), n.getId()));
                    this.sendMessage(m);//Enviamos el mensaje
                }
            }

            Message m = this.readMessage();//Guardamos el mensaje recibido en m.
            if(m!=null){
                recibidos.add(m);//Agregamos el mensaje a los recibidos.
                if(type != null && type == CDNode.Type.DESTINATION){//Verificamos si el mensaje recibido llego a su destino.
                    recibidoDestinatario.add(m); //uan vez completado ya no lo reenviamos
                    // TODO
                }else{//Sino ha llegado a su destino.
                    //reenviamos a los vecinos.
                    Iterator<Node> nNeigh = node.getNeighborNodeIterator();
                    while(nNeigh.hasNext()) {//Para cada vecino
                        Node n = nNeigh.next();
                        Message reenvio = m.clone();//clonamos el mensaje.
                        reenvio.setRecorrido((LinkedList<String>) m.getRecorrido().clone());//le damos un recorrido clonado al mensaje

                        reenvio.setSource(this.node.getId());
                        reenvio.setDestination(n.getId());
                        
                        reenvio.getRecorrido().add(n.getId());//actualizamos el recorrido para el mensaje reenviado
                        reenvio.setTTL(m.getTTL()-1);
                        this.sendMessage(reenvio);
                    }
                }
            }
            sleep(100);
        }
    }

    //Creamos el método de acceso para obtener los mensajes que han llegado a su destino.
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
