/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peer.to.peer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author babar
 */
public class serverThread implements Runnable{
    private DatagramSocket socket;
    private boolean running;
    
    
    
    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }
    public void stop(){
        running = false;
        socket.close();
    }
    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        try {
            socket = new DatagramSocket(12345);
        } catch (SocketException ex) {
            Logger.getLogger(serverThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
        running = true;
        while(true){
            try {
                socket.receive(packet);
                String msg = new String(buffer,0,packet.getLength());  
                System.out.println(msg);
            } catch (IOException ex) {
                Logger.getLogger(serverThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
                      
    }
    public void sendTo(InetSocketAddress address,String msg) throws IOException{
           byte[] buffer = msg.getBytes();
           DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
           packet.setSocketAddress(address);
           
           socket.send(packet);
           
        }
    
}
