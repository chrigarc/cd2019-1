package lab.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import lombok.Data;

@Data public class NodeProcess extends Thread{
    private LinkedList<Integer> pasoPor;
    private Integer uid;
    private Set<Integer> neighbors;
    private MsgQueue queue;
    private Set<Integer> recepients;
    
    private Integer exitState = 0; //0 - is running, 1 - received 5 messages, 2 - died by lifetime, 3 - died by an error
    
    public NodeProcess(Integer uid, Set<Integer> neighbors, Set<Integer> recepients){
        this.uid = uid;
        
        pasoPor = new LinkedList<Integer>();
        
        this.neighbors = new HashSet<Integer>();
        this.neighbors.addAll(neighbors);
        
        this.recepients = new HashSet<Integer>();
        this.recepients.addAll(recepients);
        
        queue = MsgQueue.getInstance();
    }
    
    public LinkedList<Integer> getListaMsg(){
        return this.pasoPor;
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
        Integer lifetime = 100;
        
        try{
            while(true){
                //**************** SEND MESSAGE *****************
                for(Integer finalDestUID : recepients){
                    if(queue.send(new Message(finalDestUID), getRandNeighbor(),this.uid)){
                        recepients.remove(finalDestUID);
                        
                        break;
                    }
                }
                
                //**************** RECEIVE MESSAGE **************
                Message msg = queue.receive(uid);
                
                if(msg != null){
                    if(msg.getFinalDestUID() == uid){
                        //msg reached its destination
                        received++;
                        pasoPor.add(msg.getFinalDestUID());
                    }else{
                        if(msg.getTTL() > 0){
                            //scale down message TTL
                            int x = 1;
                            msg.setTTL(msg.getTTL()-1);
                            pasoPor.add(msg.getFinalDestUID());
                            //resend same msg to the randomly selected neighbor
                            queue.send(msg, getRandNeighbor(),this.uid);
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
                
                sleep(100);
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
}
