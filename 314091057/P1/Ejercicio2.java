import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.*;
import java.util.Iterator;
import java.util.Random;

public class Ejercicio2 {

		public static void main(String args[])
		{
				Graph graph = getGraph(100);
				pintaGrafica(graph);
				graph.display();
		}

		/**
		 * Genera una gráfica con un número de nodos dados
		 *
		 * @param int nodes - el número de nodos
		 *
		 * @return graph - la gráfica creada
		 */
		public static Graph getGraph(int nodes)
		{
				Graph graph = new SingleGraph("Grafica");
				Generator gen = new RandomGenerator(1);
				gen.addSink(graph);
				gen.begin();
				while(graph.getNodeCount() < nodes && gen.nextEvents());
				gen.end();

				return graph;
		}

		/**
		 * Genera colores aleatorios
		 *
		 * @return - el color aleatorios
		 *
		 */
		public static String getRandomColor()
		{
				Random random = new Random();
				int n = random.nextInt(5);
				String[] colors = {"black","red","yellow","pink","green"};
				String color = colors[n];

				return color;
		}

		/**
		 * Pinta los nodos de una gráfica dada
		 *
		 * @param Graph graph - una gráfica a pintar
		 *
		 */
		public static void pintaGrafica(Graph graph)
		{
				Iterator<Node> ite = graph.getNodeIterator();

				while(ite.hasNext()) {

						Node node = ite.next();
						int degree = node.getDegree();

						if(degree > 0) {
								String color = getRandomColor();
								node.addAttribute("ui.style", "fill-color: " + color + ";");
						} else {
								node.addAttribute("ui.style", "fill-color: purple;");
						}

				}
		}

}
