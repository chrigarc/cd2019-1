import java.nio.file.Path;
import java.nio.file.FileSystems;
 public class Main{
  public static void main(String[] args){
    try{
            Path path_origen = FileSystems.getDefault().getPath(".", "source.txt");
            Path path_destino = FileSystems.getDefault().getPath(".", "destino.txt");
            Transmisor t = new Transmisor(path_origen);
            Receptor r = new Receptor(path_destino);
            t.setReceptor(r);
            r.setTransmisor(t);
            Thread th1 = new Thread(t);
            Thread th2 = new Thread(r);
            th1.start();
            th2.start();
        }catch(Exception ex){
            ex.printStackTrace();
        }
  }
} 