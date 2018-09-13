/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message{

    private String source;
    private int time;

    public Message(String source){
      this.source = source;
      this.time = 100;
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

}
