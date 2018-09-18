public class Transport{

  private static Transport transport;

  public static Transport getInstance(){
    if(transport == null){
      transport = new Transport();
    }
    return transport;
  }

  public boolean put(Message m, String destination){
    String source = m.getSource();
    int time = m.getTime();
    if(time == 0) {
        System.out.println("Mensaje caducado: " + source +
                           "->" + destination +"\n");
        return false;
    }
    time = time - 10;
    m.setTime(time);
    sleep(1000);
    return true;
  }

  public Message pop(String n){
    Message msg = new Message("ID: " + n);
    sleep(1000);
    return msg;
  }

  private void sleep(int ms){
    try{
      Thread.sleep(ms);
    }catch(Exception ex){
    }
  }
}
