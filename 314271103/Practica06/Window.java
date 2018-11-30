public class Window{

	/* Los segmentos */
	private Segment[] segments;

	/* Tamaño de la ventana */
	private int tamaño = CDNode.WINDOW_SIZE;

	/* El indice para recorrer la ventana */
	private int indice;

	/* Status de la ventana */
	private boolean status;

	/* Constructor de la clase */
	public Window(){
		this.indice = 0;
		segments = new Segment[tamaño];
	}

	/**
	 * Metodo que añade el contenido que se le pasa como parametro a un segmento, se añade 
	 * al arreglo de segmentos y se regresa el status del segmento.
	 *@param contenido el contenido del nuevo segmento.
	 *@return getStatus() una llamada al metodo getStatus.
	 */
	public boolean añadeContenido(String contenido){
		if(indice < tamaño){
			segments[indice] = new Segment(contenido);
			indice+=1;
			this.status = true;
		}
		return getStatus();
	}

	/**
	 * Metodo que regresa el status de la ventana.
	 *@return status el estatus de la ventana.
	 */
	public boolean getStatus(){
		return this.status;
	}

	/**
	 * Metodo que regresa el arreglo de segmentos.
	 *@return segments el arreglo de segmentos.
	 */
	public Segment[] getSegmets(){
		return this.segments;
	}

	/**
	 * Metoda para valdiar un segmento en caso de que no lo sea.
	 */
	public void reintenta(){
		for(Segment i: segments){
			if(s!= null){
				i.setStatus(true);
			}
		}
	}
}
