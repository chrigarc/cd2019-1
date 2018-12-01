import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.*;
import java.awt.Color;

public class P5{ 

    public static void main(String args[]){
        System.out.println("Proporcione número de cuenta:");
        Scanner sc = new Scanner(System.in);
        String noCuenta = sc.nextLine();

        System.out.print("Seleccione a qué puerto quiere mandar el dato:");
        System.out.print("1) 25000 \n 2) 25001 \n 3) 25002 \n 4) 25003 \n 5) 25004 \n 6) 25005 \n");
        int puertoSel = sc.nextInt();
        switch (puertoSel) {
            case 1:
               mandar(25000, noCuenta);
               break;
          case 2:
               mandar(25001, noCuenta);
               break;
          case 3:
               mandar(25002, noCuenta);
               break;
          case 4:
               mandar(25003, noCuenta);
               break;
          case 5:
               mandar(25004, noCuenta); 
               break;
          case 6:
               mandar(25005, noCuenta);
               break;
          default:
               System.out.println("error" );
               break;
        }
     System.out.println(Double.MAX_VALUE);
    }



    public byte [] toByteArray(){
            try{
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream (bytes);
                os.writeObject(this);
                os.close();
                return bytes.toByteArray();
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }

    public static void mandar(int puerto, String noCuenta){
        try{
            Graph graph = new SingleGraph("Práctica 5");
            DatagramSocket socket = new DatagramSocket(puerto, InetAddress.getByName("localhost"));
            String puertoStr= Integer.toString(puerto);
            DatoUdp elDato = new DatoUdp(noCuenta);
            byte[] elDatoEnBytes = elDato.toByteArray();
            DatagramPacket dato = new DatagramPacket(elDatoEnBytes,elDatoEnBytes.length, InetAddress.getByName("localhost"),puerto);
            System.out.println("Envio dato " + noCuenta);
            socket.close();
            
            if(socket.isBound()){
                graph.addNode(noCuenta);
                graph.addNode(puertoStr);
                graph.addEdge("edge",noCuenta,puertoStr);
                for(Node m : graph.getEachNode()){
                    m.addAttribute("ui.label", m.getId());
                    m.setAttribute("ui.style", "fill-color: blue ;");
                }
            }
            graph.display();
        }catch (Exception e){
                e.printStackTrace();
        }   
    }

}
