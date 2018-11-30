import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente{

	public static void main(String [] args){
		//Especificamos el mensaje que se enviará que es el no de cuenta.
        Cliente cliente = new Cliente("312242930");
	}

	public Cliente(String mensaje){
        try{
            
			// Se instancia un Dato y se convierte a bytes[]
            Dato datoEnviado = new Dato(mensaje);
            byte[] bytesDato = datoEnviado.toByteArray();
            //enviamos infinitamente el mensaje.
            while(true){
                DatagramSocket socket = new DatagramSocket();
	            // Se meten los bytes en el DatagramPacket, que es lo que se
	            // va a enviar por el socket.
	            // El destinatario es el servidor.
	            // El puerto es por el que esté escuchando el servidor (25000).
                DatagramPacket paquete = new DatagramPacket(bytesDato,bytesDato.length, InetAddress.getByName(Puertos.HOST_SERVIDOR),Puertos.PUERTO_DEL_SERVIDOR);
                System.out.println("Envio mensaje "+ mensaje +" al servidor "+ Puertos.HOST_SERVIDOR +" - "+Puertos.PUERTO_DEL_SERVIDOR);
                socket.send(paquete);//enviamos el paquete
                Thread.sleep(1000);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}