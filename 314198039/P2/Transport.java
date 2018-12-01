/**
 * Clase que nos ayuda a transportar el mensaje desde un nodo a otro
 */
import java.util.HashMap;
import java.util.LinkedList;

public class Transport{

    private static Transport transport;
    private HashMap<String, LinkedList<Message>> map;
    private int size;

    public static Transport getInstance(){
        if(transport == null){
            transport = new Transport();
        }
        return transport;
    }

    
    private Transport(){
        map = new HashMap<String, LinkedList<Message>>();
    }

    public boolean put(Message msg, String destino){
        boolean status = false;
	   if(!map.containsKey(destino)){
            map.put(destino, new LinkedList<Message>());
         }

         map.get(destino).add(msg);
         status = true;
        
            
        return status;
    }

    public Message pop(String n){
        Message msg = null;
	   if(map.containsKey(n)){
            LinkedList<Message> list = map.get(n);
            if(!list.isEmpty()){
                msg = list.pop();
                }
        }
        return msg;
    }

    private void sleep(int ms){
	try{
	    Thread.sleep(ms);
	}catch(Exception e){
	}	   
    }
}
