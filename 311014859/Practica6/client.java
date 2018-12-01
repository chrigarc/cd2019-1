
import java.io.Console;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
*
*Codigo escrito por Mauricio Salas
*
*/ 

// It can currently read up to 5 packets.
public class client {
    // we need to store the data how an array of byte arrays of the packets we get on server side
    //
    public static byte[][] byteValues = new byte[5][];
    public static int[] numTracker = {0, 1, 2, 3, 4};
    //might nto need
    public static ArrayList<Integer> receivedPackets = new ArrayList<Integer>();
    public static int packetNumberTotal;

    public static void main(String args[]) {
        try {
            //create a scanner for user input
            Scanner scan = new Scanner(System.in);
            //get the server address
            Selector s = Selector.open();
            String addr = ipAddress(scan);
            //String addr = "127.0.0.1";
            //get the port to use
            int port = Integer.parseInt(portSelection(scan));
            validPort(port);
            //int port = 9999;
            DatagramChannel sc = DatagramChannel.open();
            //Selector s = Selector.open();
            // is there data available at this instance? if then return if not go past the line // block forever or not but the selector lets us block for a certain amount of time
            sc.configureBlocking(false);
            // ??????
            SelectionKey saved = sc.register(s, SelectionKey.OP_READ);
            Console cons = System.console();
            //store the filename the user requests and save it as m
            String m = getFileName(cons);
            int wantedPacket = 0;
            int packetNumberTotal = 0;
            //set the socket address to the server
            InetSocketAddress serverAddr = new InetSocketAddress(addr, port);
            //This is the while for Sending the File name w/ timeout resend
            while (true) {
                ByteBuffer buff = ByteBuffer.allocate(1026);
                buff.putChar('F');
                byte[] fileByte = m.getBytes();
                buff.put(fileByte);
                buff.flip();
                //send the buffer over the socket
                sc.send(buff, serverAddr);
                int n = s.select(5000);
                if (n == 0) {
                    // didnt get any packets
                    System.out.println("Got a timeout, resending file name.");
                    buff = ByteBuffer.allocate(1026);
                    buff.putChar('F');
                    fileByte = m.getBytes();
                    buff.put(fileByte);
                    buff.flip();
                    sc.send(buff, serverAddr);
                } else { //else we got a reponse from the server.
                    System.out.println("Got info from the server");

                    Iterator i = s.selectedKeys().iterator();
                    while (i.hasNext()) {
                        //System.out.println("does i have a next");

                        SelectionKey k = (SelectionKey) i.next();

                        i.remove();
                    }
                    //System.out.println("After interator");
                    //The packetnumber the server sends back.
                    ByteBuffer getA = ByteBuffer.allocate(1028);
                    sc.receive(getA);
                    getA.flip();
                    packetNumberTotal = getA.getInt();
                    //System.out.println("The packet number after the i.remove iterator" + packetNumberTotal);
                    if (packetNumberTotal == -6) {
                        System.out.println("The file does not exist.");
                        sc.close();
                        return;//maybe system.exit.
                    }
                    //File exists, break out of this loop to go to next loop
                    //to send acknowledgemnt.
                    else if (packetNumberTotal > 0) {
                        System.out.println(packetNumberTotal);
                        break;
                    }
                }
            }
            //This is for sending the acknowledgment that client received the packet number w/ resend
            while (true) {
                ByteBuffer ackBuffer = ByteBuffer.allocate(1028);
                //Need to implement for server: if server gets A again, resend all data.
                ackBuffer.putChar('A');
                ackBuffer.putInt(-10);
                ackBuffer.flip();
                sc.send(ackBuffer, serverAddr);
                int n = s.select(5000);
                if (n == 0) {
                    // didnt get any packets
                    System.out.println("Got a timeout");
                } else { //else we got data from server.
                    break;
                }
            }
            //Set blocking true so that we hang at receives. and send back acknowledgements
            saved.cancel();
            sc.configureBlocking(true);
            FileOutputStream fos = new FileOutputStream("dl-" + m);
            // reciveing data
            while (wantedPacket != packetNumberTotal) { // this could need to change if the packet we want is no thet final packet
                //our recive buffer created every loop because unless its the last packet we keep going
                ByteBuffer receivedPackets = ByteBuffer.allocate(1028);
                sc.receive(receivedPackets);
                receivedPackets.flip();
                //the packet number fo rhte packet jsut recived form teh server
                int packetNum = receivedPackets.getInt();
                //System.out.println("The packet number of this packet is: " + packetNum);
                // first if for case 1: the packet we got from teh server is teh packet we want form teh server ie: hte lowest in our window
                if (packetNum == wantedPacket) {
                    byte[] data = new byte[receivedPackets.remaining()];
                    receivedPackets.get(data);
                    // data has our bytes
                    // write to file
                    wantedPacket++;
                    fos.write(data);
                    // send ack for the packet we just got and wrote to file
                    sendAcknowledgment(packetNum, sc, serverAddr);
                    // check our window
                    wantedPacket = checkWindow(wantedPacket, fos);
                } else if (packetNum < wantedPacket || packetNum > (wantedPacket + 5)) { // packet duplicates
                    // if the packet is not in the window we are looking for
                    //System.out.println("Got duplicate");
                    // send the packet to the server that we got this already if its less than the wanted or more then the window +5
                    sendAcknowledgment(packetNum, sc, serverAddr);
                } else if (packetNum > wantedPacket && packetNum < (wantedPacket + 5)) {
                    //if the packet is in our window but not what we need right now
                    if(packetNum<wantedPacket){
                        //System.out.println("got duplicate" + packetNum);

                    }
                    int loc = indexSpot(packetNum);
                    if (loc != -1) { // packet we jsut got is in our window
                        if (byteValues[loc] == null) { // its not a duplicate
                            byte[] data = new byte[receivedPackets.remaining()]; // write the data to teh proper location in the array
                            receivedPackets.get(data);
                            byteValues[loc] = data;
                            sendAcknowledgment(packetNum, sc, serverAddr); // send ack to server
                        }
                    } else {
                        //System.out.println("got duplicate" + packetNum);
                        sendAcknowledgment(packetNum, sc, serverAddr); // send ack to server that we got a duplicate
                    }
                } else if (packetNum == packetNumberTotal) {
                    byte[] data = new byte[receivedPackets.remaining()]; // write the data to teh proper location in the array
                    receivedPackets.get(data);
                    fos.write(data);
                    //send an ack to the server
                    sendAcknowledgment(packetNum, sc, serverAddr);
                    sc.close();
                    System.exit(0);
                }

            }


        } catch (IOException e) {
            System.out.println("error " + e);
        }
    }

