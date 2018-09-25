import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
public class P2PServidor extends Thread{

    private DatagramSocket socket;
    private String identificador;
    private int puerto;
    private Graph graph;
    private boolean activo;

    public P2PServidor(int puerto, String identificador){
        graph = new SingleGraph("P2P " + identificador);
        graph.addNode(identificador).addAttribute("ui.label", identificador);
        activo = true;
        this.identificador = identificador;
        this.puerto = puerto;
        try{
            socket = new DatagramSocket(puerto,InetAddress.getByName("0.0.0.0"));
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void run(){
        graph.display();
        byte[] bufer = new byte[1000];
        String linea;
        while(activo){
            try{
                DatagramPacket mensajeEntrada = new DatagramPacket(bufer, bufer.length);
    	        socket.receive(mensajeEntrada);
    	        linea = new String(mensajeEntrada.getData(), 0, mensajeEntrada.getLength());
            	System.out.println("Recibido:" + linea + ".");
                if(graph.getNode(linea)==null){
                    graph.addNode(linea).addAttribute("ui.label", linea);
                    graph.addEdge(identificador + "-" + linea, identificador, linea);
                }
                sleep(100);
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
    }

    public void setActivo(boolean activo){
        this.activo = activo;
    }

}
