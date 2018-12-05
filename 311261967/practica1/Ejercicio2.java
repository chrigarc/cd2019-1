import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.*;
    
public class Ejercicio2{

    public static void main(String[] args){

	//Variable que indica el grado del nodo
	int grado;
	
	Graph grafica = new SingleGraph("Segundo ejercicio");

	//Creamos la gráfica aleatoria
	Generator generador = new RandomGenerator();
	generador.addSink(grafica);
	generador.begin();
	for(int i=0; i<10; i++){
	    generador.nextEvents();
	}
	generador.end();

	/*Recorremos la gráfica para obtener el grado de cada nodo y pintarlo
	 del color que le corresponde*/
	for(Node nodo : grafica.getEachNode()){
	    grado = nodo.getDegree();
	    if(grado == 0)
		nodo.addAttribute("ui.style", "fill-color: pink;");
	    if(grado == 1)
		nodo.addAttribute("ui.style", "fill-color: blue;");
	    if(grado == 2)
		nodo.addAttribute("ui.style", "fill-color: red;");
	    if(grado == 3)
		nodo.addAttribute("ui.style", "fill-color: green;");
	    if(grado >= 4)
		nodo.addAttribute("ui.style", "fill-color: yellow;");
	}
	
	grafica.display();
    }
}