    public static String portSelection(Scanner scan) {
        System.out.println("Enter a port to connect to:");
        String info = scan.next();
        return info;
    }

    public static String ipAddress(Scanner scan) {
        System.out.println("Enter an IP address to connect to:");
        String info = scan.next();
        return info;
    }

    public static String getFileName(Console cons) {
        String info = cons.readLine("Enter a file name: ");
        return info;
    }

    public static int checkWindow(int wantedPacket, FileOutputStream fos) {
        int loc = indexSpot(wantedPacket);
        // if what were looking for isnt in our window already break out of recursiona nd go back to reciving packets
        if (loc == -1) {
            return wantedPacket;
        }
        if (byteValues[loc] == null) {
            return wantedPacket;
        }
        // the packet were looking for is in the window
        else {
            byte[] data = byteValues[loc];
            wantedPacket++;
            try {
                fos.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            moveDataWindow();
            moveNumTracker();
            return checkWindow(wantedPacket, fos);
        }
    }


    // return the num the current packet of data is stored in out window
    public static int indexSpot(int packetNum) {
        for (int i = 0; i < numTracker.length; i++) {
            if (numTracker[i] == packetNum) {
                return i;
            }
        }
        return -1;
    }

    public static void moveDataWindow() {
        byteValues[0] = null;// if so set the lowest window bytes to null
        for (int i = 0; i < 4; i++) { // this will shift our values, sets the current value to the next value
            byteValues[i] = byteValues[i + 1];
        }
        byteValues[4] = null;//clears the last array spot
    }

    public static void moveNumTracker() {
        numTracker[0] = 0;// if so set the lowest window bytes to null
        for (int i = 0; i < 4; i++) { // this will shift our values, sets the current value to the next value
            numTracker[i] = numTracker[i + 1];
        }
        if (numTracker[3] != 0 && numTracker[3] != packetNumberTotal - 1)
            numTracker[4] = numTracker[3] + 1;
    }

    public static void sendAcknowledgment(int packetNum, DatagramChannel sc, InetSocketAddress serverAddr) {
        System.out.println("Sending Ack for packet number: " + packetNum);
        ByteBuffer sendingPacket = ByteBuffer.allocate(1024);
        sendingPacket.putChar('B');
        sendingPacket.putInt(packetNum);
        sendingPacket.flip();
        try {
            sc.send(sendingPacket, serverAddr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validPort(int port) {
        if (port > 1 && port < 65535) {
            return;
        }
        System.out.println("'" + port + "' is an invalid port address.");
        System.exit(0);
    }

}

