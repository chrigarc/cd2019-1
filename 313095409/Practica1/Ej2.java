import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
import org.graphstream.algorithm.generator.*;
import java.util.*;
import java.awt.Color;

public class Ej2 {
  public static void main(String[] args) {

   Graph graph = new SingleGraph("Ej2");
   graph.setAttribute("ui.stylesheet","node {fill-mode:dyn-plain;}");
   Generator gen = new RandomGenerator(2);
   gen.addSink(graph);
   gen.begin();
   for(int i=0; i<10; i++)
       gen.nextEvents();
   gen.end();
   graph.display();

   Collection<Node> nodeSet = graph.getNodeSet();
   Node nodos [] = nodeSet.toArray(new Node [nodeSet.size()]);
   int d;
   for (int i = 0; i < nodos.length ; i++) {
     d = nodos[i].getDegree();
     switch (d) {
       case 0 : nodos[i].setAttribute("ui.color",Color.PINK);break;
       case 1 : nodos[i].setAttribute("ui.color", Color.BLUE);break;
       case 2 : nodos[i].setAttribute("ui.color", Color.RED);break;
       case 3 : nodos[i].setAttribute("ui.color", Color.GREEN);break;
       default: nodos[i].setAttribute("ui.color", Color.YELLOW);break;
     }
   }
  }
}
