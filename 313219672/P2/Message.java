/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message{

	private String source;
	private String destination;

    public Message(String source,String destination){
    	this.source = source;
    	this.destination = destination;
    }
    
    public String getSource(){
        return source;
	}
	public String getDestination(){
        return destination;
	}

	public void setDestination(String destination){
        this.destination = destination;
	}

	 public String toString(){
        return "{source: "+ source+", destination: "+destination+ "}";
	}

	public Message clone(){
        Message m  = new Message(source, destination);
        return m;
}
}
