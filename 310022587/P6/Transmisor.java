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