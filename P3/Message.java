import java.io.Serializable;
import java.util.LinkedList;

/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message{

    public final static int TTL_DEFAULT = 100;

    private int ttl;
    private String source;
    private String destination;
    private Serializable content;
    private LinkedList<String> recorrido;

    public Message(String source, String destination, Serializable content){
        this.source = source;
        this.destination = destination;
        this.content = content;
        this.ttl = TTL_DEFAULT;
        this.recorrido = new LinkedList<String>();
        this.recorrido.add(source);
        this.recorrido.add(destination);
    }

    public int getTTL(){
        return ttl;
    }

    public String getSource(){
        return source;
    }

    public String getDestination(){
        return destination;
    }

    public Serializable getContent(){
        return content;
    }

    public LinkedList<String> getRecorrido(){
        return this.recorrido;
    }

    public void setTTL(int ttl){
        this.ttl = ttl;
    }

    public void setDestination(String destination){
        this.destination = destination;
        this.recorrido.add(destination);
    }

    public void setSource(String source){
        this.source = source;
    }

    public String toString(){
        return "{source: "+ source+", destino: "+destination+", ttl: "+ttl+"}";
    }

    public Message clone(){
        Message m  = new Message(source, destination, content);
        m.recorrido = (LinkedList<String>)this.recorrido.clone();
        m.ttl = this.ttl;
        return m;
    }

    public boolean equals(Object o){
        if(o instanceof Message){
            Message tmp = (Message)o;
            return this.recorrido.toString().equals(tmp.recorrido.toString());
        }
        return false;
    }
}