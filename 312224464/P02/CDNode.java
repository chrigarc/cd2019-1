import org.graphstream.graph.Node;
import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JLabel;

public class CDNode extends JLabel implements Runnable{

	public static final String COLOR_DEFAULT = "blue";
    public static final String COLOR_SEND = "red";
    public static final String COLOR_READ = "green";
    
    private Node node;
    private boolean activo;
    private Transport transport;
    private CDGraph graph;
    private LinkedList<Message> mensajes;

    public CDNode(CDGraph g,Node n){
    	super();
    	this.node = n;
    	this.activo = true;
    	transport = Transport.getInstance();
    	this.graph = g;
    	this.setFillColor(COLOR_DEFAULT);
        this.mensajes = new LinkedList<Message>();
    }

    /**
     * En cada iteración, cualquier nodo generará un mensaje y lo
     * enviará a sus vecinos.
     *
     * Después, leerá un mensaje y lo reenviará a todos sus vecinos
     * mientras el tiempo de vida del mensaje lo permita.
     */
    public void run(){
    	
        while(this.activo){

            Iterator<Node> nuevo_nodo = node.getNeighborNodeIterator();

            //Generación del mensaje y envío a todos los vecinos del nodo
            while(nuevo_nodo.hasNext()){
                Node n = nuevo_nodo.next();
                Message m = new Message(node.getId(), n.getId());
                this.sendMessage(m);
            }

            Message m = readMessage();

            //Se recibe un mensaje y se reenvía a sus vecinos
            if(m != null){
                mensajes.add(m);
                nuevo_nodo = node.getNeighborNodeIterator();

                while(nuevo_nodo.hasNext()){
                    Node n = nuevo_nodo.next();
                    Message reenviado = m.clonar();
                    reenviado.setDestino(n.getId());
                    reenviado.setTiempoVida(m.getTiempoVida()-1);
                    this.sendMessage(reenviado);
                }
            }

            sleep(100);
        }
    }

    /**
     * Muestra el flujo de los mensajes.
     */
    public String getText(){
    	String s = super.getText();

    	if(node!=null){
    		s+="ID: " + node.getId();
    	}

        if(mensajes != null && !mensajes.isEmpty()){
            s+=". Mensaje recibido de: " + mensajes.getLast().getOrigen() +".";
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

    private synchronized void setFillColor(String color){
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