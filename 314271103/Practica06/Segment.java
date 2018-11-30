public class Segment{

	/* Conteido del segmento */
	private String contenido;

	/* Status del segmento */
	private boolean status; //indica si el segmento es valido, esto se asigna al azar con una probalidad de 5 %

	/* Probabilidad de que el segmento sea valido */
	private double probabilidad = Math.random() * 100;

	/* Constructor de la clase */
	public Segment(String contenido){
		this.contenido = contenido;
		if(probabilidad > 5.0){
			setStatus(true)
		}else{
			setStatus(false);
		}
	}

	/**
	 * Metodo que regresa si el segmento es valido.
	 *@return status el estatus del segmento.
	 */
	public boolean esValido(){
		return this.status;
	}

	/**
	 * Metodo que regresa el contenido del segmento.
	 *@return contenido el contenido del segmento.
	 */
	public boolean getContenido(){
		return this.status;
	}

	/** 
	 * Metodo que regresa el status del segmento.
	 *@param status el status del segmento.
	 */
	public void setStatus(boolean status){
		this.status = status;
	}
}
