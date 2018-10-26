import java.nio.file.Path;
import java.nio.file.FileSystems;
public class Main{

    public static void main(String[] pps){
        try{
            Path path_origen = FileSystems.getDefault().getPath(".", "source.txt");
            Path path_destino = FileSystems.getDefault().getPath(".", "destino.txt");
            Transmisor t = new Transmisor(path_origen);
            Receptor r = new Receptor(path_destino);
            t.setReceptor(r);
            r.setTransmisor(t);
            (new Thread(t)).start();
            (new Thread(r)).start();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
