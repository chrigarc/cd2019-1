
import org.graphstream.graph.Node;
import java.util.Iterator;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.LinkedList;

public class CDNode extends JLabel implements Runnable{

    public static final String COLOR_DEFAULT = "blue";
    public static final String COLOR_SEND = "red";
    public static final String COLOR_READ = "green";


    private Node nodo;
    private boolean activo;
    private Transport transport;
    private CDGraph grafica;
    private LinkedList<Message> recibidos;


    public CDNode(CDGraph grafica,Node nodo){
        super();
        this.nodo = nodo;
        this.activo = true;
        transport = Transport.getInstance();
        this.grafica = grafica;
        this.setFillColor(COLOR_DEFAULT);
        recibidos = new LinkedList<Message>();
}

    
    public LinkedList<Message> getMessages(){
        return recibidos;
    }

    public Node getNodo(){
        return nodo;
	}

    public void run(){
        while(this.activo){
            Iterator<Node> nVecino = node.getNeighborNodeIterator();
            while(nVecino.hasNext()) {
                Node n = nVecino.next();
                Message msg = new Message(node.getId(), n.getId());
                this.sendMessage(msg,msg.getDestination());
            }
            Message msg = readMessage();
            if(msg!=null){
                recibidos.add(msg);
                nVecino = node.getNeighborNodeIterator();
                while(nVecino.hasNext()) {
                    Node n = nVecino.next();
                    Message reenvio = msg.clone();
                    reenvio.setDestination(n.getId());
                    this.sendMessage(reenvio, reenvio.getDestination());
                    
                }
            }
            sleep(100);
        }
    }

    


    public String getText(){
	String s = super.getText();
	if(node!=null){
	    s+="ID: " + node.getId();
	}
	return s;
    }
 

    public boolean sendMessage(Message m, String destination){
	this.setFillColor(COLOR_SEND);
	boolean status = transport.put(m, destination);
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
