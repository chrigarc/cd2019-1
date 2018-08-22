//javac -cp .:../lib/gs-core.jar 
//javac -cp .:LISTAJAR P1.java
//java -cp .:LISTAJAR P1
//.:../lib/gs-core-1.3.jar:../lib/gs-ui-1.3.jar:../lib/gs-algo-1.3.jar
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;


public class P1{

	public static void main(String[] pps){
		System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph graph = new SingleGraph("Tutorial 1");	
		graph.addNode("A" );
		graph.addNode("B" );
		graph.addNode("C" );
		graph.addNode("D" );
		graph.addNode("E" );
		graph.addEdge("AB", "A", "B");
		graph.addEdge("AD", "A", "D");
		graph.addEdge("AE", "A", "E");
		graph.addEdge("BC", "B", "C");
		graph.addEdge("CA", "C", "A");
		graph.getNode("A").addAttribute("ui.style", "fill-color: rgb(255,0,0);");
		graph.getNode("B").addAttribute("ui.style", "fill-color: rgb(0,255,0);");
		graph.getNode("C").addAttribute("ui.style", "fill-color: rgb(0,0,255);");
		graph.getNode("D").addAttribute("ui.style", "fill-color: rgb(0,100,255);");
		graph.getNode("E").addAttribute("ui.style", "fill-color: rgb(255,100,0);");
		graph.display();
	}
	
}
