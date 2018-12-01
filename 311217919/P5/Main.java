import java.util.Iterator;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.*;
import org.graphstream.algorithm.Algorithm;

public class Main{
  public static void main(String[] args) {
    Graph g1 = new SingleGraph("Envio");



    //Grafica g2 = new Grafica("Recibo");

    Enviamsn msn = new Enviamsn(args[0]);
    int p = msn.getPuerto();
    msn.sendMsn("31121791-9");

    g1.addNode(msn.getMensaje());
    Node e1 = g1.getNode("A");
    for (Node node : g1) {
        node.addAttribute("ui.label", node.getId());
    }
    g1.display();


    //Recibemsn rmsn = new Recibemsn(p);
    //rmsn.receiveMsn();

  }
}
