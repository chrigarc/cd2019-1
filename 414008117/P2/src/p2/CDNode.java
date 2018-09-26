/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;

import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import java.util.Iterator;
import java.util.Collection;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.LinkedList;

public class CDNode extends JLabel implements Runnable{

    public static final String COLOR_DEFAULT = "blue";
    public static final String COLOR_SEND = "red";
    public static final String COLOR_READ = "green";
    
    
    Node node;
    private boolean activo;
    private Transport transport;
    private CDGraph graph;
    String tm = ""; //Texto del mensaje que recibió.

    public CDNode(CDGraph g,Node n){
	super();
	this.node = n;
	this.activo = true;
	transport = Transport.getInstance();
	this.graph = g;
	this.setFillColor(COLOR_DEFAULT);
    }

    /**¿Qué necesitas que haga para que un nodo genere
     * un mensaje en cada iteración y lo mande a todos
     * sus vecinos, y despues lea un mensaje de y lo reenvie
     * a todos sus vecinos siempre y cuando su tiempo de 
     * vida, del mensaje, lo permita?
    **/
    public void run(){
        Message men = new Message("Mensaje de "+node.getId());
        //En esta parte envío el mensaje que yo creé a todos los vecinos.
        for(Node nodo:getVecinos()){
            String idvec = nodo.getId();
            sendMessage(men,idvec);
        }
        
        Message men2 = readMessage();
        
        //En esta parte envío el mensaje que recibí a los demás.
        if(men2 != null && men2.tvida>0){
            tm = men2.contenido;
            for(Node nodo:getVecinos()){
                if(men2.tvida>0){
                    sendMessage(men2,nodo.getId());
                }
            }
        }else{
            tm = "";
        }
    }

    /**
     * Obtiene la lista de vecinos de este nodo.
     * @return
     */
    public LinkedList<Node> getVecinos(){
        LinkedList<Node> lista = new LinkedList();
        
        Collection<Edge> col = node.getLeavingEdgeSet();
        for(Edge e:col){
            Node n0 = e.getNode0();
            Node n1 = e.getNode1();
            
            if(!lista.contains(n0) && !n0.equals(node)){
                lista.add(n0);
            }
            
            if(!lista.contains(n1) && !n1.equals(node)){
                lista.add(n1);
            }
        }
        System.out.println("Vecinos de "+node.getId()+": \n"+lista);
        return lista;
    }
    
    public String getText(){
	String s = super.getText();
	if(node!=null){
	    s+="ID: " + node.getId()+tm;
	}
	return s;
    }
 
    @Override
    public String toString(){
        return node.getId();
    }
    
    public boolean sendMessage(Message m, String destination){
        m.origen = node.getId();
        m.destino = destination;
	this.setFillColor(COLOR_SEND);
	boolean status = transport.put(m, destination);
	this.setFillColor(COLOR_DEFAULT);
	return status;
    }

    public Message readMessage(){
        this.setFillColor(COLOR_READ);
        Message m = transport.pop(node.getId());
	this.setFillColor(COLOR_DEFAULT);
	return m;
    }


    public void stop(){
	this.activo = false;
    }

    private synchronized void  setFillColor(String color){
	this.graph.addChangeColor(this.node.getId(), color);
	Color c = Color.BLUE;
	switch(color){
	case "red":
	    c = Color.RED;
	    break;
	case "green":
	    c = Color.GREEN;
	    break;
	}
	setForeground(c);
    }
    
    private void sleep(int ms){
	try{
	    Thread.sleep(ms);
	}catch(Exception ex){
	}	   
    }
}