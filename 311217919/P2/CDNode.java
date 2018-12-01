import org.graphstream.graph.Node;
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

    public CDNode(CDGraph g,Node n){
	      super();
	      this.node = n;
	      this.activo = true;
	      transport = Transport.getInstance();
	      this.graph = g;
	      this.setFillColor(COLOR_DEFAULT);
    }


    public void run(){
      while(this.activo){
        iteraVecinos();
        sleep(300);
        Message recibido = readMessage();
        reenviaMsn(recibido);
      }
    }

    public void reenviaMsn(Message recibido){
      if(recibido != null){
      //volvemos a sacar los vecinos del nodo para enviar
      Iterator<Node> vecinos = node.getNeighborNodeIterator();
      if (recibido != null)
        while (vecinos.hasNext()) {
          Node vec = vecinos.next();
          Message mensajito = recibido.clone();
          sendMessage(mensajito, vec.getId());
        }
      }
    }

    public void iteraVecinos(){
      Iterator<Node> iterador = node.getNeighborNodeIterator();
      while(iterador.hasNext()){
        Node nodovecino = iterador.next();
        String s = nodovecino.getId();
        Message m = new Message(nodovecino.getId(), s);
        sendMessage(m, nodovecino.getId());
      }
    }

    public String getText(){
      String s = super.getText();
      if(node!=null){
	    s+="ID: " + node.getId();}
      return s;
    }

    public boolean sendMessage(Message m, String destination){
	  this.setFillColor(COLOR_SEND);
	  boolean status = transport.put(m, destination);
   	this.setFillColor(COLOR_DEFAULT);
  	return status;}


    public Message readMessage(){
      this.setFillColor(COLOR_READ);
      Message m = transport.pop(node.getId());
      this.setFillColor(COLOR_DEFAULT);
      return m;
    }


    public void stop(){
      this.activo = false;
    }

    //cambia el color del nodo
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
      }catch(Exception ex){}
      }
}
