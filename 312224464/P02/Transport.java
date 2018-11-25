public class Transport{

    private static Transport transport;

    public static Transport getInstance(){
	if(transport == null){
	    transport = new Transport();
	}
	return transport;
    }

    public boolean put(Message m, String destination){
	sleep(200);
	return false;
    }

    public Message pop(String n){
	sleep(300);
	return null;
    }

    private void sleep(int ms){
	try{
	    Thread.sleep(ms);
	}catch(Exception ex){
	}	   
    }
}
