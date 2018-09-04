/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message{
    private String idSource;
    private String idDestino;
    private int tiempoVida;

    public Message(String source, String destino){
        this.idSource = source;
        this.idDestino = destino;
        this.tiempoVida = 100;
    }

    public Message(String source, String destino, int vida){
        this.idSource = source;
        this.idDestino = destino;
        this.tiempoVida = vida;
    }

    public void leerMessage(){
        if(this.tiempoVida > 0){
            this.tiempoVida--;
        }
    }

    public int getTiempoVida(){
        return this.tiempoVida;
    }

    public boolean estaVivo(){
        return (this.tiempoVida > 0);
    }

    public String getDestino(){
        return this.idDestino;
    }

    @Override
    public Message clone(){
        Message clon = new Message(this.idSource, this.idDestino, this.getTiempoVida());
        return clon;
    }
}
