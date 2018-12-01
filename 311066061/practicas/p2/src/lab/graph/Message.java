package lab.graph;

public class Message {

    public Integer destFinal = null;
    public Integer TTL = 10;

    public Message(Integer destFinal){
        this.destFinal = destFinal;
    }
}
