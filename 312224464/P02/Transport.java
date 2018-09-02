import java.util.HashMap;
import java.util.LinkedList;

public class Transport{

    public final static int CAPACIDAD = 300;

    private static Transport transport;
    private HashMap<String, LinkedList<Message>> map;
    private int size;

    /**
     * Constructor de Transport
     */
    private Transport(){
        map = new HashMap<String, LinkedList<Message>>();
        size = 0;
    }

    public static Transport getInstance(){
        if(transport == null){
            transport = new Transport();
        }
        return transport;
    }

    /**
     * Coloca un mensaje enviado por un nodo en
     * su respectiva lista de mensajes enviados.
     *
     * @return <code>true</code> cuando se guardó el mensaje
     *                          de manera satisfactoria.
     *         <code>false</code> en otro caso.
     */
    public synchronized boolean put(Message m){
        boolean status = false;

        if(!map.containsKey(m.getDestino())){
            map.put(m.getDestino(), new LinkedList<Message>());
        }
        
        if(size < CAPACIDAD){
            map.get(m.getDestino()).add(m);
            size++;
            status = true;
            sleep(10);
        }
        
        return status;
    }

    /**
     * Extrae un mensaje de los mensajes enviados por un nodo.
     */
    public synchronized Message pop(String key){
        Message m = null;

        if(map.containsKey(key)){
            LinkedList<Message> list = map.get(key);
        
            if(!list.isEmpty()){
                m = list.pop();
                size--;
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
}
