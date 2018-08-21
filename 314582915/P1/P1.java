import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

  public class P1{

    public static void main(String[] pps){
      Graph graph = new SingleGraph("Tutorial 1");
      graph.addNode("1");
      graph.addNode("2");
      graph.addNode("43");
      graph.addEdge("1","2","43");
      graph.display();

    }
  }
