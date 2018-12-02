import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.ArrayList;


public class Receptor extends CDNode{

	private String archivo;
	private ArrayList<String> textoCompleto;

	public Receptor(String nuevoArchivo){
		this.archivo = nuevoArchivo;
		this.textoCompleto = new ArrayList<>();
	}

  /**
  *  Esta a la espera de ventanas provenientes de receptor, cuando llega nueva
  *  Debe revisar su contenido y guardarlo en un archivo de texto
  *  Cuando este procesando la ventana debe indicar que no puede recibir mensajes nuevos.
  *  En caso de que se presente un error en la ventana debe indicarle a Transmisor para que
  *  reenvie el contenido en una nueva ventana
  **/

  public void run(){
        try{
        	System.out.println(">>>>>>>>>>> Iniciado");

            DatagramSocket socketRecibo = new DatagramSocket(5557, InetAddress.getByName("localhost"));
            DatagramSocket socketEnvio = new DatagramSocket(25000, InetAddress.getByName("localhost"));
            DatagramPacket paqueteRecibido = new DatagramPacket(new byte[1024], 1024);

            while (true){
            	System.out.println("Esperando ventana ...");
                // Se recibe un paqueteRecibido y se escribe en pantalla.
                socketRecibo.receive(paqueteRecibido);

                Window ventanaRecibida = Window.fromByteArray(paqueteRecibido.getData());//Recremaos la ventana recibida.
                System.out.println("Ventana recibida \""+ ventanaRecibida.toString()); //mostramos la ventana recibida.
                
                //Se envía el aviso de que se está procesando una ventana.
                String procesando = "bloqueado";
            	DatagramPacket paqueteEnvio = new DatagramPacket(procesando.getBytes(),procesando.getBytes().length, InetAddress.getByName("localhost"), 25001);//"255.255.255.255"),25001);
                socketEnvio.send(paqueteEnvio);

                //Se incia el procesamiento de la ventana
                System.out.println("Procesando la ventana ...");
                boolean correcto = true; 

                //Se revisa que todos los segementos del paqueteRecibido esten correctos y no contengan fallas.
                for (Segment s : ventanaRecibida.getSegmentos()) {

                	if(s != null){
	                	//Si encontramos segmento con error, pedimos el reenvio.
	                	if(s.getStatus() == false){
	                		//Se envía el mensaje de solicitud de reenvio.
	                		System.out.println("Fallo en algun segmento. Solicitando reeenvio");
							String confirmacion = "reenviar";
	            			paqueteEnvio = new DatagramPacket(confirmacion.getBytes(),confirmacion.getBytes().length, InetAddress.getByName("localhost"), 25001);//"255.255.255.255"),25001);
	                		socketEnvio.send(paqueteEnvio);
	                		correcto = false;
	                		break;
	                	}
                	}
                }
                //si todos los paquetes estaban correctos los guardamos.
                if(correcto){
                	//Guardamos contenido de la ventana recibida en el archivo 
                	guardarContenido(ventanaRecibida);	

                	//Se envía el ACK que avisa que se puede continuar recibiendo ventanas.
					String confirmacionTrue = "esperando";
					paqueteEnvio = new DatagramPacket(confirmacionTrue.getBytes(),confirmacionTrue.getBytes().length, InetAddress.getByName("localhost"), 25001);//"255.255.255.255"),25001);
		    		socketEnvio.send(paqueteEnvio);
		    		System.out.println("Enviando ACK "+ ventanaRecibida.toString()+"\n");
		    		correcto = true; 
                }

                Thread.sleep(1000);
			}

        } catch (Exception e){
            e.printStackTrace();
        }

  }

  /**
  * Función que dada un ventana de parametro guarda el contenido de los segmentos
  * en un archivo 
  */

  public void guardarContenido(Window ventana){
  		try {

			PrintWriter bw = new PrintWriter(new FileWriter(archivo, true));
			String contenido = "";
  			for (Segment s : ventana.getSegmentos()) {
  				if(s!= null){

  					contenido = s.getContenido()+" ";
					bw.printf("%s\r\n", contenido);//guardamos el contenido del segemento en el archivo.
  				}
  			}
			bw.close();
			System.out.println("Ventana guardada");
		} catch (IOException e) {
			e.printStackTrace();
		}
  }

  public static void main(String[] args) {
  	Thread t1 = new Thread(new Receptor("archivoRecibido.txt"));//modificar el nombre del archivo donde se guardarán las ventanas recibidas.
	t1.start();
  }
}
