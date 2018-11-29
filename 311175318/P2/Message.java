import java.io.Serializable;
import java.util.LinkedList;
/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message{

    public final static int VIDA = 100;

    private int tiempo;
    private String source;
    private String destination;

    public Message(String source, String destination){
        this.source = source;
        this.destination = destination;
        this.tiempo = VIDA;
    }

    public Message(String source){
    	this.source = source;
    	this.tiempo = VIDA;
    }

    public int getTiempo(){
        return tiempo;
    }

    public String getSource(){
        return source;
    }

    public String getDestination(){
        return destination;
    }

    public void setTiempo(int tiempo){
        this.tiempo = tiempo;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    public void setSource(String source){
        this.source = source;
    }

    public String toString(){
        return "{source: "+ source+", destination: "+destination+", Tiempo de Vida: "+tiempo+"}";
    }
    
}
