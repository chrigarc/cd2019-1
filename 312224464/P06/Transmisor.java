import java.io.*;
import java.util.ArrayList;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Transmisor extends CDNode{
	private ArrayList<String> textoCompleto;

	public Transmisor(String archivo){
  		this.textoCompleto = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
	    	StringBuilder sb = new StringBuilder();
	    	String line = br.readLine();
	    	while(line != null){
	    		textoCompleto.add(line);
	    		line = br.readLine();
	    	}
	    	br.close();
		} catch (FileNotFoundException e) {
	    	System.out.println("El archivo "+archivo+" no existe");
		} catch (IOException e){
			System.out.println("El archivo "+archivo+" no existe");
		}
	    System.out.println(textoCompleto.toString());		
	}

  /**
  * En cada iteración debe generar una nueva ventana que enviara al nodo Receptor
  * Debe consultar si es posible enviar una nueva ventana y esperar el ACK para
  * a generar cada nueva ventana
  * El contenido de la ventanas debe ser leido de un archivo de texto
  */
  public void run(){
		try{
			System.out.println(">>>>>>>>>>> Iniciado");

            DatagramSocket socketEnvio = new DatagramSocket(5558, InetAddress.getByName("localhost"));
            DatagramSocket socketRecibo = new DatagramSocket(25001, InetAddress.getByName("localhost"));
            DatagramPacket paqueteRecibido = new DatagramPacket(new byte[10], 10);
            //Creamos un ciclo infinito.
			while(true){

	            Window ventana = createWindow();//Creamos una ventana en cada iteración. 

	            //Si ya no hay ventana para enviar terminamos de enviar.
	            if(ventana == null){
	            	System.out.println("Texto traspasado");
	            	break;
	            }

	            //Preparamos la ventana para enviar al receptor.
	            byte[] bytesDato = ventana.toByteArray();
                DatagramPacket paqueteEnviado = new DatagramPacket(bytesDato,bytesDato.length, InetAddress.getByName("localhost"), 5557);//"255.255.255.255"),25000);
                socketEnvio.send(paqueteEnviado);//enviamos el paqueteEnviado

                System.out.println("Ventana enviada \""+ ventana.toString());
               	System.out.print("Esperando respuesta ...");

               	//Recibimos la señal para saber si podemos continuar enviado ventanas.
				socketRecibo.receive(paqueteRecibido);
                String datoRecibido = new String(paqueteRecibido.getData());
                System.out.println(datoRecibido);
				String bloqueado = "bloqueado";

                int senial = bloqueado.compareTo(datoRecibido);
                if(senial == -1){ //verificamos si debemos esperar.
                	Thread.sleep(1000);

                	//Se espera la confirmación para reenviar o para enviar una nueva venta
                	System.out.println("Esperando confirmacion");
                	socketRecibo.receive(paqueteRecibido);
					
					String confirmacion = new String(paqueteRecibido.getData());
					String confirmacion2 = "reenviar";
					senial = confirmacion2.compareTo(confirmacion);
					//Si la señal es para poder continuar enviando
                	if(senial == 13){
                		//Generamos una nueva ventana y avisamos se recibio el ACK de la última ventana enviada.
                		System.out.println("Recibiendo ACK "+  datoRecibido.toString());
                		for (int i = 0; i < CDNode.WINDOW_SIZE && this.textoCompleto.size() != 0; i++) {
                			this.textoCompleto.remove(0);//Quitamos los elementos que ya hemos enviado para enviar los siguientes.
                		}

                	//Si la señal no es de poder continuar enviado es porque se requiere un reeenvio.
                	}else{
                		System.out.println("Reeniando ventana "+  datoRecibido.toString());	
                	}
                }

			}//fin del ciclo infinito.
        } catch (Exception e){
            e.printStackTrace();
        }
  }


  /**
  * Función que crea una nueva venta según el tamaño predefinido.
  * y el contenido del texto cargado en una lista.
  */

  public Window createWindow(){
  	Window ventana = new Window(CDNode.WINDOW_SIZE);
	String line ="";
	Segment segmento; 
	for (int i = 0; i < CDNode.WINDOW_SIZE; i++){
		if(i < this.textoCompleto.size() ){

			line = this.textoCompleto.get(i);
			if(line.trim() != ""){
			segmento = new Segment(line);
			ventana.agregaSegmento(i, segmento);
			}
		}
	}
	if(this.textoCompleto.size() == 0){
		return null;
	}
	System.out.println(ventana.toString());
    return ventana;
  }


  public static void main(String[] args) {
  	Thread t1 = new Thread(new Transmisor("archivoEnviado.txt"));//modificar está línea para cambiar de archivo a enviar.
	t1.start();
  }

}
