/**
 * La clase es una estructura de datos con un tiempo de vida 100,
 * que conoce el id del destino y el origen
 */
public class Message{

	public final static int VIDA = 10;
	private String origen;
	private String destino;
	private int tiempo_vida;

	/**
	 * Constructor de un Mensaje
	 * @param origen el origen del mensaje.
	 * @param destino el destino del mensaje.
	 */
    public Message(String origen, String destino){
    	this.origen = origen;
    	this.destino = destino;
    	this.tiempo_vida = VIDA;
    }

    /**
     * Regresa el id del nodo que envío el mensaje.
     * @return el id del nodo que envío el mensaje.
     */
    public String getOrigen(){
    	return this.origen;
    }

    /**
     * Regresa el id del nodo que recibe el mensaje.
     * @return el id del nodo que recibe el mensaje.
     */
    public String getDestino(){
    	return this.destino;
    }

    /**
     * Regresa el tiempo de vida del mensaje.
     * @return el tiempo de vida del mensaje.
     */
    public int getTiempoVida(){
    	return this.tiempo_vida;
    }

    /**
     * Modifica el id del nodo que envío el mensaje.
     * @param origen el nuevo id del nodo que envío el mensaje.
     */
    public void setOrigen(String origen){
    	this.origen = origen;
    }

    /**
     * Modifica el id del nodo que recibe el mensaje.
     * @param destino el nuevo id del nodo que recibe el mensaje.
     */
    public void setDestino(String destino){
    	this.destino = destino;
    }

    /**
     * Modifica el tiempo de vida del mensaje.
     * @param tiempo_vida el nuevo tiempo de vida del mensaje.
     */
    public void setTiempoVida(int tiempo_vida){
    	this.tiempo_vida = tiempo_vida;
    }

    /**
     * Representación en cadena de la información del mensaje.
     */
    public String toString(){
    	String s = "Origen: " + this.origen + ". Destino: " + this.destino;
    	s+=". Tiempo de Vida: " + this.tiempo_vida + ".";
    	return s;
    }

    /**
     * Crea una copia del mensaje,
     * @return el la copia del mensaje
     */
    public Message clonar(){
        Message m = new Message(this.origen, this.destino);
        m.setTiempoVida(this.tiempo_vida);
        return m;
    }
}
