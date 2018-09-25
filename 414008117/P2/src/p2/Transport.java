/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;

public class Transport{

    private static Transport transport;
    Message mensaje;

    public static Transport getInstance(){
	if(transport == null){
	    transport = new Transport();
	}
	return transport;
    }

    public boolean put(Message m, String destination){
	sleep(200);
        boolean c = false;
        mensaje = m;
        mensaje.tvida--;
	return c;
    }

    public Message pop(String n){
	sleep(300);
        if(n == mensaje.destino){
            return mensaje;
        }else{
            return null;
        }
    }

    private void sleep(int ms){
	try{
	    Thread.sleep(ms);
	}catch(Exception ex){
        }
    }
}