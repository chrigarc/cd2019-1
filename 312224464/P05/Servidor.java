import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.awt.Color;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;


/**
 * Servidor de udp que se pone a la escucha de DatagramPacket que contengan
 * dentro DatoUdp y los escribe en pantalla.
 */
public class Servidor{

    public SingleGraph red;

    public static void main(String[] args){

        //System.out.println("Puerto que escuhará (puertos disponible: 25001, 25002, 25003, 25004, 25005)");
        //Scanner sc = new Scanner(System.in);
        //int puerto = sc.nextInt();
        System.out.println("Puerto en el que escuchará: "+ 25000);
        Servidor servidor = new Servidor();
    }

    /**
     * Crea una instancia de esta clase, poniendose a la escucha del puerto
     * definido en Constantes y escribe en pantalla todos los mensajes que le
     * lleguen.
     */
    public Servidor(){
        try{
            
            SingleGraph red = new SingleGraph("RED P2P");
            //agregamos un estilo con el que daremos forma a la gráfica.
            red.addAttribute("ui.stylesheet", "node{ fill-color: black; } node.principal {fill-color: red; size: 20px; } node.ultimo {fill-color: blue; size: 15px; }");
            red.display();

            System.out.println(" >>> Servidor escuchando ");
            int puerto = Puertos.PUERTO_DEL_SERVIDOR;
            // La IP es la local, el puerto es en el que el servidor este 
            // escuchando.
            DatagramSocket socket = new DatagramSocket(puerto, InetAddress.getByName("0.0.0.0"));

            // Un DatagramPacket para recibir los mensajes.
            DatagramPacket paquete = new DatagramPacket(new byte[100], 100);

            Node nodoInicial;
            Node nuevoNodo;
            String inicio = "";
            boolean primero = true;  
            // Bucle infinito.
            while (true){
                // Se recibe un paquete y se escribe en pantalla.
                socket.receive(paquete);
                System.out.print("Recibido paquete de "+ paquete.getAddress().getHostName() +" en el puerto "+puerto+ " : ");
                
                // Conversion de los bytes a DatoUdp
                Dato datoRecibido = Dato.fromByteArray(paquete.getData());

                String idNodo = datoRecibido.getContenido();
                System.out.println(idNodo);
                //Obtenemos el nodo que inicia la gráfica.
                if(primero){
                    nodoInicial = red.addNode(idNodo);//agregamos el primer nodo a la gráfica
                    nodoInicial.addAttribute("ui.label", nodoInicial.getId());//le asignamos su etiqueta
                    nodoInicial.addAttribute("ui.class", "principal");//le asignamos una clase para distinguirlo de los demás nodos
                    primero = false;
                    inicio = nodoInicial.getId();//obtenemos el id del primer nodo. 

                }else if(red.getNode(idNodo) == null){//agregamos nodos a la gráfica si su id no se encuentra en la gráfica.

                    //asignamos a todos los nodos que no sean el nodo inicial una clase para pintarlos de igual manera
                    for (Node n : red) {
                        if(n.getId() != inicio)
                            n.removeAttribute("ui.class");
                    }   

                    //agregamos un nuevo nodo con el identificador que se envió como mensaje del cliente.
                    nuevoNodo = red.addNode(idNodo);
                    nuevoNodo.addAttribute("ui.label", nuevoNodo.getId());//le asginamos su etiqueta
                    nuevoNodo.addAttribute("ui.class", "ultimo");//le asignamos una clase para diferenciar el último nodo agregado de los demás.
                    
                    String aristaIda = inicio + nuevoNodo.getId();
                    String aristaVuelta = nuevoNodo.getId() + inicio;

                    //verificamos que la arista que se quiere agregar no exista en la gráfica.
                    if(red.getEdge(aristaIda) == null || red.getEdge(aristaVuelta) == null){
                        red.addEdge(aristaIda, inicio, nuevoNodo.getId());//agregamos la nueva arista, entre el nodo inicial y el último que se agregó.
                    }
                    
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
