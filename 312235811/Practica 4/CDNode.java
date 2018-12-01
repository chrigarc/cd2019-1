import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import java.awt.Color;
import java.lang.Math;
 public class CDNode extends JLabel implements Runnable{
   public static final String COLOR_DEFAULT = "blue";
  public static final String COLOR_SEND = "red";
  public static final String COLOR_READ = "green";
  public static List<Message> mensajes = new ArrayList<Message>();
  public static int contadorReloj = 0;
   private Node node;
  private boolean activo;
  private Transport transport;
  private CDGraph graph;
  private int reloj;
   public CDNode(CDGraph g,Node n){
    super();
    this.node = n;
    this.activo = true;
    transport = Transport.getInstance();
    this.graph = g;
    this.setFillColor(COLOR_DEFAULT);
    contadorReloj = contadorReloj + 10;
    this.reloj = contadorReloj;
  }
   public void run(){
    Iterable<Edge> ite = this.node.getEdgeSet();
    String nodeS = this.getText();
    Node destNode = null;
    Message message = null;
    boolean status = true;
    int temporal = 0;
    if(this.node.getDegree() != 0) {
      for(Edge edge: ite) {
          destNode = edge.getOpposite(this.node);
          message = new Message(nodeS);
          temporal = this.reloj + 1;
          this.sendMessage(message, destNode.toString(), temporal);
      }
      while(this.activo)
      {
          temporal = this.reloj + 1;
          message = this.readMessage(temporal);
          for(Edge edge: ite) {
              destNode = edge.getOpposite(this.node);
              temporal = this.reloj + 1;
              status = this.sendMessage(message, destNode.toString(), temporal);
              if(status) {
                  System.out.println("Reloj: " + this.reloj + " Envío del mensaje: " + nodeS + "->" + destNode.toString() +"\n");
              }
          }
      }
    }
  }
   public String getText(){
    String s = super.getText();
    if(node!=null){
      s+= "ID: " + node.getId();
    }
    return s;
  }
   public boolean sendMessage(Message m, String destination, int valorReloj){
    //se altera el mensaje con nueva info
    m.setEnd(destination);
    String recorrido = m.getRecorrido();
    recorrido += " -> " + destination;
    m.setRecorrido(recorrido);
    int pasos = m.getPasos();
    m.setPasos(++pasos);
    mensajes.add(m);
     this.setFillColor(COLOR_SEND);
    boolean status = transport.put(m, destination, valorReloj);
    this.setFillColor(COLOR_DEFAULT);
    return status;
  }
   public Message readMessage(int valorReloj){
    int mayor = Math.max(this.reloj, valorReloj) + 1;
    this.reloj = mayor;
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
   public String toStringMensajes()
  {
    String s = "-------MENSAJES------\n\n";
    Message m = null;
    for(int i=0; i < mensajes.size(); i++) {
        m = (Message) mensajes.get(i);
        s += "Info mensaje:\n" +
             "Origen: " + m.getSource() + "\n" +
             "Final: " + m.getEnd() + "\n" +
             "Pasos: " + m.getPasos() + "\n" +
             "Recorrido: " + m.getRecorrido() + "\n\n";
    }
    return s;
  }
   public List<Message> getMensajes()
  {
      return mensajes;
  }
}