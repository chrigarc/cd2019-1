import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
    ww

public class P1{

  public static void main(String[] pps){
    Graph graph = new SingleGraph("Tutorial 1");
    graph.display();
  }

}
//javac  -cp .:LISTAJAR P1.java
//java  -cp .:LISTAJAR  P1

// .:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar

//Class RandomGenerator
