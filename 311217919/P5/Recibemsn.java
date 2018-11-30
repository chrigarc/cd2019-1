import java.net.*;
import java.io.*;

public class Recibemsn {
  public static void main(String[] args) {
    //si no se especifica el puerto a escuchar lanza error
    if (args.length != 1)
      System.out.println("Se necesita el numero de puerto");
    else {
      int port = Integer.parseInt(args[0]);
      //tamaño maximo del msj en bytes
      final int MAX_LEN = 10;
      try {
        DatagramSocket  mySocket = new DatagramSocket(port);
        // instantiates a datagram socket for receiving the data
        byte[ ] buffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(buffer, MAX_LEN);
        mySocket.receive(datagram);
        String message = new String(buffer);
        System.out.println(message);
        mySocket.close( );
      }
      catch (Exception ex) {
        ex.printStackTrace( );
      }
    } // end else
  } // end main
} // end class
