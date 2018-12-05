import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
 
public class ServidorUdp implements Runnable {

    //Escucha al servidor
    public static final int PUERTO_DEL_SERVIDOR=25000;

    //Escucha al cliente
    public static final int PUERTO_DEL_CLIENTE=25001;

    //Host servidor
    public static final String HOST_SERVIDOR="255.255.255.255";

    //Host cliente
    public static final String HOST_CLIENTE="255.255.255.255";

    DatagramSocket socket ;

    public ServidorUdp(){
	try{
	    socket = new DatagramSocket(PUERTO_DEL_SERVIDOR, InetAddress.getByName("0.0.0.0"));
	}catch(Exception e){ 
	}
    }

    public void run(){
	while(true){
	    try{
		DatagramPacket dato = new DatagramPacket(new byte[100], 100);
		while (true){
		    socket.receive(dato);
		    System.out.print("Recibido de " + dato.getAddress().getHostName() + " : ");
		    String recibido = new String(dato.getData(), 0, dato.getLength());
		    System.out.println(recibido);
		}
	    } catch(Exception e){
		e.printStackTrace();
	    }
        }
    }
}
