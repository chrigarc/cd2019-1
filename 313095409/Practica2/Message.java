/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message{

  private String source;
  private int tiempoVida;

  public Message(String source){
    this.source = source;
    this.tiempoVida = 100;
  }

  public String source(){
    return source;
  }

  public int tiempoVida(){
    return tiempoVida;
  }

  public void reduceTiempo(){
    tiempoVida -= 1;
  }
}
