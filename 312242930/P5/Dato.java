import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Dato implements Serializable{
    /**
     * serial uid
     */
    private static final long serialVersionUID = 3258698714674442547L;
    private String contenido;

    public Dato (String contenido){
        this.contenido = contenido;
    }
    
    public String getContenido(){
        return this.contenido;
    }

    public void setContenido(String contenido){
        this.contenido = contenido;
    }

    /**
     * Se autoconvierte esta clase a array de bytes.
     * @return La clase convertida a array de bytes.
     */
    public byte [] toByteArray(){
        try{
             // Se hace la conversion usando un ByteArrayOutputStream y un
             // ObjetOutputStream.
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream (bytes);
            os.writeObject(this);
            os.close();
            return bytes.toByteArray();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Se convierte el array de bytes que recibe en un objeto Dato.
     * @param bytes El array de bytes
     * @return Un Dato.
     */
    public static Dato fromByteArray (byte [] bytes){
        try{
            // Se realiza la conversion usando un ByteArrayInputStream y un
            // ObjectInputStream
            ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
            ObjectInputStream is = new ObjectInputStream(byteArray);
            Dato dato = (Dato)is.readObject();
            is.close();
            return dato;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
