/*
Clase que nos ayuda a simular el comportamiento del mensaje en nuestro sistema
*/
public class Message{

	private String origen;
	private String destino;

    public Message(String origen,String destino){
    	this.origen = origen;
    	this.destino = destino;
    }
    
    public String getorigen(){
        return origen;
	}
	public String getDestino(){
        return destino;
	}

	public void setDestino(String destino){
        this.destino = destino;
	}

	 public String toString(){
        return "{origen: "+ origen+", destino: "+destino+ "}";
	}

	public Message clone(){
        Message msg  = new Message(source, destination);
        return msg;
}
}
