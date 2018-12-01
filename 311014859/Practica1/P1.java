import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.*;
import org.graphstream.algorithm.Algorithm;

public class P1{
  public static void main(String[] pps) {
    Graph grafica = new SingleGraph("Grafo");
  	//Creamos nodos en la grafica
    grafica.addNode("B" );//
    
    grafica.addNode("A");//
  	
    grafica.addNode("D" );//Lineas de codigo hechas por Luis Pulido Alvarez
    
    grafica.addNode("E" );//
    
    grafica.addNode("C" );//
  	
    //Generamos Aristas en la graficas. 
    grafica.addEdge("AB", "A", "B");//
  	grafica.addEdge("BC", "B", "C");//
  	grafica.addEdge("CD", "C", "D");//Lineas de codigo hechas por Eduardo Rubio Lezama 
    grafica.addEdge("DE", "D", "E");//
    grafica.addEdge("EA", "E", "A");//
    

    
    Node e2 = grafica.getNode("B");//
    
    Node e1 = grafica.getNode("A");//
    
    Node e3 = grafica.getNode("C");//Lineas de codigo hechas por Mauricio Javier Salas Martínez. 
    
    Node e5 = grafica.getNode("E");//
    
    Node e4 = grafica.getNode("D");//

		e1.addAttribute("ui.style", "shape:circle;fill-color: yellow;size: 30px;"); // 
    e2.addAttribute("ui.style", "shape:circle;fill-color: red;size: 30px;");//
    e3.addAttribute("ui.style", "shape:circle;fill-color: blue;size: 30px;");// Lineas de codigo hechas por Ian Eduardo Chavez Munioa
    e4.addAttribute("ui.style", "shape:circle;fill-color: green;size: 30px;");//
    e5.addAttribute("ui.style", "shape:circle;fill-color: pink;size: 30px;");//
    grafica.display();

    // Generamos nodos 
    Graph graficaAzar = new SingleGraph("Random");//
    Generator generaAzar = new RandomGenerator();//
    generaAzar.addSink(graficaAzar);//
    generaAzar.begin();// Lineas de codigo hechas por Luis pulido 
    while (graficaAzar.getNodeCount() < 10 && generaAzar.nextEvents());//
    generaAzar.end();//
    
    //Recorremos todos los nodos de la grafica. 
    for(Node nodo : graficaAzar.getEachNode() ) { //
      int numero = nodo.getDegree();//
      if(numero > 4)  nodo.addAttribute("ui.style", "shape:circle;fill-color: yellow;size: 30px;");//
      else if (numero == 3)   nodo.addAttribute("ui.style", "shape:circle;fill-color: green;size: 30px;");//
       else if (numero == 2)  nodo.addAttribute("ui.style", "shape:circle;fill-color: red;size: 30px;");// Lineas hechas por Ian Eduardo Chavez Munioa  
      else if (numero == 1)   nodo.addAttribute("ui.style", "shape:circle;fill-color: blue;size: 30px;");//
       else  nodo.addAttribute("ui.style", "shape:circle;fill-color: pink;size: 30px;");//
    }//
    graficaAzar.display();
  }
}