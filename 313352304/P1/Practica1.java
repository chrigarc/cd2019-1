import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Practica1 {

    public static void main(String[] args) {
        Graph g = new SingleGraph("Graph 1");

        g.addNode("rojo");
        g.addNode("verde");
        g.addNode("azul");
        g.addNode("amarillo");
        g.addNode("morado");
        g.addNode("aux1");

        g.addEdge("morado-aux1", "morado", "aux1");
        g.addEdge("morado-verde", "morado", "verde");
        g.addEdge("morado-amarrillo", "morado", "amarillo");
        g.addEdge("morado-azul", "morado", "azul");
        g.addEdge("morado-rojo", "morado", "rojo");
        g.addEdge("amarillo-verde", "amarillo", "verde");
        g.addEdge("amarillo-azul", "amarillo", "azul");
        g.addEdge("amarillo-aux1", "amarillo", "aux1");
        g.addEdge("azul-aux1", "azul", "aux1");

        g.addAttribute("ui.stylesheet", "url:(./css/stylesheet1.css)");

        g.display();

        //for (node n: g) {

        //}

    }

}
