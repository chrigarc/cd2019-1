import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.awt.Color;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;

/*
* 
* Codigo hecho por Eduardo Rubio Lezama
*
*
*/
public class Propuesta{


    public static void main(String[] pps){

        

        SingleGraph grafica = new SingleGraph("Propuesta Práctica 3");
        grafica.addAttribute("ui.stylesheet", "node{ fill-color: purple; }");
        grafica.addNode("A");
        grafica.addNode("B");

        grafica.addEdge("AB", "A", "B");

        grafica.display();
    }

}