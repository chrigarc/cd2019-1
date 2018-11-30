import java.net.*;
import java.io.*;
import java.util.Random;

public class Enviamsn {
  public static void main(String[] args) {
    int puertos[];
    puertos = new int[6];
    puertos[0] = 25000;
    puertos[1] = 25001;
    puertos[2] = 25003;
    puertos[3] = 25004;
    puertos[4] = 25005;
    puertos[5] = 25006;
    Random aleatorio = new Random();
    int na = 1+aleatorio.nextInt(5);
    try {
      //nombre del Host
      InetAddress receiverHost = InetAddress.getByName(args[0]);
      //numero de puerto
      int receiverPort = puertos[na];
      //mensaje
      String message = "31121791-9";
      // inicializa un datagram socket para enviar info
      DatagramSocket mySocket = new DatagramSocket();
      byte[ ] buffer = message.getBytes( );
      DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, receiverHost, receiverPort);
      mySocket.send(datagram);
      System.out.print("El mensaje fue enviado por el puerto: " +
                        Integer.toString(receiverPort) "\n");
      mySocket.close( );
      } // end try
      catch (Exception ex) {
        ex.printStackTrace( );
      }
  } // end main
}
