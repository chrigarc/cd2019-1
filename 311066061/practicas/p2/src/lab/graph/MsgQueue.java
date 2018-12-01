package lab.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MsgQueue {

    private static MsgQueue inst;
    private Map<Integer, Message> msj;
    private Random random;

    /**
    * Crea una instancia de paso de mensajes
    */
    public static synchronized MsgQueue getInstance() {
        if (inst == null) {
            inst = new MsgQueue();
            inst.msj = new HashMap<Integer, Message>();
            inst.random = new Random();
        }
        return inst;
    }

    /**
    * Envia el mensaje msj al destinatario dest
    */
    public synchronized boolean send(Message msj, Integer dest){
        if (msj.containsKey(dest)) return false;
        msj.put(dest, msj);
        System.out.println("mensaje enviado a " + dest);
        return true;
    }

    /**
    * El destinatario dest recibe un mensaje
    */
    public synchronized Message receive(Integer dest){
        if(!msj.containsKey(dest)) return null;
        System.out.println("mensaje recibido por " + dest);
        return msj.remove(dest); // elimina el destinatario una vez que le llegó el mensaje
    }

    /**
    * Genera un número aleatorio (Integer) en el rango (0,max)
    */
    public synchronized Integer getRandom(Integer max){
        return random.nextInt(max);
    }
}
