import java.util.HashMap;
import java.util.LinkedList;

public class Transport{

    private static Transport transport;
    private HashMap<String,LinkedList<Message>> buzones;
    
    //Método para crear una instancia 
    public static Transport getInstance(){
	if(transport == null){
	    transport = new Transport();
	}
	return transport;
    }

    //Constructor de la clase
    private Transport(){
    	buzones = new HashMap<String,LinkedList<Message>>();
    }			

    public boolean put(Message m, String destination){
    	boolean realizado = false;
    	if (buzones.containsKey(destination)){
    		buzones.get(destination).add(m);
    	}else{
    		buzones.put(destination,new LinkedList<Message>());
    		buzones.get(destination).add(m);;
    	}
    	realizado=true;
		sleep(200);
		return realizado;
    }

    public Message pop(String n){
    	Message m = null;
		if(buzones.containsKey(n)){
			if(!buzones.get(n).isEmpty()){
				m = buzones.get(n).pop();
			}
		}
		sleep(300);
		return m;
    }

    private void sleep(int ms){
	try{
	    Thread.sleep(ms);
	}catch(Exception ex){
	}	   
    }
}
