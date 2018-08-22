import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
import java.awt.Color;

public class Ej1 {
  public static void main(String[] args) {
    Graph graph = new SingleGraph("Ej1");
    graph.setAttribute("ui.stylesheet",
                       "graph {fill-color:black;}"
                    + "edge {fill-color:white;}"
                    + "node {fill-mode:dyn-plain;}");

    graph.addNode("1");
    graph.getNode("1").setAttribute("ui.color", Color.RED);
    graph.addNode("2");
    graph.getNode("2").setAttribute("ui.color", Color.CYAN);
    graph.addNode("3");
    graph.getNode("3").setAttribute("ui.color", Color.GRAY);
    graph.addNode("4");
    graph.getNode("4").setAttribute("ui.color", Color.BLUE);
    graph.addNode("5");
    graph.getNode("5").setAttribute("ui.color", Color.ORANGE);

    graph.addEdge("1,2","1","2");
    graph.addEdge("1,3","1","3");
    graph.addEdge("2,3","2","3");
    graph.addEdge("3,4","3","4");
    graph.addEdge("4,5","4","5");
    graph.addEdge("4,2","4","2");
    graph.addEdge("5,3","5","3");

    graph.display();
  }
}
