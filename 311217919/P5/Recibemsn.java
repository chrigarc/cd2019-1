import java.net.*;
import java.io.*;

public class Recibemsn {
    private int port;
    private final static int MAX_LEN = 10;

    public Recibemsn(int port){
      this.port = port;
    }

    public void receiveMsn(){
      try {
        DatagramSocket  mySocket = new DatagramSocket(this.port);
        // instantiates a datagram socket for receiving the data
        byte[ ] buffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(buffer, MAX_LEN);
        mySocket.receive(datagram);
        String message = new String(buffer);
        System.out.println(message);
        mySocket.close( );
      }
      catch (Exception ex) { ex.printStackTrace();}
    }

  }
