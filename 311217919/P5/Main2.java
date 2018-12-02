import java.util.Iterator;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.algorithm.generator.*;
import org.graphstream.algorithm.Algorithm;
import java.util.Scanner;



public class Main2{
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Grafica g2 = new Grafica("Recibo");
    int n;
    System.out.println("Indique el puerto de llegada:  ");
    n = sc.nextInt();
    Recibemsn rmsn = new Recibemsn(n);

    rmsn.receiveMsn();
  }

}
