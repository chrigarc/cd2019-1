public class Segment{
    private String content;
    private boolean status; //indica si el segmento es valido, esto se asigna al azar con una probalidad de 5 %

    public Segment(String content){
        this.content = content;
        double d = Math.random() * 100;
        //System.out.println("[SEGMENT]: " + d);
        status = d > 5.0;
    }

    public boolean isValid(){
        return status;
    }

    public String getContent(){
        return content;
    }

    public void setValid(boolean valid){
        this.status = valid;
    }
}
