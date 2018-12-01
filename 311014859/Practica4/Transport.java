import java.util.HashMap;
import java.util.LinkedList;
public class Transport{
/*
*
*Codigo escrito por Luis Pulido Alvarez
*
*/
    public final static int LIMIT_CAPACITY = 500;

    private static Transport transportar;
    private HashMap<String, LinkedList<Message>> hash;
    private int tamanio;

    public static Transport getInstance(){
        if(transportar == null){
            transportar = new Transport();
        }
        return transportar;
    }

    private Transport(){
        hash = new HashMap<String, LinkedList<Message>>();
        tamanio = 0;
    }

    public synchronized Message pop(String llave){
        Message mensaje = null;
        if(hash.containsKey(llave)){
            LinkedList<Message> list = hash.get(llave);
            if(!list.isEmpty()){
                mensaje = list.pop();
                tamanio--;
                sleep(30);
            }
        }
        return mensaje;
    }

    public synchronized boolean put(Message m){
        boolean estado = false;
        if(!hash.containsKey(m.getDestination())){
            hash.put(m.getDestination(), new LinkedList<Message>());
        }
        if(tamanio < LIMIT_CAPACITY){
            hash.get(m.getDestination()).add(m);
            tamanio++;
            estado = true;
            sleep(10);
        }
        return estado;
    }

    private void sleep(int mensaje){
        try{
            Thread.sleep(mensaje);
        }catch(Exception ex){
        }
    }
}

