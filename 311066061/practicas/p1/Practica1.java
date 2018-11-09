import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.algorithm.generator.*;
import java.util.Random;



public class Practica1 {

    private static String intToString(int i) {
	return Integer.toString(i);
    }

    public static void main(String ... args) {
	    SingleGraph g = new SingleGraph("g");
	    RandomGenerator rg = new RandomGenerator();
	    Random rdm = new Random();
	    rg.addSink(g);
	    int events = ran.nextInt(200);
	    rg.begin();
	    for(int i = 0; i < events; i++)
	        rg.nextEvents();
	    rg.end();
	    String [] colors = new String [2];
	    for(int i = 0; i < 3; i++) {
	        int r, g, b;
	        r = rdm.nextInt(255);
	        g = rdm.nextInt(255);
	        b = rdm.nextInt(255);
	        colors[i] = "("+intToString(r)+","+intToString(g)+","+intToString(b)+")";
	    }
	    for(Node n : g)
	        if(n.getDegree() < 1) 
		    n.addAttribute("ui.style", "fill-color: rgb"+colors[0]+";");
	        else
		    n.addAttribute("ui.style", "fill-color: rgb"+colors[1]+";");
	    g.display();
    }
}
