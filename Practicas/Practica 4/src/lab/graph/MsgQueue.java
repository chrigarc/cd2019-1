/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package lab.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


public class MsgQueue {
    
    private static MsgQueue instance;
    private Map<Integer, Message> msgs;
    private Random rand;
    
    public static synchronized MsgQueue getInstance() {
        if (instance == null) {
            instance = new MsgQueue();
            instance.msgs = new HashMap<Integer, Message>();
            instance.rand = new Random();
        }
        return instance;
    }
    
    public synchronized boolean send(Message msg, Integer destUID,int nodoId){
        if (msgs.containsKey(destUID)) return false;
        msgs.put(destUID, msg);
        
        //Agrega a mi nodo el mensaje con su lista de nodos recorridos el primero.
        if(App.m == 0){
            if(!App.nodosPasados1.containsKey(msg.hashCode())){
                App.nodosPasados1.put(msg.hashCode(), new LinkedList<Integer>());
                App.nodosPasados1.get(msg.hashCode()).add(nodoId);
            }
        }else{
            if(!App.nodosPasados2.containsKey(msg.hashCode())){
                App.nodosPasados2.put(msg.hashCode(), new LinkedList<Integer>());
                App.nodosPasados2.get(msg.hashCode()).add(nodoId);
            }
        }
        //System.out.println("msg sent to " + destUID);
        return true;
    }
    
    public synchronized Message receive(Integer destUID){
        
        
        
        if(!msgs.containsKey(destUID)) return null;
        //System.out.println("msg received by " + destUID);
        Message msg = msgs.remove(destUID);
        if(App.m == 0){
            if(!App.nodosPasados1.containsKey(msg.hashCode())){
                App.nodosPasados1.put(msg.hashCode(), new LinkedList<Integer>());
                App.nodosPasados1.get(msg.hashCode()).add(destUID);
            }else{
                App.nodosPasados1.get(msg.hashCode()).add(destUID);
            }
        }else{
             if(!App.nodosPasados2.containsKey(msg.hashCode())){
                App.nodosPasados2.put(msg.hashCode(), new LinkedList<Integer>());
               App.nodosPasados2.get(msg.hashCode()).add(destUID);
            }else{
                 App.nodosPasados2.get(msg.hashCode()).add(destUID);
             }
                
        }
        return msg;
    }
    
    public synchronized Integer getRandom(Integer max){
        return rand.nextInt(max);
    }
}
