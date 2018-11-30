import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.io.IOException;

public class Receptor extends CDNode{

	/* BufferedWriter para escribir en el archivo destino */
	private BufferedWriter writer;

	/* Estado del receptor */
    private boolean available;

    /* La ventana que vamos a estar moviendo */
    private Window window;

    /* El transmisor de el cual vamos a recibir mensajes */
    private Transmisor transmisor;

    /* Constructor de la clase */
    public Receptor(Path path) throws IOException{
        Charset charset = Charset.forName("UTF8");
        writer = Files.newBufferedWriter(path, charset);
        available = true;
        window = null;
    }

    /**
	 * Metodo que nos ayuda a definir quien es nuestro transmisor.
	 *@return transmisor el transmisor.
     */
    public void setTransmisor(Transmisor transmisor){
        this.transmisor = transmisor;
    }

    /**
     * Metodo que regresa si el receptor esta disponible.
     *@return available estado del receptor.
     */
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

    /**
     * Metodo que recibe una ventana, la define como la ventana que se va a procesar
     * y pone al receptor en estado ocupado.
     *@return status estado de validez del receptor.
     */
    public synchronized boolean put(Window w){
        boolean status = false;
        if(available){
            window = w;
            status = true;
            available = false;
        }
        return status;
    }

    /**
     * Metodo que verifica si todos los segmentos de la ventana son validos.
     *@return status el status de la ventana.
     */
    public synchronized boolean checkWindow(Window w){
        boolean status = true;
        if(w!=null){
            for(Segment i: w.getSegments()){
                if(i!= null && !i.esValido()){
                    status = false;
                }
            }
        }else{
            status = false;
        }
        return status;
    }
}
