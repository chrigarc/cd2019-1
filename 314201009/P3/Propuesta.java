import java.util.ArrayList;
import java.util.List;

public class Propuesta{

    public static List<Message> equivalentes(List<Message> mensajes)
    {
        List<Message> equivalentes = new ArrayList<Message>();
        // List<String> ids = new ArrayList<String>();
        Message m1 = null;
        Message m2 = null;
        for(int i = 0; i < mensajes.size()-1; i++) {
            m1 = mensajes.get(i);
            // if(!ids.contains(m1.getSource())) {
            //   ids.add(m1.getSource());
              for(int j = 0; j < mensajes.size()-1; j++) {
                  m2 = mensajes.get(j);
                  // System.out.println(m1);
                  // System.out.println(m2);
                  if(!m1.equals(m2)) {
                      if(m1.getSource().equals(m2.getSource()) &&
                         m1.getEnd().equals(m2.getEnd()) &&
                         m1.getPasos() == m2.getPasos()) {
                              if(!equivalentes.contains(m2)){
                                  equivalentes.add(m2);
                              }
                         }
                  }
              }
            // }
        }
        return equivalentes;
    }

}
