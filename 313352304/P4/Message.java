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
<<<<<<< HEAD
=======
        this.recorrido.add(source);
        this.recorrido.add(destination);
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
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
        return this.recorrido;
}
=======
     public void setRecorrido(LinkedList<String> recorrido){
        this.recorrido = recorrido;
    }

    public LinkedList<String> getRecorrido(){
        return this.recorrido;
    }

>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
    public void setTTL(int ttl){
        this.ttl = ttl;
    }

    public void setDestination(String destination){
        this.destination = destination;
<<<<<<< HEAD
=======
        this.recorrido.add(destination);
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
    }

    public void setSource(String source){
        this.source = source;
    }

    public String toString(){
<<<<<<< HEAD
        return "{source: "+ source+", destination: "+destination+", ttl: "+ttl+"}";
=======
        return "{source: "+ source+", destino: "+destination+", ttl: "+ttl+"}";
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
    }

    public Message clone(){
        Message m  = new Message(source, destination, content);
<<<<<<< HEAD
        m.ttl = this.ttl;
        return m;
    }
}
=======
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
>>>>>>> 34fab885680352eba3d64b7f11e9c78bb0eec1c9
