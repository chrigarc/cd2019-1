import java.net.*;
import java.io.*;
import java.util.Random;

public class Enviamsn {

  private int puerto;
  private String host;
  //mensaje
  public String mssn;

  public Enviamsn(String host){
    this.puerto = this.PuertoRandom();
    this.host = host;
    this.mssn = "";
  }

  public int PuertoRandom(){
    int puertos[];
    puertos = new int[6];
    puertos[0] = 25000;
    puertos[1] = 25001;
    puertos[2] = 25003;
    puertos[3] = 25004;
    puertos[4] = 25005;
    puertos[5] = 25006;
    //numero aleatorio
    Random aleatorio = new Random();
    int na = 1+aleatorio.nextInt(5);
    //numero de puerto
    int receiverPort = puertos[na];
    return receiverPort;
  }

  public int getPuerto(){
    return this.puerto;
  }

  public String getHost(){
    return this.host;
  }

  public void sendMsn(String mensaje){
    try {
      //nombre del Host
      setMensaje(mensaje);
      InetAddress receiverHost = InetAddress.getByName(this.host);
      //mensaje
      String message = mensaje;
      // inicializa un datagram socket para enviar info
      DatagramSocket mySocket = new DatagramSocket();
      byte[ ] buffer = message.getBytes( );
      DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, receiverHost, this.puerto);
      mySocket.send(datagram);
      System.out.print("El mensaje fue enviado por el puerto: " +
                        Integer.toString(this.puerto) + "\n");
      mySocket.close( );
      }
      catch (Exception ex) {
        ex.printStackTrace( );
     }
   }

   public void setMensaje(String m){
     this.mssn = m;
   }

   public String getMensaje(){
     return this.mssn;
   }
}
