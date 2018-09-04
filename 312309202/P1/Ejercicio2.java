	//import java.util.Random;
    //import org.graphstream.stream.Source;

	import org.graphstream.graph.*;
	import org.graphstream.graph.implementations.*;
	import org.graphstream.algorithm.generator.*;
	import org.graphstream.stream.*;

	public class Ejercicio2 {

		public static void main(String[] args) {
			
	 	Graph graph = new SingleGraph("Random");
 		Generator gen = new RandomGenerator(2);
 		gen.addSink(graph);
 		gen.begin();
 		while (graph.getNodeCount() < 100 && gen.nextEvents());
 		gen.end();
 	
 		for (int i=0;i<100 ;i++ ) {
 

 		if(graph.getNode(i).getInDegree() == 0){
 			graph.getNode(i).addAttribute("ui.class","rosa");
 		}

 		
 		if(graph.getNode(i).getInDegree() == 1){
 			graph.getNode(i).addAttribute("ui.class","azul");
 		}


 		if(graph.getNode(i).getInDegree() == 2){
 			graph.getNode(i).addAttribute("ui.class","rojo");
 		}

 		if(graph.getNode(i).getInDegree() == 3){
 			graph.getNode(i).addAttribute("ui.class","verde");
 		}

 		if(graph.getNode(i).getInDegree() >= 4){
 			graph.getNode(i).addAttribute("ui.class","amarillo");
 		}


 		}
	
		graph.addAttribute("ui.stylesheet", "node.rosa{fill-color:pink;} node.azul{fill-color:blue;} node.verde{fill-color:green;} node.amarillo{fill-color:yellow;} node.rojo{fill-color:red;}");
		

 		graph.display();


		}
	}
 
	