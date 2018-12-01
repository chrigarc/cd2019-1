package lab.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NodeProcess extends Thread{
	private Integer id;
	private Set<Integer> vecinos;
	private MsgQueue queue;
	private Set<Integer> recipientes;

		/*
			0 - en ejecución
		  1 - recibio 5 mensajes
			2 - terminado después de su periodo de vida
			3 - terminado después de un error
			*/
	private Integer exitStatus = 0;

	public NodeProcess(Integer id, Set<Integer> vecinos, Set<Integer> recipientes){
		this.id = id;

		this.vecinos = new HashSet<Integer>();
		this.vecinos.addAll(vecinos);

		this.recipientes = new HashSet<Integer>();
		this.recipientes.addAll(recipientes);

		queue = MsgQueue.getInstance();
	}

	public void run(){
        System.out.println("Proceso " + id + " ha iniciado");

        //mata al proceso si no tiene vecinos
        if(vecinos.isEmpty()){
        	System.out.println("Proceso " + id + " no tiene vecinos, detenido");
        	exitStatus = 2;
        	return;
        }

        Integer received = 0;
        Integer lifetime = 100;

        try{
        	while(true){
        		// Envia mensajes
        		for(Integer destFinal : recipientes){
        			if(queue.send(new Message(destFinal), getRandNeighbor())){
        				recipientes.remove(destFinal);
        				break;
        			}
        		}

        		// recibe mensajes
        		Message msg = queue.receive(id);

        		if(msg != null){
        			if(msg.destFinal == id){
        				// el mensaje ha alcanzado su destinatario final
        				received++;
        			}else{
        				if(msg.TTL > 0){
        					// escala el TTL del mensaje
        					msg.TTL--;
        					// vuelve a enviar el mensaje pero a un vecino aleatorio
        					queue.send(msg, getRandNeighbor());
        				}
        			}
        		}

        		// verifica que los mensajes se han recibido
        		if(received == 1){
					System.out.println("Proceso " + id + " ha recibido todos los mensajes y terminó exitosamente");
					exitStatus = 1;
					break;
				}

        		// verifica el periodo de vida del proceso
        		lifetime--;
            	if(lifetime == 0){
            		System.out.println("Proceso " + id + " ha agotado su periodo de vida");
            		exitStatus = 2;
            		break;
            	}

            	sleep(100);
            }
        }catch(Exception e){
        	System.err.println("Proceso " + id + " terminado: " + e.getMessage());
        	exitStatus = 3;
        }
    }

	private Integer getRandNeighbor(){

		int stop = queue.getRandom(vecinos.size());
		Integer [] all = (Integer[]) vecinos.toArray(new Integer[0]);
		return all[queue.getRandom(vecinos.size())];
	}

	public Integer getexitStatus() {
		return exitStatus;
	}

	public Integer getid(){
		return id;
	}
}
