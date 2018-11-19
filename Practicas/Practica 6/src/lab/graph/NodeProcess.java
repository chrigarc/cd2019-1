package lab.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import lombok.Data;

@Data public class NodeProcess extends Thread{
    private Integer uid;
    private Set<Integer> neighbors;
    private MsgQueue queue;
    private Set<Integer> recepients;
    
    private Integer exitState = 0; //0 - is running, 1 - received 5 messages, 2 - died by lifetime, 3 - died by an error
    
    public NodeProcess(Integer uid, Set<Integer> neighbors, Set<Integer> recepients){
        this.uid = uid;
        
        this.neighbors = new HashSet<Integer>();
        this.neighbors.addAll(neighbors);
        
        this.recepients = new HashSet<Integer>();
        this.recepients.addAll(recepients);
        
        queue = MsgQueue.getInstance();
    }
    
    public void run(){
        
        //System.out.println("Process " + uid + " started");
        
        //kill process if it has no neighbors
        if(neighbors.isEmpty()){
            //System.out.println("Process " + uid + " nas no neighbors, stopped");
            exitState = 2;
            return;
        }
        
        Integer received = 0;
        Integer lifetime = 1000;
        
        try{
            enviaMsg(null);
            while(true){
                //**************** SEND MESSAGE *****************
                
                
                //**************** RECEIVE MESSAGE **************
                Message msg = queue.receive(uid);
                
                if(msg != null){
                    if(msg.getFinalDestUID() == uid){
                        //msg reached its destination
                        received++;
                        msg.setArrivo(true);
                        App.mensajesCompletos.put(msg.getId(), msg);
                    }else{
                        if(msg.getTTL() > 0){
                            //scale down message TTL
                            msg.setTTL(msg.getTTL()-1);
                            //resend same msg to the randomly selected neighbor
                            queue.send(msg, getRandNeighbor());
                        }
                        //if msg.TTL == 0 - the message has not reached its target within 10 tries and shell be forgotten
                    }
                }
                
                //**************** CHECK RECEIVED ****************
                if(received == 1){
                    //System.out.println("Process " + uid + " received all messages and finished");
                    exitState = 1;
                    break;
                }
                
                //**************** CHECK LIFETIME ****************
                lifetime--;
                if(lifetime == 0){
                    //System.out.println("Process " + uid + " lifetime finished");
                    exitState = 2;
                    break;
                }
                
                sleep(10);
            }
        }catch(Exception e){
            //System.err.println("Process " + uid + " died: " + e.getMessage());
            exitState = 3;
        }
    }
    
    private Integer getRandNeighbor(){
        
        int stop = queue.getRandom(neighbors.size());
        Integer [] all = (Integer[]) neighbors.toArray(new Integer[0]);
        return all[queue.getRandom(neighbors.size())];
    }
    
    public Integer getExitState() {
        return exitState;
    }
    
    public Integer getUid(){
        return uid;
    }
    
    public void enviaMsg(Message msg){
        if(msg == null){
            for(Integer finalDestUID : recepients){
                Message msg2 = new Message(finalDestUID, this.uid);
                if(queue.send(msg2, getRandNeighbor())){
                    recepients.remove(finalDestUID);
                    App.mensajesEnviados.put(msg2.getId(), msg2);
                    break;
                }
            }
        }else{
            for(Integer finalDestUID : recepients){
                if(queue.send(msg, getRandNeighbor())){
                    recepients.remove(finalDestUID);
                    App.mensajesEnviados.put(msg.getId(), msg);
                    break;
                }
            }
        }
    }
}
