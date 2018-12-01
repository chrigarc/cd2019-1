import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class P2P implements Runnable{ 
  
   /** Codigo escrito por Eduardo Rubio Lezama */
   public static final int PUERTO_DEL_SERVIDOR=25000;
   
   /** Codigo escrito por ian Eduardo Chavez Munioa  */
   public static final int PUERTO_DEL_CLIENTE=25001;
   
   /** Codigo escrito con Luis Pulido Alvarez */
   public static final String HOST_SERVIDOR="255.255.255.255";
   
   /** Codigo escrito por Mauricio Salas */
   public static final String HOST_CLIENTE="255.255.255.255";
    public static void main(String[] args)
   {
    new Thread(new P2P()).start();
    new Thread(new ServidorUdp()).start();
    
    
}
 /*Codigo escrito por Ian Eduardo Chavez Munioa*/
 public void run(){
    while (true) {
        try
        {
            DatagramSocket reflejo = new DatagramSocket();
             String cCadena = "310027276";
            byte[] elDatoEnBytes = cCadena.getBytes();
             
            DatagramPacket informacion = new DatagramPacket(elDatoEnBytes,
                elDatoEnBytes.length, InetAddress
                .getByName(HOST_SERVIDOR),
                PUERTO_DEL_SERVIDOR);
            
            
            System.out.println("Dato Enviado ");
            reflejo.send(informacion);
            reflejo.close();
            Thread.sleep(100);
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
} 