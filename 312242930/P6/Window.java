import java.util.Random;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
/**
* Clase para hacer la representación de la ventana deslizante que hara el control de los segmentos que se van enviando.
*/
public class Window implements Serializable{

  private Segment[] segments;
  private int size;

  public Window(int size){
  	this.size = size;
  	this.segments = new Segment[size];
  }

  public Segment[] getSegmentos(){
  	return this.segments;
  }

  public void setSegmentos(Segment[] segmentos){
  	this.segments = segmentos;
  }

  public void agregaSegmento(int i, Segment segmento){
  	if(i< this.size){
  		this.segments[i] = segmento;
  	}else{
  		System.out.println("No se pudo agregar el segmento");
  	}
  }

  public Segment get(int indice){
  	return this.segments[indice];
  }

	/**
	* Se autoconvierte esta clase a array de bytes.
	* @return La clase convertida a array de bytes.
	*/
	public byte [] toByteArray(){
		try{
		     // Se hace la conversion usando un ByteArrayOutputStream y un
		     // ObjetOutputStream.
		    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		    ObjectOutputStream os = new ObjectOutputStream (bytes);
		    os.writeObject(this);
		    os.close();
		    return bytes.toByteArray();
		}catch(Exception e){
		    e.printStackTrace();
		    return null;
		}
	}

	/**
	* Se convierte el array de bytes que recibe en un objeto Dato.
	* @param bytes El array de bytes
	* @return Un Dato.
	*/
	public static Window fromByteArray (byte [] bytes){
		try{
		    // Se realiza la conversion usando un ByteArrayInputStream y un
		    // ObjectInputStream
		    ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
		    ObjectInputStream is = new ObjectInputStream(byteArray);
		    Window ventana = (Window)is.readObject();
		    is.close();
		    return ventana;
		}catch(Exception e){
		    e.printStackTrace();
		    return null;
		}
	}  

  public String toString(){
  	String cadena = "[ ";
  	for (Segment s : this.segments) {
  		if(s != null){

  			cadena += s.getContenido()+", ";
  		}
  	}
  	return "Ventana: "+cadena+"] \n";
  }
}
