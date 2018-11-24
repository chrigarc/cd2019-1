/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;

/*
La clase es una estructura de datos con un tiempo de vida 100, que conoce el id del destino y el origen
*/
public class Message{
    
    int tvida;
    String origen;
    String destino;
    String contenido;

    public Message(String source){
        tvida = 100;
        contenido = source;
    }
    
}