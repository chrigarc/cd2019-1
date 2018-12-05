import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
    

public class Ejercicio1{
    
  public static void main(String[] pps){
    Graph grafica = new SingleGraph("Primer Ejercicio");
 
    
    //Creamos los nodos 
    grafica.addNode("1");
    grafica.addNode("2");
    grafica.addNode("3");
    grafica.addNode("4");
    grafica.addNode("5");
    grafica.addNode("6");

    for(Node nodo : grafica){
        nodo.addAttribute("ui.label", nodo.getId());
    }

    //Unimos nodos con aristas
    grafica.addEdge("(1,3)","1","3");
    grafica.addEdge("(2,4)","2","4");
    grafica.addEdge("(5,6)","5","6");
    grafica.addEdge("(1,4)","1","4");
    grafica.addEdge("(2,6)","2","6");
    grafica.addEdge("(3,6)","3","6");
    grafica.addEdge("(4,5)","4","5");

    //Obtenemos los nodos de la gráfica
    Node nodo1 = grafica.getNode("1");
    Node nodo2 = grafica.getNode("2");
    Node nodo3 = grafica.getNode("3");
    Node nodo4 = grafica.getNode("4");
    Node nodo5 = grafica.getNode("5");
    Node nodo6 = grafica.getNode("6");

    //LLamamos a cada nodo para agregarle el atributo de color
    nodo1.addAttribute("ui.style", "fill-color: blue;");
    nodo2.addAttribute("ui.style", "fill-color: red;");
    nodo3.addAttribute("ui.style", "fill-color: green;");
    nodo4.addAttribute("ui.style", "fill-color: yellow;");
    nodo5.addAttribute("ui.style", "fill-color: purple;");
    nodo6.addAttribute("ui.style", "fill-color: pink;");
    
    grafica.display();
  }

    
}
