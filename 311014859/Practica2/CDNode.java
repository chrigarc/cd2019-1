import org.graphstream.graph.Node;
import java.util.Iterator;
import javax.swing.JLabel;
import java.awt.Color;




/*
**
**
**
** CLASE HECHA  POR IAN EDUARDO CHAVEZ MUNIOA.
**
**
**
**
*/

public class CDNode extends JLabel implements Runnable{

    
    public static final String COLOR_DEFAULT = "blue";
    
    
    
    public static final String COLOR_SEND = "red";
    
    

    public static final String COLOR_READ = "green";


    
    private Node nodo;
    private boolean bandera;
    private Transport transporta;
    private CDGraph grafica;


    
    public CDNode(CDGraph grafo,Node nodo){
	   super();
	   this.nodo = nodo;
	   this.bandera = true;
	   this.transporta = Transport.getInstance();
	   this.grafica = grafo;
	   this.setFillColor(COLOR_DEFAULT);
    }


    public String getText(){
	   String s = super.getText();
	   if(nodo!=null){
	       s+="ID: " + nodo.getId();
	   }
	   return s;
    }

    public void run(){
        
        while (bandera) {
            sleep(300);
            Iterator<Node> it = nodo.getNeighborNodeIterator();
            while(it.hasNext()){
                Node guardaNodo = it.next();
                Message newMessage = new Message(nodo.getId(), nodo.getId());
                this.sendMessage(newMessage, guardaNodo.getId());
            }
            sleep(300);
            Message mandaMensaje = this.readMessage();
            if(mandaMensaje != null){
                mandaMensaje.leerMessage();
                it = nodo.getNeighborNodeIterator();
                while(it.hasNext()){
                    Node guardaNodo = it.next();
                    this.sendMessage(mandaMensaje.clone(), guardaNodo.getId());
                }
            }
        }
    }


    public boolean sendMessage(Message m, String destination){
	   this.setFillColor(COLOR_SEND);
	   boolean disponibilidad = transporta.put(m, destination);
	   this.setFillColor(COLOR_DEFAULT);
	   return disponibilidad;
    }


    public void stop(){
	   this.bandera = false;
    }

    public Message readMessage(){
       this.setFillColor(COLOR_READ);
       Message mandaMensaje = transporta.pop(nodo.getId());
       this.setFillColor(COLOR_DEFAULT);
       return mandaMensaje;
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

    private void sleep(int ms){
	   try{
	       Thread.sleep(ms);
	   }catch(Exception ex){
	   }
    }
}