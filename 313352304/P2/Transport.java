public class Transport{

    private static Transport transport;

    public static Transport getInstance(){
	if(transport == null){
	    transport = new Transport();
	}
	return transport;
    }

    public boolean put(Message m, String destination){
	
    	if(m.getMensaje().equals(destination))
    		return true;
    	return false;
    }

    public Message pop(String n){
	Message m= new Message(n);
	return m;
    }

    private void sleep(int ms){
	try{
	    Thread.sleep(ms);
	}catch(Exception ex){
	}	   
    }
}
