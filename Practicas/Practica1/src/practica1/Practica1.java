/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Bibliotecas necesarias para compilar

package practica1;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
/**
 *
 * @author gibranaguilar
 */
public class Practica1 {



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Listas Auxiliares para guardar nodos sin vecinos y con vecinos
       List<Integer> ListaCero = new ArrayList<Integer>();
       List<Integer> ListaPositivos = new ArrayList<Integer>();


       int auxcero=0;
       int auxpositivo=0;
       String cero="";
       String positivos="";

       //Creacion de una grafica con 5 nodos y diferentes colores.
       Graph graph = new SingleGraph("Practica1");
       graph.addNode("A");
       graph.addNode("B");
       graph.addNode("C");
       graph.addNode("D");
       graph.addNode("E");
       graph.addEdge("AB","A","B");
       graph.addEdge("BC","B","C");
       graph.addEdge("CA","C","A");
       graph.addEdge("DE","D","E");
       graph.addEdge("AE","A","E");
       Node A = graph.getNode(0);
       //Pintas los nodos de diferentes colores.
       graph.addAttribute("ui.stylesheet","node#A {fill-color:purple;} "
               + "node#B {fill-color:red;}"
               + "node#C {fill-color:yellow;}"
               + "node#D {fill-color:green;}");

       //Creacion de grafica aleatoria.
       Graph graphRandom= new SingleGraph ("GraficaRandom");
       //Despliegas grafica
       graph.display();

       //Creacion de grafica aleatoria.
       for(int i=0;i<10;i++){

            graphRandom.addNode(String.valueOf(i));

       }

          for(int i=0;i<10;i++){

            int numerorandom =(int) (Math.random()*10);
            int numerorandom2 =(int) (Math.random()*9);
            //System.out.println(numerorandom);

            Edge B = graphRandom.getEdge(String.valueOf(numerorandom2)+String.valueOf(i));//String.valueOf(numerorandom2)+String.valueOf(i)
            System.out.println( B );

            if(numerorandom<5 && i<8 && B==null){

                 graphRandom.addEdge(String.valueOf(i)+String.valueOf(numerorandom2),String.valueOf(i),String.valueOf(numerorandom2));

            }
       }

       //Creacion de lista con nodos sin vecinos y con vecinos.
       for(int i=0;i<10;i++){

        Node aux = graphRandom.getNode(i);
        System.out.println(aux.getDegree());


           if(aux.getDegree()==0){

               ListaCero.add(i);
               auxcero+=1;

           }else{

               auxpositivo+=1;
               ListaPositivos.add(i);

           }


       }

      //Coloreo de los conjuntos dados con las restricciones dadas.
 for (int i = 0; i <= ListaCero.size() - 1; i++) {
            cero=cero+"node#"+ ListaCero.get(i)+ "{fill-color:purple;} ";
        }

 for (int i = 0; i <= ListaPositivos.size() - 1; i++) {
            positivos=positivos+"node#"+ ListaPositivos.get(i)+ "{fill-color:#"+String.valueOf(i*i)+"AF;}";
        }

        System.out.println(cero);
        System.out.println(positivos);
        graphRandom.addAttribute("ui.stylesheet",cero+positivos);
        graphRandom.display();

    }
}
