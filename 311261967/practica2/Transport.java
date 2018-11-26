import java.util.*;

public class Transport{

    private static Transport transport;
    private HashMap<String, LinkedList<Message>> map;

    public static Transport getInstance(){
	if(transport == null){
	    transport = new Transport();
	}
	return transport;
    }

    private Transport(){
        map = new HashMap<String, LinkedList<Message>>();
    }

    
    public boolean put(Message m, String destination){
	boolean status = false;
	   if(!map.containsKey(destination)){
            map.put(destination, new LinkedList<Message>());
	}
	   map.get(destination).add(m);
	   status = true;
	   return status;
    }

    public Message pop(String n){
	Message mensaje = null;
	if(map.containsKey(n)){
            LinkedList<Message> lista = map.get(n);
            if(!lista.isEmpty()){
                mensaje = lista.pop();
	    }
        }
        return mensaje;
    }

    private void sleep(int ms){
	try{
	    Thread.sleep(ms);
	}catch(Exception ex){
	}
    }	   
}

