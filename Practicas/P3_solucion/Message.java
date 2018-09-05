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
<<<<<<< HEAD
    private LinkedList<String> recorrido;
=======
    private Object recorrido;
>>>>>>> 03864185f9105eb900e2e392da90486a497f4ac2

    public Message(String source, String destination, Serializable content){
        this.source = source;
        this.destination = destination;
        this.content = content;
        this.ttl = TTL_DEFAULT;
        this.recorrido = new LinkedList<String>();
<<<<<<< HEAD
        this.recorrido.add(source);
        this.recorrido.add(destination);
=======
>>>>>>> 03864185f9105eb900e2e392da90486a497f4ac2
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

<<<<<<< HEAD
    public LinkedList<String> getRecorrido(){
=======
    public Object getRecorrido(){
>>>>>>> 03864185f9105eb900e2e392da90486a497f4ac2
        return this.recorrido;
    }

    public void setTTL(int ttl){
        this.ttl = ttl;
    }

    public void setDestination(String destination){
<<<<<<< HEAD
        this.recorrido.add(destination);
=======
>>>>>>> 03864185f9105eb900e2e392da90486a497f4ac2
        this.destination = destination;
    }

    public void setSource(String source){
        this.source = source;
    }

    public String toString(){
        return "{source: "+ source+", destination: "+destination+", ttl: "+ttl+"}";
    }

    public Message clone(){
        Message m  = new Message(source, destination, content);
<<<<<<< HEAD
        m.recorrido = (LinkedList<String>)this.recorrido.clone();
=======
>>>>>>> 03864185f9105eb900e2e392da90486a497f4ac2
        m.ttl = this.ttl;
        return m;
    }
}
