import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class P2P implements Runnable{ 
  
    //Puerto en el que escucha el servidor. 
   public static final int PUERTO_DEL_SERVIDOR=25000;
   
    //Puerto en el que escucha el cliente 
   public static final int PUERTO_DEL_CLIENTE=25001;
   
    //Host del servidor 
   public static final String HOST_SERVIDOR="255.255.255.255";
   
    //Host del cliente 
   public static final String HOST_CLIENTE="255.255.255.255";


    public static void main(String[] args){
    new Thread(new P2P()).start();
    new Thread(new ServidorUdp()).start();
}

 public void run(){
    while(true){
        try{
            DatagramSocket socket = new DatagramSocket();
	    String usoDato = "314091057";
            byte[] datoBytes = usoDato.getBytes();
             
            DatagramPacket dato = new DatagramPacket(datoBytes, datoBytes.length, InetAddress.getByName(HOST_SERVIDOR),
						     PUERTO_DEL_SERVIDOR);
            
            System.out.println("Enviado ");
            socket.send(dato);
            socket.close();
            Thread.sleep(100);
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
 }
} 
