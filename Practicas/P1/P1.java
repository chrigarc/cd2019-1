//javac -cp .:../lib/gs-core.jar 
//javac -cp .:LISTAJAR P1.java
//java -cp .:LISTAJAR P1
//.:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.*;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;

public class P1{

	public static void main(String[] pps){
		System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		/**Graph graph = new SingleGraph("Tutorial 1");	
		graph.addNode("A" );
		graph.addNode("B" );
		graph.addNode("C" );
		graph.addNode("D" );
		graph.addNode("E" );
		graph.addEdge("AB", "A", "B");
		graph.addEdge("AD", "A", "D");
		graph.addEdge("AE", "A", "E");
		graph.addEdge("BC", "B", "C");
		graph.addEdge("BD", "B", "D");
		graph.addEdge("BE", "B", "E");
		graph.addEdge("CA", "C", "A");
		graph.addEdge("CD", "C", "D");
		graph.addEdge("CE", "C", "E");
		graph.addEdge("DE", "D", "E");
		graph.getNode("A").addAttribute("ui.style", "fill-color: rgb(255,0,0);");
		graph.getNode("B").addAttribute("ui.style", "fill-color: rgb(0,255,0);");
		graph.getNode("C").addAttribute("ui.style", "fill-color: rgb(0,0,255);");
		graph.getNode("D").addAttribute("ui.style", "fill-color: rgb(0,100,255);");
		graph.getNode("E").addAttribute("ui.style", "fill-color: rgb(255,100,0);");
		graph.display();*/
		Graph graph = new SingleGraph("Random");
	    Generator gen = new RandomGenerator(2);
	    gen.addSink(graph);
	    gen.begin();
	    for(int i=0; i<8; i++)
	        gen.nextEvents();
	    gen.end();
	    graph.display();
	}
	
}
//ejercicio 2 es generar grafica aleatoria con almenos (10 nodos)
//pintar de colores distintos todos los nodos co  al menos 1 vecino.
//pintar de morad todos los nodos sin vecinos.