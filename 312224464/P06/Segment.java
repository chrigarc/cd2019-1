import java.util.Random;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
* Clase que hace la representación de los segmentos que se contendrán en la ventana.
*/
public class Segment implements Serializable{
  private String contenido;
  private int indice;
  private boolean status; //indica si el segmento es valido, esto se asigna al azar con una probalidad de 5 %


  /*
  * Método contructor que recibe un contenido y asigna un estado del segmento para conocer su validez.
  * @param contenido - de tipo String. 
  **/
  public Segment (String contenido){
  	this.contenido = contenido; 
  	Random valido = new Random();
  	this.status = (valido.nextInt(100) >= 5);//Asignamos una validez con probalidad de 5%.
  }

  public String getContenido(){
  	return this.contenido;
  }

  public void setContenido(String contenido){
  	this.contenido = contenido;
  }

  public boolean getStatus(){
  	return this.status; 
  }

  public void setStatus(boolean status){
  	this.status = status; 
  }


}
