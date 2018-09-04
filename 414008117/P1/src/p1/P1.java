/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author emmanuel
 */
public class P1 {
    
    public static void ejercicio1(){
        Graph graph = new SingleGraph("Tutorial 1");
        
        int orden; //Orden de la gráfica
        Random rn = new Random();
        orden = rn.nextInt(6)+5; //El orden estará entre 5 y 10
        Integer i,j; //Iteradores
        
        //En esta parte agregamos los nodos a la gráfica
        for(i=0;i<orden;i++){
            graph.addNode(i.toString());
        }
        
        //En esta parte agregamos las aristas
        for(i=0;i<orden;i++){
            for(j=i+1;j<orden;j++){
                int pro = rn.nextInt(4); //Se añaden las aristas con 1/4 de probabilidad por cada par de nodos.
                if(pro == 0){
                    graph.addEdge(i.toString()+","+j.toString(), i.toString(), j.toString());
                }
            }
        }
        
        //En esta parte nos aseguramos de que no haya nodos de grado 0.
        for(Node n:graph){
            if(n.getDegree()==0){
                Node temp = graph.getNode(rn.nextInt(orden));
                graph.addEdge(n.getId()+","+temp.getId(),n,temp);
            }
        }
        
        //En esta parte pintamos todos los nodos.
        for(Node n:graph){
            Integer r = rn.nextInt(256);
            Integer g = rn.nextInt(256);
            Integer b = rn.nextInt(256);
            
            String colorstr = "rgb("+r+","+g+","+b+");";
            
            n.addAttribute("ui.style", "fill-color: "+colorstr);
        }
        
        graph.display();
    }
    
    public static void ejercicio2(){
        Graph graph = new SingleGraph("Tutorial 1");
        
        int orden; //Orden de la gráfica
        Random rn = new Random();
        orden = rn.nextInt(11)+10; //El orden estará entre 10 y 20
        Integer i,j; //Iteradores
        
        //En esta parte agregamos los nodos a la gráfica
        for(i=0;i<orden;i++){
            graph.addNode(i.toString());
        }
        
        //En esta parte agregamos las aristas
        for(i=0;i<orden;i++){
            for(j=i+1;j<orden;j++){
                int pro = rn.nextInt(4); //Se añaden las aristas con 1/4 de probabilidad por cada par de nodos.
                if(pro == 0){
                    graph.addEdge(i.toString()+","+j.toString(), i.toString(), j.toString());
                }
            }
        }
        
        //En esta parte pintamos la gráfica.
        for(Node n:graph){
            switch(n.getDegree()){
                case 0: n.addAttribute("ui.style", "fill-color: pink;");
                break;
                
                case 1: n.addAttribute("ui.style", "fill-color: blue;");
                break;
                
                case 2: n.addAttribute("ui.style", "fill-color: red;");
                break;
                
                case 3: n.addAttribute("ui.style", "fill-color: green;");
                break;
                
                default: n.addAttribute("ui.style", "fill-color: yellow;");
                break;
            }
        }
        
        graph.display();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Elija la opción 1 o 2:");
        int op = sc.nextInt();
        
        switch(op){
            case 1: ejercicio1();
            break;
            
            case 2: ejercicio2();
            break;
            
            default: System.out.println("Esa opción no es válida :'v");
        }
    }
    
}
