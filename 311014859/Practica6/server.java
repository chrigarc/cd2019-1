

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
*
*Codigo escrito por Eduardo Rubio Lezama
*
*/
public class server {

    public static byte[][] byteValues = new byte[5][];
    //public static ArrayList<int> sentPackets = new ArrayList<int>();
    public static ArrayList<Integer> acknowledgedPackets = new ArrayList<Integer>();
    public static int minValGlobal = 0;
    public static int startByteGlobal = 0;
    public static String fileNameGlobal;
    public static File myFileGlobal;
    public static int packetNumGlobal = 0;
    public static int packetAmountGlobal = 0; //TOTAL AMOUNT OF PACKETS GETS SET BY FILSESIZE IN 'F'
    public static FileInputStream fisGlobal;
    public static int counterMinGlobal = 0;
    public static int counterMaxGlobal = 0;
    public static SocketAddress clientaddr;
    public static DatagramChannel myc;
    // the acknowledgment were looking for aka the lowest packet in the array

    public static void main(String args[]) {
        try {
            int wantedAcknowledged = 0;
            // scanner for reading in data
            Scanner scan = new Scanner(System.in);
            // port to use provided by server
             // String addr = ipAddress(scan);

              int port = Integer.parseInt(getPort(scan));
              validPort(port);

             //int port = 9999;
            // opens a channnel to communicate through
            DatagramChannel c = DatagramChannel.open();
            // think of as a set of channels - along with an associated operation, int eh set for reading or writing? meant help check multiple channels at a time.
            Selector s = Selector.open();
            // is there data available at this instance? if then return if not go past the line // block forever or not but the selector lets us block for a certain amount of time
            c.configureBlocking(false);
            // ??????
            c.register(s, SelectionKey.OP_READ);
            // lsiten on a port
            c.bind(new InetSocketAddress(port));
            // get the info as long as there is a thing open to read from
            while (true) {
                //check if there is data to read
                int n = s.select(5000);

                if (n == 0) {
                    // didnt get any packets
                    System.out.println("Got a timeout");
                    if (byteValues[0] != null && packetNumGlobal < packetAmountGlobal) {
                        handleTimeouts(myc, clientaddr);
                    }

                } else { // got something
                    // ????????
                    Iterator i = s.selectedKeys().iterator();
                    // while the iterator had something in it?
                    while (i.hasNext()) {
                        // ????????
                        SelectionKey k = (SelectionKey) i.next();
                        // New channel to communicate with
                        myc = (DatagramChannel) k.channel();
                        // allocate space for the data to be held in the buffer
                        ByteBuffer buffer = ByteBuffer.allocate(1028);
                        //takes the info received form the buffer and returns the data
                        clientaddr = c.receive(buffer);
                        //flip the buffer to restrict the buffer to the content
                        buffer.flip();
                        // the bytes of the message
                        //.out.println("buffer position: " + buffer.position()+ "buffer length: " + buffer.limit());
                        char index = buffer.getChar();
                        //ack for packet num on clients end
                        if (index == 'B') {
                            int packetNum = buffer.getInt();
                            if (packetNum == minValGlobal) {     // is the packet we got what were looking for
                                //  remove the data from the storage window, put the next packet in the storage window, send that next packet, wantedAcknowledged++;,  minValGlobal++;
                                System.out.println("Got acknowledgment for packet: " + packetNum);
                                addAcknowledgement(packetNum); //
                                ///minValGlobal++;
                                checkWindow(myc, clientaddr);// removes the packet we got,
                            } else if (packetNum < minValGlobal || packetNum > (minValGlobal + 5)) {// the ack we got was a packet we already recived and isn not in the current window
                                /// should do nothing
                                //System.out.println("Got WRONG acknowledgment in if 2");
                            } else if (packetNum < (minValGlobal + 5) && packetNum > minValGlobal) {// if its in the window but not what we are looking for
                                addAcknowledgement(packetNum);
                                System.out.println("Got acknowledgment for packet: " + packetNum);
                            }
                        }

                        // F means we got filename, now we send to client packet Number.
                        else if (index == 'F') {
                            byte[] msgBuffer = new byte[buffer.remaining()];
                            // get the data from the buffer to the msgbuffer
                            buffer.get(msgBuffer);
                            // convert byte buffer into a string just a new way to do it old can still work old way is better tho// only good for fresh buffers ^
                            String message = new String(msgBuffer);
                            // print the message to the console        vcbvbc
                            System.out.println("Filename: " + message);
                            // file exists
                            if (fileExists(message)) {
                                myFileGlobal = new File(message);
                                try {
                                    fisGlobal = new FileInputStream(myFileGlobal);
                                } catch (Exception e) {
                                   // System.out.println("Exception: " + e);
                                }
                                ByteBuffer sendPacketNumber = ByteBuffer.allocate(1028);
                                packetAmountGlobal = fileSize(myFileGlobal.length());
                                sendPacketNumber.putInt(packetAmountGlobal);
                                sendPacketNumber.flip();
                                //System.out.println("Packets needed to be sent: " + packetAmountGlobal);
                                myc.send(sendPacketNumber, clientaddr);
                            }
                            // file doesnt exist
                            else {
                                ByteBuffer noFile = ByteBuffer.allocate(1028);
                                String nope = "File non existant";
                                noFile.putInt(-6);
                                byte[] noData = nope.getBytes();
                                noFile.put(noData);
                                noFile.flip();
                                myc.send(noFile, clientaddr);
                            }
                        }
                        //Acknowledged, send first 5.
                        else if (index == 'A') {

                            int again = buffer.getInt();
                            if (!acknowledgedPackets.contains(-10)) {
                                addAcknowledgement(again);
                                System.out.println("Acknowedged the initial File Name packet.");
                                if (packetAmountGlobal > 0) {
                                    startSlidingWindow(myc, clientaddr);
                                }
                            }
                        }
                        buffer.rewind();
                        i.remove();
                        acknowledgedPackets.clear();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Got an error: " + e);
        }
    }

    public static String getPort(Scanner scan) {
        System.out.println("Please enter a port to connect to");
        String info = scan.next();
        return info;
    }

    // does the file exist
    public static Boolean fileExists(String fileName) {
        File myFile = new File(fileName);
        if (myFile.exists() && !myFile.isDirectory()) {
            return true;
        }
        return false;
    }

    // calculate the total ammount of packets that needs to be sent
    public static int fileSize(long fileSize) {
        if (myFileGlobal.exists() && !myFileGlobal.isDirectory()) {
            //System.out.println("Size of file: " + fileSize);
            double count = fileSize / 1024.0;
            int ceiledValue = (int) Math.ceil(count);
            return ceiledValue;
        } else
            System.out.println("The file does not exist!");
        return 0;
    }

    public static int checkMin() {
        while (acknowledgedPackets.contains(minValGlobal)) {
            minValGlobal++;
        }
        return minValGlobal;
    }

    //send the first 5 packets in the file (if there are at least 5)
    public static void startSlidingWindow(DatagramChannel myC, SocketAddress cAddr) {
        //reset useful global variables to 0
        minValGlobal = 0;
        packetNumGlobal = 0;
        startByteGlobal = 0;
        counterMinGlobal = 0;
        //send one packet?
        if (packetNumGlobal + 1 == packetAmountGlobal) {
            ByteBuffer buf = ByteBuffer.allocate(1028);
            buf.putInt(packetNumGlobal);
            //What if its tom who has only 53 bytes? will create null bytes.
            byteValues[packetNumGlobal] = new byte[(int) myFileGlobal.length() - startByteGlobal];
            try {
                fisGlobal.read(byteValues[packetNumGlobal], 0, (int) myFileGlobal.length() - startByteGlobal);
            } catch (IOException e) {
                e.printStackTrace();
            }
            buf.put(byteValues[packetNumGlobal]);
            buf.flip();
            try {
                myC.send(buf, cAddr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Sent Packet:" + packetNumGlobal);
            packetNumGlobal++;
            startByteGlobal += (int) myFileGlobal.length();  /// final fix ??????
        }
        //check in there are not at least 5 packets to send
        else if (packetAmountGlobal < 5) { // problem if we arent even using one whole packet
            for (int i = 0; i < packetAmountGlobal; i++) {
                ByteBuffer buf = ByteBuffer.allocate(1028);
                buf.putInt(packetNumGlobal);
                //What if its tom who has only 53 bytes? will create null bytes.
                byteValues[i] = new byte[buf.remaining()];
                try {
                    fisGlobal.read(byteValues[i], 0, 1024);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                buf.put(byteValues[i]);
                buf.flip();
                try {
                    myC.send(buf, cAddr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Sent Packet:" + packetNumGlobal);
                packetNumGlobal++;
                startByteGlobal += 1024;
            }
        }

        //send if there are at least 5 packets to send
        else {
            for (int i = 0; i < 5; i++) {
                ByteBuffer buf = ByteBuffer.allocate(1028);
                buf.putInt(packetNumGlobal);
                byteValues[i] = new byte[buf.remaining()];
                try {
                    fisGlobal.read(byteValues[i], 0, 1024);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                buf.put(byteValues[i]);
                buf.flip();
                try {
                    myC.send(buf, cAddr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Sent Packet:" + packetNumGlobal);
                packetNumGlobal++;
                startByteGlobal += 1024;
                //counterMinGlobal++;
            }
        }
    }

    // this method sends the the next unsent packet
    public static void sendPacket(DatagramChannel myC, SocketAddress cAddr) {
        //this will send the packet if it isn't the the last one
        if (packetNumGlobal < packetAmountGlobal - 1) {
            try {
                ByteBuffer buf = ByteBuffer.allocate(1028);
                buf.putInt(packetNumGlobal);
                byteValues[4] = new byte[buf.remaining()];
                fisGlobal.read(byteValues[4], 0, 1024);
                buf.put(byteValues[4]);
                buf.flip();
                myC.send(buf, cAddr);
                System.out.println("Sent Packet:" + packetNumGlobal);
                packetNumGlobal++;
                startByteGlobal += 1024;
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Exception: " + e);
                e.printStackTrace();
            }
        }
        //this will send a packet if it is the last packet
        else {
           // System.out.println("in this send packet method");
            try {
                ByteBuffer buf = ByteBuffer.allocate(1028);
                buf.putInt(packetNumGlobal);
               // System.out.println("myFileGlobal.length()" + myFileGlobal.length() + "startByteGlobal " + startByteGlobal);
                byteValues[4] = new byte[(int) myFileGlobal.length() - startByteGlobal];//1024
                fisGlobal.read(byteValues[4], 0, (int) myFileGlobal.length() - startByteGlobal);
                //System.out.println("solved");
                buf.put(byteValues[4]);
                fisGlobal.close();
                buf.flip();
                myC.send(buf, cAddr);
                System.out.println("Sent Packet:" + packetNumGlobal);
                packetNumGlobal++;
                startByteGlobal += (int) myFileGlobal.length() - startByteGlobal;

            } catch (Exception e) {
                System.out.println("Exception: " + e);
                e.printStackTrace();
            }
        }
    }

    // shift the array down and send then check next spot
    public static void checkWindow(DatagramChannel myC, SocketAddress cAddr) {
        //If it doesnt contain wat we are looking for return.
        //System.out.println("CHECK WINDIW IS CALLED!! and min val is: " + minValGlobal);
        if (!acknowledgedPackets.contains(minValGlobal)) {
            return;
        }
        //if the packet now is ALREADY ack.
       // System.out.println(minValGlobal);
        if (acknowledgedPackets.contains(minValGlobal) && packetNumGlobal <= packetAmountGlobal) { // is the min value already ackd ie in list
            byteValues[0] = null;// if so set teh lowest window bytes to null
            for (int i = 0; i < 4; i++) { // this will shift our values, sets the current value to the next value
                byteValues[i] = byteValues[i + 1];
            }
            for (int i = 0; i < 5; i++) {
          //      System.out.println("Element: " + i + " contains: " + byteValues[i]);
            }
            byteValues[4] = null;//clears the last array spot
            sendPacket(myC, cAddr);
            minValGlobal++;

            checkWindow(myC, cAddr);
        }
    }

    // add the ack to our list
    public static void addAcknowledgement(int value) {
        acknowledgedPackets.add(value);
    }

    //	this method resends all of the packets that did not get acknowledged
    public static void handleTimeouts(DatagramChannel myC, SocketAddress cAddr) {
        int max = 5;
        if (packetAmountGlobal < 5)
            max = packetAmountGlobal;

        for (int i = 0; i < max; i++) {// 	what if were only sending two
            //if packet didn't get acknowledged
            if (!acknowledgedPackets.contains(minValGlobal + i)) {

                try {
                    //resend packets 0-4 in the byte array
                    ByteBuffer buf = ByteBuffer.allocate(1028);
                    buf.putInt(minValGlobal + i);
                    buf.put(byteValues[i]);
                    buf.flip();
                    myC.send(buf, cAddr);
                    System.out.println("Resent Packet:" + (minValGlobal + i));

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("Exception: " + e);
                }
            }
        }
    }

    public static void validPort(int port) {
        if (port > 1 && port < 65535) {
            return;
        }
        System.out.println("'" + port + "' is an invalid port address.");
        System.exit(0);
    }

    public static String ipAddress(Scanner scan) {
        System.out.println("Enter an IP address to connect to:");
        String info = scan.next();
        return info;
    }
}
