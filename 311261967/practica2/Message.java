/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message{

    private String source;
    private String destination;
    private int tiempoVida;

    public Message(String source, String destination){
	this.source = source;
	this.destination = destination;
	this.tiempoVida = 100;
    }

    public String getSource(){
	return source;
    }

    public String getDestination(){
	return destination;
    }

    public int getTiempo(){
	return tiempoVida;
    }

    public void setTiempo(int tiempo){
	this.tiempoVida = tiempo;
    }

    public void setDestination(String newDestination){
	this.destination = newDestination;
    }

    public String toString(){
	return "fuente: " + source + "-> destino: " + destination;
    }

    public Message clone(){
	Message mensaje = new Message(source, destination);
	return mensaje;
    }
}
