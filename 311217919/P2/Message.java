/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message {

    public final static int vida = 100;

    private int tvida;
    private String msn;
    private String recibo;
    private String envio;
    private static int idmsn = 0;
    private int id;

    public Message(String recibo, String envio){
      this.recibo = recibo;
      this.envio = envio;
      this.tvida = vida;
      this.id = ++idmsn;
    }

    public void setMensaje(String mensaje){
      this.msn = mensaje;
    }

    public String getRecibo(){
      return this.envio;
    }

    public String getMensaje(){
      if(this.msn == null){
        return "Nodo vacio";
      }
      return this.msn;
    }

    public void disminiyeTiempo(){
      this.tvida = --this.tvida;
    }



}
