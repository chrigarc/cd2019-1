import org.graphstream.graph.Node;
import java.util.Iterator;
import javax.swing.JLabel;
import java.awt.Color;

public class CDNode extends JLabel implements Runnable{

    public static final String COLOR_DEFAULT = "blue";
    public static final String COLOR_SEND = "red";
    public static final String COLOR_READ = "green";
    
    
    private Node node;
    private boolean activo;//Para saber cuando CDNode puede enviar mensajes.
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
    	/*
		System.out.println("¿Qué necesitas que haga para que un nodo genere un mensaje en cada iteración y lo mande a todos sus vecinos, 
							y despues lea un mensaje de y lo reenvie a todos sus vecinos siempre y cuando su tiempo de vida, del mensaje, lo permita?");
		*/
		//Mientras que CDNode pueda enviar mensajes.
		while(activo){
			//iterador para enviar mensajes a los vecinos del nodo de CDNode
			Iterator<Node> iterador = node.getNeighborNodeIterator();
			Node vecino;
			Message msjEnvio;
			//Generamos un mensaje en cada iteración.
			while(iterador.hasNext()){
				vecino = iterador.next();
				msjEnvio = new Message("De: "+this.node.getId()+"- Mensaje a -"+vecino.getId());//Generamos un nuevo mensaje para cada vecino.
				sendMessage(msjEnvio, vecino.getId());//enviamos un mensaje a cada vecino del nodo de CDNode
			}
			sleep(500);

			Message msjRecibido = readMessage();//Leemos un mensaje recibido.
			if(msjRecibido != null && msjRecibido.getTiempo() > 0){//Si el tiempo de vida del mensaje es mayor a cero lo reenviamos.
				Iterator<Node> iteradorReenvio = node.getNeighborNodeIterator();//iterador para reenviar a los vecinos.
				Node vecinoReenvio;
				//Reenviamos el mensaje recibido.
				while(iteradorReenvio.hasNext()){
					vecinoReenvio = iteradorReenvio.next();
					msjRecibido = new Message("De: "+this.node.getId()+"- Mensaje a -"+vecinoReenvio.getId());//Generamos un nuevo mensaje para cada vecino.
					msjRecibido.setTiempo(msjRecibido.getTiempo() -1); //disminuimos el tiempo de vida del mensaje
					sendMessage(msjRecibido, vecinoReenvio.getId());//enviamos un mensaje a cada vecino del nodo de CDNode
				}
			}
			sleep(500);
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
	this.activo = false;//Cuando ya debe dejar de enviar mensajes.
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
