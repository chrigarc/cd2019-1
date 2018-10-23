import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
public class P2P extends Thread{

    public static final int[] PUERTOS = {25000, 25001, 25002, 25003, 25004, 25005};

    private boolean activo;
    private int puerto;
    private String identificador;
    private InetAddress broadcast;
    private P2PServidor servidor;

    public P2P(int puerto, String identificador){
        super();
        this.puerto = puerto;
        try{
            broadcast = InetAddress.getByName("255.255.255.255");
        }catch(Exception ex){
            System.out.println(ex);
        }
        this.identificador = identificador;
        activo = true;
        servidor = new P2PServidor(puerto, identificador);
    }

    public void run(){
        servidor.start();
        while(activo){
            try{
                for(int i:PUERTOS){
                  //  if(i!=puerto){
                        DatagramSocket socketUDP = new DatagramSocket();
                        DatagramPacket peticion = new DatagramPacket(identificador.getBytes(), identificador.length(), broadcast, i);
                        socketUDP.send(peticion);
                        socketUDP.close();
                  //  }
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
