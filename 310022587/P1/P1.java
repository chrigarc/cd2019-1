import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.*;
import org.graphstream.algorithm.Algorithm;

public class P1{
  public static void main(String[] pps) {
    Graph graph = new SingleGraph("Tutorial 1");
    graph.addNode("A");
  	graph.addNode("B" );
  	graph.addNode("C" );
  	graph.addNode("D" );
    graph.addNode("E" );
  	graph.addEdge("AB", "A", "B");
  	graph.addEdge("BC", "B", "C");
  	graph.addEdge("CD", "C", "D");
    graph.addEdge("DE", "D", "E");
    graph.addEdge("EA", "E", "A");
    Node e1 = graph.getNode("A");
    Node e2 = graph.getNode("B");
    Node e3 = graph.getNode("C");
    Node e4 = graph.getNode("D");
    Node e5 = graph.getNode("E");
		e1.addAttribute("ui.style", "shape:circle;fill-color: yellow;size: 30px;");
    e2.addAttribute("ui.style", "shape:circle;fill-color: red;size: 30px;");
    e3.addAttribute("ui.style", "shape:circle;fill-color: blue;size: 30px;");
    e4.addAttribute("ui.style", "shape:circle;fill-color: green;size: 30px;");
    e5.addAttribute("ui.style", "shape:circle;fill-color: pink;size: 30px;");
    graph.display();
    // nueva grafica randomizada
    Graph graph2 = new SingleGraph("Random");
    Generator gen = new RandomGenerator();
    gen.addSink(graph2);
    gen.begin();
    while (graph2.getNodeCount() < 10 && gen.nextEvents());
    gen.end();

    for(Node n : graph2.getEachNode() ) {
      int deg = n.getDegree();
      if(deg > 4){
        n.addAttribute("ui.style", "shape:circle;fill-color: yellow;size: 30px;");
      } else if (deg == 3) {
        n.addAttribute("ui.style", "shape:circle;fill-color: green;size: 30px;");
      } else if (deg == 2) {
        n.addAttribute("ui.style", "shape:circle;fill-color: red;size: 30px;");
      } else if (deg == 1) {
        n.addAttribute("ui.style", "shape:circle;fill-color: blue;size: 30px;");
      } else{
        n.addAttribute("ui.style", "shape:circle;fill-color: pink;size: 30px;");
      }
    }
    graph2.display();
  }
}