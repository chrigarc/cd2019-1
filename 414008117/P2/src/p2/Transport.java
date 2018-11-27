/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;

import java.util.LinkedList;
import java.util.HashMap;

public class Transport{

    private static Transport transport;
    Message mensaje;
    HashMap<String, LinkedList<Message>> buzones;
    
    public Transport(){
        //inicializar buzones
        buzones = new HashMap();
    }
    
    public static Transport getInstance(){
	if(transport == null){
	    transport = new Transport();
	}
	return transport;
    }
    
    public boolean put(Message m, String destination){
	sleep(200);
        boolean status = false;

        LinkedList<Message> temp = buzones.get(destination);
        
        if(temp == null){ //La lista no existe.
            temp = new LinkedList(); //Creamos la lista
            m.tvida--; //Le restamos vida al mensaje.
            temp.add(m); //Agregamos el mensaje a la lista.
            buzones.put(destination, temp); //Colocamos la lista en la caja de buzones.
            status = true;
        }else{ //Si la lista ya existe, solo colocamos al final de esta al mensaje.
            m.tvida--; //Le restamos vida al mensaje.
            temp.add(m);
            status = true;
        }
	return status;
    }

    public Message pop(String n){
	sleep(300);
        Message m = null;
        LinkedList<Message> temp = buzones.get(n);
        
        if(temp != null && !temp.isEmpty()){
            m = temp.removeFirst();
        }
        
        return m;
    }

    private void sleep(int ms){
	try{
	    Thread.sleep(ms);
	}catch(Exception ex){
        }
    }
}