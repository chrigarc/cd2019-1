/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.graph;

import java.util.Random;
import lombok.Data;


@Data public class Message {
    
    public static Integer idCount = -1;    
    private Integer id;
    private Integer finalDestUID;
    private Integer TTL = 100;
    private Integer initialNode;
    protected Integer intentos;
    protected boolean arrivo;
    
    public Message(Integer finalDestUID, Integer initialNode){
        this.id = ++idCount;
        this.finalDestUID = finalDestUID;
        this.initialNode = initialNode;
        this.intentos = 20;
        this.arrivo = false;        
    }
    

}
