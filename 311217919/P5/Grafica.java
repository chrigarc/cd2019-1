import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.*;
import org.graphstream.algorithm.Algorithm;

public class Grafica {

  public Graph grafo;

  public Grafica(String nombre){
    this.grafo= new SingleGraph(nombre);
  }

  public void agregaNodo(String nodo){
    this.grafo.addNode(nodo);
  }


  public void mostrar(){
    grafo.display();
  }




}
