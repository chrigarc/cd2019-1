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
        Message m = null;
	   if(map.containsKey(n)){
            LinkedList<Message> list = map.get(n);
            if(!list.isEmpty()){
                m = list.pop();
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
