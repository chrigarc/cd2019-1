/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message{

	private String idDestino;
	private String idOrigen;
	private int vida;
    public Message(String idDestino, String idOrigen){
    		this.idDestino=idDestino;
    		this.idOrigen=idOrigen;
    		vida=100;
    }
    
}