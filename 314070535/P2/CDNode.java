import org.graphstream.graph.Node;
import java.util.Iterator;
import javax.swing.JLabel;
import java.awt.Color;

public class CDNode extends JLabel implements Runnable{

    public static final String COLOR_DEFAULT = "blue";
    public static final String COLOR_SEND = "red";
    public static final String COLOR_READ = "green";
    
        public enum Type{
        SOURCE, DESTINATION;
        SOURCE, DESTINATION, NORMAL;
    }
    public static String CreateMessage(String s, String d){
         return s + " -> " + d + " : " + System.nanoTime();
    }
    private Node node;
    private boolean activo;
    private Transport transport;
    private CDGraph graph;
    private LinkedList<Message> recibidos;
    private CDNode.Type type;
    private LinkedList<Message> exitosos;
    private CDNode.Type type;    
    
    public CDNode(CDGraph g,Node n){
	super();
	this.node = n;
	this.activo = true;
	transport = Transport.getInstance();
	this.graph = g;
	this.setFillColor(COLOR_DEFAULT);
	recibidos = new LinkedList<Message>();
    exitosos = new LinkedList<Message>();
    this.type = CDNode.Type.NORMAL;
    }
    
    public CDNode(CDGraph g,Node n, CDNode.Type type){
        this(g, n);
        this.type = type;
        if(type == CDNode.Type.DESTINATION){
            exitosos = new LinkedList<Message>();
        }
    }
    
    public LinkedList<Message> getExitosos(){
        return this.exitosos;
    }

    public LinkedList<Message> getMessages(){
        return recibidos;
    }    
    
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


    public String getText(){
	String s = super.getText();
	if(node!=null){
	        s+="ID: " + node.getId();
	    }
	    return s;
    }
    
    public Node getNode(){
        return node;
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
