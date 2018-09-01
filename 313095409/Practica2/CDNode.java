import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import java.util.Iterator;
import javax.swing.JLabel;
import java.awt.Color;

public class CDNode extends JLabel implements Runnable{

  public static final String COLOR_DEFAULT = "blue";
  public static final String COLOR_SEND = "red";
  public static final String COLOR_READ = "green";


  private Node node;
  private boolean activo;
  private Transport transport;
  private CDGraph graph;


  public CDNode(CDGraph g, Node n){
  	super();
  	this.node = n;
  	this.activo = true;
  	this.transport = Transport.getInstance();
  	this.graph = g;
  	this.setFillColor(COLOR_DEFAULT);
  }

  public void run(){
    while (activo) {
      for (Edge edge : node.getEachEdge()) {
        Node nodo_ = edge.getOpposite(node);
        while(!sendMessage(new Message("0"), nodo_.getId())){sleep(500);}
      }

      sleep(500);
      Message m = readMessage();
      sleep(500);
      if (m == null)
      continue;

      if (m.tiempoVida()>0) {
        m.reduceTiempo();
        for (Edge edge : node.getEachEdge()) {
          Node nodo_ = edge.getOpposite(node);
          while(!sendMessage(m, nodo_.getId())){sleep(500);}
        }
      }
    }
  }


  public String getText(){
  	String s = super.getText();
  	if(node!=null)
      s+="ID: " + node.getId();
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
  	} catch(Exception ex){}
  }
}
