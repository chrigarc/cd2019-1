import java.util.Iterator;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.*;
import org.graphstream.algorithm.Algorithm;
import java.util.Scanner;

public class Main{
  public static void main(String[] args) {
    Graph g1 = new SingleGraph("Envio");

    Enviamsn msn = new Enviamsn("localhost");
    int p = msn.getPuerto();
    msn.sendMsn("31121791-9");

    g1.addNode(msn.getMensaje());
    Node e1 = g1.getNode("A");
    for (Node node : g1) {
        node.addAttribute("ui.label", node.getId());
    }
    g1.display();
    Scanner sc = new Scanner(System.in);
    System.out.println("Indique un mensaje: ");
    String s;
    while(true){
      s = sc.nextLine();
      if(s.equals("-")){
        break;
      }else{
        msn.sendMsn(s);
        g1.addNode(msn.getMensaje());
        for (Iterator<Node> itr1=g1.iterator(); itr1.hasNext();){
          Node l = itr1.next();
          l.addAttribute("ui.label", l.getId());
        }
        //for (Node node : g1) {
        //}
      }
    }
  }
}
