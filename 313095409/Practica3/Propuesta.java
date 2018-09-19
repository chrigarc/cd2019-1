import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Graph;

public class Propuesta{
  private Graph propuesta;

  public Propuesta(){
    propuesta = new SingleGraph("Propuesta");
    // Termina la hoja de estilo para que las computaciones equivalentes tengan los nodos de diferentes colores
    propuesta.addAttribute("ui.stylesheet", "node.red { fill-color: red; } node.blue { fill-color: blue; } node.green { fill-color: green; }");
    propuesta.addNode("A");
    propuesta.addNode("B");
    propuesta.addNode("D");
    propuesta.addNode("E");
    propuesta.addNode("F");
    propuesta.addNode("H");
    propuesta.addNode("I");

    propuesta.addEdge("AB", "A", "B");
    propuesta.addEdge("AE", "A", "E");
    propuesta.addEdge("AH", "A", "H");
    propuesta.addEdge("AI", "A", "I");
    propuesta.addEdge("BE", "B", "E");
    propuesta.addEdge("BI", "B", "I");
    propuesta.addEdge("BF", "B", "F");
    propuesta.addEdge("EF", "E", "F");
    propuesta.addEdge("EH", "E", "H");
    propuesta.addEdge("HF", "H", "F");
    propuesta.addEdge("HD", "H", "D");
    propuesta.addEdge("IF", "I", "F");
    propuesta.addEdge("ID", "I", "D");
    propuesta.addEdge("DF", "D", "F");
  }

  public void start(){
    CDGraph cdg = new CDGraph(propuesta);
    cdg.setSource("A");
    cdg.setDestination("D");
    cdg.start();
  }

}
