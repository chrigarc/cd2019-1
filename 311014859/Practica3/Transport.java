import java.util.HashMap;
import java.util.LinkedList;
/*
* 
* Codigo hecho por Luis Pulido Alvarez
*
*
*/

public class Transport{

    public final static int LIMIT_CAPACITY = 500;

    private static Transport transportar;
    private HashMap<String, LinkedList<Message>> hash;
    private int tamanio;

    
    private Transport(){
        hash = new HashMap<String, LinkedList<Message>>();
        tamanio = 0;
    }

    public synchronized Message pop(String key){
        Message m = null;
        if(hash.containsKey(key)){
            LinkedList<Message> list = hash.get(key);
            if(!list.isEmpty()){
                m = list.pop();
                tamanio--;
                sleep(30);
            }
        }
        return m;
    }

    private void sleep(int ms){
        try{
            Thread.sleep(ms);
        }catch(Exception ex){
        }
    }
    public static Transport getInstance(){
        if(transportar == null){
            transportar = new Transport();
        }
        return transportar;
    }

    public synchronized boolean put(Message m){
        boolean disponibilidad = false;
        if(!hash.containsKey(m.getDestination())){
            hash.put(m.getDestination(), new LinkedList<Message>());
        }
        if(tamanio < LIMIT_CAPACITY){
            hash.get(m.getDestination()).add(m);
            tamanio++;
            disponibilidad = true;
            sleep(10);
        }
        return disponibilidad;
    }

}
