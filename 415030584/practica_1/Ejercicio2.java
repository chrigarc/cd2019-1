import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.*;

public class Ejercicio2{
    public static void main(String[] args) {
        Graph grafica = new SingleGraph("Ejercicio 2");
        //Creamos una gráfica aleatoria con 10 nodos
        //y grado en promedio dos
        //con un generador según el tutorial
        Generator gen = new RandomGenerator(2);
        gen.addSink(grafica);
        gen.begin();
        for(int i=0; i<10; i++)
            gen.nextEvents();
        gen.end();

        //Damos color según el grado
        for(Node n:grafica){
            if(n.getDegree() == 0){
                n.addAttribute("ui.style", "fill-color: pink;");
            }
            else if(n.getDegree() == 1){
                n.addAttribute("ui.style", "fill-color: blue;");
            }
            else if(n.getDegree() == 2){
                n.addAttribute("ui.style", "fill-color: red;");
            }
            else if(n.getDegree() == 3){
                n.addAttribute("ui.style", "fill-color: green;");
            }
            else {
                n.addAttribute("ui.style", "fill-color: yellow;");
            }
        }

        grafica.display();
    }
}
