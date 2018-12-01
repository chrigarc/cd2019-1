import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
 
public class ServidorUdp implements Runnable {
   
    public static final int PUERTO_DEL_SERVIDOR=25000;
    
    
    public static final int PUERTO_DEL_CLIENTE=25001;
    
    
    public static final String HOST_SERVIDOR="255.255.255.255";
    
    
    public static final String HOST_CLIENTE="255.255.255.255";
 DatagramSocket reflejo ;
public ServidorUdp(){
try{
      reflejo = new DatagramSocket(
                    PUERTO_DEL_SERVIDOR, InetAddress
                            .getByName("0.0.0.0"));
     }catch(Exception e){
        
    }
 }
     public void run(){
    while (true){
        try
        {
            DatagramPacket informacion = new DatagramPacket(new byte[100], 100);
             // Codigo escrito por Maurisio Salas.
            while (true)
            {
                    // Codigo escrito por Luis Pulido Alvarez
                reflejo.receive(informacion);
                System.out.print("Dato Recibido de "
                        + informacion.getAddress().getHostName() + " : ");
                
                String datoRecibido = new String(informacion.getData(), 0, informacion.getLength());
                System.out.println(datoRecibido);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            }
        }
    }
}