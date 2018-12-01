import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.awt.Color;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.Generator;

public class Main{

/*
**
**
**
** CLASE HECHA  POR LUIS PULIDO ALVAREZ.
**
**
**
**
*/

    public static void main(String[] pps){

	Graph grafica = new SingleGraph("GraficaAzar");
	grafica.addAttribute("ui.stylesheet", "node.red { fill-color: red; } node.blue { fill-color: blue; } node.green { fill-color: green; }");

	
	Generator generaAzar = new RandomGenerator(3);
	generaAzar.addSink(grafica);
	generaAzar.begin();
	while (grafica.getNodeCount() < 20 && generaAzar.nextEvents());
	generaAzar.end();

	CDGraph cdGrafica = new CDGraph(grafica);
	(new Thread(cdGrafica)).start();
    }
    
}