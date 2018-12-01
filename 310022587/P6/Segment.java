public class Segment{
    /* Contenido del segmento */
    private String contenido;
    /* Status del segmento */
    private boolean status; 
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
    
    public boolean esValido(){
        return this.status;
    }
    
    public boolean getContenido(){
        return this.status;
    }
    
    public void setStatus(boolean status){
        this.status = status;
    }
}