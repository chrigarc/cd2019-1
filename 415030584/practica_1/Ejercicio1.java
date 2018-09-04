import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

public class Ejercicio1{
    public static void main(String[] args) {
        Graph grafica = new SingleGraph("Ejercicio 1");
        grafica.addNode("A");
        grafica.addNode("B");
        grafica.addNode("C");
        grafica.addNode("D");
        grafica.addNode("E");
        grafica.addEdge("AD", "A", "D");
        grafica.addEdge("BD", "B", "D");
        grafica.addEdge("CD", "C", "D");
        grafica.addEdge("CE", "C", "E");

        Node A = grafica.getNode("A");
        Node B = grafica.getNode("B");
        Node C = grafica.getNode("C");
        Node D = grafica.getNode("D");
        Node E = grafica.getNode("E");

        A.addAttribute("ui.style", "fill-color: purple;");
        B.addAttribute("ui.style", "fill-color: grey;");
        C.addAttribute("ui.style", "fill-color: red;");
        D.addAttribute("ui.style", "fill-color: green;");
        E.addAttribute("ui.style", "fill-color: pink;");

        grafica.display();
    }

}