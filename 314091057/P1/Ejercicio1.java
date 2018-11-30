  import org.graphstream.graph.*;
	import org.graphstream.graph.implementations.*;
//class RandomGenerator
	public class Ejercicio1 {
		public static void main(String[] pps) {
			Graph graph = new SingleGraph("Tutorial 1");

			graph.addNode("A");
      Node a = graph.getNode("A");
      a.addAttribute("ui.style", "fill-color: pink;");

      graph.addNode("B");
      Node b = graph.getNode("B");
      b.addAttribute("ui.style", "fill-color: red;");

      graph.addNode("C");
      Node c = graph.getNode("C");
      c.addAttribute("ui.style", "fill-color: blue;");

      graph.addNode("D");
      Node d = graph.getNode("D");
      a.addAttribute("ui.style", "fill-color: yellow;");

      graph.addNode("F");
      Node f = graph.getNode("F");
      f.addAttribute("ui.style", "fill-color: brown;");

      graph.addEdge("AB", "A", "B");
			graph.addEdge("BC", "B", "C");
			graph.addEdge("CA", "C", "A");
      graph.addEdge("AD", "A", "D");
      graph.addEdge("BF", "F", "B");
      graph.addEdge("DF", "D", "F");

			graph.display();
		}
	}
