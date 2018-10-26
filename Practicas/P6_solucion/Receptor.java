import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.io.IOException;

public class Receptor extends CDNode{

    private BufferedWriter writer;
    private boolean available;
    private Window window;
    private Transmisor transmisor;

    public Receptor(Path path) throws IOException{
        Charset charset = Charset.forName("UTF8");
        writer = Files.newBufferedWriter(path, charset);
        available = true;
        window = null;
    }

    public void setTransmisor(Transmisor transmisor){
        this.transmisor = transmisor;
    }

    public boolean isAvailable(){
        return available;
    }

    /**
    *  Esta a la espera de ventanas provenientes de receptor, cuando llega nueva
    *  Debe revisar su contenido y guardarlo en un archivo de texto
    *  Cuando este procesando la ventana debe indicar que no puede recibir mensajes nuevos.
    *  En caso de que se presente un error en la ventana debe indicarle a Transmisor para que
    *  reenvie el contenido en una nueva ventana
    **/
    public void run(){
        while(true){
            try{
                if(window != null){
                    if(checkWindow(window)){
                        System.out.print("[RECEPTOR]: ");
                        for(Segment i: window.getSegments()){
                            if(i!= null){
                                writer.write(i.getContent(), 0, i.getContent().length());
                                System.out.print(i.getContent() + " ");
                                Thread.sleep(100);
                            }
                        }
                        System.out.println();                
                        transmisor.setAck(true);
                    }else{
                        transmisor.setAck(false);
                        System.out.println("[RECEPTOR]: VENTANA CON ERRORES");
                    }
                    available = true;
                    window = null;
                }
                Thread.sleep(100);
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
    }

    public synchronized boolean put(Window w){
        boolean status = false;
        if(available){
            window = w;
            status = true;
            available = false;
        }
        return status;
    }

    public synchronized boolean checkWindow(Window w){
        boolean status = true;
        if(w!=null){
            for(Segment i: w.getSegments()){
                if(i!= null && !i.isValid()){
                    status = false;
                }
            }
        }else{
            status = false;
        }
        return status;
    }
}
