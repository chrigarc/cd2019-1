/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message{

    private String source;
    private int time;
    private int pasos;
    private String end;
    private String recorrido;

    public Message(String source){
      this.source = source;
      this.time = 100;
      this.pasos = 0;
      this.end = "";
      this.recorrido = "Start: " + source;
    }

    public String getSource()
    {
      return this.source;
    }

    public void setSource(String source)
    {
      this.source = source;
    }

    public int getTime()
    {
      return this.time;
    }

    public void setTime(int time)
    {
      this.time = time;
    }

    public int getPasos()
    {
      return this.pasos;
    }

    public void setPasos(int pasos)
    {
      this.pasos = pasos;
    }

    public String getEnd()
    {
      return this.end;
    }

    public void setEnd(String end)
    {
      this.end = end;
    }

    public String getRecorrido()
    {
      return this.recorrido;
    }

    public void setRecorrido(String recorrido)
    {
      this.recorrido = recorrido;
    }

    @Override
    public String toString()
    {
      String s = "Info mensaje:\n" +
                 "Origen: " + this.getSource() + "\n" +
                 "Final: " + this.getEnd() + "\n" +
                 "Pasos: " + this.getPasos() + "\n" +
                 "Recorrido: " + this.getRecorrido() + "\n\n";
      return s;
    }

}
