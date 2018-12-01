import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
**
**
**
** CLASE HECHA  POR Mauricio Javier Salas Martínez.
**
**
**
**
*/
public class Transport{

    private static Transport transpota;
    private static HashMap<String, List<Message>> mandaMensaje;

    public boolean put(Message envioMensaje, String destino){
        boolean valida = false;
        if(mandaMensaje.containsKey(destino)){
            if(envioMensaje.estaVivo()){
                valida = mandaMensaje.get(destino).add(envioMensaje);
            }
        }else{
            List<Message> listaGrafica = new ArrayList<>();
            if(envioMensaje.estaVivo()){
                valida = listaGrafica.add(envioMensaje);
            }
            mandaMensaje.put(destino, listaGrafica);
        }
	sleep(200);
	return valida;
    }
    public static Transport getInstance(){
        if(transpota == null){
            transpota = new Transport();
        }
            mandaMensaje = new HashMap<>();
        return transpota;
    }    
    
    private void sleep(int num){
       try{
           Thread.sleep(num);
       }catch(Exception ex){
       }
    }
    
    public Message pop(String cadena){
        Message mensaje = null;
        if(mandaMensaje.containsKey(cadena)){
            List<Message> listaGrafica = mandaMensaje.get(cadena);
            if(!listaGrafica.isEmpty()){
                mensaje = listaGrafica.remove(listaGrafica.size() - 1);
            }
        }
	sleep(300);
	return mensaje;
    }

}