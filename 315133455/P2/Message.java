/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message{

	private String destination;
	private String source;
	private int lifeTime;
	private String mensaje;


    public Message(String source, String destination, String mensaje){
    	this.source = source;
    	this.destination=destination;
    	lifeTime=100;
    	this.mensaje=mensaje;
    }

    //Getters

    public String getSource(){
    	return source;
    }

    public String getDestination(){
    	return destination;
    }

    public int getLifeTime(){
    	return lifeTime;
    }

    public String getMensaje(){
    	return mensaje;
    }

	//Setters
	
	public void setSource(String source){
		this.source=source;
	}

	public void setDestination(String destination){
		this.destination=destination;
	}

	public void setLifeTime(int newLt){
		lifeTime=newLt;
	}

	public void setMensaje(String m){
		mensaje=m;
	}
}
