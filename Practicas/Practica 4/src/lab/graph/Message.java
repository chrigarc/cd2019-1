/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.graph;

import java.util.Random;
import lombok.Data;


@Data public class Message {
    
    private Integer finalDestUID = null;
    private Integer TTL = 10;
    
    public Message(Integer finalDestUID){
        this.finalDestUID = finalDestUID;
    }
}
