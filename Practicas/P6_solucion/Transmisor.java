import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.io.IOException;


public class Transmisor extends CDNode{

    private BufferedReader reader;
    private Receptor receptor;
    private Window current;
    private boolean ack;

    public Transmisor(Path path) throws IOException{
        Charset charset = Charset.forName("UTF8");
        reader = Files.newBufferedReader(path, charset);
        this.ack = false;
    }

    public void setReceptor(Receptor receptor){
        this.receptor = receptor;
    }

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
                    if(current == null || ack){
                        send(null);
                    }else if(current != null && !ack){
                        send(current);
                    }
                }else{
                    //System.out.println("[Transmisor]: Receptor ocupado esperando...");
                }
                Thread.sleep(100);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private synchronized void send(Window w) throws IOException{
        Window window = w;
        if(w == null){
             window = new Window();
            int index = 0;
            int line = -1;
            while(index < CDNode.WINDOW_SIZE && (line = reader.read()) > -1 ){
                StringBuilder response= new StringBuilder();
                response.append((char)line);
                window.addContent(response.toString());;
                index++;
            }
            this.current = window;
        }else{
            window.retry();
        }
        if(window != null && window.getSize() > 0){
            receptor.put(window);
        }
    }

}
