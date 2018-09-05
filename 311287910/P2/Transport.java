import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transport{

    private static Transport transport;
    private static HashMap<String, List<Message>> messages;

    public static Transport getInstance(){
	if(transport == null){
	    transport = new Transport();
	}
        messages = new HashMap<>();
	return transport;
    }

    public boolean put(Message m, String destination){
        boolean agregado = false;
        if(messages.containsKey(destination)){
            if(m.estaVivo()){
                agregado = messages.get(destination).add(m);
            }
        }else{
            List<Message> list = new ArrayList<>();
            if(m.estaVivo()){
                agregado = list.add(m);
            }
            messages.put(destination, list);
        }
	sleep(200);
	return agregado;
    }

    public Message pop(String n){
        Message m = null;
        if(messages.containsKey(n)){
            List<Message> list = messages.get(n);
            if(!list.isEmpty()){
                m = list.remove(list.size() - 1);
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