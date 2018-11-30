import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.io.IOException;

public class Transmisor extends CDNode{

	/* BufferedReader para leer el archivo de entrada */
	private BufferedReader reader;

	/* El Receptor */
    private Receptor receptor;

    /* La ventana con la que se trabajara */
    private Window actual;

    /* Confirmacion de la validacion */
    private boolean ack;

    /* Constructor de la clase */
    public Transmisor(Path path) throws IOException{
        Charset charset = Charset.forName("UTF8");
        reader = Files.newBufferedReader(path, charset);
        this.ack = false;
    }

    /** 
     * Metodo que define el receptor el cual va a "recibir" de este transmisor.
     *@param receptor el receptor.
     */
    public void setReceptor(Receptor receptor){
        this.receptor = receptor;
    }

    /**
     * Metodo que define la confirmacion ack.
     *@param ack validacion.
     */
    public synchronized void setAck(boolean ack){
        this.ack = ack;
        try{
            Thread.sleep(50);
        }catch(Exception ex){

        }
    }

    /**
    * En cada iteración debe generar una nueva ventana que enviara al nodo Receptor
    * Debe consultar si es posible enviar una nueva ventana y esperar el ACK para
    * a generar cada nueva ventana
    * El contenido de la ventanas debe ser leido de un archivo de texto
    */
    public void run(){
        while(true){
            try{
                if(receptor.isAvailable()){
                    if(actual == null || ack){
                        send(null);
                    }else if(actual != null && !ack){
                        send(actual);
                    }
                }else{
                    continue;
                }
                Thread.sleep(100);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metodo que envia la ventana al receptor.
     *@param w la ventana que vamos a enviar.
     */
    private synchronized void send(Window w) throws IOException{
        Window window = w;
        if(w == null){
             window = new Window();
            int index = 0;
            int line = -1;
            while(index < CDNode.WINDOW_SIZE && (line = reader.read()) > -1 ){
                StringBuilder response= new StringBuilder();
                response.append((char)line);
                window.añadeContenido(response.toString());;
                index++;
            }
            this.actual = window;
        }else{
            window.reintenta();
        }
        if(window != null && window.getSize() > 0){
            receptor.put(window);
        }
    }
}
