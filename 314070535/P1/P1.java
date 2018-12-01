import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.*;
import java.awt.Color;

public class Prueba{

	public static void main(String[] pps){
		Graph graph = new SingleGraph("Tutorial");

		Generator gen = new RandomGenerator(2);
		gen.addSink(graph);
		gen.begin();
		for(int i=0; i<100; i++){
			gen.nextEvents();	
		}


		for( Node o : graph.getEachNode()){
		if(o.getDegree()==0) o.setAttribute("ui.style", "fill-color: pink ;");
		if(o.getDegree()==1) o.setAttribute("ui.style", "fill-color: blue ;");
		if(o.getDegree()==2) o.setAttribute("ui.style", "fill-color: red ;");
		if(o.getDegree()>3) o.setAttribute("ui.style", "fill-color: yellow ;");



       	System.out.println(o.getDegree());


        
        // Do something with node
  }
		gen.end();
		graph.display();

	}
}