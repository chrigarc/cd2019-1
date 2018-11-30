import java.util.LinkedList;

public class Transport{

  public static LinkedList<Tuple<Message,String>> registro;

  private static Transport transport;

  private class Tuple<X, Y> {
    public final X x;
    public final Y y;
    public Tuple(X x, Y y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object other) {
       if (other == this) {
           return true;
       }

       if (!(other instanceof Tuple)){
           return false;
       }

       Tuple<X,Y> other_ = (Tuple<X,Y>) other;

       return other_.x.equals(this.x) && other_.y.equals(this.y);
    }
  }

  public static Transport getInstance(){
  	if(transport == null) {
      transport = new Transport();
      registro = new LinkedList<Tuple<Message,String>>();
    }
      return transport;
  }

  public boolean put(Message m, String destinationNodeId){
    sleep(200);
    try{
      registro.add(new Tuple(m, destinationNodeId));
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public Message pop(String nodeId){
	  sleep(300);
    try {
      for (Tuple<Message,String> tupla : registro) {
        if (tupla.y.equals(nodeId))
        return tupla.x;
      }
    } catch (Exception e) {
      return null;
    }
    return null;
  }

  private void sleep(int ms){
	  try{
	     Thread.sleep(ms);
	  } catch(Exception ex){ }
  }
}
