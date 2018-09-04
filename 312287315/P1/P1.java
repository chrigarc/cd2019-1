import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.util.Random;
import java.awt.Color;

public class P1{
	/**Metodo que genera una gráfica al azar con la cantidad de aristas y nodos dada.
	 * @param nodos ,int. Es la cantidad de nodos para la grafica.
	 * @param aristas, int. es la cantidad de aristas para la grafica.
	 * @return Graph, regresa la gráfica generada al azar.
	 */
	public static Graph hazGrafica(int nodos, int aristas){
		if(aristas > (nodos*(nodos-1)/2)){//Si la cantidad de aristas es más grande de la posible no podemos acomodarlas todas
			System.out.println("cantidad de aristas  mas grande de las posibles");
			return null;
		}else{
		Graph g = new SingleGraph("Grafica de " + Integer.toString(nodos)+ " aristas y " + Integer.toString(aristas) + " aristas");
		int i = nodos; //contador auxiliar
		while(i > 0){//Agregamos los nodos con nombres de 1, 2, 3... hasta el numero nodos dado
			g.addNode(Integer.toString(i));//agregamos el nodo i
			i--;
		}
		int[] gradoNodo = new int[nodos];//guardamos el grado del nodo i en la posicion i-1 para casos como cuando el ultimo nodo tiene su maxima cantidad de aristas pero por el algoritmo se busca ponerle una arista
		Random r = new Random();//numero aleatorio para seleccionar un nodo al azar
		i = 0;
		while(aristas > 0){//mientras aún haya aristas que acomodar
			int extremo = r.nextInt(nodos);//Seleccionamos un nodo al azar para unir al nodo i
			if(gradoNodo[i] == (nodos-1)){//Si un nodo tiene el grado máximo ya no puede agregar otra arista
				i = (i+1)%nodos;//entonces nos movemos al siguiente nodo
				}else{	
				if(!g.getNode(Integer.toString(i+1)).hasEdgeBetween(Integer.toString(extremo+1)) && i != extremo){//Si la arista generada por el nodo extremo e i , procedemos a hacer la arista
					g.addEdge(Integer.toString(i+1) + Integer.toString(extremo+1), Integer.toString(i+1), Integer.toString(extremo+1));//agregamos la arista con extremo e i
					aristas--;//agregamos una arista entonces nos queda una arista menos que poner
					i = (i+1)%nodos;//nos movemos al siguiente nodo
				}
			}
		}
		return g;
	}//if
	}
	public static void main(String[] args){
	Random r = new Random();
	Graph graph = hazGrafica(10, r.nextInt(25));

	Graph g = new SingleGraph("Grafica de 5 vertices");
	g.addNode("1");
	g.addNode("2");
	g.addNode("3");
	g.addNode("4");
	g.addNode("5");
	g.addEdge("1-2", "1", "2");
	g.addEdge("2-3", "2", "3");
	g.addEdge("3-4", "3", "4");
	g.addEdge("4-5", "4", "5");
	g.addEdge("5-1", "5", "1");
	g.addEdge("1-3", "1", "3");
	g.addEdge("1-4", "1", "4");
	g.addAttribute("ui.stylesheet", "node#1 { fill-color: #A901DB; size: 15px, 15px; } node#2 { fill-color: #04B4AE; size: 15px, 15px; } node#3 { fill-color: #3ADF00; size: 15px, 15px; } node#4 { fill-color: #FFBF00; size: 15px, 15px; } node#5 { fill-color: #B40404; size: 15px, 15px; }" );
	
	String formato = "";
	for(int i = 1; i <= 10; i++){
		int grado = graph.getNode(Integer.toString(i)).getOutDegree();
		switch(grado){
			case 0: 
				formato += "node#" + i + " {fill-color: #FA5882;} ";
				break;
			case 1:
				formato += "node#" + i + " {fill-color: #0080FF;} ";
				break;
			case 2:
				formato += "node#" + i + " {fill-color: #B40404;} ";
				break;
			case 3:
				formato += "node#" + i + " {fill-color: #04B404;} ";
				break;
			default: 
				formato += "node#" + i + " {fill-color: #D7DF01;} ";
		}
		
		
	}
	graph.addAttribute("ui.stylesheet", formato);
	g.display();

	graph.display();
	}
}

//javac -cp .:LISTAJAR P1.java
// java -cp .:LISTAJAR P1
//javac -cp .:../lib/gs-core-1.3.jar P1.java
//java -cp .:../lib/gs-core-1.3.jar P1
